package com.smartisanos.smartfolder.aoa.p056h;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;

/* renamed from: com.smartisanos.smartfolder.aoa.h.e */
/* loaded from: classes.dex */
public class ContentStorageChangeObserver extends StorageChangeObserver {

    /* renamed from: a */
    private static final String f3678a = ContentStorageChangeObserver.class.getSimpleName();

    /* renamed from: b */
    private static int f3679b = 0;

    /* renamed from: d */
    private C0762a f3680d;

    /* renamed from: e */
    private HandlerThread f3681e;

    public ContentStorageChangeObserver(String str) {
        StringBuilder sb = new StringBuilder("contentobserver-");
        int i = f3679b;
        f3679b = i + 1;
        this.f3681e = new HandlerThread(sb.append(i).toString());
        this.f3681e.start();
        this.f3680d = new C0762a(Uri.parse(str), new Handler(this.f3681e.getLooper()));
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    /* renamed from: a */
    public final void mo418a() {
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    /* renamed from: b */
    public final void mo417b() {
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    /* renamed from: c */
    public final String mo416c() {
        return this.f3680d.f3683b.toString();
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    public void finalize() {
        super.finalize();
        f3679b--;
    }

    /* compiled from: ContentStorageChangeObserver.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.e$a */
    /* loaded from: classes.dex */
    private class C0762a extends ContentObserver {

        /* renamed from: b */
        private Uri f3683b;

        public C0762a(Uri uri, Handler handler) {
            super(handler);
            this.f3683b = uri;
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            HandShaker.debug(ContentStorageChangeObserver.f3678a, "onChange:toObserve=" + this.f3683b);
            ContentStorageChangeObserver.this.m544a(0, this.f3683b.toString(), ContentStorageChangeObserver.this);
        }
    }
}
