package com.smartisan.moreapps;

import android.view.View;

/* compiled from: ProductsAdapter.java */
/* renamed from: com.smartisan.moreapps.h */
/* loaded from: classes.dex */
final class View$OnClickListenerC0468h implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ String f2652a;

    /* renamed from: b */
    final /* synthetic */ ProductsAdapter f2653b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0468h(ProductsAdapter productsAdapter, String str) {
        this.f2653b = productsAdapter;
        this.f2652a = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ProductsAdapter.m1980a(this.f2653b, this.f2652a);
    }
}
