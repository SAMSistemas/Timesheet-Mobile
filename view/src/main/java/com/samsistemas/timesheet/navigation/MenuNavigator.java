package com.samsistemas.timesheet.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.view.View;

import com.samsistemas.timesheet.activity.MenuActivity;
import com.samsistemas.timesheet.animation.ScaleUpAnimator;
import com.samsistemas.timesheet.navigation.base.Navigator;

/**
 * Class used to navigate to MenuActivity.
 *
 * @author jonatan.salas
 */
public class MenuNavigator implements Navigator {
    private static MenuNavigator sMenuNavigator = null;

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
        Intent menuIntent = new Intent(actualActivity.getApplicationContext(), MenuActivity.class);
        menuIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        return menuIntent;
    }

    /**
     * Static method that generates a singleton instance.
     *
     * @return a singleton MenuNavigator object.
     */
    public static MenuNavigator newInstance() {
        if(null == sMenuNavigator) sMenuNavigator = new MenuNavigator();
        return sMenuNavigator;
    }
}
