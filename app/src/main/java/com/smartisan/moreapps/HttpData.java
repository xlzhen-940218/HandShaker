package com.smartisan.moreapps;

import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import org.apache.http.protocol.HTTP;

/* renamed from: com.smartisan.moreapps.d */
/* loaded from: classes.dex */
public final class HttpData {
    /* renamed from: a */
    public static String m1992a(String str) {
        int i = 0;
        InputStream inputStream = null;
        if (!TextUtils.isEmpty(str)) {
            while (i < 3) {
                i++;
                inputStream = m1991b(str);
                if (inputStream != null) {
                    break;
                }
            }
        }
        return m1993a(inputStream);
    }

    /* renamed from: b */
    public static InputStream m1991b(String str) {
        try {
            return new URL(str).openStream();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private static String m1993a(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        char[] cArr = new char[1024];
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                try {
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, HTTP.UTF_8);
                    while (true) {
                        int read = inputStreamReader.read(cArr);
                        if (read <= 0) {
                            break;
                        }
                        stringBuffer.append(cArr, 0, read);
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (UnsupportedEncodingException e2) {
                    e2.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                }
            } catch (IOException e4) {
                e4.printStackTrace();
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
            }
            return stringBuffer.toString();
        } catch (Throwable th) {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            }
            throw th;
        }
    }
}
