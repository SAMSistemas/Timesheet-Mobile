package com.samsistemas.timesheet.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.samsistemas.timesheet.facade.PersonFacade;
import com.samsistemas.timesheet.model.Person;

/**
 * @author jonatan.salas
 */
public class PersonLoader extends AsyncTaskLoader<Person> {
    protected PersonFacade mFacade;
    protected Context mContext;
    protected Person mPerson;
    private long personId;

    public PersonLoader(Context context) {
        super(context);
        this.mFacade = PersonFacade.newInstance();
        this.mContext = context;
    }

    @Override
    public Person loadInBackground() {
        mPerson = mFacade.findById(mContext, getPersonId());
        return (null != mPerson) ? mPerson : null;
    }

    public PersonLoader setPersonId(long personId) {
        this.personId = personId;
        return this;
    }

    public long getPersonId() {
        return personId;
    }
}
