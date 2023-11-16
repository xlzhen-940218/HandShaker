package org.bouncycastle.util.p062b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: org.a.b.b.a */
/* loaded from: classes.dex */
public final class C0910a {

    /* renamed from: a */
    private static int f4070a = 512;

    /* renamed from: a */
    private static void m127a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[f4070a];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read < 0) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    /* renamed from: a */
    public static byte[] m128a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            m127a(inputStream, byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static int m126a(InputStream inputStream, byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int read = inputStream.read(bArr, i + 0, length - i);
            if (read < 0) {
                break;
            }
            i += read;
        }
        return i;
    }
}
