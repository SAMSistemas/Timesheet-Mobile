package com.samsistemas.timesheet.activity;

import android.app.Activity;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.samsistemas.timesheet.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar actionBar = getSupportActionBar();
        if(null != actionBar)
            ToolbarUtil.styleWithBackButton(actionBar, "Jonatan E. Salas");

        findTextView(R.id.username_textview, R.drawable.ic_account_box_black);
        findTextView(R.id.email_textview, R.drawable.ic_email_black);
        findTextView(R.id.work_textview, R.drawable.ic_work_black);
        findTextView(R.id.enterprise_textview, R.drawable.ic_domain_black);
        findTextView(R.id.location_textview, R.drawable.ic_location_city_black);
    }

    /**
     *
     * @param id
     * @param drawable
     */
    private void findTextView(@IdRes int id, @DrawableRes int drawable) {
        Drawable customDrawable = DrawableUtil.modifyDrawableColor(
                getApplicationContext(),
                drawable,
                R.color.primary,
                PorterDuff.Mode.SRC_ATOP
        );

        TextView textView = (TextView) findViewById(id);
        customDrawable.setBounds(0, 0, customDrawable.getIntrinsicWidth(), customDrawable.getIntrinsicHeight());
        textView.setCompoundDrawables(customDrawable, null, null, null);
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
}
