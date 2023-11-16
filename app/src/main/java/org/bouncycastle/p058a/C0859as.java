package org.bouncycastle.p058a;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.a.a.as */
/* loaded from: classes.dex */
public final class C0859as implements InterfaceC0885h {

    /* renamed from: a */
    private C0874bg f4014a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0859as(C0874bg c0874bg) {
        this.f4014a = c0874bg;
    }

    @Override // org.p057a.p058a.InterfaceC0846af
    /* renamed from: a */
    public final AbstractC0856ap mo142a() {
        return new C0858ar(this.f4014a.m169a());
    }

    @Override // org.p057a.p058a.InterfaceC0885h
    /* renamed from: c */
    public final InputStream mo145c() {
        return this.f4014a;
    }
}
