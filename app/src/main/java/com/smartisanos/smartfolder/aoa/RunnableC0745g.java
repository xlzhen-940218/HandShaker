package com.smartisanos.smartfolder.aoa;

import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

/* compiled from: MainActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.g */
/* loaded from: classes.dex */
final class RunnableC0745g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ boolean f3575a;

    /* renamed from: b */
    final /* synthetic */ C0741f f3576b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0745g(C0741f c0741f, boolean z) {
        this.f3576b = c0741f;
        this.f3575a = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HandShaker.debug("QRCode, activity, handleSendResult: " + this.f3575a);
        if (this.f3575a) {
            MainActivity.m805i(this.f3576b.f3556a);
        } else {
            MainActivity.m803j(this.f3576b.f3556a);
        }
    }
}
