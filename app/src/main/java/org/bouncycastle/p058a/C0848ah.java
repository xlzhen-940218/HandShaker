package org.bouncycastle.p058a;

import org.bouncycastle.util.C0904a;

import java.io.IOException;

/* renamed from: org.a.a.ah */
/* loaded from: classes.dex */
public final class C0848ah extends AbstractC0883f {

    /* renamed from: a */
    byte[] f4003a;

    public C0848ah(byte[] bArr) {
        this.f4003a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        try {
            c0860at.m172a(10, this.f4003a);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0848ah) {
            return C0904a.m139a(this.f4003a, ((C0848ah) abstractC0856ap).f4003a);
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return C0904a.m140a(this.f4003a);
    }
}
