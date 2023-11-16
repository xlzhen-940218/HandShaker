package org.bouncycastle.p058a;

import org.bouncycastle.util.C0904a;

import java.io.IOException;

/* renamed from: org.a.a.be */
/* loaded from: classes.dex */
public final class C0872be extends AbstractC0856ap {

    /* renamed from: a */
    private boolean f4024a;

    /* renamed from: b */
    private int f4025b;

    /* renamed from: c */
    private byte[] f4026c;

    public C0872be(boolean z, int i, byte[] bArr) {
        this.f4024a = z;
        this.f4025b = i;
        this.f4026c = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        try {
            c0860at.m173a(this.f4024a ? 32 : 0, this.f4025b, this.f4026c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final boolean equals(Object obj) {
        if (obj instanceof C0872be) {
            C0872be c0872be = (C0872be) obj;
            return this.f4024a == c0872be.f4024a && this.f4025b == c0872be.f4025b && C0904a.m139a(this.f4026c, c0872be.f4026c);
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return ((this.f4024a ? -1 : 0) ^ this.f4025b) ^ C0904a.m140a(this.f4026c);
    }
}
