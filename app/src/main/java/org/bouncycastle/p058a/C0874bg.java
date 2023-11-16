package org.bouncycastle.p058a;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.bouncycastle.util.p062b.C0910a;

/* renamed from: org.a.a.bg */
/* loaded from: classes.dex */
final class C0874bg extends AbstractC0878bk {

    /* renamed from: b */
    private static final byte[] f4028b = new byte[0];

    /* renamed from: c */
    private int f4029c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0874bg(InputStream inputStream, int i) {
        super(inputStream);
        if (i < 0) {
            throw new IllegalArgumentException("negative lengths not allowed");
        }
        this.f4029c = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final byte[] m169a() {
        byte[] bArr;
        if (this.f4029c > 0) {
            bArr = new byte[this.f4029c];
            try {
                if (C0910a.m126a(this.f4039a, bArr) < this.f4029c) {
                    //throw new EOFException();
                    return null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.f4029c = 0;
        } else {
            bArr = f4028b;
        }
        m166b();
        return bArr;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.f4029c <= 0) {
            m166b();
            return -1;
        }
        int read = this.f4039a.read();
        if (read < 0) {
            throw new EOFException();
        }
        this.f4029c--;
        return read;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.f4029c <= 0) {
            m166b();
            return -1;
        }
        int read = this.f4039a.read(bArr, i, Math.min(i2, this.f4029c));
        if (read < 0) {
            throw new EOFException();
        }
        this.f4029c -= read;
        return read;
    }
}
