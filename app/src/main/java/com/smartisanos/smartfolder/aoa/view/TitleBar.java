package com.smartisanos.smartfolder.aoa.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smartisanos.smartfolder.aoa.R;

/* loaded from: classes.dex */
public class TitleBar extends RelativeLayout {

    /* renamed from: a */
    private View f3943a;

    /* renamed from: b */
    private View f3944b;

    /* renamed from: c */
    private TextView f3945c;

    public TitleBar(Context context) {
        super(context);
        m216a(context);
    }

    public TitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m216a(context);
    }

    public TitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m216a(context);
    }

    /* renamed from: a */
    private void m216a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_titlebar, (ViewGroup) this, true);
        this.f3943a = findViewById(R.id.title_btn_back);
        this.f3944b = findViewById(R.id.title_btn_settings);
        this.f3945c = (TextView) findViewById(R.id.title_tv_title);
    }

    /* renamed from: a */
    public final void m217a(int i) {
        this.f3945c.setText(i);
    }

    /* renamed from: a */
    public final void m215a(View.OnClickListener onClickListener) {
        this.f3943a.setOnClickListener(onClickListener);
    }

    /* renamed from: b */
    public final void m213b(View.OnClickListener onClickListener) {
        this.f3944b.setOnClickListener(onClickListener);
    }

    /* renamed from: a */
    public final void m218a() {
        this.f3943a.setVisibility(View.GONE);
    }

    /* renamed from: b */
    public final void m214b() {
        this.f3944b.setVisibility(View.GONE);
    }
}
