package com.smartisanos.smartfolder.aoa.p056h;

/* compiled from: DeviceInfoHelper.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.k */
/* loaded from: classes.dex */
final class C0774k implements StorageUtils.InterfaceC0759a {

    /* renamed from: a */
    final /* synthetic */ DeviceInfo deviceInfo;
    DeviceInfoHelper deviceInfoHelper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0774k(DeviceInfoHelper deviceInfoHelper, DeviceInfo deviceInfo) {
        this.deviceInfo = deviceInfo;
        this.deviceInfoHelper = deviceInfoHelper;
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageUtils.InterfaceC0759a
    /* renamed from: a */
    public final void mo423a(long j, long j2) {
        this.deviceInfo.appSize = j;
        this.deviceInfo.cacheSize = j2;
        this.deviceInfo.initOther();
        deviceInfoHelper.notifyListener();
    }
}
