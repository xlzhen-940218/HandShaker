package com.smartisan.feedbackhelper.upload;

import android.os.Handler;
import android.os.Message;
import com.smartisan.feedbackhelper.upload.UploadWorker;
import com.smartisan.feedbackhelper.utils.ComplainReport;
import org.apache.http.entity.ContentLengthStrategy;
import org.json.JSONException;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReportSender.java */
/* renamed from: com.smartisan.feedbackhelper.upload.j */
/* loaded from: classes.dex */
public final class HandlerC0446j extends Handler {

    /* renamed from: a */
    final /* synthetic */ ReportSender f2482a;

    /* renamed from: b */
    private int f2483b = 1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC0446j(ReportSender reportSender) {
        this.f2482a = reportSender;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        UploadWorker uploadWorker;
        UploadWorker uploadWorker2;
        switch (message.what) {
            case ContentLengthStrategy.IDENTITY /* -1 */:
                if (this.f2483b > 3) {
                    uploadWorker2 = this.f2482a.f2469j;
                    uploadWorker2.m2108a(UploadWorker.EnumC0448b.f2497c);
                    this.f2483b = 0;
                } else {
                    try {
                        this.f2482a.m2115a((ComplainReport) message.obj);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                }
                this.f2483b++;
                return;
            case 0:
            default:
                return;
            case 1:
                uploadWorker = this.f2482a.f2469j;
                uploadWorker.m2108a(UploadWorker.EnumC0448b.f2498d);
                this.f2483b = 1;
                return;
        }
    }
}
