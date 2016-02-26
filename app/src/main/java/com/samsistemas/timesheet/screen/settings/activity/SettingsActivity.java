package com.samsistemas.timesheet.screen.settings.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

import com.jonisaa.commons.activity.BasePreferenceActivity;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.screen.menu.activity.MainActivity;
import com.samsistemas.timesheet.screen.settings.fragment.SettingsFragment;

/**
 * @author jonatan.salas
 */
public class SettingsActivity extends BasePreferenceActivity<SettingsFragment> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();

        if (null != actionBar) {
            actionBar.setTitle(R.string.action_settings);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayUseLogoEnabled(false);

            int color = ContextCompat.getColor(getApplicationContext(), R.color.primary);

            actionBar.setBackgroundDrawable(new ColorDrawable(color));
        }
    }

    @Override
    public int getLayout() {
        return android.R.layout.activity_list_item;
    }

    @Override
    public int getContent() {
        return android.R.id.content;
    }

    @Nullable
    @Override
    public SettingsFragment createFragment() {
        return new SettingsFragment();
    }

    @Override
    public void restoreFragmentState(@Nullable Bundle bundle) { }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }

        return true;
    }

    @Override
    public void onBackPressed() {
        final Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                IntentCompat.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        startActivity(intent);
        finish();
    }
}
