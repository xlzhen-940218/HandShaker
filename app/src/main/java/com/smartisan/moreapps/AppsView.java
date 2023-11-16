package com.smartisan.moreapps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.smartisan.moreapps.download.DownloadApkReceiver;
import com.smartisanos.smartfolder.aoa.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class AppsView extends RelativeLayout {

    /* renamed from: a */
    DownloadApkReceiver f2618a;

    /* renamed from: b */
    BroadcastReceiver f2619b;

    /* renamed from: c */
    private Context f2620c;

    /* renamed from: d */
    public ProductsAdapter f2621d;

    /* renamed from: e */
    private ListView f2622e;

    public AppsView(Context context) {
        super(context);
        this.f2619b = new C0465c(this);
        this.f2620c = context;
        m2011a();
    }

    public AppsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2619b = new C0465c(this);
        this.f2620c = context;
        m2011a();
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0078, code lost:
        if (r0 == false) goto L25;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void m2011a() {
        boolean z;
        ArrayList<AppInfoList.C0464a> m1997a;
        View inflate = LayoutInflater.from(this.f2620c).inflate(R.layout.more_apps_layout, this);
        this.f2621d = new ProductsAdapter(this.f2620c);
        if (new File(this.f2620c.getApplicationContext().getFilesDir().toString(), "string.xml").exists()) {
            String m2008a = m2008a(this.f2620c.getApplicationContext().getFilesDir().toString() + "/string.xml");
            if (TextUtils.isEmpty(m2008a) || (m1997a = new AppListParser(this.f2620c, m2008a, true).m1997a()) == null || m1997a.size() <= 0) {
                z = false;
            } else {
                this.f2621d.m1978a(m1997a);
                z = true;
            }
        }
        this.f2621d.m1982a();
        this.f2622e = (ListView) inflate.findViewById(R.id.app_list);
        this.f2622e.setAdapter((ListAdapter) this.f2621d);
        m2010a(this.f2622e);
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2);
        int i3 = calendar.get(5);
        long currentTimeMillis = System.currentTimeMillis();
        long m1968b = SmartisanAppPref.m1972a(this.f2620c).m1968b();
        calendar.setTimeInMillis(m1968b);
        if (!((i == calendar.get(1) && i2 == calendar.get(2) && i3 == calendar.get(5) && currentTimeMillis > m1968b) ? false : true)) {
            return;
        }
        new AsyncTaskC0463a(this.f2620c).execute(new Void[0]);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE");
        this.f2618a = new DownloadApkReceiver();
        getContext().registerReceiver(this.f2618a, intentFilter);
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter2.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter2.addDataScheme("package");
        this.f2620c.registerReceiver(this.f2619b, intentFilter2);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f2620c != null) {
            this.f2620c.unregisterReceiver(this.f2619b);
            this.f2620c.unregisterReceiver(this.f2618a);
        }
    }

    /* renamed from: a */
    private static void m2010a(ListView listView) {
        int makeMeasureSpec;
        ListAdapter adapter = listView.getAdapter();
        if (adapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < adapter.getCount(); i2++) {
                View view = adapter.getView(i2, null, listView);
                AbsListView.LayoutParams layoutParams = (AbsListView.LayoutParams) view.getLayoutParams();
                if (layoutParams == null) {
                    layoutParams = new AbsListView.LayoutParams(-1, -2, 0);
                    view.setLayoutParams(layoutParams);
                }
                int childMeasureSpec = ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.AT_MOST), 0, layoutParams.width);
                int i3 = layoutParams.height;
                if (i3 > 0) {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, MeasureSpec.EXACTLY);
                } else {
                    makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
                }
                view.measure(childMeasureSpec, makeMeasureSpec);
                i += view.getMeasuredHeight();
            }
            ViewGroup.LayoutParams layoutParams2 = listView.getLayoutParams();
            layoutParams2.height = (listView.getDividerHeight() * (adapter.getCount() - 1)) + i;
            listView.setLayoutParams(layoutParams2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.io.BufferedReader] */
    /* renamed from: a */
    private static String m2008a(String str) {
        FileInputStream fileInputStream;
        Throwable th;
        IOException e;
        BufferedReader bufferedReader;
        FileNotFoundException e2;
        Object isEmpty = TextUtils.isEmpty(str);
        if ((boolean) isEmpty) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            try {
                fileInputStream = new FileInputStream(str);
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    while (true) {
                        try {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                stringBuffer.append(readLine);
                            } else {
                                break;
                            }
                        } catch (FileNotFoundException e4) {
                            e2 = e4;
                            e2.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return stringBuffer.toString();
                        } catch (IOException e6) {
                            e = e6;
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e7) {
                                    e7.printStackTrace();
                                }
                            }
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            return stringBuffer.toString();
                        }
                    }
                    fileInputStream.close();
                    bufferedReader.close();
                } catch (FileNotFoundException e8) {
                    bufferedReader = null;
                    e2 = e8;
                } catch (IOException e9) {
                    bufferedReader = null;
                    e = e9;
                } catch (Throwable th2) {
                    isEmpty = 0;
                    th = th2;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e10) {
                            e10.printStackTrace();
                            throw th;
                        }
                    }
                    fileInputStream.close();
                    throw th;
                }
            } catch (FileNotFoundException e11) {
                fileInputStream = null;
                e2 = e11;
                bufferedReader = null;
            } catch (IOException e12) {
                fileInputStream = null;
                e = e12;
                bufferedReader = null;
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
                isEmpty = 0;
            }
            return stringBuffer.toString();
        } catch (Throwable th4) {
            th = th4;
        }
        return null;
    }

    /* renamed from: com.smartisan.moreapps.AppsView$a */
    /* loaded from: classes.dex */
    public static class AsyncTaskC0463a extends AsyncTask<Void, Void, ArrayList<AppInfoList.C0464a>> {

        /* renamed from: a */
        private int f2623a = 0;

        /* renamed from: b */
        private Context f2624b;

        @Override // android.os.AsyncTask
        protected final /* synthetic */ ArrayList<AppInfoList.C0464a> doInBackground(Void[] voidArr) {
            return m2007a();
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(ArrayList<AppInfoList.C0464a> arrayList) {
            ArrayList<AppInfoList.C0464a> arrayList2 = arrayList;
            super.onPostExecute(arrayList2);
            if (arrayList2 == null || arrayList2.size() <= 0) {
                return;
            }
            if (this.f2623a > 0) {
                SmartisanAppPref.m1972a(this.f2624b).m1974a(this.f2623a);
            }
            SmartisanAppPref.m1972a(this.f2624b).m1969a(false);
        }

        public AsyncTaskC0463a(Context context) {
            this.f2624b = null;
            this.f2624b = context;
        }

        /* renamed from: a */
        private ArrayList<AppInfoList.C0464a> m2007a() {
            String str;
            Exception e;
            Throwable th;
            FileOutputStream fileOutputStream = null;
            if (m2006b()) {
                SmartisanAppPref.m1972a(this.f2624b).m1969a(true);
                if (SmartisanAppUtils.m1965b(this.f2624b)) {
                    new HttpData();
                    try {
                        str = new JSONObject(HttpData.m1992a("http://setting.smartisan.com/config/app/i18n")).getString("uri");
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                        str = null;
                    }
                    if (TextUtils.isEmpty(str) || !SmartisanAppUtils.m1965b(this.f2624b)) {
                        return null;
                    }
                    String m1992a = HttpData.m1992a(str);
                    if (!TextUtils.isEmpty(m1992a)) {
                        File filesDir = this.f2624b.getApplicationContext().getFilesDir();
                        File file = new File(filesDir.toString());
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        File file2 = new File(filesDir.toString(), "string.xml");
                        if (file2.exists()) {
                            file2.delete();
                        }
                        try {
                            try {
                                file2.createNewFile();
                                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                                try {
                                    fileOutputStream2.write(m1992a.getBytes());
                                    try {
                                        fileOutputStream2.close();
                                    } catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                } catch (IOException e3) {
                                    e = e3;
                                    fileOutputStream = fileOutputStream2;
                                    e.printStackTrace();
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                        }
                                    }
                                    return new AppListParser(this.f2624b, m1992a, false).m1997a();
                                } catch (Throwable th1) {
                                    th = th1;
                                    fileOutputStream = fileOutputStream2;
                                    if (fileOutputStream != null) {
                                        try {
                                            fileOutputStream.close();
                                        } catch (IOException e5) {
                                            e5.printStackTrace();
                                        }
                                    }
                                    throw th;
                                }
                            } catch (IOException e6) {
                                e = e6;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    return new AppListParser(this.f2624b, m1992a, false).m1997a();
                }
                return null;
            }
            SmartisanAppPref.m1972a(this.f2624b).m1969a(false);
            return null;
        }

        /* renamed from: b */
        private boolean m2006b() {
            boolean z = false;
            try {
                int m1975a = SmartisanAppPref.m1972a(this.f2624b).m1975a();
                new HttpData();
                String m1992a = HttpData.m1992a("http://setting.smartisan.com/config/app/i18n_version");
                if (!TextUtils.isEmpty(m1992a)) {
                    VersionInfo m1963a = VersionInfo.m1963a(new JSONObject(m1992a));
                    if (m1963a != null && m1963a.m1964a() > m1975a) {
                        z = true;
                    }
                    this.f2623a = m1963a.m1964a();
                    SmartisanAppPref.m1972a(this.f2624b).m1973a(System.currentTimeMillis());
                }
            } catch (Exception e) {
                Log.e("AppsView", "Fail to get verion information, exception:" + e.toString());
            }
            return z;
        }
    }
}
