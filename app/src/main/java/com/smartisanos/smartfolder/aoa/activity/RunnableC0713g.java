package com.smartisanos.smartfolder.aoa.activity;

import android.view.View;

/* compiled from: SettingsActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.activity.g */
/* loaded from: classes.dex */
final class RunnableC0713g implements Runnable {

    /* renamed from: a */
    final /* synthetic */ View f3449a;

    /* renamed from: b */
    final /* synthetic */ SettingsActivity f3450b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0713g(SettingsActivity settingsActivity, View view) {
        this.f3450b = settingsActivity;
        this.f3449a = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f3449a.setClickable(true);
    }
}
