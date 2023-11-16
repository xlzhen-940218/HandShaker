package com.smartisanos.smartfolder.aoa.qrcode;

import android.app.Activity;
import android.widget.Toast;
import com.smartisanos.smartfolder.aoa.R;

/* compiled from: HandShakerCaptureManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.k */
/* loaded from: classes.dex */
final class RunnableC0799k implements Runnable {

    /* renamed from: a */
    final /* synthetic */ SBarcodeCallback f3851a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0799k(SBarcodeCallback c0797i) {
        this.f3851a = c0797i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Activity activity;
        Activity activity2;
        activity = this.f3851a.handShakerCaptureManager.activity;
        String string = activity.getResources().getString(R.string.scan_qrcode_error_toast);
        activity2 = this.f3851a.handShakerCaptureManager.activity;
        Toast.makeText(activity2, string, Toast.LENGTH_SHORT).show();
    }
}
