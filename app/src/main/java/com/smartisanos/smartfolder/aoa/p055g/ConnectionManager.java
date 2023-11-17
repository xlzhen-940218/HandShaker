package com.smartisanos.smartfolder.aoa.p055g;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.aoa.p053e.HeartBeatChecker;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.service.MediaScannerService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* renamed from: com.smartisanos.smartfolder.aoa.g.c */
/* loaded from: classes.dex */
public abstract class ConnectionManager implements HeartBeatChecker.InterfaceC0740b {

    /* renamed from: a */
    protected Context context;

    /* renamed from: b */
    protected InterfaceC0750a f3600b;

    /* renamed from: c */
    public MediaScannerService.BinderC0811a f3601c;

    /* renamed from: e */
    private volatile Connection connection;

    /* renamed from: f */
    private boolean f3604f;

    /* renamed from: g */
    private volatile boolean f3605g;

    /* renamed from: h */
    private ServiceConnection f3606h = new ServiceConnectionC0751d(this);

    /* renamed from: d */
    private Set<Connection> f3602d = new HashSet(10);

    /* compiled from: ConnectionManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.g.c$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0750a {
        /* renamed from: a */
        void mo267a();

        /* renamed from: d */
        void mo284d();

        /* renamed from: e */
        void mo283e();
    }

    public ConnectionManager(Context context, InterfaceC0750a interfaceC0750a) {
        this.context = context;
        this.f3600b = interfaceC0750a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final synchronized Connection m605a(Connection.SspReader sspReader, Connection.C0748c c0748c, Connection.EnumC0746a enumC0746a) {
        Connection connection;
        connection = new Connection(sspReader, c0748c, enumC0746a, this);
        this.f3602d.add(connection);
        HandShaker.debug("ConnectionManager", "startConnection count = " + this.f3602d.size() + ", Connection = " + connection);
        return connection;
    }

    /* renamed from: a */
    public final void m601a(boolean z) {
        HandShaker.debug("ConnectionManager", "endConnection");
        if (this.f3605g) {
            this.connection.m625a(z);
        }
    }

    /* renamed from: a */
    public synchronized void mo244a(Connection connection) {
        HandShaker.debug("ConnectionManager", "onConnected, mIsMediaScannerBound = " + this.f3604f + ", Thread id = " + Thread.currentThread().getId());
        if (this.f3605g) {
            HandShaker.debug("ConnectionManager", "onConnected: has connected");
            mo240b(this.connection);
        }
        HandShaker.debug("ConnectionManager", "onConnecte success, connection count = " + this.f3602d.size());
        this.f3602d.remove(connection);
        this.f3605g = true;
        this.connection = connection;
        FolderApp.f3381e = this.connection.m624b();
        EventManager.getInstance().m797a(connection);
        this.f3600b.mo284d();
        HeartBeatChecker.getInstance().m675a(this.connection.m615i() * 1000);
        HeartBeatChecker.getInstance().m674a(this);
        if (!this.f3604f) {
            this.f3604f = this.context.bindService(new Intent(this.context, MediaScannerService.class), this.f3606h, 1);
            HandShaker.debug("ConnectionManager", "onConnected, mIsMediaScannerBound = " + this.f3604f);
        }
    }

    /* renamed from: b */
    public synchronized void mo240b(Connection connection) {
        HandShaker.debug("ConnectionManager", "onDisconnected, mIsMediaScannerBound = " + this.f3604f + ", mIsConnected = " + this.f3605g);
        if (this.connection != connection) {
            this.f3602d.remove(connection);
            HandShaker.debug("ConnectionManager", "onDisconnected remove, count = " + this.f3602d.size() + ", connection = " + connection);
        } else {
            if (this.f3605g) {
                this.f3605g = false;
                EventManager.getInstance().m797a((Connection) null);
                HeartBeatChecker.getInstance().m672b();
                this.connection = null;
                this.f3600b.mo283e();
            }
            if (this.f3604f) {
                try {
                    this.context.unbindService(this.f3606h);
                    this.f3604f = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public final synchronized void m599c(Connection connection) {
        HandShaker.debug("ConnectionManager", "closeOthers");
        for (Connection connection2 : this.f3602d) {
            if (connection2 != connection) {
                connection2.m621c();
            }
        }
    }

    /* renamed from: f */
    public final Context m598f() {
        if(this.context == null){
            this.context = FolderApp.getInstance().getApplicationContext();
        }
        return this.context;
    }

    /* renamed from: a */
    public final void m603a(String str) {
        if (this.f3601c != null) {
            this.f3601c.m272a(str);
        } else {
            HandShaker.error("ConnectionManager", "mMediaScanner is null, cannot update media store !");
        }
    }

    /* renamed from: a */
    public final void m602a(List<String> list) {
        for (String str : list) {
            m603a(str);
        }
    }

    /* renamed from: g */
    public final boolean m597g() {
        return this.f3605g;
    }

    @Override // com.smartisanos.smartfolder.aoa.p053e.HeartBeatChecker.InterfaceC0740b
    /* renamed from: b */
    public final boolean mo600b() {
        return this.connection != null && this.connection.m619e();
    }

    @Override // com.smartisanos.smartfolder.aoa.p053e.HeartBeatChecker.InterfaceC0740b
    /* renamed from: a */
    public final void mo606a() {
        m601a(true);
    }
}
