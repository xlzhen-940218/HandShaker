package org.bouncycastle.p058a;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.a.a.at */
/* loaded from: classes.dex */
public class C0860at extends FilterOutputStream {
    public C0860at(OutputStream outputStream) {
        super(outputStream);
    }

    /* renamed from: a */
    private void m175a(int i) throws IOException {
        if (i <= 127) {
            write((byte) i);
            return;
        }
        int i2 = 1;
        int i3 = i;
        while (true) {
            i3 >>>= 8;
            if (i3 == 0) {
                break;
            }
            i2++;
        }
        write((byte) (i2 | 128));
        for (int i4 = (i2 - 1) * 8; i4 >= 0; i4 -= 8) {
            write((byte) (i >> i4));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m176a() throws IOException {
        write(5);
        write(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m174a(int i, int i2) throws IOException {
        int i3 = 4;
        if (i2 < 31) {
            write(i | i2);
            return;
        }
        write(i | 31);
        if (i2 < 128) {
            write(i2);
            return;
        }
        byte[] bArr = new byte[5];
        bArr[4] = (byte) (i2 & 127);
        do {
            i2 >>= 7;
            i3--;
            bArr[i3] = (byte) ((i2 & 127) | 128);
        } while (i2 > 127);
        write(bArr, i3, 5 - i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m173a(int i, int i2, byte[] bArr) throws IOException {
        m174a(i, i2);
        m175a(bArr.length);
        write(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m172a(int i, byte[] bArr) throws IOException {
        write(i);
        m175a(bArr.length);
        write(bArr);
    }

    /* renamed from: a */
    public void mo144a(Object obj) throws IOException {
        if (obj == null) {
            m176a();
        } else if (obj instanceof AbstractC0856ap) {
            ((AbstractC0856ap) obj).mo143a(this);
        } else if (!(obj instanceof InterfaceC0846af)) {
            throw new IOException("object not DEREncodable");
        } else {
            ((InterfaceC0846af) obj).mo142a().mo143a(this);
        }
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterOutputStream, java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }
}
