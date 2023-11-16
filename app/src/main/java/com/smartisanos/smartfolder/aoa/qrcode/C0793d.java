package com.smartisanos.smartfolder.aoa.qrcode;

import android.os.Handler;
import android.os.Message;
import com.journeyapps.barcodescanner.Size;
import com.smartisanos.smartfolder.aoa.R;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCameraPreview.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.d */
/* loaded from: classes.dex */
public final class C0793d implements Handler.Callback {

    /* renamed from: a */
    final /* synthetic */ HandShakerCameraPreview handShakerCameraPreview;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0793d(HandShakerCameraPreview handShakerCameraPreview) {
        this.handShakerCameraPreview = handShakerCameraPreview;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        HandShakerCameraPreview.InterfaceC0787a interfaceC0787a;
        HandShakerCameraPreview.InterfaceC0787a interfaceC0787a2;
        if (message.what == R.id.zxing_prewiew_size_ready) {
            HandShakerCameraPreview.m355b(this.handShakerCameraPreview, (Size) message.obj);
            return true;
        }
        if (message.what == R.id.zxing_camera_error) {
            Exception exc = (Exception) message.obj;
            if (this.handShakerCameraPreview.m347g()) {
                this.handShakerCameraPreview.pause();
                interfaceC0787a2 = this.handShakerCameraPreview.f3790A;
                interfaceC0787a2.error(exc);
            }
        } else if (message.what == R.id.zxing_camera_closed) {
            interfaceC0787a = this.handShakerCameraPreview.f3790A;
            interfaceC0787a.close();
        }
        return false;
    }
}
