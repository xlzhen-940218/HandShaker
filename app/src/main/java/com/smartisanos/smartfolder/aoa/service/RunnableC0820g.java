package com.smartisanos.smartfolder.aoa.service;

import android.app.Service;
import android.os.PowerManager;
import com.smartisan.trackerlib.Agent;
import com.smartisanos.smartfolder.aoa.p054f.SyncManager;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ConnectionManagerService.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.g */
/* loaded from: classes.dex */
public final class RunnableC0820g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ AbstractC0808a f3898a;
    ConnectionManagerService connectionManagerService;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0820g(ConnectionManagerService connectionManagerService,AbstractC0808a abstractC0808a) {
        this.f3898a = abstractC0808a;
        this.connectionManagerService = connectionManagerService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        PowerManager.WakeLock wakeLock;
        str = ConnectionManagerService.TAG;
        HandShaker.debug(str, "mDisconnectedRunnable");
        CommonUtils.m513a((Service) connectionManagerService, false);
        wakeLock = connectionManagerService.wakeLock;
        wakeLock.release();
        SyncManager.getInstance().m652a(false);
        this.f3898a.mo265c();
        Agent.m1952a().m1942e();
    }
}
