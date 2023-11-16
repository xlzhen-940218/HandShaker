package com.smartisan.updater;

import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Environment;
import com.smartisan.trackerlib.Agent;
import com.smartisan.updater.C0481l;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/* renamed from: com.smartisan.updater.j */
/* loaded from: classes.dex */
public final class OsUpdateNotifer {

    /* renamed from: a */
    private String f2731a;

    /* renamed from: b */
    private AlertDialog f2732b;

    /* renamed from: a */
    public final void m1853a(Context context) throws Throwable {
        if (m1854a()) {
            this.f2731a = "A" + UpdateUtils.m1834d(context);
            Agent.m1952a().m1951a((Application) context.getApplicationContext());
            if (this.f2732b == null) {
                this.f2732b = new AlertDialog.Builder(context, 5).setTitle(C0481l.C0482a.os_update_dlg_title).setMessage(C0481l.C0482a.os_update_dlg_msg).setNegativeButton(C0481l.C0482a.os_update_dlg_cancel_btn, (DialogInterface.OnClickListener) null).setPositiveButton(C0481l.C0482a.os_update_dlg_ok_btn, new DialogInterface$OnClickListenerC0480k(this, context)).create();
                this.f2732b.setCancelable(false);
                this.f2732b.setCanceledOnTouchOutside(false);
            }
            if (this.f2732b.isShowing()) {
                return;
            }
            this.f2732b.show();
            if (this.f2731a == null) {
                return;
            }
            Agent.m1952a().m1950a(this.f2731a + 8000);
            Agent.m1952a().m1942e();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x0070 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static boolean m1854a() throws Throwable {
        FileInputStream fileInputStream;
        Throwable th;
        Exception e;
        String property;
        FileInputStream fileInputStream2 = null;
        if (!Build.MANUFACTURER.equals("smartisan")) {
            return false;
        }
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(new File(Environment.getRootDirectory(), "build.prop"));
        } catch (IOException e1) {
            e = e1;
            fileInputStream = null;
        } catch (Throwable th1) {
            th = th1;
            if (fileInputStream2 != null) {
            }
            throw th;
        }
        try {
            try {
                properties.load(fileInputStream);
                try {
                    fileInputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            e = e4;
            e.printStackTrace();
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            return properties.size() <= 0 ? false : false;
        }
        if (properties.size() <= 0 && (property = properties.getProperty("ro.smartisan.version", null)) != null) {
            String[] split = property.split("-")[0].split("\\.");
            int[] iArr = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                try {
                    iArr[i] = Integer.parseInt(split[i]);
                } catch (NumberFormatException e6) {
                    e6.printStackTrace();
                }
            }
            if (iArr[0] <= 2) {
                if (iArr[0] != 2 || iArr[1] <= 5) {
                    return iArr.length < 3 || iArr[0] != 2 || iArr[1] != 5 || iArr[2] < 8;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
