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
    final /* synthetic */ HandShakerCameraPreview f3829a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SurfaceHolder$CallbackC0792c(HandShakerCameraPreview handShakerCameraPreview) {
        this.f3829a = handShakerCameraPreview;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.f3829a.f3806q = null;
    }

    @Override // android.view.SurfaceHolder.Callback
    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        String str;
        if (surfaceHolder == null) {
            str = HandShakerCameraPreview.TAG;
            Log.e(str, "*** WARNING *** surfaceChanged() gave us a null surface!");
            return;
        }
        this.f3829a.f3806q = new Size(i2, i3);
        this.f3829a.m342l();
    }
}
