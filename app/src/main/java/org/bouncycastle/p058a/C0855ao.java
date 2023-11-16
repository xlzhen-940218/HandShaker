package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.ao */
/* loaded from: classes.dex */
public final class C0855ao extends AbstractC0883f {

    /* renamed from: a */
    String f4012a;

    public C0855ao(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        this.f4012a = new String(cArr);
    }

    public final String toString() {
        return this.f4012a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        char[] charArray = this.f4012a.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i != charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        try {
            c0860at.m172a(18, bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return this.f4012a.hashCode();
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0855ao) {
            return this.f4012a.equals(((C0855ao) abstractC0856ap).f4012a);
        }
        return false;
    }
}
