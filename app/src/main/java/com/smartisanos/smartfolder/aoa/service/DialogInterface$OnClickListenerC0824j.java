package com.smartisanos.smartfolder.aoa.service;

import android.content.DialogInterface;
import com.smartisanos.smartfolder.aoa.service.UiDialogService;

/* compiled from: UiDialogService.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.j */
/* loaded from: classes.dex */
final class DialogInterface$OnClickListenerC0824j implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ UiDialogService.HandlerC0813a f3911a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0824j(UiDialogService.HandlerC0813a handlerC0813a) {
        this.f3911a = handlerC0813a;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
    }
}
