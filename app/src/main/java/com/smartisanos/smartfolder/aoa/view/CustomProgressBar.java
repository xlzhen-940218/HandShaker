package com.smartisanos.smartfolder.aoa.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smartisanos.smartfolder.aoa.R;

/* renamed from: com.smartisanos.smartfolder.aoa.view.c */
/* loaded from: classes.dex */
public final class CustomProgressBar {

    /* renamed from: a */
    private Context f3960a;

    /* renamed from: b */
    private ImageView f3961b;

    /* renamed from: c */
    private TextView f3962c;

    /* renamed from: d */
    private Dialog f3963d;

    public CustomProgressBar(Context context) {
        this.f3960a = context;
        View inflate = LayoutInflater.from(this.f3960a).inflate(R.layout.layout_progressbar, (ViewGroup) null);
        this.f3961b = (ImageView) inflate.findViewById(R.id.loading_img);
        this.f3962c = (TextView) inflate.findViewById(R.id.loading_tip);
        this.f3963d = new Dialog(this.f3960a, R.style.loading_dialog);
        this.f3963d.setCancelable(false);
        this.f3963d.setContentView((LinearLayout) inflate.findViewById(R.id.loading_root_view));
        WindowManager.LayoutParams attributes = this.f3963d.getWindow().getAttributes();
        attributes.dimAmount = 0.8f;
        this.f3963d.getWindow().setAttributes(attributes);
    }

    /* renamed from: b */
    public final void m200b() {
        if (this.f3963d.isShowing()) {
            this.f3963d.dismiss();
        }
        this.f3961b.clearAnimation();
    }

    /* renamed from: a */
    public final void m201a() {
        Animation animation;
        if (this.f3960a != null) {
            animation = AnimationUtils.loadAnimation(this.f3960a, R.anim.loading_animation);
        } else {
            animation = null;
        }
        if (animation != null) {
            this.f3963d.show();
            this.f3961b.startAnimation(animation);
        }
    }
}
