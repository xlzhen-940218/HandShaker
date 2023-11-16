package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.ak */
/* loaded from: classes.dex */
public final class C0851ak extends AbstractC0883f {

    /* renamed from: a */
    String f4007a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0851ak(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        this.f4007a = new String(cArr);
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0851ak) {
            return this.f4007a.equals(((C0851ak) abstractC0856ap).f4007a);
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return this.f4007a.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        char[] charArray = this.f4007a.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i != charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        try {
            c0860at.m172a(24, bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
