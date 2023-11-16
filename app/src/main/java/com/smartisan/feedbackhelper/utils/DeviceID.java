package com.smartisan.feedbackhelper.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

/* renamed from: com.smartisan.feedbackhelper.utils.i */
/* loaded from: classes.dex */
public final class DeviceID {

    /* renamed from: a */
    private static String f2591a = null;

    /* renamed from: b */
    private static String f2592b = null;

    /* renamed from: c */
    private static String f2593c = null;

    /* renamed from: d */
    private static String f2594d = null;

    /* renamed from: e */
    private static DeviceID f2595e = new DeviceID();

    private DeviceID() {
    }

    /* renamed from: a */
    public static DeviceID m2049a() {
        return f2595e;
    }

    /* renamed from: a */
    public final synchronized String m2048a(Context context) {
        String str;
        if (TextUtils.isEmpty(f2591a)) {
            String m2045c = m2045c();
            if ("SERIAL".equals(m2045c)) {
                f2591a = Build.SERIAL;
            } else if ("TELEPHONY_DEVICE_ID".equals(m2045c)) {
                f2591a = m2046b(context);
            } else if ("WIFI_MAC".equals(m2045c)) {
                f2591a = m2047b();
            }
            Log.d("BugReportDeviceID", String.format("DeviceID : %s, IDType: %s", f2591a, m2045c));
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            if (TextUtils.isEmpty(f2591a)) {
                String string = defaultSharedPreferences.getString("device_id", null);
                if (defaultSharedPreferences.getString("device_id_type", "").equals(m2045c) && string != null) {
                    f2591a = string;
                } else {
                    str = "INVALID_DEVICE_ID";
                }
            } else {
                SharedPreferences.Editor edit = defaultSharedPreferences.edit();
                edit.putString("device_id", f2591a);
                edit.putString("device_id_type", m2045c);
                edit.commit();
            }
        }
        str = f2591a;
        return str;
    }

    /* renamed from: c */
    private synchronized String m2045c() {
        if (f2593c == null) {
            f2593c = C0462r.m2017a("ro.bugreport.uid.type", "TELEPHONY_DEVICE_ID");
        }
        return f2593c;
    }

    /* renamed from: b */
    public final synchronized String m2046b(Context context) {
        if (f2592b == null) {
            f2592b = ((TelephonyManager) context.getSystemService("phone")).getDeviceId();
        }
        return f2592b;
    }

    /* renamed from: b */
    public final synchronized String m2047b() {
        return "unknown";
    }
}
