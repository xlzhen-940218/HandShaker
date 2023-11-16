package com.smartisan.moreapps;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* renamed from: com.smartisan.moreapps.k */
/* loaded from: classes.dex */
public final class SmartisanAppUtils {
    /* renamed from: a */
    public static int m1966a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
            if (networkInfo != null && networkInfo.isConnected()) {
                return 1;
            }
            NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
            if (networkInfo2 != null && networkInfo2.isConnected()) {
                return 2;
            }
        }
        return 0;
    }

    /* renamed from: b */
    public static boolean m1965b(Context context) {
        return 1 == m1966a(context);
    }
}
