package org.bouncycastle.p058a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* renamed from: org.a.a.bd */
/* loaded from: classes.dex */
public final class C0871bd extends AbstractC0883f {

    /* renamed from: a */
    private static final char[] f4022a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: b */
    private byte[] f4023b;

    public C0871bd(byte[] bArr) {
        this.f4023b = bArr;
    }

    /* renamed from: c */
    private String m170c() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new C0886i(byteArrayOutputStream).mo144a(this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != byteArray.length; i++) {
                stringBuffer.append(f4022a[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(f4022a[byteArray[i] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new RuntimeException("internal error encoding BitString");
        }
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0871bd) {
            return m170c().equals(((C0871bd) abstractC0856ap).m170c());
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return m170c().hashCode();
    }

    public final String toString() {
        return m170c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        try {
            c0860at.m172a(28, this.f4023b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
