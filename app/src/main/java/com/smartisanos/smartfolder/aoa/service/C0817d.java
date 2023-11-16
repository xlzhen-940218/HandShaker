package com.smartisanos.smartfolder.aoa.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

/* compiled from: ConnectionManagerService.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.d */
/* loaded from: classes.dex */
final class C0817d extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ ConnectionManagerService f3895a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0817d(ConnectionManagerService connectionManagerService) {
        this.f3895a = connectionManagerService;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String str;
        UsbConnectionManager usbConnectionManager;
        BroadcastReceiver broadcastReceiver;
        UsbConnectionManager usbConnectionManager2;
        str = ConnectionManagerService.TAG;
        HandShaker.debug(str, "Accessory(" + ((UsbAccessory) intent.getParcelableExtra("accessory")) + ") detached.");
        usbConnectionManager = this.f3895a.usbConnectionManager;
        if (usbConnectionManager.m597g()) {
            usbConnectionManager2 = this.f3895a.usbConnectionManager;
            usbConnectionManager2.m601a(true);
        }
        ConnectionManagerService connectionManagerService = this.f3895a;
        broadcastReceiver = this.f3895a.f3870k;
        connectionManagerService.unregisterReceiver(broadcastReceiver);
    }
}
