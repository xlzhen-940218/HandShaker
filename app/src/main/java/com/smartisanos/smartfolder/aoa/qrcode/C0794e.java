package com.smartisanos.smartfolder.aoa.qrcode;

import android.os.Handler;

import com.journeyapps.barcodescanner.RotationCallback;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCameraPreview.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.e */
/* loaded from: classes.dex */
public class C0794e implements RotationCallback {

    /* renamed from: a */
    final /* synthetic */ HandShakerCameraPreview f3831a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0794e(HandShakerCameraPreview handShakerCameraPreview) {
        this.f3831a = handShakerCameraPreview;
    }

    @Override // com.journeyapps.barcodescanner.RotationCallback
    /* renamed from: a */
    public void onRotationChanged(int rotate) {
        Handler handler;
        handler = this.f3831a.handler;
        handler.postDelayed(new RunnableC0795f(this), 250L);
    }
}
