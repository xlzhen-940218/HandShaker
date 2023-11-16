package org.bouncycastle.util.encoders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* renamed from: org.a.b.a.a */
/* loaded from: classes.dex */
public final class Base64 {

    /* renamed from: a */
    private static final Encoder encoder = new Base64Encoder();

    /* renamed from: a */
    public static byte[] encode(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((bArr.length / 4) * 3);
        try {
            encoder.encode(bArr, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("exception decoding base64 string: " + e);
        }
    }
}
