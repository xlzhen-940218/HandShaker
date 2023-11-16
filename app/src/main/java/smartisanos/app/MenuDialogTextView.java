package smartisanos.app;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.widget.TextView;

/* loaded from: classes.dex */
public class MenuDialogTextView extends TextView {

    /* renamed from: a */
    private float f4170a;

    /* renamed from: b */
    private int f4171b;

    public MenuDialogTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.f4170a = (93.0f * displayMetrics.density) + 0.5f;
        this.f4171b = Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels);
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int width = getWidth();
        if (width <= this.f4171b) {
            return super.onTouchEvent(motionEvent);
        }
        float x = motionEvent.getX();
        switch (motionEvent.getActionMasked()) {
            case 0:
                if (x < this.f4170a || x > width - this.f4170a) {
                    return true;
                }
                break;
            case 1:
            case 2:
            case 3:
                if (x < this.f4170a || x > width - this.f4170a) {
                    setPressed(false);
                    return true;
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }
}
