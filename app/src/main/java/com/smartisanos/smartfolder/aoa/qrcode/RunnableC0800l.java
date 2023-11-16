package com.smartisanos.smartfolder.aoa.qrcode;

import com.journeyapps.barcodescanner.BarcodeCallback;

/* compiled from: HandShakerCaptureManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.l */
/* loaded from: classes.dex */
final class RunnableC0800l implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SBarcodeCallback sBarcodeCallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0800l(SBarcodeCallback c0797i) {
        this.sBarcodeCallback = c0797i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        MainScanView mainScanView;
        BarcodeCallback barcodeCallback;
        mainScanView = this.sBarcodeCallback.handShakerCaptureManager.mainScanView;
        barcodeCallback = this.sBarcodeCallback.handShakerCaptureManager.sBarcodeCallback;
        mainScanView.m337a(barcodeCallback);
    }
}
