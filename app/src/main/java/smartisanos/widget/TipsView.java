package smartisanos.widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.smartisanos.smartfolder.aoa.R;

/* loaded from: classes.dex */
public class TipsView extends androidx.appcompat.widget.AppCompatTextView {
    public TipsView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /* renamed from: a */
    private void m23a() {
        float measureText;
        float dimensionPixelSize = getContext().getResources().getDimensionPixelSize(R.dimen.tips_max_width);
        Paint paint = new Paint();
        paint.setTextSize(getTextSize());
        if (getText() == null) {
            measureText = 0.0f;
        } else {
            measureText = paint.measureText(getText().toString());
        }
        if (measureText > dimensionPixelSize) {
            setGravity(3);
        } else {
            setGravity(17);
        }
    }

    @Override // android.widget.TextView
    public void setText(CharSequence charSequence, TextView.BufferType bufferType) {
        super.setText(charSequence, bufferType);
        m23a();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        m23a();
    }

    @Override // android.widget.TextView
    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        m23a();
    }
}
