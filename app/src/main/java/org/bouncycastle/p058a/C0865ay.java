package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.ay */
/* loaded from: classes.dex */
public final class C0865ay implements InterfaceC0890m {

    /* renamed from: a */
    private C0891n f4017a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0865ay(C0891n c0891n) {
        this.f4017a = c0891n;
    }

    @Override // org.p057a.p058a.InterfaceC0846af
    /* renamed from: a */
    public final AbstractC0856ap mo142a() {
        try {
            return new C0864ax(this.f4017a.m151b());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
