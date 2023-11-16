package com.smartisanos.smartfolder.aoa;

import android.content.Context;
import com.smartisan.updater.ApkUpdater;
import com.smartisan.updater.UpdateUI;

/* renamed from: com.smartisanos.smartfolder.aoa.h */
/* loaded from: classes.dex */
public final class VersionUpdater {
    /* renamed from: a */
    public static void m557a(Context context, boolean z, UpdateUI updateUI) {
        ApkUpdater apkUpdater = new ApkUpdater(context, "http://update.smartisanos.com/handshaker/update_info", z, context.getString(R.string.new_handshaker_name));
        apkUpdater.m1873c();
        if (z) {
            apkUpdater.m1877a(updateUI);
        }
        apkUpdater.m1875b();
        apkUpdater.m1883a();
    }
}
