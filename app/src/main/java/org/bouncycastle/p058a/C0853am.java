package org.bouncycastle.p058a;

import java.io.IOException;
import java.math.BigInteger;

/* renamed from: org.a.a.am */
/* loaded from: classes.dex */
public final class C0853am extends AbstractC0883f {

    /* renamed from: a */
    byte[] f4009a;

    public C0853am(BigInteger bigInteger) {
        this.f4009a = bigInteger.toByteArray();
    }

    public C0853am(byte[] bArr) {
        this.f4009a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        try {
            c0860at.m172a(2, this.f4009a);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0853am) {
            C0853am c0853am = (C0853am) abstractC0856ap;
            if (this.f4009a.length == c0853am.f4009a.length) {
                for (int i = 0; i != this.f4009a.length; i++) {
                    if (this.f4009a[i] != c0853am.f4009a[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    /* renamed from: c */
    public final BigInteger m179c() {
        return new BigInteger(1, this.f4009a);
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 != this.f4009a.length; i2++) {
            i ^= (this.f4009a[i2] & 255) << (i2 % 4);
        }
        return i;
    }

    /* renamed from: a */
    public static C0853am m180a(Object obj) {
        AbstractC0856ap abstractC0856ap = (AbstractC0856ap) obj;
        while (abstractC0856ap != null && !(abstractC0856ap instanceof C0853am)) {
            if (abstractC0856ap instanceof AbstractC0884g) {
                return new C0853am(((AbstractC0884g) abstractC0856ap).mo148d());
            }
            if (!(abstractC0856ap instanceof AbstractC0892o)) {
                throw new IllegalArgumentException("illegal object in getInstance: " + abstractC0856ap.getClass().getName());
            }
            AbstractC0892o abstractC0892o = (AbstractC0892o) abstractC0856ap;
            abstractC0856ap = abstractC0892o.f4052d != null ? abstractC0892o.f4052d.mo142a() : null;
        }
        return (C0853am) abstractC0856ap;
    }

    public final String toString() {
        return new BigInteger(this.f4009a).toString();
    }
}
