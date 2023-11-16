package com.smartisan.feedbackhelper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.smartisan.feedbackhelper.utils.AsyncDialog;

/* compiled from: FeedbackActivity.java */
/* renamed from: com.smartisan.feedbackhelper.a */
/* loaded from: classes.dex */
final class C0418a extends BroadcastReceiver {

    /* renamed from: a */
    final /* synthetic */ FeedbackActivity f2419a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0418a(FeedbackActivity feedbackActivity) {
        this.f2419a = feedbackActivity;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        AsyncDialog asyncDialog;
        ReportProblemDescriptionFragment reportProblemDescriptionFragment;
        String str = null;
        if (intent != null) {
            str = intent.getAction();
        }
        asyncDialog = this.f2419a.f2416s;
        asyncDialog.m2063a();
        if (str.equals("smartisan.intent.action.BUGREPORT.REPORT_UPLOAD_SUCCESS")) {
            reportProblemDescriptionFragment = this.f2419a.f2412o;
            reportProblemDescriptionFragment.m2125b();
            this.f2419a.finish();
        }
    }
}
