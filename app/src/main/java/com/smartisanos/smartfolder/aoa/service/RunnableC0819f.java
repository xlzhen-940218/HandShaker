package com.smartisanos.smartfolder.aoa.service;

import android.app.Service;
import android.os.PowerManager;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ConnectionManagerService.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.f */
/* loaded from: classes.dex */
public final class RunnableC0819f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ AbstractC0808a f3897a;
    ConnectionManagerService connectionManagerService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0819f(ConnectionManagerService connectionManagerService,AbstractC0808a abstractC0808a) {
        this.f3897a = abstractC0808a;
        this.connectionManagerService = connectionManagerService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        PowerManager.WakeLock wakeLock;
        str = ConnectionManagerService.TAG;
        HandShaker.debug(str, "mConnectedRunnable");
        CommonUtils.m513a((Service) connectionManagerService, true);
        wakeLock = connectionManagerService.wakeLock;
        wakeLock.acquire();
        this.f3897a.mo266b();
    }
}
