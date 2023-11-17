package com.smartisanos.smartfolder.aoa.p055g;

import android.content.SharedPreferences;
import android.util.Base64;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.aoa.p050b.TrustCancelEvent;
import com.smartisanos.smartfolder.aoa.p050b.TrustFinishEvent;
import com.smartisanos.smartfolder.aoa.p050b.TrustRequestEvent;
import com.smartisanos.smartfolder.aoa.p050b.TrustResponseEvent;
import com.smartisanos.smartfolder.aoa.p052d.ClipboardHandler;
import com.smartisanos.smartfolder.aoa.p052d.PhotoLibraryHandler;
import com.smartisanos.smartfolder.aoa.p052d.ThumbnailHandler;
import com.smartisanos.smartfolder.aoa.p052d.VideoLibraryHandler;
import com.smartisanos.smartfolder.aoa.p053e.HeartBeatChecker;
import com.smartisanos.smartfolder.aoa.p056h.ByteArrayToHex;
import com.smartisanos.smartfolder.aoa.p056h.DeviceInfo;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.aoa.p056h.DeviceInfoHelper;
import com.smartisanos.smartfolder.aoa.p056h.Pbkdf2PasswordHashUtil;

import java.security.SecureRandom;
import java.util.ArrayList;

import javax.crypto.Cipher;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

/* renamed from: com.smartisanos.smartfolder.aoa.g.j */
/* loaded from: classes.dex */
public class Transfer {

    /* renamed from: a */
    private static final String f3641a = Transfer.class.getSimpleName();

    /* renamed from: b */
    private Connection.C0748c f3642b;

    /* renamed from: c */
    private Connection connection;

    /* renamed from: d */
    private ConnectionManager connectionManager;

    public Transfer(Connection connection) {
        this.connection = connection;
        this.f3642b = connection.m616h();
        this.connectionManager = this.connection.getConnectionManager();
    }

