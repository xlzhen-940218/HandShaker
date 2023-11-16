package smartisanos.widget.letters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.smartisanos.smartfolder.aoa.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class LettersBar extends View {

    /* renamed from: a */
    int f4504a;

    /* renamed from: b */
    int f4505b;

    /* renamed from: c */
    private Context f4506c;

    /* renamed from: d */
    private List<LBLetter> f4507d;

    /* renamed from: e */
    private int f4508e;

    /* renamed from: f */
    private boolean f4509f;

    /* renamed from: g */
    private int f4510g;

    /* renamed from: h */
    private int f4511h;

    /* renamed from: i */
    private int f4512i;

    /* renamed from: j */
    private int f4513j;

    /* renamed from: k */
    private int f4514k;

    /* renamed from: l */
    private Paint f4515l;

    /* renamed from: m */
    private Bitmap f4516m;

    /* renamed from: n */
    private int f4517n;

    /* renamed from: o */
    private int f4518o;

    /* renamed from: p */
    private int f4519p;

    /* renamed from: q */
    private int f4520q;

    /* renamed from: r */
    private Bitmap f4521r;

    /* renamed from: s */
    private int f4522s;

    /* renamed from: t */
    private int f4523t;

    /* renamed from: u */
    private InterfaceC0949a f4524u;

    /* renamed from: v */
    private boolean f4525v;

    /* renamed from: smartisanos.widget.letters.LettersBar$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0949a {
        /* renamed from: a */
        boolean m15a();
    }

    public LettersBar(Context context) {
        this(context, null);
    }

    public LettersBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LettersBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4508e = -1;
        this.f4509f = true;
        this.f4510g = 0;
        this.f4515l = new Paint();
        this.f4506c = context;
        Resources resources = this.f4506c.getResources();
        this.f4511h = resources.getDimensionPixelOffset(R.dimen.letters_bar_width);
        this.f4512i = resources.getDimensionPixelSize(R.dimen.letters_bar_single_letter_min_height);
        this.f4513j = resources.getDimensionPixelOffset(R.dimen.letters_bar_letter_font_size);
        this.f4514k = resources.getDimensionPixelOffset(R.dimen.letters_bar_letter_font_x_offset);
        this.f4516m = BitmapFactory.decodeResource(resources, R.drawable.letters_bar_highlight_icon);
        this.f4517n = resources.getDimensionPixelOffset(R.dimen.letters_bar_highlight_icon_x);
        this.f4518o = resources.getDimensionPixelOffset(R.dimen.letters_bar_highlight_icon_y);
        this.f4519p = resources.getDimensionPixelOffset(R.dimen.letters_bar_symbol_x);
        this.f4520q = resources.getDimensionPixelOffset(R.dimen.letters_bar_symbol_y);
        this.f4504a = resources.getColor(R.color.has_chosen_letter_font_color);
        this.f4505b = resources.getColor(R.color.no_chosen_letter_font_color);
        this.f4521r = BitmapFactory.decodeResource(resources, R.drawable.letters_bar_dot);
        this.f4522s = resources.getDimensionPixelSize(R.dimen.letters_bar_dot_y_offset);
        this.f4523t = resources.getDimensionPixelOffset(R.dimen.letters_bar_dot_x_offset);
        this.f4515l.setTypeface(Typeface.DEFAULT_BOLD);
        this.f4515l.setTextSize(this.f4513j);
        this.f4515l.setAntiAlias(true);
    }

    /* renamed from: a */
    public final void m17a(boolean z) {
        this.f4509f = z;
        if (this.f4509f) {
            this.f4508e = -1;
        }
        invalidate();
    }

    /* renamed from: b */
    public final void m16b(boolean z) {
        this.f4525v = z;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE && mode != 1073741824) {
            size = 0;
        }
        this.f4510g = size;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        super.dispatchTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        float y = motionEvent.getY();
        int i = this.f4508e;
        int height = (int) ((y / getHeight()) * this.f4507d.size());
        switch (action) {
            case 0:
                if (i != height && height >= 0 && height < this.f4507d.size()) {
                    this.f4508e = height;
                }
                this.f4525v = true;
                break;
            case 1:
            case 3:
                if (this.f4509f) {
                    if (this.f4524u != null && height >= 0 && height < this.f4507d.size()) {
                        InterfaceC0949a interfaceC0949a = this.f4524u;
                        this.f4507d.get(height).m1b();
                        if (interfaceC0949a.m15a()) {
                            this.f4508e = height;
                        }
                    }
                    this.f4508e = -1;
                    this.f4525v = false;
                    break;
                }
                break;
            case 2:
                if (this.f4509f && i != height && this.f4524u != null && height >= 0 && height < this.f4507d.size()) {
                    InterfaceC0949a interfaceC0949a2 = this.f4524u;
                    this.f4507d.get(height).m1b();
                    if (interfaceC0949a2.m15a()) {
                        this.f4508e = height;
                    }
                }
                this.f4525v = true;
                break;
            default:
                return true;
        }
        invalidate();
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x00cf, code lost:
        if (r7 > 0) goto L30;
     */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void onDraw(Canvas canvas) {
        int i;
        super.onDraw(canvas);
        if (this.f4525v || !this.f4509f) {
            setBackground(getResources().getDrawable(R.drawable.letters_bar_background));
        } else {
            setBackground(null);
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 1;
        if (this.f4510g / this.f4507d.size() < this.f4512i) {
            i2 = Math.max(2, ((int) Math.ceil(this.f4507d.size() / ((this.f4510g / this.f4512i) - 2))) * 2);
        }
        arrayList.add(0);
        if (i2 < this.f4507d.size() / 2) {
            for (int i3 = i2; i3 < this.f4507d.size() - 1; i3 += i2) {
                arrayList.add(Integer.valueOf(i3));
            }
        }
        if (i2 < this.f4507d.size()) {
            arrayList.add(Integer.valueOf(this.f4507d.size() - 1));
        }
        if (arrayList.size() != 0 && arrayList.size() != this.f4507d.size()) {
            int size = (arrayList.size() * 2) - 1;
            int i4 = (arrayList.size() < 2 || ((Integer) arrayList.get(arrayList.size() + (-2))).intValue() != this.f4507d.size() + (-2)) ? size : size - 1;
            i = (this.f4510g - (this.f4512i * i4)) / i4;
        }
        i = 0;
        boolean z = i > 0 && arrayList.size() < this.f4507d.size();
        float max = Math.max(getHeight() / (this.f4507d.size() + 0.5f), this.f4512i);
        int i5 = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            float measureText = this.f4514k - (this.f4515l.measureText(this.f4507d.get(intValue).m1b()) / 2.0f);
            float f = ((i + max) * i5) + (max / 2.0f);
            if (m19a(intValue) && this.f4509f) {
                canvas.drawBitmap(this.f4516m, this.f4517n, f - this.f4518o, (Paint) null);
            }
            if (this.f4507d.get(intValue).m2a() != 2) {
                m18a(intValue, canvas, measureText, f, i5, max, i);
            } else {
                float f2 = measureText - this.f4519p;
                float f3 = f - this.f4520q;
                Bitmap[] m0c = this.f4507d.get(intValue).m0c();
                if (m19a(intValue) && this.f4509f) {
                    canvas.drawBitmap(m0c[2], f2, f3, (Paint) null);
                } else if (m20a() || !this.f4509f) {
                    canvas.drawBitmap(m0c[1], f2, f3, (Paint) null);
                } else {
                    canvas.drawBitmap(m0c[0], f2, f3, (Paint) null);
                }
            }
            i5++;
            if (z && intValue != this.f4507d.size() - 2) {
                m18a(intValue, canvas, measureText, f, i5, max, i);
                i5++;
            }
        }
        canvas.restore();
    }

    /* renamed from: a */
    private void m18a(int i, Canvas canvas, float f, float f2, int i2, float f3, int i3) {
        if (m19a(i) && this.f4509f) {
            this.f4515l.setColor(-1);
            this.f4515l.setFakeBoldText(true);
        } else {
            this.f4515l.setColor((m20a() || !this.f4509f) ? this.f4504a : this.f4505b);
            this.f4515l.setFakeBoldText(false);
        }
        String m1b = this.f4507d.get(i).m1b();
        if (!m1b.startsWith(".")) {
            if (this.f4507d.get(i).m2a() == 1) {
                this.f4515l.setTextSize(34.0f);
                this.f4515l.setFakeBoldText(true);
            } else {
                this.f4515l.setTextSize(this.f4513j);
            }
            canvas.drawText(m1b, f, 24.0f + f2, this.f4515l);
            return;
        }
        canvas.drawBitmap(this.f4521r, ((this.f4511h - this.f4523t) - this.f4521r.getWidth()) / 2, (((i3 + f3) * i2) + (f3 / 2.0f)) - this.f4522s, (Paint) null);
    }

    /* renamed from: a */
    private boolean m20a() {
        return this.f4508e != -1;
    }

    /* renamed from: a */
    private boolean m19a(int i) {
        return this.f4508e == i;
    }
}
