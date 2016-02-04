package com.samsistemas.timesheet.commons.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.samsistemas.timesheet.commons.controller.ControllerImpl;
import com.samsistemas.timesheet.commons.controller.base.Controller;
import com.samsistemas.timesheet.commons.model.Person;

/**
 * @author jonatan.salas
 */
public class PersonLoader extends AsyncTaskLoader<Person> {
    private static final String LOG_TAG = PersonLoader.class.getSimpleName();
    private static final Class<Person> clazz = Person.class;
    private final Object lock = new Object();
    private Controller<Person> controller;
//    private final long id;

    public PersonLoader(Context context) {
        super(context);
        this.controller = new ControllerImpl<>();
//        final SharedPreferences pref = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
//        this.id = pref.getLong(USER_ID, 0);
    }

    @Override
    public Person loadInBackground() {
        Person person = null;

        if (controller.getCount(clazz) == 0) {
            try {
                synchronized (lock) {
                    lock.wait(3000);
//                    person = controller.findById(clazz, id);
                }
            } catch (InterruptedException ex) {
                Log.e(LOG_TAG, ex.getMessage(), ex.getCause());
            }
        } else {
            synchronized (lock) {
//                person = controller.findById(clazz, id);
                lock.notify();
            }
        }

        return person;
    }
}
