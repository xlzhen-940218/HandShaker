package com.smartisan.updater;

import android.content.SharedPreferences;
import com.smartisan.updater.UpdateSharedPreference;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UpdateSharedPreference.java */
/* renamed from: com.smartisan.updater.n */
/* loaded from: classes.dex */
public final class SharedPreferences$OnSharedPreferenceChangeListenerC0484n implements SharedPreferences.OnSharedPreferenceChangeListener {

    /* renamed from: a */
    final /* synthetic */ UpdateSharedPreference f2739a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SharedPreferences$OnSharedPreferenceChangeListenerC0484n(UpdateSharedPreference updateSharedPreference) {
        this.f2739a = updateSharedPreference;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        UpdateSharedPreference.InterfaceC0483a interfaceC0483a;
        if ("need_update".equals(str)) {
            interfaceC0483a = this.f2739a.f2737c;
            if (interfaceC0483a != null) {
                sharedPreferences.getBoolean(str, false);
            }
        }
    }
}
