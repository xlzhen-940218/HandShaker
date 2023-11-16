package smartisanos.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.smartisanos.smartfolder.aoa.R;

/* loaded from: classes.dex */
public class ItemCheck extends LinearLayout {

    /* renamed from: a */
    private View f4214a;

    /* renamed from: b */
    private Drawable f4215b;

    public ItemCheck(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f4215b != null) {
            this.f4214a.setTranslationX(this.f4215b.getIntrinsicWidth() + getResources().getDimensionPixelOffset(R.dimen.item_check_icon_left_margin));
        }
    }
}
