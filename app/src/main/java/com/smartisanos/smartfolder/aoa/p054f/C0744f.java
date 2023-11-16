package com.smartisanos.smartfolder.aoa.p054f;

import android.database.ContentObserver;
import android.os.Handler;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SyncManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.f.f */
/* loaded from: classes.dex */
public final class C0744f extends ContentObserver {

    /* renamed from: a */
    final /* synthetic */ SyncManager f3574a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0744f(SyncManager syncManager, Handler handler) {
        super(handler);
        this.f3574a = syncManager;
    }

    @Override // android.database.ContentObserver
    public final void onChange(boolean z) {
        super.onChange(z);
        HandShaker.debug("SyncManager", "mObserver.onchange");
        this.f3574a.m636f();
    }
}
