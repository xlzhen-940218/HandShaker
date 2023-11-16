package com.smartisan.trackerlib;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.smartisan.trackerlib.p045b.UploadTask;
import com.smartisan.trackerlib.p046c.CommonUtil;
import com.smartisan.trackerlib.p046c.LogUtils;
import org.json.JSONObject;

/* renamed from: com.smartisan.trackerlib.a */
/* loaded from: classes.dex */
public final class Agent {

    /* renamed from: a */
    private static Agent f2673a;

    /* renamed from: b */
    private TrackerCache f2674b;

    /* renamed from: c */
    private Application f2675c;

    private Agent() {
    }

    /* renamed from: a */
    public static synchronized Agent m1952a() {
        Agent agent;
        synchronized (Agent.class) {
            if (f2673a == null) {
                f2673a = new Agent();
            }
            agent = f2673a;
        }
        return agent;
    }

    /* renamed from: a */
    public final synchronized void m1951a(Application application) {
        this.f2675c = application;
        if (this.f2674b == null) {
            this.f2674b = new TrackerCache();
        }
        new UploadTask().m1932a();
    }

    /* renamed from: b */
    public final Context m1947b() {
        return this.f2675c;
    }

    /* renamed from: c */
    public final void m1944c() throws Exception {
        m1948a(CommonUtil.m1915c(), CommonUtil.m1913d(), false);
    }

    /* renamed from: d */
    public final void m1943d() throws Exception {
        m1948a(CommonUtil.m1917b(), null, false);
    }

    /* renamed from: a */
    public final void m1949a(String str, String str2) {
        m1945b(str, str2);
    }

    /* renamed from: b */
    private synchronized void m1945b(String str, String str2) {
        m1948a(str, str2, true);
    }

    /* renamed from: a */
    private synchronized void m1948a(String str, String str2, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (!m1946b(str2)) {
                    LogUtils.m1902a("eventdata is not json " + str2);
                } else {
                    this.f2674b.m1933a(new TransportEntity(str, str2, z));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: e */
    public final void m1942e() {
        this.f2674b.m1934a();
    }

    /* renamed from: b */
    private static boolean m1946b(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            new JSONObject(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public final void m1950a(String str) {
        m1945b(str, null);
    }
}
