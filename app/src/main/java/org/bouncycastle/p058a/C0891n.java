package org.bouncycastle.p058a;

import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.a.a.n */
/* loaded from: classes.dex */
public final class C0891n {

    /* renamed from: a */
    private final InputStream f4047a;

    /* renamed from: b */
    private final int f4048b;

    public C0891n(InputStream inputStream) {
        this(inputStream, (byte) 0);
    }

    private C0891n(InputStream inputStream, byte b) {
        this.f4047a = inputStream;
        this.f4048b = Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public final C0880c m151b() throws IOException {
        C0880c c0880c = new C0880c();
        while (true) {
            InterfaceC0846af m152a = m152a();
            if (m152a == null) {
                return c0880c;
            }
            c0880c.m183a(m152a.mo142a());
        }
    }

    /* renamed from: a */
    public final InterfaceC0846af m152a() throws IOException {
        int read = this.f4047a.read();
        if (read == -1) {
            return null;
        }
        if (this.f4047a instanceof C0875bh) {
            ((C0875bh) this.f4047a).m168a(false);
        }
        int m161a = C0881d.m161a(this.f4047a, read);
        boolean z = (read & 32) != 0;
        int m158b = C0881d.m158b(this.f4047a, this.f4048b);
        if (m158b < 0) {
            if (z) {
                C0875bh c0875bh = new C0875bh(this.f4047a);
                if ((read & 128) != 0) {
                    return new C0903z(read, m161a, c0875bh);
                }
                C0891n c0891n = new C0891n(c0875bh);
                switch (m161a) {
                    case 4:
                        return new C0896s(c0891n);
                    case 16:
                        return new C0899v(c0891n);
                    case 17:
                        return new C0901x(c0891n);
                    default:
                        throw new IOException("unknown BER object encountered");
                }
            }
            throw new IOException("indefinite length primitive encoding encountered");
        }
        C0874bg c0874bg = new C0874bg(this.f4047a, m158b);
        if ((read & 64) != 0) {
            return new C0842ab(z, m161a, c0874bg.m169a());
        }
        if ((read & 128) != 0) {
            return new C0903z(read, m161a, c0874bg);
        }
        if (!z) {
            switch (m161a) {
                case 4:
                    return new C0859as(c0874bg);
                default:
                    return C0881d.m162a(m161a, c0874bg.m169a());
            }
        }
        switch (m161a) {
            case 4:
                return new C0896s(new C0891n(c0874bg));
            case 16:
                return new C0863aw(new C0891n(c0874bg));
            case 17:
                return new C0865ay(new C0891n(c0874bg));
            default:
                return new C0872be(true, m161a, c0874bg.m169a());
        }
    }
}
