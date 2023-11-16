package com.smartisanos.smartfolder.aoa.p056h;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.R;
import java.util.Locale;

/* renamed from: com.smartisanos.smartfolder.aoa.h.aa */
/* loaded from: classes.dex */
public final class SharedPreferenceHelper {

    /* renamed from: a */
    public static Locale locale;

    /* renamed from: d */
    private static SharedPreferences getSmartFolder() {
        return FolderApp.getInstance().getSharedPreferences("com.smartisanos.smartfolder", 0);
    }

    /* renamed from: a */
    public static int getLocalLanguage() {
        return getSmartFolder().getInt("LocaleLanguage", 0);
    }

    /* renamed from: a */
    public static void setLocalLanguage(int i) {
        SharedPreferences.Editor edit = getSmartFolder().edit();
        edit.putInt("LocaleLanguage", i);
        edit.commit();
    }

    /* renamed from: b */
    public static boolean isFirstLaunch() {
        return getSmartFolder().getBoolean("isFirstLaunch", true);
    }

    /* renamed from: c */
    public static void firstLaunch() {
        SharedPreferences.Editor edit = getSmartFolder().edit();
        edit.putBoolean("isFirstLaunch", false);
        edit.commit();
    }

    /* renamed from: a */
    public static void setLocale(Context context, int localeIndex) {
        Resources resources = context.getResources();
        Configuration configuration = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        if (localeIndex == 0) {
            configuration.locale = locale;
        } else {
            String str = context.getResources().getStringArray(R.array.language_current_string)[localeIndex];
            if (str.length() == 5) {
                configuration.locale = new Locale(str.substring(0, 2), str.substring(3, 5));
            } else {
                configuration.locale = new Locale(str);
            }
        }
        resources.updateConfiguration(configuration, displayMetrics);
    }

    /* renamed from: a */
    public static boolean getBoolean(String str) {
        return getSmartFolder().getBoolean(str, false);
    }

    /* renamed from: b */
    public static void setBooleanTrue(String str) {
        SharedPreferences.Editor edit = getSmartFolder().edit();
        edit.putBoolean(str, true);
        edit.commit();
    }
}
