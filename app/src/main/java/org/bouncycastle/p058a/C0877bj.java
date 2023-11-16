package org.bouncycastle.p058a;

import java.io.IOException;
import java.util.Enumeration;

/* renamed from: org.a.a.bj */
/* loaded from: classes.dex */
public final class C0877bj extends C0862av {

    /* renamed from: a */
    private byte[] f4036a;

    /* renamed from: b */
    private boolean f4037b = false;

    /* renamed from: c */
    private int f4038c = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0877bj(byte[] bArr) {
        this.f4036a = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.C0862av, org.p057a.p058a.AbstractC0887j, org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        try {
            c0860at.m172a(48, this.f4036a);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // org.p057a.p058a.AbstractC0887j
    /* renamed from: c */
    public final Enumeration mo156c() {
        return this.f4037b ? super.mo156c() : new C0876bi(this.f4036a);
    }

    @Override // org.p057a.p058a.AbstractC0887j
    /* renamed from: d */
    public final int mo155d() {
        if (this.f4038c < 0) {
            C0876bi c0876bi = new C0876bi(this.f4036a);
            this.f4038c = 0;
            while (c0876bi.hasMoreElements()) {
                c0876bi.nextElement();
                this.f4038c++;
            }
        }
        return this.f4038c;
    }
}
