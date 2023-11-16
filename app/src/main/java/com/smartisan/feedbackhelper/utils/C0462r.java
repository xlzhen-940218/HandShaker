package com.smartisan.feedbackhelper.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: Util.java */
@SuppressLint({"SimpleDateFormat", "UseValueOf", "SdCardPath"})
/* renamed from: com.smartisan.feedbackhelper.utils.r */
/* loaded from: classes.dex */
public final class C0462r {

    /* renamed from: a */
    static String f2613a = "BugReportUtil";

    /* renamed from: c */
    private static Class<?> f2615c = null;

    /* renamed from: b */
    public static SimpleDateFormat f2614b = null;

    /* renamed from: d */
    private static String f2616d = null;

    /* renamed from: e */
    private static String f2617e = "Failed to get system property : %s";

    /* renamed from: a */
    public static String m2021a(Context context) {
        try {
            if (f2616d == null) {
                f2616d = context.getPackageManager().getPackageInfo(context.getPackageName(), 128).versionName;
            }
            return f2616d;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(f2613a, context.getPackageName() + " not found", e);
            return null;
        }
    }

    /* renamed from: a */
    public static String m2017a(String str, String str2) {
        try {
            if (f2615c == null) {
                f2615c = Class.forName("android.os.SystemProperties");
            }
            String str3 = (String) f2615c.getMethod("get", String.class).invoke(f2615c, str);
            return TextUtils.isEmpty(str3) ? str2 : str3;
        } catch (ClassNotFoundException e) {
            Log.e(f2613a, String.format(f2617e, str), e);
            return str2;
        } catch (IllegalAccessException e2) {
            Log.e(f2613a, String.format(f2617e, str), e2);
            return str2;
        } catch (IllegalArgumentException e3) {
            Log.e(f2613a, String.format(f2617e, str), e3);
            return str2;
        } catch (NoSuchMethodException e4) {
            Log.e(f2613a, String.format(f2617e, str), e4);
            return str2;
        } catch (InvocationTargetException e5) {
            Log.e(f2613a, String.format(f2617e, str), e5);
            return str2;
        }
    }

    /* renamed from: a */
    public static String m2016a(String str, Date date) {
        if (f2614b == null) {
            f2614b = new SimpleDateFormat(str);
        } else {
            f2614b.applyPattern(str);
        }
        try {
            return f2614b.format(date);
        } catch (StringIndexOutOfBoundsException e) {
            return f2614b.format(new Date(System.currentTimeMillis()));
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: b */
    private static boolean m2014b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        return file.isDirectory() && file.exists() && file.canWrite() && file.getUsableSpace() >= 52428800;
    }

    /* renamed from: a */
    public static String m2022a() {
        Log.d(f2613a, "try to get Log path for report");
        if (m2014b(Constants.f2569e)) {
            Log.d(f2613a, "get external storage for log path!");
            return Constants.f2570f;
        } else if (m2014b(Constants.f2567c)) {
            Log.d(f2613a, "get storage for log path!");
            return Constants.f2568d;
        } else {
            return null;
        }
    }

    /* renamed from: a */
    public static void m2015a(String[] strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                m2018a(str);
            }
        }
    }

    /* renamed from: a */
    public static boolean m2018a(String str) {
        Log.d(f2613a, String.format("removeFile() : %s", str));
        if (str == null) {
            return true;
        }
        File file = new File(str);
        if (!file.exists()) {
            Log.w(f2613a, String.format("File Not Exist : %s", str));
            return true;
        }
        if (file.isDirectory()) {
            if (!str.endsWith(File.separator)) {
                str = str + File.separator;
            }
            String[] list = file.list();
            for (int i = 0; list != null && i < list.length; i++) {
                if (!m2018a(str + list[i])) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /* renamed from: a */
    private static void m2019a(InputStream inputStream, String str) {
        Log.d(f2613a, String.format("saveDataToFile %s", str));
        if (str == null) {
            Log.d(f2613a, "Invalid data or file path");
            return;
        }
        Exception e;
        Throwable th;
        FileOutputStream fileOutputStream = null;
        try {
            try {
                File file = new File(str);
                File parentFile = file.getParentFile();
                if (parentFile != null && !parentFile.exists()) {
                    parentFile.mkdirs();
                }
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[1024];
                    int read = inputStream.read(bArr);
                    while (read >= 0) {
                        fileOutputStream2.write(bArr, 0, read);
                        read = inputStream.read(bArr);
                    }
                    fileOutputStream2.flush();
                    try {
                        fileOutputStream2.close();
                    } catch (IOException e1) {
                        Log.e(f2613a, "Error closing Output Stream", e1);
                    }
                } catch (FileNotFoundException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                            Log.e(f2613a, "Error closing Output Stream", e3);
                        }
                    }
                } catch (IOException e4) {
                    e = e4;
                    fileOutputStream = fileOutputStream2;
                    e.printStackTrace();
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e5) {
                            Log.e(f2613a, "Error closing Output Stream", e5);
                        }
                    }
                } catch (Throwable th1) {
                    th = th1;
                    fileOutputStream = fileOutputStream2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e6) {
                            Log.e(f2613a, "Error closing Output Stream", e6);
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e7) {
                e = e7;
            } catch (IOException e8) {
                e = e8;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0077 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m2013b(String str, String str2) throws Throwable {
        FileInputStream fileInputStream = null;
        Throwable th;
        Exception e;
        boolean z;
        if (str == null || str2 == null) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        File file2 = new File(str2);
        boolean exists = file2.exists();
        Object str3 = exists;
        if (exists) {
            boolean isDirectory = file2.isDirectory();
            str3 = isDirectory;
            if (isDirectory) {
                String str4 = str2 + File.separator + file.getName();
                file2 = new File(str4);
                str3 = str4;
            }
        }
        try {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e1) {
                e = e1;
                fileInputStream = null;
            } catch (Throwable th1) {
                th = th1;
                str3 = 0;

                throw th;
            }
            m2019a(fileInputStream, file2.getAbsolutePath());
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            z = true;
            str3 = fileInputStream;
            return z;
        } catch (Throwable th2) {
            th = th2;
            if ((int)str3 != 0) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            throw th;
        }
    }

    /* renamed from: c */
    public static boolean m2012c(String str, String str2) throws Throwable {
        boolean m2013b = m2013b(str, str2);
        String str3 = str2 + File.separator + new File(str).getName();
        if (new File(str3).length() > 100000) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str3, options);
            options.inSampleSize = m2020a(options);
            options.inJustDecodeBounds = false;
            Bitmap decodeFile = BitmapFactory.decodeFile(str3, options);
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(str3)));
                decodeFile.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return m2013b;
    }

    /* renamed from: a */
    public static int m2020a(BitmapFactory.Options options) {
        int ceil = (int) Math.ceil(Math.sqrt((options.outWidth * options.outHeight) / 16384.0d));
        if (ceil <= 15) {
            return 3;
        }
        return (ceil + 7) / 3;
    }
}
