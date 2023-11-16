package com.smartisanos.smartfolder.aoa.service;

import com.smartisan.trackerlib.Agent;
import com.smartisanos.smartfolder.aoa.p050b.QuitEvent;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ConnectionManagerService.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.b */
/* loaded from: classes.dex */
final class C0815b extends AbstractC0808a {

    /* renamed from: a */
    final /* synthetic */ ConnectionManagerService f3893a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0815b(ConnectionManagerService connectionManagerService) {
        super(connectionManagerService, (byte) 0);
        this.f3893a = connectionManagerService;
    }

    @Override // com.smartisanos.smartfolder.aoa.service.ConnectionManagerService.AbstractC0808a, com.smartisanos.smartfolder.aoa.p055g.ConnectionManager.InterfaceC0750a
    /* renamed from: a */
    public final void mo267a() {
        WifiConnectionManager wifiConnectionManager;
        WifiConnectionManager wifiConnectionManager2;
        wifiConnectionManager = this.f3893a.wifiConnectionManager;
        if (wifiConnectionManager.m597g()) {
            EventBus.getDefault().post(new QuitEvent());
        }
        wifiConnectionManager2 = this.f3893a.wifiConnectionManager;
        wifiConnectionManager2.m236d();
    }

    @Override // com.smartisanos.smartfolder.aoa.service.ConnectionManagerService.AbstractC0808a
    /* renamed from: b */
    public final void mo266b() {
        String str;
        ConnectionManagerService.InterfaceC0810c interfaceC0810c;
        ConnectionManagerService.InterfaceC0810c interfaceC0810c2;
        ConnectionManagerService.InterfaceC0810c interfaceC0810c3;
        super.mo266b();
        str = ConnectionManagerService.TAG;
        StringBuilder sb = new StringBuilder("onUsbConnectedRunnable, mListener = ");
        interfaceC0810c = this.f3893a.f3862c;
        HandShaker.debug(str, sb.append(interfaceC0810c).toString());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", 0);
            Agent.m1952a().m1949a("A300003", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            HandShaker.error("Tracker", "onUsbConnect exception:" + e.toString());
        }
        interfaceC0810c2 = this.f3893a.f3862c;
        if (interfaceC0810c2 != null) {
            interfaceC0810c3 = this.f3893a.f3862c;
            interfaceC0810c3.mo281a();
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.service.ConnectionManagerService.AbstractC0808a
    /* renamed from: c */
    public final void mo265c() {
        String str;
        ConnectionManagerService.InterfaceC0810c interfaceC0810c;
        ConnectionManagerService.InterfaceC0810c interfaceC0810c2;
        ConnectionManagerService.InterfaceC0810c interfaceC0810c3;
        super.mo265c();
        str = ConnectionManagerService.TAG;
        StringBuilder sb = new StringBuilder("onUsbDisconnectedRunnable, mListener = ");
        interfaceC0810c = this.f3893a.f3862c;
        HandShaker.debug(str, sb.append(interfaceC0810c).toString());
        interfaceC0810c2 = this.f3893a.f3862c;
        if (interfaceC0810c2 != null) {
            interfaceC0810c3 = this.f3893a.f3862c;
            interfaceC0810c3.mo280b();
        }
    }
}
