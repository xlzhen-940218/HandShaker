package com.smartisan.feedbackhelper.upload;

import android.os.Looper;
import android.widget.Toast;

import com.smartisanos.smartfolder.aoa.R;


/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReliableUploader.java */
/* renamed from: com.smartisan.feedbackhelper.upload.a */
/* loaded from: classes.dex */
public final class C0438a extends Thread {

    /* renamed from: a */
    final /* synthetic */ ReliableUploader f2458a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0438a(ReliableUploader reliableUploader) {
        this.f2458a = reliableUploader;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Looper.prepare();
        Toast.makeText(this.f2458a, R.string.report_failed_notification, 1).show();
        this.f2458a.onDestroy();
        Looper.loop();
    }
}
