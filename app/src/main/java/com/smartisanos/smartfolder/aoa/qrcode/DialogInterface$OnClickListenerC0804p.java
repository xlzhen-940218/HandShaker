package com.smartisanos.smartfolder.aoa.qrcode;

import android.content.DialogInterface;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCaptureManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.p */
/* loaded from: classes.dex */
public final class DialogInterface$OnClickListenerC0804p implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ HandShakerCaptureManager f3856a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0804p(HandShakerCaptureManager handShakerCaptureManager) {
        this.f3856a = handShakerCaptureManager;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.f3856a.activity.finish();
    }
}
