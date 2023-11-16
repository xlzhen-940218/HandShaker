package org.bouncycastle.p058a;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* renamed from: org.a.a.l */
/* loaded from: classes.dex */
public abstract class AbstractC0889l extends AbstractC0883f {

    /* renamed from: a */
    protected Vector f4046a = new Vector();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m154a(InterfaceC0846af interfaceC0846af) {
        this.f4046a.addElement(interfaceC0846af);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public abstract void mo143a(C0860at c0860at) throws IOException;

    /* renamed from: c */
    public final Enumeration m153c() {
        return this.f4046a.elements();
    }

    public String toString() {
        return this.f4046a.toString();
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public int hashCode() {
        Enumeration elements = this.f4046a.elements();
        int size = this.f4046a.size();
        while (elements.hasMoreElements()) {
            Object nextElement = elements.nextElement();
            size *= 17;
            if (nextElement != null) {
                size ^= nextElement.hashCode();
            }
        }
        return size;
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof AbstractC0889l) {
            AbstractC0889l abstractC0889l = (AbstractC0889l) abstractC0856ap;
            if (this.f4046a.size() != abstractC0889l.f4046a.size()) {
                return false;
            }
            Enumeration elements = this.f4046a.elements();
            Enumeration elements2 = abstractC0889l.f4046a.elements();
            while (elements.hasMoreElements()) {
                AbstractC0856ap mo142a = ((InterfaceC0846af) elements.nextElement()).mo142a();
                AbstractC0856ap mo142a2 = ((InterfaceC0846af) elements2.nextElement()).mo142a();
                if (mo142a != mo142a2 && (mo142a == null || !mo142a.equals(mo142a2))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
