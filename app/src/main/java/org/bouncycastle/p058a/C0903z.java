package org.bouncycastle.p058a;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.a.a.z */
/* loaded from: classes.dex */
public final class C0903z implements InterfaceC0893p {

    /* renamed from: a */
    private int f4059a;

    /* renamed from: b */
    private int f4060b;

    /* renamed from: c */
    private InputStream f4061c;

    /* renamed from: d */
    private boolean f4062d;

    /* JADX INFO: Access modifiers changed from: protected */
    public C0903z(int i, int i2, InputStream inputStream) {
        this.f4059a = i;
        this.f4060b = i2;
        this.f4061c = inputStream;
        this.f4062d = inputStream instanceof C0875bh;
    }

    /* renamed from: a */
    private static C0880c m141a(InputStream inputStream) {
        try {
            return new C0891n(inputStream).m151b();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override // org.p057a.p058a.InterfaceC0846af
    /* renamed from: a */
    public final AbstractC0856ap mo142a() {
        if (this.f4062d) {
            C0880c m141a = m141a(this.f4061c);
            return m141a.f4002a.size() == 1 ? new C0902y(true, this.f4060b, m141a.m184a(0)) : new C0902y(false, this.f4060b, C0895r.m146a(m141a));
        }
        if ((this.f4059a & 32) != 0) {
            C0880c m141a2 = m141a(this.f4061c);
            return m141a2.f4002a.size() == 1 ? new C0868ba(true, this.f4060b, m141a2.m184a(0)) : new C0868ba(false, this.f4060b, C0849ai.m182a(m141a2));
        }
        return new C0868ba(false, this.f4060b, new C0858ar(((C0874bg) this.f4061c).m169a()));
    }
}
