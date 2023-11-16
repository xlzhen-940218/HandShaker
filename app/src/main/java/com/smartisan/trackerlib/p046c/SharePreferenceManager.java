package com.smartisan.trackerlib.p046c;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* renamed from: com.smartisan.trackerlib.c.c */
/* loaded from: classes.dex */
public final class SharePreferenceManager {

    /* renamed from: a */
    public static SharePreferenceManager f2690a;

    private SharePreferenceManager() {
    }

    /* renamed from: a */
    public static synchronized SharePreferenceManager m1899a() {
        SharePreferenceManager sharePreferenceManager;
        synchronized (SharePreferenceManager.class) {
            if (f2690a == null) {
                f2690a = new SharePreferenceManager();
            }
            sharePreferenceManager = f2690a;
        }
        return sharePreferenceManager;
    }

    /* renamed from: a */
    private static SharedPreferences m1897a(String str, Context context) {
        if (TextUtils.isEmpty(str)) {
            str = "com.smartisanos.realtrack";
        }
        return context.getSharedPreferences(str, 0);
    }

    /* renamed from: a */
    public static boolean m1894a(String str, String str2, String str3, Context context) {
        return m1897a(str3, context).edit().putString(str, str2).commit();
    }

    /* renamed from: a */
    public static String m1895a(String str, String str2, Context context, String str3) {
        SharedPreferences m1897a = m1897a(str2, context);
        return m1897a != null ? m1897a.getString(str, str3) : str3;
    }

    /* renamed from: a */
    public static boolean m1898a(String str, int i, String str2, Context context) {
        return m1897a(str2, context).edit().putInt(str, i).commit();
    }

    /* renamed from: a */
    public static int m1896a(String str, String str2, Context context) {
        return m1897a(str2, context).getInt(str, -1);
    }
}
