package org.bouncycastle.p058a.p059a;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.p058a.AbstractC0856ap;
import org.bouncycastle.p058a.AbstractC0867b;
import org.bouncycastle.p058a.AbstractC0887j;
import org.bouncycastle.p058a.C0853am;
import org.bouncycastle.p058a.C0862av;
import org.bouncycastle.p058a.C0880c;

/* renamed from: org.a.a.a.a */
/* loaded from: classes.dex */
public final class C0840a extends AbstractC0867b {

    /* renamed from: a */
    private BigInteger f3987a;

    /* renamed from: b */
    private BigInteger f3988b;

    public C0840a(AbstractC0887j abstractC0887j) {
        if (abstractC0887j.mo155d() != 2) {
            throw new IllegalArgumentException("Bad sequence size: " + abstractC0887j.mo155d());
        }
        Enumeration mo156c = abstractC0887j.mo156c();
        this.f3987a = C0853am.m180a(mo156c.nextElement()).m179c();
        this.f3988b = C0853am.m180a(mo156c.nextElement()).m179c();
    }

    /* renamed from: c */
    public final BigInteger m187c() {
        return this.f3987a;
    }

    /* renamed from: d */
    public final BigInteger m186d() {
        return this.f3988b;
    }

    @Override // org.p057a.p058a.AbstractC0867b
    /* renamed from: b */
    public final AbstractC0856ap mo171b() {
        C0880c c0880c = new C0880c();
        c0880c.m183a(new C0853am(this.f3987a));
        c0880c.m183a(new C0853am(this.f3988b));
        return new C0862av(c0880c);
    }
}
