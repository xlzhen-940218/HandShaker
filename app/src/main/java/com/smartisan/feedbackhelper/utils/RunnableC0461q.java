package com.smartisan.feedbackhelper.utils;

import android.view.View;

/* compiled from: ScreenShotsAdapter.java */
/* renamed from: com.smartisan.feedbackhelper.utils.q */
/* loaded from: classes.dex */
final class RunnableC0461q implements Runnable {

    /* renamed from: a */
    final /* synthetic */ View f2611a;

    /* renamed from: b */
    final /* synthetic */ View$OnClickListenerC0460p f2612b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0461q(View$OnClickListenerC0460p view$OnClickListenerC0460p, View view) {
        this.f2612b = view$OnClickListenerC0460p;
        this.f2611a = view;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ((View) this.f2611a.getParent()).setEnabled(true);
    }
}
