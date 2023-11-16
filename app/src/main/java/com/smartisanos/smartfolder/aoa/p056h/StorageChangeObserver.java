package com.smartisanos.smartfolder.aoa.p056h;

import android.provider.MediaStore;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import java.util.HashSet;

/* renamed from: com.smartisanos.smartfolder.aoa.h.ab */
/* loaded from: classes.dex */
public abstract class StorageChangeObserver {

    /* renamed from: a */
    private static final String f3650a = StorageChangeObserver.class.getSimpleName();

    /* renamed from: b */
    private static final HashSet<String> f3651b;

    /* renamed from: c */
    InterfaceC0758a f3652c;

    /* renamed from: d */
    private boolean f3653d = false;

    /* compiled from: StorageChangeObserver.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.ab$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0758a {
        /* renamed from: a */
        void mo538a(int i, String str, StorageChangeObserver storageChangeObserver);
    }

    /* renamed from: a */
    public abstract void mo418a();

    /* renamed from: b */
    public abstract void mo417b();

    /* renamed from: c */
    public abstract String mo416c();

    static {
        HashSet<String> hashSet = new HashSet<>();
        f3651b = hashSet;
        hashSet.add(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString());
        f3651b.add(MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString());
        f3651b.add(MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString());
    }

    /* renamed from: e */
    public final void m541e() {
        mo418a();
        this.f3653d = true;
    }

    /* renamed from: f */
    public final void m540f() {
        if (this.f3653d) {
            mo417b();
            this.f3653d = false;
        }
    }

    /* renamed from: a */
    public final void m543a(InterfaceC0758a interfaceC0758a) {
        this.f3652c = interfaceC0758a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m544a(int i, String str, StorageChangeObserver storageChangeObserver) {
        if (this.f3652c != null) {
            this.f3652c.mo538a(i, str, storageChangeObserver);
        } else {
            HandShaker.info(f3650a, "storage change, but no listener set");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    public void finalize() {
        //super.finalize();
        m540f();
    }

    /* renamed from: a */
    public static StorageChangeObserver m542a(String str) {
        HandShaker.debug(f3650a, "getStorageChangeObserverForPath:" + str);
        return f3651b.contains(str) ? new ContentStorageChangeObserver(str) : new FileStorageChangeObserver(str);
    }

    /* renamed from: g */
    public final EventManager.REQUEST_TYPE m539g() {
        String mo416c = mo416c();
        if (MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.toString().equals(mo416c)) {
            return EventManager.REQUEST_TYPE.AUDIO;
        }
        if (MediaStore.Images.Media.EXTERNAL_CONTENT_URI.toString().equals(mo416c)) {
            return EventManager.REQUEST_TYPE.IMAGE;
        }
        if (MediaStore.Video.Media.EXTERNAL_CONTENT_URI.toString().equals(mo416c)) {
            return EventManager.REQUEST_TYPE.VIDEO;
        }
        return EventManager.REQUEST_TYPE.FILE;
    }
}
