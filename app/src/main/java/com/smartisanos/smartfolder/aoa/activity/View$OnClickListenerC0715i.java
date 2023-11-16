package com.smartisanos.smartfolder.aoa.activity;

import android.view.View;
import com.smartisanos.smartfolder.aoa.view.MenuDialogWrapper;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SettingsActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.activity.i */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0715i implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ MenuDialogWrapper f3454a;

    /* renamed from: b */
    final /* synthetic */ SettingsActivity f3455b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0715i(SettingsActivity settingsActivity, MenuDialogWrapper menuDialogWrapper) {
        this.f3455b = settingsActivity;
        this.f3454a = menuDialogWrapper;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.f3454a.m195b();
    }
}
