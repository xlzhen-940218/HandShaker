package com.smartisanos.smartfolder.aoa.p052d;

import com.smartisanos.smartfolder.aoa.p054f.SyncManager;
import com.smartisanos.smartfolder.aoa.p055g.SspExecutorManager;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FileProcessor.java */
/* renamed from: com.smartisanos.smartfolder.aoa.d.d */
/* loaded from: classes.dex */
public final class C0730d implements SspExecutorManager.InterfaceC0754b {

    /* renamed from: a */
    final /* synthetic */ SmartSyncProtocolProtos.SSPFile f3505a;

    /* renamed from: b */
    final /* synthetic */ FileProcessor f3506b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0730d(FileProcessor fileProcessor, SmartSyncProtocolProtos.SSPFile SSPFile) {
        this.f3506b = fileProcessor;
        this.f3505a = SSPFile;
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.SspExecutorManager.InterfaceC0754b
    /* renamed from: a */
    public final void mo576a(boolean z) {
        HandShaker.debug("SyncManager", "upload end, isSuccess: " + z);
        if (z) {
            SyncManager.getInstance().m650b(this.f3505a);
        }
    }
}
