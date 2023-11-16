package com.smartisan.feedbackhelper.utils;

import android.content.DialogInterface;
import android.view.View;
import com.smartisan.feedbackhelper.utils.CustomDialog;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CustomDialog.java */
/* renamed from: com.smartisan.feedbackhelper.utils.h */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0458h implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ CustomDialog f2589a;

    /* renamed from: b */
    final /* synthetic */ CustomDialog.C0456a f2590b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0458h(CustomDialog.C0456a c0456a, CustomDialog customDialog) {
        this.f2590b = c0456a;
        this.f2589a = customDialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        DialogInterface.OnClickListener onClickListener;
        onClickListener = this.f2590b.f2586g;
        onClickListener.onClick(this.f2589a, -2);
    }
}
