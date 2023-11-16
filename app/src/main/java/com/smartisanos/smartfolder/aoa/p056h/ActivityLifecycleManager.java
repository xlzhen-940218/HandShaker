package com.smartisanos.smartfolder.aoa.p056h;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.smartisanos.smartfolder.aoa.h.a */
/* loaded from: classes.dex */
public class ActivityLifecycleManager implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a */
    private static final String TAG = ActivityLifecycleManager.class.getSimpleName();

    /* renamed from: b */
    private static ActivityLifecycleManager instance = new ActivityLifecycleManager();

    /* renamed from: c */
    private static List<Activity> activities = new ArrayList();

    /* renamed from: d */
    private int activityCount;

    private ActivityLifecycleManager() {
    }

    /* renamed from: a */
    public static ActivityLifecycleManager getInstance() {
        return instance;
    }

    /* renamed from: b */
    public final boolean activitiesIsEmpty() {
        return this.activityCount == 0;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        activities.add(activity);
        HandShaker.debug(TAG, activity + ": onCreate");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        HandShaker.debug(TAG, activity + ": onStart");
        this.activityCount++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        HandShaker.debug(TAG, activity + ": onResume");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        HandShaker.debug(TAG, activity + ": onPause");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        HandShaker.debug(TAG, activity + ": onStop");
        this.activityCount--;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        HandShaker.debug(TAG, activity + ": onSaveInstance");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        activities.remove(activity);
        HandShaker.debug(TAG, activity + ": onDestroy");
    }

    /* renamed from: a */
    public static void m555a(Activity activity) {
        for (Activity activity2 : activities) {
            if (activity2 != activity) {
                activity2.recreate();
            }
        }
    }

    /* renamed from: c */
    public static boolean m553c() {
        return !activities.isEmpty();
    }
}
