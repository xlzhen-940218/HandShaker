package com.smartisanos.smartfolder.aoa.qrcode;

import android.util.Log;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCaptureManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.m */
/* loaded from: classes.dex */
public final class C0801m implements HandShakerCameraPreview.InterfaceC0787a {

    /* renamed from: a */
    final /* synthetic */ HandShakerCaptureManager handShakerCaptureManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0801m(HandShakerCaptureManager handShakerCaptureManager) {
        this.handShakerCaptureManager = handShakerCaptureManager;
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: a */
    public final void mo308a() {
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: b */
    public final void start() {
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: c */
    public final void stop() {
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: a */
    public final void error(Exception exc) {
        this.handShakerCaptureManager.error();
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: d */
    public final void close() {
        boolean z;
        String str;
        z = this.handShakerCaptureManager.f3844k;
        if (z) {
            str = HandShakerCaptureManager.TAG;
            Log.d(str, "Camera closed; finishing activity");
            this.handShakerCaptureManager.activity.finish();
        }
    }
}
