package com.smartisanos.smartfolder.aoa.p056h;

import android.content.Context;
import android.text.format.Formatter;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DeviceInfoHelper.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.h */
/* loaded from: classes.dex */
public final class RunnableC0771h implements Runnable {

    /* renamed from: a */
    final /* synthetic */ DeviceInfoHelper f3736a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0771h(DeviceInfoHelper deviceInfoHelper) {
        this.f3736a = deviceInfoHelper;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        String formatFileSize;
        Context context2;
        String formatFileSize2;
        Context context3;
        String formatFileSize3;
        Context context4;
        String formatFileSize4;
        Context context5;
        String formatFileSize5;
        Context context6;
        String formatFileSize6;
        Context context7;
        String formatFileSize7;
        Context context8;
        String formatFileSize8;
        EventManager.getInstance();
        if (EventManager.m794c() && DeviceInfoHelper.needDeviceInfo) {
            this.f3736a.m462f();
            DeviceInfo m466d = this.f3736a.getDeviceInfo();
            SmartSyncProtocolProtos.SSPGetDeviceInfoResponse d = SmartSyncProtocolProtos.SSPGetDeviceInfoResponse
                    .newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.GET_DEVICE_INFO_REQUEST)
                    .setApkVersion(m466d.getVersionCode())
                    .setClientTimestamp(System.currentTimeMillis() / 1000)
                    .setClientSmartSyncProtocolVersion("1")
                    .setPhoneModel(m466d.getProductName())
                    .setPhoneColor(m466d.getHousingColor())
                    .setDiskSize(m466d.getDeviceSpace())
                    .setExtDiskSize(m466d.getSdTotalSize())
                    .setRamSize(m466d.getMemoryInfo())
                    .setBatteryCapacity(DeviceInfo.getBatteryCapacity())
                    .setBatteryPercentage(m466d.getBatteryPercentage())
                    .setPhoneName(m466d.getDeviceName())
                    .setUsedDiskSize(m466d.getUsedSpaceSize())
                    .setExtUsedDiskSize(m466d.getUseSpace())
                    .setRootPath(m466d.getFullPath())
                    .setProductBrand(m466d.getProductBrand())
                    .setProductManufacturer(m466d.getProductManufacturer())
                    .setSmartisanVersion(m466d.getSmartISanVersion())
                    .setPhoneLocked(CommonUtils.isKeyguardLocked())
                    .setExternalStoragePath(m466d.getExternalStoragePath())
                    .setAudioSize(m466d.audioSize)
                    .setPicVideoSize(m466d.pictureVideoSize)
                    .setDownloadSize(m466d.downloadSize)
                    .setAppSize(m466d.appSize)
                    .setCacheSize(m466d.cacheSize)
                    .setOtherSize(m466d.otherSize)
                    .setDebugBuildTime(CommonUtils.getDebugBuildTime()).build();
            StringBuilder sb = new StringBuilder("音频:");
            context = this.f3736a.context;
            formatFileSize = Formatter.formatFileSize(context, m466d.audioSize);
            HandShaker.debug(sb.append(formatFileSize).append(", ").append(m466d.audioSize).toString());
            StringBuilder sb2 = new StringBuilder("图片视频:");
            context2 = this.f3736a.context;
            formatFileSize2 = Formatter.formatFileSize(context2, m466d.pictureVideoSize);
            HandShaker.debug(sb2.append(formatFileSize2).append(", ").append(m466d.pictureVideoSize).toString());
            StringBuilder sb3 = new StringBuilder("下载:");
            context3 = this.f3736a.context;
            formatFileSize3 = Formatter.formatFileSize(context3, m466d.downloadSize);
            HandShaker.debug(sb3.append(formatFileSize3).append(", ").append(m466d.downloadSize).toString());
            StringBuilder sb4 = new StringBuilder("应用:");
            context4 = this.f3736a.context;
            formatFileSize4 = Formatter.formatFileSize(context4, m466d.appSize);
            HandShaker.debug(sb4.append(formatFileSize4).append(", ").append(m466d.appSize).toString());
            StringBuilder sb5 = new StringBuilder("缓存:");
            context5 = this.f3736a.context;
            formatFileSize5 = Formatter.formatFileSize(context5, m466d.cacheSize);
            HandShaker.debug(sb5.append(formatFileSize5).append(", ").append(m466d.cacheSize).toString());
            StringBuilder sb6 = new StringBuilder("其他:");
            context6 = this.f3736a.context;
            formatFileSize6 = Formatter.formatFileSize(context6, m466d.otherSize);
            HandShaker.debug(sb6.append(formatFileSize6).append(", ").append(m466d.otherSize).toString());
            StringBuilder sb7 = new StringBuilder("总容量:");
            context7 = this.f3736a.context;
            formatFileSize7 = Formatter.formatFileSize(context7, d.getExtDiskSize());
            HandShaker.debug(sb7.append(formatFileSize7).append(", ").append(d.getExtDiskSize()).toString());
            StringBuilder sb8 = new StringBuilder("可用容量:");
            context8 = this.f3736a.context;
            formatFileSize8 = Formatter.formatFileSize(context8, d.getExtUsedDiskSize());
            HandShaker.debug(sb8.append(formatFileSize8).append(", ").append(d.getExtUsedDiskSize()).toString());
            HandShaker.debug("DeviceInfoHelper", "notifyListener return " + d.getExtDiskSize() + ", " + d.getExtUsedDiskSize());
            EventManager.getInstance().m798a(EventManager.REQUEST_TYPE.DEVICE_INFO, d.toByteArray());
        }
    }
}
