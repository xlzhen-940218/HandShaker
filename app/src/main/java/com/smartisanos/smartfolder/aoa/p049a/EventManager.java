package com.smartisanos.smartfolder.aoa.p049a;

import com.smartisanos.smartfolder.aoa.p055g.Connection;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;

/* renamed from: com.smartisanos.smartfolder.aoa.a.a */
/* loaded from: classes.dex */
public final class EventManager {

    /* renamed from: a */
    private static EventManager instance = null;

    /* renamed from: b */
    private static final Object lock = new Object();

    /* renamed from: c */
    private static long f3409c = 0;

    /* renamed from: d */
    private final ArrayList<REQUEST_TYPE> f3410d = new ArrayList<>();

    /* renamed from: e */
    private Connection f3411e;

    /* compiled from: EventManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.a.a$a */
    /* loaded from: classes.dex */
    public enum REQUEST_TYPE {
        AUDIO,
        VIDEO,
        IMAGE,
        DEVICE_INFO,
        FILE,
        MEDIA,
        CLIPBOARD,
        PHOTO_SYNC
    }

    /* renamed from: a */
    public static EventManager getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new EventManager();
                }
            }
        }
        return instance;
    }

    /* renamed from: b */
    public static void m796b() {
        if (instance == null) {
            instance = getInstance();
        }
        f3409c = -2147483647L;
    }

    /* renamed from: a */
    public final void m797a(Connection connection) {
        this.f3411e = connection;
    }

    /* renamed from: a */
    public final void m799a(REQUEST_TYPE enumC0705a) {
        if (m794c()) {
            synchronized (this.f3410d) {
                this.f3410d.add(enumC0705a);
            }
        }
    }

    /* renamed from: c */
    public static boolean m794c() {
        return f3409c < 0;
    }

    /* renamed from: b */
    public final void m795b(REQUEST_TYPE enumC0705a) {
        synchronized (this.f3410d) {
            this.f3410d.remove(enumC0705a);
        }
    }

    /* renamed from: d */
    public static int m793d() {
        long j = f3409c;
        f3409c++;
        return (int) j;
    }

    /* renamed from: a */
    public final void m798a(REQUEST_TYPE enumC0705a, byte[] bArr) {
        synchronized (this.f3410d) {
            if (this.f3411e != null && this.f3410d.contains(enumC0705a)) {
                int m793d = m793d();
                this.f3411e.m616h().m609a(m793d, bArr);
                HandShaker.debug(EventBus.TAG, "onEvent success " + m793d);
            }
        }
    }
}
