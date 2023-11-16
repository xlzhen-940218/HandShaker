package smartisanos.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;

import com.smartisanos.smartfolder.aoa.R;

/* loaded from: classes.dex */
public class ShadowButton extends androidx.appcompat.widget.AppCompatButton {

    /* renamed from: a */
    private ColorStateList f4395a;

    /* renamed from: b */
    private float f4396b;

    /* renamed from: c */
    private float f4397c;

    /* renamed from: d */
    private float f4398d;

    /* renamed from: e */
    private boolean f4399e;

    public ShadowButton(Context context) {
        this(context, null);
    }

    public ShadowButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842824);
    }

    public ShadowButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f4399e = true;
        /*TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ShadowButton, i, 0);
        this.f4395a = obtainStyledAttributes.getColorStateList(R.styleable.f4487t);
        this.f4396b = obtainStyledAttributes.getFloat(R.styleable.f4484q, 0.0f);
        this.f4397c = obtainStyledAttributes.getFloat(R.styleable.f4485r, 0.0f);
        this.f4398d = obtainStyledAttributes.getFloat(R.styleable.f4486s, 0.0f);
        obtainStyledAttributes.recycle();*/
        ShadowTextView.m32a(this, this.f4395a, this.f4398d, this.f4396b, this.f4397c);
    }

    @Override // android.widget.TextView, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.f4399e) {
            ShadowTextView.m32a(this, this.f4395a, this.f4398d, this.f4396b, this.f4397c);
        } else {
            ShadowTextView.m32a(this, this.f4395a, 0.0f, 0.0f, 0.0f);
        }
    }
}
