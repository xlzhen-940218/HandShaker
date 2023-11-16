package com.smartisan.updater;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.smartisan.trackerlib.Agent;
import com.smartisan.updater.p047a.CollectionInterrupt;
import com.smartisan.updater.p047a.InterruptUtil;
import com.smartisanos.smartfolder.aoa.R;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

/* renamed from: com.smartisan.updater.a */
/* loaded from: classes.dex */
public final class ApkUpdater extends AsyncTask<Void, Void, Version> implements DialogInterface.OnDismissListener {

    /* renamed from: a */
    public Context f2699a;

    /* renamed from: b */
    private String f2700b;

    /* renamed from: c */
    private String f2701c;

    /* renamed from: d */
    private DownloadReceiver downloadReceiver;

    /* renamed from: e */
    public boolean f2703e;

    /* renamed from: g */
    private String f2705g;

    /* renamed from: h */
    private long f2706h;

    /* renamed from: j */
    public String f2708j;

    /* renamed from: k */
    private boolean f2709k;

    /* renamed from: l */
    private String f2710l;

    /* renamed from: m */
    private boolean f2711m;

    /* renamed from: n */
    private CollectionInterrupt f2712n;

    /* renamed from: o */
    private CollectionInterrupt f2713o;

    /* renamed from: f */
    private UpdateUI f2704f = null;

    /* renamed from: i */
    private boolean f2707i = false;

