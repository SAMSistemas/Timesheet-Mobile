package com.samsistemas.timesheet.screen.account.view;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import com.samsistemas.timesheet.domain.Person;

/**
 * @author jonatan.salas
 */
public interface AccountView {

    void styleActionBar(ActionBar actionBar, String title);

    void bindAccountInfo(@Nullable Person person);
}
