package org.bouncycastle.p058a;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.a.a.d */
/* loaded from: classes.dex */
public final class C0881d extends FilterInputStream {

    /* renamed from: a */
    private final int f4042a;

    /* renamed from: b */
    private final boolean f4043b;

    public C0881d(InputStream inputStream) {
        this(inputStream, (byte) 0);
    }

    private C0881d(InputStream inputStream, byte b) {
        this(inputStream, Integer.MAX_VALUE, false);
    }

    private C0881d(InputStream inputStream, int i, boolean z) {
        super(inputStream);
        this.f4042a = i;
        this.f4043b = z;
    }

    public C0881d(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static int m161a(InputStream inputStream, int i) throws IOException {
        int i2 = i & 31;
        if (i2 == 31) {
            int i3 = 0;
            int read = inputStream.read();
            if ((read & 127) == 0) {
                throw new IOException("corrupted stream - invalid high tag number found");
            }
            while (read >= 0 && (read & 128) != 0) {
                i3 = ((read & 127) | i3) << 7;
                read = inputStream.read();
            }
            if (read < 0) {
                throw new EOFException("EOF found inside tag value.");
            }
            return (read & 127) | i3;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AbstractC0856ap m162a(int i, byte[] bArr) {
        switch (i) {
            case 1:
                return new C0845ae(bArr);
            case 2:
                return new C0853am(bArr);
            case 3:
                byte b = bArr[0];
                byte[] bArr2 = new byte[bArr.length - 1];
                System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
                return new C0844ad(bArr2, b);
            case 4:
                return new C0858ar(bArr);
            case 5:
                return C0854an.f4010a;
            case 6:
                return new C0857aq(bArr);
            case 7:
            case 8:
            case 9:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 21:
            case 25:
            case 29:
            default:
                return new C0872be(false, i, bArr);
            case 10:
                return new C0848ah(bArr);
            case 12:
                return new C0870bc(bArr);
            case 18:
                return new C0855ao(bArr);
            case 19:
                return new C0861au(bArr);
            case 20:
                return new C0866az(bArr);
            case 22:
                return new C0852al(bArr);
            case 23:
                return new C0869bb(bArr);
            case 24:
                return new C0851ak(bArr);
            case 26:
                return new C0873bf(bArr);
            case 27:
                return new C0850aj(bArr);
            case 28:
                return new C0871bd(bArr);
            case 30:
                return new C0843ac(bArr);
        }
    }

    /* renamed from: b */
    private int m159b() {
        try {
            return m158b(this, this.f4042a);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static int m158b(InputStream inputStream, int i) throws IOException {
        int i2 = 0;
        int read = inputStream.read();
        if (read < 0) {
            throw new EOFException("EOF found when length expected");
        }
        if (read == 128) {
            return -1;
        }
        if (read > 127) {
            int i3 = read & 127;
            if (i3 > 4) {
                throw new IOException("DER length more than 4 bytes");
            }
            int i4 = 0;
            while (i2 < i3) {
                int read2 = inputStream.read();
                if (read2 < 0) {
                    throw new EOFException("EOF found reading length");
                }
                i2++;
                i4 = read2 + (i4 << 8);
            }
            if (i4 < 0) {
                throw new IOException("corrupted stream - negative length found");
            }
            if (i4 >= i) {
                throw new IOException("corrupted stream - out of bounds length found");
            }
            return i4;
        }
        return read;
    }

    /* renamed from: a */
    private static C0880c m160a(C0874bg c0874bg) throws IOException {
        C0881d c0881d = new C0881d(c0874bg);
        C0880c c0880c = new C0880c();
        while (true) {
            AbstractC0856ap m163a = c0881d.m163a();
            if (m163a == null) {
                return c0880c;
            }
            c0880c.m183a(m163a);
        }
    }

    /* renamed from: a */
    public final AbstractC0856ap m163a() throws IOException {
        int read = read();
        if (read <= 0) {
            if (read == 0) {
                throw new IOException("unexpected end-of-contents marker");
            }
            return null;
        }
        int m161a = m161a(this, read);
        boolean z = (read & 32) != 0;
        int m159b = m159b();
        if (m159b < 0) {
            if (z) {
                C0875bh c0875bh = new C0875bh(this);
                if ((read & 128) != 0) {
                    return new C0903z(read, m161a, c0875bh).mo142a();
                }
                C0891n c0891n = new C0891n(c0875bh);
                switch (m161a) {
                    case 4:
                        return new C0896s(c0891n).mo142a();
                    case 16:
                        return new C0899v(c0891n).mo142a();
                    case 17:
                        return new C0901x(c0891n).mo142a();
                    default:
                        throw new IOException("unknown BER object encountered");
                }
            }
            throw new IOException("indefinite length primitive encoding encountered");
        }
        boolean z2 = (read & 32) != 0;
        C0874bg c0874bg = new C0874bg(this, m159b);
        if ((read & 64) != 0) {
            return new C0842ab(z2, m161a, c0874bg.m169a());
        }
        if ((read & 128) != 0) {
            return new C0903z(read, m161a, c0874bg).mo142a();
        }
        if (z2) {
            switch (m161a) {
                case 4:
                    return new C0894q(m160a(c0874bg).f4002a);
                case 16:
                    return this.f4043b ? new C0877bj(c0874bg.m169a()) : C0849ai.m182a(m160a(c0874bg));
                case 17:
                    return C0849ai.m181b(m160a(c0874bg));
                default:
                    return new C0872be(true, m161a, c0874bg.m169a());
            }
        }
        return m162a(m161a, c0874bg.m169a());
    }
}
