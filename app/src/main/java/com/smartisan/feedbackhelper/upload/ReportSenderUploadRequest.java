package com.smartisan.feedbackhelper.upload;

import android.os.Build;
import com.android.volley.Response;
import com.smartisan.feedbackhelper.utils.MultiPartStringRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ReportSender.java */
/* renamed from: com.smartisan.feedbackhelper.upload.i */
/* loaded from: classes.dex */
final class ReportSenderUploadRequest extends MultiPartStringRequest {

    /* renamed from: a */
    final /* synthetic */ Map filePartMaps;

    /* renamed from: b */
    final /* synthetic */ Map paramMaps = null;

    /* renamed from: c */
    final /* synthetic */ ReportSender reportSender;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReportSenderUploadRequest(ReportSender reportSender, String str, Response.Listener listener, Response.ErrorListener errorListener, Map filePartMaps) {
        super(str, listener, errorListener);
        this.reportSender = reportSender;
        this.filePartMaps = filePartMaps;
    }

    @Override // com.smartisan.feedbackhelper.utils.MultiPartStringRequest, com.smartisan.feedbackhelper.utils.MultiPartRequest
    /* renamed from: s */
    public final Map<String, File> getFilePartMaps() {
        return this.filePartMaps;
    }

    @Override // com.smartisan.feedbackhelper.utils.MultiPartStringRequest, com.smartisan.feedbackhelper.utils.MultiPartRequest
    /* renamed from: t */
    public final Map<String, String> getParamMaps() {
        return this.paramMaps;
    }

    @Override // com.android.volley.Request
    /* renamed from: h */
    public final Map<String, String> getHeaders() {
        int i;
        HashMap hashMap = new HashMap();
        hashMap.put("X-deviceid", this.reportSender.deviceId);
        hashMap.put("X-product", Build.MODEL);
        i = this.reportSender.f2468i;
        hashMap.put("X-usertype", String.valueOf(i));
        return hashMap;
    }


}
