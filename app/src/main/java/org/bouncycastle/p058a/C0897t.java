package org.bouncycastle.p058a;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.a.a.t */
/* loaded from: classes.dex */
public final class C0897t extends C0860at {
    public C0897t(OutputStream outputStream) {
        super(outputStream);
    }

    @Override // org.p057a.p058a.C0860at
    /* renamed from: a */
    public final void mo144a(Object obj) throws IOException {
        if (obj == null) {
            m176a();
        } else if (obj instanceof AbstractC0856ap) {
            ((AbstractC0856ap) obj).mo143a(this);
        } else if (!(obj instanceof InterfaceC0846af)) {
            throw new IOException("object not BEREncodable");
        } else {
            ((InterfaceC0846af) obj).mo142a().mo143a(this);
        }
    }
}
