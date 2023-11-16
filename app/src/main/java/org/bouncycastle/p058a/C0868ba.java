package org.bouncycastle.p058a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* renamed from: org.a.a.ba */
/* loaded from: classes.dex */
public class C0868ba extends AbstractC0892o {

    /* renamed from: e */
    private static final byte[] f4019e = new byte[0];

    public C0868ba(boolean z, int i, InterfaceC0846af interfaceC0846af) {
        super(z, i, interfaceC0846af);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0892o, org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public void mo143a(C0860at c0860at) throws IOException {
        byte[] byteArray;
        if (this.f4050b) {
            c0860at.m173a(160, this.f4049a, f4019e);
            return;
        }
        AbstractC0856ap mo142a = this.f4052d.mo142a();
        if ("DER".equals("DER")) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new C0860at(byteArrayOutputStream).mo144a(mo142a);
            byteArray = byteArrayOutputStream.toByteArray();
        } else {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            new C0886i(byteArrayOutputStream2).mo144a(mo142a);
            byteArray = byteArrayOutputStream2.toByteArray();
        }
        if (this.f4051c) {
            c0860at.m173a(160, this.f4049a, byteArray);
            return;
        }
        c0860at.m174a((byteArray[0] & 32) == 0 ? 128 : 160, this.f4049a);
        c0860at.write(byteArray, 1, byteArray.length - 1);
    }
}
