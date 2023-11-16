package com.smartisanos.smartfolder.aoa.p056h;

import android.os.Environment;
import android.os.FileObserver;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;

/* renamed from: com.smartisanos.smartfolder.aoa.h.r */
/* loaded from: classes.dex */
public class FileStorageChangeObserver extends StorageChangeObserver {

    /* renamed from: d */
    private static final String f3755d = FileStorageChangeObserver.class.getSimpleName();

    /* renamed from: e */
    private static final String f3756e = Environment.getExternalStorageDirectory().getAbsolutePath();

    /* renamed from: a */
    FileObserver f3757a;

    /* renamed from: b */
    String f3758b;

    public FileStorageChangeObserver(String str) {
        this.f3758b = str;
        this.f3757a = new FileObserverC0780a(str);
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    /* renamed from: a */
    public final void mo418a() {
        this.f3757a.startWatching();
        EventManager.getInstance().m799a(EventManager.REQUEST_TYPE.FILE);
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    /* renamed from: b */
    public final void mo417b() {
        this.f3757a.stopWatching();
        EventManager.getInstance().m795b(EventManager.REQUEST_TYPE.FILE);
        HandShaker.debug(f3755d, "onStop");
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver
    /* renamed from: c */
    public final String mo416c() {
        return ((FileObserverC0780a) this.f3757a).m415a();
    }

    /* compiled from: FileStorageChangeObserver.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.r$a */
    /* loaded from: classes.dex */
    private class FileObserverC0780a extends FileObserver {

        /* renamed from: b */
        private String f3760b;

        public FileObserverC0780a(String str) {
            super(str, 4040);
            this.f3760b = str;
        }

        @Override // android.os.FileObserver
        public final void onEvent(int i, String str) {
            FileStorageChangeObserver.this.m544a(i & 65535, str == null ? FileStorageChangeObserver.this.f3758b : FileStorageChangeObserver.this.f3758b + "/" + str, FileStorageChangeObserver.this);
        }

        /* renamed from: a */
        public final String m415a() {
            return this.f3760b;
        }
    }
}
