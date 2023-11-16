package org.bouncycastle.p058a;

/* renamed from: org.a.a.b */
/* loaded from: classes.dex */
public abstract class AbstractC0867b implements InterfaceC0846af {
    @Override // org.p057a.p058a.InterfaceC0846af
    /* renamed from: a */
    public final AbstractC0856ap mo142a() {
        return mo171b();
    }

    /* renamed from: b */
    public abstract AbstractC0856ap mo171b();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InterfaceC0846af) {
            return mo171b().equals(((InterfaceC0846af) obj).mo142a());
        }
        return false;
    }

    public int hashCode() {
        return mo171b().hashCode();
    }
}
