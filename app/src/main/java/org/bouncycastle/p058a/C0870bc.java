package org.bouncycastle.p058a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* renamed from: org.a.a.bc */
/* loaded from: classes.dex */
public final class C0870bc extends AbstractC0883f {

    /* renamed from: a */
    String f4021a;

    public final String toString() {
        return this.f4021a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0870bc(byte[] bArr) {
        char c;
        int i;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i4 < bArr.length) {
            i3++;
            if ((bArr[i4] & 240) == 240) {
                i3++;
                i4 += 4;
            } else {
                i4 = (bArr[i4] & 224) == 224 ? i4 + 3 : (bArr[i4] & 192) == 192 ? i4 + 2 : i4 + 1;
            }
        }
        char[] cArr = new char[i3];
        int i5 = 0;
        while (i5 < bArr.length) {
            if ((bArr[i5] & 240) == 240) {
                int i6 = (((((bArr[i5] & 3) << 18) | ((bArr[i5 + 1] & 63) << 12)) | ((bArr[i5 + 2] & 63) << 6)) | (bArr[i5 + 3] & 63)) - 65536;
                char c2 = (char) (55296 | (i6 >> 10));
                c = (char) ((i6 & 1023) | 56320);
                i = i2 + 1;
                cArr[i2] = c2;
                i5 += 4;
            } else if ((bArr[i5] & 224) == 224) {
                c = (char) (((bArr[i5] & 15) << 12) | ((bArr[i5 + 1] & 63) << 6) | (bArr[i5 + 2] & 63));
                i5 += 3;
                i = i2;
            } else if ((bArr[i5] & 208) == 208) {
                c = (char) (((bArr[i5] & 31) << 6) | (bArr[i5 + 1] & 63));
                i5 += 2;
                i = i2;
            } else if ((bArr[i5] & 192) == 192) {
                c = (char) (((bArr[i5] & 31) << 6) | (bArr[i5 + 1] & 63));
                i5 += 2;
                i = i2;
            } else {
                c = (char) (bArr[i5] & 255);
                i5++;
                i = i2;
            }
            i2 = i + 1;
            cArr[i] = c;
        }
        this.f4021a = new String(cArr);
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return this.f4021a.hashCode();
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0870bc) {
            return this.f4021a.equals(((C0870bc) abstractC0856ap).f4021a);
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) {
        String str = this.f4021a;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        char[] charArray = str.toCharArray();
        int i = 0;
        while (i < charArray.length) {
            char c = charArray[i];
            if (c < 128) {
                byteArrayOutputStream.write(c);
            } else if (c < 2048) {
                byteArrayOutputStream.write((c >> 6) | 192);
                byteArrayOutputStream.write((c & '?') | 128);
            } else if (c < 55296 || c > 57343) {
                byteArrayOutputStream.write((c >> '\f') | 224);
                byteArrayOutputStream.write(((c >> 6) & 63) | 128);
                byteArrayOutputStream.write((c & '?') | 128);
            } else if (i + 1 >= charArray.length) {
                throw new IllegalStateException("invalid UTF-16 codepoint");
            } else {
                i++;
                char c2 = charArray[i];
                if (c > 56319) {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
                int i2 = (((c & 1023) << 10) | (c2 & 1023)) + 65536;
                byteArrayOutputStream.write((i2 >> 18) | 240);
                byteArrayOutputStream.write(((i2 >> 12) & 63) | 128);
                byteArrayOutputStream.write(((i2 >> 6) & 63) | 128);
                byteArrayOutputStream.write((i2 & 63) | 128);
            }
            i++;
        }
        try {
            c0860at.m172a(12, byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
