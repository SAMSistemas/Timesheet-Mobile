package com.samsistemas.timesheet.settings.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.samsistemas.timesheet.R;
import com.samsistemas.timesheet.common.activity.MenuActivity;
import com.samsistemas.timesheet.common.utility.ToolbarUtil;
import com.samsistemas.timesheet.settings.fragment.SettingsFragment;

import net.xpece.android.support.preference.Fixes;

/**
 * @author jonatan.salas
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fixes.updateLayoutInflaterFactory(getLayoutInflater());
        setContentView(android.R.layout.activity_list_item);
        final ActionBar actionBar = getSupportActionBar();

        if (null != actionBar) {
            ToolbarUtil.styleWithBackButton(actionBar, R.string.action_settings);
        }

        getSupportFragmentManager()
                .beginTransaction()
                .add(android.R.id.content, new SettingsFragment())
                .commit();
    }

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
