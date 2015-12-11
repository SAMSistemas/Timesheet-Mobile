package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.AsyncTaskLoader;

import static com.samsistemas.timesheet.constant.SessionConst.*;
import com.samsistemas.timesheet.facade.PersonFacade;
import com.samsistemas.timesheet.model.Person;

/**
 * @author jonatan.salas
 */
public class PersonLoader extends AsyncTaskLoader<Person> {
//    protected static final String TAG = PersonLoader.class.getSimpleName();
    private final PersonFacade mFacade;
    private final Context mContext;

    public PersonLoader(Context context) {
        super(context);
        this.mFacade = PersonFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public Person loadInBackground() {
        final SharedPreferences prefs = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        Person person = mFacade.findById(mContext, prefs.getLong(USER_ID, 0));

        if ((null != person)
           && (null != person.getName())
           && (null != person.getLastName())
           && (null != person.getUsername())) {
            return person;
        } else {
            return loadInBackground();
        }
    }
}
