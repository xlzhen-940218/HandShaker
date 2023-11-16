package com.smartisan.feedbackhelper.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.OldHurlStack;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

/* renamed from: com.smartisan.feedbackhelper.utils.l */
/* loaded from: classes.dex */
public class MultiPartStack extends OldHurlStack {

    /* renamed from: a */
    private static final String f2598a = MultiPartStack.class.getSimpleName();

    @Override // com.android.volley.toolbox.HurlStack, com.android.volley.toolbox.HttpStack
    /* renamed from: a */
    public final HttpResponse performRequest(Request<?> request, Map<String, String> map) throws AuthFailureError, IOException {
        HttpRequestBase httpPut;
        if (!(request instanceof MultiPartRequest)) {
            return super.performRequest(request, map);
        }
        switch (request.getMethod()) {
            case -1:
                byte[] mo2330l = request.getBody();
                if (mo2330l != null) {
                    httpPut = new HttpPost(request.getUrl());
                    if (request.getBodyContentType() != null) {
                        httpPut.addHeader("Content-Type", request.getBodyContentType());
                    }
                    ((HttpPost)httpPut).setEntity(new ByteArrayEntity(mo2330l));
                    break;
                } else {
                    httpPut = new HttpGet(request.getUrl());
                    break;
                }
            case 0:
                httpPut = new HttpGet(request.getUrl());
                break;
            case 1:
                httpPut = new HttpPost(request.getUrl());
                if (request.getBodyContentType() != null) {
                    httpPut.addHeader("Content-Type", request.getBodyContentType());
                }
                m2042a((HttpEntityEnclosingRequestBase) httpPut, request);
                break;
            case 2:
                httpPut = new HttpPut(request.getUrl());
                if (request.getBodyContentType() != null) {
                    httpPut.addHeader("Content-Type", request.getBodyContentType());
                }
                m2042a((HttpEntityEnclosingRequestBase) httpPut, request);
                break;
            case 3:
                httpPut = new HttpDelete(request.getUrl());
                break;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
        addHeaders((HttpUriRequest) httpPut, map);
        addHeaders((HttpUriRequest) httpPut, request.getHeaders());
        HttpParams params = httpPut.getParams();
        int m2407o = request.getTimeoutMs();
        if (m2407o != -1) {
            HttpConnectionParams.setSoTimeout(params, m2407o);
        }
        return new DefaultHttpClient(params).execute(httpPut);
    }

    /* renamed from: a */
    private static void addHeaders(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String str : map.keySet()) {
            httpUriRequest.setHeader(str, map.get(str));
        }
    }

    /* renamed from: a */
    private static void m2042a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, Request<?> request) {
        if (request instanceof MultiPartRequest) {
            MultipartEntityBuilder create = MultipartEntityBuilder.create();
            create.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            for (Map.Entry<String, File> entry : ((MultiPartRequest) request).getFilePartMaps().entrySet()) {
                create.addPart(entry.getKey(), new FileBody(entry.getValue()));
            }
            ContentType create2 = ContentType.create(HTTP.PLAIN_TEXT_TYPE, HTTP.UTF_8);
            Map<String, String> mo2036t = ((MultiPartRequest) request).getParamMaps();
            if (mo2036t != null) {
                for (Map.Entry<String, String> entry2 : mo2036t.entrySet()) {
                    try {
                        create.addPart(entry2.getKey(), new StringBody(entry2.getValue(), create2));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            httpEntityEnclosingRequestBase.setEntity(create.build());
        }
    }
}
