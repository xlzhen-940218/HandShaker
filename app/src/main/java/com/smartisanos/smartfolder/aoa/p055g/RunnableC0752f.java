package com.smartisanos.smartfolder.aoa.p055g;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SSPEventDispatcher.java */
/* renamed from: com.smartisanos.smartfolder.aoa.g.f */
/* loaded from: classes.dex */
public final class RunnableC0752f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int f3611a;

    /* renamed from: b */
    final /* synthetic */ Object[] f3612b;

    /* renamed from: c */
    final /* synthetic */ SSPEventDispatcher f3613c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0752f(SSPEventDispatcher sSPEventDispatcher, int i, Object[] objArr) {
        this.f3613c = sSPEventDispatcher;
        this.f3611a = i;
        this.f3612b = objArr;
    }

    @Override // java.lang.Runnable
    public final void run() {
        SSPEventDispatcher.m594a(this.f3613c, this.f3611a, this.f3612b);
    }
}
