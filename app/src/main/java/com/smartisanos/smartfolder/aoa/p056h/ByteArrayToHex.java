package com.smartisanos.smartfolder.aoa.p056h;

/* renamed from: com.smartisanos.smartfolder.aoa.h.c */
/* loaded from: classes.dex */
public final class ByteArrayToHex {

    /* renamed from: a */
    private static final char[] f3672a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: b */
    private static final char[] f3673b = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static String m520a(byte[] bArr) {
        return m517b(bArr);
    }

    /* renamed from: b */
    public static String m517b(byte[] bArr) {
        return new String(m519a(bArr, f3672a));
    }

    /* renamed from: a */
    private static char[] m519a(byte[] bArr, char[] cArr) {
        int i = 0;
        int length = bArr.length;
        char[] cArr2 = new char[length << 1];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & 15];
        }
        return cArr2;
    }

    /* renamed from: a */
    public static byte[] m518a(char[] cArr) {
        int i = 0;
        int length = cArr.length;
        if ((length & 1) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] bArr = new byte[length >> 1];
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            i = i3 + 1;
            bArr[i2] = (byte) (((m521a(cArr[i], i) << 4) | m521a(cArr[i3], i3)) & 255);
            i2++;
        }
        return bArr;
    }

    /* renamed from: a */
    private static int m521a(char c, int i) {
        int digit = Character.digit(c, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + c + " at index " + i);
        }
        return digit;
    }
}
