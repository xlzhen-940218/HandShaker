package com.smartisanos.smartfolder.aoa.p056h;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.R;

/* renamed from: com.smartisanos.smartfolder.aoa.h.af */
/* loaded from: classes.dex */
public final class ToastHelper {

    /* renamed from: a */
    private static Handler f3666a;

    /* renamed from: b */
    public static Toast toast;

    /* renamed from: c */
    private static boolean f3668c = false;

    @SuppressLint({"ShowToast"})
    /* renamed from: d */
    private static synchronized void m524d() {
        synchronized (ToastHelper.class) {
            if (!f3668c) {
                f3668c = true;
                toast = Toast.makeText(FolderApp.getInstance(), "", Toast.LENGTH_SHORT);
                f3666a = new Handler(Looper.getMainLooper());
            }
        }
    }

    /* renamed from: a */
    public static void m527a() {
        if (!f3668c) {
            m524d();
        }
    }

    /* renamed from: b */
    public static void m526b() {
        m527a();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            f3666a.post(new RunnableC0761ag());
            return;
        }
        toast.setText(R.string.error_proto);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }
}
