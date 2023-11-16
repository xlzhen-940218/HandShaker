package com.smartisan.moreapps;

import android.view.View;

/* compiled from: ProductsAdapter.java */
/* renamed from: com.smartisan.moreapps.i */
/* loaded from: classes.dex */
final class View$OnClickListenerC0469i implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ String f2654a;

    /* renamed from: b */
    final /* synthetic */ ProductsAdapter f2655b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0469i(ProductsAdapter productsAdapter, String str) {
        this.f2655b = productsAdapter;
        this.f2654a = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ProductsAdapter.m1976b(this.f2655b, this.f2654a);
    }
}
