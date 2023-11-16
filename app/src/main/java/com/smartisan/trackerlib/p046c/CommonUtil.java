package com.smartisan.trackerlib.p046c;

import android.content.ContentValues;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.smartisan.trackerlib.Agent;
import com.smartisan.trackerlib.TransportEntity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.json.JSONObject;

/* renamed from: com.smartisan.trackerlib.c.a */
/* loaded from: classes.dex */
public final class CommonUtil {

    /* renamed from: a */
    private static String f2688a;

    /* renamed from: b */
    private static String f2689b = "";

    /* JADX WARN: Removed duplicated region for block: B:12:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0032  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static int m1919a(Context context) {
        Exception exc;
        NetworkInfo.State state;
        NetworkInfo.State state2;
        NetworkInfo.State state3;
        int i = 0;
        NetworkInfo.State state4 = null;
        if (context == null) {
            return 0;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            state4 = connectivityManager.getNetworkInfo(0).getState();
        } catch (Exception e) {
            exc = e;
            state = null;
        }
        try {
            state3 = connectivityManager.getNetworkInfo(1).getState();
            state2 = state4;
        } catch (Exception e2) {
            state = state4;
            exc = e2;
            exc.printStackTrace();
            state2 = state;
            state3 = null;
            if (state3 != NetworkInfo.State.CONNECTED) {
            }
            return i;
        }
        if (state3 != NetworkInfo.State.CONNECTED) {
            i = 2;
        } else {
            i = state2 == NetworkInfo.State.CONNECTED ? 1 : 0;
        }
        return i;
    }

    /* renamed from: b */
    public static String m1916b(Context context) {
        if (f2688a == null) {
            try {
                Class<?> cls = Class.forName("android.telephony.TelephonyManager");
                String str = (String) cls.getMethod("getImei", new Class[0]).invoke(cls.getMethod("getDefault", new Class[0]).invoke(cls, new Object[0]), new Object[0]);
                f2688a = str;
                if (str == null) {
                    f2688a = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                }
                LogUtils.m1902a("get imei through getImei invoke : " + f2688a);
            } catch (Exception e) {
                f2688a = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                LogUtils.m1902a("get imei through getDeviceId : " + f2688a);
            }
        }
        return f2688a;
    }

    /* renamed from: a */
    public static String m1920a() {
        return Build.MODEL;
    }

    /* renamed from: c */
    public static String m1914c(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        while (string.length() < 16) {
            string = "0" + string;
        }
        return string;
    }

    /* renamed from: d */
    public static String m1912d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            LogUtils.m1902a("get null version name  with: " + e.getMessage());
            return null;
        }
    }

    /* renamed from: b */
    public static String m1917b() throws Exception {
        String m1908f = m1908f(Agent.m1952a().m1947b());
        if (!TextUtils.isEmpty(m1908f)) {
            return "A" + m1908f + "0000";
        }
        return m1908f;
    }

    /* renamed from: c */
    public static String m1915c() throws Exception {
        String m1908f = m1908f(Agent.m1952a().m1947b());
        if (!TextUtils.isEmpty(m1908f)) {
            return "A" + m1908f + "0001";
        }
        return m1908f;
    }

    /* renamed from: f */
    private static String m1908f(Context context) throws Exception {
        try {
            int i = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("AppId");
            return i < 10 ? "0" + i : String.valueOf(i);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("AppID is NULL");
        }
    }

    /* renamed from: d */
    public static String m1913d() {
        String str;
        DisplayMetrics displayMetrics = Agent.m1952a().m1947b().getResources().getDisplayMetrics();
        String str2 = displayMetrics.widthPixels + "*" + displayMetrics.heightPixels;
        int m1919a = m1919a(Agent.m1952a().m1947b());
        if (m1919a == 2) {
            str = "wifi";
        } else if (m1919a != 1) {
            str = "none";
        } else {
            str = ((TelephonyManager) Agent.m1952a().m1947b().getSystemService(Context.TELEPHONY_SERVICE)).getSimOperator();
            if (str == null) {
                str = "";
            }
        }
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        try {
            SharePreferenceManager.m1899a();
            jSONObject.put("last_uptime", SharePreferenceManager.m1896a("key_launch_time", "com.smartisan.LibTracker", Agent.m1952a().m1947b()));
            jSONObject.put("resolution", str2);
            jSONObject.put("network", str);
            SharePreferenceManager.m1899a();
            SharePreferenceManager.m1898a("key_launch_time", (int) (System.currentTimeMillis() / 1000), "com.smartisan.LibTracker", Agent.m1952a().m1947b());
            return jSONObject.toString();
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: e */
    public static String m1910e(Context context) throws Exception {
        try {
            String sb = new StringBuilder().append(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getInt("AppChannel")).toString();
            while (sb.length() < 5) {
                sb = "0" + sb;
            }
            return sb;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("channel id is null");
        }
    }

    /* renamed from: e */
    public static String m1911e() {
        if (!TextUtils.isEmpty(f2689b)) {
            return f2689b;
        }
        SharePreferenceManager.m1899a();
        String m1895a = SharePreferenceManager.m1895a("track_device_id", "com.smartisan.LibTracker", Agent.m1952a().m1947b(), "");
        f2689b = m1895a;
        if (TextUtils.isEmpty(m1895a)) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("imei", m1916b(Agent.m1952a().m1947b()));
                jSONObject.put("android_id", m1914c(Agent.m1952a().m1947b()));
                f2689b = jSONObject.toString();
                SharePreferenceManager.m1899a();
                SharePreferenceManager.m1894a("track_device_id", f2689b, "com.smartisan.LibTracker", Agent.m1952a().m1947b());
            } catch (Exception e) {
            }
        }
        return f2689b;
    }

    /* renamed from: f */
    public static String m1909f() {
        try {
            return "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10) + (Build.CPU_ABI.length() % 10) + (Build.DEVICE.length() % 10) + (Build.DISPLAY.length() % 10) + (Build.HOST.length() % 10) + (Build.ID.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10) + (Build.TAGS.length() % 10) + (Build.TYPE.length() % 10) + (Build.USER.length() % 10);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: a */
    public static ContentValues m1918a(TransportEntity transportEntity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("eventid", transportEntity.m1959b());
        contentValues.put("time_stamp", Long.valueOf(transportEntity.m1955d()));
        contentValues.put("eventdata", transportEntity.m1957c());
        contentValues.put("wifionly", Integer.valueOf(transportEntity.m1954e() ? 1 : 0));
        contentValues.put("upload_time", Integer.valueOf(transportEntity.m1953f()));
        return contentValues;
    }

    /* renamed from: g */
    public static String m1907g() {
        return Build.VERSION.RELEASE;
    }

    /* renamed from: h */
    public static long m1906h() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -7);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        LogUtils.m1902a("date is : " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()) + " time stamp is : " + calendar.getTimeInMillis());
        return calendar.getTimeInMillis();
    }

    /* renamed from: i */
    public static String m1905i() {
        return ((WifiManager) Agent.m1952a().m1947b().getApplicationContext().getSystemService(Context.WIFI_SERVICE)).getConnectionInfo().getMacAddress();
    }
}
