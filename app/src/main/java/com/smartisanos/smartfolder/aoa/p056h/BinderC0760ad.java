package com.smartisanos.smartfolder.aoa.p056h;

import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageStats;
import android.os.RemoteException;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: StorageUtils.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.ad */
/* loaded from: classes.dex */
final class BinderC0760ad extends IPackageStatsObserver.Stub {

    /* renamed from: a */
    final /* synthetic */ AtomicLong f3654a;

    /* renamed from: b */
    final /* synthetic */ AtomicLong f3655b;

    /* renamed from: c */
    final /* synthetic */ AtomicInteger f3656c;

    /* renamed from: d */
    final /* synthetic */ int f3657d;

    /* renamed from: e */
    final /* synthetic */ StorageUtils.InterfaceC0759a f3658e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BinderC0760ad(AtomicLong atomicLong, AtomicLong atomicLong2, AtomicInteger atomicInteger, int i, StorageUtils.InterfaceC0759a interfaceC0759a) {
        this.f3654a = atomicLong;
        this.f3655b = atomicLong2;
        this.f3656c = atomicInteger;
        this.f3657d = i;
        this.f3658e = interfaceC0759a;
    }



    @Override
    public void onGetStatsCompleted(PackageStats pStats, boolean succeeded) throws RemoteException {
        this.f3654a.addAndGet(pStats.dataSize);
        this.f3654a.addAndGet(pStats.codeSize);
        this.f3655b.addAndGet(pStats.cacheSize);
        if (this.f3656c.addAndGet(1) == this.f3657d) {
            this.f3658e.mo423a(this.f3654a.longValue(), this.f3655b.longValue());
        }
    }
}
