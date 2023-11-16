package com.smartisanos.smartfolder.aoa.p056h;

import android.view.View;
import com.smartisanos.smartfolder.aoa.p050b.TrustResponseEvent;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import org.greenrobot.eventbus.EventBus;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DialogUtils.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.m */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0775m implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ int f3740a;

    /* renamed from: b */
    final /* synthetic */ String f3741b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0775m(int i, String str) {
        this.f3740a = i;
        this.f3741b = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        EventBus.getDefault().post(new TrustResponseEvent(SmartSyncProtocolProtos.SSPHandShakeTrustType.TRUST_NO, this.f3740a, this.f3741b));
    }
}
