package com.samsistemas.timesheet.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.activity.base.BaseAppCompatActivity;
import com.samsistemas.timesheet.loader.PersonLoader;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.navigation.MenuNavigator;
import com.samsistemas.timesheet.util.DrawableUtil;
import com.samsistemas.timesheet.util.ToolbarUtil;
import com.samsistemas.timesheet.fragment.ChangePasswordFragment;

import java.lang.ref.WeakReference;

/**
 * @author jonatan.salas
 */
public class AccountActivity extends BaseAppCompatActivity {
    private static final int PERSON_LOADER_ID = 0;

    private CollapsingToolbarLayout mToolbarLayout;
    private TextView mUsername;
    private TextView mEmail;
    private TextView mWork;
    private TextView mEnterprise;
    private TextView mLocation;

    @Override
    @LayoutRes
    public int getLayoutResourceId() {
        return R.layout.activity_accounts;
    }

    @Override
    public void setUserInterface() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (null != actionBar)
            ToolbarUtil.styleWithBackButton(actionBar, "");

        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mUsername = (TextView) findViewById(R.id.username_textview);
        mEmail = (TextView) findViewById(R.id.email_textview);
        mWork = (TextView) findViewById(R.id.work_textview);
        mEnterprise = (TextView) findViewById(R.id.enterprise_textview);
        mLocation = (TextView) findViewById(R.id.location_textview);
    }

    @Override
    public void initialize() {
        getSupportLoaderManager().initLoader(PERSON_LOADER_ID, null, new LoaderManager.LoaderCallbacks<Person>() {

            @Override
            public Loader<Person> onCreateLoader(int id, Bundle args) {
                if (id == PERSON_LOADER_ID) {
                    return new PersonLoader(getApplicationContext());
                }

                return null;
            }

            @Override
            public void onLoadFinished(Loader<Person> loader, Person data) {
                if (null != data) {
                    String fullName = data.getName() + " " + data.getLastName();
                    mToolbarLayout.setTitle(fullName);

                    mUsername.setText(data.getUsername());
                    final String emailString = data.getUsername() + getApplicationContext().getString(R.string.domain);
                    mEmail.setText(emailString);
                    mWork.setText(data.getWorkPosition().getDescription());
                } else {
                    loader.reset();
                    Snackbar.make(mToolbarLayout, "Ops, we can't load your data now..", Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoaderReset(Loader<Person> loader) {
                loader.reset();
            }

        }).forceLoad();
    }

    @Override
    public void populateViews() {
        final Drawable usernameDrawable = DrawableUtil.modifyDrawableColorWithBounds(
                getApplicationContext(), R.drawable.ic_account_box_black
        );

        final Drawable emailDrawable = DrawableUtil.modifyDrawableColorWithBounds(
                getApplicationContext(),
                R.drawable.ic_email_black
        );

        final Drawable workDrawable = DrawableUtil.modifyDrawableColorWithBounds(
                getApplicationContext(),
                R.drawable.ic_work_black
        );

        final Drawable enterpriseDrawable = DrawableUtil.modifyDrawableColorWithBounds(
                getApplicationContext(),
                R.drawable.ic_domain_black
        );

        final Drawable locationDrawable = DrawableUtil.modifyDrawableColorWithBounds(
                getApplicationContext(),
                R.drawable.ic_location_city_black
        );

        mUsername.setCompoundDrawables(usernameDrawable, null, null, null);
        mEmail.setCompoundDrawables(emailDrawable, null, null, null);
        mWork.setCompoundDrawables(workDrawable, null, null, null);
        mEnterprise.setCompoundDrawables(enterpriseDrawable, null, null, null);
        mLocation.setCompoundDrawables(locationDrawable, null, null, null);
    }

    @Override
    public void setListeners() { }

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
                fragment.setActivityReference(new WeakReference<Activity>(this));
                fragment.show(getSupportFragmentManager(), ChangePasswordFragment.TAG);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        MenuNavigator.newInstance().navigate(this);
    }
}
