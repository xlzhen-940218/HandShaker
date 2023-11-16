package com.smartisan.updater;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

/* compiled from: OsUpdateNotifer.java */
/* renamed from: com.smartisan.updater.k */
/* loaded from: classes.dex */
final class DialogInterface$OnClickListenerC0480k implements DialogInterface.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ Context f2733a;

    /* renamed from: b */
    final /* synthetic */ OsUpdateNotifer f2734b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DialogInterface$OnClickListenerC0480k(OsUpdateNotifer osUpdateNotifer, Context context) {
        this.f2734b = osUpdateNotifer;
        this.f2733a = context;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        String str;
        Context context = this.f2733a;
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setPackage("com.android.browser");
            String packageName = context.getApplicationContext().getPackageName();
            if ("com.smartisan.reader".equals(packageName)) {
                str = "https://www.smartisan.com/apps/gx.html?utm_source=%E9%94%A4%E5%AD%90%E9%98%85%E8%AF%BB&utm_medium=%E6%9B%B4%E7%BB%86%E6%8F%90%E7%A4%BA%E6%A1%86&utm_campaign=258OS%E5%8D%87%E7%BA%A7";
            } else if ("com.smartisanos.bbs".equals(packageName)) {
                str = "https://www.smartisan.com/apps/gx.html?utm_source=%E9%94%A4%E5%AD%90%E8%AE%BA%E5%9D%9B&utm_medium=%E6%9B%B4%E7%BB%86%E6%8F%90%E7%A4%BA%E6%A1%86&utm_campaign=258OS%E5%8D%87%E7%BA%A7";
            } else if ("com.smartisanos.drivingmode".equals(packageName)) {
                str = "https://www.smartisan.com/apps/gx.html?utm_source=%E9%94%A4%E5%AD%90%E9%A9%BE%E9%A9%B6&utm_medium=%E6%9B%B4%E7%BB%86%E6%8F%90%E7%A4%BA%E6%A1%86&utm_campaign=258OS%E5%8D%87%E7%BA%A7";
            } else {
                str = "http://gx.t.tt";
            }
            intent.setData(Uri.parse(str));
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
