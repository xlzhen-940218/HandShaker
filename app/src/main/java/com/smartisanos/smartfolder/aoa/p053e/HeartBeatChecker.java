package com.smartisanos.smartfolder.aoa.p053e;

import android.annotation.TargetApi;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.service.MediaScannerService;

/* renamed from: com.smartisanos.smartfolder.aoa.e.a */
/* loaded from: classes.dex */
public final class HeartBeatChecker {

    /* renamed from: a */
    private static final HeartBeatChecker instance = new HeartBeatChecker();

    /* renamed from: b */
    private long f3549b;

    /* renamed from: c */
    private HandlerThread mediaScannerServiceHandlerThread;

    /* renamed from: d */
    private Handler f3551d;

    /* renamed from: e */
    private InterfaceC0740b f3552e;

    /* renamed from: f */
    private boolean f3553f;

    /* renamed from: g */
    private long f3554g = 15000;

    /* compiled from: HeartBeatChecker.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.e.a$b */
    /* loaded from: classes.dex */
    public interface InterfaceC0740b {
        /* renamed from: a */
        void mo606a();

        /* renamed from: b */
        boolean mo600b();
    }

    private HeartBeatChecker() {
    }

    /* renamed from: a */
    public static HeartBeatChecker getInstance() {
        return instance;
    }

    /* renamed from: a */
    public final synchronized void m674a(InterfaceC0740b interfaceC0740b) {
        if (this.f3553f) {
            HandShaker.error("HeartBeatChecker", "has been connected!!!");
            m672b();
        }
        this.f3553f = true;
        this.f3552e = interfaceC0740b;
        this.mediaScannerServiceHandlerThread = new HandlerThread(MediaScannerService.class.getSimpleName());
        this.mediaScannerServiceHandlerThread.start();
        this.f3551d = new HandlerC0739a(this.mediaScannerServiceHandlerThread.getLooper());
        m669e();
        m671c();
    }

    @TargetApi(19)
    /* renamed from: b */
    public final synchronized void m672b() {
        if (this.f3553f) {
            this.f3553f = false;
            if (this.mediaScannerServiceHandlerThread != null) {
                this.mediaScannerServiceHandlerThread.quitSafely();
                this.mediaScannerServiceHandlerThread = null;
            }
            this.f3551d = null;
            this.f3552e = null;
        }
    }

    /* renamed from: c */
    public final void m671c() {
        this.f3549b = SystemClock.uptimeMillis();
        HandShaker.info("HeartBeatChecker", "onHeartBeat");
    }

    /* renamed from: d */
    public final long getClientTimestamp() {
        return this.f3549b;
    }

    /* renamed from: e */
    public final void m669e() {
        if (this.f3551d != null) {
            this.f3551d.sendEmptyMessageDelayed(0, 1000L);
        }
    }

    /* renamed from: a */
    public final void m675a(long j) {
        if (j != 0) {
            HandShaker.debug("HeartBeatChecker", "setTimeout " + j);
            if (j < 5000) {
                j = 5000;
            }
            this.f3554g = j;
        }
    }

    /* compiled from: HeartBeatChecker.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.e.a$a */
    /* loaded from: classes.dex */
    private class HandlerC0739a extends Handler {
        public HandlerC0739a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            HeartBeatChecker.m673a(HeartBeatChecker.this);
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m673a(HeartBeatChecker heartBeatChecker) {
        if (heartBeatChecker.f3552e != null && !heartBeatChecker.f3552e.mo600b()) {
            long uptimeMillis = SystemClock.uptimeMillis() - heartBeatChecker.f3549b;
            HandShaker.info("HeartBeatChecker", "check timeInterval: " + uptimeMillis + ",  heartBeatTime: " + heartBeatChecker.f3549b);
            if (uptimeMillis > heartBeatChecker.f3554g) {
                HandShaker.debug("HeartBeatChecker", "time out !!!");
                heartBeatChecker.f3552e.mo606a();
                return;
            }
            heartBeatChecker.m669e();
        }
    }
}
