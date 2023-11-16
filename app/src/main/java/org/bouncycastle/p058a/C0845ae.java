package org.bouncycastle.p058a;

import java.io.IOException;

/* renamed from: org.a.a.ae */
/* loaded from: classes.dex */
public final class C0845ae extends AbstractC0883f {

    /* renamed from: b */
    public static final C0845ae f3999b = new C0845ae(false);

    /* renamed from: c */
    public static final C0845ae f4000c = new C0845ae(true);

    /* renamed from: a */
    byte f4001a;

    private C0845ae(boolean z) {
        this.f4001a = z ? (byte) -1 : (byte) 0;
    }

    public C0845ae(byte[] bArr) {
        this.f4001a = bArr[0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        try {
            c0860at.m172a(1, new byte[]{this.f4001a});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    protected final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        return abstractC0856ap != null && (abstractC0856ap instanceof C0845ae) && this.f4001a == ((C0845ae) abstractC0856ap).f4001a;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return this.f4001a;
    }

    public final String toString() {
        return this.f4001a != 0 ? "TRUE" : "FALSE";
    }
}
