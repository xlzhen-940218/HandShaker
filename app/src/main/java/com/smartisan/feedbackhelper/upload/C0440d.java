package com.smartisan.feedbackhelper.upload;

import android.util.Log;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.smartisan.feedbackhelper.utils.ComplainReport;
import java.io.File;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReportSender.java */
/* renamed from: com.smartisan.feedbackhelper.upload.d */
/* loaded from: classes.dex */
public final class C0440d implements Response.Listener<JSONObject> {

    /* renamed from: a */
    final /* synthetic */ ComplainReport f2470a;

    /* renamed from: b */
    final /* synthetic */ ReportSender f2471b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0440d(ReportSender reportSender, ComplainReport complainReport) {
        this.f2471b = reportSender;
        this.f2470a = complainReport;
    }

    @Override // com.android.volley.Response.Listener
    /* renamed from: a */
    public final /* synthetic */ void onResponse(JSONObject jSONObject) {
        int i;
        String str;
        ReportSenderUploadRequest reportSenderUploadRequest;
        JSONObject jSONObject2 = jSONObject;
        try {
            Log.i(this.f2471b.f2462a, "Struct Data response");
            ComplainReport complainReport = this.f2470a;
            if (complainReport.m2084i() == null) {
                i = jSONObject2.getJSONObject("data").getInt("tid");
                complainReport.m2089f("UploadID:" + i);
            } else if (complainReport.m2084i().contains("UploadID:")) {
                i = Integer.parseInt(complainReport.m2084i().split(":")[1]);
            } else {
                i = jSONObject2.getJSONObject("data").getInt("tid");
                complainReport.m2089f("UploadID:" + i);
            }
            StringBuilder sb = new StringBuilder();
            str = this.f2471b.f2466g;
            String sb2 = sb.append(str).append(i).toString();
            ReportSender reportSender = this.f2471b;
            RequestQueue requestQueue = ReportSender.f2461d;
            ComplainReport complainReport2 = this.f2470a;
            String m2098b = complainReport2.m2098b();
            HashMap hashMap = new HashMap();
            hashMap.put("Log-File", new File(m2098b));
            C0443g c0443g = new C0443g(reportSender, complainReport2);
            ReportSenderUploadMessageErrorListener reportSenderUploadMessageErrorListener = new ReportSenderUploadMessageErrorListener(reportSender, complainReport2);
            if (sb2 == null) {
                reportSenderUploadRequest = null;
            } else {
                reportSenderUploadRequest = new ReportSenderUploadRequest(reportSender, sb2, c0443g, reportSenderUploadMessageErrorListener, hashMap);
            }
            requestQueue.add(reportSenderUploadRequest);
        } catch (JSONException e) {
            this.f2471b.errorResponse(-1, this.f2470a);
        }
    }
}
