package com.samsistemas.timesheet.commons.activity.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

/**
 * Base activity class to use in this project
 *
 * @author jonatan.salas
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        setUserInterface();
        initialize();
        populateViews();
        setListeners();
    }

    /**
     * Method that returns the id of the layout file that we want to display to the user
     *
     * @return an int value representing the layout resource id
     */
    @LayoutRes
    public abstract int getLayoutResourceId();

    /**
     * Method that helps you to initialize the UI widgets to display
     */
    public abstract void setUserInterface();

    /**
     * Method that lets you initialize some values
     */
    public abstract void initialize();

    /**
     * Method used to display data in the views
     */
    public abstract void populateViews();

    /**
     * Method that sets the listeners needed for the view
     */
    public abstract void setListeners();
}
