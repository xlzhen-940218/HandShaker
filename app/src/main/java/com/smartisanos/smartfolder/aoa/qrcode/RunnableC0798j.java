package com.smartisanos.smartfolder.aoa.qrcode;

import com.journeyapps.barcodescanner.BarcodeResult;

/* compiled from: HandShakerCaptureManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.j */
/* loaded from: classes.dex */
final class RunnableC0798j implements Runnable {

    /* renamed from: a */
    final /* synthetic */ BarcodeResult f3849a;

    /* renamed from: b */
    final /* synthetic */ SBarcodeCallback f3850b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0798j(SBarcodeCallback c0797i, BarcodeResult barcodeResult) {
        this.f3850b = c0797i;
        this.f3849a = barcodeResult;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f3850b.handShakerCaptureManager.m327a(this.f3849a);
    }
}
