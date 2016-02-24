package com.samsistemas.timesheet.screen.addhours.presenter;

import android.support.annotation.NonNull;

import com.jonisaa.commons.presenter.BasePresenter;
import com.samsistemas.timesheet.screen.addhours.view.AddHoursView;

/**
 * @author jonatan.salas
 */
public class AddHoursPresenter extends BasePresenter<AddHoursView> {

    private AddHoursPresenter(@NonNull AddHoursView view) {
        super(view);
    }

    public static AddHoursPresenter getInstance(@NonNull AddHoursView view) {
        return new AddHoursPresenter(view);
    }
}
