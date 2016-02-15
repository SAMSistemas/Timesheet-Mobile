package com.samsistemas.timesheet.screen.account.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.activity.MenuActivity;
import com.samsistemas.timesheet.common.utility.DeveloperUtility;
import com.samsistemas.timesheet.common.utility.PreferenceUtility;
import com.samsistemas.timesheet.domain.Person;
import com.samsistemas.timesheet.screen.account.fragment.ChangePasswordFragment;
import com.samsistemas.timesheet.screen.account.presenter.AccountPresenterImpl;
import com.samsistemas.timesheet.screen.account.presenter.base.AccountPresenter;
import com.samsistemas.timesheet.screen.account.view.AccountView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author jonatan.salas
 */
public class AccountActivity extends AppCompatActivity implements AccountView {
    private static final String SAM_DOMAIN = "@samsistemas.com.ar";
    private AccountPresenter presenter;
    private ActionBar actionBar;

    @Bind(R.id.tool_bar) Toolbar toolbar;
    @Bind(R.id.toolbar_layout) CollapsingToolbarLayout toolbarLayout;
    @Bind(R.id.username) TextView username;
    @Bind(R.id.email) TextView email;
    @Bind(R.id.work) TextView work;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        DeveloperUtility.enableStrictModeChecker();
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        final Long id = PreferenceUtility.getSessionId(getApplicationContext());

        actionBar = getSupportActionBar();
        presenter = new AccountPresenterImpl();
        presenter.setAccountView(this);
        presenter.styleBar("");
        presenter.setAccountData(id);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accounts, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_mode_edit:
                ChangePasswordFragment fragment = new ChangePasswordFragment();
                fragment.setContext(this);
                fragment.show(getSupportFragmentManager(), ChangePasswordFragment.TAG);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        presenter.onBackPressed();
    }

    @Override
    public void styleActionBar(String title) {
        actionBar.setTitle(title);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayUseLogoEnabled(false);
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
        final Intent intent = new Intent(getApplicationContext(), MenuActivity.class);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                        IntentCompat.FLAG_ACTIVITY_CLEAR_TASK |
                        Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        startActivity(intent);
        finish();
    }
}
