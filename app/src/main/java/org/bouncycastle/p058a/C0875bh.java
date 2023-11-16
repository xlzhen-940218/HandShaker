package org.bouncycastle.p058a;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: org.a.a.bh */
/* loaded from: classes.dex */
public final class C0875bh extends AbstractC0878bk {

    /* renamed from: b */
    private int f4030b;

    /* renamed from: c */
    private int f4031c;

    /* renamed from: d */
    private boolean f4032d;

    /* renamed from: e */
    private boolean f4033e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0875bh(InputStream inputStream) throws IOException {
        super(inputStream);
        this.f4032d = false;
        this.f4033e = true;
        this.f4030b = inputStream.read();
        this.f4031c = inputStream.read();
        this.f4032d = this.f4031c < 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m168a(boolean z) {
        this.f4033e = z;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        if (this.f4033e || i2 < 3) {
            return super.read(bArr, i, i2);
        }
        if (this.f4032d) {
            return -1;
        }
        int read = this.f4039a.read(bArr, i + 2, i2 - 2);
        if (read < 0) {
            throw new EOFException();
        }
        bArr[i] = (byte) this.f4030b;
        bArr[i + 1] = (byte) this.f4031c;
        this.f4030b = this.f4039a.read();
        this.f4031c = this.f4039a.read();
        if (this.f4031c < 0) {
            throw new EOFException();
        }
        return read + 2;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        if (this.f4033e && this.f4030b == 0 && this.f4031c == 0) {
            this.f4032d = true;
            m166b();
        }
        if (this.f4032d) {
            return -1;
        }
        int read = this.f4039a.read();
        if (read < 0) {
            throw new EOFException();
        }
        int i = this.f4030b;
        this.f4030b = this.f4031c;
        this.f4031c = read;
        return i;
    }
}
