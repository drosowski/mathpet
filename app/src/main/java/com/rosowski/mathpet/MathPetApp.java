package com.rosowski.mathpet;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

/**
 * Custom Application to force portrait mode.
 * Created by danielrosowski on 16.09.16.
 */
public class MathPetApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity,
                                          Bundle savedInstanceState) {
                activity.setRequestedOrientation(
                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            }

            @Override
            public void onActivityStarted(Activity activity) {
                // do nothing
            }

            @Override
            public void onActivityResumed(Activity activity) {
                // do nothing
            }

            @Override
            public void onActivityPaused(Activity activity) {
                // do nothing
            }

            @Override
            public void onActivityStopped(Activity activity) {
                // do nothing
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                // do nothing
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                // do nothing
            }
        });

    }
}
