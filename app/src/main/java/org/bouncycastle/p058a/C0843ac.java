package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.ac */
/* loaded from: classes.dex */
public final class C0843ac extends AbstractC0883f {

    /* renamed from: a */
    String f3995a;

    public C0843ac(byte[] bArr) {
        char[] cArr = new char[bArr.length / 2];
        for (int i = 0; i != cArr.length; i++) {
            cArr[i] = (char) ((bArr[i * 2] << 8) | (bArr[(i * 2) + 1] & 255));
        }
        this.f3995a = new String(cArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        char[] charArray = this.f3995a.toCharArray();
        byte[] bArr = new byte[charArray.length * 2];
        for (int i = 0; i != charArray.length; i++) {
            bArr[i * 2] = (byte) (charArray[i] >> '\b');
            bArr[(i * 2) + 1] = (byte) charArray[i];
        }
        try {
            c0860at.m172a(30, bArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final String toString() {
        return this.f3995a;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return this.f3995a.hashCode();
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    protected final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0843ac) {
            return this.f3995a.equals(((C0843ac) abstractC0856ap).f3995a);
        }
        return false;
    }
}
