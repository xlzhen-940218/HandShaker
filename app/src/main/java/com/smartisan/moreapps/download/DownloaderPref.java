package com.smartisan.moreapps.download;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.smartisan.moreapps.download.b */
/* loaded from: classes.dex */
public final class DownloaderPref {

    /* renamed from: b */
    private static DownloaderPref f2644b;

    /* renamed from: a */
    private SharedPreferences f2645a;

    private DownloaderPref() {
    }

    /* renamed from: a */
    public static DownloaderPref m1987a(Context context) {
        if (f2644b == null) {
            DownloaderPref downloaderPref = new DownloaderPref();
            f2644b = downloaderPref;
            downloaderPref.f2645a = context.getSharedPreferences("apk_download", 0);
        }
        return f2644b;
    }

    /* renamed from: a */
    public final long m1986a(String str) {
        return this.f2645a.getLong(str, -1L);
    }
}
