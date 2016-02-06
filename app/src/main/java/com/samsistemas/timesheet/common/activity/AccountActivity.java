package com.samsistemas.timesheet.common.activity;

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
import com.samsistemas.timesheet.common.activity.base.BaseAppCompatActivity;
import com.samsistemas.timesheet.common.fragment.ChangePasswordFragment;
import com.samsistemas.timesheet.data.loader.PersonLoader;
import com.samsistemas.timesheet.data.domain.Person;
import com.samsistemas.timesheet.common.navigation.MenuNavigator;
import com.samsistemas.timesheet.common.utility.DrawableUtil;
import com.samsistemas.timesheet.common.utility.ToolbarUtil;

import static com.samsistemas.timesheet.common.utility.LoaderId.PERSON_LOADER_ID;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Account activity that acts as view controller
 *
 * @author jonatan.salas
 */
public class AccountActivity extends BaseAppCompatActivity {

    @Bind(R.id.tool_bar)
    Toolbar mToolbar;

    @Bind(R.id.toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;

    @Bind(R.id.username_textview)
    TextView mUsername;

    @Bind(R.id.email_textview)
    TextView mEmail;

    @Bind(R.id.work_textview)
    TextView mWork;

    @Bind(R.id.enterprise_textview)
    TextView mEnterprise;

    @Bind(R.id.location_textview)
    TextView mLocation;

    @Override
    @LayoutRes
    public int getLayoutResourceId() {
        return R.layout.activity_account;
    }

    @Override
    public void setUserInterface() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (null != actionBar)
            ToolbarUtil.styleWithBackButton(actionBar, "");
    }

    @Override
    public void initialize() {
        initPersonLoader();
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
                fragment.setContext(this);
                fragment.show(getSupportFragmentManager(), ChangePasswordFragment.TAG);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        MenuNavigator.newInstance().navigate(this);
    }

    private void initPersonLoader() {
        getSupportLoaderManager().initLoader(PERSON_LOADER_ID, null, new LoaderManager.LoaderCallbacks<Person>() {

            @Override
            public Loader<Person> onCreateLoader(int id, Bundle args) {
                return (id == PERSON_LOADER_ID) ? new PersonLoader(getApplicationContext()) : null;
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
}
