package org.bouncycastle.p058a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

/* renamed from: org.a.a.q */
/* loaded from: classes.dex */
public final class C0894q extends C0858ar {

    /* renamed from: b */
    private Vector f4053b;

    public C0894q(Vector vector) {
        super(m149a(vector));
        this.f4053b = vector;
    }

    public C0894q(byte[] bArr) {
        super(bArr);
    }

    /* renamed from: a */
    private static byte[] m149a(Vector vector) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 == vector.size()) {
                return byteArrayOutputStream.toByteArray();
            }
            try {
                byteArrayOutputStream.write(((C0858ar) vector.elementAt(i2)).mo148d());
                i = i2 + 1;
            } catch (IOException e) {
                throw new IllegalArgumentException("exception converting octets " + e.toString());
            } catch (ClassCastException e2) {
                throw new IllegalArgumentException(vector.elementAt(i2).getClass().getName() + " found in input should only contain DEROctetString");
            }
        }
    }

    @Override // org.p057a.p058a.C0858ar, org.p057a.p058a.AbstractC0884g, org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) throws IOException {
        if (!(c0860at instanceof C0886i) && !(c0860at instanceof C0897t)) {
            super.mo143a(c0860at);
            return;
        }
        c0860at.write(36);
        c0860at.write(128);
        if (this.f4053b != null) {
            for (int i = 0; i != this.f4053b.size(); i++) {
                c0860at.mo144a(this.f4053b.elementAt(i));
            }
        } else {
            for (int i2 = 0; i2 < this.f4044a.length; i2 += 1000) {
                byte[] bArr = new byte[(i2 + 1000 > this.f4044a.length ? this.f4044a.length : i2 + 1000) - i2];
                System.arraycopy(this.f4044a, i2, bArr, 0, bArr.length);
                c0860at.mo144a(new C0858ar(bArr));
            }
        }
        c0860at.write(0);
        c0860at.write(0);
    }

    @Override // org.p057a.p058a.AbstractC0884g
    /* renamed from: d */
    public final byte[] mo148d() {
        return this.f4044a;
    }

    /* renamed from: e */
    public final Enumeration m147e() {
        if (this.f4053b == null) {
            Vector vector = new Vector();
            int i = 0;
            for (int i2 = 0; i2 + 1 < this.f4044a.length; i2++) {
                if (this.f4044a[i2] == 0 && this.f4044a[i2 + 1] == 0) {
                    byte[] bArr = new byte[(i2 - i) + 1];
                    System.arraycopy(this.f4044a, i, bArr, 0, bArr.length);
                    vector.addElement(new C0858ar(bArr));
                    i = i2 + 1;
                }
            }
            byte[] bArr2 = new byte[this.f4044a.length - i];
            System.arraycopy(this.f4044a, i, bArr2, 0, bArr2.length);
            vector.addElement(new C0858ar(bArr2));
            return vector.elements();
        }
        return this.f4053b.elements();
    }
}
