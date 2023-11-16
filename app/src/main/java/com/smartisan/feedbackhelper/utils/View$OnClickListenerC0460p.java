package com.smartisan.feedbackhelper.utils;

import android.content.Context;
import android.view.View;
import com.smartisan.feedbackhelper.FeedbackActivity;
import com.smartisan.feedbackhelper.ReportProblemDescriptionFragment;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ScreenShotsAdapter.java */
/* renamed from: com.smartisan.feedbackhelper.utils.p */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0460p implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ScreenShotsAdapter f2610a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0460p(ScreenShotsAdapter screenShotsAdapter) {
        this.f2610a = screenShotsAdapter;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Context context;
        ((View) view.getParent()).setEnabled(false);
        FeedbackActivity.f2398l = Integer.parseInt(view.getTag().toString());
        context = this.f2610a.context;
        ReportProblemDescriptionFragment.m2127a(context, "image/*");
        this.f2610a.f2604c.postDelayed(new RunnableC0461q(this, view), 700L);
    }
}
