package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.bb */
/* loaded from: classes.dex */
public final class C0869bb extends AbstractC0883f {

    /* renamed from: a */
    String f4020a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0869bb(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) (bArr[i] & 255);
        }
        this.f4020a = new String(cArr);
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0869bb) {
            return this.f4020a.equals(((C0869bb) abstractC0856ap).f4020a);
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return this.f4020a.hashCode();
    }

    public final String toString() {
        return this.f4020a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        char[] charArray = this.f4020a.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i != charArray.length; i++) {
            bArr[i] = (byte) charArray[i];
        }
        try {
            c0860at.m172a(23, bArr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
