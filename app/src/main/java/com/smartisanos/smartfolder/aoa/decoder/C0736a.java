package com.smartisanos.smartfolder.aoa.decoder;

import android.content.ComponentName;
import android.content.Intent;

import com.google.protobuf.InvalidProtocolBufferException;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p050b.QuitEvent;
import com.smartisanos.smartfolder.aoa.p050b.WakeLockEvent;
import com.smartisanos.smartfolder.aoa.p052d.FileProcessor;
import com.smartisanos.smartfolder.aoa.p052d.MediaUtils;
import com.smartisanos.smartfolder.aoa.p054f.SyncManager;
import com.smartisanos.smartfolder.aoa.p055g.Connection;
import com.smartisanos.smartfolder.aoa.p055g.SSPEventDispatcher;
import com.smartisanos.smartfolder.aoa.p055g.Transfer;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.aoa.p056h.DeviceInfoHelper;
import com.smartisanos.smartfolder.aoa.service.UiDialogService;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: Decoder.java */
/* renamed from: com.smartisanos.smartfolder.aoa.decoder.a */
/* loaded from: classes.dex */
public final class C0736a {

    /* renamed from: a */
    private static String f3535a = "Decoder";

    /* renamed from: b */
    private final FileProcessor f3536b;

    /* renamed from: c */
    private Connection.C0748c f3537c;

    /* renamed from: d */
    private Transfer f3538d;

    /* renamed from: e */
    private Connection f3539e;

    /* renamed from: f */
    private boolean f3540f;

    /* renamed from: g */
    private boolean f3541g;

    /* renamed from: h */
    private C0737a f3542h;

    public C0736a(Connection connection) {
        this.f3539e = connection;
        this.f3537c = connection.m616h();
        this.f3538d = new Transfer(connection);
        this.f3536b = new FileProcessor(connection.getConnectionManager().m598f().getApplicationContext(), this.f3539e);
    }

