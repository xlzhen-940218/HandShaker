package com.smartisanos.smartfolder.aoa.p056h;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p049a.StorageObserverManager;
import java.io.Closeable;

/* renamed from: com.smartisanos.smartfolder.aoa.h.q */
/* loaded from: classes.dex */
public final class DocumentFileChangeObserver extends StorageChangeObserver {

    /* renamed from: a */
    private static int f3746a = 0;

    /* renamed from: b */
    private final Uri f3747b;

    /* renamed from: d */
    private String f3748d;

    /* renamed from: e */
    private C0779a f3749e;

    /* renamed from: f */
    private HandlerThread f3750f;

    /* renamed from: g */
    private Cursor f3751g;

    public DocumentFileChangeObserver(String str, Uri uri, Uri uri2) {
        StringBuilder sb = new StringBuilder("DocFileChangeObserver-");
        int i = f3746a;
        f3746a = i + 1;
        this.f3750f = new HandlerThread(sb.append(i).toString());
        this.f3750f.start();
        this.f3748d = str;
        Handler handler = new Handler(this.f3750f.getLooper());
        this.f3747b = uri;
        this.f3749e = new C0779a(this.f3748d, uri2, handler);
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    /* renamed from: a */
    public final synchronized void mo418a() {
        try {
            this.f3751g = FolderApp.getInstance().getContentResolver().query(this.f3747b, new String[]{"document_id"}, null, null, null);
            this.f3749e.m419a();
        } catch (Exception e) {
            HandShaker.warn("DocumentFileChangeObserver start failed: " + e);
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    /* renamed from: b */
    public final synchronized void mo417b() {
        CommonUtils.m509a(this.f3751g);
        FolderApp.getInstance().getContentResolver().unregisterContentObserver(this.f3749e);
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    /* renamed from: c */
    public final String mo416c() {
        return this.f3748d;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    public final void finalize() {
        super.finalize();
        f3746a--;
    }

    /* compiled from: DocumentFileChangeObserver.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.q$a */
    /* loaded from: classes.dex */
    private class C0779a extends ContentObserver {

        /* renamed from: b */
        private final String f3753b;

        /* renamed from: c */
        private final Uri f3754c;

        public C0779a(String str, Uri uri, Handler handler) {
            super(handler);
            this.f3753b = str;
            this.f3754c = uri;
        }

        /* renamed from: a */
        public final void m419a() {
            FolderApp.getInstance().getContentResolver().registerContentObserver(this.f3754c, true, this);
        }

        /* JADX WARN: Not initialized variable reg: 1, insn: 0x004f: MOVE  (r6 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:20:0x004f */
        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            Closeable closeable;
            Cursor cursor;
            Closeable closeable2 = null;
            try {
                try {
                    try {
                        cursor = FolderApp.getInstance().getContentResolver().query(DocumentFileChangeObserver.this.f3747b, new String[]{"document_id"}, null, null, null);
                    } catch (Exception e) {
                        e = e;
                        cursor = null;
                        e.printStackTrace();
                        CommonUtils.m509a(cursor);
                    } catch (Throwable th) {
                        th = th;
                        CommonUtils.m509a(closeable2);
                        throw th;
                    }
                } catch (IllegalArgumentException e2) {
                    HandShaker.info("ExtSdcard maybe Unplugged");
                    cursor = null;
                }
                try {
                    if (cursor == null) {
                        StorageObserverManager.m792a().m791a(this.f3753b);
                    } else {
                        DocumentFileChangeObserver.this.m544a(5632, this.f3753b, DocumentFileChangeObserver.this);
                    }
                    CommonUtils.m509a(cursor);
                } catch (Exception e3) {
                    CommonUtils.m509a(cursor);
                }
            } catch (Throwable th2) {
            }
        }
    }
}
