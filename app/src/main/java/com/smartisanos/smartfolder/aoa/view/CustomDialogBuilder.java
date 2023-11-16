package com.smartisanos.smartfolder.aoa.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.smartisanos.smartfolder.aoa.R;

/* renamed from: com.smartisanos.smartfolder.aoa.view.a */
/* loaded from: classes.dex */
public final class CustomDialogBuilder extends AlertDialog.Builder {

    /* renamed from: a */
    private Context f3946a;

    /* renamed from: b */
    private View f3947b;

    /* renamed from: c */
    private TextView f3948c;

    /* renamed from: d */
    private LinearLayout f3949d;

    /* renamed from: e */
    private TextView f3950e;

    /* renamed from: f */
    private LinearLayout f3951f;

    /* renamed from: g */
    private Button f3952g;

    /* renamed from: h */
    private Button f3953h;

    /* renamed from: i */
    private Button f3954i;

    /* renamed from: j */
    private ImageView f3955j;

    /* renamed from: k */
    private LayoutInflater f3956k;

    /* renamed from: l */
    public AlertDialog f3957l;

    private CustomDialogBuilder(Context context) {
        super(context);
        this.f3946a = context;
        this.f3956k = (LayoutInflater) this.f3946a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.f3947b = this.f3956k.inflate(R.layout.dialog_custom, (ViewGroup) null);
        setView(this.f3947b);
        this.f3948c = (TextView) this.f3947b.findViewById(R.id.custom_dialog_title);
        this.f3949d = (LinearLayout) this.f3947b.findViewById(R.id.custom_dialog_content);
        this.f3950e = (TextView) this.f3947b.findViewById(R.id.custom_dialog_message);
        this.f3951f = (LinearLayout) this.f3947b.findViewById(R.id.custom_dialog_buttons);
        this.f3955j = (ImageView) this.f3947b.findViewById(R.id.custom_dialog_content_img);
    }

    @Override // android.app.AlertDialog.Builder
    /* renamed from: a */
    public final CustomDialogBuilder setTitle(int i) {
        this.f3948c.setText(i);
        return this;
    }

    @Override // android.app.AlertDialog.Builder
    /* renamed from: b */
    public final CustomDialogBuilder setMessage(int i) {
        this.f3950e.setText(i);
        return this;
    }

    @Override // android.app.AlertDialog.Builder
    /* renamed from: a */
    public final CustomDialogBuilder setMessage(CharSequence charSequence) {
        this.f3950e.setText(charSequence);
        return this;
    }

    /* renamed from: a */
    public final CustomDialogBuilder m210a(int i, View.OnClickListener onClickListener) {
        this.f3952g = (Button) this.f3956k.inflate(R.layout.view_custom_dialog_button, (ViewGroup) this.f3951f, false);
        this.f3952g.setText(i);
        m207a(this.f3952g, onClickListener);
        this.f3951f.addView(this.f3952g);
        m204b();
        return this;
    }

    /* renamed from: b */
    public final CustomDialogBuilder m202b(int i, View.OnClickListener onClickListener) {
        this.f3954i = (Button) this.f3956k.inflate(R.layout.view_custom_dialog_button, (ViewGroup) this.f3951f, false);
        this.f3954i.setText(i);
        m207a(this.f3954i, onClickListener);
        this.f3951f.addView(this.f3954i);
        m204b();
        return this;
    }

    /* renamed from: a */
    public final CustomDialogBuilder m208a(View.OnClickListener onClickListener) {
        this.f3953h = (Button) this.f3956k.inflate(R.layout.view_custom_dialog_button, (ViewGroup) this.f3951f, false);
        this.f3953h.setText(R.string.trust_once);
        m207a(this.f3953h, onClickListener);
        this.f3951f.addView(this.f3953h);
        m204b();
        return this;
    }

    /* renamed from: b */
    private void m204b() {
        int i;
        Button[] buttonArr = new Button[3];
        if (this.f3954i != null) {
            buttonArr[0] = this.f3954i;
            i = 1;
        } else {
            i = 0;
        }
        if (this.f3953h != null) {
            buttonArr[i] = this.f3953h;
            i++;
        }
        if (this.f3952g != null) {
            buttonArr[i] = this.f3952g;
            i++;
        }
        if (i == 1) {
            buttonArr[0].setBackgroundResource(R.drawable.btn_alertdialog_smartisanos_all_pressed);
        } else if (i == 2) {
            buttonArr[0].setBackgroundResource(R.drawable.btn_alertdialog_smartisanos_left_pressed);
            buttonArr[1].setBackgroundResource(R.drawable.btn_alertdialog_smartisanos_right_pressed);
        } else if (i == 3) {
            buttonArr[0].setBackgroundResource(R.drawable.btn_alertdialog_smartisanos_left_pressed);
            buttonArr[1].setBackgroundResource(R.drawable.btn_alertdialog_smartisanos_mid_pressed);
            buttonArr[2].setBackgroundResource(R.drawable.btn_alertdialog_smartisanos_right_pressed);
        }
    }

    /* renamed from: a */
    private void m207a(Button button, View.OnClickListener onClickListener) {
        button.setOnClickListener(new View$OnClickListenerC0834b(this, onClickListener));
    }

    @Override // android.app.AlertDialog.Builder
    public final AlertDialog create() {
        this.f3957l = super.create();
        this.f3957l.getWindow().setLayout(-1, -2);
        return this.f3957l;
    }

    /* renamed from: a */
    @SuppressLint("ResourceType")
    public static CustomDialogBuilder m209a(Context context) {
        return new CustomDialogBuilder(new ContextThemeWrapper(context, android.R.style.Theme_DeviceDefault_Light));
    }

    /* renamed from: a */
    public final void m212a() {
        this.f3955j.setVisibility(View.VISIBLE);
        this.f3955j.setImageResource(R.drawable.grant_permission_intro);
    }

    @Override // android.app.AlertDialog.Builder
    public final /* synthetic */ AlertDialog.Builder setTitle(CharSequence charSequence) {
        this.f3948c.setText(charSequence);
        return this;
    }
}
