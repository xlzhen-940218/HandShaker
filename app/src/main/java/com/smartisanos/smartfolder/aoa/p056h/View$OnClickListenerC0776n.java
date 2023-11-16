package com.smartisanos.smartfolder.aoa.p056h;

import android.view.View;
import com.smartisanos.smartfolder.aoa.p050b.TrustResponseEvent;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import org.greenrobot.eventbus.EventBus;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DialogUtils.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.n */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0776n implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ int f3742a;

    /* renamed from: b */
    final /* synthetic */ String f3743b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0776n(int i, String str) {
        this.f3742a = i;
        this.f3743b = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        EventBus.getDefault().post(new TrustResponseEvent(SmartSyncProtocolProtos.SSPHandShakeTrustType.TRUST_ONCE, this.f3742a, this.f3743b));
    }
}
