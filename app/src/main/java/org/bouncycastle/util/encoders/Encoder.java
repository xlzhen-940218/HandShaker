package org.bouncycastle.util.encoders;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: org.a.b.a.c */
/* loaded from: classes.dex */
public interface Encoder {
    /* renamed from: a */
    int mo130a(byte[] bArr, int i, OutputStream outputStream) throws IOException;

    /* renamed from: b */
    int encode(byte[] bArr, int i, OutputStream outputStream)throws IOException;
}
