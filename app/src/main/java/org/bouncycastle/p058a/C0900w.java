package org.bouncycastle.p058a;

import java.io.IOException;
import java.util.Enumeration;

/* renamed from: org.a.a.w */
/* loaded from: classes.dex */
public final class C0900w extends C0864ax {
    public C0900w() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0900w(C0847ag c0847ag) {
        super(c0847ag);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.C0864ax, org.p057a.p058a.AbstractC0889l, org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) throws IOException {
        if (!(c0860at instanceof C0886i) && !(c0860at instanceof C0897t)) {
            super.mo143a(c0860at);
            return;
        }
        c0860at.write(49);
        c0860at.write(128);
        Enumeration c = m153c();
        while (c.hasMoreElements()) {
            c0860at.mo144a(c.nextElement());
        }
        c0860at.write(0);
        c0860at.write(0);
    }
}
