package com.smartisan.trackerlib;

import com.smartisan.trackerlib.p045b.UploadTask;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.smartisan.trackerlib.b */
/* loaded from: classes.dex */
public final class TrackerCache {

    /* renamed from: b */
    private int f2680b = 9;

    /* renamed from: a */
    private CopyOnWriteArrayList<TransportEntity> f2679a = new CopyOnWriteArrayList<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1933a(TransportEntity transportEntity) {
        this.f2679a.add(transportEntity);
        if (this.f2679a.size() <= this.f2680b) {
            return;
        }
        m1934a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m1934a() {
        TransportEntity[] transportEntityArr = new TransportEntity[this.f2679a.size()];
        this.f2679a.toArray(transportEntityArr);
        this.f2679a.clear();
        if (transportEntityArr.length <= 0) {
            return;
        }
        new UploadTask(transportEntityArr).m1932a();
    }
}
