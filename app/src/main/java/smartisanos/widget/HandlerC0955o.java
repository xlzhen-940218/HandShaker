package smartisanos.widget;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SwitchEx.java */
/* renamed from: smartisanos.widget.o */
/* loaded from: classes.dex */
public final class HandlerC0955o extends Handler {

    /* renamed from: a */
    final /* synthetic */ SwitchEx f4566a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC0955o(SwitchEx switchEx) {
        this.f4566a = switchEx;
    }

    @Override // android.os.Handler
    public final void dispatchMessage(Message message) {
        if (message.what == 1) {
            this.f4566a.m26a(((Boolean) message.obj).booleanValue(), false);
        }
    }
}
