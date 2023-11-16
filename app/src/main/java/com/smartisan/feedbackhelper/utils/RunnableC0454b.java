package com.smartisan.feedbackhelper.utils;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AsyncDialog.java */
/* renamed from: com.smartisan.feedbackhelper.utils.b */
/* loaded from: classes.dex */
public final class RunnableC0454b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ AsyncDialog f2564a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0454b(AsyncDialog asyncDialog) {
        this.f2564a = asyncDialog;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.f2564a.progressDialog != null) {
            this.f2564a.progressDialog.show();
        }
    }
}