    @Override // android.os.AsyncTask
    protected final /* synthetic */ Version doInBackground(Void[] voidArr) {
        try {
            return m1869e();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(Version version) {
        AlertDialog.Builder builder;
        String string;
        Version version2 = version;
        super.onPostExecute(version2);
        if (this.f2703e && this.f2704f != null) {
            this.f2704f.mo768b();
        }
        if (this.f2699a != null) {
            if (version2 != null) {
                if (version2.m1888e()) {
                    if (version2.m1887f()) {
                        if (!m1863i()) {
                            boolean m1886g = version2.m1886g();
                            AlertDialog.Builder builder2 = new AlertDialog.Builder(this.f2699a, 5);
                            builder2.setTitle(C0481l.C0482a.check_update);
                            builder2.setMessage(this.f2699a.getString(C0481l.C0482a.ota_upate_hint, this.f2705g));
                            builder2.setNegativeButton(C0481l.C0482a.update_cancel, new DialogInterface$OnClickListenerC0477f(this, m1886g));
                            builder2.setCancelable(false);
                            AlertDialog create = builder2.create();
                            create.setOnDismissListener(this);
                            if (!(this.f2699a instanceof Activity)) {
                                create.getWindow().setType(Build.VERSION.SDK_INT >= 19 ? 2005 : 2002);
                            }
                            create.show();
                            return;
                        }
                        return;
                    } else if (version2.m1886g()) {
                        if (m1863i()) {
                            return;
                        }
                        if (UpdateUtils.m1837b(this.f2699a)) {
                            Context context = this.f2699a;
                            int i = C0481l.C0482a.force_update__var_mobile;
                            Object[] objArr = new Object[3];
                            objArr[0] = this.f2705g;
                            objArr[1] = version2.m1893a();
                            objArr[2] = Float.valueOf(version2.m1884i() <= 0 ? m1882a(this.f2706h) : m1882a(version2.m1884i()));
                            string = context.getString(i, objArr);
                        } else {
                            Context context2 = this.f2699a;
                            int i2 = C0481l.C0482a.force_update__var_wifi;
                            Object[] objArr2 = new Object[3];
                            objArr2[0] = this.f2705g;
                            objArr2[1] = version2.m1893a();
                            objArr2[2] = Float.valueOf(version2.m1884i() <= 0 ? m1882a(this.f2706h) : m1882a(version2.m1884i()));
                            string = context2.getString(i2, objArr2);
                        }
                        int i3 = C0481l.C0482a.update_download;
                        if (m1876a(version2.m1890c(), version2.m1889d())) {
                            i3 = C0481l.C0482a.update_install;
                        }
                        AlertDialog.Builder builder3 = new AlertDialog.Builder(this.f2699a, 5);
                        builder3.setTitle(C0481l.C0482a.check_update);
                        builder3.setMessage(string);
                        builder3.setPositiveButton(i3, new DialogInterface$OnClickListenerC0473b(this, version2));
                        builder3.setNegativeButton(C0481l.C0482a.update_cancel, new DialogInterface$OnClickListenerC0474c(this));
                        builder3.setCancelable(false);
                        AlertDialog create2 = builder3.create();
                        create2.setOnDismissListener(this);
                        if (!(this.f2699a instanceof Activity)) {
                            create2.getWindow().setType(Build.VERSION.SDK_INT >= 19 ? 2005 : 2002);
                        }
                        create2.show();
                        return;
                    } else if (!m1863i()) {
                        Context context3 = this.f2699a;
                        if (version2.m1891b() > UpdateSharedPreference.m1849a(this.f2699a).m1844c()) {
                            UpdateSharedPreference.m1849a(this.f2699a).m1847a(true);
                            UpdateSharedPreference.m1849a(this.f2699a).m1851a(version2.m1891b());
                        }
                        if (!this.f2711m && !this.f2703e && !UpdateSharedPreference.m1849a(this.f2699a).m1852a()) {
                            return;
                        }
                        if (!this.f2703e) {
                            builder = new AlertDialog.Builder(context3, 5);
                        } else {
                            builder = new AlertDialog.Builder(context3);
                        }
                        builder.setMessage(String.format(this.f2701c, version2.m1893a()));
                        builder.setTitle(C0481l.C0482a.check_update);
                        int i4 = C0481l.C0482a.update_download;
                        if (m1876a(version2.m1890c(), version2.m1889d())) {
                            i4 = C0481l.C0482a.update_install;
                        }
                        builder.setPositiveButton(i4, new DialogInterface$OnClickListenerC0475d(this, version2));
                        builder.setNegativeButton(C0481l.C0482a.update_cancel, new DialogInterface$OnClickListenerC0476e(this));
                        builder.setCancelable(true);
                        AlertDialog create3 = builder.create();
                        create3.setCanceledOnTouchOutside(false);
                        create3.setOnDismissListener(this);
                        if (!(this.f2699a instanceof Activity)) {
                            create3.getWindow().setType(Build.VERSION.SDK_INT >= 19 ? 2005 : 2002);
                        }
                        create3.show();
                        return;
                    } else {
                        return;
                    }
                }
                m1871d();
                if (this.f2703e) {
                    Toast.makeText(this.f2699a, this.f2699a.getString(C0481l.C0482a.no_updated_version), Toast.LENGTH_SHORT).show();
                    return;
                }
                return;
            }
            m1871d();
            if (!this.f2703e || UpdateUtils.m1841a(this.f2699a, UpdateSharedPreference.m1849a(this.f2699a).m1846b())) {
                return;
            }
            Toast.makeText(this.f2699a, this.f2699a.getString(C0481l.C0482a.check_update_fail), Toast.LENGTH_SHORT).show();
        }
    }

    public ApkUpdater(Context context, String str, boolean z, String str2) {
        this.f2699a = context;
        this.f2703e = z;
        this.f2700b = str;
        this.f2710l = "A" + UpdateUtils.m1834d(context);
        Agent.m1952a().m1951a((Application) context.getApplicationContext());
        this.f2705g = str2;
        this.f2706h = 11500000L;
        this.f2711m = true;
        this.f2712n = new CollectionInterrupt(context);
        this.f2713o = new CollectionInterrupt(context);
    }

    /* renamed from: a */
    public final void m1877a(UpdateUI updateUI) {
        this.f2704f = updateUI;
    }

    /* renamed from: d */
    private void m1871d() {
        if (this.f2709k && !m1863i()) {
            try {
                new OsUpdateNotifer().m1853a(this.f2699a);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override // android.os.AsyncTask
    protected final void onPreExecute() {
        super.onPreExecute();
        if (this.f2703e && this.f2704f != null) {
            this.f2704f.mo769a();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* renamed from: e */
    private Version m1869e() throws Throwable {
        InputStream inputStream = null;
        int r2 = 1;
        r2 = 1;
        int r22 = 1;
        UpdateSharedPreference m1849a = UpdateSharedPreference.m1849a(this.f2699a);
        if (UpdateUtils.m1841a(this.f2699a, m1849a.m1846b())) {
            return null;
        }
        new HttpGetData();
        try {
            try {
                String str = this.f2700b;
                Bundle bundle = new Bundle();
                bundle.putString("uri", str);
                bundle.putInt("need_update", 1);
                Bundle m1862a = this.f2712n.m1862a(bundle);
                if (m1862a != null) {
                    if ((m1862a == null ? -1 : m1862a.getInt("need_update")) != 1) {
                        r22 = 0;
                    }
                }
                if (r22 == 0) {
                    Version version = new Version();
                    version.m1885h();
                    HttpGetData.close((InputStream) null);
                    return version;
                }
                inputStream = HttpGetData.m1856a(m1862a == null ? "" : m1862a == null ? null : m1862a.getString("uri"));
                if (inputStream == null) {
                    HttpGetData.close(inputStream);
                    return null;
                }
                try {
                    Version m1892a = Version.m1892a(this.f2699a, new JSONObject(HttpGetData.streamToString(inputStream, HTTP.UTF_8)));
                    CollectionInterrupt collectionInterrupt = this.f2713o;
                    if (m1862a == null) {
                        m1862a = new Bundle();
                    }
                    m1862a.putParcelable(m1892a.getClass().getName(), m1892a);
                    Bundle m1862a2 = collectionInterrupt.m1862a(m1862a);
                    Version version2 = m1862a2 == null ? null : (Version) InterruptUtil.m1859a(m1862a2, Version.class);
                    if (version2 == null || !version2.m1888e() || m1880a(version2)) {
                        m1849a.m1850a(System.currentTimeMillis());
                        HttpGetData.close(inputStream);
                        return version2;
                    }
                    if (version2 != null) {
                        Log.e("ApkUpdater", "Invalid version info:" + version2.toString());
                    }
                    HttpGetData.close(inputStream);
                    return null;
                } catch (Exception e) {
                    e = e;
                    Log.e("ApkUpdater", "update error", e);
                    m1849a.m1850a(System.currentTimeMillis());
                    HttpGetData.close(inputStream);
                    return null;
                }
            } catch (Exception e2) {
                
                inputStream = null;
            } catch (Throwable th) {
                th = th;
                r2 = 0;
                HttpGetData.close(inputStream);
                throw th;
            }
        } catch (Throwable th2) {
            HttpGetData.close(inputStream);
            throw th2;
        }
        return null;
    }

    /* renamed from: a */
    public final boolean m1883a() {
        if (this.f2703e) {
            Agent.m1952a().m1950a(this.f2710l + 7006);
        }
        if (UpdateUtils.m1843a(this.f2699a)) {
            execute(new Void[0]);
            return true;
        }
        if (this.f2703e) {
            Toast.makeText(this.f2699a, this.f2699a.getString(C0481l.C0482a.no_network), Toast.LENGTH_SHORT).show();
        }
        m1871d();
        return false;
    }

    /* renamed from: b */
    public final void m1875b() {
        if (this.f2699a != null) {
            this.f2701c = this.f2699a.getString(R.string.check_apk_update_message);
        }
    }

    /* renamed from: c */
    public final void m1873c() {
        if (this.f2699a != null) {
            this.f2707i = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public synchronized void m1867f() {
        if (this.f2699a != null) {
            this.downloadReceiver = new DownloadReceiver();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.DOWNLOAD_COMPLETE");
            this.f2699a.registerReceiver(this.downloadReceiver, intentFilter);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public synchronized void m1865g() {
        if (this.downloadReceiver != null) {
            this.f2699a.unregisterReceiver(this.downloadReceiver);
            this.downloadReceiver = null;
        }
    }

    /* renamed from: a */
    private static boolean m1880a(Version version) {
        if (version == null || !version.m1888e()) {
            return false;
        }
        String m1890c = version.m1890c();
        if (TextUtils.isEmpty(m1890c)) {
            return false;
        }
        try {
            String authority = new URL(m1890c).getAuthority();
            if (!TextUtils.isEmpty(authority) && (authority.equals("dl.smartisan.cn") || authority.equals("dl2.smartisan.cn") || authority.equals("update.smartisanos.com") || authority.contains("smartisan.com"))) {
                return true;
            }
            Log.e("ApkUpdater", "Invalid download url:" + m1890c);
            return false;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        m1871d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void m1864h() {
        if (this.f2699a instanceof Activity) {
            ((Activity) this.f2699a).finish();
            new Timer().schedule(new C0478g(this), 300L);
            return;
        }
        Process.killProcess(Process.myPid());
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0019, code lost:
        if (r0.isDestroyed() == false) goto L9;
     */
    /* renamed from: i */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean m1863i() {
        if (this.f2699a != null) {
            if (this.f2699a instanceof Activity) {
                Activity activity = (Activity) this.f2699a;
                if (!activity.isFinishing()) {
                }
            }
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ApkUpdater.java */
    /* renamed from: com.smartisan.updater.a$a */
    /* loaded from: classes.dex */
    public class DownloadReceiver extends BroadcastReceiver {

        /* renamed from: b */
        public boolean f2715b = false;

        public DownloadReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(UpdateSharedPreference.m1849a(context).m1846b());
            query.setFilterByStatus(8);
            new Thread(new RunnableC0479h(ApkUpdater.this,this, (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE), query, context)).start();
        }
    }

    /* renamed from: a */
    public final boolean m1876a(String str, String str2) {
        File file = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + UpdateUtils.m1839a(Uri.parse(str)));
        if (file.exists()) {
            if (this.f2707i && !str2.equals(UpdateUtils.m1838a(file))) {
                file.delete();
                return false;
            }
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public final void m1881a(Context context, String str) {
        File file = new File(Environment.getExternalStorageDirectory() + "/" + Environment.DIRECTORY_DOWNLOADS + "/" + UpdateUtils.m1839a(Uri.parse(str)));
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        context.startActivity(intent);
        m1864h();
    }

    /* renamed from: a */
    private static float m1882a(long j) {
        return (float) (Math.round((((((float) j) / 1.0f) / 1024.0f) / 1024.0f) * 10.0f) / 10.0d);
    }
}
