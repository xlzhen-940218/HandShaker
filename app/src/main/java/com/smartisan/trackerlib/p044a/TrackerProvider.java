package com.smartisan.trackerlib.p044a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.smartisan.trackerlib.Agent;
import com.smartisan.trackerlib.TransportEntity;
import com.smartisan.trackerlib.p046c.CommonUtil;
import com.smartisan.trackerlib.p046c.LogUtils;

/* renamed from: com.smartisan.trackerlib.a.a */
/* loaded from: classes.dex */
public final class TrackerProvider {

    /* renamed from: a */
    private static Context f2676a;

    /* renamed from: b */
    private static TrackerProvider f2677b;

    /* renamed from: c */
    private static SQLiteDatabase f2678c;

    /* renamed from: a */
    public static TrackerProvider m1941a() {
        if (f2676a == null) {
            f2676a = Agent.m1952a().m1947b();
        }
        if (f2677b == null) {
            f2677b = new TrackerProvider();
        }
        return f2677b;
    }

    private TrackerProvider() {
        f2678c = new C0470a(f2676a).getWritableDatabase();
    }

    /* renamed from: c */
    private static void m1935c() {
        if (f2678c == null || !f2678c.isOpen()) {
            f2678c = new C0470a(f2676a).getWritableDatabase();
        }
    }

    /* renamed from: b */
    public static void m1937b() {
        if (f2678c != null && f2678c.isOpen()) {
            f2678c.close();
            f2678c = null;
        }
    }

    /* renamed from: a */
    public static boolean m1939a(TransportEntity[] transportEntityArr) {
        if (transportEntityArr == null || transportEntityArr.length <= 0) {
            return false;
        }
        m1935c();
        boolean z = true;
        for (int i = 0; i < transportEntityArr.length; i++) {
            long insert = f2678c.insert("actual_raw_transport", null, CommonUtil.m1918a(transportEntityArr[i]));
            if (insert <= 0) {
                LogUtils.m1902a("Failed to insert row : id: " + insert + "--entity: " + transportEntityArr[i].toString());
                z = false;
            }
        }
        return z;
    }

    /* renamed from: a */
    public static void m1938a(String[] strArr) {
        if (strArr.length >= 0) {
            String str = strArr[0];
            for (int i = 1; i < strArr.length; i++) {
                str = str + "," + strArr[i];
            }
            m1940a("_id in (" + str + ")");
        }
    }

    /* renamed from: a */
    public static int m1940a(String str) {
        m1935c();
        return f2678c.delete("actual_raw_transport", str, null);
    }

    /* renamed from: b */
    public static Cursor m1936b(String str) {
        m1935c();
        return f2678c.query("actual_raw_transport", null, str, null, null, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: TrackerProvider.java */
    /* renamed from: com.smartisan.trackerlib.a.a$a */
    /* loaded from: classes.dex */
    public static class C0470a extends SQLiteOpenHelper {
        C0470a(Context context) {
            super(context, "libtracker.db", (SQLiteDatabase.CursorFactory) null, 1);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public final void onCreate(SQLiteDatabase sQLiteDatabase) {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS actual_raw_transport (_id INTEGER PRIMARY KEY AUTOINCREMENT,eventid TEXT,time_stamp LONG,eventdata TEXT,wifionly INTEGER,upload_time INTEGER);");
        }
    }
}
