package org.bouncycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* renamed from: org.a.b.a.d */
/* loaded from: classes.dex */
public final class Hex {

    /* renamed from: a */
    private static final Encoder f4067a = new HexEncoder();

    /* renamed from: a */
    public static byte[] m134a(byte[] bArr) {
        return m133a(bArr, bArr.length);
    }

    /* renamed from: a */
    private static byte[] m133a(byte[] bArr, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f4067a.mo130a(bArr, i, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("exception encoding Hex string: " + e);
        }
    }
}
