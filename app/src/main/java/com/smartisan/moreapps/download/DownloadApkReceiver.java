package com.smartisan.moreapps.download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;
import com.smartisan.moreapps.AppsView;
import com.smartisan.moreapps.SmartisanAppPref;
import com.smartisan.moreapps.SmartisanAppUtils;
import com.smartisanos.smartfolder.aoa.R;

import java.io.File;

/* loaded from: classes.dex */
public class DownloadApkReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int m1966a = SmartisanAppUtils.m1966a(context);
        if (TextUtils.equals(action, "android.net.conn.CONNECTIVITY_CHANGE")) {
            if (m1966a != 1) {
                if (m1966a == 0 && DownloaderUtils.m1985a(context)) {
                    Toast.makeText(context, context.getString(R.string.network_error_message), Toast.LENGTH_SHORT).show();
                }
            } else {
                SharedPreferences sharedPreferences = context.getSharedPreferences("download_pending", 0);
                String[][] strArr = {sharedPreferences.getString("package_names", "").split("##"), sharedPreferences.getString("location_names", "").split("##")};
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("package_names", "");
                edit.putString("location_names", "");
                edit.commit();
                for (int i = 0; i < strArr[0].length; i++) {
                    String str = strArr[0][i];
                    String str2 = strArr[1][i];
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        new AppDownloader(context).m1988a(str, str2);
                    }
                }
                if (SmartisanAppPref.m1972a(context).m1967c()) {
                    new AppsView.AsyncTaskC0463a(context).execute(new Void[0]);
                }
            }
        }
        if (TextUtils.equals(action, "android.intent.action.DOWNLOAD_COMPLETE")) {
            if (!DownloaderUtils.m1985a(context)) {
                context.getSharedPreferences("download_pending", 0).edit().clear().commit();
            }
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterByStatus(8);
            Cursor query2 = ((DownloadManager) context.getSystemService("download")).query(query);
            if (query2 != null) {
                try {
                    if (query2.moveToFirst()) {
                        String string = query2.getString(query2.getColumnIndex("local_filename"));
                        Intent intent2 = new Intent("android.intent.action.VIEW");
                        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent2.setDataAndType(Uri.fromFile(new File(string)), "application/vnd.android.package-archive");
                        context.startActivity(intent2);
                    }
                } finally {
                    query2.close();
                }
            }
        }
    }
}
