package smartisanos.widget.letters;

import android.graphics.Bitmap;

/* renamed from: smartisanos.widget.letters.b */
/* loaded from: classes.dex */
public final class LBLetter {

    /* renamed from: a */
    public static LBLetter f4557a = new LBLetter("#");

    /* renamed from: b */
    private int f4558b = 0;

    /* renamed from: c */
    private String f4559c;

    /* renamed from: d */
    private Bitmap[] f4560d;

    private LBLetter(String str) {
        this.f4559c = str;
        if (this.f4558b == 2) {
            throw new IllegalStateException("symbol must have 3 bitmaps!");
        }
    }

    /* renamed from: a */
    public final int m2a() {
        return this.f4558b;
    }

    /* renamed from: b */
    public final String m1b() {
        return this.f4559c;
    }

    /* renamed from: c */
    public final Bitmap[] m0c() {
        return this.f4560d;
    }
}
