package com.smartisanos.smartfolder.aoa.service;

import android.content.Context;
import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p050b.StartWifiServerEvent;
import com.smartisanos.smartfolder.aoa.p050b.TrustCancelEvent;
import com.smartisanos.smartfolder.aoa.p050b.TrustFinishEvent;
import com.smartisanos.smartfolder.aoa.p050b.TrustRequestEvent;
import com.smartisanos.smartfolder.aoa.p055g.Connection;
import com.smartisanos.smartfolder.aoa.p055g.ConnectionManager;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.NetWorkUtils;

import java.net.ServerSocket;
import java.util.concurrent.atomic.AtomicBoolean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* renamed from: com.smartisanos.smartfolder.aoa.service.m */
/* loaded from: classes.dex */
public final class WifiConnectionManager extends ConnectionManager {

    /* renamed from: c */
    private final String f3919c;

    /* renamed from: d */
    private final HandlerC0829b f3920d;

    /* renamed from: e */
    private final C0830c f3921e;

    /* renamed from: f */
    private NsdManager f3922f;

    /* renamed from: g */
    private volatile boolean f3923g;

    /* renamed from: h */
    private final AtomicBoolean f3924h;

    /* compiled from: WifiConnectionManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.m$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0828a {
        /* renamed from: a */
        void mo229a(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: WifiConnectionManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.m$c */
    /* loaded from: classes.dex */
    public class C0830c extends TcpSocketManager {
        C0830c() {
        }

        @Override // com.smartisanos.smartfolder.aoa.service.TcpSocketManager
        /* renamed from: a */
        protected final Connection mo227a(TcpSocketManager.C0823b c0823b, Connection.C0748c c0748c) {
            return WifiConnectionManager.this.m605a(c0823b, c0748c, Connection.EnumC0746a.WIFI);
        }

        @Override // com.smartisanos.smartfolder.aoa.service.TcpSocketManager
        /* renamed from: a */
        protected final boolean mo228a() {
            return (WifiConnectionManager.this.m597g() || WifiConnectionManager.this.f3923g) ? false : true;
        }

        @Override // com.smartisanos.smartfolder.aoa.service.TcpSocketManager
        /* renamed from: b */
        protected final void mo226b() {
            WifiConnectionManager.this.f3920d.sendEmptyMessage(1);
        }

        @Override // com.smartisanos.smartfolder.aoa.service.TcpSocketManager
        /* renamed from: c */
        protected final void mo225c() {
            WifiConnectionManager.this.f3600b.mo267a();
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.ConnectionManager
    /* renamed from: a */
    public final synchronized void mo244a(Connection connection) {
        m599c(connection);
        super.mo244a(connection);
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.ConnectionManager
    /* renamed from: b */
    public final void mo240b(Connection connection) {
        super.mo240b(connection);
        this.f3921e.m263a(connection);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: WifiConnectionManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.m$b */
    /* loaded from: classes.dex */
    public class HandlerC0829b extends Handler implements NsdManager.RegistrationListener {
        public HandlerC0829b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    WifiConnectionManager.m235d(WifiConnectionManager.this);
                    return;
                case 1:
                    WifiConnectionManager.m233e(WifiConnectionManager.this);
                    return;
                default:
                    throw new IllegalArgumentException("unknown msg id: " + message.what);
            }
        }

        @Override // android.net.nsd.NsdManager.RegistrationListener
        public void onRegistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
            WifiConnectionManager.m232f(WifiConnectionManager.this);
            HandShaker.error("WifiConnectionManager", "Failed to register service, errCode=" + i);
        }

        @Override // android.net.nsd.NsdManager.RegistrationListener
        public void onUnregistrationFailed(NsdServiceInfo nsdServiceInfo, int i) {
            WifiConnectionManager.m232f(WifiConnectionManager.this);
            HandShaker.error("WifiConnectionManager", "Service unregister failed, errorCode = " + i);
        }

        @Override // android.net.nsd.NsdManager.RegistrationListener
        public void onServiceRegistered(NsdServiceInfo nsdServiceInfo) {
            WifiConnectionManager.this.f3924h.set(true);
            WifiConnectionManager.m232f(WifiConnectionManager.this);
            HandShaker.info("WifiConnectionManager", "Registered service. Actual name used: " + nsdServiceInfo.getServiceName());
        }

        @Override // android.net.nsd.NsdManager.RegistrationListener
        public void onServiceUnregistered(NsdServiceInfo nsdServiceInfo) {
            WifiConnectionManager.this.f3924h.set(false);
            WifiConnectionManager.m232f(WifiConnectionManager.this);
            HandShaker.info("WifiConnectionManager", "Service " + nsdServiceInfo.getServiceName() + " unregistered");
        }
    }

    public WifiConnectionManager(Context context, ConnectionManager.InterfaceC0750a interfaceC0750a) {
        super(context, interfaceC0750a);
        this.f3919c = "WifiConnectionManager";
        this.f3924h = new AtomicBoolean(false);
        HandlerThread handlerThread = new HandlerThread(HandlerC0829b.class.getSimpleName());
        handlerThread.start();
        this.f3920d = new HandlerC0829b(handlerThread.getLooper());
        EventBus.getDefault().register(this);
        this.f3921e = new C0830c();
    }

    /* renamed from: c */
    public final void m238c() {
        HandShaker.debug("WifiConnectionManager", "startNsdBroadcast isRegister: " + this.f3924h);
        this.f3920d.removeMessages(0);
        this.f3920d.sendEmptyMessage(0);
    }

    /* renamed from: d */
    public final void m236d() {
        HandShaker.debug("WifiConnectionManager", "stopNsdBroadcast");
        this.f3920d.removeMessages(1);
        this.f3920d.sendEmptyMessage(1);
    }

    /* renamed from: e */
    public final boolean m234e() {
        return this.f3924h.get();
    }

    /* renamed from: h */
    private NsdManager m230h() {
        if (this.f3922f == null) {
            this.f3922f = (NsdManager) this.f3599a.getSystemService("servicediscovery");
        }
        return this.f3922f;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public final void onMessageEvent(TrustRequestEvent trustRequestEvent) {
        HandShaker.debug("WifiConnectionManager", "TrustRequestEvent mIsTrustRequesting " + this.f3923g);
        if (!this.f3923g) {
            this.f3923g = true;
            m599c(trustRequestEvent.m766a());
            m236d();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(TrustFinishEvent trustFinishEvent) {
        HandShaker.debug("WifiConnectionManager", "TrustRequestEvent mIsTrustRequesting " + this.f3923g);
        this.f3923g = false;
        if (!m597g()) {
            EventBus.getDefault().post(new StartWifiServerEvent());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(TrustCancelEvent trustCancelEvent) {
        HandShaker.debug("WifiConnectionManager", "TrustCancelEvent mIsTrustRequesting " + this.f3923g);
        this.f3923g = false;
        EventBus.getDefault().post(new StartWifiServerEvent());
    }

    /* renamed from: a */
    public final void m241a(String str, InterfaceC0828a interfaceC0828a) {
        if (this.f3921e == null || TextUtils.isEmpty(str)) {
            HandShaker.error("WifiConnectionManager", "QRCode, Fail to send link info, info:" + str);
            return;
        }
        String m377c = NetWorkUtils.m377c(FolderApp.getInstance());
        int d = this.f3921e.m260d();
        if (d >= 0) {
            new Thread(new RunnableC0831n(this, str, m377c, d, interfaceC0828a)).start();
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m235d(WifiConnectionManager wifiConnectionManager) {
        synchronized (wifiConnectionManager.f3924h) {
            try {
                HandShaker.debug("WifiConnectionManager", "doStartNsdBroadcast");
                ServerSocket a = wifiConnectionManager.f3921e.m264a(0);
                if (!wifiConnectionManager.f3924h.get() && a != null) {
                    NsdServiceInfo nsdServiceInfo = new NsdServiceInfo();
                    nsdServiceInfo.setServiceName("handshaker_ssp_");
                    nsdServiceInfo.setServiceType("_handshaker_ssp._tcp.");
                    nsdServiceInfo.setPort(wifiConnectionManager.f3921e.m260d());
                    wifiConnectionManager.m230h().registerService(nsdServiceInfo, 1, wifiConnectionManager.f3920d);
                    wifiConnectionManager.f3924h.wait();
                } else {
                    HandShaker.debug("doStartNsdBroadcast fail, maybe has been registered or serversocket create fail. mIsNsdRegistered: " + wifiConnectionManager.f3924h + ", mServerSocket: " + a);
                }
            } catch (Exception e) {
                HandShaker.debug("WifiConnectionManager", "doStartNsdBroadcast exception: " + e);
                e.printStackTrace();
            }
        }
    }

    /* renamed from: e */
    static /* synthetic */ void m233e(WifiConnectionManager wifiConnectionManager) {
        synchronized (wifiConnectionManager.f3924h) {
            try {
                if (!wifiConnectionManager.m597g() && !wifiConnectionManager.f3923g) {
                    wifiConnectionManager.f3921e.m259e();
                }
                if (wifiConnectionManager.f3924h.get()) {
                    HandShaker.info("WifiConnectionManager", "unregister nsd service");
                    wifiConnectionManager.m230h().unregisterService(wifiConnectionManager.f3920d);
                    wifiConnectionManager.f3924h.wait();
                }
            } catch (Exception e) {
                HandShaker.debug("doStopNsdBroadcast exception: " + e);
                e.printStackTrace();
            }
        }
    }

    /* renamed from: f */
    static /* synthetic */ void m232f(WifiConnectionManager wifiConnectionManager) {
        synchronized (wifiConnectionManager.f3924h) {
            wifiConnectionManager.f3924h.notify();
        }
    }
}
