package com.smartisanos.smartfolder.aoa.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes.dex */
public class MediaScannerService extends Service {

    /* renamed from: a */
    public static String TAG = MediaScannerService.class.getSimpleName();

    /* renamed from: b */
    private volatile Looper f3877b;

    /* renamed from: c */
    public volatile HandlerC0812b f3878c;

    /* renamed from: d */
    private HandlerThread f3879d;

    /* renamed from: g */
    private MediaScannerConnection f3882g;

    /* renamed from: e */
    public Queue<String> f3880e = new ArrayDeque();

    /* renamed from: f */
    private BinderC0811a f3881f = new BinderC0811a();

    /* renamed from: h */
    private MediaScannerConnection.MediaScannerConnectionClient f3883h = new C0821h(this);

    /* renamed from: com.smartisanos.smartfolder.aoa.service.MediaScannerService$a */
    /* loaded from: classes.dex */
    public class BinderC0811a extends Binder {
        public BinderC0811a() {
        }

        /* renamed from: a */
        public final void m272a(String str) {
            MediaScannerService.this.m275a(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.MediaScannerService$b */
    /* loaded from: classes.dex */
    public final class HandlerC0812b extends Handler {
        public HandlerC0812b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (MediaScannerService.this.f3882g.isConnected()) {
                try {
                    MediaScannerService.this.f3882g.scanFile((String) message.obj, null);
                } catch (IllegalStateException e) {
                    HandShaker.error("Fail to scan file, exception:" + e.toString());
                }
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.f3879d = new HandlerThread(MediaScannerService.class.getSimpleName());
        this.f3879d.start();
        this.f3877b = this.f3879d.getLooper();
        this.f3878c = new HandlerC0812b(this.f3877b);
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        if (this.f3882g.isConnected()) {
            this.f3882g.disconnect();
            return false;
        }
        return false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        if (this.f3882g == null) {
            this.f3882g = new MediaScannerConnection(this, this.f3883h);
            this.f3882g.connect();
        }
        return this.f3881f;
    }

    /* renamed from: a */
    public final void m275a(String str) {
        HandShaker.verbose(TAG, "Scan File: " + str);
        if (this.f3882g.isConnected()) {
            this.f3878c.sendMessage(this.f3878c.obtainMessage(0, str));
            return;
        }
        this.f3882g.connect();
        this.f3880e.offer(str);
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        this.f3879d.quitSafely();
    }
}
