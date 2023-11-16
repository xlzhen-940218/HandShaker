package smartisanos.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

import com.smartisanos.smartfolder.aoa.R;

/* loaded from: classes.dex */
public class ShadowTextView extends androidx.appcompat.widget.AppCompatTextView {

    /* renamed from: a */
    private ColorStateList f4400a;

    /* renamed from: b */
    private float f4401b;

    /* renamed from: c */
    private float f4402c;

    /* renamed from: d */
    private float f4403d;

    /* renamed from: e */
    private boolean f4404e;

    public ShadowTextView(Context context) {
        this(context, null);
    }

    public ShadowTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public ShadowTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4404e = true;
        /*TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.f4488u, i, 0);
        this.f4400a = obtainStyledAttributes.getColorStateList(R.styleable.f4492y);
        this.f4401b = obtainStyledAttributes.getFloat(R.styleable.f4489v, 0.0f);
        this.f4402c = obtainStyledAttributes.getFloat(R.styleable.f4490w, 0.0f);
        this.f4403d = obtainStyledAttributes.getFloat(R.styleable.f4491x, 0.0f);
        obtainStyledAttributes.recycle();*/
        m32a(this, this.f4400a, this.f4403d, this.f4401b, this.f4402c);
    }

    /* renamed from: a */
    public static void m32a(TextView textView, ColorStateList colorStateList, float f, float f2, float f3) {
        if (colorStateList != null && textView != null) {
            textView.setShadowLayer(f, f2, f3, colorStateList.getColorForState(textView.getDrawableState(), 0));
            textView.invalidate();
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f4404e) {
            m32a(this, this.f4400a, this.f4403d, this.f4401b, this.f4402c);
        } else {
            m32a(this, this.f4400a, 0.0f, 0.0f, 0.0f);
        }
    }
}
