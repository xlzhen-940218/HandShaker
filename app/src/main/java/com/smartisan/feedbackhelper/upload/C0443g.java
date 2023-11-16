package com.smartisan.feedbackhelper.upload;

import android.util.Log;
import com.android.volley.Response;
import com.smartisan.feedbackhelper.utils.ComplainReport;

/* compiled from: ReportSender.java */
/* renamed from: com.smartisan.feedbackhelper.upload.g */
/* loaded from: classes.dex */
final class C0443g implements Response.Listener<String> {

    /* renamed from: a */
    final /* synthetic */ ComplainReport complainReport;

    /* renamed from: b */
    final /* synthetic */ ReportSender reportSender;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0443g(ReportSender reportSender, ComplainReport complainReport) {
        this.reportSender = reportSender;
        this.complainReport = complainReport;
    }

    @Override // com.android.volley.Response.Listener
    /* renamed from: a */
    public final /* synthetic */ void onResponse(String str) {
        Log.i(this.reportSender.f2462a, "File response");
        this.reportSender.errorResponse(1, this.complainReport);
    }
}
