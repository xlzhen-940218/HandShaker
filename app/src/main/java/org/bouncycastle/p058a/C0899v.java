package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.v */
/* loaded from: classes.dex */
public final class C0899v implements InterfaceC0888k {

    /* renamed from: a */
    private C0891n f4057a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0899v(C0891n c0891n) {
        this.f4057a = c0891n;
    }

    @Override // org.p057a.p058a.InterfaceC0846af
    /* renamed from: a */
    public final AbstractC0856ap mo142a() {
        try {
            return new C0898u(this.f4057a.m151b());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