    /* renamed from: a */
    private boolean m563a(int i, boolean z) {
        if (z) {
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(1, this.connection.m631a());
                byte[] doFinal = cipher.doFinal("ok".getBytes());
                HandShaker.info(f3641a, "writeKeyExchangeInfo====rsaResultStr===" + ByteArrayToHex.m517b(doFinal));
                this.f3642b.m609a(i, Base64.encodeToString(doFinal, 0).getBytes());
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                this.f3642b.m609a(i, "failed".getBytes());
                HandShaker.debug(f3641a, "writeKeyExchangeInfo exception: " + e);
                this.connection.m621c();
                return false;
            }
        }
        HandShaker.debug(f3641a, "writeKeyExchangeInfo flag false");
        this.f3642b.m609a(i, "failed".getBytes());
        this.connection.m621c();
        return false;
    }

    /* renamed from: a */
    public final void m562a(int i, byte[] bArr) throws InvalidProtocolBufferException {
        boolean z;
        HandShaker.info(f3641a, "writeKeyExchangeInfo, connection type: " + this.connection.m618f());
        if (this.connection.m618f() == Connection.EnumC0746a.USB) {
            HandShaker.info(f3641a, "writeKeyExchangeInfo===usb==serving===");
            //PublicKey m677a = KeyUtils.getInstance().getPublicKey(bArr);
            /*this.connection.m626a(m677a);
            if (m563a(i, m677a != null)) {
                HandShaker.info(f3641a, "writeKeyExchangeInfo=====send===success");
                this.f3644d.mo244a(this.connection);
                return;
            }*/
            return;
        }
        SmartSyncProtocolProtos.SSPHeartBeatRequest m1072a = SmartSyncProtocolProtos.SSPHeartBeatRequest.parseFrom(bArr);
        DeviceInfo m466d = DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo();
        HandShaker.info(f3641a, "writeKeyExchangeInfo==wifi===serving, requestType:" + m1072a.getType());
        if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.HANDSHAKE_REQUEST_01) {
            SmartSyncProtocolProtos.SSPHandShakeRequest01 sspHandShakeRequest01 = SmartSyncProtocolProtos.SSPHandShakeRequest01.parseFrom(bArr);
            this.connection.setHostName(sspHandShakeRequest01.getHostName());
            this.connection.setHeartbeatTimeoutSecond(sspHandShakeRequest01.getHeartbeatTimeoutSecond());
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPHandShakeResponse01.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.HANDSHAKE_RESPONSE_01)
                    .setApkVersion(m466d.getVersionCode())
                    .setApkVersionName(m466d.getVersionName())
                    .setClientTimestamp(System.currentTimeMillis() / 1000)
                    .setClientSmartSyncProtocolVersion("1")
                    .setClientMinHostVersion(CommonUtils.getMinHostVersion(1))
                    .setDeviceUuid(FolderApp.serialNumber)
                    .setDeviceName(m466d.getDeviceName())
                    .setUsbSerial(m466d.getUsbSerial())
                    .setIsSmartisanDevice(CommonUtils.isSmartisanPhone())
                    .setClientMinHostVersionCode(CommonUtils.getClientMinHostVersionCode(1))
                    .build()
                    .toByteArray());
            //this.connection.m626a(KeyUtils.getInstance().getPublicKey(sspHandShakeRequest01));
            HandShaker.debug(f3641a, "Reponse01 returned");
        } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.HANDSHAKE_REQUEST_02) {
            SmartSyncProtocolProtos.SSPHandShakeRequest02 sspHandShakeRequest02 = SmartSyncProtocolProtos.SSPHandShakeRequest02.parseFrom(bArr);
            if (sspHandShakeRequest02.getTrustType() == SmartSyncProtocolProtos.SSPHandShakeTrustType.TRUST_REMOVE) {
                FolderApp.getInstance().getSharedPreferences(sspHandShakeRequest02.getHostUuid(), 0).edit().clear().commit();
            }
            String hostUuid = sspHandShakeRequest02.getHostUuid();
            ByteString derivedKey = sspHandShakeRequest02.getDerivedKey();
            if (!derivedKey.isEmpty()) {
                if (FolderApp.getInstance().getSharedPreferences(hostUuid, 0).contains("hostUuid")) {
                    byte[] compareDerivedKey = Pbkdf2PasswordHashUtil.getDerivedKey(hostUuid.toCharArray()
                            , ByteArrayToHex.m518a(new String(FolderApp.getInstance().getSharedPreferences(hostUuid, 0)
                                    .getString("salt", "").getBytes()).toCharArray()));
                    if (derivedKey.isEmpty()) {
                        z = false;
                    } else {
                        z = derivedKey.equals(ByteString.copyFrom(compareDerivedKey));
                    }
                    if (z) {
                        this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPHandShakeResponse02.newBuilder()
                                .setType(SmartSyncProtocolProtos.SSPRequestType.HANDSHAKE_RESPONSE_02)
                                .setDeviceUuid(FolderApp.serialNumber)
                                .setTrustType(SmartSyncProtocolProtos.SSPHandShakeTrustType.TRUST_ALWAYS)
                                .setDeviceName(m466d.getDeviceName())
                                .setResult(getResult(true))
                                .setDerivedKey(derivedKey).build().toByteArray());
                        this.connectionManager.mo244a(this.connection);
                        return;
                    }
                    this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPHandShakeResponse02.newBuilder()
                            .setType(SmartSyncProtocolProtos.SSPRequestType.HANDSHAKE_RESPONSE_02)
                            .setTrustType(SmartSyncProtocolProtos.SSPHandShakeTrustType.TRUST_ALWAYS)
                            .setDeviceUuid(FolderApp.serialNumber)
                            .setDeviceName(m466d.getDeviceName())
                            .setResult(getResult(false))
                            .build().toByteArray());
                    return;
                }
            }
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPHandShakeResponse02.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.HANDSHAKE_RESPONSE_02)
                    .setTrustType(SmartSyncProtocolProtos.SSPHandShakeTrustType.TRUST_WAITING)
                    .setDeviceUuid(FolderApp.serialNumber)
                    .setDeviceName(m466d.getDeviceName())
                    .build().toByteArray());
            EventBus.getDefault().post(new TrustRequestEvent(this.connection, i, hostUuid));
            EventBus.getDefault().register(this);
            HandShaker.debug(f3641a, "Reponse02 request returned");
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(TrustCancelEvent trustCancelEvent) {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(TrustResponseEvent trustResponseEvent) {
        try {
            if (HandShaker.LOG) {
                HandShaker.debug(f3641a, "TrustResponseEvent");
            }
            EventBus.getDefault().unregister(this);
            String m762b = trustResponseEvent.m762b();
            int m763a = trustResponseEvent.m763a();
            SmartSyncProtocolProtos.SSPHandShakeTrustType m761c = trustResponseEvent.m761c();
            DeviceInfo m466d = DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo();
            byte[] bArr = new byte[256];
            new SecureRandom().nextBytes(bArr);
            byte[] m376a = Pbkdf2PasswordHashUtil.getDerivedKey(trustResponseEvent.m762b().toCharArray(), bArr);
            if (m761c == SmartSyncProtocolProtos.SSPHandShakeTrustType.TRUST_ALWAYS) {
                SharedPreferences.Editor edit = FolderApp.getInstance().getSharedPreferences(m762b, 0).edit();
                edit.putString("hostUuid", ByteArrayToHex.m520a(m762b.getBytes()));
                edit.putString("salt", ByteArrayToHex.m520a(bArr));
                edit.putString("derivedKey", ByteArrayToHex.m520a(m376a));
                edit.putString("trustType", m761c.toString());
                edit.commit();
            }
            if (m761c == SmartSyncProtocolProtos.SSPHandShakeTrustType.TRUST_ALWAYS) {
                this.f3642b.m609a(m763a, SmartSyncProtocolProtos.SSPHandShakeResponse02.newBuilder()
                        .setType(SmartSyncProtocolProtos.SSPRequestType.HANDSHAKE_RESPONSE_02)
                        .setTrustType(m761c)
                        .setDeviceUuid(FolderApp.serialNumber)
                        .setDeviceName(m466d.getDeviceName())
                        .setDerivedKey(ByteString.copyFrom(m376a))
                        .setResult(getResult(true))
                        .build()
                        .toByteArray());
                this.connectionManager.mo244a(this.connection);
            } else if (m761c == SmartSyncProtocolProtos.SSPHandShakeTrustType.TRUST_ONCE) {
                this.f3642b.m609a(m763a, SmartSyncProtocolProtos.SSPHandShakeResponse02.newBuilder()
                        .setType(SmartSyncProtocolProtos.SSPRequestType.HANDSHAKE_RESPONSE_02)
                        .setTrustType(m761c).setDeviceUuid(FolderApp.serialNumber)
                        .setDeviceName(m466d.getDeviceName())
                        .setResult(getResult(true)).build().toByteArray());
                this.connectionManager.mo244a(this.connection);
            } else {
                this.f3642b.m609a(m763a, SmartSyncProtocolProtos.SSPHandShakeResponse02.newBuilder()
                        .setType(SmartSyncProtocolProtos.SSPRequestType.HANDSHAKE_RESPONSE_02)
                        .setTrustType(m761c).setDeviceUuid(FolderApp.serialNumber)
                        .setDeviceName(m466d.getDeviceName())
                        .setResult(getResult(false)).build().toByteArray());
            }
        } finally {
            EventBus.getDefault().post(new TrustFinishEvent());
            HandShaker.debug(f3641a, "post TrustFinishEvent");
        }
    }

    /* renamed from: a */
    private String getResult(boolean z) {
        if (z) {
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                if (this.connection.m631a() == null) {
                    return "failed";
                }
                cipher.init(1, this.connection.m631a());
                return Base64.encodeToString(cipher.doFinal("ok".getBytes()), 0);
            } catch (Exception e) {
                e.printStackTrace();
                return "failed";
            }
        }
        return "failed";
    }

    /* renamed from: a */
    public final void m568a(int i, SmartSyncProtocolProtos.SSPGetDeviceInfoRequest SSPGetDeviceInfoRequest) {
        if (SSPGetDeviceInfoRequest.getNeedDeviceInfoCallback()) {
            DeviceInfoHelper.needDeviceInfo = SSPGetDeviceInfoRequest.getNeedDeviceInfoCallback();
            EventManager.getInstance().m799a(EventManager.REQUEST_TYPE.DEVICE_INFO);
        } else {
            EventManager.getInstance().m795b(EventManager.REQUEST_TYPE.DEVICE_INFO);
        }
        HandShaker.debug("deviceInfoObserverFlag " + DeviceInfoHelper.needDeviceInfo);
        if (SSPGetDeviceInfoRequest.getNeedPhotoLibraryCallback()) {
            DeviceInfoHelper.needImage = SSPGetDeviceInfoRequest.getNeedPhotoLibraryCallback();
            EventManager.getInstance().m799a(EventManager.REQUEST_TYPE.IMAGE);
        } else {
            EventManager.getInstance().m795b(EventManager.REQUEST_TYPE.IMAGE);
        }
        if (SSPGetDeviceInfoRequest.getNeedAudioLibraryCallback()) {
            DeviceInfoHelper.f3687d = SSPGetDeviceInfoRequest.getNeedAudioLibraryCallback();
            EventManager.getInstance().m799a(EventManager.REQUEST_TYPE.AUDIO);
        } else {
            EventManager.getInstance().m795b(EventManager.REQUEST_TYPE.AUDIO);
        }
        if (SSPGetDeviceInfoRequest.getNeedVideoLibraryCallback()) {
            DeviceInfoHelper.needVideo = true;
            EventManager.getInstance().m799a(EventManager.REQUEST_TYPE.VIDEO);
        } else {
            EventManager.getInstance().m795b(EventManager.REQUEST_TYPE.VIDEO);
        }
        DeviceInfo deviceInfo = DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo();
        if (this.connection.m618f() == Connection.EnumC0746a.USB) {
            SmartSyncProtocolProtos.SSPGetDeviceInfoResponse d = SmartSyncProtocolProtos.SSPGetDeviceInfoResponse
                    .newBuilder().
                    setHostSmartSyncProtocolVersion(SSPGetDeviceInfoRequest.getHostSmartSyncProtocolVersion())
                    .setHostTimestamp(SSPGetDeviceInfoRequest.getHostTimestamp())
                    .setApkVersion(deviceInfo.getVersionCode())
                    .setClientTimestamp(System.currentTimeMillis() / 1000)
                    .setClientSmartSyncProtocolVersion("1")
                    .setPhoneModel(deviceInfo.getProductName())
                    .setPhoneColor(deviceInfo.getHousingColor())
                    .setDiskSize(deviceInfo.getDeviceSpace())
                    .setExtDiskSize(deviceInfo.getSdTotalSize())
                    .setRamSize(deviceInfo.getMemoryInfo())
                    .setBatteryCapacity(DeviceInfo.getBatteryCapacity())
                    .setBatteryPercentage(deviceInfo.getBatteryPercentage())
                    .setPhoneName(deviceInfo.getDeviceName())
                    .setExtUsedDiskSize(deviceInfo.getUsedSpaceSize())
                    .setUsedDiskSize(deviceInfo.getUseSpace())
                    .setRootPath(deviceInfo.getFullPath())
                    .setProductBrand(deviceInfo.getProductBrand())
                    .setProductManufacturer(deviceInfo.getProductManufacturer())
                    .setSmartisanVersion(deviceInfo.getSmartISanVersion())
                    .setType(SmartSyncProtocolProtos.SSPRequestType.GET_DEVICE_INFO_REQUEST)
                    .setPhoneLocked(CommonUtils.isKeyguardLocked())
                    .setHostAppVersion(SSPGetDeviceInfoRequest.getHostAppVersion())
                    .setHostMinClientVersion(SSPGetDeviceInfoRequest.getHostMinClientVersion())
                    .setClientMinHostVersion(CommonUtils.getMinHostVersion(SSPGetDeviceInfoRequest.getHostType()))
                    .setApkVersionName(deviceInfo.getVersionName())
                    .setExternalStoragePath(deviceInfo.getExternalStoragePath())
                    .setPhoneId(FolderApp.serialNumber)
                    .setAudioSize(deviceInfo.audioSize)
                    .setPicVideoSize(deviceInfo.pictureVideoSize)
                    .setDownloadSize(deviceInfo.downloadSize)
                    .setAppSize(deviceInfo.appSize)
                    .setCacheSize(deviceInfo.cacheSize)
                    .setOtherSize(deviceInfo.otherSize)
                    .setDebugBuildTime(CommonUtils.getDebugBuildTime())
                    .setClientMinHostVersionCode(CommonUtils.getClientMinHostVersionCode(SSPGetDeviceInfoRequest.getHostType()))
                    .build();
            HandShaker.debug("音频:" + deviceInfo.audioSize);
            HandShaker.debug("图片视频: " + deviceInfo.pictureVideoSize);
            HandShaker.debug("下载: " + deviceInfo.downloadSize);
            HandShaker.debug("应用: " + deviceInfo.appSize);
            HandShaker.debug("缓存: " + deviceInfo.cacheSize);
            HandShaker.debug("其他: " + deviceInfo.otherSize);
            HandShaker.debug("总容量: " + d.getExtDiskSize());
            HandShaker.debug("可用容量: " + d.getExtUsedDiskSize());
            this.f3642b.m609a(i, d.toByteArray());
            HandShaker.debug("USB SSPGetDeviceInfoResponse send");
            return;
        }
        SmartSyncProtocolProtos.SSPGetDeviceInfoResponse d2 = SmartSyncProtocolProtos.SSPGetDeviceInfoResponse
                .newBuilder()
                .setHostSmartSyncProtocolVersion(SSPGetDeviceInfoRequest.getHostSmartSyncProtocolVersion())
                .setHostTimestamp(SSPGetDeviceInfoRequest.getHostTimestamp())
                .setApkVersion(deviceInfo.getVersionCode())
                .setClientTimestamp(System.currentTimeMillis() / 1000)
                .setClientSmartSyncProtocolVersion("1")
                .setPhoneModel(deviceInfo.getProductName())
                .setPhoneColor(deviceInfo.getHousingColor())
                .setDiskSize(deviceInfo.getDeviceSpace())
                .setExtDiskSize(deviceInfo.getSdTotalSize())
                .setRamSize(deviceInfo.getMemoryInfo())
                .setBatteryCapacity(DeviceInfo.getBatteryCapacity())
                .setBatteryPercentage(deviceInfo.getBatteryPercentage())
                .setPhoneName(deviceInfo.getDeviceName())
                .setExtUsedDiskSize(deviceInfo.getUsedSpaceSize())
                .setUsedDiskSize(deviceInfo.getUseSpace())
                .setRootPath(deviceInfo.getFullPath())
                .setProductBrand(deviceInfo.getProductBrand())
                .setProductManufacturer(deviceInfo.getProductManufacturer())
                .setSmartisanVersion(deviceInfo.getSmartISanVersion())
                .setType(SmartSyncProtocolProtos.SSPRequestType.GET_DEVICE_INFO_REQUEST)
                .setPhoneLocked(CommonUtils.isKeyguardLocked())
                .setHostAppVersion(SSPGetDeviceInfoRequest.getHostAppVersion())
                .setHostMinClientVersion(SSPGetDeviceInfoRequest.getHostMinClientVersion())
                .setClientMinHostVersion(CommonUtils.getMinHostVersion(SSPGetDeviceInfoRequest.getHostType()))
                .setApkVersionName(deviceInfo.getVersionName())
                .setExternalStoragePath(deviceInfo.getExternalStoragePath())
                .setPhoneId(FolderApp.serialNumber)
                .setAudioSize(deviceInfo.audioSize)
                .setPicVideoSize(deviceInfo.pictureVideoSize)
                .setDownloadSize(deviceInfo.downloadSize)
                .setAppSize(deviceInfo.appSize)
                .setCacheSize(deviceInfo.cacheSize)
                .setOtherSize(deviceInfo.otherSize)
                .setDebugBuildTime(CommonUtils.getDebugBuildTime())
                .setClientMinHostVersionCode(CommonUtils.getClientMinHostVersionCode(SSPGetDeviceInfoRequest.getHostType()))
                .build();
        HandShaker.debug("音频: " + deviceInfo.audioSize);
        HandShaker.debug("图片视频: " + deviceInfo.pictureVideoSize);
        HandShaker.debug("下载: " + deviceInfo.downloadSize);
        HandShaker.debug("应用: " + deviceInfo.appSize);
        HandShaker.debug("缓存: " + deviceInfo.cacheSize);
        HandShaker.debug("其他: " + deviceInfo.otherSize);
        HandShaker.debug("总容量: " + d2.getExtDiskSize());
        HandShaker.debug("可用容量: " + d2.getExtUsedDiskSize());
        this.f3642b.m609a(i, d2.toByteArray());
        HandShaker.debug("WIFI SSPGetDeviceInfoResponse send");
    }

    /* renamed from: a */
    public final void m569a(int i) {
        PhotoLibraryHandler.m705a();
        ArrayList<ArrayList> m704b = PhotoLibraryHandler.m704b();
        int m491k = CommonUtils.getCameraFolderHashCode();
        if (m704b.size() > 0) {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPGetPhotoLibraryResponse.newBuilder()
                    .addAllImage((Iterable<? extends SmartSyncProtocolProtos.SSPImageFile>) m704b.get(0))
                    .addAllAlbum(m704b.get(1))
                    .setCameraAlbumId(m491k)
                    .setType(SmartSyncProtocolProtos.SSPRequestType.GET_PHOTO_LIB_REQUEST)
                    .build().toByteArray());
        } else {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPGetPhotoLibraryResponse.newBuilder()
                    .setCameraAlbumId(m491k)
                    .setType(SmartSyncProtocolProtos.SSPRequestType.GET_PHOTO_LIB_REQUEST)
                    .build().toByteArray());
        }
    }

    /* renamed from: b */
    public final void m560b(int i) {
        VideoLibraryHandler.m687a();
        ArrayList<ArrayList> m686b = VideoLibraryHandler.m686b();
        if (m686b.size() > 0) {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPGetVideoLibraryResponse.newBuilder()
                    .addAllVideo((Iterable<? extends SmartSyncProtocolProtos.SSPVideoFile>) m686b.get(0))
                    .addAllAlbum(m686b.get(1)).setType(SmartSyncProtocolProtos.SSPRequestType.GET_VIDEO_LIB_REQUEST)
                    .build().toByteArray());
            return;
        }
        HandShaker.info(f3641a, "writeVideo result is null...");
        this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPGetVideoLibraryResponse.newBuilder()
                .setType(SmartSyncProtocolProtos.SSPRequestType.GET_VIDEO_LIB_REQUEST).build().toByteArray());
    }

    /* renamed from: a */
    public final void m567a(int i, SmartSyncProtocolProtos.SSPGetThumbnailRequest sspGetThumbnailRequest) {
        ArrayList<ArrayList> m702a = ThumbnailHandler.m703a().m702a(sspGetThumbnailRequest);
        if (m702a.size() > 0) {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPGetThumbnailResponse.newBuilder()
                    .addAllImage((Iterable<? extends SmartSyncProtocolProtos.SSPImageFile>) m702a.get(0))
                    .addAllVideo(m702a.get(1))
                    .addAllAudioAlbum(m702a.get(2))
                    .setType(SmartSyncProtocolProtos.SSPRequestType.GET_THUMBNAIL_REQUEST)
                    .build().toByteArray());
            return;
        }
        ThumbnailHandler.m703a().m701b();
        HandShaker.info(f3641a, "resultList is null.....");
    }

    /* renamed from: a */
    public final void m566a(int i, SmartSyncProtocolProtos.SSPHeartBeatRequest SSPHeartBeatRequest) {
        this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPHeartBeatResponse
                .newBuilder()
                .setHostTimestamp(SSPHeartBeatRequest.getHostTimestamp())
                .setClientTimestamp(HeartBeatChecker.getInstance().getClientTimestamp())
                .setType(SmartSyncProtocolProtos.SSPRequestType.HEART_BEAT_REQUEST)
                .build().toByteArray());
    }

    /* renamed from: c */
    public final void m559c(int i) {
        ArrayList<SmartSyncProtocolProtos.SSPClipboard> m756b = ClipboardHandler.getInstance().m756b();
        m756b.size();
        this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPGetClipboardResponse.newBuilder()
                .setType(SmartSyncProtocolProtos.SSPRequestType.GET_CLIPBOARD_REQUEST)
                .addAllClipboard(m756b)
                .build().toByteArray());
    }

    /* renamed from: d */
    public final void m558d(int i) {
        ArrayList<Boolean> clearClipboardList = ClipboardHandler.getInstance().clearClipboard();
        if (clearClipboardList.size() > 0) {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPClearClipboardResponse.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.CLEAR_CLIPBOARD_REQUEST)
                    .setSucceed(clearClipboardList.get(0).booleanValue()).build().toByteArray());
        } else {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPClearClipboardResponse.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.CLEAR_CLIPBOARD_REQUEST)
                    .setSucceed(false).build().toByteArray());
        }
    }

    /* renamed from: a */
    public final void m564a(int i, SmartSyncProtocolProtos.SSPDeleteClipboardRequest c0688x) {
        ArrayList<Boolean> m757a = ClipboardHandler.getInstance().m757a(c0688x);
        if (m757a.size() > 0) {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPDeleteClipboardResponse
                    .newBuilder().setType(SmartSyncProtocolProtos.SSPRequestType.DELETE_CLIPBOARD_REQUEST)
                    .setSucceed(m757a.get(0).booleanValue()).build().toByteArray());
        } else {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPDeleteClipboardResponse.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.DELETE_CLIPBOARD_REQUEST)
                    .setSucceed(false).build().toByteArray());
        }
    }

    /* renamed from: a */
    public final void m565a(int i, SmartSyncProtocolProtos.SSPPostClipboardRequest c0611dd) {
        ArrayList<Boolean> m755b = ClipboardHandler.getInstance().m755b(c0611dd);
        if (m755b.size() > 0) {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPPostClipboardResponse.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.POST_CLIPBOARD_REQUEST)
                    .setSucceed(m755b.get(0).booleanValue()).build().toByteArray());
        } else {
            this.f3642b.m609a(i, SmartSyncProtocolProtos.SSPPostClipboardResponse.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.POST_CLIPBOARD_REQUEST)
                    .setSucceed(false).build().toByteArray());
        }
    }
}
