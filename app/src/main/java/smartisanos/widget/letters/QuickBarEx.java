package smartisanos.widget.letters;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.smartisanos.smartfolder.aoa.R;

import java.util.Locale;

import smartisanos.widget.SurnameGridView;

/* loaded from: classes.dex */
public class QuickBarEx extends LinearLayout {

    /* renamed from: a */
    public static final boolean f4526a = Log.isLoggable("QuickBarEx", Log.DEBUG);

    /* renamed from: b */
    public static int f4527b = 1;

    /* renamed from: c */
    public static int f4528c = 2;

    /* renamed from: d */
    public static int f4529d = 0;

    /* renamed from: e */
    public static int f4530e = 1;

    /* renamed from: f */
    private Context f4531f;

    /* renamed from: g */
    public LettersBar f4532g;

    /* renamed from: h */
    private int f4533h;

    /* renamed from: i */
    private int f4534i;

    /* renamed from: j */
    private int f4535j;

    /* renamed from: k */
    private int f4536k;

    /* renamed from: l */
    private float f4537l;

    /* renamed from: m */
    private float f4538m;

    /* renamed from: n */
    private float f4539n;

    /* renamed from: o */
    public InterfaceC0950a f4540o;

    /* renamed from: p */
    public boolean f4541p;

    /* renamed from: q */
    public boolean f4542q;

    /* renamed from: r */
    private PopupWindow f4543r;

    /* renamed from: s */
    private SurnameGridView f4544s;

    /* renamed from: t */
    private SurnameFlowLayout f4545t;

    /* renamed from: u */
    private int f4546u;

    /* renamed from: v */
    private PopupWindow f4547v;

    /* renamed from: w */
    private TextView f4548w;

    /* renamed from: x */
    private boolean f4549x;

    /* renamed from: y */
    private int f4550y;

