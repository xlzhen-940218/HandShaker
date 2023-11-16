package com.smartisan.feedbackhelper.utils;

import android.view.View;
import java.util.LinkedList;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ScreenShotsAdapter.java */
/* renamed from: com.smartisan.feedbackhelper.utils.o */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0459o implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ScreenShotsAdapter f2609a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0459o(ScreenShotsAdapter screenShotsAdapter) {
        this.f2609a = screenShotsAdapter;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        LinkedList linkedList;
        int intValue = ((Integer) view.getTag()).intValue();
        linkedList = this.f2609a.f2608g;
        linkedList.remove(intValue);
        this.f2609a.notifyDataSetChanged();
    }
}
