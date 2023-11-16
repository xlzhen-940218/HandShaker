package com.smartisan.moreapps;

import android.content.ComponentName;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.smartisanos.smartfolder.aoa.R;

import java.util.ArrayList;

/* renamed from: com.smartisan.moreapps.a */
/* loaded from: classes.dex */
public final class AppInfoList {

    /* renamed from: a */
    public boolean[] f2625a;

    /* renamed from: b */
    public String[] f2626b;

    /* renamed from: c */
    public String[] f2627c;

    /* renamed from: d */
    private ArrayList<C0464a> f2628d;

    /* renamed from: e */
    private Context f2629e;

    /* renamed from: f */
    private final String[] f2630f;

    public AppInfoList() {
        this.f2625a = new boolean[]{false, false, false, false, false, false, false};
        this.f2626b = new String[]{"com.smartisanos.home", "com.smartisan.notes", "com.smartisan.calendar", "com.smartisan.clock", "com.smartisan.email", "com.smartisan.mover", "com.smartisan.reader"};
        this.f2627c = new String[this.f2626b.length];
        this.f2630f = new String[]{"http://update.smartisanos.com/launcher/update_info", "http://update.smartisanos.com/notes/update_info", "http://update.smartisanos.com/calendar/update_info", "http://update.smartisanos.com/clock/update_info", "http://update.smartisanos.com/email/update_info", "http://update.smartisanos.com/smiling_cloud_sync/update_info", "http://update.smartisanos.com/reader/update_info"};
    }

    public AppInfoList(Context context) {
        this.f2625a = new boolean[]{false, false, false, false, false, false, false};
        this.f2626b = new String[]{"com.smartisanos.home", "com.smartisan.notes", "com.smartisan.calendar", "com.smartisan.clock", "com.smartisan.email", "com.smartisan.mover", "com.smartisan.reader"};
        this.f2627c = new String[this.f2626b.length];
        this.f2630f = new String[]{"http://update.smartisanos.com/launcher/update_info", "http://update.smartisanos.com/notes/update_info", "http://update.smartisanos.com/calendar/update_info", "http://update.smartisanos.com/clock/update_info", "http://update.smartisanos.com/email/update_info", "http://update.smartisanos.com/smiling_cloud_sync/update_info", "http://update.smartisanos.com/reader/update_info"};
        this.f2629e = context;
    }

    /* renamed from: a */
    public final void m2005a() {
        int[] iArr = {R.drawable.launcher, R.drawable.notes, R.drawable.calendar, R.drawable.clock
                , R.drawable.email, R.drawable.mover, R.drawable.reader};
        int[] iArr2 = {R.string.launcher_app_txt, R.string.notes_app_txt, R.string.calendar_app_txt,
                R.string.clock_app_txt, R.string.email_app_txt, R.string.mover_app_txt, R.string.reader_app_txt};
        int[] iArr3 = {R.string.launcher_des_txt, R.string.notes_des_txt, R.string.calendar_des_txt
                , R.string.clock_des_txt, R.string.email_des_txt, R.string.mover_des_txt, R.string.reader_des_txt};
        this.f2628d = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            int i2 = iArr[i];
            int integer = this.f2629e.getResources().getInteger(R.integer.item_icon_size);
            Bitmap decodeResource = BitmapFactory.decodeResource(this.f2629e.getResources(), i2);
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeResource, integer, integer, true);
            if (createScaledBitmap != decodeResource) {
                decodeResource.recycle();
            }
            this.f2628d.add(new C0464a(this.f2625a[i], this.f2626b[i], this.f2629e.getResources().getString(iArr2[i]), this.f2629e.getResources().getString(iArr3[i]), this.f2630f[i], createScaledBitmap));
        }
    }

    /* renamed from: a */
    public final void m2001a(ArrayList<C0464a> arrayList) {
        this.f2628d = arrayList;
    }

    /* renamed from: a */
    public final void m2002a(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < this.f2626b.length; i++) {
                if (str.equals(this.f2626b[i])) {
                    this.f2627c[i] = str2;
                }
            }
        }
    }

    /* renamed from: a */
    public final C0464a m2004a(int i) {
        if (i < 0 || i >= this.f2628d.size()) {
            return null;
        }
        return this.f2628d.get(i);
    }

    /* renamed from: b */
    public final int m2000b() {
        return this.f2628d.size();
    }

    /* renamed from: a */
    public final boolean m2003a(String str) {
        if (!TextUtils.isEmpty(str)) {
            int i = -1;
            for (int i2 = 0; i2 < this.f2628d.size(); i2++) {
                if (this.f2628d.get(i2).f2634d.equals(str)) {
                    i = i2;
                }
            }
            if (i >= 0 && i < this.f2628d.size()) {
                this.f2628d.remove(i);
            }
        }
        return false;
    }

    /* renamed from: b */
    public final ComponentName m1999b(String str) {
        if (!TextUtils.isEmpty(str)) {
            for (int i = 0; i < this.f2626b.length; i++) {
                if (this.f2626b[i].equals(str) && i >= 0 && i < this.f2627c.length) {
                    return new ComponentName(str, this.f2627c[i]);
                }
            }
        }
        return null;
    }

    /* renamed from: c */
    public final String m1998c(String str) {
        if (!TextUtils.isEmpty(str)) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= this.f2628d.size()) {
                    break;
                }
                C0464a c0464a = this.f2628d.get(i2);
                if (!c0464a.f2634d.equals(str)) {
                    i = i2 + 1;
                } else {
                    return c0464a.f2635e;
                }
            }
        }
        return null;
    }

    /* compiled from: AppInfoList.java */
    /* renamed from: com.smartisan.moreapps.a$a */
    /* loaded from: classes.dex */
    public static class C0464a {

        /* renamed from: a */
        public boolean f2631a;

        /* renamed from: b */
        public String f2632b;

        /* renamed from: c */
        public String f2633c;

        /* renamed from: d */
        public String f2634d;

        /* renamed from: e */
        public String f2635e;

        /* renamed from: f */
        public Bitmap f2636f;

        public C0464a(boolean z, String str, String str2, String str3, String str4, Bitmap bitmap) {
            this.f2631a = false;
            this.f2631a = z;
            this.f2632b = str2;
            this.f2634d = str;
            this.f2633c = str3;
            this.f2635e = str4;
            this.f2636f = bitmap;
        }
    }
}