    public QuickBarEx(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /* renamed from: smartisanos.widget.letters.QuickBarEx$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0950a {
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f4535j = View.MeasureSpec.getSize(i2);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Point point = null;
        boolean z;
        View childAt;
        View childAt2;
        int i;
        if (!AlphabetsConfig.m4a(Locale.getDefault().getLanguage())) {
            return super.dispatchTouchEvent(motionEvent);
        }
        int action = motionEvent.getAction();
        motionEvent.getX();
        motionEvent.getY();
        float rawX = motionEvent.getRawX();
        switch (action) {
            case 0:
                this.f4549x = false;
                this.f4539n = rawX;
                this.f4538m = rawX;
                if (this.f4536k == 1) {
                    this.f4537l = 0.0f;
                }
                if (this.f4536k == 3) {
                    this.f4537l = rawX - this.f4534i;
                }
                if (this.f4536k == 3 && this.f4543r != null && this.f4543r.isShowing()) {
                    this.f4543r.dismiss();
                    z = false;
                    break;
                }
                z = false;
                break;
            case 1:
            case 3:
                if (this.f4549x) {
                    if (AlphabetsConfig.m3b(Locale.getDefault().getLanguage()) == 1) {
                        if (this.f4550y >= 0 && this.f4545t != null && (childAt2 = this.f4545t.getChildAt(this.f4550y)) != null) {
                            childAt2.setPressed(false);
                            childAt2.performClick();
                        }
                        this.f4550y = -1;
                        z = false;
                        break;
                    } else {
                        if (this.f4550y >= 0 && this.f4544s != null && (childAt = this.f4544s.getChildAt(this.f4550y)) != null) {
                            childAt.setPressed(false);
                            this.f4544s.performItemClick(childAt, this.f4550y, -1L);
                        }
                        this.f4550y = -1;
                        z = false;
                        break;
                    }
                } else {
                    this.f4537l = 0.0f;
                    if (getX() != this.f4533h && this.f4536k == 2) {
                        if (this.f4539n - this.f4538m <= 0.0f) {
                            float x = getX();
                            ((WindowManager) this.f4531f.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getSize(new Point());
                            if (x < point.x - 150) {
                                AnimatorSet animatorSet = new AnimatorSet();
                                animatorSet.play(ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("x", getX(), this.f4534i)));
                                animatorSet.setDuration(200L);
                                animatorSet.addListener(new C0951c(this));
                                animatorSet.start();
                                z = true;
                                break;
                            }
                        }
                        if (this.f4543r != null) {
                            this.f4543r.dismiss();
                            this.f4543r = null;
                            this.f4544s = null;
                            this.f4545t = null;
                        }
                        if (this.f4547v != null) {
                            this.f4547v.dismiss();
                            this.f4547v = null;
                            this.f4548w = null;
                        }
                        AnimatorSet animatorSet2 = new AnimatorSet();
                        animatorSet2.play(ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat("x", getX(), this.f4533h)));
                        animatorSet2.setDuration(200L);
                        animatorSet2.addListener(new C0952d(this));
                        animatorSet2.start();
                        z = true;
                    }
                    z = false;
                    break;
                }
            case 2:
                if (!this.f4549x) {
                    this.f4538m = this.f4539n;
                    this.f4539n = rawX;
                    if (this.f4536k == 1) {
                        if (rawX < this.f4533h) {
                            m12a(2);
                            m13a(rawX, this.f4537l);
                            z = true;
                            break;
                        }
                        z = false;
                        break;
                    } else {
                        if (this.f4536k == 2) {
                            m13a(rawX, this.f4537l);
                        } else {
                            if (this.f4536k == 3 && this.f4539n - this.f4538m > 40.0f && this.f4538m > this.f4534i) {
                                m12a(2);
                                m13a(rawX, this.f4537l);
                            }
                            z = false;
                        }
                        z = true;
                    }
                } else if (this.f4543r != null && this.f4543r.isShowing()) {
                    int rawX2 = (int) motionEvent.getRawX();
                    int rawY = (int) motionEvent.getRawY();
                    if (AlphabetsConfig.m3b(Locale.getDefault().getLanguage()) == 1) {
                        if (this.f4545t != null) {
                            int[] iArr = new int[2];
                            this.f4545t.getLocationOnScreen(iArr);
                            int i2 = 0;
                            while (true) {
                                if (i2 >= this.f4545t.getChildCount()) {
                                    i = -1;
                                } else {
                                    View childAt3 = this.f4545t.getChildAt(i2);
                                    Rect rect = new Rect();
                                    childAt3.getHitRect(rect);
                                    if (rect.contains(rawX2 - iArr[0], rawY - iArr[1])) {
                                        i = i2;
                                    } else {
                                        i2++;
                                    }
                                }
                            }

                        }
                    } else if (this.f4544s != null) {
                        int[] iArr2 = new int[2];
                        this.f4544s.getLocationOnScreen(iArr2);
                        int pointToPosition = this.f4544s.pointToPosition(rawX2 - iArr2[0], rawY - iArr2[1]);
                        if (pointToPosition >= 0) {
                            if (this.f4550y != pointToPosition) {
                                View childAt7 = this.f4544s.getChildAt(this.f4550y);
                                if (childAt7 != null) {
                                    childAt7.setPressed(false);
                                    m11a((View) null);
                                }
                                View childAt8 = this.f4544s.getChildAt(pointToPosition);
                                if (childAt8 != null) {
                                    childAt8.setPressed(true);
                                    childAt8.setTag((String) this.f4544s.getAdapter().getItem(pointToPosition));
                                    m11a(childAt8);
                                }
                            }
                        } else {
                            View childAt9 = this.f4544s.getChildAt(this.f4550y);
                            if (childAt9 != null) {
                                childAt9.setPressed(false);
                                m11a((View) null);
                            }
                        }
                        this.f4550y = pointToPosition;
                    }
                    z = false;
                    break;
                } else {
                    z = false;
                    break;
                }
                break;
            default:
                z = false;
                break;
        }
        return z || super.dispatchTouchEvent(motionEvent);
    }

    /* renamed from: a */
    private void m12a(int i) {
        this.f4536k = i;
        this.f4532g.m17a(i == 1);
    }

    /* renamed from: a */
    private void m13a(float f, float f2) {
        float f3 = f - f2;
        if (f3 >= this.f4533h) {
            setX(this.f4533h);
            m12a(1);
        } else if (f3 <= this.f4534i) {
            setX(this.f4534i);
            m12a(3);
        } else if (f3 > this.f4534i) {
            setX(f3);
            m12a(2);
        }
    }

    /* renamed from: a */
    private int m14a() {
        return (int) ((this.f4531f.getResources().getDisplayMetrics().density * 20.0d) + 0.5d);
    }

    /* renamed from: a */
    private void m11a(View view) {
        if (view == null) {
            if (this.f4547v != null) {
                this.f4547v.dismiss();
                return;
            }
            return;
        }
        if (this.f4547v == null) {
            this.f4547v = new PopupWindow();
            this.f4547v.setWindowLayoutMode(-2, -2);
            this.f4547v.setBackgroundDrawable(getResources().getDrawable(17170445));
            this.f4547v.setClippingEnabled(true);
            this.f4548w = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.surname_second_popup_ex, (ViewGroup) null);
            if (AlphabetsConfig.m3b(Locale.getDefault().getLanguage()) == 1) {
                this.f4548w.setPadding(m14a(), this.f4548w.getPaddingTop(), m14a(), this.f4548w.getPaddingBottom());
            }
        }
        int[] r0 = new int[2];
        this.f4547v.setContentView(this.f4548w);
        view.getLocationOnScreen(r0);
        Log.d("QuickBarEx", "original anchorViewLocation[0] = " + r0[0] + ", anchorViewLocation[1] = " + r0[1]);
        this.f4548w.setText(String.valueOf(view.getTag()));
        this.f4548w.measure(0, 0);
        int measuredWidth = this.f4548w.getMeasuredWidth();
        int measuredHeight = this.f4548w.getMeasuredHeight();
        int width = view.getWidth();
        int height = view.getHeight();
        int[] iArr = {0,  this.f4546u};
        Log.d("QuickBarEx", "adjusted anchorViewLocation[0] = " + iArr[0] + ", anchorViewLocation[1] = " + iArr[1]);
        int i = iArr[1] + (height / 2);
        int i2 = ((width / 2) + iArr[0]) - (measuredWidth / 2);
        int i3 = (i - measuredHeight) - 20;
        if (f4526a) {
            Log.d("QuickBarEx", "x = " + i2 + ", width = " + measuredWidth + ", anchorView.getWidth() = " + view.getWidth());
            Log.d("QuickBarEx", "y = " + i3 + ", height = " + measuredHeight + ", anchorView.getHeight() = " + view.getHeight());
        }
        this.f4547v.showAtLocation(this, 51, i2, i3);
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (i == 8 && view == this && this.f4543r != null && this.f4543r.isShowing()) {
            this.f4543r.dismiss();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static /* synthetic */ void m6c(QuickBarEx quickBarEx) {
        quickBarEx.setX(quickBarEx.f4534i);
        quickBarEx.m12a(3);
        quickBarEx.f4532g.m16b(true);
        quickBarEx.invalidate();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static /* synthetic */ void m5d(QuickBarEx quickBarEx) {
        quickBarEx.m12a(1);
        quickBarEx.setX(quickBarEx.f4533h);
        quickBarEx.f4532g.m16b(false);
        quickBarEx.invalidate();
    }
}
