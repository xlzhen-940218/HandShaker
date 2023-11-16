package com.smartisanos.smartfolder.aoa.p055g;

import java.nio.ByteBuffer;

/* renamed from: com.smartisanos.smartfolder.aoa.g.i */
/* loaded from: classes.dex */
public final class SspPacket {

    /* renamed from: a */
    private int f3638a;

    /* renamed from: b */
    private byte f3639b;

    /* renamed from: c */
    private ByteBuffer f3640c;

    public SspPacket(int i, byte b, ByteBuffer byteBuffer) {
        this.f3638a = i;
        this.f3639b = b;
        this.f3640c = byteBuffer;
    }

    /* renamed from: a */
    public final int m572a() {
        return this.f3638a;
    }

    /* renamed from: b */
    public final byte m571b() {
        return this.f3639b;
    }

    /* renamed from: c */
    public final ByteBuffer m570c() {
        return this.f3640c;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getClass().getSimpleName()).append("[sid = ").append(this.f3638a).append(", flag = ").append((int) this.f3639b).append(", len=").append(this.f3640c.limit()).append("]");
        return stringBuffer.toString();
    }
}
