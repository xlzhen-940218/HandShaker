package com.smartisan.updater;

import android.content.Context;
import android.content.SharedPreferences;

/* renamed from: com.smartisan.updater.m */
/* loaded from: classes.dex */
public final class UpdateSharedPreference {

    /* renamed from: b */
    private static UpdateSharedPreference f2735b;

    /* renamed from: a */
    private SharedPreferences f2736a;

    /* renamed from: c */
    public InterfaceC0483a f2737c;

    /* renamed from: d */
    private SharedPreferences.OnSharedPreferenceChangeListener f2738d;

    /* compiled from: UpdateSharedPreference.java */
    /* renamed from: com.smartisan.updater.m$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0483a {
    }

    private UpdateSharedPreference() {
    }

    /* renamed from: a */
    public static UpdateSharedPreference m1849a(Context context) {
        if (f2735b == null) {
            UpdateSharedPreference updateSharedPreference = new UpdateSharedPreference();
            f2735b = updateSharedPreference;
            updateSharedPreference.f2736a = context.getSharedPreferences("version_update", 0);
            updateSharedPreference.f2738d = new SharedPreferences$OnSharedPreferenceChangeListenerC0484n(updateSharedPreference);
            updateSharedPreference.f2736a.registerOnSharedPreferenceChangeListener(updateSharedPreference.f2738d);
        }
        return f2735b;
    }

    /* renamed from: a */
    public final void m1850a(long j) {
        this.f2736a.edit().putLong("update_time", j).commit();
    }

    /* renamed from: a */
    public final boolean m1852a() {
        return this.f2736a.getBoolean("show_dialog", true);
    }

    /* renamed from: a */
    public final void m1847a(boolean z) {
        this.f2736a.edit().putBoolean("show_dialog", z).commit();
    }

    /* renamed from: b */
    public final void m1845b(long j) {
        this.f2736a.edit().putLong("download_id", j).commit();
    }

    /* renamed from: b */
    public final long m1846b() {
        return this.f2736a.getLong("download_id", -1L);
    }

    /* renamed from: a */
    public final void m1851a(int i) {
        this.f2736a.edit().putInt("check_version", i).commit();
    }

    /* renamed from: c */
    public final int m1844c() {
        return this.f2736a.getInt("check_version", 0);
    }
}
