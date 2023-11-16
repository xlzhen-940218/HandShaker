package org.bouncycastle.p058a;

import java.io.IOException;
import java.util.Enumeration;

/* renamed from: org.a.a.y */
/* loaded from: classes.dex */
public final class C0902y extends C0868ba {
    public C0902y(boolean z, int i, InterfaceC0846af interfaceC0846af) {
        super(z, i, interfaceC0846af);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.C0868ba, org.p057a.p058a.AbstractC0892o, org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) throws IOException {
        Enumeration m153c;
        if (!(c0860at instanceof C0886i) && !(c0860at instanceof C0897t)) {
            super.mo143a(c0860at);
            return;
        }
        c0860at.m174a(160, this.f4049a);
        c0860at.write(128);
        if (!this.f4050b) {
            if (this.f4051c) {
                c0860at.mo144a(this.f4052d);
            } else {
                if (this.f4052d instanceof AbstractC0884g) {
                    m153c = this.f4052d instanceof C0894q ? ((C0894q) this.f4052d).m147e() : new C0894q(((AbstractC0884g) this.f4052d).mo148d()).m147e();
                } else if (this.f4052d instanceof AbstractC0887j) {
                    m153c = ((AbstractC0887j) this.f4052d).mo156c();
                } else if (!(this.f4052d instanceof AbstractC0889l)) {
                    throw new RuntimeException("not implemented: " + this.f4052d.getClass().getName());
                } else {
                    m153c = ((AbstractC0889l) this.f4052d).m153c();
                }
                while (m153c.hasMoreElements()) {
                    c0860at.mo144a(m153c.nextElement());
                }
            }
        }
        c0860at.write(0);
        c0860at.write(0);
    }
}
