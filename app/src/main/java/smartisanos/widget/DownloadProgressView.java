package smartisanos.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class DownloadProgressView extends View {

    /* renamed from: f */
    private static int f4188f = 6;

    /* renamed from: g */
    private static int f4189g = 4;

    /* renamed from: h */
    private static int f4190h;

    /* renamed from: a */
    private int f4191a;

    /* renamed from: b */
    private int f4192b;

    /* renamed from: c */
    private Paint f4193c;

    /* renamed from: d */
    public int f4194d;

    /* renamed from: e */
    private ValueAnimator f4195e;

    /* renamed from: i */
    private int f4196i;

    /* renamed from: j */
    private int f4197j;

    /* renamed from: k */
    private int f4198k;

    /* renamed from: l */
    private int f4199l;

    /* renamed from: m */
    private int f4200m;

    /* renamed from: n */
    private int f4201n;

    /* renamed from: o */
    private int f4202o;

    /* renamed from: p */
    private int f4203p;

    /* renamed from: q */
    private int f4204q;

    /* renamed from: r */
    private int f4205r;

    /* renamed from: s */
    private Drawable f4206s;

    /* renamed from: t */
    private Drawable f4207t;

    /* renamed from: u */
    private Drawable f4208u;

    /* renamed from: v */
    private RectF f4209v;

    /* renamed from: w */
    private Shader f4210w;

    /* renamed from: x */
    private Shader f4211x;

    public DownloadProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == Integer.MIN_VALUE || size <= 0) {
            this.f4196i = f4190h;
        } else {
            this.f4196i = size;
        }
        if (this.f4196i < (this.f4199l + this.f4197j) * 2) {
            throw new IllegalArgumentException("illegal custom values, the view width should match: width >= 2*(inner_circle_radius + back_ring_width) , current width:" + this.f4196i + " inner_circle_radius:" + this.f4199l + " back_ring_width:" + this.f4197j);
        }
        setMeasuredDimension(this.f4196i, this.f4196i);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int i = this.f4198k;
        int i2 = this.f4197j;
        if (this.f4193c == null) {
            this.f4193c = new Paint();
        }
        this.f4193c.reset();
        this.f4193c.setStyle(Paint.Style.STROKE);
        this.f4193c.setAntiAlias(true);
        this.f4193c.setColor(0);
        canvas.drawRect(0.0f, 0.0f, this.f4196i, this.f4196i, this.f4193c);
        int i3 = this.f4196i / 2;
        int i4 = this.f4196i / 2;
        this.f4193c.setColor(-1);
        Drawable m80a = m80a(this.f4192b);
        if (m80a != null && this.f4194d != 255) {
            int intrinsicWidth = m80a.getIntrinsicWidth();
            int intrinsicHeight = m80a.getIntrinsicHeight();
            m80a.setBounds((this.f4196i - intrinsicWidth) / 2, (this.f4196i - intrinsicHeight) / 2, (intrinsicWidth + this.f4196i) / 2, (intrinsicHeight + this.f4196i) / 2);
            m80a.setAlpha(255 - this.f4194d);
            m80a.draw(canvas);
        }
        Drawable m80a2 = m80a(this.f4191a);
        if (m80a2 != null) {
            int intrinsicWidth2 = m80a2.getIntrinsicWidth();
            int intrinsicHeight2 = m80a2.getIntrinsicHeight();
            m80a2.setBounds((this.f4196i - intrinsicWidth2) / 2, (this.f4196i - intrinsicHeight2) / 2, (intrinsicWidth2 + this.f4196i) / 2, (intrinsicHeight2 + this.f4196i) / 2);
            m80a2.setAlpha(this.f4194d);
            m80a2.draw(canvas);
        }
        this.f4193c.setStrokeWidth(i2);
        this.f4193c.setColor(Color.parseColor("#e6e6e6"));
        canvas.drawCircle(i3, i4, this.f4199l, this.f4193c);
        this.f4193c.setStrokeWidth(i);
        this.f4193c.setColor(Color.parseColor("#f2f2f2"));
        canvas.drawCircle(i3, i4, this.f4199l, this.f4193c);
        this.f4193c.setStrokeCap(Paint.Cap.ROUND);
        this.f4193c.setStrokeJoin(Paint.Join.ROUND);
        if (this.f4209v == null) {
            this.f4209v = new RectF(i3 - this.f4199l, i4 - this.f4199l, i3 + this.f4199l, this.f4199l + i4);
        }
        this.f4193c.setStrokeWidth(i2);
        if (this.f4191a == 3) {
            this.f4193c.setColor(this.f4205r);
        } else {
            if (this.f4211x == null) {
                this.f4211x = new LinearGradient(0.0f, (i4 - this.f4199l) - this.f4197j, 0.0f, this.f4199l + i4 + this.f4197j, this.f4203p, this.f4204q, Shader.TileMode.CLAMP);
            }
            this.f4193c.setShader(this.f4211x);
        }
        float f = (360.0f * this.f4200m) / 100.0f;
        canvas.drawArc(this.f4209v, -90.0f, f, false, this.f4193c);
        this.f4193c.setStrokeWidth(i);
        if (this.f4191a == 3) {
            this.f4193c.setColor(this.f4205r);
        } else {
            if (this.f4210w == null) {
                this.f4210w = new LinearGradient(0.0f, (i4 - this.f4199l) - this.f4198k, 0.0f, this.f4199l + i4 + this.f4198k, this.f4201n, this.f4202o, Shader.TileMode.CLAMP);
            }
            this.f4193c.setShader(this.f4210w);
        }
        canvas.drawArc(this.f4209v, -90.0f, f, false, this.f4193c);
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        m78b();
        super.onDetachedFromWindow();
    }

    /* renamed from: a */
    private Drawable m80a(int i) {
        switch (i) {
            case 1:
                return this.f4206s;
            case 2:
                return this.f4207t;
            case 3:
                return this.f4208u;
            default:
                return null;
        }
    }

    /* renamed from: a */
    private boolean m81a() {
        return this.f4195e != null && this.f4195e.isRunning();
    }

    /* renamed from: b */
    private void m78b() {
        if (m81a()) {
            this.f4195e.cancel();
        }
        this.f4194d = 255;
    }

    /* loaded from: classes.dex */
    static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new C0931b();

        /* renamed from: a */
        int f4212a;

        /* renamed from: b */
        int f4213b;

        /* JADX INFO: Access modifiers changed from: package-private */
        public /* synthetic */ SavedState(Parcel parcel, byte b) {
            this(parcel);
        }

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.f4212a = parcel.readInt();
            this.f4213b = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f4212a);
            parcel.writeInt(this.f4213b);
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.f4212a = this.f4200m;
        savedState.f4213b = this.f4191a;
        return savedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        int i = savedState.f4213b;
        switch (i) {
            case 1:
            case 2:
            case 3:
                if (this.f4191a != i) {
                    this.f4192b = this.f4191a;
                    this.f4191a = i;
                    if (this.f4195e == null) {
                        this.f4195e = ValueAnimator.ofInt(0, 255);
                        this.f4195e.addUpdateListener(new C0930a(this));
                        this.f4195e.setDuration(300L);
                    }
                    if (m81a()) {
                        m78b();
                    }
                    this.f4195e.start();
                    invalidate();
                }
                int i2 = savedState.f4212a;
                if (this.f4200m == i2) {
                    return;
                }
                this.f4200m = i2;
                invalidate();
                return;
            default:
                throw new IllegalArgumentException("invalid state value:" + i);
        }
    }
}
