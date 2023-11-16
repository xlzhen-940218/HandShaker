package com.smartisanos.smartfolder.aoa.p051c;

/* renamed from: com.smartisanos.smartfolder.aoa.c.b */
/* loaded from: classes.dex */
public final class PacketTooLargeException extends Exception {

    /* renamed from: a */
    private int f3473a;

    /* renamed from: b */
    private byte f3474b;

    public PacketTooLargeException(String str, int i, byte b) {
        super(str);
        this.f3473a = i;
        this.f3474b = b;
    }

    /* renamed from: a */
    public final int m760a() {
        return this.f3473a;
    }

    /* renamed from: b */
    public final byte m759b() {
        return this.f3474b;
    }
}
