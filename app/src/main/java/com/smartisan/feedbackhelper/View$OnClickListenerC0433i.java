package com.smartisan.feedbackhelper;

import android.content.Context;
import android.view.View;

/* compiled from: ReportProblemDescriptionFragment.java */
/* renamed from: com.smartisan.feedbackhelper.i */
/* loaded from: classes.dex */
final class View$OnClickListenerC0433i implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ReportProblemDescriptionFragment f2451a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0433i(ReportProblemDescriptionFragment reportProblemDescriptionFragment) {
        this.f2451a = reportProblemDescriptionFragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Context context;
        if (!this.f2451a.m2123c()) {
            context = this.f2451a.context;
            ((FeedbackActivity) context).finish();
            return;
        }
        this.f2451a.m2121d();
    }
}
