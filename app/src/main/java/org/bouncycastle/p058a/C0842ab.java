package org.bouncycastle.p058a;

import org.bouncycastle.util.C0904a;

import java.io.IOException;

/* renamed from: org.a.a.ab */
/* loaded from: classes.dex */
public final class C0842ab extends AbstractC0883f {

    /* renamed from: a */
    private final boolean f3992a;

    /* renamed from: b */
    private final int f3993b;

    /* renamed from: c */
    private final byte[] f3994c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0842ab(boolean z, int i, byte[] bArr) {
        this.f3992a = z;
        this.f3993b = i;
        this.f3994c = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        try {
            c0860at.m173a(this.f3992a ? 96 : 64, this.f3993b, this.f3994c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0842ab) {
            C0842ab c0842ab = (C0842ab) abstractC0856ap;
            return this.f3992a == c0842ab.f3992a && this.f3993b == c0842ab.f3993b && C0904a.m139a(this.f3994c, c0842ab.f3994c);
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return ((this.f3992a ? 1 : 0) ^ this.f3993b) ^ C0904a.m140a(this.f3994c);
    }
}
