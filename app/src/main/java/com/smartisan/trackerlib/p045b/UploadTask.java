package com.smartisan.trackerlib.p045b;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.text.TextUtils;
import com.smartisan.trackerlib.Agent;
import com.smartisan.trackerlib.TransportEntity;
import com.smartisan.trackerlib.p044a.TrackerProvider;
import com.smartisan.trackerlib.p046c.CommonUtil;
import com.smartisan.trackerlib.p046c.LogUtils;
import com.smartisan.trackerlib.p046c.SharePreferenceManager;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.smartisan.trackerlib.b.a */
/* loaded from: classes.dex */
public final class UploadTask implements Runnable {

    /* renamed from: a */
    private static List<String> f2681a = new ArrayList();

    /* renamed from: b */
    private static List<String> f2682b = new ArrayList();

    /* renamed from: c */
    private static List<TransportEntity> f2683c = new ArrayList();

    /* renamed from: d */
    private static String f2684d = "";

    /* renamed from: g */
    private static ExecutorService f2685g;

    /* renamed from: e */
    private boolean f2686e = false;

    /* renamed from: f */
    private List<TransportEntity[]> f2687f = new ArrayList();

    /* renamed from: b */
    private static synchronized void m1926b() {
        synchronized (UploadTask.class) {
            if (f2685g == null) {
                f2685g = Executors.newSingleThreadExecutor();
            }
        }
    }

    public UploadTask(TransportEntity[] transportEntityArr) {
        this.f2687f.add(transportEntityArr);
    }

    public UploadTask() {
    }

    /* renamed from: a */
    public final void m1932a() {
        m1926b();
        f2685g.execute(this);
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        List<TransportEntity> arrayList = new ArrayList<>();
        List<TransportEntity> arrayList2 = new ArrayList<>();
        for (TransportEntity[] transportEntityArr : this.f2687f) {
            for (TransportEntity transportEntity : transportEntityArr) {
                if (transportEntity.m1954e()) {
                    arrayList.add(transportEntity);
                } else {
                    arrayList2.add(transportEntity);
                }
            }
        }
        switch (CommonUtil.m1919a(Agent.m1952a().m1947b())) {
            case 0:
                arrayList.addAll(arrayList2);
                TrackerProvider.m1941a();
                TrackerProvider.m1939a(m1928a(arrayList));
                break;
            case 1:
                m1927a(arrayList2, 1);
                TrackerProvider.m1941a();
                TrackerProvider.m1939a(m1928a(arrayList));
                m1931a(1);
                break;
            case 2:
                arrayList.addAll(arrayList2);
                if (arrayList.size() > 150) {
                    z = m1927a(arrayList, 2);
                    arrayList.clear();
                } else {
                    z = true;
                }
                if (z) {
                    m1924b(arrayList);
                }
                m1931a(2);
                break;
        }
        TrackerProvider.m1941a();
        TrackerProvider.m1937b();
    }

