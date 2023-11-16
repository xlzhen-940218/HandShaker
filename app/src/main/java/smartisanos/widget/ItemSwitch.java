package smartisanos.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

/* loaded from: classes.dex */
public class ItemSwitch extends LinearLayout {

    /* renamed from: a */
    protected SwitchEx f4216a;

    /* renamed from: b */
    protected TextView f4217b;

    /* renamed from: c */
    protected TextView f4218c;

    public ItemSwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.f4218c != null) {
            this.f4218c.setEnabled(z);
        }
        if (this.f4216a != null) {
            this.f4216a.setEnabled(z);
        }
        if (this.f4217b != null) {
            this.f4217b.setEnabled(z);
        }
    }
}
