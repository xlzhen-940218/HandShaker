package com.smartisan.feedbackhelper.utils;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.smartisan.feedbackhelper.utils.m */
/* loaded from: classes.dex */
public class MultiPartStringRequest extends Request<String> implements MultiPartRequest {

    /* renamed from: a */
    private final Response.Listener<String> listener;

    /* renamed from: b */
    private Map<String, File> filePartMaps;

    /* renamed from: c */
    private Map<String, String> paramsMaps;

    public MultiPartStringRequest(String str, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(2, str, errorListener);
        this.filePartMaps = new HashMap<>();
        this.paramsMaps = new HashMap<>();
        this.listener = listener;
    }

    /* renamed from: s */
    public Map<String, File> getFilePartMaps() {
        return this.filePartMaps;
    }

    /* renamed from: t */
    public Map<String, String> getParamMaps() {
        return this.paramsMaps;
    }

    @Override
    public String getBodyContentType() {
        return null;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String str;
        try {
            str = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            str = new String(response.data);
        }
        return Response.success(str, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        String str2 = response;
        if (this.listener == null) {
            return;
        }
        this.listener.onResponse(str2);
    }
}
