package com.smartisan.updater;

import android.content.Context;
import android.widget.Toast;

/* compiled from: UpdateUtils.java */
/* renamed from: com.smartisan.updater.q */
/* loaded from: classes.dex */
final class RunnableC0485q implements Runnable {

    /* renamed from: a */
    final /* synthetic */ Context f2740a;

    /* renamed from: b */
    final /* synthetic */ int f2741b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0485q(Context context, int i) {
        this.f2740a = context;
        this.f2741b = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Toast.makeText(this.f2740a.getApplicationContext(), this.f2741b, Toast.LENGTH_SHORT).show();
    }
}
