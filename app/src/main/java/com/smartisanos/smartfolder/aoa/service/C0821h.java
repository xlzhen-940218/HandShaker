package com.smartisanos.smartfolder.aoa.service;

import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Message;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

import java.util.Queue;

/* compiled from: MediaScannerService.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.h */
/* loaded from: classes.dex */
final class C0821h implements MediaScannerConnection.MediaScannerConnectionClient {

    /* renamed from: a */
    final /* synthetic */ MediaScannerService f3899a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0821h(MediaScannerService mediaScannerService) {
        this.f3899a = mediaScannerService;
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x000a */
    @Override // android.media.MediaScannerConnection.MediaScannerConnectionClient
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void onMediaScannerConnected() {
        Queue queue = null;
        MediaScannerService.HandlerC0812b handlerC0812b;
        Queue queue2;
        MediaScannerService.HandlerC0812b handlerC0812b2;
        while (!queue.isEmpty()) {
            handlerC0812b = this.f3899a.f3878c;
            queue2 = this.f3899a.f3880e;
            Message obtainMessage = handlerC0812b.obtainMessage(0, queue2.poll());
            handlerC0812b2 = this.f3899a.f3878c;
            handlerC0812b2.sendMessage(obtainMessage);
        }
    }

    @Override // android.media.MediaScannerConnection.OnScanCompletedListener
    public final void onScanCompleted(String str, Uri uri) {
        String str2;
        str2 = MediaScannerService.TAG;
        HandShaker.verbose(str2, "onScanCompleted:" + str);
    }
}
