package com.smartisan.moreapps;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* compiled from: AppsView.java */
/* renamed from: com.smartisan.moreapps.c */
/* loaded from: classes.dex */
final class C0465c extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ AppsView f2640a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0465c(AppsView appsView) {
        this.f2640a = appsView;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        ProductsAdapter productsAdapter;
        ProductsAdapter productsAdapter2;
        ProductsAdapter productsAdapter3;
        ProductsAdapter productsAdapter4;
        String action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (action.equals("android.intent.action.PACKAGE_ADDED")) {
                productsAdapter3 = this.f2640a.f2621d;
                if (productsAdapter3.m1979a(schemeSpecificPart, true)) {
                    productsAdapter4 = this.f2640a.f2621d;
                    productsAdapter4.notifyDataSetChanged();
                }
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                productsAdapter = this.f2640a.f2621d;
                if (productsAdapter.m1979a(schemeSpecificPart, false)) {
                    productsAdapter2 = this.f2640a.f2621d;
                    productsAdapter2.notifyDataSetChanged();
                }
            }
        }
    }
}
