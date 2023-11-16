package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.f */
/* loaded from: classes.dex */
public abstract class AbstractC0883f extends AbstractC0856ap {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public abstract void mo143a(C0860at c0860at) throws IOException;

    /* renamed from: a */
    abstract boolean mo150a(AbstractC0856ap abstractC0856ap);

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InterfaceC0846af) && mo150a(((InterfaceC0846af) obj).mo142a());
    }
}
