package com.smartisanos.smartfolder.aoa.view;

import android.view.View;

/* compiled from: ShareView.java */
/* renamed from: com.smartisanos.smartfolder.aoa.view.f */
/* loaded from: classes.dex */
final class View$OnClickListenerC0836f implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ShareView f3984a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0836f(ShareView shareView) {
        this.f3984a = shareView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.f3984a.isShowing()) {
            this.f3984a.dismiss();
        }
    }
}
