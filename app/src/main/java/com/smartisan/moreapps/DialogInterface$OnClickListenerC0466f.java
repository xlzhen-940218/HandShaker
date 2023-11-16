package com.smartisan.moreapps;

import android.content.Context;
import android.content.DialogInterface;
import com.smartisan.moreapps.download.AppDownloader;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ProductsAdapter.java */
/* renamed from: com.smartisan.moreapps.f */
/* loaded from: classes.dex */
public final class DialogInterface$OnClickListenerC0466f implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ String f2648a;

    /* renamed from: b */
    final /* synthetic */ String f2649b;

    /* renamed from: c */
    final /* synthetic */ ProductsAdapter f2650c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0466f(ProductsAdapter productsAdapter, String str, String str2) {
        this.f2650c = productsAdapter;
        this.f2648a = str;
        this.f2649b = str2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        Context context;
        context = this.f2650c.f2647b;
        new AppDownloader(context).m1988a(this.f2648a, this.f2649b);
        dialogInterface.dismiss();
    }
}
