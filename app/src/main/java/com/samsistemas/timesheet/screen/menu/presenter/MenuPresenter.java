package com.samsistemas.timesheet.screen.menu.presenter;

import android.support.annotation.NonNull;

import com.jonisaa.commons.presenter.BasePresenter;
import com.samsistemas.timesheet.screen.menu.view.MenuView;

/**
 * @author jonatan.salas
 */
public class MenuPresenter extends BasePresenter<MenuView> {

    private MenuPresenter(@NonNull MenuView view) {
        super(view);
    }

    public static MenuPresenter getInstance(@NonNull MenuView view) {
        return new MenuPresenter(view);
    }
}
