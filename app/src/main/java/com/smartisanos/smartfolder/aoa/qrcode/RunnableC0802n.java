package com.smartisanos.smartfolder.aoa.qrcode;

import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCaptureManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.n */
/* loaded from: classes.dex */
public final class RunnableC0802n implements Runnable {

    /* renamed from: a */
    final /* synthetic */ HandShakerCaptureManager f3854a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0802n(HandShakerCaptureManager handShakerCaptureManager) {
        this.f3854a = handShakerCaptureManager;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        str = HandShakerCaptureManager.TAG;
        Log.d(str, "Finishing due to inactivity");
        this.f3854a.activity.finish();
    }
}
