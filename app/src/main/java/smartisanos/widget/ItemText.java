package smartisanos.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.smartisanos.smartfolder.aoa.R;

/* loaded from: classes.dex */
public class ItemText extends RelativeLayout {

    /* renamed from: a */
    private ImageView f4219a;

    /* renamed from: b */
    private Drawable f4220b;

    /* renamed from: c */
    private Drawable f4221c;

    /* renamed from: d */
    private Bitmap f4222d;

    /* renamed from: e */
    private boolean f4223e;

    /* renamed from: f */
    private boolean f4224f;

    public ItemText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.View
    public void setPressed(boolean z) {
        super.setPressed(z);
        if (this.f4224f != z) {
            this.f4224f = z;
            if (this.f4220b != null) {
                this.f4220b.setState(getDrawableState());
                this.f4220b.invalidateSelf();
            }
            if (this.f4221c == null) {
                return;
            }
            this.f4221c.setState(getDrawableState());
            this.f4221c.invalidateSelf();
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.f4220b != null) {
            canvas.save();
            if (this.f4220b != null) {
                canvas.translate(getResources().getDimensionPixelOffset(R.dimen.item_check_icon_left_margin), (getHeight() - this.f4220b.getIntrinsicHeight()) / 2);
                this.f4220b.draw(canvas);
            }
            canvas.restore();
        }
        if (this.f4223e) {
            canvas.save();
            canvas.drawBitmap(this.f4222d, ((getWidth() - getResources().getDimensionPixelOffset(R.dimen.item_text_right_arrow_margin)) - this.f4219a.getWidth()) - this.f4222d.getWidth(), (getHeight() - this.f4222d.getHeight()) / 2, new Paint(-1));
            canvas.restore();
        }
    }
}
