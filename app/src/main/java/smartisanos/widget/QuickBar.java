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
public class QuickBar extends View {

    /* renamed from: a */
    public static int f4238a = 0;

    /* renamed from: b */
    public static int f4239b = 1;

    /* renamed from: c */
    public static int f4240c = 1;

    /* renamed from: d */
    public static int f4241d = 2;

    /* renamed from: A */
    private long f4242A;

    /* renamed from: B */
    private float f4243B;

    /* renamed from: C */
    private float f4244C;

    /* renamed from: D */
    private float f4245D;

    /* renamed from: E */
    private float f4246E;

    /* renamed from: F */
    public boolean f4247F;

    /* renamed from: G */
    public boolean f4248G;

    /* renamed from: H */
    private int f4249H;

    /* renamed from: I */
    private int f4250I;

    /* renamed from: J */
    private int f4251J;

    /* renamed from: K */
    private int f4252K;

    /* renamed from: L */
    private int f4253L;

    /* renamed from: M */
    private int f4254M;

    /* renamed from: N */
    private int f4255N;

    /* renamed from: O */
    private int f4256O;

    /* renamed from: P */
    private int f4257P;

    /* renamed from: Q */
    private int f4258Q;

    /* renamed from: R */
    private int f4259R;

    /* renamed from: S */
    private int f4260S;

    /* renamed from: T */
    private int f4261T;

    /* renamed from: U */
    private boolean f4262U;

    /* renamed from: V */
    private boolean f4263V;

    /* renamed from: W */
    private int f4264W;

    /* renamed from: aa */
    private int f4265aa;

    /* renamed from: ab */
    private boolean f4266ab;

    /* renamed from: ac */
    private boolean f4267ac;

    /* renamed from: ad */
    private InterfaceC0925a f4268ad;

    /* renamed from: ae */
    private int f4269ae;

    /* renamed from: af */
    private int f4270af;

    /* renamed from: ag */
    private boolean f4271ag;

    /* renamed from: ah */
    private MotionEvent f4272ah;

    /* renamed from: ai */
    private boolean f4273ai;

    /* renamed from: aj */
    private PopupWindow f4274aj;

    /* renamed from: ak */
    private int f4275ak;

    /* renamed from: al */
    private int f4276al;

    /* renamed from: am */
    private boolean f4277am;

    /* renamed from: an */
    private boolean f4278an;

    /* renamed from: ao */
    private int f4279ao;

    /* renamed from: ap */
    private SurnameGridView f4280ap;

    /* renamed from: aq */
    private PopupWindow f4281aq;

    /* renamed from: ar */
    private TextView f4282ar;

    /* renamed from: as */
    private Runnable f4283as;

    /* renamed from: e */
    boolean f4284e;

    /* renamed from: f */
    private Context f4285f;

    /* renamed from: g */
    private int f4286g;

    /* renamed from: h */
    private ArrayList<String> f4287h;

    /* renamed from: i */
    private String[] f4288i;

    /* renamed from: j */
    private int f4289j;

    /* renamed from: k */
    private Paint f4290k;

    /* renamed from: l */
    private NinePatchDrawable f4291l;

    /* renamed from: m */
    private NinePatch f4292m;

    /* renamed from: n */
    private Bitmap f4293n;

    /* renamed from: o */
    private int[] f4294o;

    /* renamed from: p */
    private Bitmap[][] f4295p;

    /* renamed from: q */
    private Bitmap f4296q;

    /* renamed from: r */
    private int f4297r;

    /* renamed from: s */
    private int f4298s;

    /* renamed from: t */
    private int f4299t;

    /* renamed from: u */
    private float f4300u;

    /* renamed from: v */
    private float f4301v;

    /* renamed from: w */
    private float f4302w;

    /* renamed from: x */
    private int f4303x;

    /* renamed from: y */
    private int f4304y;

    /* renamed from: z */
    private int f4305z;

