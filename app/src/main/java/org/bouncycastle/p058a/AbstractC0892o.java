package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.o */
/* loaded from: classes.dex */
public abstract class AbstractC0892o extends AbstractC0883f implements InterfaceC0893p {

    /* renamed from: a */
    int f4049a;

    /* renamed from: b */
    boolean f4050b = false;

    /* renamed from: c */
    boolean f4051c;

    /* renamed from: d */
    InterfaceC0846af f4052d;

    public AbstractC0892o(boolean z, int i, InterfaceC0846af interfaceC0846af) {
        this.f4051c = true;
        this.f4052d = null;
        if (interfaceC0846af instanceof InterfaceC0839a) {
            this.f4051c = true;
        } else {
            this.f4051c = z;
        }
        this.f4049a = i;
        this.f4052d = interfaceC0846af;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public abstract void mo143a(C0860at c0860at) throws IOException;

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof AbstractC0892o) {
            AbstractC0892o abstractC0892o = (AbstractC0892o) abstractC0856ap;
            if (this.f4049a == abstractC0892o.f4049a && this.f4050b == abstractC0892o.f4050b && this.f4051c == abstractC0892o.f4051c) {
                if (this.f4052d == null) {
                    if (abstractC0892o.f4052d != null) {
                        return false;
                    }
                } else if (!this.f4052d.mo142a().equals(abstractC0892o.f4052d.mo142a())) {
                    return false;
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public int hashCode() {
        int i = this.f4049a;
        return this.f4052d != null ? i ^ this.f4052d.hashCode() : i;
    }

    public String toString() {
        return "[" + this.f4049a + "]" + this.f4052d;
    }
}
