package com.smartisan.updater;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.smartisan.updater.C0481l;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* renamed from: com.smartisan.updater.p */
/* loaded from: classes.dex */
public final class UpdateUtils {
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public static boolean m1843a(Context context) {
        boolean z = false;
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                switch (activeNetworkInfo.getType()) {
                    case 0:
                        switch (activeNetworkInfo.getSubtype()) {
                            case 1:
                            case 2:
                            case 4:
                            case 7:
                                return false;
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                                z = true;
                                break;
                        }
                    case 1:
                        return true;
                }
                return z;
            }
            z = false;
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: b */
    public static boolean m1837b(Context context) {
        boolean z = false;
        NetworkInfo activeNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null && (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) != null && activeNetworkInfo.isAvailable() && activeNetworkInfo.getState() == NetworkInfo.State.CONNECTED) {
                switch (activeNetworkInfo.getType()) {
                    case 0:
                        switch (activeNetworkInfo.getSubtype()) {
                            case 1:
                            case 2:
                            case 4:
                            case 7:
                                return false;
                            case 3:
                            case 5:
                            case 6:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                                z = true;
                                break;
                        }
                    case 1:
                        return false;
                }
                return z;
            }
            z = false;
            return z;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public static long m1840a(Context context, String str) {
        try {
            Uri parse = Uri.parse(str);
            DownloadManager.Request request = new DownloadManager.Request(parse);
            request.setAllowedNetworkTypes(3);
            request.setAllowedOverRoaming(false);
            request.setMimeType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str)));
            request.setNotificationVisibility(1);
            request.setVisibleInDownloadsUi(true);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, m1839a(parse));
            request.allowScanningByMediaScanner();
            return ((DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE)).enqueue(request);
        } catch (Exception e) {
            e.printStackTrace();
            m1842a(context, C0481l.C0482a.system_downloader_err);
            return 0L;
        }
    }

    /* renamed from: a */
    private static void m1842a(Context context, int i) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            new Handler(Looper.getMainLooper()).post(new RunnableC0485q(context, i));
        } else {
            Toast.makeText(context.getApplicationContext(), i, Toast.LENGTH_SHORT).show();
        }
    }

    /* renamed from: a */
    public static String m1839a(Uri uri) {
        if (uri == null) {
            return null;
        }
        String lastPathSegment = uri.getLastPathSegment();
        if (lastPathSegment == null) {
            return "update.apk";
        }
        if (!lastPathSegment.endsWith(".apk")) {
            return lastPathSegment + ".apk";
        }
        return lastPathSegment;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m1841a(Context context, long j) {
        boolean z;
        if (j >= 0) {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(j);
            Cursor cursor = null;
            try {
                try {
                    cursor = downloadManager.query(query);
                    if (cursor != null && cursor.moveToFirst()) {
                        @SuppressLint("Range") int i = cursor.getInt(cursor.getColumnIndex("status"));
                        if (i == 1 || i == 2) {
                            z = true;
                            if (cursor == null) {
                                cursor.close();
                                return z;
                            }
                            return z;
                        }
                    }
                    z = false;
                    if (cursor == null) {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    m1842a(context, C0481l.C0482a.system_downloader_err);
                    if (cursor != null) {
                        cursor.close();
                        return false;
                    }
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return false;
    }

    /* renamed from: c */
    public static int m1835c(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Utils", "getVersionCode", e);
            return 1;
        }
    }

    /* renamed from: d */
    public static final String m1834d(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData.containsKey("AppId")) {
                int i = applicationInfo.metaData.getInt("AppId");
                return i < 10 ? "0" + i : String.valueOf(i);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("cann't get AppId, plz check your manifest");
    }

    /* renamed from: f */
    private static int m1832f(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (!applicationInfo.metaData.containsKey("AppChannel")) {
                throw new RuntimeException("channel key AppChannel does not match, plz check your manifest ");
            }
            return applicationInfo.metaData.getInt("AppChannel");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: e */
    public static boolean m1833e(Context context) {
        return 101 == m1832f(context);
    }

    /* renamed from: b */
    public static void m1836b(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage("com.android.vending");
            launchIntentForPackage.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"));
            launchIntentForPackage.setData(Uri.parse("market://details?id=" + str));
            context.startActivity(launchIntentForPackage);
        } catch (ActivityNotFoundException e) {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + str)));
        }
    }

    /* renamed from: a */
    public static String m1838a(File file) {
        try {
            if (!file.isFile()) {
                return null;
            }
            byte[] bArr = new byte[1024];
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    messageDigest.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return new BigInteger(1, messageDigest.digest()).toString(16);
                }
            }
        } catch (IOException e) {
            Log.e("Utils", "getFileMD5", e);
            return "";
        } catch (NoSuchAlgorithmException e2) {
            Log.e("Utils", "getFileMD5", e2);
            return "";
        }
    }
}
