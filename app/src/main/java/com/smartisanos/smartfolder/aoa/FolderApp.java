package com.smartisanos.smartfolder.aoa;

import android.app.Application;
import android.provider.Settings;
import android.text.TextUtils;
import com.smartisan.trackerlib.Agent;
import com.smartisanos.smartfolder.aoa.p056h.ActivityLifecycleManager;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.aoa.p056h.SharedPreferenceHelper;

/* loaded from: classes.dex */
public class FolderApp extends Application {

    /* renamed from: a */
    public static boolean f3378a = true;

    /* renamed from: c */
    public static boolean f3379c = CommonUtils.m485q();

    /* renamed from: d */
    public static String serialNumber = null;

    /* renamed from: e */
    public static String f3381e = null;

    /* renamed from: f */
    private static FolderApp instance;

    /* renamed from: b */
    public boolean isSmartISanVersionLow = false;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();

        Agent.m1952a().m1951a(this);
        String serialNumber = Settings.Secure.getString(getContentResolver(), "android_id");
        if (TextUtils.isEmpty(serialNumber)) {
            serialNumber = CommonUtils.getProp("ro.serialno");
        }
        FolderApp.serialNumber = serialNumber;
        instance = this;
        String version = getSmartISanVersion();
        if (!TextUtils.isEmpty(version)) {
            this.isSmartISanVersionLow = version.startsWith("1.5");
        }
        registerActivityLifecycleCallbacks(ActivityLifecycleManager.getInstance());
        SharedPreferenceHelper.locale = getResources().getConfiguration().locale;
        SharedPreferenceHelper.setLocale(this, SharedPreferenceHelper.getLocalLanguage());
    }

    /* renamed from: b */
    private static String getSmartISanVersion() {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class).invoke(cls, "ro.smartisan.version");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static FolderApp getInstance() {
        return instance;
    }
}
