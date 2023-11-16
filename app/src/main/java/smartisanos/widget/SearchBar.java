package smartisanos.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.text.method.TextKeyListener;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/* loaded from: classes.dex */
public class SearchBar extends RelativeLayout implements View.OnClickListener {

    /* renamed from: a */
    private static final String f4377a = SearchBar.class.getSimpleName();

    /* renamed from: b */
    private EditText f4378b;

    /* renamed from: c */
    public TextView f4379c;

    /* renamed from: d */
    private View f4380d;

    /* renamed from: e */
    private View f4381e;

    /* renamed from: f */
    private View f4382f;

    /* renamed from: g */
    public ImageView f4383g;

    /* renamed from: h */
    public InterfaceC0928b f4384h;

    /* renamed from: i */
    public InterfaceC0927a f4385i;

    /* renamed from: j */
    private boolean f4386j;

    /* renamed from: k */
    public boolean f4387k;

    /* renamed from: l */
    public boolean f4388l;

    /* renamed from: m */
    private boolean f4389m;

    /* renamed from: n */
    private boolean f4390n;

    /* renamed from: o */
    private boolean f4391o;

    /* renamed from: p */
    private int f4392p;

    /* renamed from: q */
    private int f4393q;

    /* renamed from: r */
    private int f4394r;

    public SearchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /* renamed from: smartisanos.widget.SearchBar$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0927a {
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    /* renamed from: smartisanos.widget.SearchBar$b */
    /* loaded from: classes.dex */
    public interface InterfaceC0928b {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view != this.f4378b && view != this.f4381e) {
            if (view != this.f4383g) {
                if (view != this.f4379c) {
                    if (view != this.f4382f) {
                        return;
                    }
                    this.f4378b.setText((CharSequence) null);
                } else if (!this.f4386j || this.f4387k) {
                } else {
                    this.f4386j = false;
                    m36c();
                    if (!this.f4391o) {
                        return;
                    }
                    post(new RunnableC0954n(this));
                }
            }
        } else if (this.f4389m && !this.f4386j && !this.f4387k) {
            this.f4386j = true;
            if (this.f4390n) {
                m36c();
            }
            if (this.f4391o) {
                m40a(true);
            }
        }
    }

    /* renamed from: c */
    private void m36c() {
        this.f4378b.setFocusable(this.f4386j);
        this.f4378b.setCursorVisible(this.f4386j);
        this.f4378b.setFocusableInTouchMode(this.f4386j);
        if (this.f4386j) {
            this.f4378b.requestFocus();
        } else {
            TextKeyListener.clear(this.f4378b.getText());
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        this.f4381e.setEnabled(z);
        this.f4378b.setEnabled(z);
        this.f4383g.setEnabled(z);
        this.f4379c.setEnabled(z);
        this.f4380d.setEnabled(z);
        this.f4382f.setEnabled(z);
    }

    /* renamed from: a */
    public final void m44a() {
        ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.f4378b.getWindowToken(), 2);
    }

    /* renamed from: b */
    public final void m39b() {
        ((InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE)).showSoftInput(this.f4378b, 0);
    }

    /* renamed from: a */
    public final void m40a(boolean z) {
        int i;
        if (this.f4393q == 0 || this.f4394r == 0) {
            this.f4393q = m43a(this.f4379c);
            this.f4394r = m43a(this.f4383g);
        }
        int i2 = z ? this.f4393q : this.f4394r;
        int i3 = z ? this.f4394r : this.f4393q;
        int i4 = z ? this.f4392p : -this.f4392p;
        if (this.f4388l) {
            i = i3 - i2;
        } else {
            i = z ? i4 + (-this.f4393q) : i4 + this.f4393q;
        }
        DecelerateInterpolator decelerateInterpolator = new DecelerateInterpolator(1.5f);
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this.f4381e, "right", this.f4381e.getRight(), i + this.f4381e.getRight());
        ofInt.setStartDelay((this.f4388l && z) ? 200L : 0L);
        ofInt.addListener(new C0945i(this, z));
        TextView textView = this.f4379c;
        Property property = View.TRANSLATION_X;
        float[] fArr = new float[2];
        fArr[0] = z ? this.f4393q : 0.0f;
        fArr[1] = z ? 0.0f : this.f4393q;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, property, fArr);
        ofFloat.setStartDelay((this.f4388l && z) ? 200L : 0L);
        ObjectAnimator objectAnimator = null;
        if (this.f4388l) {
            Property property2 = View.ALPHA;
            float[] fArr2 = new float[2];
            fArr2[0] = z ? 1.0f : 0.0f;
            fArr2[1] = z ? 0.0f : 1.0f;
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.f4383g, PropertyValuesHolder.ofFloat(property2, fArr2));
            ofPropertyValuesHolder.setStartDelay(z ? 0L : 200L);
            ofPropertyValuesHolder.addListener(new C0946j(this, z));
            objectAnimator = ofPropertyValuesHolder;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200L);
        animatorSet.setInterpolator(decelerateInterpolator);
        animatorSet.addListener(new C0947k(this, z));
        AnimatorSet.Builder play = animatorSet.play(ofInt);
        if (ofFloat != null) {
            play.with(ofFloat);
        }
        if (objectAnimator != null) {
            play.with(objectAnimator);
        }
        if (z && !this.f4388l && this.f4379c.getVisibility() != View.VISIBLE) {
            this.f4379c.setVisibility(View.VISIBLE);
            addOnLayoutChangeListener(new View$OnLayoutChangeListenerC0953m(this, animatorSet));
            return;
        }
        animatorSet.start();
    }

    /* renamed from: a */
    private static int m43a(View view) {
        if (view == null) {
            return 0;
        }
        if (view.getMeasuredWidth() <= 0 || view.getMeasuredHeight() <= 0) {
            view.measure(View.MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, 0));
        }
        return view.getMeasuredWidth();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m41a(SearchBar searchBar, boolean z) {
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) searchBar.f4381e.getLayoutParams();
        layoutParams.addRule(0, (z || !searchBar.f4388l) ? searchBar.f4379c.getId() : searchBar.f4383g.getId());
        layoutParams.rightMargin = (z || searchBar.f4388l) ? 0 : searchBar.f4392p;
    }
}
