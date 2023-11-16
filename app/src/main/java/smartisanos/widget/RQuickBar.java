package smartisanos.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.smartisanos.smartfolder.aoa.R;

import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes.dex */
public class RQuickBar extends View {

    /* renamed from: a */
    public static int f4306a = 0;

    /* renamed from: b */
    public static int f4307b = 1;

    /* renamed from: c */
    public static int f4308c = 1;

    /* renamed from: d */
    public static int f4309d = 2;

    /* renamed from: A */
    private long f4310A;

    /* renamed from: B */
    private float f4311B;

    /* renamed from: C */
    private float f4312C;

    /* renamed from: D */
    private float f4313D;

    /* renamed from: E */
    private float f4314E;

    /* renamed from: F */
    public boolean f4315F;

    /* renamed from: G */
    public boolean f4316G;

    /* renamed from: H */
    private int f4317H;

    /* renamed from: I */
    private int f4318I;

    /* renamed from: J */
    private int f4319J;

    /* renamed from: K */
    private int f4320K;

    /* renamed from: L */
    private int f4321L;

    /* renamed from: M */
    private int f4322M;

    /* renamed from: N */
    private int f4323N;

    /* renamed from: O */
    private int f4324O;

    /* renamed from: P */
    private int f4325P;

    /* renamed from: Q */
    private int f4326Q;

    /* renamed from: R */
    private int f4327R;

    /* renamed from: S */
    private int f4328S;

    /* renamed from: T */
    private int f4329T;

    /* renamed from: U */
    private boolean f4330U;

    /* renamed from: V */
    private boolean f4331V;

    /* renamed from: W */
    private int f4332W;

    /* renamed from: aa */
    private int f4333aa;

    /* renamed from: ab */
    private boolean f4334ab;

    /* renamed from: ac */
    private boolean f4335ac;

    /* renamed from: ad */
    private InterfaceC0926a f4336ad;

    /* renamed from: ae */
    private int f4337ae;

    /* renamed from: af */
    private int f4338af;

    /* renamed from: ag */
    private boolean f4339ag;

    /* renamed from: ah */
    private MotionEvent f4340ah;

    /* renamed from: ai */
    private boolean f4341ai;

    /* renamed from: aj */
    private PopupWindow f4342aj;

    /* renamed from: ak */
    private int f4343ak;

    /* renamed from: al */
    private int f4344al;

    /* renamed from: am */
    private boolean f4345am;

    /* renamed from: an */
    private boolean f4346an;

    /* renamed from: ao */
    private int f4347ao;

    /* renamed from: ap */
    private SurnameGridView f4348ap;

    /* renamed from: aq */
    private PopupWindow f4349aq;

    /* renamed from: ar */
    private TextView f4350ar;

    /* renamed from: as */
    private Runnable f4351as;

    /* renamed from: e */
    boolean f4352e;

    /* renamed from: f */
    private Context f4353f;

    /* renamed from: g */
    private int f4354g;

    /* renamed from: h */
    private ArrayList<String> f4355h;

    /* renamed from: i */
    private String[] f4356i;

    /* renamed from: j */
    private int f4357j;

    /* renamed from: k */
    private Paint f4358k;

    /* renamed from: l */
    private NinePatchDrawable f4359l;

    /* renamed from: m */
    private NinePatch f4360m;

    /* renamed from: n */
    private Bitmap f4361n;

    /* renamed from: o */
    private int[] f4362o;

    /* renamed from: p */
    private Bitmap[][] f4363p;

    /* renamed from: q */
    private Bitmap f4364q;

    /* renamed from: r */
    private int f4365r;

    /* renamed from: s */
    private int f4366s;

    /* renamed from: t */
    private int f4367t;

    /* renamed from: u */
    private float f4368u;

    /* renamed from: v */
    private float f4369v;

    /* renamed from: w */
    private float f4370w;

    /* renamed from: x */
    private int f4371x;

    /* renamed from: y */
    private int f4372y;

    /* renamed from: z */
    private int f4373z;

