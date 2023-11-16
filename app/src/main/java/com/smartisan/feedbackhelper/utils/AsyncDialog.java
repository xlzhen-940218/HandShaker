package com.smartisan.feedbackhelper.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;

/* renamed from: com.smartisan.feedbackhelper.utils.a */
/* loaded from: classes.dex */
public final class AsyncDialog {

    /* renamed from: a */
    public ProgressDialog progressDialog;

    /* renamed from: b */
    private final Activity activity;

    /* renamed from: d */
    private boolean f2560d;

    /* renamed from: e */
    private Runnable f2561e = new RunnableC0454b(this);

    /* renamed from: c */
    private final Handler f2559c = new Handler();

    /* renamed from: e */
    static /* synthetic */ boolean m2056e(AsyncDialog asyncDialog) {
        asyncDialog.f2560d = true;
        return true;
    }

    public AsyncDialog(Activity activity) {
        this.activity = activity;
    }

    /* renamed from: a */
    public final void m2060a(Runnable runnable, int i) {
        if (!this.f2560d) {
            new AsyncTaskC0453a(i).execute(runnable);
        }
    }

    /* renamed from: a */
    public final void m2063a() {
        this.f2559c.removeCallbacks(this.f2561e);
        this.f2560d = false;
        if (!this.activity.isFinishing()) {
            if (this.progressDialog != null && this.progressDialog.isShowing()) {
                this.progressDialog.dismiss();
            }
            this.progressDialog = null;
        }
    }

    /* compiled from: AsyncDialog.java */
    /* renamed from: com.smartisan.feedbackhelper.utils.a$a */
    /* loaded from: classes.dex */
    private class AsyncTaskC0453a extends AsyncTask<Runnable, Void, Void> {

        /* renamed from: a */
        final Runnable f2562a = null;

        @Override // android.os.AsyncTask
        protected final /* synthetic */ Void doInBackground(Runnable[] runnableArr) {
            Runnable[] runnableArr2 = runnableArr;
            if (runnableArr2 != null) {
                for (Runnable runnable : runnableArr2) {
                    runnable.run();
                }
                return null;
            }
            return null;
        }

        public AsyncTaskC0453a(int i) {
            if (AsyncDialog.this.progressDialog == null) {
                ProgressDialog progressDialog = new ProgressDialog(AsyncDialog.this.activity, 16973940);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setCancelable(false);
                progressDialog.getWindow().setBackgroundDrawableResource(17170445);
                AsyncDialog.this.progressDialog = progressDialog;
            }
            AsyncDialog.this.progressDialog.setMessage(AsyncDialog.this.activity.getText(i));
        }

        @Override // android.os.AsyncTask
        protected final void onPreExecute() {
            AsyncDialog.this.f2559c.postDelayed(AsyncDialog.this.f2561e, 0L);
            AsyncDialog.m2056e(AsyncDialog.this);
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(Void r2) {
            if (this.f2562a == null) {
                return;
            }
            this.f2562a.run();
        }
    }
}
