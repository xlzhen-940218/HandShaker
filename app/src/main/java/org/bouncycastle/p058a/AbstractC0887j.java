package org.bouncycastle.p058a;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* renamed from: org.a.a.j */
/* loaded from: classes.dex */
public abstract class AbstractC0887j extends AbstractC0883f {

    /* renamed from: a */
    private Vector f4045a = new Vector();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m157a(InterfaceC0846af interfaceC0846af) {
        this.f4045a.addElement(interfaceC0846af);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public abstract void mo143a(C0860at c0860at) throws IOException;

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof AbstractC0887j) {
            AbstractC0887j abstractC0887j = (AbstractC0887j) abstractC0856ap;
            if (mo155d() != abstractC0887j.mo155d()) {
                return false;
            }
            Enumeration mo156c = mo156c();
            Enumeration mo156c2 = abstractC0887j.mo156c();
            while (mo156c.hasMoreElements()) {
                AbstractC0856ap mo142a = ((InterfaceC0846af) mo156c.nextElement()).mo142a();
                AbstractC0856ap mo142a2 = ((InterfaceC0846af) mo156c2.nextElement()).mo142a();
                if (mo142a != mo142a2 && (mo142a == null || !mo142a.equals(mo142a2))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: c */
    public Enumeration mo156c() {
        return this.f4045a.elements();
    }

    /* renamed from: d */
    public int mo155d() {
        return this.f4045a.size();
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public int hashCode() {
        Enumeration mo156c = mo156c();
        int mo155d = mo155d();
        while (mo156c.hasMoreElements()) {
            Object nextElement = mo156c.nextElement();
            mo155d *= 17;
            if (nextElement != null) {
                mo155d ^= nextElement.hashCode();
            }
        }
        return mo155d;
    }

    public String toString() {
        return this.f4045a.toString();
    }
}
