package com.smartisan.updater;

import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/* renamed from: com.smartisan.updater.i */
/* loaded from: classes.dex */
public final class HttpGetData {
    /* renamed from: a */
    public static InputStream m1856a(String str) {
        int i = 0;
        InputStream inputStream = null;
        if (!TextUtils.isEmpty(str)) {
            while (i < 3) {
                i++;
                inputStream = m1855b(str);
                if (inputStream != null) {
                    break;
                }
            }
        }
        return inputStream;
    }

    /* renamed from: b */
    private static InputStream m1855b(String str) {
        try {
            return new URL(str).openStream();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String streamToString(InputStream inputStream, String str) throws IOException {
        if (inputStream == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
        char[] cArr = new char[10240];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read > 0) {
                stringBuffer.append(cArr, 0, read);
            } else {
                return stringBuffer.toString();
            }
        }
    }

    /* renamed from: a */
    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
