package com.smartisanos.smartfolder.aoa.qrcode;

import android.graphics.SurfaceTexture;
import android.view.TextureView;
import com.journeyapps.barcodescanner.Size;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCameraPreview.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.b */
/* loaded from: classes.dex */
public final class TextureView$SurfaceTextureListenerC0791b implements TextureView.SurfaceTextureListener {

    /* renamed from: a */
    final /* synthetic */ HandShakerCameraPreview handShakerCameraPreview;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextureView$SurfaceTextureListenerC0791b(HandShakerCameraPreview handShakerCameraPreview) {
        this.handShakerCameraPreview = handShakerCameraPreview;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        onSurfaceTextureSizeChanged(surfaceTexture, i, i2);
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int width, int height) {
        this.handShakerCameraPreview.surfaceSize = new Size(width, height);
        this.handShakerCameraPreview.m342l();
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
