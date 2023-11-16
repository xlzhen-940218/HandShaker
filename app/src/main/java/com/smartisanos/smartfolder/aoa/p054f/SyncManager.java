package com.smartisanos.smartfolder.aoa.p054f;

import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.aoa.p052d.MediaUtils;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.aoa.p056h.Md5Utils;
import com.smartisanos.smartfolder.aoa.p056h.MediaDataProvider;
import com.smartisanos.smartfolder.aoa.p056h.SharedPreferenceHelper;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.smartisanos.smartfolder.aoa.f.e */
/* loaded from: classes.dex */
public final class SyncManager {

    /* renamed from: a */
    private List<SmartSyncProtocolProtos.SSPFile> f3563a;

    /* renamed from: b */
    private List<SmartSyncProtocolProtos.SSPFile> f3564b;

    /* renamed from: c */
    private int f3565c;

    /* renamed from: d */
    private long f3566d;

    /* renamed from: e */
    private List<SmartSyncProtocolProtos.SSPFileChangeItem> f3567e;

    /* renamed from: f */
    private HandlerThread f3568f;

    /* renamed from: g */
    private Handler f3569g;

    /* renamed from: h */
    private PhoneStrategy phoneStrategy;

    /* renamed from: i */
    private ContentObserver f3571i;

    /* synthetic */ SyncManager(byte b) {
        this();
    }