    public RQuickBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /* renamed from: smartisanos.widget.RQuickBar$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0926a {
        /* renamed from: a */
        boolean m46a();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE && mode != 1073741824) {
            size = 0;
        }
        this.f4371x = size;
        this.f4314E = this.f4371x / 9.0f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        float f;
        super.onDraw(canvas);
        int height = getHeight();
        if ((this.f4352e || getX() != this.f4366s) && this.f4359l != null) {
            if (this.f4371x != this.f4359l.copyBounds().height()) {
                this.f4359l.setBounds(new Rect(0, 0, this.f4372y, this.f4371x));
            }
            this.f4359l.draw(canvas);
        }
        int size = height / this.f4355h.size();
        this.f4339ag = false;
        int max = this.f4339ag ? size : Math.max(size, this.f4325P);
        ArrayList<Integer> m56a = m56a();
        if (this.f4339ag || m56a.size() == 0) {
            i = 0;
        } else {
            if (m56a.size() != this.f4355h.size()) {
                int size2 = (m56a.size() * 2) - 1;
                int i3 = (m56a.size() < 2 || m56a.get(m56a.size() + (-2)).intValue() != this.f4355h.size() + (-2)) ? size2 : size2 - 1;
                int i4 = (this.f4371x - (this.f4325P * i3)) / i3;
                if (i4 > 0) {
                    i = i4;
                }
            }
            i = 0;
        }
        boolean z = i > 0 && m56a.size() < this.f4355h.size();
        int i5 = 0;
        Iterator<Integer> it = m56a.iterator();
        while (true) {
            int i6 = i5;
            if (!it.hasNext()) {
                break;
            }
            int intValue = it.next().intValue();
            float measureText = this.f4318I - (this.f4358k.measureText(this.f4355h.get(intValue)) / 2.0f);
            float f2 = ((max + i) * i6) + (max / 2);
            if (intValue == this.f4357j && this.f4365r == 3) {
                this.f4358k.setColor(-1);
                this.f4358k.setFakeBoldText(true);
                canvas.drawBitmap(this.f4361n, this.f4319J, f2 - this.f4320K, (Paint) null);
            } else {
                this.f4358k.setColor(this.f4357j == -1 ? this.f4338af : this.f4337ae);
                this.f4358k.setFakeBoldText(false);
            }
            if (i6 >= this.f4328S) {
                canvas.drawText(this.f4355h.get(intValue), measureText, f2 + this.f4323N, this.f4358k);
            } else if (this.f4362o != null && i6 < this.f4362o.length && this.f4363p != null) {
                float f3 = measureText - this.f4321L;
                float f4 = f2 - this.f4322M;
                if (this.f4357j == i6 && this.f4365r == 3) {
                    canvas.drawBitmap(this.f4363p[i6][2], f3, f4, (Paint) null);
                } else if (this.f4357j != -1) {
                    canvas.drawBitmap(this.f4363p[i6][1], f3, f4, (Paint) null);
                } else {
                    canvas.drawBitmap(this.f4363p[i6][0], f3, f4, (Paint) null);
                }
            }
            i5 = i6 + 1;
            if (z && intValue != this.f4355h.size() - 2) {
                canvas.drawBitmap(this.f4364q, ((this.f4373z + this.f4327R) - this.f4364q.getWidth()) / 2, (((max + i) * i5) + (max / 2)) - this.f4326Q, (Paint) null);
                i5++;
            }
        }
        if (this.f4352e && this.f4331V && this.f4311B > this.f4373z) {
            int i7 = (int) ((this.f4311B - this.f4373z) / this.f4313D);
            int i8 = (int) (this.f4312C / this.f4314E);
            int i9 = i7 == 3 ? i7 - 1 : i7;
            if (i8 >= 8) {
                if (i8 == 9) {
                    i8--;
                }
                i2 = i8;
                f = this.f4371x;
            } else {
                i2 = i8;
                f = (i8 + 1) * this.f4314E;
            }
            this.f4360m.draw(canvas, new RectF(this.f4373z + (i9 * this.f4313D), i2 * this.f4314E, ((i9 + 1) * this.f4313D) + this.f4373z, f));
        }
        canvas.restore();
    }

    /* renamed from: a */
    private ArrayList<Integer> m56a() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i = 1;
        if (!this.f4339ag && this.f4371x / this.f4355h.size() < this.f4325P) {
            i = Math.max(2, ((int) Math.ceil(this.f4355h.size() / ((this.f4371x / this.f4325P) - 1))) * 2);
        }
        arrayList.add(0);
        if (i < this.f4355h.size() / 2) {
            for (int i2 = i; i2 < this.f4355h.size() - 1; i2 += i) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (i < this.f4355h.size()) {
            arrayList.add(Integer.valueOf(this.f4355h.size() - 1));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:130:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0298  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        View childAt;
        super.dispatchTouchEvent(motionEvent);
        int action = motionEvent.getAction();
        if (this.f4334ab && ((!this.f4335ac || action != 2) && !this.f4315F)) {
            this.f4335ac = false;
            float y = motionEvent.getY();
            float rawX = motionEvent.getRawX();
            this.f4311B = motionEvent.getX();
            this.f4312C = y;
            int i = this.f4357j;
            int height = (int) ((y / getHeight()) * this.f4355h.size());
            switch (action) {
                case 0:
                    this.f4341ai = false;
                    this.f4369v = rawX;
                    this.f4370w = rawX;
                    this.f4332W = m55a(this.f4311B, this.f4312C);
                    this.f4330U = false;
                    this.f4331V = true;
                    this.f4329T = this.f4365r;
                    this.f4352e = true;
                    if (this.f4365r == 4) {
                        this.f4368u = rawX - this.f4367t;
                    }
                    if (this.f4365r == 3) {
                        this.f4368u = 0.0f;
                        if (i != height && this.f4336ad != null && height >= 0 && height < this.f4355h.size()) {
                            this.f4357j = height;
                        }
                    }
                    invalidate();
                    if (this.f4365r == 4) {
                        if (this.f4342aj != null && this.f4342aj.isShowing()) {
                            this.f4342aj.dismiss();
                            this.f4345am = true;
                        }
                        if (this.f4346an) {
                            this.f4340ah = motionEvent;
                            postDelayed(this.f4351as, ViewConfiguration.getLongPressTimeout());
                            break;
                        }
                    }
                    break;
                case 1:
                case 3:
                    m47c();
                    if (this.f4341ai) {
                        if (this.f4347ao >= 0 && this.f4348ap != null && (childAt = this.f4348ap.getChildAt(this.f4347ao)) != null) {
                            childAt.setPressed(false);
                            this.f4348ap.performItemClick(childAt, this.f4347ao, -1L);
                        }
                        this.f4347ao = -1;
                        this.f4331V = false;
                        invalidate();
                        break;
                    } else {
                        if (this.f4345am) {
                            this.f4345am = false;
                            if (this.f4365r == 4) {
                                this.f4331V = false;
                                invalidate();
                                break;
                            }
                        }
                        if (this.f4365r == 3 && this.f4336ad != null && height >= 0 && height < this.f4355h.size()) {
                            InterfaceC0926a interfaceC0926a = this.f4336ad;
                            this.f4355h.get(height);
                            if (interfaceC0926a.m46a()) {
                                this.f4357j = height;
                            }
                        }
                        int i2 = this.f4329T;
                        int i3 = this.f4365r;
                        float x = motionEvent.getX();
                        int i4 = this.f4332W;
                        if (this.f4330U) {
                            this.f4331V = false;
                        } else {
                            if (x >= this.f4373z) {
                                if (i2 == 4 && i3 == 4) {
                                    if (i4 == 27) {
                                        m50b();
                                    } else if (this.f4336ad != null && i4 >= 0 && i4 < this.f4356i.length) {
                                        if (this.f4336ad.m46a()) {
                                            this.f4357j = i4 - this.f4328S;
                                            invalidate();
                                        }
                                        m50b();
                                    }
                                }
                            }
                            this.f4368u = 0.0f;
                            if (getX() == this.f4366s) {
                                if (this.f4365r == 1) {
                                    this.f4331V = false;
                                    invalidate();
                                    if (this.f4370w - this.f4369v > 0.0f || getX() >= this.f4317H - this.f4324O) {
                                        m50b();
                                        break;
                                    } else {
                                        AnimatorSet animatorSet = new AnimatorSet();
                                        animatorSet.play(ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("x", getX(), this.f4367t)));
                                        animatorSet.setDuration(this.f4310A);
                                        animatorSet.addListener(new C0943g(this));
                                        animatorSet.start();
                                        break;
                                    }
                                }
                            } else {
                                setX(this.f4366s);
                                this.f4352e = false;
                                this.f4357j = -1;
                                this.f4365r = 3;
                                invalidate();
                                break;
                            }
                        }
                        invalidate();
                        this.f4368u = 0.0f;
                        if (getX() == this.f4366s) {
                        }
                    }
                    break;
                case 2:
                    if (!this.f4341ai) {
                        if (getX() != this.f4367t) {
                            m47c();
                        }
                        this.f4352e = true;
                        this.f4369v = this.f4370w;
                        this.f4370w = rawX;
                        this.f4333aa = m55a(this.f4311B, this.f4312C);
                        if (!this.f4330U && this.f4333aa != this.f4332W) {
                            this.f4330U = true;
                            this.f4331V = false;
                            m47c();
                        }
                        if (this.f4365r == 3) {
                            if (rawX < this.f4366s) {
                                this.f4365r = 1;
                                m49b(rawX, this.f4368u);
                                if (this.f4336ad != null) {
                                }
                            }
                        } else if (this.f4365r == 4) {
                            if (this.f4370w - this.f4369v > 5.0f && this.f4369v > this.f4367t) {
                                this.f4365r = 1;
                                m49b(rawX, this.f4368u);
                            }
                        } else if (this.f4365r == 1) {
                            m49b(rawX, this.f4368u);
                        }
                        if (((this.f4365r != 4 && this.f4365r != 1) || getX() > this.f4317H - 100) && i != height && this.f4336ad != null && height >= 0 && height < this.f4355h.size()) {
                            InterfaceC0926a interfaceC0926a2 = this.f4336ad;
                            this.f4355h.get(height);
                            if (interfaceC0926a2.m46a()) {
                                this.f4357j = height;
                            }
                        }
                        invalidate();
                        break;
                    } else if (this.f4342aj != null && this.f4342aj.isShowing()) {
                        int rawX2 = (int) motionEvent.getRawX();
                        int rawY = (int) motionEvent.getRawY();
                        if (this.f4348ap != null) {
                            int[] iArr = new int[2];
                            this.f4348ap.getLocationOnScreen(iArr);
                            int pointToPosition = this.f4348ap.pointToPosition(rawX2 - iArr[0], rawY - iArr[1]);
                            if (pointToPosition >= 0) {
                                if (this.f4347ao != pointToPosition) {
                                    View childAt2 = this.f4348ap.getChildAt(this.f4347ao);
                                    if (childAt2 != null) {
                                        childAt2.setPressed(false);
                                        m54a((View) null);
                                    }
                                    View childAt3 = this.f4348ap.getChildAt(pointToPosition);
                                    if (childAt3 != null) {
                                        childAt3.setPressed(true);
                                        childAt3.setTag((String) this.f4348ap.getAdapter().getItem(pointToPosition));
                                        m54a(childAt3);
                                    }
                                }
                            } else {
                                View childAt4 = this.f4348ap.getChildAt(this.f4347ao);
                                if (childAt4 != null) {
                                    childAt4.setPressed(false);
                                    m54a((View) null);
                                }
                            }
                            this.f4347ao = pointToPosition;
                            break;
                        }
                    }
                    break;
            }
        }
        return true;
    }

    /* renamed from: a */
    private int m55a(float f, float f2) {
        if (f < this.f4373z) {
            return -1;
        }
        return ((int) (((f - this.f4373z) / this.f4313D) + 1.0f)) + (((int) (f2 / this.f4314E)) * 3);
    }

    /* renamed from: b */
    private void m49b(float f, float f2) {
        float f3 = f - f2;
        if (f3 >= this.f4366s) {
            setX(this.f4366s);
            this.f4365r = 3;
        } else if (f3 <= this.f4367t) {
            setX(this.f4367t);
            this.f4365r = 4;
        } else if (f3 > this.f4367t) {
            setX(f3);
            this.f4365r = 1;
        }
    }

    /* renamed from: c */
    private void m47c() {
        removeCallbacks(this.f4351as);
        this.f4340ah = null;
    }

    /* renamed from: a */
    private void m54a(View view) {
        if (view == null) {
            if (this.f4349aq != null) {
                this.f4349aq.dismiss();
                return;
            }
            return;
        }
        if (this.f4349aq == null) {
            this.f4349aq = new PopupWindow();
            this.f4349aq.setWindowLayoutMode(-2, -2);
            this.f4349aq.setBackgroundDrawable(getResources().getDrawable(17170445));
            this.f4349aq.setClippingEnabled(true);
            this.f4350ar = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.surname_second_popup, (ViewGroup) null);
        }
        this.f4349aq.setContentView(this.f4350ar);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.f4350ar.setText(String.valueOf(view.getTag()));
        this.f4350ar.measure(0, 0);
        int measuredWidth = this.f4350ar.getMeasuredWidth();
        int measuredHeight = ((iArr[1] - this.f4350ar.getMeasuredHeight()) + (this.f4344al / 2)) - ((int) ((this.f4353f.getResources().getDisplayMetrics().density * 6.78000020980835d) + 0.5d));
        this.f4349aq.showAtLocation(this, 0, iArr[0] - ((measuredWidth - this.f4343ak) / 2), measuredHeight + this.f4354g);
    }

    /* renamed from: b */
    private void m50b() {
        if (this.f4342aj != null) {
            this.f4342aj.dismiss();
            this.f4342aj = null;
            this.f4348ap = null;
        }
        if (this.f4349aq != null) {
            this.f4349aq.dismiss();
            this.f4349aq = null;
            this.f4350ar = null;
        }
        m47c();
        this.f4335ac = true;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("x", getX(), this.f4366s)));
        animatorSet.setDuration(this.f4310A);
        animatorSet.addListener(new C0944h(this));
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m53a(RQuickBar rQuickBar) {
        rQuickBar.setX(rQuickBar.f4367t);
        rQuickBar.f4352e = true;
        rQuickBar.f4365r = 4;
        rQuickBar.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public  /* synthetic */ void m52a(RQuickBar rQuickBar, int i) {
        rQuickBar.f4330U = false;
        rQuickBar.f4331V = false;
        rQuickBar.f4352e = false;
        rQuickBar.f4357j = -1;
        rQuickBar.f4365r = 3;
        rQuickBar.setX(rQuickBar.f4366s);
        switch (i) {
            case 1:
                super.setVisibility(View.VISIBLE);
                break;
            case 2:
                super.setVisibility(View.GONE);
                break;
        }
        rQuickBar.invalidate();
    }
}
