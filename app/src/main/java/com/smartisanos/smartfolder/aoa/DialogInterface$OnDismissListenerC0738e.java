package com.smartisanos.smartfolder.aoa;

import android.content.DialogInterface;
import com.smartisanos.smartfolder.aoa.MainActivity;

/* compiled from: MainActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.e */
/* loaded from: classes.dex */
final class DialogInterface$OnDismissListenerC0738e implements DialogInterface.OnDismissListener {

    /* renamed from: a */
    final /* synthetic */ MainActivity f3547a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnDismissListenerC0738e(MainActivity mainActivity) {
        this.f3547a = mainActivity;
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        MainActivity.HandlerC0703b handlerC0703b;
        MainActivity.HandlerC0703b handlerC0703b2;
        handlerC0703b = this.f3547a.f3400q;
        if (handlerC0703b.hasMessages(1)) {
            handlerC0703b2 = this.f3547a.f3400q;
            handlerC0703b2.removeMessages(1);
        }
    }
}
