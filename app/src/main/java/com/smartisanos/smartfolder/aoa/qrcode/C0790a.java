package com.smartisanos.smartfolder.aoa.qrcode;

import android.os.Handler;
import android.os.Message;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;

import com.smartisanos.smartfolder.aoa.R;

import java.util.List;

/* compiled from: HandShakerBarcodeView.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.a */
/* loaded from: classes.dex */
final class C0790a implements Handler.Callback {

    /* renamed from: a */
    final /* synthetic */ HandShakerBarcodeView handShakerBarcodeView;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0790a(HandShakerBarcodeView handShakerBarcodeView) {
        this.handShakerBarcodeView = handShakerBarcodeView;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        BarcodeCallback barcodeCallback;
        int i;
        BarcodeCallback barcodeCallback2;
        BarcodeCallback barcodeCallback3;
        int i2;
        BarcodeCallback barcodeCallback4;
        int i3;
        if (message.what == R.id.zxing_decode_succeeded) {
            BarcodeResult barcodeResult = (BarcodeResult) message.obj;
            if (barcodeResult != null) {
                barcodeCallback3 = this.handShakerBarcodeView.barcodeCallback;
                if (barcodeCallback3 != null) {
                    i2 = this.handShakerBarcodeView.f3779a;
                    if (i2 != HandShakerBarcodeView.EnumC0786a.f3785a) {
                        barcodeCallback4 = this.handShakerBarcodeView.barcodeCallback;
                        barcodeCallback4.barcodeResult(barcodeResult);
                        i3 = this.handShakerBarcodeView.f3779a;
                        if (i3 == HandShakerBarcodeView.EnumC0786a.f3786b) {
                            this.handShakerBarcodeView.m375a();
                        }
                    }
                }
            }
            return true;
        } else if (message.what == R.id.zxing_decode_failed) {
            return true;
        } else {
            if (message.what == R.id.zxing_possible_result_points) {
                List<ResultPoint> list = (List) message.obj;
                barcodeCallback = this.handShakerBarcodeView.barcodeCallback;
                if (barcodeCallback != null) {
                    i = this.handShakerBarcodeView.f3779a;
                    if (i != HandShakerBarcodeView.EnumC0786a.f3785a) {
                        barcodeCallback2 = this.handShakerBarcodeView.barcodeCallback;
                        barcodeCallback2.possibleResultPoints(list);
                    }
                }
                return true;
            }
            return false;
        }
    }
}
