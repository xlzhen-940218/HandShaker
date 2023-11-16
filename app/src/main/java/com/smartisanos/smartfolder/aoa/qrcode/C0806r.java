package com.smartisanos.smartfolder.aoa.qrcode;

import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

/* compiled from: ScanActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.r */
/* loaded from: classes.dex */
final class C0806r implements MainScanView.InterfaceC0788a {

    /* renamed from: a */
    final /* synthetic */ ScanActivity f3858a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0806r(ScanActivity scanActivity) {
        this.f3858a = scanActivity;
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.MainScanView.InterfaceC0788a
    /* renamed from: a */
    public final void mo303a() {
        HandShaker.debug("ScanActivity", "Torch on");
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.MainScanView.InterfaceC0788a
    /* renamed from: b */
    public final void mo302b() {
        HandShaker.debug("ScanActivity", "Torch off");
    }
}