    private SyncManager() {
        this.f3565c = 0;
        this.f3571i = new C0744f(this, new Handler(Looper.getMainLooper()));
        if (CommonUtils.m484r()) {
            this.phoneStrategy = new SmartisanPhoneStragety();
        } else {
            this.phoneStrategy = new DefaultPhoneStrategy();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SyncManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.f.e$b */
    /* loaded from: classes.dex */
    public static class SyncManagerInstance {

        /* renamed from: a */
        private static final SyncManager SYNC_MANAGER = new SyncManager((byte) 0);
    }

    /* renamed from: a */
    public static SyncManager getInstance() {
        return SyncManagerInstance.SYNC_MANAGER;
    }

    /* renamed from: b */
    public final boolean m651b() {
        return this.f3565c == 1;
    }

    /* renamed from: c */
    private List<SmartSyncProtocolProtos.SSPFile> m645c() {
        SmartSyncProtocolProtos.SSPFile d;
        Cursor cursor = null;
        ArrayList<Long> m714b = MediaUtils.m714b();
        ArrayList arrayList = new ArrayList();
        try {
            Uri mo665a = this.phoneStrategy.mo665a();
            String[] mo663b = this.phoneStrategy.mo663b();
            String mo662c = this.phoneStrategy.mo662c();
            String m715a = MediaUtils.m715a(m714b);
            String str = " _data LIKE '" + Environment.getExternalStorageDirectory() + "%' ";
            if (!TextUtils.isEmpty(m715a)) {
                str = str + " AND " + m715a;
            }
            if (!TextUtils.isEmpty(mo662c)) {
                str = str + " AND " + mo662c;
            }
            HandShaker.debug("SyncManager", "selection: " + str);
            Cursor cursor1 = MediaUtils.m718a(mo665a, mo663b, str, null, " date_added desc");
            if (cursor1 != null) {
                while (cursor1.moveToNext()) {
                    try {
                        String path = cursor1.getString(0) == null ? "" : cursor1.getString(0);
                        if (TextUtils.isEmpty(path)) {
                            d = null;
                        } else {
                            SmartSyncProtocolProtos.SSPFile.Builder builder = SmartSyncProtocolProtos.SSPFile.newBuilder();
                            builder.setPath(path);
                            builder.setFileSize(cursor1.getLong(2));
                            String extData = this.phoneStrategy.getExtData(cursor1);
                            if (extData != null) {
                                builder.setExtData(extData);
                            }
                            String builderPath = builder.getPath();
                            if (!TextUtils.isEmpty(builderPath)) {
                                File file = new File(builderPath);
                                if (file.exists() && !file.isDirectory()) {
                                    String str2 = file.getName().toLowerCase() + file.length() + checksum(file);
                                    HandShaker.debug("SyncManager", "getChecksum checksumValue: " + str2);
                                    builder.setChecksum(Md5Utils.md5(str2));
                                    builder.setFileSize(file.length());
                                }
                            }
                            d = builder.build();
                        }
                        if (d != null) {
                            arrayList.add(d);
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursor1;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }
            if (cursor1 != null) {
                cursor1.close();
            }
            return arrayList;
        } catch (Throwable th2) {

        }
        return arrayList;
    }

    /* renamed from: a */
    public final synchronized SmartSyncProtocolProtos.SSPPhotoSyncResponse m658a(SmartSyncProtocolProtos.SSPPhotoSyncRequest sspPhotoSyncRequest) {
        SmartSyncProtocolProtos.SSPPhotoSyncResponse d;
        synchronized (this) {
            SmartSyncProtocolProtos.SSPPhotoSyncResponse.Builder newBuilder = SmartSyncProtocolProtos.SSPPhotoSyncResponse.newBuilder();
            if (this.f3565c != 0) {
                HandShaker.debug("SyncManager", "startSync fail, status error. mSyncStatus: " + this.f3565c);
                newBuilder.setIsSuccess(false);
                d = newBuilder.build();
            } else {
                try {
                    HandShaker.debug("SyncManager", "startSync sync status: SYNC_STATUS_SYNCING");
                    this.f3565c = 1;
                    this.f3568f = new HandlerThread("SyncManager");
                    this.f3568f.start();
                    this.f3569g = new HandlerC0742a(this.f3568f.getLooper());
                    this.f3567e = new ArrayList();
                    String pcId = sspPhotoSyncRequest.getPcId();
                    newBuilder.setIsFirst(SharedPreferenceHelper.getBoolean(pcId) ? false : true);
                    List<SmartSyncProtocolProtos.SSPFile> m1128m = sspPhotoSyncRequest.getFilesList();
                    if (HandShaker.LOG) {
                        HandShaker.debug("SyncManager", "startSync file list from PC----------------------");
                        m635f(m1128m);
                        HandShaker.debug("SyncManager", "startSync file list from PC end----------------------");
                    }
                    this.f3563a = new ArrayList();
                    this.f3563a.addAll(m1128m);
                    this.f3564b = m645c();
                    newBuilder.addAllFiles(this.f3564b);
                    m642d();
                    SharedPreferenceHelper.setBooleanTrue(pcId);
                    newBuilder.setIsSuccess(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    m639e();
                    newBuilder.setIsSuccess(false);
                }
                d = newBuilder.build();
            }
        }
        return d;
    }

    /* renamed from: a */
    public final SmartSyncProtocolProtos.SSPSyncMonitorRequest m652a(boolean z) {
        SmartSyncProtocolProtos.SSPSyncMonitorRequest.Builder m1060l = SmartSyncProtocolProtos.SSPSyncMonitorRequest.newBuilder();
        if (this.f3565c != 0) {
            HandShaker.debug("SyncManager", "requestSyncMonitor isMonitor: " + z);
            this.f3569g.removeMessages(1);
            Message obtainMessage = this.f3569g.obtainMessage(2);
            obtainMessage.obj = Boolean.valueOf(z);
            this.f3569g.sendMessageDelayed(obtainMessage, 1500L);
            m1060l.setIsSyncMonitor(true);
        } else {
            HandShaker.debug("SyncManager", "requestSyncMonitor fail mSyncStatus: " + this.f3565c);
            m1060l.setIsSyncMonitor(false);
        }
        return m1060l.build();
    }

    /* renamed from: b */
    public final synchronized void m646b(boolean z) {
        HandShaker.debug("SyncManager", "doRequestSyncMonitor isMonitor: " + z);
        m634g();
        if (this.f3565c == 1) {
            for (SmartSyncProtocolProtos.SSPFile SSPFile : this.f3564b) {
                SmartSyncProtocolProtos.SSPFile m654a = m654a(SSPFile.getPath(), this.f3563a);
                if (m654a == null) {
                    if (HandShaker.LOG) {
                        HandShaker.debug("SyncManager", "synching over add to monitor:");
                        m638e(SSPFile);
                    }
                    this.f3563a.add(SSPFile);
                } else {
                    this.f3563a.remove(m654a);
                    this.f3563a.add(SSPFile);
                }
            }
        }
        if (z) {
            if (this.f3565c != 1) {
                HandShaker.debug("SyncManager", "doRequestSyncMonitor fail, status error. mSyncStatus: " + this.f3565c);
            } else {
                HandShaker.debug("SyncManager", "sync status: monitor");
                this.f3565c = 2;
                m643c(this.f3567e);
                this.f3567e.clear();
                this.f3567e = null;
                this.f3564b.clear();
                this.f3564b = null;
                MediaDataProvider.m401a().m390d();
            }
        } else if (this.f3565c == 0) {
            HandShaker.debug("SyncManager", "doRequestSyncMonitor fail, status error. mSyncStatus: " + this.f3565c);
        } else {
            if (this.f3565c == 1) {
                m643c(this.f3567e);
                this.f3567e.clear();
                this.f3567e = null;
            }
            HandShaker.debug("SyncManager", "sync status: SYNC_STATUS_NO");
            m639e();
            MediaDataProvider.m401a().m390d();
        }
    }

    /* renamed from: d */
    private synchronized void m642d() {
        EventManager.getInstance().m799a(EventManager.REQUEST_TYPE.PHOTO_SYNC);
        FolderApp.getInstance().getContentResolver().registerContentObserver(this.phoneStrategy.mo665a(), false, this.f3571i);
        HandShaker.debug("SyncManager", "startMonitor");
    }

    /* renamed from: e */
    private synchronized void m639e() {
        try {
            this.f3565c = 0;
            FolderApp.getInstance().getContentResolver().unregisterContentObserver(this.f3571i);
            EventManager.getInstance().m795b(EventManager.REQUEST_TYPE.PHOTO_SYNC);
            this.f3568f.quit();
            this.f3568f = null;
            this.f3569g = null;
            HandShaker.debug("SyncManager", "stopMonitor");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m636f() {
        if (this.f3569g != null) {
            this.f3569g.removeMessages(1);
            this.f3569g.sendEmptyMessageDelayed(1, 1500L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public synchronized void m634g() {
        long uptimeMillis = SystemClock.uptimeMillis();
        if (uptimeMillis - this.f3566d < 1000) {
            HandShaker.debug("SyncManager", "checkChange too frequently.");
        } else {
            HandShaker.debug("SyncManager", "start checkChange");
            this.f3566d = uptimeMillis;
            List<SmartSyncProtocolProtos.SSPFile> m645c = m645c();
            HandShaker.debug("SyncManager", "newPhotos size: " + m645c.size());
            if (this.f3565c == 2) {
                HandShaker.debug("SyncManager", "onFileChange");
                m643c(getFileChangeItems(m645c));
                this.f3563a = m645c;
            } else if (this.f3565c == 1) {
                m647b(m640d(m645c));
                HandShaker.debug("SyncManager", "mChangeItems.addAll");
            }
        }
    }

    /* renamed from: b */
    private void m647b(List<SmartSyncProtocolProtos.SSPFileChangeItem> list) {
        boolean z;
        for (SmartSyncProtocolProtos.SSPFileChangeItem sspFileChangeItem : list) {
            Iterator<SmartSyncProtocolProtos.SSPFileChangeItem> it = this.f3567e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                SmartSyncProtocolProtos.SSPFileChangeItem next = it.next();
                if (sspFileChangeItem.getFile().getPath().toLowerCase().equals(next.getFile().getPath().toLowerCase())
                        && sspFileChangeItem.getStatus() == next.getStatus()) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                this.f3567e.add(sspFileChangeItem);
            }
        }
    }

    /* renamed from: c */
    private static void m643c(List<SmartSyncProtocolProtos.SSPFileChangeItem> list) {
        if (list != null && !list.isEmpty()) {
            SmartSyncProtocolProtos.SSPFileChange d = SmartSyncProtocolProtos.SSPFileChange.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.FILE_CHANGE)
                    .addAllFileChangeItems(list)
                    .build();
            HandShaker.debug("SyncManager", "PHOTO_SYNC sent " + d.getType());
            for (SmartSyncProtocolProtos.SSPFileChangeItem sspFileChangeItem : list) {
                HandShaker.debug("SyncManager", "onFileChange: " + sspFileChangeItem.getStatus()
                        + ", " + sspFileChangeItem.getFile().getPath() 
                        + ", " + sspFileChangeItem.getFile().getChecksum() 
                        + ", " + sspFileChangeItem.getFile().getFileSize());
            }
            EventManager.getInstance().m798a(EventManager.REQUEST_TYPE.PHOTO_SYNC, d.toByteArray());
            HandShaker.debug("SyncManager", "PHOTO_SYNC sent success");
        }
    }

    /* renamed from: d */
    private synchronized List<SmartSyncProtocolProtos.SSPFileChangeItem> m640d(List<SmartSyncProtocolProtos.SSPFile> list) {
        ArrayList arrayList;
        ArrayList<SmartSyncProtocolProtos.SSPFile> arrayList2 = new ArrayList();
        ArrayList<SmartSyncProtocolProtos.SSPFile> arrayList3 = new ArrayList();
        arrayList = new ArrayList();
        arrayList2.addAll(this.f3564b);
        arrayList3.addAll(this.f3563a);
        HandShaker.debug("SyncManager", "----------------------diffForSyncing----------------------");
        HandShaker.debug("SyncManager", "----------------------syncingPhotos----------------------");
        m635f(arrayList2);
        HandShaker.debug("SyncManager", "----------------------monitorPhotos----------------------");
        m635f(arrayList3);
        HandShaker.debug("SyncManager", "----------------------newPhotos----------------------");
        m635f(list);
        HandShaker.debug("SyncManager", "----------------------diff----------------------");
        for (SmartSyncProtocolProtos.SSPFile SSPFile : list) {
            SmartSyncProtocolProtos.SSPFileChangeItem.Builder m1690n = SmartSyncProtocolProtos.SSPFileChangeItem.newBuilder();
            m1690n.setFile(SSPFile);
            SmartSyncProtocolProtos.SSPFile m654a = m654a(SSPFile.getPath(), arrayList2);
            SmartSyncProtocolProtos.SSPFile m654a2 = m654a(SSPFile.getPath(), arrayList3);
            if (m654a != null || m654a2 != null) {
                if (m654a2 != null) {
                    arrayList3.remove(m654a2);
                }
                if (m654a != null) {
                    arrayList2.remove(m654a);
                    if (!m659a(SSPFile, m654a)) {
                    }
                }
                if (m654a2 == null) {
                    m1690n.setStatus(SmartSyncProtocolProtos.SSPFileChangeStatus.Added);
                    arrayList.add(m1690n.build());
                    this.f3563a.add(SSPFile);
                } else if (!m659a(SSPFile, m654a2)) {
                    m1690n.setStatus(compareFileChange(SSPFile, m654a2));
                    arrayList.add(m1690n.build());
                    this.f3563a.set(this.f3563a.indexOf(m654a2), SSPFile);
                }
            } else {
                m1690n.setStatus(SmartSyncProtocolProtos.SSPFileChangeStatus.Added);
                arrayList.add(m1690n.build());
                this.f3563a.add(SSPFile);
            }
        }
        for (SmartSyncProtocolProtos.SSPFile SSPFile2 : arrayList2) {
            SmartSyncProtocolProtos.SSPFileChangeItem.Builder m1690n2 = SmartSyncProtocolProtos.SSPFileChangeItem.newBuilder();
            m1690n2.setFile(SSPFile2);
            m1690n2.setStatus(SmartSyncProtocolProtos.SSPFileChangeStatus.Deleted);
            arrayList.add(m1690n2.build());
        }
        for (SmartSyncProtocolProtos.SSPFile SSPFile3 : arrayList3) {
            SmartSyncProtocolProtos.SSPFileChangeItem.Builder m1690n3 = SmartSyncProtocolProtos.SSPFileChangeItem.newBuilder();
            m1690n3.setFile(SSPFile3);
            m1690n3.setStatus(SmartSyncProtocolProtos.SSPFileChangeStatus.Deleted);
            arrayList.add(m1690n3.build());
        }
        m633g(arrayList);
        HandShaker.debug("SyncManager", "----------------------diff end----------------------");
        return arrayList;
    }

    /* renamed from: a */
    private static boolean m659a(SmartSyncProtocolProtos.SSPFile SSPFile, SmartSyncProtocolProtos.SSPFile SSPFile2) {
        return TextUtils.equals(SSPFile.getChecksum(), SSPFile2.getChecksum()) && m655a(SSPFile.getExtData(), SSPFile2.getExtData());
    }

    /* renamed from: a */
    private static boolean m655a(String str, String str2) {
        if (CommonUtils.m484r()) {
            PhotoExtInfo m666a = PhotoExtInfo.createPhotoExtInfo((String) null, str);
            PhotoExtInfo m666a2 = PhotoExtInfo.createPhotoExtInfo((String) null, str2);
            if (m666a != null) {
                return m666a.equals(m666a2);
            }
            return m666a2 == null;
        }
        return true;
    }

    /* renamed from: b */
    private static SmartSyncProtocolProtos.SSPFileChangeStatus compareFileChange(SmartSyncProtocolProtos.SSPFile SSPFile
            , SmartSyncProtocolProtos.SSPFile SSPFile2) {
        boolean z = !TextUtils.equals(SSPFile.getChecksum(), SSPFile2.getChecksum());
        boolean z2 = m655a(SSPFile.getExtData(), SSPFile2.getExtData()) ? false : true;
        if (z && z2) {
            return SmartSyncProtocolProtos.SSPFileChangeStatus.FileAndInfoModified;
        }
        if (!z && z2) {
            return SmartSyncProtocolProtos.SSPFileChangeStatus.InfoModified;
        }
        return SmartSyncProtocolProtos.SSPFileChangeStatus.Modified;
    }

    /* renamed from: e */
    private synchronized List<SmartSyncProtocolProtos.SSPFileChangeItem> getFileChangeItems(List<SmartSyncProtocolProtos.SSPFile> list) {
        ArrayList arrayList;
        ArrayList<SmartSyncProtocolProtos.SSPFile> arrayList2 = new ArrayList();
        arrayList = new ArrayList();
        arrayList2.addAll(this.f3563a);
        HandShaker.debug("SyncManager", "----------------------diffForMonitor----------------------");
        HandShaker.debug("SyncManager", "----------------------oldPhotos----------------------");
        m635f(arrayList2);
        HandShaker.debug("SyncManager", "----------------------newPhotos----------------------");
        m635f(list);
        HandShaker.debug("SyncManager", "----------------------diff----------------------");
        for (SmartSyncProtocolProtos.SSPFile SSPFile : list) {
            SmartSyncProtocolProtos.SSPFileChangeItem.Builder m1690n = SmartSyncProtocolProtos.SSPFileChangeItem.newBuilder();
            m1690n.setFile(SSPFile);
            SmartSyncProtocolProtos.SSPFile m654a = m654a(SSPFile.getPath(), arrayList2);
            if (m654a != null) {
                arrayList2.remove(m654a);
                if (!m659a(SSPFile, m654a)) {
                    m1690n.setStatus(compareFileChange(SSPFile, m654a));
                    arrayList.add(m1690n.build());
                }
            } else {
                m1690n.setStatus(SmartSyncProtocolProtos.SSPFileChangeStatus.Added);
                arrayList.add(m1690n.build());
            }
        }
        for (SmartSyncProtocolProtos.SSPFile SSPFile2 : arrayList2) {
            SmartSyncProtocolProtos.SSPFileChangeItem.Builder m1690n2 = SmartSyncProtocolProtos.SSPFileChangeItem.newBuilder();
            m1690n2.setFile(SSPFile2);
            m1690n2.setStatus(SmartSyncProtocolProtos.SSPFileChangeStatus.Deleted);
            arrayList.add(m1690n2.build());
        }
        m633g(arrayList);
        HandShaker.debug("SyncManager", "----------------------diff end----------------------");
        return arrayList;
    }

    /* renamed from: a */
    public final synchronized boolean m660a(SmartSyncProtocolProtos.SSPFile SSPFile) {
        boolean z;
        switch (this.f3565c) {
            case 1:
                SmartSyncProtocolProtos.SSPFile m654a = m654a(SSPFile.getPath(), this.f3564b);
                if (m654a == null) {
                    z = false;
                    break;
                } else {
                    this.f3564b.remove(m654a);
                    if (m654a(SSPFile.getPath(), this.f3563a) == null) {
                        this.f3563a.add(m654a);
                    }
                }
            default:
                z = true;
                break;
        }
        return z;
    }

    /* renamed from: b */
    public final synchronized boolean m650b(SmartSyncProtocolProtos.SSPFile SSPFile) {
        boolean z;
        if (CommonUtils.m484r() && TextUtils.isEmpty(SSPFile.getExtData())) {
            PhotoExtInfo photoExtInfo = new PhotoExtInfo();
            SmartSyncProtocolProtos.SSPFile.Builder builder = SmartSyncProtocolProtos.SSPFile.newBuilder();
            builder.setPath(SSPFile.getPath());
            builder.setFileSize(SSPFile.getFileSize());
            builder.setChecksum(SSPFile.getChecksum());
            builder.setExtData(photoExtInfo.toString());
            SSPFile = builder.build();
        }
        m636f();
        HandShaker.debug("SyncManager", "addOrUpdate:");
        m638e(SSPFile);
        switch (this.f3565c) {
            case 1:
                SmartSyncProtocolProtos.SSPFile sspFile = m654a(SSPFile.getPath(), this.f3564b);
                if (sspFile != null) {
                    this.f3564b.remove(sspFile);
                }
            case 2:
                SmartSyncProtocolProtos.SSPFile m654a2 = m654a(SSPFile.getPath(), this.f3563a);
                if (m654a2 == null) {
                    HandShaker.debug("SyncManager", "addOrUpdate:add");
                    this.f3563a.add(SSPFile);
                } else {
                    HandShaker.debug("SyncManager", "addOrUpdate:update");
                    this.f3563a.set(this.f3563a.indexOf(m654a2), SSPFile);
                }
                z = true;
                break;
            default:
                z = false;
                break;
        }
        return z;
    }

    /* renamed from: d */
    private synchronized boolean m641d(SmartSyncProtocolProtos.SSPFile SSPFile) {
        boolean z = true;
        synchronized (this) {
            switch (this.f3565c) {
                case 1:
                    SmartSyncProtocolProtos.SSPFile m654a = m654a(SSPFile.getPath(), this.f3564b);
                    if (m654a != null) {
                        this.f3564b.remove(m654a);
                    }
                    SmartSyncProtocolProtos.SSPFile m654a2 = m654a(SSPFile.getPath(), this.f3563a);
                    if (m654a2 == null) {
                        this.f3563a.add(SSPFile);
                        break;
                    } else {
                        this.f3563a.set(this.f3563a.indexOf(m654a2), SSPFile);
                        break;
                    }
                case 2:
                    SmartSyncProtocolProtos.SSPFile m654a3 = m654a(SSPFile.getPath(), this.f3563a);
                    if (m654a3 != null) {
                        this.f3563a.set(this.f3563a.indexOf(m654a3), SSPFile);
                        break;
                    } else {
                        z = false;
                        break;
                    }
                default:
                    z = false;
                    break;
            }
        }
        return z;
    }

    /* renamed from: a */
    public final synchronized void m653a(List<SmartSyncProtocolProtos.SSPFile> list) {
        for (SmartSyncProtocolProtos.SSPFile SSPFile : list) {
            m641d(SSPFile);
        }
    }

    /* renamed from: c */
    public final synchronized boolean m644c(SmartSyncProtocolProtos.SSPFile SSPFile) {
        boolean z = true;
        synchronized (this) {
            switch (this.f3565c) {
                case 1:
                    SmartSyncProtocolProtos.SSPFile m654a = m654a(SSPFile.getPath(), this.f3564b);
                    if (m654a != null) {
                        this.f3564b.remove(m654a);
                    }
                    SmartSyncProtocolProtos.SSPFile m654a2 = m654a(SSPFile.getPath(), this.f3563a);
                    if (m654a2 == null) {
                        if (m654a == null) {
                            z = false;
                            break;
                        }
                    } else {
                        this.f3563a.remove(m654a2);
                        break;
                    }
                    break;
                case 2:
                    SmartSyncProtocolProtos.SSPFile m654a3 = m654a(SSPFile.getPath(), this.f3563a);
                    if (m654a3 != null) {
                        this.f3563a.remove(m654a3);
                        break;
                    } else {
                        z = false;
                        break;
                    }
                default:
                    z = false;
                    break;
            }
        }
        return z;
    }

    /* renamed from: a */
    private static SmartSyncProtocolProtos.SSPFile m654a(String str, List<SmartSyncProtocolProtos.SSPFile> list) {
        if (str == null) {
            return null;
        }
        String lowerCase = str.toLowerCase();
        for (SmartSyncProtocolProtos.SSPFile sspFile : list) {
            if (TextUtils.equals(lowerCase, sspFile.getPath().toLowerCase())) {
                return sspFile;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static String checksum(File file) {
        FileInputStream fileInputStream;
        Throwable th;
        String str = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (IOException e) {
            e = e;
            fileInputStream = null;
        } catch (Throwable th2) {
            fileInputStream = null;
            th = th2;
            CommonUtils.m509a(fileInputStream);
        }
        try {
            byte[] bArr = new byte[Long.valueOf(Math.min(100L, file.length())).intValue()];
            fileInputStream.read(bArr);
            str = Base64.encodeToString(bArr, 0).replaceAll("\n|\r", "");
            CommonUtils.m509a(fileInputStream);
        } catch (Throwable th3) {
            th = th3;
            CommonUtils.m509a(fileInputStream);
        }
        return str;
    }

    /* compiled from: SyncManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.f.e$a */
    /* loaded from: classes.dex */
    private class HandlerC0742a extends Handler {
        public HandlerC0742a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    SyncManager.this.m634g();
                    break;
                case 2:
                    SyncManager.this.m646b(((Boolean) message.obj).booleanValue());
                    break;
            }
            super.handleMessage(message);
        }
    }

    /* renamed from: f */
    private static void m635f(List<SmartSyncProtocolProtos.SSPFile> list) {
        for (SmartSyncProtocolProtos.SSPFile SSPFile : list) {
            m638e(SSPFile);
        }
    }

    /* renamed from: e */
    private static void m638e(SmartSyncProtocolProtos.SSPFile SSPFile) {
        HandShaker.debug("SyncManager", "[path:" + SSPFile.getPath() + ", checksum:" + SSPFile.getChecksum() + ", fileSize:" + SSPFile.getFileSize() + ", extData: " + SSPFile.getExtData() + "]");
    }

    /* renamed from: g */
    private static void m633g(List<SmartSyncProtocolProtos.SSPFileEvent> list) {
        for (SmartSyncProtocolProtos.SSPFileEvent SSPFileEvent : list) {
            HandShaker.debug("SyncManager", "[path:" + SSPFileEvent.getFile().getPath() + ", status:" + SSPFileEvent.getEvent() + "]");
        }
    }
}
