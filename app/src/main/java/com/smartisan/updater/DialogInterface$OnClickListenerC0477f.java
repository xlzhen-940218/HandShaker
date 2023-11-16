package com.smartisan.updater;

import android.content.DialogInterface;

/* compiled from: ApkUpdater.java */
/* renamed from: com.smartisan.updater.f */
/* loaded from: classes.dex */
final class DialogInterface$OnClickListenerC0477f implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ boolean f2724a;

    /* renamed from: b */
    final /* synthetic */ ApkUpdater f2725b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0477f(ApkUpdater apkUpdater, boolean z) {
        this.f2725b = apkUpdater;
        this.f2724a = z;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.f2724a) {
            this.f2725b.m1864h();
        }
    }
}
