package com.smartisanos.smartfolder.aoa.p049a;

import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.io.File;
import java.util.HashMap;

/* renamed from: com.smartisanos.smartfolder.aoa.a.b */
/* loaded from: classes.dex */
public class StorageObserverManager implements StorageChangeObserver.InterfaceC0758a {

    /* renamed from: a */
    private static final String f3421a = StorageObserverManager.class.getSimpleName();

    /* renamed from: c */
    private static StorageObserverManager f3422c;

    /* renamed from: b */
    private HashMap<String, StorageChangeObserver> f3423b = new HashMap<>();

    private StorageObserverManager() {
    }

    /* renamed from: a */
    public final void m790a(String str, StorageChangeObserver storageChangeObserver) {
        this.f3423b.put(str, storageChangeObserver);
        storageChangeObserver.m543a(this);
        storageChangeObserver.m541e();
    }

    /* renamed from: a */
    public final void m791a(String str) {
        if (this.f3423b.get(str) != null) {
            StorageChangeObserver remove = this.f3423b.remove(str);
            if (remove != null) {
                remove.m540f();
                return;
            } else {
                HandShaker.warn(f3421a, str + " not observered");
                return;
            }
        }
        HandShaker.warn(f3421a, str + " not observered");
    }

    /* renamed from: b */
    public final StorageChangeObserver m789b(String str) {
        return this.f3423b.get(str);
    }

    /* renamed from: a */
    public static StorageObserverManager m792a() {
        if (f3422c == null) {
            StorageObserverManager storageObserverManager = new StorageObserverManager();
            f3422c = storageObserverManager;
            return storageObserverManager;
        }
        return f3422c;
    }

    @Override // com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver.InterfaceC0758a
    /* renamed from: a */
    public final void mo538a(int i, String str, StorageChangeObserver storageChangeObserver) {
        SmartSyncProtocolProtos.SSPFileEventType sspFileEventType;
        EventManager.getInstance();
        if (!EventManager.m794c()) {
            HandShaker.warn(f3421a, "EventManager not Initialized");
            return;
        }
        HandShaker.warn(f3421a, "onStorageChange fileType : " + i + " : {" + str + "}");
        switch (i) {
            case 8:
                sspFileEventType = SmartSyncProtocolProtos.SSPFileEventType.FILE_EVENT_CLOSE_WRITE;
                break;
            case 64:
                sspFileEventType = SmartSyncProtocolProtos.SSPFileEventType.FILE_EVENT_MOVED_FROM;
                break;
            case 128:
                sspFileEventType = SmartSyncProtocolProtos.SSPFileEventType.FILE_EVENT_MOVED_TO;
                break;
            case 256:
                sspFileEventType = SmartSyncProtocolProtos.SSPFileEventType.FILE_EVENT_CREATE;
                break;
            case 512:
                sspFileEventType = SmartSyncProtocolProtos.SSPFileEventType.FILE_EVENT_DELETE;
                break;
            case 1024:
                sspFileEventType = SmartSyncProtocolProtos.SSPFileEventType.FILE_EVENT_DELETE_SELF;
                break;
            case 2048:
                sspFileEventType = SmartSyncProtocolProtos.SSPFileEventType.FILE_EVENT_MOVE_SELF;
                break;
            case 5632:
                sspFileEventType = SmartSyncProtocolProtos.SSPFileEventType.FILE_EVENT_DIR_CHANGED;
                break;
            default:
                sspFileEventType = null;
                break;
        }
        if (sspFileEventType != null) {
            File file = new File(str);
            boolean isDirectory = file.isDirectory();
            EventManager.getInstance().m798a(storageChangeObserver.m539g()
                    , SmartSyncProtocolProtos.SSPMonitorFolderResponse.newBuilder()
                            .setType(SmartSyncProtocolProtos.SSPRequestType.MONITOR_FOLDER_RESPONSE)
                            .addEvent(SmartSyncProtocolProtos.SSPFileEvent.newBuilder()
                                    .setFile(SmartSyncProtocolProtos.SSPFile.newBuilder().setPath(str)
                                            .setFileSize(file.length()).setModifiedTimestamp(file.lastModified() / 1000)
                                            .setIsDirectory(isDirectory).build())
                                    .setEvent(sspFileEventType)
                                    .build()).build().toByteArray());
        }
    }
}
