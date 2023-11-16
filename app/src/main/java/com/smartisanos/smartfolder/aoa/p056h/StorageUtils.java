package com.smartisanos.smartfolder.aoa.p056h;

import android.content.Context;

import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: com.smartisanos.smartfolder.aoa.h.ac */
/* loaded from: classes.dex */
public final class StorageUtils {

    /* compiled from: StorageUtils.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.ac$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0759a {
        /* renamed from: a */
        void mo423a(long j, long j2);
    }

    /* renamed from: a */
    private static void m535a(Context context, List<ApplicationInfo> list, IPackageStatsObserver iPackageStatsObserver) {
        if (list == null || list.size() == 0) {
            HandShaker.error("Parameters are wrong in getPkgSize");
            return;
        }
        Method method = null;
        try {
            method = PackageManager.class.getMethod("getPackageSizeInfo", String.class, IPackageStatsObserver.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (method != null) {
            PackageManager packageManager = context.getPackageManager();
            method.setAccessible(true);
            Iterator<ApplicationInfo> it = list.iterator();
            while (it.hasNext()) {
                try {
                    method.invoke(packageManager, it.next().packageName, iPackageStatsObserver);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* renamed from: a */
    public static void m536a(Context context, InterfaceC0759a interfaceC0759a) {
        List<ApplicationInfo> installedApplications = context.getPackageManager().getInstalledApplications(8704);
        int size = installedApplications.size();
        m535a(context, installedApplications, new BinderC0760ad(new AtomicLong(0L), new AtomicLong(0L), new AtomicInteger(0), size, interfaceC0759a));
    }

    /* renamed from: a */
    public static long getAudioSize(Context context) {
        Cursor cursor;
        Exception e;
        long j = 0;
        String string;
        long j2 = 0;
        try {
            cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_size"}, null, null, null);
            try {
                try {
                    String m483s = CommonUtils.getStoragePath();
                    if (cursor == null || !cursor.moveToFirst()) {
                        j = 0;
                    } else {
                        while (true) {
                            j = (TextUtils.isEmpty(m483s) || (string = cursor.getString(0)) == null || !string.startsWith(m483s)) ? cursor.getLong(1) + j2 : j2;
                            try {
                                if (!cursor.moveToNext()) {
                                    break;
                                }
                                j2 = j;
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                CommonUtils.m509a(cursor);
                                return j;
                            }
                        }
                    }
                    CommonUtils.m509a(cursor);
                } catch (Throwable th) {
                    th = th;
                    CommonUtils.m509a(cursor);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                j = j2;
            }
        } catch (Exception e4) {
            e = e4;
            cursor = null;
            j = 0;
        } catch (Throwable th2) {
            //th = th2;
            cursor = null;
            CommonUtils.m509a(cursor);
            //throw th;
        }
        return j;
    }

    /* renamed from: a */
    public static long m533a(String... strArr) {
        long j = 0;
        for (String str : strArr) {
            j += m534a(new File(Environment.getExternalStorageDirectory(), str));
        }
        return j;
    }

    /* renamed from: a */
    private static long m534a(File file) {
        File[] listFiles;
        long length;
        long j = 0;
        if (file != null) {
            try {
                if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                    for (int i = 0; i < listFiles.length; i++) {
                        if (listFiles[i].isDirectory()) {
                            length = m534a(listFiles[i]);
                        } else {
                            length = listFiles[i].length();
                        }
                        j += length;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return j;
    }
}
