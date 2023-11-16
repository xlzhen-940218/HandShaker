package com.smartisan.feedbackhelper;

import android.content.Context;
import android.view.View;

/* compiled from: ReportProblemDescriptionFragment.java */
/* renamed from: com.smartisan.feedbackhelper.j */
/* loaded from: classes.dex */
final class View$OnClickListenerC0434j implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ReportProblemDescriptionFragment f2452a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0434j(ReportProblemDescriptionFragment reportProblemDescriptionFragment) {
        this.f2452a = reportProblemDescriptionFragment;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Context context;
        Context context2;
        if (ReportProblemDescriptionFragment.m2122c(this.f2452a)) {
            context = this.f2452a.context;
            if (context instanceof FeedbackActivity) {
                this.f2452a.f2439c.setCursorVisible(false);
                this.f2452a.f2442f.setCursorVisible(false);
                context2 = this.f2452a.context;
                ((FeedbackActivity) context2).m2140a();
            }
        }
    }
}
