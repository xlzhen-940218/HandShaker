package com.smartisanos.smartfolder.aoa;

import android.content.Intent;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MainActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.d */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0722d implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ MainActivity f3475a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0722d(MainActivity mainActivity) {
        this.f3475a = mainActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.f3475a.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 33);
    }
}
