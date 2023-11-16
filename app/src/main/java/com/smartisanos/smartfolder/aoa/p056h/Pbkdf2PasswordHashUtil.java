package com.smartisanos.smartfolder.aoa.p056h;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* renamed from: com.smartisanos.smartfolder.aoa.h.z */
/* loaded from: classes.dex */
public final class Pbkdf2PasswordHashUtil {
    /* renamed from: a */
    public static byte[] getDerivedKey(char[] cArr, byte[] bArr) {
        try {
            return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
                    .generateSecret(new PBEKeySpec(cArr, bArr, 1500, 2048))
                    .getEncoded();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