    /* renamed from: a */
    public final void m684a(int i, byte b, byte[] bArr) throws InvalidProtocolBufferException {
        if (b == 0) {
            HandShaker.debug("decode HANDSHAKE_FLAG");
            if (this.f3539e.m618f() == Connection.EnumC0746a.USB && CommonUtils.isKeyguardLocked()) {
                HandShaker.debug(f3535a, "phone locked");
                this.f3542h = new C0737a(i, bArr);
                this.f3541g = true;
                EventBus.getDefault().register(this);
                this.f3537c.m609a(i, "locked".getBytes());
                return;
            }
            m682a(i, bArr);
        } else if (b == 1) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, 128);
            byte[] copyOfRange2 = Arrays.copyOfRange(bArr, 128, bArr.length);
            if (m681a(copyOfRange, copyOfRange2)) {
                try {
                    SmartSyncProtocolProtos.SSPHeartBeatRequest m1072a = SmartSyncProtocolProtos.SSPHeartBeatRequest
                            .parseFrom(copyOfRange2);
                    if (m1072a.getType() != SmartSyncProtocolProtos.SSPRequestType.HEART_BEAT_REQUEST) {
                        HandShaker.debug(f3535a, "session = " + i + ", requestType = " + m1072a.getType());
                    }
                    if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_DEVICE_INFO_REQUEST) {
                        SmartSyncProtocolProtos.SSPGetDeviceInfoRequest sspGetDeviceInfoRequest = SmartSyncProtocolProtos
                                .SSPGetDeviceInfoRequest.parseFrom(copyOfRange2);
                        this.f3538d.m568a(i, sspGetDeviceInfoRequest);
                        if (!this.f3540f) {
                            String m429p = DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().getVersionName();
                            String hostMinClientVersion = sspGetDeviceInfoRequest.getHostMinClientVersion();
                            HandShaker.debug(f3535a, "apk_update " + m429p + ", " + hostMinClientVersion);
                            this.f3540f = true;
                            if (CommonUtils.m507a(m429p, hostMinClientVersion) < 0) {
                                SSPEventDispatcher.m596a().mo589e();
                                EventBus.getDefault().post(new QuitEvent(false));
                                return;
                            }
                            int hostType = sspGetDeviceInfoRequest.getHostType();
                            String hostAppVersion = sspGetDeviceInfoRequest.getHostAppVersion();
                            SSPEventDispatcher.m596a().mo591a(hostType, sspGetDeviceInfoRequest.getHostAppVersionCode(), hostAppVersion);
                            return;
                        }
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.HEART_BEAT_REQUEST) {
                        this.f3538d.m566a(i, SmartSyncProtocolProtos.SSPHeartBeatRequest.parseFrom(copyOfRange2));
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_PHOTO_LIB_REQUEST) {
                        this.f3538d.m569a(i);
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_AUDIO_LIB_REQUEST) {
                        this.f3537c.m609a(i, MediaUtils.m721a().toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_VIDEO_LIB_REQUEST) {
                        this.f3538d.m560b(i);
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_THUMBNAIL_REQUEST) {
                        this.f3538d.m567a(i, SmartSyncProtocolProtos.SSPGetThumbnailRequest.parseFrom(copyOfRange2));
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_DIR_FILES_REQUEST) {
                        this.f3537c.m609a(i, FileProcessor.getDirFilesResponse(SmartSyncProtocolProtos.SSPGetDirFilesRequest.parseFrom(copyOfRange2)).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_FILE_COUNT_REQUEST) {
                        this.f3537c.m609a(i, this.f3536b.m742a(SmartSyncProtocolProtos.SSPGetFileCountRequest.parseFrom(copyOfRange2)).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_FILE_EXIST_REQUEST) {
                        this.f3537c.m609a(i, this.f3536b.m744a(SmartSyncProtocolProtos.SSPFileExistRequest.parseFrom(copyOfRange2)).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_CREATE_FOLDER_REQUEST) {
                        this.f3537c.m609a(i, this.f3536b.m738a(SmartSyncProtocolProtos.SSPCreateFolderRequest.parseFrom(copyOfRange2)).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_RENAME_FILE_REQUEST) {
                        this.f3537c.m609a(i, this.f3536b.m740a(SmartSyncProtocolProtos.SSPRenameFileRequest.parseFrom(copyOfRange2)).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_DELETE_FILE_REQUEST) {
                        this.f3537c.m609a(i, this.f3536b.m745a(SmartSyncProtocolProtos.SSPDeleteFileRequest.parseFrom(copyOfRange2)).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_DOWNLOAD_FILE_REQUEST) {
                        this.f3536b.m750a(i, SmartSyncProtocolProtos.SSPDownloadFileRequest.parseFrom(copyOfRange2));
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_UPLOAD_FILE_REQUEST_HEADER) {
                        this.f3537c.m609a(i, this.f3536b.m749a(i, SmartSyncProtocolProtos.SSPUploadFileRequest.parseFrom(copyOfRange2)).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.MONITOR_FOLDER_REQUEST) {
                        this.f3537c.m609a(i, this.f3536b.m741a(SmartSyncProtocolProtos.SSPMonitorFolderRequest.parseFrom(copyOfRange2)).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.GET_CLIPBOARD_REQUEST) {
                        SmartSyncProtocolProtos.SSPGetClipboardRequest.parseFrom(copyOfRange2);
                        this.f3538d.m559c(i);
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.CLEAR_CLIPBOARD_REQUEST) {
                        SmartSyncProtocolProtos.SSPClearClipboardRequest.parseFrom(copyOfRange2);
                        this.f3538d.m558d(i);
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.DELETE_CLIPBOARD_REQUEST) {
                        this.f3538d.m564a(i, SmartSyncProtocolProtos.SSPDeleteClipboardRequest.parseFrom(copyOfRange2));
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.POST_CLIPBOARD_REQUEST) {
                        this.f3538d.m565a(i, SmartSyncProtocolProtos.SSPPostClipboardRequest.parseFrom(copyOfRange2));
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.PHOTO_SYNC_REQUEST) {
                        this.f3537c.m609a(i, SyncManager.getInstance().m658a(SmartSyncProtocolProtos.SSPPhotoSyncRequest.parseFrom(copyOfRange2)).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.SYNC_MONITOR_REQUEST) {
                        this.f3537c.m609a(i, SyncManager.getInstance().m652a(SmartSyncProtocolProtos.SSPSyncMonitorRequest.parseFrom(copyOfRange2).getIsSyncMonitor()).toByteArray());
                        return;
                    } else if (m1072a.getType() == SmartSyncProtocolProtos.SSPRequestType.UPDATE_FILE_INFO) {
                        SmartSyncProtocolProtos.SSPUpdateFileRequest sspUpdateFileRequest = SmartSyncProtocolProtos.SSPUpdateFileRequest.parseFrom(copyOfRange2);
                        SmartSyncProtocolProtos.SSPUpdateFileResponse updateFileResponse = FileProcessor.getUpdateFileResponse(sspUpdateFileRequest);
                        HandShaker.debug(f3535a, "updateFileRequest isSync" + sspUpdateFileRequest.getIsSync()
                                + ", size: " + sspUpdateFileRequest.getFilesList().size());
                        if (sspUpdateFileRequest.getIsSync()) {
                            SyncManager.getInstance().m653a(sspUpdateFileRequest.getFilesList());
                        }
                        this.f3537c.m609a(i, updateFileResponse.toByteArray());
                        return;
                    } else {
                        return;
                    }
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                    return;
                }
            }
            this.f3537c.m609a(i, "rsa verify failed".getBytes());
        }
    }

    /* renamed from: a */
    private boolean m681a(byte[] bArr, byte[] bArr2) {
        Signature signature = null;
        try {
            signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(this.f3539e.m631a());
            signature.update(bArr2);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        } catch (SignatureException e3) {
            e3.printStackTrace();
        }
        try {
            return signature.verify(bArr);
        } catch (SignatureException e4) {
            e4.printStackTrace();
            return false;
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public final void onMessageEvent(WakeLockEvent wakeLockEvent) {
        if (HandShaker.LOG) {
            HandShaker.debug(f3535a, "onMessageEvent WakeLockEvent mIsWaitLock: " + this.f3541g);
        }
        if (this.f3541g) {
            try {
                m682a(this.f3542h.f3543a, this.f3542h.f3544b);
            } catch (Exception e) {
                e.printStackTrace();
                HandShaker.debug(f3535a, "onMessageEvent(WakeLockEvent event) exception: " + e);
            }
            m680b();
        }
    }

    /* renamed from: a */
    private void m682a(int i, byte[] bArr) throws InvalidProtocolBufferException {
        if (CommonUtils.isSmartisanPhone() && CommonUtils.getSmartISanVersion().compareTo("2.5.8") < 0) {
            ComponentName componentName = new ComponentName(FolderApp.getInstance(), UiDialogService.class);
            Intent intent = new Intent("ACTION_ALERT_SECURITY_SYSTEM_UPDATE");
            intent.setComponent(componentName);
            FolderApp.getInstance().startService(intent);
        }
        this.f3538d.m562a(i, bArr);
    }

    /* renamed from: a */
    public final void m685a() {
        m680b();
    }

    /* renamed from: b */
    private void m680b() {
        if (this.f3541g) {
            this.f3541g = false;
            this.f3542h = null;
            EventBus.getDefault().unregister(this);
        }
    }

    /* renamed from: a */
    public final void m683a(int i, String str) {
        this.f3536b.m748a(i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: Decoder.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.decoder.a$a */
    /* loaded from: classes.dex */
    public class C0737a {

        /* renamed from: a */
        int f3543a;

        /* renamed from: b */
        byte[] f3544b;

        public C0737a(int i, byte[] bArr) {
            this.f3543a = i;
            this.f3544b = bArr;
        }
    }
}
