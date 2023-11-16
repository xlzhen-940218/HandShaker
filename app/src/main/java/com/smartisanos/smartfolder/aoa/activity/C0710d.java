package com.smartisanos.smartfolder.aoa.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ConnecttingFragment.java */
/* renamed from: com.smartisanos.smartfolder.aoa.activity.d */
/* loaded from: classes.dex */
public final class C0710d extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ ConnecttingFragment f3446a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0710d(ConnecttingFragment connecttingFragment) {
        this.f3446a = connecttingFragment;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        this.f3446a.m781a();
    }
}
