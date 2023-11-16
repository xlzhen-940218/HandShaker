package com.smartisanos.smartfolder.aoa.activity;

import android.app.AlertDialog;
import android.view.View;

import com.google.zxing.integration.android.IntentIntegrator;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.aoa.p056h.NetWorkUtils;
import com.smartisanos.smartfolder.aoa.qrcode.ScanActivity;
import com.smartisanos.smartfolder.aoa.view.CustomDialogBuilder;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ConnecttingFragment.java */
/* renamed from: com.smartisanos.smartfolder.aoa.activity.e */
/* loaded from: classes.dex */
public final class StartScanQRCodeClickListener implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ConnecttingFragment connecttingFragment;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StartScanQRCodeClickListener(ConnecttingFragment connecttingFragment) {
        this.connecttingFragment = connecttingFragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        View view2;
        View view3;
        View view4;
        AlertDialog alertDialog;
        AlertDialog alertDialog2;
        int i = R.string.button_setting_txt;
        if (!NetWorkUtils.m379a(FolderApp.getInstance())) {
            view2 = this.connecttingFragment.f3442c;
            view3 = this.connecttingFragment.noWifiLayoutView;
            if (view2 != view3) {
                ConnecttingFragment connecttingFragment = this.connecttingFragment;
                view4 = this.connecttingFragment.noWifiLayoutView;
                connecttingFragment.f3442c = view4;
                this.connecttingFragment.m777b();
                return;
            }
            return;
        }
        if (!(CommonUtils.isVivo() ? CommonUtils.checkVivoCameraPermission() : CommonUtils.m481u())) {
            this.connecttingFragment.m775c();
            String string = this.connecttingFragment.getResources().getString(R.string.scan_camera_occupy_txt);
            if (CommonUtils.isVivo()) {
                string = this.connecttingFragment.getResources().getString(R.string.scan_camera_permission_txt);
            } else if (CommonUtils.checkCameraPermission()) {
                i = R.string.button_verify_txt;
            } else {
                string = this.connecttingFragment.getResources().getString(R.string.scan_camera_permission_txt);
            }
            CustomDialogBuilder m209a = CustomDialogBuilder.m209a(this.connecttingFragment.getActivity());
            m209a.setTitle(R.string.permission_title);
            m209a.setMessage(string);
            m209a.m210a(i, new View$OnClickListenerC0712f(this));
            this.connecttingFragment.f3444e = m209a.create();
            alertDialog = this.connecttingFragment.f3444e;
            alertDialog.setCancelable(false);
            alertDialog2 = this.connecttingFragment.f3444e;
            alertDialog2.show();
            return;
        }
        new IntentIntegrator(this.connecttingFragment.getActivity()).setCaptureActivity(ScanActivity.class)
                .setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
                .setCameraId(0).setBeepEnabled(true).setBarcodeImageEnabled(true).createScanIntent();
    }
}
