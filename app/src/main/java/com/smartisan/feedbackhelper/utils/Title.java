package com.smartisan.feedbackhelper.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartisanos.smartfolder.aoa.R;


/* loaded from: classes.dex */
public class Title extends RelativeLayout {

    /* renamed from: f */
    private static int backBtnMaxWidth;

    /* renamed from: a */
    private TextView titleTv;

    /* renamed from: b */
    private TextView backBtn;

    /* renamed from: c */
    private TextView okBtn;

    /* renamed from: d */
    private View holderPlace;

    /* renamed from: e */
    private int f2554e;

    /* renamed from: g */
    private int f2555g;

    /* renamed from: h */
    private int f2556h;

    public Title(Context context) {
        super(context);
    }

    public Title(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public Title(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = LayoutInflater.from(context).inflate(R.layout.feedback_title_layout, (ViewGroup) this, true);
        this.titleTv = (TextView) inflate.findViewById(R.id.feedback_tv_title);
        this.backBtn = (TextView) inflate.findViewById(R.id.feedback_btn_back);
        this.okBtn = (TextView) inflate.findViewById(R.id.feedback_btn_ok);
        this.holderPlace = inflate.findViewById(R.id.feedback_place_holder);
        backBtnMaxWidth = getResources().getDimensionPixelSize(R.dimen.title_back_btn_max_width);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Title, i, 0);
        CharSequence text = obtainStyledAttributes.getText(R.styleable.Title_feedback_backText);
        if (text != null) {
            this.backBtn.setText(text);
        }
        ColorStateList colorStateList = obtainStyledAttributes.getColorStateList(R.styleable.Title_feedback_backTextColor);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Title_feedback_backTextSize, -1);
        if (colorStateList != null) {
            this.backBtn.setTextColor(colorStateList);
        }
        if (dimensionPixelSize > 0) {
            this.backBtn.setTextSize(0, dimensionPixelSize);
        }
        this.f2554e = context.getResources().getDisplayMetrics().widthPixels;
        this.f2555g = getResources().getDrawable(R.drawable.feedback_title_btn_back).getIntrinsicWidth() - 4;
        this.f2556h = getResources().getDrawable(R.drawable.feedback_title_btn_ok).getIntrinsicWidth();
        CharSequence text2 = obtainStyledAttributes.getText(R.styleable.Title_feedback_okText);
        if (text2 != null) {
            this.okBtn.setVisibility(View.VISIBLE);
            this.okBtn.setText(text2);
        }
        ColorStateList colorStateList2 = obtainStyledAttributes.getColorStateList(R.styleable.Title_feedback_okTextColor);
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Title_feedback_okTextSize, -1);
        if (colorStateList2 != null) {
            this.okBtn.setTextColor(colorStateList2);
        }
        if (dimensionPixelSize2 > 0) {
            this.okBtn.setTextSize(0, dimensionPixelSize2);
        }
        CharSequence text3 = obtainStyledAttributes.getText(R.styleable.Title_feedback_itemtitle);
        if (text3 != null) {
            this.titleTv.setText(text3);
        }
        ColorStateList colorStateList3 = obtainStyledAttributes.getColorStateList(R.styleable.Title_feedback_titleColor);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Title_feedback_titleSize, -1);
        if (colorStateList3 != null) {
            this.titleTv.setTextColor(colorStateList3);
        }
        if (dimensionPixelSize3 > 0) {
            this.titleTv.setTextSize(0, dimensionPixelSize3);
        }
        m2064d();
        obtainStyledAttributes.recycle();
        setBackgroundResource(R.drawable.title_bar_bg);
    }

    /* renamed from: d */
    private void m2064d() {
        Drawable background = this.backBtn.getBackground();
        if (background != null) {
            this.f2555g = background.getIntrinsicWidth() - 4;
        }
        float min = Math.min(m2068a(this.backBtn), backBtnMaxWidth - this.f2555g) + this.f2555g;
        float min2 = Math.min(m2068a(this.okBtn), backBtnMaxWidth);
        if (this.f2554e - (2.0f * min) > m2068a(this.titleTv) + 5.0f) {
            this.holderPlace.setMinimumWidth((int) min);
            this.titleTv.setGravity(17);
            return;
        }
        if (min2 == 0.0f) {
            this.okBtn.setVisibility(View.GONE);
            this.holderPlace.setMinimumWidth(getResources().getDimensionPixelSize(R.dimen.title_place_holder_min_width));
        } else {
            this.holderPlace.setMinimumWidth(((int) min2) + this.f2556h);
            this.okBtn.setVisibility(View.VISIBLE);
        }
        this.titleTv.setGravity(3);
    }

    /* renamed from: a */
    private static float m2068a(TextView textView) {
        Paint paint = new Paint();
        paint.setTextSize(textView.getTextSize());
        if (textView.getText() == null) {
            return 0.0f;
        }
        return paint.measureText(textView.getText().toString());
    }

    /* renamed from: a */
    public final TextView m2069a() {
        return this.titleTv;
    }

    /* renamed from: b */
    public final TextView m2066b() {
        return this.okBtn;
    }

    /* renamed from: c */
    public final TextView m2065c() {
        return this.backBtn;
    }

    /* renamed from: a */
    public final void m2067a(CharSequence charSequence) {
        if (this.backBtn != null) {
            this.backBtn.setText(charSequence);
            m2064d();
        }
    }
}
