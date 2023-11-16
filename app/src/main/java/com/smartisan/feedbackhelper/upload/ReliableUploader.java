package com.smartisan.feedbackhelper.upload;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.smartisan.feedbackhelper.upload.UploadWorker;
import com.smartisan.feedbackhelper.utils.ComplainReport;

/* loaded from: classes.dex */
public class ReliableUploader extends Service {

    /* renamed from: a */
    private int f2456a = UploadWorker.EnumC0448b.f2498d;

    /* renamed from: b */
    private UploadWorker f2457b;

    @Override // android.app.Service
    public void onCreate() {
        Log.i("BugReportReliableUploader", "onCreate()");
        super.onCreate();
        this.f2457b = new UploadWorker(this);
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        Log.i("BugReportReliableUploader", "onStartCommand()");
        ComplainReport complainReport = null;
        if (intent != null) {
            complainReport = (ComplainReport) intent.getParcelableExtra("COMPLAINT_REPORT");
        }
        if (complainReport != null) {
            m2119a(complainReport);
            return 1;
        }
        return 1;
    }

    /* renamed from: a */
    private synchronized void m2119a(ComplainReport complainReport) {
        Log.d("BugReportReliableUploader", "startUpload");
        this.f2457b.m2106a(complainReport);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onDestroy() {
        Log.d("BugReportReliableUploader", "Reliable Upload onDestroy().");
        super.onDestroy();
    }
}
