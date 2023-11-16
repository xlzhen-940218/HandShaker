package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.az */
/* loaded from: classes.dex */
public final class C0866az extends AbstractC0883f {

    /* renamed from: a */
    String f4018a;

    public C0866az(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        this.f4018a = new String(cArr);
    }

    public final String toString() {
        return this.f4018a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        char[] charArray = this.f4018a.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i != charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        try {
            c0860at.m172a(20, bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0866az) {
            return this.f4018a.equals(((C0866az) abstractC0856ap).f4018a);
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return this.f4018a.hashCode();
    }
}
