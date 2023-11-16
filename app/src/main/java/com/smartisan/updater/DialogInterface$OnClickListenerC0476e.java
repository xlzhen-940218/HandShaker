package com.smartisan.updater;

import android.content.Context;
import android.content.DialogInterface;

/* compiled from: ApkUpdater.java */
/* renamed from: com.smartisan.updater.e */
/* loaded from: classes.dex */
final class DialogInterface$OnClickListenerC0476e implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ApkUpdater f2723a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0476e(ApkUpdater apkUpdater) {
        this.f2723a = apkUpdater;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        boolean z;
        Context context;
        z = this.f2723a.f2703e;
        if (!z) {
            context = this.f2723a.f2699a;
            UpdateSharedPreference.m1849a(context).m1847a(false);
        }
    }
}
