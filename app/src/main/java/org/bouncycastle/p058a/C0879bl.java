package org.bouncycastle.p058a;

/* renamed from: org.a.a.bl */
/* loaded from: classes.dex */
public final class C0879bl {

    /* renamed from: a */
    private String f4040a;

    /* renamed from: b */
    private int f4041b = 0;

    public C0879bl(String str) {
        this.f4040a = str;
    }

    /* renamed from: a */
    public final boolean m165a() {
        return this.f4041b != -1;
    }

    /* renamed from: b */
    public final String m164b() {
        if (this.f4041b == -1) {
            return null;
        }
        int indexOf = this.f4040a.indexOf(46, this.f4041b);
        if (indexOf == -1) {
            String substring = this.f4040a.substring(this.f4041b);
            this.f4041b = -1;
            return substring;
        }
        String substring2 = this.f4040a.substring(this.f4041b, indexOf);
        this.f4041b = indexOf + 1;
        return substring2;
    }
}
