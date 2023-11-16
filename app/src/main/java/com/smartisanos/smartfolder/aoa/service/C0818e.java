package com.smartisanos.smartfolder.aoa.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.smartisanos.smartfolder.aoa.p056h.ActivityLifecycleManager;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

/* compiled from: ConnectionManagerService.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.e */
/* loaded from: classes.dex */
final class C0818e extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ ConnectionManagerService f3896a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0818e(ConnectionManagerService connectionManagerService) {
        this.f3896a = connectionManagerService;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String str;
        boolean m289f;
        WifiConnectionManager wifiConnectionManager;
        boolean m291e;
        UsbConnectionManager usbConnectionManager;
        WifiConnectionManager wifiConnectionManager2;
        WifiConnectionManager wifiConnectionManager3;
        str = ConnectionManagerService.TAG;
        HandShaker.debug(str, "mWifiReceiver.onReceive: " + intent);
        m289f = this.f3896a.m289f();
        if (m289f) {
            m291e = this.f3896a.m291e();
            if (!m291e) {
                usbConnectionManager = this.f3896a.usbConnectionManager;
                if (!usbConnectionManager.m248c()) {
                    ActivityLifecycleManager.getInstance();
                    if (ActivityLifecycleManager.m553c()) {
                        wifiConnectionManager2 = this.f3896a.wifiConnectionManager;
                        if (!wifiConnectionManager2.m234e()) {
                            wifiConnectionManager3 = this.f3896a.wifiConnectionManager;
                            wifiConnectionManager3.m238c();
                            return;
                        }
                        return;
                    }
                }
            }
        }
        wifiConnectionManager = this.f3896a.wifiConnectionManager;
        wifiConnectionManager.m236d();
    }
}
