package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.AsyncTaskLoader;

import com.samsistemas.timesheet.constant.SessionConst;
import com.samsistemas.timesheet.facade.PersonFacade;
import com.samsistemas.timesheet.model.Person;

/**
 * @author jonatan.salas
 */
public class PersonLoader extends AsyncTaskLoader<Person> implements SessionConst {
//    protected static final String TAG = PersonLoader.class.getSimpleName();
    protected PersonFacade mFacade;
    protected Context mContext;
    protected Person mPerson;

    public PersonLoader(Context context) {
        super(context);
        this.mFacade = PersonFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public Person loadInBackground() {
        final SharedPreferences prefs = mContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        mPerson = mFacade.findById(mContext, prefs.getLong(USER_ID, 0));

        if((null != mPerson) &&
           (null != mPerson.getName()) &&
           (null != mPerson.getLastName()) &&
           (null != mPerson.getUsername())) {
            return mPerson;
        } else {
            return loadInBackground();
        }
    }
}