    public QuickBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /* renamed from: smartisanos.widget.QuickBar$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0925a {
        /* renamed from: a */
        boolean m57a();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        if (mode != Integer.MIN_VALUE && mode != 1073741824) {
            size = 0;
        }
        this.f4303x = size;
        this.f4246E = this.f4303x / 9.0f;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        float f;
        super.onDraw(canvas);
        int height = getHeight();
        if ((this.f4284e || getX() != this.f4298s) && this.f4291l != null) {
            if (this.f4303x != this.f4291l.copyBounds().height()) {
                this.f4291l.setBounds(new Rect(0, 0, this.f4304y, this.f4303x));
            }
            this.f4291l.draw(canvas);
        }
        int size = height / this.f4287h.size();
        this.f4271ag = false;
        int max = this.f4271ag ? size : Math.max(size, this.f4257P);
        ArrayList<Integer> m67a = m67a();
        if (this.f4271ag || m67a.size() == 0) {
            i = 0;
        } else {
            if (m67a.size() != this.f4287h.size()) {
                int size2 = (m67a.size() * 2) - 1;
                int i3 = (m67a.size() < 2 || m67a.get(m67a.size() + (-2)).intValue() != this.f4287h.size() + (-2)) ? size2 : size2 - 1;
                int i4 = (this.f4303x - (this.f4257P * i3)) / i3;
                if (i4 > 0) {
                    i = i4;
                }
            }
            i = 0;
        }
        boolean z = i > 0 && m67a.size() < this.f4287h.size();
        int i5 = 0;
        Iterator<Integer> it = m67a.iterator();
        while (true) {
            int i6 = i5;
            if (!it.hasNext()) {
                break;
            }
            int intValue = it.next().intValue();
            float measureText = this.f4250I - (this.f4290k.measureText(this.f4287h.get(intValue)) / 2.0f);
            float f2 = ((max + i) * i6) + (max / 2);
            if (intValue == this.f4289j && this.f4297r == 3) {
                this.f4290k.setColor(-1);
                this.f4290k.setFakeBoldText(true);
                canvas.drawBitmap(this.f4293n, this.f4251J, f2 - this.f4252K, (Paint) null);
            } else {
                this.f4290k.setColor(this.f4289j == -1 ? this.f4270af : this.f4269ae);
                this.f4290k.setFakeBoldText(false);
            }
            if (i6 >= this.f4260S) {
                canvas.drawText(this.f4287h.get(intValue), measureText, f2 + this.f4255N, this.f4290k);
            } else if (this.f4294o != null && i6 < this.f4294o.length && this.f4295p != null) {
                float f3 = measureText - this.f4253L;
                float f4 = f2 - this.f4254M;
                if (this.f4289j == i6 && this.f4297r == 3) {
                    canvas.drawBitmap(this.f4295p[i6][2], f3, f4, (Paint) null);
                } else if (this.f4289j != -1) {
                    canvas.drawBitmap(this.f4295p[i6][1], f3, f4, (Paint) null);
                } else {
                    canvas.drawBitmap(this.f4295p[i6][0], f3, f4, (Paint) null);
                }
            }
            i5 = i6 + 1;
            if (z && intValue != this.f4287h.size() - 2) {
                canvas.drawBitmap(this.f4296q, ((this.f4305z + this.f4259R) - this.f4296q.getWidth()) / 2, (((max + i) * i5) + (max / 2)) - this.f4258Q, (Paint) null);
                i5++;
            }
        }
        if (this.f4284e && this.f4263V && this.f4243B > this.f4305z) {
            int i7 = (int) ((this.f4243B - this.f4305z) / this.f4245D);
            int i8 = (int) (this.f4244C / this.f4246E);
            int i9 = i7 == 3 ? i7 - 1 : i7;
            if (i8 >= 8) {
                if (i8 == 9) {
                    i8--;
                }
                i2 = i8;
                f = this.f4303x;
            } else {
                i2 = i8;
                f = (i8 + 1) * this.f4246E;
            }
            this.f4292m.draw(canvas, new RectF(this.f4305z + (i9 * this.f4245D), i2 * this.f4246E, ((i9 + 1) * this.f4245D) + this.f4305z, f));
        }
        canvas.restore();
    }

    /* renamed from: a */
    private ArrayList<Integer> m67a() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i = 1;
        if (!this.f4271ag && this.f4303x / this.f4287h.size() < this.f4257P) {
            i = Math.max(2, ((int) Math.ceil(this.f4287h.size() / ((this.f4303x / this.f4257P) - 1))) * 2);
        }
        arrayList.add(0);
        if (i < this.f4287h.size() / 2) {
            for (int i2 = i; i2 < this.f4287h.size() - 1; i2 += i) {
                arrayList.add(Integer.valueOf(i2));
            }
        }
        if (i < this.f4287h.size()) {
            arrayList.add(Integer.valueOf(this.f4287h.size() - 1));
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
        if (this.f4266ab && ((!this.f4267ac || action != 2) && !this.f4247F)) {
            this.f4267ac = false;
            float y = motionEvent.getY();
            float rawX = motionEvent.getRawX();
            this.f4243B = motionEvent.getX();
            this.f4244C = y;
            int i = this.f4289j;
            int height = (int) ((y / getHeight()) * this.f4287h.size());
            switch (action) {
                case 0:
                    this.f4273ai = false;
                    this.f4301v = rawX;
                    this.f4302w = rawX;
                    this.f4264W = m66a(this.f4243B, this.f4244C);
                    this.f4262U = false;
                    this.f4263V = true;
                    this.f4261T = this.f4297r;
                    this.f4284e = true;
                    if (this.f4297r == 4) {
                        this.f4300u = rawX - this.f4299t;
                    }
                    if (this.f4297r == 3) {
                        this.f4300u = 0.0f;
                        if (i != height && this.f4268ad != null && height >= 0 && height < this.f4287h.size()) {
                            this.f4289j = height;
                        }
                    }
                    invalidate();
                    if (this.f4297r == 4) {
                        if (this.f4274aj != null && this.f4274aj.isShowing()) {
                            this.f4274aj.dismiss();
                            this.f4277am = true;
                        }
                        if (this.f4278an) {
                            this.f4272ah = motionEvent;
                            postDelayed(this.f4283as, ViewConfiguration.getLongPressTimeout());
                            break;
                        }
                    }
                    break;
                case 1:
                case 3:
                    m58c();
                    if (this.f4273ai) {
                        if (this.f4279ao >= 0 && this.f4280ap != null && (childAt = this.f4280ap.getChildAt(this.f4279ao)) != null) {
                            childAt.setPressed(false);
                            this.f4280ap.performItemClick(childAt, this.f4279ao, -1L);
                        }
                        this.f4279ao = -1;
                        this.f4263V = false;
                        invalidate();
                        break;
                    } else {
                        if (this.f4277am) {
                            this.f4277am = false;
                            if (this.f4297r == 4) {
                                this.f4263V = false;
                                invalidate();
                                break;
                            }
                        }
                        if (this.f4297r == 3 && this.f4268ad != null && height >= 0 && height < this.f4287h.size()) {
                            InterfaceC0925a interfaceC0925a = this.f4268ad;
                            this.f4287h.get(height);
                            if (interfaceC0925a.m57a()) {
                                this.f4289j = height;
                            }
                        }
                        int i2 = this.f4261T;
                        int i3 = this.f4297r;
                        float x = motionEvent.getX();
                        int i4 = this.f4264W;
                        if (this.f4262U) {
                            this.f4263V = false;
                        } else {
                            if (x >= this.f4305z) {
                                if (i2 == 4 && i3 == 4) {
                                    if (i4 == 27) {
                                        m61b();
                                    } else if (this.f4268ad != null && i4 >= 0 && i4 < this.f4288i.length) {
                                        if (this.f4268ad.m57a()) {
                                            this.f4289j = i4 - this.f4260S;
                                            invalidate();
                                        }
                                        m61b();
                                    }
                                }
                            }
                            this.f4300u = 0.0f;
                            if (getX() == this.f4298s) {
                                if (this.f4297r == 1) {
                                    this.f4263V = false;
                                    invalidate();
                                    if (this.f4302w - this.f4301v > 0.0f || getX() >= this.f4249H - this.f4256O) {
                                        m61b();
                                        break;
                                    } else {
                                        AnimatorSet animatorSet = new AnimatorSet();
                                        animatorSet.play(ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("x", getX(), this.f4299t)));
                                        animatorSet.setDuration(this.f4242A);
                                        animatorSet.addListener(new C0933d(this));
                                        animatorSet.start();
                                        break;
                                    }
                                }
                            } else {
                                setX(this.f4298s);
                                this.f4284e = false;
                                this.f4289j = -1;
                                this.f4297r = 3;
                                invalidate();
                                break;
                            }
                        }
                        invalidate();
                        this.f4300u = 0.0f;
                        if (getX() == this.f4298s) {
                        }
                    }
                    break;
                case 2:
                    if (!this.f4273ai) {
                        if (getX() != this.f4299t) {
                            m58c();
                        }
                        this.f4284e = true;
                        this.f4301v = this.f4302w;
                        this.f4302w = rawX;
                        this.f4265aa = m66a(this.f4243B, this.f4244C);
                        if (!this.f4262U && this.f4265aa != this.f4264W) {
                            this.f4262U = true;
                            this.f4263V = false;
                            m58c();
                        }
                        if (this.f4297r == 3) {
                            if (rawX < this.f4298s) {
                                this.f4297r = 1;
                                m60b(rawX, this.f4300u);
                                if (this.f4268ad != null) {
                                }
                            }
                        } else if (this.f4297r == 4) {
                            if (this.f4302w - this.f4301v > 5.0f && this.f4301v > this.f4299t) {
                                this.f4297r = 1;
                                m60b(rawX, this.f4300u);
                            }
                        } else if (this.f4297r == 1) {
                            m60b(rawX, this.f4300u);
                        }
                        if (((this.f4297r != 4 && this.f4297r != 1) || getX() > this.f4249H - 100) && i != height && this.f4268ad != null && height >= 0 && height < this.f4287h.size()) {
                            InterfaceC0925a interfaceC0925a2 = this.f4268ad;
                            this.f4287h.get(height);
                            if (interfaceC0925a2.m57a()) {
                                this.f4289j = height;
                            }
                        }
                        invalidate();
                        break;
                    } else if (this.f4274aj != null && this.f4274aj.isShowing()) {
                        int rawX2 = (int) motionEvent.getRawX();
                        int rawY = (int) motionEvent.getRawY();
                        if (this.f4280ap != null) {
                            int[] iArr = new int[2];
                            this.f4280ap.getLocationOnScreen(iArr);
                            int pointToPosition = this.f4280ap.pointToPosition(rawX2 - iArr[0], rawY - iArr[1]);
                            if (pointToPosition >= 0) {
                                if (this.f4279ao != pointToPosition) {
                                    View childAt2 = this.f4280ap.getChildAt(this.f4279ao);
                                    if (childAt2 != null) {
                                        childAt2.setPressed(false);
                                        m65a((View) null);
                                    }
                                    View childAt3 = this.f4280ap.getChildAt(pointToPosition);
                                    if (childAt3 != null) {
                                        childAt3.setPressed(true);
                                        childAt3.setTag((String) this.f4280ap.getAdapter().getItem(pointToPosition));
                                        m65a(childAt3);
                                    }
                                }
                            } else {
                                View childAt4 = this.f4280ap.getChildAt(this.f4279ao);
                                if (childAt4 != null) {
                                    childAt4.setPressed(false);
                                    m65a((View) null);
                                }
                            }
                            this.f4279ao = pointToPosition;
                            break;
                        }
                    }
                    break;
            }
        }
        return true;
    }

    /* renamed from: a */
    private int m66a(float f, float f2) {
        if (f < this.f4305z) {
            return -1;
        }
        return ((int) (((f - this.f4305z) / this.f4245D) + 1.0f)) + (((int) (f2 / this.f4246E)) * 3);
    }

    /* renamed from: b */
    private void m60b(float f, float f2) {
        float f3 = f - f2;
        if (f3 >= this.f4298s) {
            setX(this.f4298s);
            this.f4297r = 3;
        } else if (f3 <= this.f4299t) {
            setX(this.f4299t);
            this.f4297r = 4;
        } else if (f3 > this.f4299t) {
            setX(f3);
            this.f4297r = 1;
        }
    }

    /* renamed from: c */
    private void m58c() {
        removeCallbacks(this.f4283as);
        this.f4272ah = null;
    }

    /* renamed from: a */
    private void m65a(View view) {
        if (view == null) {
            if (this.f4281aq != null) {
                this.f4281aq.dismiss();
                return;
            }
            return;
        }
        if (this.f4281aq == null) {
            this.f4281aq = new PopupWindow();
            this.f4281aq.setWindowLayoutMode(-2, -2);
            this.f4281aq.setBackgroundDrawable(getResources().getDrawable(17170445));
            this.f4281aq.setClippingEnabled(true);
            this.f4282ar = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.surname_second_popup, (ViewGroup) null);
        }
        this.f4281aq.setContentView(this.f4282ar);
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        this.f4282ar.setText(String.valueOf(view.getTag()));
        this.f4282ar.measure(0, 0);
        int measuredWidth = this.f4282ar.getMeasuredWidth();
        int measuredHeight = ((iArr[1] - this.f4282ar.getMeasuredHeight()) + (this.f4276al / 2)) - ((int) ((this.f4285f.getResources().getDisplayMetrics().density * 6.78000020980835d) + 0.5d));
        this.f4281aq.showAtLocation(this, 0, iArr[0] - ((measuredWidth - this.f4275ak) / 2), measuredHeight + this.f4286g);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 8 && view == this) {
            if (this.f4274aj != null && this.f4274aj.isShowing()) {
                this.f4274aj.dismiss();
            }
            if (this.f4281aq != null && this.f4281aq.isShowing()) {
                this.f4281aq.dismiss();
            }
        }
    }

    /* renamed from: b */
    private void m61b() {
        if (this.f4274aj != null) {
            this.f4274aj.dismiss();
            this.f4274aj = null;
            this.f4280ap = null;
        }
        if (this.f4281aq != null) {
            this.f4281aq.dismiss();
            this.f4281aq = null;
            this.f4282ar = null;
        }
        m58c();
        this.f4267ac = true;
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("x", getX(), this.f4298s)));
        animatorSet.setDuration(this.f4242A);
        animatorSet.addListener(new C0934e(this));
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m64a(QuickBar quickBar) {
        quickBar.setX(quickBar.f4299t);
        quickBar.f4284e = true;
        quickBar.f4297r = 4;
        quickBar.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public  /* synthetic */ void m63a(QuickBar quickBar, int i) {
        quickBar.f4262U = false;
        quickBar.f4263V = false;
        quickBar.f4284e = false;
        quickBar.f4289j = -1;
        quickBar.f4297r = 3;
        quickBar.setX(quickBar.f4298s);
        switch (i) {
            case 1:
                super.setVisibility(View.VISIBLE);
                break;
            case 2:
                super.setVisibility(View.GONE);
                break;
        }
        quickBar.invalidate();
    }
}
