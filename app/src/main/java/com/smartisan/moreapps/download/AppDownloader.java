package com.smartisan.moreapps.download;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.smartisan.moreapps.HttpData;
import com.smartisan.moreapps.SmartisanAppPref;
import com.smartisan.moreapps.VersionInfo;
import com.smartisan.p043a.C0411a;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

/* renamed from: com.smartisan.moreapps.download.a */
/* loaded from: classes.dex */
public final class AppDownloader extends AsyncTask<Void, Void, VersionInfo> {

    /* renamed from: a */
    private Context f2641a;

    /* renamed from: b */
    private String f2642b;

    /* renamed from: c */
    private String f2643c;

    @Override // android.os.AsyncTask
    protected final /* synthetic */ VersionInfo doInBackground(Void[] voidArr) {
        return m1990a();
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(VersionInfo versionInfo) {
        VersionInfo versionInfo2 = versionInfo;
        super.onPostExecute(versionInfo2);
        if (versionInfo2 == null || this.f2641a == null) {
            return;
        }
        SmartisanAppPref.m1972a(this.f2641a).m1970a(this.f2643c, DownloaderUtils.m1983a(this.f2641a, versionInfo2.m1962b()));
    }

    public AppDownloader(Context context) {
        this.f2641a = context;
    }

    /* renamed from: a */
    public final boolean m1988a(String str, String str2) {
        int m1984a;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        this.f2643c = str + "_ID";
        this.f2642b = str2;
        long m1971a = SmartisanAppPref.m1972a(this.f2641a).m1971a(this.f2643c);
        Context context = this.f2641a;
        if (m1971a >= 0 && context != null && ((m1984a = DownloaderUtils.m1984a(context, m1971a)) == 1 || m1984a == 2)) {
            return false;
        }
        execute(new Void[0]);
        Toast.makeText(this.f2641a, this.f2641a.getString(C0411a.C0416e.downloading_message), Toast.LENGTH_SHORT).show();
        return true;
    }

    @Override // android.os.AsyncTask
    protected final void onPreExecute() {
        super.onPreExecute();
    }

    /* renamed from: a */
    private VersionInfo m1990a() {
        VersionInfo versionInfo;
        new HttpData();
        try {
            String m1992a = HttpData.m1992a(this.f2642b);
            if (m1992a != null) {
                versionInfo = VersionInfo.m1963a(new JSONObject(m1992a));
                if (versionInfo == null || !m1989a(versionInfo)) {
                    Log.e("AppDownloader", "Invalid version info:" + (versionInfo == null ? "" : versionInfo.toString()));
                    return null;
                }
            } else {
                versionInfo = null;
            }
            return versionInfo;
        } catch (Exception e) {
            Log.e("AppDownloader", "update error ", e);
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m1989a(VersionInfo versionInfo) {
        if (versionInfo == null) {
            return false;
        }
        String m1962b = versionInfo.m1962b();
        if (TextUtils.isEmpty(m1962b)) {
            return false;
        }
        try {
            String authority = new URL(m1962b).getAuthority();
            if (!TextUtils.isEmpty(authority) && (authority.equals("dl.smartisan.cn") || authority.equals("dl2.smartisan.cn") || authority.equals("update.smartisanos.com"))) {
                return true;
            }
            Log.e("AppDownloader", "Invalid download url:" + m1962b);
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
