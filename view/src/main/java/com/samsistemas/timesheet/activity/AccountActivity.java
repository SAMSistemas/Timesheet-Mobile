package com.samsistemas.timesheet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.facade.PersonFacade;
import com.samsistemas.timesheet.model.Person;
import com.samsistemas.timesheet.navigation.MenuNavigator;
import com.samsistemas.timesheet.util.DrawableUtil;
import com.samsistemas.timesheet.util.ToolbarUtil;
import com.samsistemas.timesheet.fragment.ChangePasswordFragment;

import java.lang.ref.WeakReference;

/**
 *
 * @author jonatan.salas
 */
public class AccountActivity extends AppCompatActivity {
    private CollapsingToolbarLayout mToolbarLayout;
    private ActionBar mActionBar;
    private TextView mUsername;
    private TextView mEmail;
    private TextView mWork;
    private TextView mEnterprise;
    private TextView mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActionBar = getSupportActionBar();

        mToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        mUsername = (TextView) findViewById(R.id.username_textview);
        mEmail = (TextView) findViewById(R.id.email_textview);
        mWork = (TextView) findViewById(R.id.work_textview);
        mEnterprise = (TextView) findViewById(R.id.enterprise_textview);
        mLocation = (TextView) findViewById(R.id.location_textview);

        fetchData();

        mUsername.setCompoundDrawables(getCustomDrawable(R.drawable.ic_account_box_black), null, null, null);
        mEmail.setCompoundDrawables(getCustomDrawable(R.drawable.ic_email_black), null, null, null);
        mWork.setCompoundDrawables(getCustomDrawable(R.drawable.ic_work_black), null, null, null);
        mEnterprise.setCompoundDrawables(getCustomDrawable(R.drawable.ic_domain_black), null, null, null);
        mLocation.setCompoundDrawables(getCustomDrawable(R.drawable.ic_location_city_black), null, null, null);
    }


    @Override
    protected void onResume() {
        fetchData();
        super.onResume();
    }

    protected void fetchData() {
        final SharedPreferences prefs = getSharedPreferences(getString(R.string.preference_filename), Context.MODE_PRIVATE);
        new FetchPersonTask(getApplicationContext()).execute(prefs.getLong(getString(R.string.user_id), 1));
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

    public class FetchPersonTask extends AsyncTask<Long, Void, Person> {
        protected Context mContext;

        public FetchPersonTask(Context context) {
            this.mContext = context;
        }

        @Override
        protected Person doInBackground(Long... params) {
            final PersonFacade personFacade = PersonFacade.newInstance();
            return personFacade.findById(mContext, params[0]);
        }

        @Override
        protected void onPostExecute(Person person) {
            if(null != person) {
                String fullName = person.getName() + " " + person.getLastName();

                if (null != mActionBar)
                    ToolbarUtil.styleWithBackButton(mActionBar, "");
                    mToolbarLayout.setTitle(fullName);

                mUsername.setText(person.getUsername());
                final String emailString = fullName + "@samsistemas.com.ar";
                mEmail.setText(emailString);
                mWork.setText(person.getWorkPosition().getDescription());
            }
        }
    }
}
