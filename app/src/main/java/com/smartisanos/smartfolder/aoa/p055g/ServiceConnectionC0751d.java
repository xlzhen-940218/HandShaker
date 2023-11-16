package com.smartisanos.smartfolder.aoa.p055g;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.smartisanos.smartfolder.aoa.service.MediaScannerService;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ConnectionManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.g.d */
/* loaded from: classes.dex */
public final class ServiceConnectionC0751d implements ServiceConnection {

    /* renamed from: a */
    final /* synthetic */ ConnectionManager f3607a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ServiceConnectionC0751d(ConnectionManager connectionManager) {
        this.f3607a = connectionManager;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        this.f3607a.f3601c = (MediaScannerService.BinderC0811a) iBinder;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.f3607a.f3601c = null;
    }
}
