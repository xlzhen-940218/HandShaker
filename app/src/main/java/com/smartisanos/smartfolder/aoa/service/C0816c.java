package com.smartisanos.smartfolder.aoa.service;

import com.smartisan.trackerlib.Agent;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ConnectionManagerService.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.c */
/* loaded from: classes.dex */
final class C0816c extends AbstractC0808a {

    /* renamed from: a */
    final /* synthetic */ ConnectionManagerService f3894a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0816c(ConnectionManagerService connectionManagerService) {
        super(connectionManagerService, (byte) 0);
        this.f3894a = connectionManagerService;
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
        StringBuilder sb = new StringBuilder("onWifiConnectedRunnable, mListener = ");
        interfaceC0810c = this.f3894a.f3862c;
        HandShaker.debug(str, sb.append(interfaceC0810c).toString());
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", 1);
            Agent.m1952a().m1949a("A300003", jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            HandShaker.error("Tracker", "onWifiConnect exception:" + e.toString());
        }
        interfaceC0810c2 = this.f3894a.f3862c;
        if (interfaceC0810c2 != null) {
            interfaceC0810c3 = this.f3894a.f3862c;
            interfaceC0810c3.mo279c();
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
        StringBuilder sb = new StringBuilder("onWifiDisconnectedRunnable, mListener = ");
        interfaceC0810c = this.f3894a.f3862c;
        HandShaker.debug(str, sb.append(interfaceC0810c).toString());
        interfaceC0810c2 = this.f3894a.f3862c;
        if (interfaceC0810c2 != null) {
            interfaceC0810c3 = this.f3894a.f3862c;
            interfaceC0810c3.mo278d();
        }
    }
}
