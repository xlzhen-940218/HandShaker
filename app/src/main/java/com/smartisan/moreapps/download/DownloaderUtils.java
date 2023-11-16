package com.smartisan.moreapps.download;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.webkit.MimeTypeMap;
import android.widget.Toast;
import com.smartisan.moreapps.AppInfoList;
import com.smartisan.p043a.C0411a;

/* renamed from: com.smartisan.moreapps.download.c */
/* loaded from: classes.dex */
public final class DownloaderUtils {
    /* renamed from: a */
    public static long m1983a(Context context, String str) {
        Uri parse = Uri.parse(str);
        DownloadManager.Request request = new DownloadManager.Request(parse);
        request.setAllowedNetworkTypes(3);
        request.setAllowedOverRoaming(false);
        request.setMimeType(MimeTypeMap.getSingleton().getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(str)));
        request.setNotificationVisibility(1);
        request.setVisibleInDownloadsUi(true);
        String str2 = null;
        if (parse != null) {
            str2 = parse.getLastPathSegment();
            if (str2 == null) {
                str2 = "update.apk";
            } else if (!str2.endsWith(".apk")) {
                str2 = str2 + ".apk";
            }
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str2);
        try {
            return ((DownloadManager) context.getSystemService("download")).enqueue(request);
        } catch (Exception e) {
            Toast.makeText(context, C0411a.C0416e.system_downloader_error, 1).show();
            return 0L;
        }
    }

    /* renamed from: a */
    public static boolean m1985a(Context context) {
        String[] strArr = new AppInfoList().f2626b;
        for (int i = 0; i < strArr.length; i++) {
            long m1986a = DownloaderPref.m1987a(context).m1986a(strArr[i] + "_ID");
            if (m1986a >= 0 && context != null && m1984a(context, m1986a) == 4) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public static int m1984a(Context context, long j) {
        int i;
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(j);
        Cursor query2 = ((DownloadManager) context.getSystemService("download")).query(query);
        if (query2 == null) {
            return -1;
        }
        try {
            if (!query2.moveToFirst()) {
                i = -1;
            } else {
                i = query2.getInt(query2.getColumnIndex("status"));
            }
            return i;
        } finally {
            query2.close();
        }
    }
}
