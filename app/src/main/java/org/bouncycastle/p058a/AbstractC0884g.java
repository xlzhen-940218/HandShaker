package org.bouncycastle.p058a;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.C0904a;
import org.bouncycastle.util.encoders.Hex;

/* renamed from: org.a.a.g */
/* loaded from: classes.dex */
public abstract class AbstractC0884g extends AbstractC0883f implements InterfaceC0885h {

    /* renamed from: a */
    byte[] f4044a;

    public AbstractC0884g(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException("string cannot be null");
        }
        this.f4044a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public abstract void mo143a(C0860at c0860at) throws IOException;

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof AbstractC0884g) {
            byte[] bArr = ((AbstractC0884g) abstractC0856ap).f4044a;
            byte[] bArr2 = this.f4044a;
            if (bArr.length == bArr2.length) {
                for (int i = 0; i != bArr.length; i++) {
                    if (bArr[i] != bArr2[i]) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // org.p057a.p058a.InterfaceC0885h
    /* renamed from: c */
    public final InputStream mo145c() {
        return new ByteArrayInputStream(this.f4044a);
    }

    /* renamed from: d */
    public byte[] mo148d() {
        return this.f4044a;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public int hashCode() {
        return C0904a.m140a(mo148d());
    }

    public String toString() {
        return "#" + new String(Hex.m134a(this.f4044a));
    }
}
