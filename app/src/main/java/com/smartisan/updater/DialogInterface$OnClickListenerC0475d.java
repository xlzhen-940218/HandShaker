package com.smartisan.updater;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;
import com.smartisan.updater.C0481l;

/* compiled from: ApkUpdater.java */
/* renamed from: com.smartisan.updater.d */
/* loaded from: classes.dex */
final class DialogInterface$OnClickListenerC0475d implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ Version f2721a;

    /* renamed from: b */
    final /* synthetic */ ApkUpdater f2722b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0475d(ApkUpdater apkUpdater, Version version) {
        this.f2722b = apkUpdater;
        this.f2721a = version;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        Context context;
        Context context2;
        Context context3;
        Context context4;
        boolean z;
        Context context5;
        Context context6;
        Context context7;
        Context context8;
        Context context9;
        context = this.f2722b.f2699a;
        if (!UpdateUtils.m1843a(context)) {
            context9 = this.f2722b.f2699a;
            Toast.makeText(context9, C0481l.C0482a.no_network, Toast.LENGTH_LONG).show();
        } else if (!this.f2722b.m1876a(this.f2721a.m1890c(), this.f2721a.m1889d())) {
            context2 = this.f2722b.f2699a;
            if (UpdateUtils.m1833e(context2)) {
                context6 = this.f2722b.f2699a;
                context7 = this.f2722b.f2699a;
                UpdateUtils.m1836b(context6, context7.getApplicationContext().getPackageName());
                return;
            }
            context3 = this.f2722b.f2699a;
            long m1840a = UpdateUtils.m1840a(context3, this.f2721a.m1890c());
            context4 = this.f2722b.f2699a;
            UpdateSharedPreference.m1849a(context4).m1845b(m1840a);
            z = this.f2722b.f2703e;
            if (!z) {
                context5 = this.f2722b.f2699a;
                UpdateSharedPreference.m1849a(context5).m1847a(true);
            }
            this.f2722b.f2708j = this.f2721a.m1889d();
            this.f2722b.m1867f();
        } else {
            ApkUpdater apkUpdater = this.f2722b;
            context8 = this.f2722b.f2699a;
            apkUpdater.m1881a(context8, this.f2721a.m1890c());
        }
    }
}
