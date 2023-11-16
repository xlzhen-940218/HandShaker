package com.smartisanos.smartfolder.aoa.p050b;

import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

/* renamed from: com.smartisanos.smartfolder.aoa.b.h */
/* loaded from: classes.dex */
public final class TrustResponseEvent {

    /* renamed from: a */
    private SmartSyncProtocolProtos.SSPHandShakeTrustType f3469a;

    /* renamed from: b */
    private int f3470b;

    /* renamed from: c */
    private String f3471c;

    public TrustResponseEvent(SmartSyncProtocolProtos.SSPHandShakeTrustType SSPHandShakeTrustType, int i, String str) {
        this.f3469a = SSPHandShakeTrustType;
        this.f3470b = i;
        this.f3471c = str;
    }

    /* renamed from: a */
    public final int m763a() {
        return this.f3470b;
    }

    /* renamed from: b */
    public final String m762b() {
        return this.f3471c;
    }

    /* renamed from: c */
    public final SmartSyncProtocolProtos.SSPHandShakeTrustType m761c() {
        return this.f3469a;
    }
}
