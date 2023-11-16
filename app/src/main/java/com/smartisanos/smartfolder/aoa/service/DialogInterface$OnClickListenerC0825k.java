package com.smartisanos.smartfolder.aoa.service;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.smartisanos.smartfolder.aoa.FolderApp;

/* compiled from: UiDialogService.java */
/* renamed from: com.smartisanos.smartfolder.aoa.service.k */
/* loaded from: classes.dex */
final class DialogInterface$OnClickListenerC0825k implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ UiDialogService.HandlerC0813a f3912a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0825k(UiDialogService.HandlerC0813a handlerC0813a) {
        this.f3912a = handlerC0813a;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.smartisan.com/apps/gx.html?utm_source=handshaker&utm_medium=%E6%9B%B4%E7%BB%86%E6%8F%90%E7%A4%BA%E6%A1%86&utm_campaign=258OS%E5%8D%87%E7%BA%A7"));
        intent.setFlags(805306368);
        FolderApp.getInstance().startActivity(intent);
    }
}