    /* renamed from: a */
    private void m1931a(int i) {
        try {
            ArrayList arrayList = new ArrayList();
            int i2 = 0;
            int i3 = 0;
            while (f2683c != null && f2683c.size() > 0 && i2 < 3) {
                arrayList.clear();
                arrayList.addAll(f2683c);
                f2683c.clear();
                i2++;
                i3 += i2 * 1000;
                if (!m1921c(arrayList, i)) {
                    break;
                } else if (f2683c.size() > 0) {
                    Thread.sleep(i3);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (f2683c != null) {
            f2683c.clear();
        }
        this.f2686e = false;
    }

    /* renamed from: a */
    private static TransportEntity[] m1928a(List<TransportEntity> list) {
        TransportEntity[] transportEntityArr = new TransportEntity[list.size()];
        list.toArray(transportEntityArr);
        return transportEntityArr;
    }

    /* renamed from: b */
    private void m1924b(List<TransportEntity> list) {
        new ArrayList();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        TrackerProvider.m1941a();
        TrackerProvider.m1940a("time_stamp <= " + CommonUtil.m1906h());
        while (true) {
            list.addAll(m1925b(150 - list.size()));
            if (list.size() <= 0 || this.f2686e) {
                break;
            } else if (!m1921c(list, 2)) {
                if (arrayList.size() > 0) {
                    TrackerProvider.m1941a();
                    TrackerProvider.m1939a(m1928a(arrayList));
                }
                f2681a.clear();
                return;
            } else {
                arrayList.clear();
                m1923b(list, 2);
                if (f2681a.size() > 0) {
                    String[] strArr = new String[f2681a.size()];
                    f2681a.toArray(strArr);
                    TrackerProvider.m1941a();
                    TrackerProvider.m1938a(strArr);
                    f2681a.clear();
                }
                list.clear();
            }
        }
        this.f2686e = false;
    }

    /* renamed from: a */
    private boolean m1927a(List<TransportEntity> list, int i) {
        if (!m1921c(list, i)) {
            TrackerProvider.m1941a();
            TrackerProvider.m1939a(m1928a(list));
            return false;
        }
        m1923b(list, i);
        boolean z = !this.f2686e;
        this.f2686e = false;
        return z;
    }

    /* renamed from: b */
    private void m1923b(List<TransportEntity> list, int i) {
        if (list != null && list.size() > 0) {
            for (TransportEntity transportEntity : list) {
                if (f2682b.contains(new StringBuilder().append(transportEntity.m1955d()).toString())) {
                    f2683c.add(transportEntity);
                }
            }
            if (f2683c.size() > 150) {
                m1931a(i);
            }
            f2682b.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: c */
    private boolean m1921c(List<TransportEntity> list, int i) {
        boolean z;
        HttpsURLConnection httpsURLConnection = null;
        boolean z2 = false;
        if (CommonUtil.m1919a(Agent.m1952a().m1947b()) == i) {
            Agent.m1952a();
            if (TextUtils.isEmpty(f2684d)) {
                SharePreferenceManager.m1899a();
                String m1895a = SharePreferenceManager.m1895a("track_uid", "com.smartisan.LibTracker", Agent.m1952a().m1947b(), "");
                f2684d = m1895a;
                if (TextUtils.isEmpty(m1895a)) {
                    f2684d = m1922c();
                    SharePreferenceManager.m1899a();
                    SharePreferenceManager.m1894a("track_uid", f2684d, "com.smartisan.LibTracker", Agent.m1952a().m1947b());
                }
            }
            String str = f2684d;
            if (!TextUtils.isEmpty(str)) {
                JSONArray jSONArray = new JSONArray();
                for (TransportEntity transportEntity : list) {
                    transportEntity.m1960a(str);
                    jSONArray.put(transportEntity.m1961a());
                }
                Object httpsURLConnection2 = null;
                try {
                    try {
                        httpsURLConnection = (HttpsURLConnection) new URL("https://dc.smartisan.com/v1/tracker/app").openConnection();
                        try {
                            httpsURLConnection.setDoOutput(true);
                            httpsURLConnection.setDoInput(true);
                            httpsURLConnection.setRequestMethod("POST");
                            httpsURLConnection.setConnectTimeout(10000);
                            httpsURLConnection.setReadTimeout(10000);
                            httpsURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                            httpsURLConnection.setRequestProperty("Content-Encoding", "application/gzip");
                            httpsURLConnection.setUseCaches(false);
                            httpsURLConnection.connect();
                            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(httpsURLConnection.getOutputStream());
                            gZIPOutputStream.write(jSONArray.toString().getBytes());
                            gZIPOutputStream.flush();
                            gZIPOutputStream.close();
                            int responseCode = httpsURLConnection.getResponseCode();
                            httpsURLConnection2 = responseCode;
                            if (responseCode == 200) {
                                ArrayList arrayList = new ArrayList();
                                arrayList.addAll(list);
                                String m1930a = m1930a(httpsURLConnection.getInputStream());
                                LogUtils.m1902a("uploadtask  " + m1930a);
                                m1929a(m1930a, arrayList);
                                z2 = true;
                                httpsURLConnection2 = arrayList;
                            }
                        } catch (Exception e) {
                            httpsURLConnection2 = httpsURLConnection;
                            e = e;
                            LogUtils.m1902a("connection is error: " + e.getMessage());
                            if (httpsURLConnection2 != null) {
                                ((HttpsURLConnection)httpsURLConnection2).disconnect();
                                z = false;
                                httpsURLConnection2 = httpsURLConnection2;
                                z2 = z;
                                return z2;
                            }
                            z = z2;
                            httpsURLConnection2 = httpsURLConnection2;
                            z2 = z;
                            return z2;
                        } catch (Throwable th) {
                            httpsURLConnection2 = httpsURLConnection;
                            th = th;
                            if (httpsURLConnection2 != null) {
                                ((HttpsURLConnection)httpsURLConnection2).disconnect();
                            }
                            throw th;
                        }
                    } catch (Exception e2) {

                    }
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                        z = z2;
                        httpsURLConnection2 = httpsURLConnection2;
                        z2 = z;
                    }
                    z = z2;
                    httpsURLConnection2 = httpsURLConnection2;
                    z2 = z;
                } catch (Throwable th2) {

                }
            }
        }
        return z2;
    }

    /* renamed from: a */
    private boolean m1929a(String str, List<TransportEntity> list) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code");
            ArrayList arrayList = new ArrayList();
            boolean z = optInt == 0;
            try {
                if (optInt == 500) {
                    f2683c.addAll(list);
                    this.f2686e = true;
                    return z;
                } else if (optInt == 0) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("data");
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        if (jSONObject2.optInt("code") == 400) {
                            LogUtils.m1901b(jSONObject2.toString());
                        } else if (jSONObject2.optInt("code") == 500) {
                            arrayList.add(jSONObject2.optString("timestamp"));
                        }
                    }
                    f2682b.addAll(arrayList);
                    if (arrayList.size() > 50) {
                        this.f2686e = true;
                        return z;
                    }
                    this.f2686e = false;
                    return z;
                } else {
                    return z;
                }
            } catch (Exception e) {
                return z;
            }
        } catch (Exception e2) {
            return false;
        }
    }

