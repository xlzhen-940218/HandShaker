package com.smartisan.trackerlib.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.smartisan.trackerlib.Agent;
import com.smartisan.trackerlib.p045b.UploadTask;
import com.smartisan.trackerlib.p046c.CommonUtil;
import com.smartisan.trackerlib.p046c.LogUtils;

/* loaded from: classes.dex */
public class TrackerReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            LogUtils.m1902a("receive the action   " + action);
            if (CommonUtil.m1919a(Agent.m1952a().m1947b()) != 0) {
                new UploadTask().m1932a();
            }
        }
    }
}
