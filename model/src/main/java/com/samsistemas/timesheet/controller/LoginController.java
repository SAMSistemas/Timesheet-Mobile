package com.samsistemas.timesheet.controller;

import android.content.Context;
import android.support.annotation.NonNull;

import com.samsistemas.timesheet.controller.base.BaseLoginController;

/**
 * Login controller implementation.
 *
 * @author jonatan.salas
 */
public class LoginController implements BaseLoginController {

    @Override
    public boolean performLogin(@NonNull final Context context, @NonNull final String[] credentials) {
        return true;
    }
}
