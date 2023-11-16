package com.smartisanos.smartfolder.aoa;

import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.service.ConnectionManagerService;

/* compiled from: MainActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.b */
/* loaded from: classes.dex */
final class C0720b implements ConnectionManagerService.InterfaceC0810c {

    /* renamed from: a */
    final /* synthetic */ MainActivity f3464a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0720b(MainActivity mainActivity) {
        this.f3464a = mainActivity;
    }

    @Override // com.smartisanos.smartfolder.aoa.service.ConnectionManagerService.InterfaceC0810c
    /* renamed from: a */
    public final void mo281a() {
        HandShaker.debug("MainActivity", "onUsbConnected");
        this.f3464a.m804j();
    }

    @Override // com.smartisanos.smartfolder.aoa.service.ConnectionManagerService.InterfaceC0810c
    /* renamed from: b */
    public final void mo280b() {
        HandShaker.debug("MainActivity", "onUsbDisConnected");
        this.f3464a.m817b();
    }

    @Override // com.smartisanos.smartfolder.aoa.service.ConnectionManagerService.InterfaceC0810c
    /* renamed from: c */
    public final void mo279c() {
        HandShaker.debug("MainActivity", "onWifiConnected");
    }

    @Override // com.smartisanos.smartfolder.aoa.service.ConnectionManagerService.InterfaceC0810c
    /* renamed from: d */
    public final void mo278d() {
        ConnectionManagerService connectionManagerService;
        HandShaker.debug("MainActivity", "onWifiDisConnected");
        connectionManagerService = this.f3464a.f3391h;
        if (!connectionManagerService.m297b()) {
            this.f3464a.m817b();
        }
    }
}
