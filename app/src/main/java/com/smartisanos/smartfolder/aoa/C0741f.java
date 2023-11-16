package com.smartisanos.smartfolder.aoa;

import com.smartisanos.smartfolder.aoa.MainActivity;
import com.smartisanos.smartfolder.aoa.service.WifiConnectionManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.f */
/* loaded from: classes.dex */
public final class C0741f implements WifiConnectionManager.InterfaceC0828a {

    /* renamed from: a */
    final /* synthetic */ MainActivity f3556a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0741f(MainActivity mainActivity) {
        this.f3556a = mainActivity;
    }

    @Override // com.smartisanos.smartfolder.aoa.service.WifiConnectionManager.InterfaceC0828a
    /* renamed from: a */
    public final void mo229a(boolean z) {
        MainActivity.HandlerC0703b handlerC0703b;
        handlerC0703b = this.f3556a.f3400q;
        handlerC0703b.post(new RunnableC0745g(this, z));
    }
}
