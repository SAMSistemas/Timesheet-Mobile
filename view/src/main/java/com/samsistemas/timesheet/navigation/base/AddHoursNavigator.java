package com.samsistemas.timesheet.navigation.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.view.View;

import com.samsistemas.timesheet.activity.AddHoursActivity;
import com.samsistemas.timesheet.animation.ScaleUpAnimator;

/**
 * Class used to navigate to AccountActivity.
 *
 * @author jonatan.salas
 */
public class AddHoursNavigator implements Navigator {
    private static AddHoursNavigator sAddHoursNavigator = null;

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
        Intent addHoursIntent = new Intent(actualActivity.getApplicationContext(), AddHoursActivity.class);
        addHoursIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        return addHoursIntent;
    }

    /**
     * Static method that generates a singleton instance.
     *
     * @return a singleton AccountNavigator object.
     */
    public static AddHoursNavigator newInstance() {
        if(null == sAddHoursNavigator) sAddHoursNavigator = new AddHoursNavigator();
        return sAddHoursNavigator;
    }
}
