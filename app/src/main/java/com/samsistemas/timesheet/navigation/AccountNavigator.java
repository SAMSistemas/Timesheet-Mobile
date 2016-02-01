package com.samsistemas.timesheet.navigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.IntentCompat;
import android.view.View;

import com.samsistemas.timesheet.activity.AccountActivity;
import com.samsistemas.timesheet.animation.ScaleUpAnimator;
import com.samsistemas.timesheet.navigation.base.Navigator;

/**
 * Class used to navigate to AccountActivity.
 *
 * @author jonatan.salas
 */
public class AccountNavigator implements Navigator {
    private static AccountNavigator sAccountNavigator = null;

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
        Intent accountIntent = new Intent(actualActivity.getApplicationContext(), AccountActivity.class);
        accountIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | IntentCompat.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        return accountIntent;
    }

    /**
     * Static method that generates a singleton instance.
     *
     * @return a singleton AccountNavigator object.
     */
    public static AccountNavigator newInstance() {
        if(null == sAccountNavigator) sAccountNavigator = new AccountNavigator();
        return sAccountNavigator;
    }
}
