package com.smartisanos.smartfolder.aoa.activity;

import android.content.Intent;
import android.view.View;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;

/* compiled from: ConnecttingFragment.java */
/* renamed from: com.smartisanos.smartfolder.aoa.activity.f */
/* loaded from: classes.dex */
final class View$OnClickListenerC0712f implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ StartScanQRCodeClickListener f3448a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0712f(StartScanQRCodeClickListener startScanQRCodeClickListener) {
        this.f3448a = startScanQRCodeClickListener;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (CommonUtils.isVivo()) {
            this.f3448a.connecttingFragment.startActivity(new Intent("android.settings.APPLICATION_SETTINGS"));
        } else if (!CommonUtils.checkCameraPermission()) {
            this.f3448a.connecttingFragment.startActivity(new Intent("android.settings.APPLICATION_SETTINGS"));
        }
    }
}
