package com.samsistemas.timesheet.view.account.view;

import android.support.annotation.Nullable;

import com.samsistemas.timesheet.domain.Person;

/**
 * @author jonatan.salas
 */
public interface AccountView {

    void styleActionBar(String title);

    void bindAccountInfo(@Nullable Person person);

    void navigateToHome();
}
