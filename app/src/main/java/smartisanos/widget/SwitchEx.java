package smartisanos.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.smartisanos.smartfolder.aoa.R;

/* loaded from: classes.dex */
public class SwitchEx extends CheckBox {

    /* renamed from: f */
    private static boolean f4407f;

    /* renamed from: g */
    private static Bitmap f4408g;

    /* renamed from: h */
    private static Bitmap f4409h;

    /* renamed from: i */
    private static Bitmap f4410i;

    /* renamed from: j */
    private static Bitmap f4411j;

    /* renamed from: k */
    private static Bitmap f4412k;

    /* renamed from: l */
    private static PorterDuffXfermode f4413l = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);

    /* renamed from: m */
    private static Canvas f4414m = new Canvas();

    /* renamed from: n */
    private static Paint f4415n = new Paint();

    /* renamed from: o */
    private static Bitmap f4416o;

    /* renamed from: p */
    private static Bitmap f4417p;

    /* renamed from: q */
    private static Bitmap f4418q;

    /* renamed from: r */
    private static Bitmap f4419r;

    /* renamed from: s */
    private static float f4420s;

    /* renamed from: t */
    private static float f4421t;

    /* renamed from: u */
    private static float f4422u;

    /* renamed from: v */
    private static float f4423v;

    /* renamed from: w */
    private static float f4424w;

    /* renamed from: A */
    private float f4425A;

    /* renamed from: B */
    private float f4426B;

    /* renamed from: C */
    private float f4427C;

    /* renamed from: D */
    private float f4428D;

    /* renamed from: E */
    private float f4429E;

    /* renamed from: F */
    private float f4430F;

    /* renamed from: G */
    private int f4431G;

    /* renamed from: H */
    private int f4432H;

    /* renamed from: I */
    private int f4433I;

    /* renamed from: J */
    private boolean f4434J;

    /* renamed from: K */
    private boolean f4435K;

    /* renamed from: L */
    private boolean f4436L;

    /* renamed from: M */
    private boolean f4437M;

    /* renamed from: N */
    private boolean f4438N;

    /* renamed from: O */
    private RunnableC0929a f4439O;

    /* renamed from: P */
    private CompoundButton.OnCheckedChangeListener f4440P;

    /* renamed from: Q */
    private CompoundButton.OnCheckedChangeListener f4441Q;

    /* renamed from: R */
    private boolean f4442R;

    /* renamed from: S */
    private Handler f4443S;

    /* renamed from: a */
    private final float f4444a;

    /* renamed from: b */
    private final float f4445b;

    /* renamed from: c */
    private final float f4446c;

    /* renamed from: d */
    private Resources f4447d;

    /* renamed from: e */
    private ViewParent f4448e;

    /* renamed from: x */
    private float f4449x;

    /* renamed from: y */
    private float f4450y;

    /* renamed from: z */
    private float f4451z;

    public SwitchEx(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842860);
    }

    public SwitchEx(Context context) {
        this(context, null);
    }

    /* renamed from: a */
    private static void m29a(Resources resources) {
        if (f4408g == null || f4410i == null || f4411j == null || f4412k == null) {
            f4408g = ((BitmapDrawable) resources.getDrawable(R.drawable.switch_ex_bottom)).getBitmap();
            f4410i = ((BitmapDrawable) resources.getDrawable(R.drawable.switch_ex_unpressed)).getBitmap();
            f4411j = ((BitmapDrawable) resources.getDrawable(R.drawable.switch_ex_frame)).getBitmap();
            f4412k = ((BitmapDrawable) resources.getDrawable(R.drawable.switch_ex_mask)).getBitmap();
            f4409h = f4410i;
        }
        f4415n.setColor(-1);
        f4424w = f4410i.getWidth();
        f4422u = f4412k.getWidth();
        f4423v = f4412k.getHeight();
        f4420s = f4424w / 2.0f;
        f4421t = f4422u - (f4424w / 2.0f);
        f4407f = false;
        if (f4416o != null && f4417p != null && f4418q != null && f4419r != null) {
            return;
        }
        f4416o = m30a(191, f4420s - (f4424w / 2.0f));
        f4417p = m30a(191, f4421t - (f4424w / 2.0f));
        f4418q = m30a(255, f4420s - (f4424w / 2.0f));
        f4419r = m30a(255, f4421t - (f4424w / 2.0f));
    }

    public SwitchEx(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4444a = 350.0f;
        this.f4445b = 2.0f;
        this.f4446c = 16.0f;
        this.f4433I = 255;
        this.f4434J = false;
        this.f4442R = false;
        this.f4443S = new HandlerC0955o(this);
        if (this.f4447d == null) {
            this.f4447d = context.getResources();
        }
        m29a(this.f4447d);
        this.f4431G = ViewConfiguration.getPressedStateDuration() + ViewConfiguration.getTapTimeout();
        this.f4432H = ViewConfiguration.get(context).getScaledTouchSlop();
        this.f4425A = this.f4434J ? f4420s : f4421t;
        this.f4451z = this.f4425A - (f4424w / 2.0f);
        float f = getResources().getDisplayMetrics().density;
        this.f4427C = (int) ((350.0f * f) + 0.5f);
        this.f4428D = (int) ((f * 2.0f) + 0.5f);
    }

    @Override // android.widget.TextView, android.view.View
    public void setEnabled(boolean z) {
        this.f4433I = z ? 255 : 191;
        super.setEnabled(z);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public boolean isChecked() {
        return this.f4434J;
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        setChecked(!this.f4434J);
    }

    /* renamed from: a */
    private void m27a(boolean z) {
        this.f4443S.removeMessages(1);
        this.f4443S.sendMessageDelayed(this.f4443S.obtainMessage(1, Boolean.valueOf(z)), 20L);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void setChecked(boolean z) {
        m26a(z, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m26a(boolean z, boolean z2) {
        if (this.f4434J != z) {
            this.f4434J = z;
            this.f4425A = z ? f4420s : f4421t;
            this.f4451z = this.f4425A - (f4424w / 2.0f);
            if (z2) {
                invalidate();
            }
            if (!this.f4435K) {
                this.f4435K = true;
                if (this.f4440P != null) {
                    this.f4440P.onCheckedChanged(this, this.f4434J);
                }
                if (this.f4441Q != null) {
                    this.f4441Q.onCheckedChanged(this, this.f4434J);
                }
                this.f4435K = false;
            }
        }
    }

    @Override // android.widget.CompoundButton
    public void setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
        this.f4440P = onCheckedChangeListener;
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f4437M) {
            return true;
        }
        int action = motionEvent.getAction();
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        float abs = Math.abs(x - this.f4450y);
        float abs2 = Math.abs(y - this.f4449x);
        switch (action) {
            case 0:
                this.f4448e = getParent();
                if (this.f4448e != null) {
                    this.f4448e.requestDisallowInterceptTouchEvent(true);
                }
                this.f4450y = x;
                this.f4449x = y;
                this.f4426B = this.f4434J ? f4420s : f4421t;
                break;
            case 1:
            case 3:
                this.f4442R = false;
                float eventTime = (float) (motionEvent.getEventTime() - motionEvent.getDownTime());
                if (abs2 < this.f4432H && abs < this.f4432H && eventTime < this.f4431G) {
                    if (this.f4439O == null) {
                        this.f4439O = new RunnableC0929a(this, (byte) 0);
                    }
                    if (!post(this.f4439O)) {
                        performClick();
                        break;
                    }
                } else {
                    m24b(this.f4436L);
                    this.f4438N = true;
                    invalidate();
                    return true;
                }
                break;
            case 2:
                this.f4442R = true;
                motionEvent.getEventTime();
                motionEvent.getDownTime();
                this.f4425A = (this.f4426B + motionEvent.getX()) - this.f4450y;
                if (this.f4425A >= f4420s) {
                    this.f4425A = f4420s;
                }
                if (this.f4425A <= f4421t) {
                    this.f4425A = f4421t;
                }
                this.f4436L = this.f4425A > ((f4420s - f4421t) / 2.0f) + f4421t;
                this.f4451z = this.f4425A - (f4424w / 2.0f);
                break;
        }
        this.f4438N = true;
        invalidate();
        return isEnabled();
    }

    /* renamed from: smartisanos.widget.SwitchEx$a */
    /* loaded from: classes.dex */
    private final class RunnableC0929a implements Runnable {
        private RunnableC0929a() {
        }

        /* synthetic */ RunnableC0929a(SwitchEx switchEx, byte b) {
            this();
        }

        @Override // java.lang.Runnable
        public final void run() {
            SwitchEx.this.performClick();
        }
    }

    @Override // android.widget.CompoundButton, android.view.View
    public boolean performClick() {
        m24b(!this.f4434J);
        return true;
    }

    /* renamed from: a */
    private Bitmap m31a() {
        Bitmap createBitmap = Bitmap.createBitmap(f4412k.getWidth(), f4412k.getHeight(), Bitmap.Config.ARGB_8888);
        f4414m.setBitmap(createBitmap);
        f4415n.setAlpha(this.f4433I);
        f4414m.drawBitmap(f4412k, 0.0f, 0.0f, f4415n);
        f4415n.setXfermode(f4413l);
        f4414m.drawBitmap(f4408g, this.f4451z, 0.0f, f4415n);
        f4415n.setXfermode(null);
        f4414m.drawBitmap(f4411j, 0.0f, 0.0f, f4415n);
        f4414m.drawBitmap(f4409h, this.f4451z, 0.0f, f4415n);
        return createBitmap;
    }

    /* renamed from: a */
    private static Bitmap m30a(int i, float f) {
        Bitmap createBitmap = Bitmap.createBitmap(f4412k.getWidth(), f4412k.getHeight(), Bitmap.Config.ARGB_8888);
        f4414m.setBitmap(createBitmap);
        f4415n.setAlpha(i);
        f4414m.drawBitmap(f4412k, 0.0f, 0.0f, f4415n);
        f4415n.setXfermode(f4413l);
        f4414m.drawBitmap(f4408g, f, 0.0f, f4415n);
        f4415n.setXfermode(null);
        f4414m.drawBitmap(f4411j, 0.0f, 0.0f, f4415n);
        f4414m.drawBitmap(f4409h, f, 0.0f, f4415n);
        return createBitmap;
    }

    @Override // android.widget.CompoundButton, android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        Bitmap m31a;
        if (f4407f) {
            m29a(this.f4447d);
        }
        canvas.save();
        float f = f4420s - (f4424w / 2.0f);
        float f2 = f4421t - (f4424w / 2.0f);
        if (this.f4433I == 255) {
            if (this.f4451z == f2) {
                m31a = f4419r;
            } else if (this.f4451z == f) {
                m31a = f4418q;
            } else {
                m31a = m31a();
            }
        } else if (this.f4451z == f2) {
            m31a = f4417p;
        } else if (this.f4451z == f) {
            m31a = f4416o;
        } else {
            m31a = m31a();
        }
        f4415n.setAlpha(this.f4433I);
        canvas.drawBitmap(m31a, 0.0f, this.f4428D, f4415n);
        canvas.restore();
        if (this.f4451z > f2) {
            if (this.f4451z < f) {
                if (!this.f4442R) {
                    this.f4437M = true;
                    m25b();
                    return;
                }
                return;
            }
            this.f4437M = false;
            this.f4429E = f4420s;
            if (this.f4438N) {
                m27a(true);
                this.f4438N = false;
                return;
            }
            return;
        }
        this.f4437M = false;
        this.f4429E = f4421t;
        if (this.f4438N) {
            m27a(false);
            this.f4438N = false;
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension((int) f4422u, (int) (f4423v + (2.0f * this.f4428D)));
    }

    /* renamed from: b */
    private void m24b(boolean z) {
        this.f4430F = z ? this.f4427C : -this.f4427C;
        this.f4429E = this.f4425A;
        m25b();
    }

    /* renamed from: b */
    private void m25b() {
        this.f4429E += (this.f4430F * 16.0f) / 1000.0f;
        if (this.f4429E > f4421t) {
            if (this.f4429E >= f4420s) {
                this.f4437M = false;
                this.f4429E = f4420s;
                m27a(true);
            }
        } else {
            this.f4437M = false;
            this.f4429E = f4421t;
            m27a(false);
        }
        this.f4425A = this.f4429E;
        this.f4451z = this.f4425A - (f4424w / 2.0f);
        invalidate();
    }

    @Override // android.view.View
    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(SwitchEx.class.getName());
        accessibilityEvent.setChecked(this.f4434J);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(SwitchEx.class.getName());
        accessibilityNodeInfo.setChecked(this.f4434J);
    }
}
