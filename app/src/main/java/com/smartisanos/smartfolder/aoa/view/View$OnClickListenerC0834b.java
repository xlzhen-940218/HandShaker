package com.smartisanos.smartfolder.aoa.view;

import android.app.AlertDialog;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: CustomDialogBuilder.java */
/* renamed from: com.smartisanos.smartfolder.aoa.view.b */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0834b implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ View.OnClickListener f3958a;

    /* renamed from: b */
    final /* synthetic */ CustomDialogBuilder f3959b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0834b(CustomDialogBuilder customDialogBuilder, View.OnClickListener onClickListener) {
        this.f3959b = customDialogBuilder;
        this.f3958a = onClickListener;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        AlertDialog alertDialog;
        AlertDialog alertDialog2;
        if (this.f3958a != null) {
            this.f3958a.onClick(view);
        }
        alertDialog = this.f3959b.f3957l;
        if (alertDialog != null) {
            alertDialog2 = this.f3959b.f3957l;
            alertDialog2.dismiss();
        }
    }
}
