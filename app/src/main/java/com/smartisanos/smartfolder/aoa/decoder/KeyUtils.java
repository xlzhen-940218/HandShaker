package com.smartisanos.smartfolder.aoa.decoder;

import com.smartisanos.smartfolder.aoa.p056h.Md5Utils;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import org.apache.http.protocol.HTTP;
import org.bouncycastle.p058a.AbstractC0887j;
import org.bouncycastle.p058a.C0881d;
import org.bouncycastle.p058a.p059a.C0840a;
import org.bouncycastle.util.encoders.Base64;

/* renamed from: com.smartisanos.smartfolder.aoa.decoder.b */
/* loaded from: classes.dex */
public final class KeyUtils {

    /* renamed from: a */
    private static KeyUtils instance = new KeyUtils();

    /* renamed from: a */
    public static KeyUtils getInstance() {
        return instance;
    }

    /* renamed from: a */
    public final synchronized PublicKey getPublicKey(SmartSyncProtocolProtos.SSPHandShakeRequest01 SSPHandShakeRequest01) {
        PublicKey publicKey;
        PublicKey publicKey2 = null;
        synchronized (this) {
            try {
                try {
                    byte[] md5Bytes = SSPHandShakeRequest01.getMd5().toByteArray();
                    byte[] m138a = Base64.encode(new String(C0735C.parseIoBuffer(SSPHandShakeRequest01.getEnckey().toByteArray())
                            , StandardCharsets.UTF_8).trim().getBytes());
                    byte[] m402a = Md5Utils.md5(m138a);
                    if (m402a != null) {
                        if (Arrays.equals(m402a, md5Bytes)) {
                            C0840a c0840a = new C0840a((AbstractC0887j) new C0881d(new ByteArrayInputStream(m138a)).m163a());
                            publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(c0840a.m187c(), c0840a.m186d()));
                        } else {
                            publicKey = null;
                        }
                        publicKey2 = publicKey;
                    }
                } catch (InvalidKeySpecException e2) {
                    e2.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchAlgorithmException e3) {
                e3.printStackTrace();
            }
        }
        return publicKey2;
    }

    /* renamed from: a */
    public final synchronized PublicKey getPublicKey(byte[] bArr) {
        PublicKey publicKey;
        PublicKey publicKey2 = null;
        synchronized (this) {
            try {
                try {
                    byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 16);
                    byte[] m138a = Base64.encode(new String(C0735C.parseIoBuffer(Arrays.copyOfRange(bArr, 16, bArr.length)), HTTP.UTF_8).trim().getBytes());
                    byte[] m402a = Md5Utils.md5(m138a);
                    if (m402a != null) {
                        if (Arrays.equals(m402a, copyOfRange)) {
                            C0840a c0840a = new C0840a((AbstractC0887j) new C0881d(new ByteArrayInputStream(m138a)).m163a());
                            publicKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(c0840a.m187c(), c0840a.m186d()));
                        } else {
                            publicKey = null;
                        }
                        publicKey2 = publicKey;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e2) {
                    e2.printStackTrace();
                }
            } catch (InvalidKeySpecException e3) {
                e3.printStackTrace();
            }
        }
        return publicKey2;
    }
}
