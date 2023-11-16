package com.smartisanos.smartfolder.aoa.p056h;

import android.util.Log;
import com.smartisanos.smartfolder.aoa.FolderApp;

/* compiled from: LogUtils.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.t */
/* loaded from: classes.dex */
public final class HandShaker {

    /* renamed from: a */
    public static final boolean LOG;

    /* renamed from: b */
    public static String TAG = "HandShaker";

    static {
        FolderApp.getInstance();
        LOG = false;
    }

    /* renamed from: a */
    public static void debug(String str, String str2) {
        if (LOG) {
            Log.d(str, str2);
        }
    }

    /* renamed from: a */
    public static void debug(String str) {
        if (LOG) {
            Log.d(TAG, str);
        }
    }

    /* renamed from: b */
    public static void info(String str, String str2) {
        if (LOG) {
            Log.i(str, str2);
        }
    }

    /* renamed from: c */
    public static void error(String str, String str2) {
        if (LOG) {
            Log.e(str, str2);
        }
    }

    /* renamed from: b */
    public static void info(String str) {
        if (LOG) {
            Log.i(TAG, str);
        }
    }

    /* renamed from: d */
    public static void verbose(String str, String str2) {
        if (LOG) {
            Log.v(str, str2);
        }
    }

    /* renamed from: e */
    public static void warn(String str, String str2) {
        if (LOG) {
            Log.w(str, str2);
        }
    }

    /* renamed from: c */
    public static void warn(String str) {
        if (LOG) {
            Log.w(TAG, str);
        }
    }

    /* renamed from: d */
    public static void error(String str) {
        if (LOG) {
            Log.e(TAG, str);
        }
    }

    /* renamed from: f */
    public static void noLogInfo(String str, String str2) {
        Log.i(str, str2);
    }
}
