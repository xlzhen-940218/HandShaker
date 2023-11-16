package com.smartisan.feedbackhelper.utils;

import android.os.Environment;
import java.util.Properties;
import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;

/* renamed from: com.smartisan.feedbackhelper.utils.e */
/* loaded from: classes.dex */
public final class Constants {

    /* renamed from: a */
    public static final String f2565a;

    /* renamed from: b */
    public static final String f2566b;

    /* renamed from: c */
    public static final String f2567c;

    /* renamed from: d */
    public static final String f2568d;

    /* renamed from: e */
    public static final String f2569e;

    /* renamed from: f */
    public static final String f2570f;

    /* renamed from: g */
    public static final String f2571g;

    /* renamed from: h */
    public static final String f2572h;

    /* renamed from: i */
    public static final String f2573i;

    /* renamed from: j */
    public static final String f2574j;

    /* renamed from: k */
    public static final String f2575k;

    /* renamed from: l */
    public static final String f2576l;

    /* renamed from: m */
    public static final String f2577m;

    /* renamed from: n */
    public static final String f2578n;

    /* renamed from: o */
    private static CookieStore f2579o;

    static {
        Properties properties = new Properties();
        f2565a = properties.getProperty("bugreport.service", "bugreport");
        f2566b = properties.getProperty("server.host", "http://auto.smartisan.com");
        f2567c = Environment.getExternalStorageDirectory().getAbsolutePath();
        f2568d = f2567c + "/smartisan/feedback";
        String str = System.getenv("SECONDARY_STORAGE");
        if (str != null) {
            f2569e = str.split("[:]")[0];
        } else {
            f2569e = System.getenv("EXTERNAL_ALT_STORAGE");
        }
        f2570f = f2569e != null ? f2569e + "/smartisan/feedback" : null;
        f2571g = f2566b + "/cloud-api/login/clientLogon?";
        f2572h = f2566b + "/cloud-api/login/clientLogout?";
        f2573i = f2566b + "/cloud-api/files/list?";
        f2574j = f2566b + "/cloud-api/files/uploadSal?";
        f2575k = f2566b + "/cloud-api/folder/create?";
        f2576l = f2566b + "/cloud-api/files/getuploadid?";
        f2577m = f2566b + "/cloud-api/files/upload?";
        f2578n = f2566b + "/cloud-api/files/resume?";
        f2579o = new BasicCookieStore();
    }
}
