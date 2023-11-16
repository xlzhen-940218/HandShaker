package org.bouncycastle.p058a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.bouncycastle.util.C0904a;

/* renamed from: org.a.a.ad */
/* loaded from: classes.dex */
public final class C0844ad extends AbstractC0883f {

    /* renamed from: c */
    private static final char[] f3996c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    protected byte[] f3997a;

    /* renamed from: b */
    protected int f3998b;

    public C0844ad(byte[] bArr, int i) {
        this.f3997a = bArr;
        this.f3998b = i;
    }

    /* renamed from: c */
    private String m185c() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new C0886i(byteArrayOutputStream).mo144a(this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != byteArray.length; i++) {
                stringBuffer.append(f3996c[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(f3996c[byteArray[i] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new RuntimeException("internal error encoding BitString");
        }
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    protected final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0844ad) {
            C0844ad c0844ad = (C0844ad) abstractC0856ap;
            return this.f3998b == c0844ad.f3998b && C0904a.m139a(this.f3997a, c0844ad.f3997a);
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return this.f3998b ^ C0904a.m140a(this.f3997a);
    }

    public final String toString() {
        return m185c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        byte[] bArr = new byte[this.f3997a.length + 1];
        bArr[0] = (byte) this.f3998b;
        System.arraycopy(this.f3997a, 0, bArr, 1, bArr.length - 1);
        try {
            c0860at.m172a(3, bArr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
