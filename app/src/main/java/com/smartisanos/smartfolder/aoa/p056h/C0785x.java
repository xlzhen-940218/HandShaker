package com.smartisanos.smartfolder.aoa.p056h;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MediaDataProvider.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.x */
/* loaded from: classes.dex */
public final class C0785x implements Handler.Callback {

    /* renamed from: a */
    final /* synthetic */ MediaDataProvider f3778a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0785x(MediaDataProvider mediaDataProvider) {
        this.f3778a = mediaDataProvider;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.f3778a.m388f();
                return false;
            default:
                return false;
        }
    }
}
