package org.bouncycastle.p058a;

import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.p062b.C0910a;

/* renamed from: org.a.a.s */
/* loaded from: classes.dex */
public final class C0896s implements InterfaceC0885h {

    /* renamed from: a */
    private C0891n f4056a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0896s(C0891n c0891n) {
        this.f4056a = c0891n;
    }

    @Override // org.p057a.p058a.InterfaceC0846af
    /* renamed from: a */
    public final AbstractC0856ap mo142a() {
        return new C0894q(C0910a.m128a(mo145c()));
    }

    @Override // org.p057a.p058a.InterfaceC0885h
    /* renamed from: c */
    public final InputStream mo145c() {
        return new C0841aa(this.f4056a);
    }
}
