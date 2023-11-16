package org.bouncycastle.p058a;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: org.a.a.aa */
/* loaded from: classes.dex */
public final class C0841aa extends InputStream {

    /* renamed from: a */
    private final C0891n f3989a;

    /* renamed from: b */
    private boolean f3990b = true;

    /* renamed from: c */
    private InputStream f3991c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0841aa(C0891n c0891n) {
        this.f3989a = c0891n;
    }

    @Override // java.io.InputStream
    public final int read() throws IOException {
        InterfaceC0885h interfaceC0885h;
        if (this.f3991c == null) {
            if (this.f3990b && (interfaceC0885h = (InterfaceC0885h) this.f3989a.m152a()) != null) {
                this.f3990b = false;
                this.f3991c = interfaceC0885h.mo145c();
            }
            return -1;
        }
        while (true) {
            int read = this.f3991c.read();
            if (read >= 0) {
                return read;
            }
            InterfaceC0885h interfaceC0885h2 = (InterfaceC0885h) this.f3989a.m152a();
            if (interfaceC0885h2 == null) {
                this.f3991c = null;
                return -1;
            }
            this.f3991c = interfaceC0885h2.mo145c();
            break;
        }
        return -1;
    }

    @Override // java.io.InputStream
    public final int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        InterfaceC0885h interfaceC0885h;
        int i4 = 0;
        if (this.f3991c == null) {
            if (this.f3990b && (interfaceC0885h = (InterfaceC0885h) this.f3989a.m152a()) != null) {
                this.f3990b = false;
                this.f3991c = interfaceC0885h.mo145c();
            }
            return -1;
        }
        while (true) {
            int read = this.f3991c.read(bArr, i + i4, i2 - i4);
            if (read >= 0) {
                i3 = read + i4;
                if (i3 == i2) {
                    return i3;
                }
            } else {
                InterfaceC0885h interfaceC0885h2 = (InterfaceC0885h) this.f3989a.m152a();
                if (interfaceC0885h2 == null) {
                    this.f3991c = null;
                    if (i4 <= 0) {
                        return -1;
                    }
                    return i4;
                }
                this.f3991c = interfaceC0885h2.mo145c();
                i3 = i4;
            }
            i4 = i3;
        }
    }
}
