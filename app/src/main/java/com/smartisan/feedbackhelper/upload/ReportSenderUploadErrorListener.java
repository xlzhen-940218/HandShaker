package com.smartisan.feedbackhelper.upload;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.smartisan.feedbackhelper.utils.ComplainReport;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReportSender.java */
/* renamed from: com.smartisan.feedbackhelper.upload.e */
/* loaded from: classes.dex */
public final class ReportSenderUploadErrorListener implements Response.ErrorListener {

    /* renamed from: a */
    final /* synthetic */ ComplainReport complainReport;

    /* renamed from: b */
    final /* synthetic */ ReportSender reportSender;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReportSenderUploadErrorListener(ReportSender reportSender, ComplainReport complainReport) {
        this.reportSender = reportSender;
        this.complainReport = complainReport;
    }

    @Override // com.android.volley.Response.InterfaceC0359a
    /* renamed from: a */
    public final void onErrorResponse(VolleyError volleyError) {
        this.reportSender.errorResponse(-1, this.complainReport);
    }
}
