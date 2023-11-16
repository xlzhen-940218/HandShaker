package com.smartisanos.smartfolder.aoa.p056h;

import android.widget.Toast;
import com.smartisanos.smartfolder.aoa.R;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ToastHelper.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.ag */
/* loaded from: classes.dex */
public final class RunnableC0761ag implements Runnable {

    /* renamed from: a */
    final /* synthetic */ int textRes = R.string.error_proto;

    /* renamed from: b */
    final /* synthetic */ int duration = 0;

    @Override // java.lang.Runnable
    public final void run() {
        Toast toast;
        Toast toast2;
        Toast toast3;
        toast = ToastHelper.toast;
        toast.setText(this.textRes);
        toast2 = ToastHelper.toast;
        toast2.setDuration(this.duration);
        toast3 = ToastHelper.toast;
        toast3.show();
    }
}
