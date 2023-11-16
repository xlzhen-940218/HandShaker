package com.smartisan.updater;

import android.os.Process;
import java.util.TimerTask;

/* compiled from: ApkUpdater.java */
/* renamed from: com.smartisan.updater.g */
/* loaded from: classes.dex */
final class C0478g extends TimerTask {

    /* renamed from: a */
    final /* synthetic */ ApkUpdater f2726a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0478g(ApkUpdater apkUpdater) {
        this.f2726a = apkUpdater;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        Process.killProcess(Process.myPid());
    }
}
