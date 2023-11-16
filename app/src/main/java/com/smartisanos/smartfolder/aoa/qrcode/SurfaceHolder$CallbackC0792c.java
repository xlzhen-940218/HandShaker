package com.smartisanos.smartfolder.aoa.qrcode;

import android.util.Log;
import android.view.SurfaceHolder;
import com.journeyapps.barcodescanner.Size;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCameraPreview.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.c */
/* loaded from: classes.dex */
public final class SurfaceHolder$CallbackC0792c implements SurfaceHolder.Callback {

    /* renamed from: a */
    final /* synthetic */ HandShakerCameraPreview handShakerCameraPreview;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SurfaceHolder$CallbackC0792c(HandShakerCameraPreview handShakerCameraPreview) {
        this.handShakerCameraPreview = handShakerCameraPreview;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.handShakerCameraPreview.surfaceSize = null;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        String str;
        if (surfaceHolder == null) {
            str = HandShakerCameraPreview.TAG;
            Log.e(str, "*** WARNING *** surfaceChanged() gave us a null surface!");
            return;
        }
        this.handShakerCameraPreview.surfaceSize = new Size(i2, i3);
        this.handShakerCameraPreview.m342l();
    }
}
