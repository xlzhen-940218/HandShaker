package smartisanos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

/* loaded from: classes.dex */
public class RadioGroupExpand extends RadioGroup {

    /* renamed from: a */
    private View.OnLongClickListener f4374a;

    public RadioGroupExpand(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.View
    public void setOnLongClickListener(View.OnLongClickListener onLongClickListener) {
        this.f4374a = onLongClickListener;
    }
}
