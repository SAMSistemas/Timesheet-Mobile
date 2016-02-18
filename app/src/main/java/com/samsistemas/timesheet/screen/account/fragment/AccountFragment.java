package com.samsistemas.timesheet.screen.account.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.fragment.BaseFragment;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.screen.account.presenter.AccountPresenterImpl;
import com.samsistemas.timesheet.screen.account.presenter.base.AccountPresenter;
import com.samsistemas.timesheet.screen.account.view.AccountView;
import com.samsistemas.timesheet.screen.menu.activity.MenuActivity;
import com.samsistemas.timesheet.utility.DeveloperUtility;
import com.samsistemas.timesheet.utility.PreferenceUtility;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author jonatan.salas
 */
public class AccountFragment extends BaseFragment implements AccountView {
    private static final String SAM_DOMAIN = "@samsistemas.com.ar";
    private AccountPresenter presenter;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account, container, false);
        ButterKnife.bind(this, view);

        presenter = new AccountPresenterImpl();
        presenter.setAccountView(this);

        return view;
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
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
        presenter.styleBar("");
        presenter.setAccountData(PreferenceUtility.getSessionId(getContext()));
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

    @Override
    public void navigateToHome() {
        final Intent intent = new Intent(getContext(), MenuActivity.class);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                        IntentCompat.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        startActivity(intent);
        getActivity().finish();
    }
}
