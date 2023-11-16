package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.aw */
/* loaded from: classes.dex */
public final class C0863aw implements InterfaceC0888k {

    /* renamed from: a */
    private C0891n f4016a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0863aw(C0891n c0891n) {
        this.f4016a = c0891n;
    }

    @Override // org.p057a.p058a.InterfaceC0846af
    /* renamed from: a */
    public final AbstractC0856ap mo142a() {
        try {
            return new C0862av(this.f4016a.m151b());
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
