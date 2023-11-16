package com.smartisan.feedbackhelper.upload;

import android.os.Build;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ReportSender.java */
/* renamed from: com.smartisan.feedbackhelper.upload.f */
/* loaded from: classes.dex */
public final class ReportSenderJsonRequest extends JsonObjectRequest {

    /* renamed from: a */
    final /* synthetic */ ReportSender reportSender;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReportSenderJsonRequest(ReportSender reportSender, String str, JSONObject jSONObject, Response.Listener listener, Response.ErrorListener errorListener) {
        super(str, jSONObject, listener, errorListener);
        this.reportSender = reportSender;
    }

    @Override // com.android.volley.Request
    /* renamed from: h */
    public final Map<String, String> getParams() {
        String str;
        int i;
        HashMap hashMap = new HashMap();
        hashMap.put("content-type", "application/json");
        str = this.reportSender.deviceId;
        hashMap.put("X-deviceid", str);
        hashMap.put("X-product", Build.MODEL);
        i = this.reportSender.f2468i;
        hashMap.put("X-usertype", String.valueOf(i));
        return hashMap;
    }


}
