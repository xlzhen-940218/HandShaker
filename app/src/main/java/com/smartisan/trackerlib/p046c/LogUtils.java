package com.smartisan.trackerlib.p046c;

import android.text.TextUtils;
import android.util.Log;

import java.util.Locale;

import org.apache.http.HttpStatus;

/* renamed from: com.smartisan.trackerlib.c.b */
/* loaded from: classes.dex */
public class LogUtils {
    /* renamed from: a */
    private static boolean m1903a(int i) {
        return 2 <= i;
    }

    /* renamed from: a */
    public static void m1902a(String str) {
        if (m1903a(3)) {
            m1904a('d', "TrackSmartisan", str);
        }
    }

    /* renamed from: b */
    public static void m1901b(String str) {
        if (m1903a(6)) {
            m1904a('e', "TrackSmartisan", str);
        }
    }

    /* renamed from: a */
    private static void m1904a(char c, String str, String str2) {
        String m1900c;
        String str3;
        for (int i = 0; i < str2.length(); i += 3500) {
            if (i + 3800 < str2.length()) {
                m1900c = m1900c(str2.substring(i, i + 3500));
            } else {
                m1900c = m1900c(str2.substring(i, str2.length()));
            }
            StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
            int i2 = 3;
            while (true) {
                if (i2 >= stackTrace.length) {
                    str3 = "<unknown>";
                    break;
                } else if (stackTrace[i2].getClassName().equals(LogUtils.class.getName())) {
                    i2++;
                } else {
                    String className = stackTrace[i2].getClassName();
                    String substring = className.substring(className.lastIndexOf(46) + 1);
                    str3 = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[i2].getMethodName();
                    break;
                }
            }
            String format = String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str3, m1900c);
            switch (c) {
                case HttpStatus.SC_CONTINUE /* 100 */:
                    Log.d(str, format);
                    break;
                case HttpStatus.SC_SWITCHING_PROTOCOLS /* 101 */:
                    Log.e(str, format);
                    break;
                case 'i':
                    Log.i(str, format);
                    break;
                case 'v':
                    Log.v(str, format);
                    break;
                case 'w':
                    Log.w(str, format);
                    break;
                default:
                    Log.wtf(str, format);
                    break;
            }
        }
    }

    /* renamed from: c */
    private static String m1900c(String str) {
        if (TextUtils.isEmpty(str)) {
            return "No msg for this report";
        }
        return str;
    }
}
