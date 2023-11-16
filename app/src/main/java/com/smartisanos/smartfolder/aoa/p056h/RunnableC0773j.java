package com.smartisanos.smartfolder.aoa.p056h;

import android.content.Context;
import android.os.Environment;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeviceInfoHelper.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.j */
/* loaded from: classes.dex */
public final class RunnableC0773j implements Runnable {

    /* renamed from: a */
    final /* synthetic */ DeviceInfo deviceInfo;
    private Context context;
    private DeviceInfoHelper deviceInfoHelper;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0773j(Context context,DeviceInfoHelper deviceInfoHelper,DeviceInfo deviceInfo) {
        this.context = context;
        this.deviceInfoHelper=deviceInfoHelper;
        this.deviceInfo = deviceInfo;
    }

    @Override // java.lang.Runnable
    public final void run() {
        DeviceInfo deviceInfo = this.deviceInfo;
        deviceInfo.audioSize = StorageUtils.getAudioSize(context);
        deviceInfo.pictureVideoSize = StorageUtils.m533a(Environment.DIRECTORY_DCIM, Environment.DIRECTORY_MOVIES, Environment.DIRECTORY_PICTURES);
        deviceInfo.downloadSize = StorageUtils.m533a(Environment.DIRECTORY_DOWNLOADS);
        StorageUtils.m536a(context, new C0774k(deviceInfoHelper,deviceInfo));
    }
}
