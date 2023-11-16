package org.bouncycastle.p058a;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;

/* renamed from: org.a.a.aq */
/* loaded from: classes.dex */
public final class C0857aq extends AbstractC0883f {

    /* renamed from: a */
    String f4013a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0857aq(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        BigInteger bigInteger = null;
        long j = 0;
        int i = 0;
        while (i != bArr.length) {
            int i2 = bArr[i] & 255;
            if (j < 36028797018963968L) {
                j = (j * 128) + (i2 & 127);
                if ((i2 & 128) == 0) {
                    if (z) {
                        switch (((int) j) / 40) {
                            case 0:
                                stringBuffer.append('0');
                                break;
                            case 1:
                                stringBuffer.append('1');
                                j -= 40;
                                break;
                            default:
                                stringBuffer.append('2');
                                j -= 80;
                                break;
                        }
                        z = false;
                    }
                    stringBuffer.append('.');
                    stringBuffer.append(j);
                    j = 0;
                }
            } else {
                bigInteger = (bigInteger == null ? BigInteger.valueOf(j) : bigInteger).shiftLeft(7).or(BigInteger.valueOf(i2 & 127));
                if ((i2 & 128) == 0) {
                    stringBuffer.append('.');
                    stringBuffer.append(bigInteger);
                    bigInteger = null;
                    j = 0;
                }
            }
            i++;
            bigInteger = bigInteger;
            z = z;
        }
        this.f4013a = stringBuffer.toString();
    }

    /* renamed from: a */
    private static void m178a(OutputStream outputStream, long j) throws IOException {
        if (j >= 128) {
            if (j >= 16384) {
                if (j >= 2097152) {
                    if (j >= 268435456) {
                        if (j >= 34359738368L) {
                            if (j >= 4398046511104L) {
                                if (j >= 562949953421312L) {
                                    if (j >= 72057594037927936L) {
                                        outputStream.write(((int) (j >> 56)) | 128);
                                    }
                                    outputStream.write(((int) (j >> 49)) | 128);
                                }
                                outputStream.write(((int) (j >> 42)) | 128);
                            }
                            outputStream.write(((int) (j >> 35)) | 128);
                        }
                        outputStream.write(((int) (j >> 28)) | 128);
                    }
                    outputStream.write(((int) (j >> 21)) | 128);
                }
                outputStream.write(((int) (j >> 14)) | 128);
            }
            outputStream.write(((int) (j >> 7)) | 128);
        }
        outputStream.write(((int) j) & 127);
    }

    /* renamed from: a */
    private static void m177a(OutputStream outputStream, BigInteger bigInteger) throws IOException {
        int bitLength = (bigInteger.bitLength() + 6) / 7;
        if (bitLength == 0) {
            outputStream.write(0);
            return;
        }
        byte[] bArr = new byte[bitLength];
        for (int i = bitLength - 1; i >= 0; i--) {
            bArr[i] = (byte) ((bigInteger.intValue() & 127) | 128);
            bigInteger = bigInteger.shiftRight(7);
        }
        int i2 = bitLength - 1;
        bArr[i2] = (byte) (bArr[i2] & Byte.MAX_VALUE);
        outputStream.write(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p057a.p058a.AbstractC0883f, org.p057a.p058a.AbstractC0856ap
    /* renamed from: a */
    public final void mo143a(C0860at c0860at) throws IOException {
        C0879bl c0879bl = new C0879bl(this.f4013a);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C0860at c0860at2 = new C0860at(byteArrayOutputStream);
        m178a(byteArrayOutputStream, (Integer.parseInt(c0879bl.m164b()) * 40) + Integer.parseInt(c0879bl.m164b()));
        while (c0879bl.m165a()) {
            String m164b = c0879bl.m164b();
            if (m164b.length() < 18) {
                m178a(byteArrayOutputStream, Long.parseLong(m164b));
            } else {
                m177a(byteArrayOutputStream, new BigInteger(m164b));
            }
        }
        c0860at2.close();
        c0860at.m172a(6, byteArrayOutputStream.toByteArray());
    }

    @Override // org.p057a.p058a.AbstractC0883f
    /* renamed from: a */
    final boolean mo150a(AbstractC0856ap abstractC0856ap) {
        if (abstractC0856ap instanceof C0857aq) {
            return this.f4013a.equals(((C0857aq) abstractC0856ap).f4013a);
        }
        return false;
    }

    @Override // org.p057a.p058a.AbstractC0856ap, org.p057a.p058a.AbstractC0867b
    public final int hashCode() {
        return this.f4013a.hashCode();
    }

    public final String toString() {
        return this.f4013a;
    }
}
