package com.smartisan.feedbackhelper;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: ReportProblemDescriptionFragment.java */
/* renamed from: com.smartisan.feedbackhelper.k */
/* loaded from: classes.dex */
final class View$OnTouchListenerC0435k implements View.OnTouchListener {

    /* renamed from: a */
    final /* synthetic */ ReportProblemDescriptionFragment f2453a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnTouchListenerC0435k(ReportProblemDescriptionFragment reportProblemDescriptionFragment) {
        this.f2453a = reportProblemDescriptionFragment;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        return 2 == motionEvent.getAction();
    }
}
