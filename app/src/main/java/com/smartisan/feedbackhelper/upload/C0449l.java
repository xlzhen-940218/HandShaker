package com.smartisan.feedbackhelper.upload;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploadWorker.java */
/* renamed from: com.smartisan.feedbackhelper.upload.l */
/* loaded from: classes.dex */
public final class C0449l extends Thread {

    /* renamed from: a */
    final /* synthetic */ UploadWorker f2500a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0449l(UploadWorker uploadWorker) {
        this.f2500a = uploadWorker;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        UploadWorker.m2107a(this.f2500a);
    }
}
