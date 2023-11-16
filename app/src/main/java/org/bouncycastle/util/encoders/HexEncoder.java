package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.a.b.a.e */
/* loaded from: classes.dex */
public final class HexEncoder implements Encoder {

    /* renamed from: a */
    protected final byte[] f4068a = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: b */
    protected final byte[] f4069b = new byte[128];

    public HexEncoder() {
        m132a();
    }

    /* renamed from: a */
    private void m132a() {
        for (int i = 0; i < this.f4068a.length; i++) {
            this.f4069b[this.f4068a[i]] = (byte) i;
        }
        this.f4069b[65] = this.f4069b[97];
        this.f4069b[66] = this.f4069b[98];
        this.f4069b[67] = this.f4069b[99];
        this.f4069b[68] = this.f4069b[100];
        this.f4069b[69] = this.f4069b[101];
        this.f4069b[70] = this.f4069b[102];
    }

    /* renamed from: a */
    private static boolean m131a(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    @Override // org.p057a.p060b.p061a.InterfaceC0907c
    /* renamed from: a */
    public final int mo130a(byte[] bArr, int i, OutputStream outputStream) throws IOException {
        for (int i2 = 0; i2 < i + 0; i2++) {
            int i3 = bArr[i2] & 255;
            outputStream.write(this.f4068a[i3 >>> 4]);
            outputStream.write(this.f4068a[i3 & 15]);
        }
        return i * 2;
    }

    @Override // org.p057a.p060b.p061a.InterfaceC0907c
    /* renamed from: b */
    public final int encode(byte[] bArr, int i, OutputStream outputStream) throws IOException {
        int i2 = 0;
        int i3 = i + 0;
        while (i3 > 0 && m131a((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = 0;
        while (i2 < i3) {
            int i5 = i2;
            while (i5 < i3 && m131a((char) bArr[i5])) {
                i5++;
            }
            int i6 = i5 + 1;
            byte b = this.f4069b[bArr[i5]];
            while (i6 < i3 && m131a((char) bArr[i6])) {
                i6++;
            }
            outputStream.write(this.f4069b[bArr[i6]] | (b << 4));
            i4++;
            i2 = i6 + 1;
        }
        return i4;
    }
}
