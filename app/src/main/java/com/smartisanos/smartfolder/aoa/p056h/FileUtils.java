package com.smartisanos.smartfolder.aoa.p056h;

import android.text.TextUtils;

/* renamed from: com.smartisanos.smartfolder.aoa.h.s */
/* loaded from: classes.dex */
public final class FileUtils {
    /* renamed from: a */
    public static boolean m414a(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith("/system");
    }
}
