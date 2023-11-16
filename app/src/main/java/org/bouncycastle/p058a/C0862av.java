package org.bouncycastle.p058a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;

/* renamed from: org.a.a.av */
/* loaded from: classes.dex */
public class C0862av extends AbstractC0887j {
    public C0862av() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0887j, org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public void mo143a(C0860at c0860at) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C0860at c0860at2 = new C0860at(byteArrayOutputStream);
        Enumeration c = mo156c();
        while (c.hasMoreElements()) {
            c0860at2.mo144a(c.nextElement());
        }
        c0860at2.close();
        c0860at.m172a(48, byteArrayOutputStream.toByteArray());
    }

    public C0862av(C0847ag c0847ag) {
        for (int i = 0; i != c0847ag.f4002a.size(); i++) {
            m157a(c0847ag.m184a(i));
        }
    }
}
