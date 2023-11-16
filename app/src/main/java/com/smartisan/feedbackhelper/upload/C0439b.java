package com.smartisan.feedbackhelper.upload;

import android.os.Looper;
import android.widget.Toast;

import com.smartisanos.smartfolder.aoa.R;


/* compiled from: ReliableUploader.java */
/* renamed from: com.smartisan.feedbackhelper.upload.b */
/* loaded from: classes.dex */
final class C0439b extends Thread {

    /* renamed from: a */
    final /* synthetic */ ReliableUploader f2459a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0439b(ReliableUploader reliableUploader) {
        this.f2459a = reliableUploader;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        Looper.prepare();
        Toast.makeText(this.f2459a, R.string.report_success_notification, 1).show();
        this.f2459a.onDestroy();
        Looper.loop();
    }
}
