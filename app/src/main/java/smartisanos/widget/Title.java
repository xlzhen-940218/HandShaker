package smartisanos.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartisanos.smartfolder.aoa.R;

/* loaded from: classes.dex */
public class Title extends RelativeLayout {

    /* renamed from: a */
     ViewTreeObserver.OnGlobalLayoutListener f4453a;

    /* renamed from: b */
    private TextView f4454b;

    /* renamed from: c */
    private ShadowTextView f4455c;

    /* renamed from: d */
    private ShadowButton f4456d;

    /* renamed from: e */
    private boolean f4457e;

    /* renamed from: f */
    private boolean f4458f;

    /* renamed from: g */
    private View f4459g;

    /* renamed from: h */
    private String f4460h;

    public Title(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.ViewGroup, android.view.View
    @TargetApi(16)
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.f4457e && !this.f4458f) {
            getViewTreeObserver().removeOnGlobalLayoutListener(this.f4453a);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        int dimensionPixelSize;
        super.onConfigurationChanged(configuration);
        if (!m21a(configuration).equals(this.f4460h)) {
            this.f4460h = m21a(getResources().getConfiguration());
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.f4454b.getLayoutParams();
            float width = this.f4455c.getWidth();
            float width2 = (this.f4456d.getVisibility() == View.GONE || TextUtils.isEmpty(this.f4456d.getText())) ? 0.0f : this.f4456d.getWidth();
            if (width == 0.0f && width2 == 0.0f) {
                m22a(0);
                this.f4454b.setGravity(17);
                this.f4454b.setPadding(0, 0, 0, 0);
                return;
            }
            float max = getResources().getDisplayMetrics().widthPixels - (Math.max(width, width2) * 2.0f);
            TextView textView = this.f4454b;
            Paint paint = new Paint();
            paint.setTextSize(textView.getTextSize());
            if (max > (textView.getText() == null ? 0.0f : paint.measureText(textView.getText().toString())) + 5.0f) {
                m22a((int) width);
                layoutParams.removeRule(9);
                this.f4454b.setGravity(17);
                this.f4454b.setPadding(0, 0, 0, 0);
                return;
            }
            if (width2 == 0.0f || this.f4456d.getVisibility() == View.GONE) {
                dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tips_min_width);
                m22a(dimensionPixelSize);
            } else {
                dimensionPixelSize = (int) width2;
                m22a(dimensionPixelSize);
            }
            layoutParams.addRule(9);
            this.f4454b.setGravity(3);
            this.f4454b.setPadding(((int) width) + 1, 0, dimensionPixelSize, 0);
        }
    }

    /* renamed from: a */
    private static String m21a(Configuration configuration) {
        return new StringBuffer().append(configuration.fontScale).append("|").append(configuration.orientation).toString();
    }

    /* renamed from: a */
    private void m22a(int i) {
        ViewGroup.LayoutParams layoutParams = this.f4459g.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(i, 1);
        } else {
            layoutParams.width = i;
        }
        this.f4459g.setLayoutParams(layoutParams);
    }
}
