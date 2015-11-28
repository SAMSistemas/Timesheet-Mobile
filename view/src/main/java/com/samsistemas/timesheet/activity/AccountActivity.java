package com.samsistemas.timesheet.activity;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.constant.SessionConst;
import com.samsistemas.timesheet.loader.PersonLoader;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.navigation.MenuNavigator;
import com.samsistemas.timesheet.util.DevUtil;
import com.samsistemas.timesheet.util.DrawableUtil;
import com.samsistemas.timesheet.util.ToolbarUtil;
import com.samsistemas.timesheet.fragment.ChangePasswordFragment;

import java.lang.ref.WeakReference;

/**
 *
 * @author jonatan.salas
 */
public class AccountActivity extends AppCompatActivity implements SessionConst {
    private static final int PERSON_LOADER_ID = 0;

    private CollapsingToolbarLayout mToolbarLayout;
    private TextView mUsername;
    private TextView mEmail;
    private TextView mWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Use this to check troubles
        DevUtil.enableStrictModeChecker();
        setContentView(R.layout.activity_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if (null != actionBar)
            ToolbarUtil.styleWithBackButton(actionBar, "");

        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mUsername = (TextView) findViewById(R.id.username_textview);
        mEmail = (TextView) findViewById(R.id.email_textview);
        mWork = (TextView) findViewById(R.id.work_textview);
        TextView enterprise = (TextView) findViewById(R.id.enterprise_textview);
        TextView location = (TextView) findViewById(R.id.location_textview);

        initPersonLoader();

        mUsername.setCompoundDrawables(getCustomDrawable(R.drawable.ic_account_box_black), null, null, null);
        mEmail.setCompoundDrawables(getCustomDrawable(R.drawable.ic_email_black), null, null, null);
        mWork.setCompoundDrawables(getCustomDrawable(R.drawable.ic_work_black), null, null, null);
        enterprise.setCompoundDrawables(getCustomDrawable(R.drawable.ic_domain_black), null, null, null);
        location.setCompoundDrawables(getCustomDrawable(R.drawable.ic_location_city_black), null, null, null);
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

    protected Drawable getCustomDrawable(@DrawableRes int id) {
        Drawable drawable = DrawableUtil.modifyDrawableColor(
                getApplicationContext(),
                id,
                R.color.primary,
                PorterDuff.Mode.SRC_ATOP
        );

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());

        return drawable;
    }

    protected void initPersonLoader() {
        getSupportLoaderManager().initLoader(PERSON_LOADER_ID, null, new LoaderManager.LoaderCallbacks<Person>() {

            @Override
            public Loader<Person> onCreateLoader(int id, Bundle args) {
                return new PersonLoader(getApplicationContext());
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
