package com.samsistemas.timesheet.common.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.samsistemas.timesheet.settings.activity.SettingsActivity;
import com.samsistemas.timesheet.common.animation.ScaleUpAnimator;
import com.samsistemas.timesheet.common.navigation.base.Navigator;

/**
 * Class used to navigate to the settings activity.
 *
 * @author jonatan.salas
 */
public class SettingsNavigator implements Navigator {
    private static SettingsNavigator sSettingsNavigator = null;

    @Override
    public void navigate(@NonNull Activity actualActivity) {
        actualActivity.startActivity(getCompleteIntent(actualActivity));
        actualActivity.finish();
    }

    @Override
    public void navigateWithAnimation(@NonNull Activity actualActivity, @NonNull View view) {
        Bundle options = ScaleUpAnimator.newInstance().saveAnimation(view);
        ActivityCompat.startActivity(actualActivity, getCompleteIntent(actualActivity), options);

        actualActivity.finish();
    }

    @Override
    public Intent getCompleteIntent(@NonNull Activity actualActivity) {
        Intent settingsIntent = new Intent(actualActivity.getApplicationContext(), SettingsActivity.class);
        settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        return settingsIntent;
    }

    /**
     * Static method that generates a singleton instance.
     *
     * @return a singleton SettingsNavigator object.
     */
    public static SettingsNavigator newInstance() {
        if(null == sSettingsNavigator) sSettingsNavigator = new SettingsNavigator();
        return sSettingsNavigator;
    }
}
