package com.smartisan.updater.p047a;

import android.os.Bundle;
import android.os.Parcelable;

/* renamed from: com.smartisan.updater.a.c */
/* loaded from: classes.dex */
public final class InterruptUtil {
    /* renamed from: a */
    public static <T extends Parcelable> T m1859a(Bundle bundle, Class<T> cls) {
        if (bundle == null) {
            return null;
        }
        try {
            return (T) bundle.getParcelable(cls.getName());
        } catch (ClassCastException e) {
            e.printStackTrace();
            return null;
        }
    }
}
