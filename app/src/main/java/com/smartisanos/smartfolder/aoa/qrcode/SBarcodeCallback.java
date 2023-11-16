package com.smartisanos.smartfolder.aoa.qrcode;

import android.os.Handler;

import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.BeepManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCaptureManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.i */
/* loaded from: classes.dex */
public final class SBarcodeCallback implements BarcodeCallback {

    /* renamed from: a */
    final /* synthetic */ HandShakerCaptureManager handShakerCaptureManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SBarcodeCallback(HandShakerCaptureManager handShakerCaptureManager) {
        this.handShakerCaptureManager = handShakerCaptureManager;
    }

    @Override // com.journeyapps.barcodescanner.BarcodeCallback
    /* renamed from: a */
    public final void barcodeResult(BarcodeResult barcodeResult) {
        BeepManager beepManager;
        Handler handler;
        Handler handler2;
        MainScanView mainScanView;
        Handler handler3;
        beepManager = this.handShakerCaptureManager.beepManager;
        beepManager.playBeepSoundAndVibrate();
        if (HandShakerCaptureManager.m324b(barcodeResult)) {
            mainScanView = this.handShakerCaptureManager.mainScanView;
            mainScanView.m340a();
            handler3 = this.handShakerCaptureManager.handler;
            handler3.post(new RunnableC0798j(this, barcodeResult));
            return;
        }
        handler = this.handShakerCaptureManager.handler;
        handler.post(new RunnableC0799k(this));
        handler2 = this.handShakerCaptureManager.handler;
        handler2.postDelayed(new RunnableC0800l(this), 1000L);
    }

    @Override // com.journeyapps.barcodescanner.BarcodeCallback
    /* renamed from: a */
    public final void possibleResultPoints(List<ResultPoint> list) {
    }
}
