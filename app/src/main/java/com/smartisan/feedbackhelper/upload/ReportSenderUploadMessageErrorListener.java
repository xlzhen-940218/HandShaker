package com.smartisan.feedbackhelper.upload;

import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.smartisan.feedbackhelper.utils.ComplainReport;

/* compiled from: ReportSender.java */
/* renamed from: com.smartisan.feedbackhelper.upload.h */
/* loaded from: classes.dex */
final class ReportSenderUploadMessageErrorListener implements Response.ErrorListener {

    /* renamed from: a */
    final /* synthetic */ ComplainReport complainReport;

    /* renamed from: b */
    final /* synthetic */ ReportSender reportSender;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReportSenderUploadMessageErrorListener(ReportSender reportSender, ComplainReport complainReport) {
        this.reportSender = reportSender;
        this.complainReport = complainReport;
    }

    @Override // com.android.volley.Response.InterfaceC0359a
    /* renamed from: a */
    public final void onErrorResponse(VolleyError volleyError) {
        if (volleyError != null) {
            if (volleyError.networkResponse != null) {
                Log.i(this.reportSender.f2462a, " error " + new String(volleyError.networkResponse.data));
            }
            this.reportSender.errorResponse(-1, this.complainReport);
        }
    }
}
