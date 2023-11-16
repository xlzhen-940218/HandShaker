package com.smartisan.feedbackhelper.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smartisanos.smartfolder.aoa.R;

/* renamed from: com.smartisan.feedbackhelper.utils.f */
/* loaded from: classes.dex */
public final class CustomDialog extends Dialog {
    public CustomDialog(Context context, int i) {
        super(context, i);
    }

    /* compiled from: CustomDialog.java */
    /* renamed from: com.smartisan.feedbackhelper.utils.f$a */
    /* loaded from: classes.dex */
    public static class C0456a {

        /* renamed from: a */
        private Context f2580a;

        /* renamed from: b */
        private String f2581b;

        /* renamed from: c */
        private String f2582c;

        /* renamed from: d */
        private String f2583d;

        /* renamed from: e */
        private View f2584e;

        /* renamed from: f */
        public DialogInterface.OnClickListener f2585f;

        /* renamed from: g */
        public DialogInterface.OnClickListener f2586g;

        public C0456a(Context context) {
            this.f2580a = context;
        }

        /* renamed from: a */
        public final C0456a m2054a(int i) {
            this.f2581b = (String) this.f2580a.getText(i);
            return this;
        }

        /* renamed from: a */
        public final C0456a m2053a(int i, DialogInterface.OnClickListener onClickListener) {
            this.f2582c = (String) this.f2580a.getText(i);
            this.f2585f = onClickListener;
            return this;
        }

        /* renamed from: b */
        public final C0456a m2051b(int i, DialogInterface.OnClickListener onClickListener) {
            this.f2583d = (String) this.f2580a.getText(i);
            this.f2586g = onClickListener;
            return this;
        }

        /* renamed from: a */
        public final CustomDialog m2055a() {
            CustomDialog customDialog = new CustomDialog(this.f2580a, R.style.FeedBackAlertDialogTheme);
            customDialog.setCanceledOnTouchOutside(false);
            customDialog.requestWindowFeature(1);
            View inflate = ((LayoutInflater) this.f2580a.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_dialog_layout, (ViewGroup) null);
            customDialog.addContentView(inflate, new ViewGroup.LayoutParams(-1, -2));
            if (this.f2582c != null) {
                ((Button) inflate.findViewById(R.id.feedback_dialog_positiveButton)).setText(this.f2582c);
                if (this.f2585f != null) {
                    ((Button) inflate.findViewById(R.id.feedback_dialog_positiveButton)).setOnClickListener(new View$OnClickListenerC0457g(this, customDialog));
                }
            } else {
                inflate.findViewById(R.id.feedback_dialog_positiveButton).setVisibility(View.GONE);
            }
            if (this.f2583d != null) {
                ((Button) inflate.findViewById(R.id.feedback_dialog_negativeButton)).setText(this.f2583d);
                if (this.f2586g != null) {
                    ((Button) inflate.findViewById(R.id.feedback_dialog_negativeButton)).setOnClickListener(new View$OnClickListenerC0458h(this, customDialog));
                }
            } else {
                inflate.findViewById(R.id.feedback_dialog_negativeButton).setVisibility(View.GONE);
            }
            if (this.f2581b != null) {
                ((TextView) inflate.findViewById(R.id.feedback_dialog_message)).setText(this.f2581b);
            } else if (this.f2584e != null) {
                ((LinearLayout) inflate.findViewById(R.id.feedback_dialog_content)).removeAllViews();
                ((LinearLayout) inflate.findViewById(R.id.feedback_dialog_content)).addView(this.f2584e, new ViewGroup.LayoutParams(-2, -2));
            }
            customDialog.setContentView(inflate);
            return customDialog;
        }
    }
}
