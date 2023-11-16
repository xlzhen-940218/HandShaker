package com.smartisan.feedbackhelper;

import android.content.Intent;
import android.util.Log;
import com.smartisan.feedbackhelper.utils.BugReportException;
import com.smartisan.feedbackhelper.utils.C0462r;
import com.smartisan.feedbackhelper.utils.ReportGenerate;
import java.util.ArrayList;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FeedbackActivity.java */
/* renamed from: com.smartisan.feedbackhelper.c */
/* loaded from: classes.dex */
public final class C0420c extends Thread {

    /* renamed from: a */
    final /* synthetic */ FeedbackActivity f2421a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0420c(FeedbackActivity feedbackActivity) {
        this.f2421a = feedbackActivity;
    }

    /* JADX WARN: Incorrect condition in loop: B:9:0x0044 */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        StringBuilder sb;
        StringBuilder sb2;
        StringBuilder sb3;
        ArrayList arrayList = null;
        ArrayList arrayList2;
        long currentTimeMillis = System.currentTimeMillis();
        try {
            this.f2421a.f2405g = FeedbackActivity.m2134a("feedback", currentTimeMillis).getAbsolutePath();
            String str = "";
            sb = this.f2421a.f2413p;
            if (sb != null) {
                sb2 = this.f2421a.f2413p;
                if (!sb2.toString().equals("")) {
                    sb3 = this.f2421a.f2413p;
                    String sb4 = sb3.toString();
                    for (int i = 0; i < arrayList.size(); i++) {
                        arrayList2 = this.f2421a.f2414q;
                        C0462r.m2012c((String) arrayList2.get(i), this.f2421a.f2405g);
                    }
                    str = sb4;
                }
            }
            Intent intent = new Intent(this.f2421a, ReportGenerate.class);
            intent.setAction("smartisan.intent.action.REPORT.DATA");
            intent.putExtra("tag", "sdk");
            intent.putExtra("from", "sdk");
            intent.putExtra("summary", this.f2421a.f2407i);
            intent.putExtra("description", this.f2421a.f2400b);
            intent.putExtra("createtime", currentTimeMillis);
            intent.putExtra("attach_files", this.f2421a.f2405g);
            intent.putExtra("snapshots", str);
            intent.putExtra("email_add", this.f2421a.f2406h);
            intent.putExtra("packageName", this.f2421a.f2408j);
            this.f2421a.startService(intent);
        } catch (BugReportException e) {
            Log.e(this.f2421a.f2399a, "Error processing others@" + currentTimeMillis, e);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
