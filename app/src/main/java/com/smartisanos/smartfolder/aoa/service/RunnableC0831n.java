package com.smartisanos.smartfolder.aoa.service;

import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;

/* compiled from: WifiConnectionManager.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.n */
/* loaded from: classes.dex */
final class RunnableC0831n implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f3927a;

    /* renamed from: b */
    final /* synthetic */ String f3928b;

    /* renamed from: c */
    final /* synthetic */ int f3929c;

    /* renamed from: d */
    final /* synthetic */ WifiConnectionManager.InterfaceC0828a f3930d;

    /* renamed from: e */
    final /* synthetic */ WifiConnectionManager f3931e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0831n(WifiConnectionManager wifiConnectionManager, String str, String str2, int i, WifiConnectionManager.InterfaceC0828a interfaceC0828a) {
        this.f3931e = wifiConnectionManager;
        this.f3927a = str;
        this.f3928b = str2;
        this.f3929c = i;
        this.f3930d = interfaceC0828a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        HandShaker.info("QRCode, pcInfo:" + this.f3927a + " local:" + this.f3928b + ":" + this.f3929c);
        boolean m506a = CommonUtils.m506a(this.f3927a, this.f3928b, this.f3929c);
        if (this.f3930d != null) {
            this.f3930d.mo229a(m506a);
        }
    }
}
