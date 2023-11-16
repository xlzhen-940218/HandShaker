package com.smartisanos.smartfolder.aoa;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.service.ConnectionManagerService;

/* compiled from: MainActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.a */
/* loaded from: classes.dex */
final class ServiceConnectionC0704a implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ MainActivity f3406a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionC0704a(MainActivity mainActivity) {
        this.f3406a = mainActivity;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ConnectionManagerService connectionManagerService;
        ConnectionManagerService.InterfaceC0810c interfaceC0810c;
        ConnectionManagerService connectionManagerService2;
        ConnectionManagerService connectionManagerService3;
        ConnectionManagerService connectionManagerService4;
        AlertDialog alertDialog;
        AlertDialog alertDialog2;
        ConnectionManagerService connectionManagerService5;
        MainActivity.m816b(this.f3406a);
        this.f3406a.f3391h = ((ConnectionManagerService.BinderC0809b) iBinder).m282a();
        connectionManagerService = this.f3406a.f3391h;
        interfaceC0810c = this.f3406a.f3402s;
        connectionManagerService.m300a(interfaceC0810c);
        StringBuilder sb = new StringBuilder("onServiceConnected, usb connect: ");
        connectionManagerService2 = this.f3406a.f3391h;
        StringBuilder append = sb.append(connectionManagerService2.m297b()).append(", Wifi connect: ");
        connectionManagerService3 = this.f3406a.f3391h;
        HandShaker.debug("MainActivity", append.append(connectionManagerService3.m295c()).toString());
        connectionManagerService4 = this.f3406a.f3391h;
        if (!connectionManagerService4.m297b()) {
            connectionManagerService5 = this.f3406a.f3391h;
            if (!connectionManagerService5.m295c()) {
                return;
            }
        }
        alertDialog = this.f3406a.f3395l;
        if (alertDialog != null) {
            alertDialog2 = this.f3406a.f3395l;
            alertDialog2.dismiss();
        }
        this.f3406a.m802k();
        this.f3406a.m815c();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        HandShaker.debug("MainActivity", "onServiceDisconnected, ComponentName = " + componentName);
    }
}
