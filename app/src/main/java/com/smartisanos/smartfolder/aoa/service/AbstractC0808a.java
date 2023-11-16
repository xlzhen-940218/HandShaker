package com.smartisanos.smartfolder.aoa.service;

import com.smartisanos.smartfolder.aoa.p050b.StartWifiServerEvent;
import com.smartisanos.smartfolder.aoa.p055g.ConnectionManager;
import com.smartisanos.smartfolder.aoa.p056h.ActivityLifecycleManager;
import com.smartisanos.smartfolder.aoa.p056h.MediaDataProvider;

import org.greenrobot.eventbus.EventBus;

public abstract class AbstractC0808a implements ConnectionManager.InterfaceC0750a {

    /* renamed from: a */
    private Runnable f3872a;

    /* renamed from: c */
    private Runnable f3874c;
    private ConnectionManagerService connectionManagerService;

    private AbstractC0808a(ConnectionManagerService connectionManagerService) {
        this.connectionManagerService = connectionManagerService;
        this.f3872a = new RunnableC0819f(connectionManagerService,this);
        this.f3874c = new RunnableC0820g(connectionManagerService,this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ AbstractC0808a(ConnectionManagerService connectionManagerService, byte b) {
        this(connectionManagerService);
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.ConnectionManager.InterfaceC0750a
    /* renamed from: a */
    public void mo267a() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo266b() {
        ConnectionManagerService.m287g(connectionManagerService);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public void mo265c() {
        connectionManagerService.stopForeground(true);
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.ConnectionManager.InterfaceC0750a
    /* renamed from: d */
    public final void mo284d() {
        MediaDataProvider.m401a().m395b();
        connectionManagerService.m301a();
        connectionManagerService.f3865f.post(this.f3872a);
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.ConnectionManager.InterfaceC0750a
    /* renamed from: e */
    public final void mo283e() {
        MediaDataProvider.m401a().m392c();
        if (ActivityLifecycleManager.getInstance().activitiesIsEmpty()) {
            connectionManagerService.m301a();
        } else {
            EventBus.getDefault().post(new StartWifiServerEvent());
        }
        connectionManagerService.f3865f.post(this.f3874c);
    }
}