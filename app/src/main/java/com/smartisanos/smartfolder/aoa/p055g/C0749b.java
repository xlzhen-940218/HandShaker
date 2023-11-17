package com.smartisanos.smartfolder.aoa.p055g;

import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Connection.java */
/* renamed from: com.smartisanos.smartfolder.aoa.g.b */
/* loaded from: classes.dex */
public final class C0749b extends Thread {

    /* renamed from: a */
    final /* synthetic */ Connection connection;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0749b(Connection connection) {
        this.connection = connection;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0067, code lost:
        com.smartisanos.smartfolder.aoa.p056h.C0781t.m412a(com.smartisanos.smartfolder.aoa.p055g.Connection.f3577a, " Reader cannot read packet anymore, disconnecting...");
     */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        Connection.SspReader abstractC0747b;
        SspExecutorManager sspExecutorManager;
        Connection.SspReader abstractC0747b2;
        SspExecutorManager sspExecutorManager2;
        Thread.currentThread().setName(getClass().getSimpleName());
        while (true) {
            if (Thread.interrupted()) {
                HandShaker.debug(Connection.TAG, "Thread interrupted, disconnecting...");
                break;
            }
            try {
                abstractC0747b2 = this.connection.sspReader;
                SspPacket mo256b = abstractC0747b2.mo256b();
                if (mo256b == null) {
                    break;
                }
                sspExecutorManager2 = this.connection.sspExecutorManager;
                sspExecutorManager2.m584a(mo256b);
            } catch (Exception e4) {
                e4.printStackTrace();
                HandShaker.debug(Connection.TAG, "Disconnect due to " + e4);
            }
        }
        HandShaker.debug(Connection.TAG, "mReaderThread stop run");
        abstractC0747b = this.connection.sspReader;
        abstractC0747b.mo268a();
        this.connection.m620d();
    }
}
