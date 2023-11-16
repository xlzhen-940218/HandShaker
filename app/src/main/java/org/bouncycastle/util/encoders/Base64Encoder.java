package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.a.b.a.b */
/* loaded from: classes.dex */
public final class Base64Encoder implements Encoder {

    /* renamed from: a */
    protected final byte[] f4064a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};

    /* renamed from: b */
    protected byte f4065b = 61;

    /* renamed from: c */
    protected final byte[] f4066c = new byte[128];

    public Base64Encoder() {
        m137a();
    }

    /* renamed from: a */
    private static int m135a(byte[] bArr, int i, int i2) {
        while (i < i2 && m136a((char) bArr[i])) {
            i++;
        }
        return i;
    }

    /* renamed from: a */
    private void m137a() {
        for (int i = 0; i < this.f4064a.length; i++) {
            this.f4066c[this.f4064a[i]] = (byte) i;
        }
    }

    /* renamed from: a */
    private static boolean m136a(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    @Override // org.p057a.p060b.p061a.InterfaceC0907c
    /* renamed from: a */
    public final int mo130a(byte[] bArr, int i, OutputStream outputStream) throws IOException {
        int i2 = i % 3;
        int i3 = i - i2;
        for (int i4 = 0; i4 < i3 + 0; i4 += 3) {
            int i5 = bArr[i4] & 255;
            int i6 = bArr[i4 + 1] & 255;
            int i7 = bArr[i4 + 2] & 255;
            outputStream.write(this.f4064a[(i5 >>> 2) & 63]);
            outputStream.write(this.f4064a[((i5 << 4) | (i6 >>> 4)) & 63]);
            outputStream.write(this.f4064a[((i6 << 2) | (i7 >>> 6)) & 63]);
            outputStream.write(this.f4064a[i7 & 63]);
        }
        switch (i2) {
            case 1:
                int i8 = bArr[i3 + 0] & 255;
                outputStream.write(this.f4064a[(i8 >>> 2) & 63]);
                outputStream.write(this.f4064a[(i8 << 4) & 63]);
                outputStream.write(this.f4065b);
                outputStream.write(this.f4065b);
                break;
            case 2:
                int i9 = bArr[i3 + 0] & 255;
                int i10 = bArr[i3 + 0 + 1] & 255;
                outputStream.write(this.f4064a[(i9 >>> 2) & 63]);
                outputStream.write(this.f4064a[((i9 << 4) | (i10 >>> 4)) & 63]);
                outputStream.write(this.f4064a[(i10 << 2) & 63]);
                outputStream.write(this.f4065b);
                break;
        }
        return (i2 != 0 ? 4 : 0) + ((i3 / 3) * 4);
    }

    @Override // org.p057a.p060b.p061a.InterfaceC0907c
    /* renamed from: b */
    public final int encode(byte[] bArr, int i, OutputStream outputStream) throws IOException {
        int i2;
        int i3 = 0;
        int i4 = i + 0;
        while (i4 > 0 && m136a((char) bArr[i4 - 1])) {
            i4--;
        }
        int i5 = i4 - 4;
        int m135a = m135a(bArr, 0, i5);
        while (m135a < i5) {
            int i6 = m135a + 1;
            byte b = this.f4066c[bArr[m135a]];
            int m135a2 = m135a(bArr, i6, i5);
            int i7 = m135a2 + 1;
            byte b2 = this.f4066c[bArr[m135a2]];
            int m135a3 = m135a(bArr, i7, i5);
            int i8 = m135a3 + 1;
            byte b3 = this.f4066c[bArr[m135a3]];
            int m135a4 = m135a(bArr, i8, i5);
            int i9 = m135a4 + 1;
            byte b4 = this.f4066c[bArr[m135a4]];
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i3 += 3;
            m135a = m135a(bArr, i9, i5);
        }
        char c = (char) bArr[i4 - 4];
        char c2 = (char) bArr[i4 - 3];
        char c3 = (char) bArr[i4 - 2];
        char c4 = (char) bArr[i4 - 1];
        if (c3 == this.f4065b) {
            outputStream.write((this.f4066c[c] << 2) | (this.f4066c[c2] >> 4));
            i2 = 1;
        } else if (c4 == this.f4065b) {
            byte b5 = this.f4066c[c];
            byte b6 = this.f4066c[c2];
            byte b7 = this.f4066c[c3];
            outputStream.write((b5 << 2) | (b6 >> 4));
            outputStream.write((b6 << 4) | (b7 >> 2));
            i2 = 2;
        } else {
            byte b8 = this.f4066c[c];
            byte b9 = this.f4066c[c2];
            byte b10 = this.f4066c[c3];
            byte b11 = this.f4066c[c4];
            outputStream.write((b8 << 2) | (b9 >> 4));
            outputStream.write((b9 << 4) | (b10 >> 2));
            outputStream.write((b10 << 6) | b11);
            i2 = 3;
        }
        return i2 + i3;
    }
}
