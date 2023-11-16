package com.smartisanos.smartfolder.aoa.p056h;

import android.content.Context;
import android.text.TextUtils;

import com.smartisanos.smartfolder.aoa.FolderApp;

public class DeviceInfo {

    /* renamed from: a */
    public long audioSize;

    /* renamed from: b */
    public long pictureVideoSize;

    /* renamed from: c */
    public long downloadSize;

    /* renamed from: d */
    public long otherSize;

    /* renamed from: e */
    public long appSize;

    /* renamed from: f */
    public long cacheSize;

    /* renamed from: h */
    public int level;

    /* renamed from: i */
    public String deviceName;

    /* renamed from: j */
    public String productName;

    /* renamed from: k */
    public String housingColor;

    /* renamed from: l */
    public long deviceSpace;

    /* renamed from: m */
    public long sdTotalSize;

    /* renamed from: n */
    public long memoryInfo;

    /* renamed from: o */
    public long usedSpaceSize;

    /* renamed from: p */
    public long useSpace;

    /* renamed from: q */
    public String fullPath;

    /* renamed from: r */
    public String productBrand;

    /* renamed from: s */
    public String productManufacturer;

    /* renamed from: t */
    public String smartISanVersion;

    /* renamed from: u */
    public String versionCode;

    /* renamed from: v */
    public String versionName;

    /* renamed from: w */
    public String serialNumber;

    /* renamed from: x */
    public String externalStoragePath;

    /* renamed from: y */
    public String f3726y;

    /* synthetic */ DeviceInfo(DeviceInfoHelper deviceInfoHelper, byte b) {
        this();
    }

    public DeviceInfo() {
        this.deviceName = CommonUtils.getDeviceName();
        this.productName = CommonUtils.getProductName();
        this.housingColor = CommonUtils.getProp("ro.housing.color");
        this.deviceSpace = CommonUtils.getDeviceSpace();
        this.sdTotalSize = CommonUtils.getExtSdTotalSize();
        this.memoryInfo = CommonUtils.getMemoryInfo();
        this.usedSpaceSize = CommonUtils.usedSpaceSize();
        this.useSpace = CommonUtils.usedStorageSpace();
        this.fullPath = CommonUtils.getStorageFullPath();
        this.productBrand = CommonUtils.getProp("ro.product.brand");
        this.productManufacturer = CommonUtils.getProp("ro.product.manufacturer");
        this.smartISanVersion = CommonUtils.getProp("ro.smartisan.version");
        this.versionCode = CommonUtils.getVersionCode();
        this.versionName = CommonUtils.getVersionName();
        this.serialNumber = CommonUtils.getProp("ro.serialno");
        this.audioSize = -1L;
        this.pictureVideoSize = -1L;
        this.downloadSize = -1L;
        this.otherSize = -1L;
        this.appSize = -1L;
        this.cacheSize = -1L;
        new Thread(new RunnableC0773j(FolderApp.getInstance(),DeviceInfoHelper.getDeviceInfoHelper(),this)).start();
        setExternalStoragePath(CommonUtils.getStoragePath());
    }

    /* renamed from: a */
    public final void initOther() {
        this.otherSize = ((((this.usedSpaceSize - this.appSize) - this.audioSize) - this.pictureVideoSize) - this.downloadSize) - this.cacheSize;
    }

    /* renamed from: b */
    public final int getBatteryPercentage() {
        return this.level;
    }

    /* renamed from: c */
    public final String getDeviceName() {
        return this.deviceName;
    }

    /* renamed from: d */
    public final String getProductName() {
        return this.productName;
    }

    /* renamed from: e */
    public final String getHousingColor() {
        return this.housingColor;
    }

    /* renamed from: f */
    public final long getDeviceSpace() {
        return this.deviceSpace;
    }

    /* renamed from: g */
    public final long getSdTotalSize() {
        return this.sdTotalSize;
    }

    /* renamed from: h */
    public final long getMemoryInfo() {
        return this.memoryInfo;
    }

    /* renamed from: i */
    public final long getUsedSpaceSize() {
        return this.usedSpaceSize;
    }

    /* renamed from: j */
    public final long getUseSpace() {
        return this.useSpace;
    }

    /* renamed from: k */
    public final String getFullPath() {
        return this.fullPath;
    }

    /* renamed from: l */
    public final String getProductBrand() {
        return this.productBrand;
    }

    /* renamed from: m */
    public final String getProductManufacturer() {
        return this.productManufacturer;
    }

    /* renamed from: n */
    public final String getSmartISanVersion() {
        return this.smartISanVersion;
    }

    /* renamed from: o */
    public final String getVersionCode() {
        return this.versionCode;
    }

    /* renamed from: p */
    public final String getVersionName() {
        return this.versionName;
    }

    /* renamed from: q */
    public static double getBatteryCapacity() {
        Object obj;
        try {
            obj = Class.forName("com.android.internal.os.PowerProfile").getConstructor(Context.class).newInstance(FolderApp.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
            obj = null;
        }
        try {
            return ((Double) Class.forName("com.android.internal.os.PowerProfile").getMethod("getAveragePower", String.class).invoke(obj, "battery.capacity")).doubleValue();
        } catch (Exception e2) {
            e2.printStackTrace();
            return 0.0d;
        }
    }

    /* renamed from: r */
    public final String getUsbSerial() {
        return this.serialNumber;
    }

    /* renamed from: s */
    public final String getExternalStoragePath() {
        return this.externalStoragePath;
    }

    /* renamed from: a */
    public final void setExternalStoragePath(String externalStoragePath) {
        this.externalStoragePath = externalStoragePath;
        if (!TextUtils.isEmpty(externalStoragePath)) {
            this.f3726y = externalStoragePath;
        }
    }

    /* renamed from: b */
    public final boolean m448b(String str) {
        if (TextUtils.isEmpty(str) || !TextUtils.isEmpty(this.externalStoragePath) || TextUtils.isEmpty(this.f3726y)) {
            return false;
        }
        return str.startsWith(this.f3726y);
    }

    /* renamed from: c */
    public final boolean m444c(String str) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(this.externalStoragePath)) {
            return false;
        }
        return str.startsWith(this.externalStoragePath);
    }
}