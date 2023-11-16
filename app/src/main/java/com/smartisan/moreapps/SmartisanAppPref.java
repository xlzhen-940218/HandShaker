package com.smartisan.moreapps;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.smartisan.moreapps.j */
/* loaded from: classes.dex */
public final class SmartisanAppPref {

    /* renamed from: b */
    private static SmartisanAppPref f2656b;

    /* renamed from: a */
    private SharedPreferences f2657a;

    private SmartisanAppPref() {
    }

    /* renamed from: a */
    public static SmartisanAppPref m1972a(Context context) {
        if (f2656b == null) {
            SmartisanAppPref smartisanAppPref = new SmartisanAppPref();
            f2656b = smartisanAppPref;
            smartisanAppPref.f2657a = context.getSharedPreferences("smartisan_apps", 0);
        }
        return f2656b;
    }

    /* renamed from: a */
    public final void m1970a(String str, long j) {
        this.f2657a.edit().putLong(str, j).commit();
    }

    /* renamed from: a */
    public final long m1971a(String str) {
        return this.f2657a.getLong(str, -1L);
    }

    /* renamed from: a */
    public final void m1974a(int i) {
        this.f2657a.edit().putInt("app_list_verion", i).commit();
    }

    /* renamed from: a */
    public final int m1975a() {
        return this.f2657a.getInt("app_list_verion", 0);
    }

    /* renamed from: a */
    public final void m1973a(long j) {
        this.f2657a.edit().putLong("app_list_check_time", j).commit();
    }

    /* renamed from: b */
    public final long m1968b() {
        return this.f2657a.getLong("app_list_check_time", 0L);
    }

    /* renamed from: a */
    public final void m1969a(boolean z) {
        this.f2657a.edit().putBoolean("app_list_need_update", z).commit();
    }

    /* renamed from: c */
    public final boolean m1967c() {
        return this.f2657a.getBoolean("app_list_need_update", false);
    }
}
