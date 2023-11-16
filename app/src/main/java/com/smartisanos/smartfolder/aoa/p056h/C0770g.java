package com.smartisanos.smartfolder.aoa.p056h;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeviceInfoHelper.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.g */
/* loaded from: classes.dex */
public final class C0770g implements StorageUtils.InterfaceC0759a {

    /* renamed from: a */
    final /* synthetic */ DeviceInfoHelper deviceInfoHelper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0770g(DeviceInfoHelper deviceInfoHelper) {
        this.deviceInfoHelper = deviceInfoHelper;
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageUtils.InterfaceC0759a
    /* renamed from: a */
    public final void mo423a(long j, long j2) {
        this.deviceInfoHelper.deviceInfo.appSize = j;
        this.deviceInfoHelper.deviceInfo.cacheSize = j2;
        this.deviceInfoHelper.deviceInfo.otherSize = ((((this.deviceInfoHelper.deviceInfo.usedSpaceSize
                - this.deviceInfoHelper.deviceInfo.appSize) - this.deviceInfoHelper.deviceInfo.audioSize)
                - this.deviceInfoHelper.deviceInfo.pictureVideoSize) - this.deviceInfoHelper.deviceInfo.downloadSize)
                - this.deviceInfoHelper.deviceInfo.cacheSize;
        this.deviceInfoHelper.notifyListener();
    }
}
