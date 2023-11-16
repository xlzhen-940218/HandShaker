package com.smartisanos.smartfolder.aoa.p050b;

import com.smartisanos.smartfolder.aoa.p055g.Connection;

/* renamed from: com.smartisanos.smartfolder.aoa.b.g */
/* loaded from: classes.dex */
public final class TrustRequestEvent {

    /* renamed from: a */
    private Connection f3466a;

    /* renamed from: b */
    private int f3467b;

    /* renamed from: c */
    private String f3468c;

    public TrustRequestEvent(Connection connection, int i, String str) {
        this.f3466a = connection;
        this.f3467b = i;
        this.f3468c = str;
    }

    /* renamed from: a */
    public final Connection m766a() {
        return this.f3466a;
    }

    /* renamed from: b */
    public final int m765b() {
        return this.f3467b;
    }

    /* renamed from: c */
    public final String m764c() {
        return this.f3468c;
    }
}
