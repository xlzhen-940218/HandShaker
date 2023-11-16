package com.smartisan.feedbackhelper.utils;

import android.content.DialogInterface;
import android.view.View;
import com.smartisan.feedbackhelper.utils.CustomDialog;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CustomDialog.java */
/* renamed from: com.smartisan.feedbackhelper.utils.g */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0457g implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ CustomDialog f2587a;

    /* renamed from: b */
    final /* synthetic */ CustomDialog.C0456a f2588b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0457g(CustomDialog.C0456a c0456a, CustomDialog customDialog) {
        this.f2588b = c0456a;
        this.f2587a = customDialog;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        DialogInterface.OnClickListener onClickListener;
        onClickListener = this.f2588b.f2585f;
        onClickListener.onClick(this.f2587a, -1);
    }
}
