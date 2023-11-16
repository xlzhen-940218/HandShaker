package org.bouncycastle.p058a;

import java.io.IOException;
import java.util.Enumeration;

/* renamed from: org.a.a.bi */
/* loaded from: classes.dex */
final class C0876bi implements Enumeration {

    /* renamed from: a */
    private C0881d f4034a;

    /* renamed from: b */
    private Object f4035b = m167a();

    public C0876bi(byte[] bArr) {
        this.f4034a = new C0881d(bArr);
    }

    /* renamed from: a */
    private Object m167a() {
        try {
            return this.f4034a.m163a();
        } catch (IOException e) {
            throw new IllegalStateException("malformed DER construction: " + e);
        }
    }

    @Override // java.util.Enumeration
    public final boolean hasMoreElements() {
        return this.f4035b != null;
    }

    @Override // java.util.Enumeration
    public final Object nextElement() {
        Object obj = this.f4035b;
        this.f4035b = m167a();
        return obj;
    }
}
