package com.samsistemas.timesheet.screen.menu.view;

import android.support.annotation.Nullable;

import com.samsistemas.timesheet.domain.Person;

/**
 * @author jonatan.salas
 */
public interface MainView {

    void addPersonDataToNavigationDrawer(@Nullable Person person);
}
