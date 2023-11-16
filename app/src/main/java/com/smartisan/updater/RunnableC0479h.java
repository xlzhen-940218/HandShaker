package com.smartisan.updater;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/* compiled from: ApkUpdater.java */
/* renamed from: com.smartisan.updater.h */
/* loaded from: classes.dex */
final class RunnableC0479h implements Runnable {

    /* renamed from: a */
    final /* synthetic */ DownloadManager downloadManager;

    /* renamed from: b */
    final /* synthetic */ DownloadManager.Query query;

    /* renamed from: c */
    final /* synthetic */ Context context;

    /* renamed from: d */
    final /* synthetic */ ApkUpdater.DownloadReceiver f2730d;
    ApkUpdater apkUpdater;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0479h(ApkUpdater apkUpdater,ApkUpdater.DownloadReceiver c0472a, DownloadManager downloadManager, DownloadManager.Query query, Context context) {
        this.f2730d = c0472a;
        this.apkUpdater = apkUpdater;
        this.downloadManager = downloadManager;
        this.query = query;
        this.context = context;
    }

    @SuppressLint("Range")
    @Override // java.lang.Runnable
    public final void run() {
        String str;
        String str2;
        String str3;
        boolean z;
        Cursor query = this.downloadManager.query(this.query);
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    apkUpdater.m1865g();
                    str = query.getString(query.getColumnIndex("local_filename"));
                } else {
                    str = null;
                }
            } finally {
                query.close();
            }
        } else {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            File file = new File(str);
            String m1838a = UpdateUtils.m1838a(file);
            str2 = apkUpdater.f2708j;
            if (!TextUtils.isEmpty(str2)) {
                str3 = apkUpdater.f2708j;
                if (str3.equals(m1838a)) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
                    this.context.startActivity(intent);
                    z = this.f2730d.f2715b;
                    if (z) {
                        apkUpdater.m1864h();
                    }
                } else {
                    Log.i("ApkUpdater", "Md5 don't match, do not install file");
                }
            } else {
                Log.e("ApkUpdater", "Md5 is empty, do not install file");
            }
        }
        apkUpdater.f2708j = null;
    }
}