    /* renamed from: b */
    @SuppressLint("Range")
    private static List<TransportEntity> m1925b(int i) {
        ArrayList arrayList = new ArrayList();
        TrackerProvider.m1941a();
        Cursor m1936b = TrackerProvider.m1936b("wifionly <= 1");
        try {
            if (m1936b.moveToFirst()) {
                do {
                   String string = m1936b.getString(m1936b.getColumnIndex("eventid"));
                    long j = m1936b.getLong(m1936b.getColumnIndex("time_stamp"));
                    String string2 = m1936b.getString(m1936b.getColumnIndex("eventdata"));
                    int i2 = m1936b.getInt(m1936b.getColumnIndex("wifionly"));
                    int i3 = m1936b.getInt(m1936b.getColumnIndex("_id"));
                    arrayList.add(new TransportEntity(string, string2, j, i2 == 1, m1936b.getInt(m1936b.getColumnIndex("upload_time"))));
                    f2681a.add(String.valueOf(i3));
                    if (!m1936b.moveToNext()) {
                        break;
                    }
                } while (arrayList.size() < i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            m1936b.close();
        }
        return arrayList;
    }

    /* renamed from: c */
    private static String m1922c() {
        String str = "";
        HttpsURLConnection httpsURLConnection = null;
        try {
            try {
                HttpsURLConnection httpsURLConnection2 = (HttpsURLConnection) new URL("https://dc.smartisan.com/v1/tracker/android_uid").openConnection();
                try {
                    httpsURLConnection2.setDoOutput(true);
                    httpsURLConnection2.setDoInput(true);
                    httpsURLConnection2.setRequestMethod("POST");
                    httpsURLConnection2.setConnectTimeout(10000);
                    httpsURLConnection2.setReadTimeout(10000);
                    httpsURLConnection2.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    httpsURLConnection2.setUseCaches(false);
                    httpsURLConnection2.connect();
                    DataOutputStream dataOutputStream = new DataOutputStream(httpsURLConnection2.getOutputStream());
                    String m1916b = CommonUtil.m1916b(Agent.m1952a().m1947b());
                    String m1905i = CommonUtil.m1905i();
                    String m1909f = CommonUtil.m1909f();
                    String str2 = "imei=" + m1916b + "&android_id=" + CommonUtil.m1914c(Agent.m1952a().m1947b());
                    if (!TextUtils.isEmpty(m1905i)) {
                        str2 = str2 + "&mac=" + m1905i;
                    }
                    if (!TextUtils.isEmpty(m1909f)) {
                        str2 = str2 + "&mark_id=" + m1909f;
                    }
                    LogUtils.m1902a("get uid param   " + str2);
                    dataOutputStream.write(str2.getBytes());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    int responseCode = httpsURLConnection2.getResponseCode();
                    LogUtils.m1902a("get uid code   " + responseCode);
                    if (responseCode == 200) {
                        String m1930a = m1930a(httpsURLConnection2.getInputStream());
                        LogUtils.m1902a("get uid response   " + m1930a);
                        JSONObject jSONObject = new JSONObject(m1930a);
                        if (jSONObject.getInt("code") == 0) {
                            str = jSONObject.getString("data");
                        }
                    }
                    if (httpsURLConnection2 != null) {
                        httpsURLConnection2.disconnect();
                        return str;
                    }
                } catch (Exception e) {
                    httpsURLConnection = httpsURLConnection2;
                    e = e;
                    LogUtils.m1902a("connection is error: " + e.getMessage());
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                        return "";
                    }
                    return str;
                } catch (Throwable th) {
                    httpsURLConnection = httpsURLConnection2;
                    th = th;
                    if (httpsURLConnection != null) {
                        httpsURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {

            }
        } catch (Exception e2) {

        }
        return str;
    }

    /* renamed from: a */
    private static String m1930a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        inputStream.close();
        String str = new String(byteArray);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }
}
