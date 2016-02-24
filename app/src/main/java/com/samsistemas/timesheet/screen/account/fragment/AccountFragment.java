package com.samsistemas.timesheet.screen.account.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.jonisaa.commons.fragment.CallbackFragment;
import com.jonisaa.commons.utility.DeveloperUtility;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.screen.account.presenter.AccountPresenter;
import com.samsistemas.timesheet.screen.account.view.AccountView;
import com.samsistemas.timesheet.utility.PreferenceUtility;

import butterknife.Bind;

/**
 * @author jonatan.salas
 */
public class AccountFragment extends CallbackFragment<AccountPresenter> implements AccountView {
    private static final String SAM_DOMAIN = "@samsistemas.com.ar";
    private ActionBar actionBar;

    @Bind(R.id.tool_bar)
    Toolbar toolbar;

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;

    @Bind(R.id.username)
    TextView username;

    @Bind(R.id.email)
    TextView email;

    @Bind(R.id.work)
    TextView work;

    public AccountFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_account;
    }

    @Nullable
    @Override
    public View onViewCreated(@Nullable View view) {
        return view;
    }

    @NonNull
    @Override
    public AccountPresenter createPresenter() {
        return AccountPresenter.getInstance(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DeveloperUtility.enableStrictModeApi(true);

        final AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        if (null != toolbar) {
            getToolbarCallback().synchronize(toolbar);
        }

        actionBar = activity.getSupportActionBar();
        getPresenter().styleBar("");
        getPresenter().setAccountData(PreferenceUtility.getSessionId(getContext()));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_accounts, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
            case R.id.action_mode_edit:
                ChangePasswordFragment fragment = new ChangePasswordFragment();
                fragment.setContext(getActivity());
                fragment.show(getActivity().getSupportFragmentManager(), ChangePasswordFragment.TAG);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void styleActionBar(String title) {
        actionBar.setTitle(title);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void bindAccountInfo(@Nullable Person person) {
        if (null != person) {
            final String mail = person.getUsername() + SAM_DOMAIN;
            final String completeName = person.getName() + " " + person.getLastName();

            toolbarLayout.setTitle(completeName);
            username.setText(person.getUsername());
            email.setText(mail);
            work.setText(person.getWorkPosition().getDescription());

        } else {
            Snackbar.make(toolbar, "There's a trouble loading person data. Please, try again.", Snackbar.LENGTH_SHORT).show();
        }
    }
}
