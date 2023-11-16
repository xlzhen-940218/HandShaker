package org.bouncycastle.p058a;

import java.io.InputStream;

/* renamed from: org.a.a.bk */
/* loaded from: classes.dex */
abstract class AbstractC0878bk extends InputStream {

    /* renamed from: a */
    protected final InputStream f4039a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractC0878bk(InputStream inputStream) {
        this.f4039a = inputStream;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public final void m166b() {
        if (this.f4039a instanceof C0875bh) {
            ((C0875bh) this.f4039a).m168a(true);
        }
    }
}
