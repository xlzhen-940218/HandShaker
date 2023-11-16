package com.smartisan.feedbackhelper.upload;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.smartisan.feedbackhelper.utils.ComplainReport;
import com.smartisan.feedbackhelper.utils.DeviceID;
import com.smartisan.feedbackhelper.utils.JsonData;
import com.smartisan.feedbackhelper.utils.MultiPartStack;

import org.json.JSONException;

/* renamed from: com.smartisan.feedbackhelper.upload.c */
/* loaded from: classes.dex */
public final class ReportSender {

    /* renamed from: c */
    public static RequestQueue f2460c;

    /* renamed from: d */
    public static RequestQueue f2461d;

    /* renamed from: b */
    Context context;

    /* renamed from: h */
    public String deviceId;

    /* renamed from: i */
    public int f2468i;

    /* renamed from: j */
    public UploadWorker f2469j;

    /* renamed from: a */
    String f2462a = "BugReportReportSender";

    /* renamed from: f */
    private String f2465f = "http://auto.smartisan.com/v2/api/report";

    /* renamed from: g */
    String f2466g = "http://auto.smartisan.com/v2/api/log?tid=";

    /* renamed from: e */
    Handler f2464e = new HandlerC0446j(this);

    public ReportSender(ReliableUploader reliableUploader, UploadWorker uploadWorker) {
        this.context = reliableUploader.getBaseContext();
        this.deviceId = DeviceID.m2049a().m2048a(this.context);
        if (f2460c == null) {
            f2460c = Volley.newRequestQueue(this.context, null);
        }
        if (f2461d == null) {
            f2461d = Volley.newRequestQueue(this.context, new MultiPartStack());
        }
        this.f2469j = uploadWorker;
    }

    /* renamed from: a */
    public final void m2115a(ComplainReport complainReport) throws JSONException {
        RequestQueue requestQueue = f2460c;
        ReportSenderJsonRequest reportSenderJsonRequest = new ReportSenderJsonRequest(this
                , this.f2465f, JsonData.toJson(this.context, complainReport), new C0440d(this, complainReport)
                , new ReportSenderUploadErrorListener(this, complainReport));
        reportSenderJsonRequest.setTag((Object) "obj");
        requestQueue.add(reportSenderJsonRequest);
        requestQueue.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void errorResponse(int i, ComplainReport complainReport) {
        Message message = new Message();
        message.what = i;
        message.obj = complainReport;
        this.f2464e.sendMessage(message);
    }
}
