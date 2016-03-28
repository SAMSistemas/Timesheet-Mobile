package com.samsistemas.timesheet.screen.login.interactor;

import com.orm.SugarRecord;
import com.samsistemas.timesheet.domain.Project;
import com.samsistemas.timesheet.domain.TaskType;
import com.samsistemas.timesheet.screen.login.callback.LoginCallBack;
import com.samsistemas.timesheet.screen.login.callback.PersonCallBack;
import com.samsistemas.timesheet.screen.login.callback.ProjectCallback;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.domain.Session;

import com.samsistemas.timesheet.screen.login.callback.TaskTypeCallback;
import com.samsistemas.timesheet.screen.login.interactor.base.LoginInteractor;
import com.samsistemas.timesheet.screen.login.listener.OnCreateSessionListener;
import com.samsistemas.timesheet.screen.login.listener.OnLoginFinishedListener;
import com.samsistemas.timesheet.screen.login.validation.EmailValidator;
import com.samsistemas.timesheet.screen.login.validation.PasswordValidator;

import java.util.List;

import static com.orm.SugarRecord.save;

import static com.samsistemas.timesheet.utility.ThreadUtility.runInBackground;
import static com.samsistemas.timesheet.utility.ThreadUtility.CallBack;

/**
 * @author jonatan.salas
 */
public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final String username,
                      final String password,
                      final OnLoginFinishedListener listener,
                      final OnCreateSessionListener sessionListener) {
        boolean error = false;

        if (!EmailValidator.newInstance().validate(username)) {
            listener.onUsernameError();
            error = true;
        }

        if (!PasswordValidator.newInstance().validate(password)) {
            listener.onPasswordError();
            error = true;
        }

        if (!error) {
            final Boolean login = runInBackground(new LoginCallBack(username, password));

            if (login) {
                if (null != sessionListener) {
                    final Person person = runInBackground(new PersonCallBack(username, password));
                    save(person);

                    final Long id = runInBackground(new CallBack<Long>() {
                        @Override
                        public Long execute() {
                            Session session = new Session()
                                    .setActive(true)
                                    .setPerson(person);

                            return save(session);
                        }
                    });

                    final List<TaskType> taskTypes = runInBackground(new TaskTypeCallback(username, password, person.getWorkPosition()));
                    final List<Project> projects = runInBackground(new ProjectCallback(username, password));

                    for (Project project: projects) {
                        save(project.getClient());
                    }

                    SugarRecord.saveInTx(projects);

                    for (int i = 0; i < taskTypes.size(); i++) {
                        save(taskTypes.get(i));
                    }

                    sessionListener.onSessionCreate(id);
                }

                listener.onLoginSuccess();
            } else {
                listener.onLoginFailure();
            }
        }
    }
}
