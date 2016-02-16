package com.samsistemas.timesheet.utility;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.IntentCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


/**
 * @author jonatan.salas
 */
public class ActivityUtility {

    public static void startActivityWithAnimation(AppCompatActivity activity, Class launchActivity, View view) {
        final Intent intent = new Intent(activity.getApplicationContext(), launchActivity);

        intent.setFlags(
                Intent.FLAG_ACTIVITY_NEW_TASK |
                IntentCompat.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        );

        int startX = ((Float) ViewCompat.getX(view)).intValue();
        int startY = ((Float) ViewCompat.getY(view)).intValue();

        Bundle anim = ActivityOptionsCompat
                .makeScaleUpAnimation(view, startX, startY, view.getWidth(), view.getHeight()).toBundle();
        ActivityCompat.startActivity(activity, intent, anim);
        activity.finish();
    }
}
