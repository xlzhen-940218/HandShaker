package com.smartisanos.smartfolder.aoa.qrcode;

import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCaptureManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.q */
/* loaded from: classes.dex */
public final class DialogInterface$OnCancelListenerC0805q implements DialogInterface.OnCancelListener {

    /* renamed from: a */
    final /* synthetic */ HandShakerCaptureManager f3857a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnCancelListenerC0805q(HandShakerCaptureManager handShakerCaptureManager) {
        this.f3857a = handShakerCaptureManager;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public final void onCancel(DialogInterface dialogInterface) {
        this.f3857a.activity.finish();
    }
}
