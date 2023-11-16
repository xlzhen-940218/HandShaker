package smartisanos.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.smartisanos.smartfolder.aoa.R;

/* loaded from: classes.dex */
public class RoundedRectLinearLayout extends LinearLayout {

    /* renamed from: a */
    private Path f4375a;

    /* renamed from: b */
    private float f4376b;

    public RoundedRectLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m45a(attributeSet);
    }

    public RoundedRectLinearLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m45a(attributeSet);
    }

    /* renamed from: a */
    private void m45a(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RoundedRectLinearLayout, 0, 0);
            this.f4376b = obtainStyledAttributes.getDimensionPixelSize(R.styleable.RoundedRectLinearLayout_cornerRadius, 0);
            obtainStyledAttributes.recycle();
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.f4376b > 0.0f) {
            this.f4375a = new Path();
            this.f4375a.addRoundRect(new RectF(0.0f, 0.0f, i, i2), this.f4376b, this.f4376b, Path.Direction.CW);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        int save = canvas.save();
        if (this.f4376b > 0.0f) {
            canvas.clipPath(this.f4375a);
        }
        super.dispatchDraw(canvas);
        canvas.restoreToCount(save);
    }
}
