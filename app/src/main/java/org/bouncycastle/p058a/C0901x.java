package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.x */
/* loaded from: classes.dex */
public final class C0901x implements InterfaceC0890m {

    /* renamed from: a */
    private C0891n f4058a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0901x(C0891n c0891n) {
        this.f4058a = c0891n;
    }

    @Override // org.p057a.p058a.InterfaceC0846af
    /* renamed from: a */
    public final AbstractC0856ap mo142a() {
        try {
            return new C0900w(this.f4058a.m151b());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
