package com.smartisanos.smartfolder.aoa.p052d;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.StatFs;
import android.os.SystemClock;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import androidx.documentfile.provider.DocumentFile;
/*import android.support.p003v4.p007c.ArrayMap;
import android.support.p003v4.p007c.SimpleArrayMap;*/
import android.text.TextUtils;

import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.aoa.p049a.StorageObserverManager;
import com.smartisanos.smartfolder.aoa.p050b.SDCardRemovedEvent;
import com.smartisanos.smartfolder.aoa.p050b.UploadFileEvent;
import com.smartisanos.smartfolder.aoa.p054f.PhotoExtInfo;
import com.smartisanos.smartfolder.aoa.p054f.SyncManager;
import com.smartisanos.smartfolder.aoa.p055g.Connection;
import com.smartisanos.smartfolder.aoa.p055g.ConnectionManager;
import com.smartisanos.smartfolder.aoa.p055g.SSPEventDispatcher;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.aoa.p056h.DeviceInfoHelper;
import com.smartisanos.smartfolder.aoa.p056h.DocumentFileChangeObserver;
import com.smartisanos.smartfolder.aoa.p056h.FileUtils;
import com.smartisanos.smartfolder.aoa.p056h.StorageChangeObserver;
import com.smartisanos.smartfolder.aoa.p056h.StorageVolumeWrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.SyncFailedException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

/* renamed from: com.smartisanos.smartfolder.aoa.d.c */
/* loaded from: classes.dex */
public final class FileProcessor {

    /* renamed from: a */
    public static final Uri GALLERY_DELETE_FILES = Uri.parse("content://smartisanos_gallery/delete_files");

    /* renamed from: b */
    private final ConnectionManager connectionManager;

    /* renamed from: c */
    private final Context context;

    /* renamed from: d */
    private final StorageManager storageManager;

    /* renamed from: e */
    private final Connection connection;

    /* renamed from: f */
    private final Connection.C0748c f3498f;

    /* renamed from: g */
    private volatile AtomicInteger atomicInteger = new AtomicInteger(0);

    public FileProcessor(Context context, Connection connection) {
        this.context = context;
        this.connection = connection;
        this.f3498f = this.connection.m616h();
        this.connectionManager = connection.getConnectionManager();
        this.storageManager = (StorageManager) this.context.getSystemService(Context.STORAGE_SERVICE);
    }

    /* renamed from: a */
    public final SmartSyncProtocolProtos.SSPCreateFolderResponse m738a(SmartSyncProtocolProtos.SSPCreateFolderRequest SSPCreateFolderRequest) {
        String m1729l = SSPCreateFolderRequest.getFile().getPath();
        File file = new File(m1729l);
        SmartSyncProtocolProtos.SSPCreateFolderResponse.Builder builder = SmartSyncProtocolProtos.SSPCreateFolderResponse.newBuilder();
        builder.setType(SmartSyncProtocolProtos.SSPRequestType.GET_CREATE_FOLDER_REQUEST);
        builder.setFile(SSPCreateFolderRequest.getFile());
        if (m735a(file)) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_PATH_OR_NAME_TOO_LONG);
            builder.setSucceed(false);
            return builder.build();
        } else if (file.exists()) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_TARGET_ALREADY_EXIST);
            builder.setSucceed(false);
            return builder.build();
        } else if (DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().m448b(m1729l)) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SDCARD_REMOVED);
            builder.setSucceed(false);
            return builder.build();
        } else if (FileUtils.m414a(m1729l)) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SYSTEM_FILE);
            return builder.build();
        } else if (CommonUtils.m501c(m1729l)) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SDCARD_NO_PERMISSION);
            return builder.build();
        } else {
            C0729b m722f = m722f(file);
            if (m722f == null) {
                builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
                return builder.build();
            } else if (!m730b(m722f)) {
                m723e(file);
                builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_PERMISSION_ERROR);
                builder.setSucceed(false);
                return builder.build();
            } else {
                DocumentFile m728b = m728b(file.getParentFile());
                if (m728b == null) {
                    builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
                    builder.setSucceed(false);
                    return builder.build();
                }
                builder.setSucceed(m728b.findFile(file.getName()) != null);
                return builder.build();
            }
        }
    }

    /* renamed from: a */
    private static boolean m735a(File file) {
        int length = file.getName().getBytes().length;
        int length2 = file.getAbsolutePath().getBytes().length;
        if (length < 256 && length2 < 4096) {
            return false;
        }
        HandShaker.debug("File name/path is too long, nameLen:" + length + " pathLen:" + length2);
        return true;
    }

    /* renamed from: a */
    public final SmartSyncProtocolProtos.SSPRenameFileResponse m740a(SmartSyncProtocolProtos.SSPRenameFileRequest SSPRenameFileRequest) {
        String sourcePath = SSPRenameFileRequest.getSourceFile().getPath();
        String targetPath = SSPRenameFileRequest.getTargetFile().getPath();
        File sourceFile = new File(sourcePath);
        File targetFile = new File(targetPath);
        SmartSyncProtocolProtos.SSPRenameFileResponse.Builder builder = SmartSyncProtocolProtos.SSPRenameFileResponse.newBuilder();
        builder.setSourceFile(SSPRenameFileRequest.getSourceFile());
        builder.setTargetFile(SSPRenameFileRequest.getTargetFile());
        builder.setType(SmartSyncProtocolProtos.SSPRequestType.GET_RENAME_FILE_REQUEST);
        if (DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().m448b(sourcePath)) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SDCARD_REMOVED);
            builder.setSucceed(false);
            return builder.build();
        } else if (m735a(targetFile)) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_PATH_OR_NAME_TOO_LONG);
            builder.setSucceed(false);
            return builder.build();
        } else if (targetFile.exists()) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_TARGET_ALREADY_EXIST);
            return builder.build();
        } else if (FileUtils.m414a(targetPath)) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SYSTEM_FILE);
            return builder.build();
        } else if (CommonUtils.m501c(targetPath)) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SDCARD_NO_PERMISSION);
            return builder.build();
        } else {
            C0729b m722f = m722f(targetFile);
            if (m722f == null) {
                builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
                return builder.build();
            } else if (!m730b(m722f)) {
                m723e(targetFile);
                builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_PERMISSION_ERROR);
                builder.setSucceed(false);
                return builder.build();
            } else {
                DocumentFile m728b = m728b(sourceFile);
                if (!sourceFile.exists() || m728b == null) {
                    builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
                    return builder.build();
                }
                List<String> m724d = m724d(sourceFile);
                boolean renameTo = m728b.renameTo(targetFile.getName());
                List<String> m724d2 = m724d(targetFile);
                builder.setSucceed(renameTo);
                this.connectionManager.m602a(m724d);
                this.connectionManager.m602a(m724d2);
                return builder.build();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:150:0x02e5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final SmartSyncProtocolProtos.SSPDeleteFileResponse m745a(SmartSyncProtocolProtos.SSPDeleteFileRequest SSPDeleteFileRequest) {
        boolean z;
        boolean z2;
        ParcelFileDescriptor parcelFileDescriptor;
        Throwable th;
        Exception exc;
        SmartSyncProtocolProtos.SSPDeleteFileResponse.Builder responseBuilder = SmartSyncProtocolProtos.SSPDeleteFileResponse.newBuilder();
        responseBuilder.setType(SmartSyncProtocolProtos.SSPRequestType.GET_DELETE_FILE_REQUEST);
        int m1796m = SSPDeleteFileRequest.getFileCount();
        if (m1796m > 0) {
            HandShaker.debug("DeleteFiles", "File count from message: " + m1796m);
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList<SmartSyncProtocolProtos.SSPFile> arrayList4 = new ArrayList();
            boolean z3 = true;
            List<SmartSyncProtocolProtos.SSPFile> fileList = SSPDeleteFileRequest.getFileList();
            Iterator<SmartSyncProtocolProtos.SSPFile> it = fileList.iterator();
            int i = 0;
            while (true) {
                z = z3;
                if (!it.hasNext()) {
                    break;
                }
                SmartSyncProtocolProtos.SSPFile sspFile = it.next();
                String m1729l = sspFile.getPath();
                SmartSyncProtocolProtos.SSPFile.Builder builder = SmartSyncProtocolProtos.SSPFile.newBuilder();
                builder.setPath(m1729l);
                builder.setFileSize(sspFile.getFileSize());
                if (z) {
                    boolean isSync = SSPDeleteFileRequest.getIsSync();
                    String m1729l2 = sspFile.getPath();
                    if ((isSync || sspFile.getIsTrash()) && CommonUtils.isSmartisanPhone()) {
                        if (FolderApp.getInstance().getContentResolver().delete(GALLERY_DELETE_FILES, " LOWER(_data) = ? ", new String[]{m1729l2.toLowerCase()}) > 0) {
                            FolderApp.getInstance().sendBroadcast(new Intent("com.smartisanos.gallery.SYNC_DELETION"));
                        } else {
                            HandShaker.error("FileProcessor", "delete error with " + m1729l2);
                        }
                        builder.setSucceed(true);
                        z2 = false;
                    } else if (DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().m448b(m1729l2)) {
                        builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SDCARD_REMOVED);
                        builder.setSucceed(false);
                        z2 = false;
                    } else {
                        File file = new File(m1729l2);
                        if (!file.exists()) {
                            this.connectionManager.m603a(m1729l2);
                            builder.setSucceed(true);
                            z2 = false;
                        } else if (FileUtils.m414a(m1729l2)) {
                            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SYSTEM_FILE);
                            builder.setSucceed(false);
                            z2 = false;
                        } else if (CommonUtils.m501c(m1729l2)) {
                            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SDCARD_NO_PERMISSION);
                            builder.setSucceed(false);
                            z2 = false;
                        } else {
                            C0729b m722f = m722f(file);
                            if (m722f == null) {
                                builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
                                builder.setSucceed(false);
                                z2 = false;
                            } else if (!m730b(m722f)) {
                                m723e(file);
                                builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_PERMISSION_ERROR);
                                builder.setSucceed(false);
                                z2 = false;
                            } else if (m728b(file) == null) {
                                builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
                                builder.setSucceed(false);
                                z2 = false;
                            } else {
                                z2 = true;
                            }
                        }
                    }
                    if (z2) {
                        EventBus.getDefault().post(new UploadFileEvent());
                        DocumentFile documentFile = m728b(new File(m1729l));
                        if (documentFile == null) {
                            HandShaker.noLogInfo("DeleteFiles", "File " + m1729l + " is null");
                            z3 = false;
                        } else {
                            boolean mo3764c = documentFile.isDirectory();
                            z = documentFile.delete();
                            if (!z) {
                                builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_UNKNOW_ERROR);
                                HandShaker.noLogInfo("DeleteFiles", "Fail to delete file:" + m1729l);
                            } else if (!CommonUtils.m511a(this.context, documentFile.getUri())) {
                                long id = sspFile.getId();
                                if (id == 0) {
                                    long m733a = m733a(m1729l);
                                    if (m733a != 0) {
                                        arrayList.add(Long.valueOf(m733a));
                                    } else {
                                        arrayList2.add(m1729l);
                                    }
                                } else {
                                    arrayList.add(Long.valueOf(id));
                                }
                                if (mo3764c) {
                                    arrayList3.add(m1729l);
                                }
                            }
                            builder.setSucceed(z);
                            ParcelFileDescriptor parcelFileDescriptor2 = null;
                            try {
                                try {
                                    ParcelFileDescriptor openFileDescriptor = this.context.getContentResolver().openFileDescriptor(documentFile.getUri(), "r");
                                    if (openFileDescriptor != null) {
                                        try {
                                            try {
                                                openFileDescriptor.getFileDescriptor().sync();
                                            } catch (Throwable th2) {
                                                parcelFileDescriptor = null;
                                                th = th2;
                                                if (parcelFileDescriptor != null) {
                                                    try {
                                                        parcelFileDescriptor.close();
                                                    } catch (Exception e) {
                                                    }
                                                }
                                                throw th;
                                            }
                                        } catch (Exception e2) {
                                            parcelFileDescriptor = openFileDescriptor;
                                            exc = e2;
                                            try {
                                                HandShaker.debug("FileProcessor", "other exception " + exc.getMessage());
                                                if (parcelFileDescriptor != null) {
                                                    try {
                                                        parcelFileDescriptor.close();
                                                    } catch (Exception e3) {
                                                    }
                                                }
                                                z3 = z;
                                                arrayList4.add(builder.build());
                                                i = i;
                                            } catch (Throwable th3) {
                                                th = th3;
                                                if (parcelFileDescriptor != null) {
                                                }
                                                throw th;
                                            }
                                        }
                                    } else {
                                        HandShaker.debug("FileProcessor", "file not found and sync fail ");
                                    }
                                    if (openFileDescriptor != null) {
                                        try {
                                            openFileDescriptor.close();
                                        } catch (Exception e4) {
                                        }
                                    }
                                } catch (FileNotFoundException e5) {
                                    HandShaker.debug("FileProcessor", "Exception, file not found and sync fail ");
                                    if (0 != 0) {
                                        try {
                                            parcelFileDescriptor2.close();
                                        } catch (Exception e6) {
                                        }
                                    }
                                } catch (SyncFailedException e7) {
                                    HandShaker.debug("FileProcessor", "sync fail");
                                    if (0 != 0) {
                                        try {
                                            parcelFileDescriptor2.close();
                                        } catch (Exception e8) {
                                        }
                                    }
                                }
                            } catch (Exception e9) {
                                parcelFileDescriptor = null;
                                exc = e9;
                            } catch (Throwable th4) {
                                parcelFileDescriptor = null;
                                th = th4;
                                if (parcelFileDescriptor != null) {
                                }

                            }
                        }
                    } else if (builder.getSucceed()) {
                        i++;
                    } else {
                        z = false;
                    }
                } else {
                    builder.setSucceed(false);
                    builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_CANCEL_ACTION);
                }
                z3 = z;
                arrayList4.add(builder.build());
                i = i;
            }
            if (arrayList3.size() > 0) {
                int i2 = 0;
                while (true) {
                    int i3 = i2;
                    if (i3 >= arrayList3.size()) {
                        break;
                    }
                    ArrayList<Long> m727b = m727b((String) arrayList3.get(i3));
                    if (m727b != null && m727b.size() > 0) {
                        arrayList.addAll(m727b);
                    }
                    i2 = i3 + 1;
                }
            }
            int i4 = 0;
            if (arrayList.size() > 0) {
                if (arrayList.size() == 0) {
                    i4 = 0;
                } else {
                    i4 = this.context.getContentResolver().delete(MediaStore.Files.getContentUri("external"), m732a(arrayList), null);
                    HandShaker.debug("DeleteFiles", "To delete count:" + arrayList.size() + ", real " + i4 + " are deleted from Media DB");
                }
            }
            HandShaker.debug("DeleteFiles", "File count to be deleted by Id:" + arrayList.size() + ", not in DB:" + arrayList2.size() + ", other:" + i + ", delete from DB:" + i4);
            responseBuilder.setSucceed(z);
            responseBuilder.addAllFile(arrayList4);
            if (SSPDeleteFileRequest.getIsSync()) {
                for (SmartSyncProtocolProtos.SSPFile SSPFile : fileList) {
                    SyncManager.getInstance().m644c(SSPFile);
                }
            }
        }
        return (SmartSyncProtocolProtos.SSPDeleteFileResponse) responseBuilder.build();
    }

    /* renamed from: a */
    private static long m733a(String str) {
        long j = 0;
        Cursor cursor = null;
        if (!TextUtils.isEmpty(str)) {
            Uri contentUri = MediaStore.Files.getContentUri("external");
            String[] strArr = {"_id", "_data"};
            String str2 = "lower(_data)='" + str.toLowerCase() + "'";
            HandShaker.debug("DeleteFiles", "Query file ID, where:" + str2);
            try {
                try {
                    cursor = MediaUtils.m718a(contentUri, strArr, str2, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        j = cursor.getLong(0);
                    }
                } catch (Exception e) {
                    HandShaker.noLogInfo("DeleteFiles", "Query directory fail, exception:" + e.toString());
                    if (cursor != null) {
                        cursor.close();
                    }
                }
                HandShaker.debug("DeleteFiles", "Query file ID, id=" + j);
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
        return j;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00a6  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static ArrayList<Long> m727b(String str) {
        Cursor cursor;
        Throwable th;
        Exception e;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri contentUri = MediaStore.Files.getContentUri("external");
        String[] strArr = {"_id", "_data"};
        String str2 = "lower(_data) like '" + str.toLowerCase() + "%'";
        ArrayList<Long> arrayList = new ArrayList<>();
        try {
            cursor = MediaUtils.m718a(contentUri, strArr, str2, null, "_id asc");
            if (cursor != null) {
                try {
                    try {
                        if (cursor.moveToFirst()) {
                            do {
                                long j = cursor.getLong(0);
                                if (j != 0) {
                                    arrayList.add(Long.valueOf(j));
                                }
                            } while (cursor.moveToNext());
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    HandShaker.noLogInfo("DeleteFiles", "Query directory fail, exception:" + e.toString());
                    if (cursor != null) {
                        cursor.close();
                    }
                    HandShaker.debug("DeleteFiles", "Query directory end, id:" + arrayList.size());
                    return arrayList;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } catch (Exception e3) {
            cursor = null;
            e = e3;
        } catch (Throwable th3) {
            cursor = null;
            th = th3;
            if (cursor != null) {
            }

        }
        HandShaker.debug("DeleteFiles", "Query directory end, id:" + arrayList.size());
        return arrayList;
    }

    /* renamed from: a */
    private static String m732a(ArrayList<Long> arrayList) {
        String format;
        StringBuilder sb = new StringBuilder();
        sb.append("_id in ");
        for (int i = 0; i < arrayList.size(); i++) {
            if (i == 0) {
                format = String.format("(%d", arrayList.get(i));
            } else {
                format = String.format(", %d", arrayList.get(i));
            }
            sb.append(format);
            if (i == arrayList.size() - 1) {
                sb.append(")");
            }
        }
        HandShaker.debug("DeleteFiles", "Delete by id, where:" + sb.toString());
        return sb.toString();
    }

    /* renamed from: a */
    public final SmartSyncProtocolProtos.SSPUploadFileResponse m749a(int i, SmartSyncProtocolProtos.SSPUploadFileRequest sspUploadFileRequest) {
        DocumentFile mo3766a;
        SmartSyncProtocolProtos.SSPFile sspFile = sspUploadFileRequest.getFile();
        SmartSyncProtocolProtos.SSPUploadFileResponse.Builder m1005l = SmartSyncProtocolProtos.SSPUploadFileResponse.newBuilder();
        m1005l.setFile(sspUploadFileRequest.getFile());
        m1005l.setType(SmartSyncProtocolProtos.SSPRequestType.GET_UPLOAD_FILE_REQUEST_HEADER);
        m1005l.setCanceled(false);
        String m1729l = sspFile.getPath();
        File file = new File(m1729l);
        File parentFile = file.getParentFile();
        if (m735a(file)) {
            m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_PATH_OR_NAME_TOO_LONG);
            return m1005l.build();
        } else if (DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().m448b(m1729l)) {
            m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SDCARD_REMOVED);
            return m1005l.build();
        } else if (sspFile.getIsDirectory()) {
            m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
            return m1005l.build();
        } else if (FileUtils.m414a(m1729l)) {
            m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SYSTEM_FILE);
            return m1005l.build();
        } else if (CommonUtils.m501c(m1729l)) {
            m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SDCARD_NO_PERMISSION);
            return m1005l.build();
        } else {
            C0729b m722f = m722f(file);
            if (m722f == null) {
                m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
                return m1005l.build();
            } else if (!m730b(m722f)) {
                m723e(file);
                m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_PERMISSION_ERROR);
                return m1005l.build();
            } else {
                DocumentFile documentFile = m736a(m722f, parentFile);
                if (documentFile == null) {
                    m723e(file);
                    m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_UNKNOW_ERROR);
                    return m1005l.build();
                } else if (!documentFile.canWrite()) {
                    m723e(file);
                    m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_PERMISSION_ERROR);
                    return m1005l.build();
                } else {
                    String parent = file.getParent();
                    long m1728m = sspFile.getFileSize();
                    StatFs statFs = new StatFs(parent);
                    if (!(((long) statFs.getBlockSize()) * ((long) statFs.getAvailableBlocks()) > m1728m)) {
                        m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INSUFFICIENT_DISK_SPACE_ERROR);
                        return m1005l.build();
                    }
                    String name = file.getName();
                    DocumentFile m3783b = documentFile.findFile(name);
                    if (m3783b != null && !m3783b.delete()) {
                        mo3766a = null;
                    } else {
                        mo3766a = documentFile.createFile("vnd.android.document/root", name);
                    }
                    if (mo3766a == null) {
                        m1005l.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_UNKNOW_ERROR);
                        return m1005l.build();
                    }
                    m1005l.setCanceled(true);
                    C0730d c0730d = null;
                    if (sspUploadFileRequest.getIsSync()) {
                        c0730d = new C0730d(this, sspFile);
                    }
                    this.connection.m630a(i, m1729l, mo3766a, sspFile.getFileSize(), sspFile.getModifiedTimestamp(), c0730d);
                    return m1005l.build();
                }
            }
        }
    }

    @TargetApi(21)
    /* renamed from: a */
    private DocumentFile fromTreeUri(C0729b c0729b) {
        return DocumentFile.fromTreeUri(this.context
                , DocumentsContract.buildTreeDocumentUri("com.android.externalstorage.documents",
                        c0729b.f3503c));
    }

    /* renamed from: b */
    private boolean m730b(C0729b c0729b) {
        if (m725c(c0729b.f3501a)) {
            return true;
        }
        return fromTreeUri(c0729b).canWrite();
    }

    /* renamed from: a */
    private DocumentFile m736a(C0729b c0729b, File file) {
        boolean z;
        String str;
        int i = 0;
        File file2 = c0729b.f3501a;
        if (m725c(file2)) {
            if (file.exists() || file.mkdirs()) {
                return DocumentFile.fromFile(file);
            }
            return null;

        }
        String absolutePath = file.getAbsolutePath();
        String absolutePath2 = file2.getAbsolutePath();
        if (!absolutePath2.equals(absolutePath)) {
            str = absolutePath.substring(absolutePath2.length() + 1);
            z = false;
        } else {
            z = true;
            str = null;
        }
        DocumentFile m737a = fromTreeUri(c0729b);
        if (z) {
            return m737a;
        }
        String[] split = str.split(File.separator);
        DocumentFile documentFile = m737a;
        while (i < split.length) {
            String str2 = split[i];
            DocumentFile m3783b = documentFile.findFile(str2);
            if (m3783b == null && (m3783b = documentFile.findFile(str2)) == null) {
                return null;
            }
            i++;
            documentFile = m3783b;
        }
        return documentFile;
    }

    /* renamed from: a */
    public final SmartSyncProtocolProtos.SSPFileExistResponse m744a(SmartSyncProtocolProtos.SSPFileExistRequest SSPFileExistRequest) {
        DocumentFile documentFile;
        SmartSyncProtocolProtos.SSPFile sspFile = SSPFileExistRequest.getFile();
        File file = new File(sspFile.getPath());
        C0729b m722f = m722f(file);
        return SmartSyncProtocolProtos.SSPFileExistResponse.newBuilder()
                .setFile(sspFile)
                .setExist(m722f != null && m730b(m722f) && (documentFile = m728b(file)) != null && documentFile.exists())
                .setType(SmartSyncProtocolProtos.SSPRequestType.GET_FILE_EXIST_REQUEST).build();
    }

    /* renamed from: a */
    public final SmartSyncProtocolProtos.SSPGetFileCountResponse m742a(SmartSyncProtocolProtos.SSPGetFileCountRequest sspGetFileCountRequest) {
        int i;
        SmartSyncProtocolProtos.SSPFile sspFile = sspGetFileCountRequest.getDir();
        String path = sspFile.getPath();
        int m1476m = sspGetFileCountRequest.getMaxdepth();
        List<String> exclusionPatternList = sspGetFileCountRequest.getExclusionPatternList();
        File file = new File(path);
        if (!file.isAbsolute() || !file.isDirectory()) {
            i = 0;
        } else {
            i = m734a(file, m1476m, new C0728a(exclusionPatternList));
        }
        return SmartSyncProtocolProtos.SSPGetFileCountResponse.newBuilder()
                .setDir(sspFile)
                .setMaxdepth(sspGetFileCountRequest.getMaxdepth())
                .setCount(i)
                .setType(SmartSyncProtocolProtos.SSPRequestType.GET_FILE_COUNT_REQUEST).build();
    }

    /* renamed from: a */
    public static SmartSyncProtocolProtos.SSPGetDirFilesResponse getDirFilesResponse(SmartSyncProtocolProtos.SSPGetDirFilesRequest SSPGetDirFilesRequest) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        String m1729l = SSPGetDirFilesRequest.getDir().getPath();
        long m1498m = SSPGetDirFilesRequest.getMaxdepth();
        ArrayList arrayList = new ArrayList();
        m746a(DocumentFile.fromFile(new File(m1729l)), m1498m, arrayList);
        HandShaker.info("GetFiles", "count:" + arrayList.size() + " path:" + m1729l);
        ArrayList<SmartSyncProtocolProtos.SSPFile> arrayList2 = new ArrayList();
        if (!TextUtils.isEmpty(m1729l) && arrayList.size() > 0) {
            ArrayMap<String, Long> fileIdMaps = getFileIdMaps(arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                SmartSyncProtocolProtos.SSPFile.Builder builder = (SmartSyncProtocolProtos.SSPFile.Builder) it.next();
                long id = 0;
                String path = builder.getPath();
                if (fileIdMaps.containsKey(path)) {
                    id = fileIdMaps.get(path).longValue();
                }
                builder.setId(id);
                arrayList2.add(builder.build());
            }
        }
        return SmartSyncProtocolProtos.SSPGetDirFilesResponse.newBuilder()
                .setDir(SSPGetDirFilesRequest.getDir())
                .addAllFile(arrayList2)
                .setMaxdepth(SSPGetDirFilesRequest.getMaxdepth())
                .setTimecost(Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime).intValue())
                .setType(SmartSyncProtocolProtos.SSPRequestType.GET_DIR_FILES_REQUEST).build();
    }

    /* renamed from: a */
    public static SmartSyncProtocolProtos.SSPUpdateFileResponse getUpdateFileResponse(SmartSyncProtocolProtos.SSPUpdateFileRequest SSPUpdateFileRequest) {
        List<SmartSyncProtocolProtos.SSPFile> filesList = SSPUpdateFileRequest.getFilesList();
        HandShaker.debug("FileProcessor", "updateFile size:" + filesList.size());
        for (SmartSyncProtocolProtos.SSPFile sspFile : filesList) {
            PhotoExtInfo photoExtInfo = PhotoExtInfo.m667a(sspFile.getPath(), sspFile);
            String path = photoExtInfo.path;
            if (HandShaker.LOG) {
                HandShaker.debug("getContentValues: createTime: " + photoExtInfo.createTime);
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("date_added", Long.valueOf(photoExtInfo.createTime));
            if (!photoExtInfo.isExif) {
                contentValues.put("datetaken", Long.valueOf(photoExtInfo.createTime * 1000));
            }
            contentValues.put("orientation", Integer.valueOf(photoExtInfo.orientation));
            if (HandShaker.LOG) {
                HandShaker.debug("updatePhotoInfo value:" + contentValues);
            }
            int update = FolderApp.getInstance().getContentResolver().update(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues, " LOWER(_data) = LOWER(?) ", new String[]{path});
            if (HandShaker.LOG) {
                HandShaker.debug("updatePhotoInfo count: " + update);
            }
            if (CommonUtils.m484r()) {
                String str2 = photoExtInfo.path;
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("_data", photoExtInfo.path);
                contentValues2.put("star", Boolean.valueOf(photoExtInfo.star));
                contentValues2.put("orientation", Integer.valueOf(photoExtInfo.orientation));
                contentValues2.put("date_attribute_update", Long.valueOf(photoExtInfo.updateTime));
                int update2 = FolderApp.getInstance().getContentResolver().update(MediaUtils.f3508b, contentValues2, " LOWER(_data) = LOWER(?) ", new String[]{str2});
                if (HandShaker.LOG) {
                    HandShaker.debug("updateSmartisanPhotoInfo count: " + update2);
                }
            }
        }
        HandShaker.debug("FileProcessor", "updateFile over!");
        return SmartSyncProtocolProtos.SSPUpdateFileResponse.newBuilder().setIsSuccess(true)
                .setType(SmartSyncProtocolProtos.SSPRequestType.GET_DIR_FILES_REQUEST).build();
    }

    /* renamed from: a */
    public final SmartSyncProtocolProtos.SSPMonitorFolderRequest m741a(SmartSyncProtocolProtos.SSPMonitorFolderRequest sspMonitorFolderRequest) {
        StorageChangeObserver m542a;
        String path = sspMonitorFolderRequest.getFile().getPath();
        File file = new File(path);
        SmartSyncProtocolProtos.SSPMonitorFolderRequest.Builder m1141a = SmartSyncProtocolProtos.SSPMonitorFolderRequest.newBuilder()
                .setType(SmartSyncProtocolProtos.SSPRequestType.MONITOR_FOLDER_RESPONSE_HEADER);
        if (DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().m448b(path)) {
            m1141a.setRegister(false);
            return m1141a.build();
        } else if (FileUtils.m414a(path)) {
            m1141a.setRegister(false);
            return m1141a.build();
        } else if (CommonUtils.m501c(path)) {
            m1141a.setRegister(false);
            return m1141a.build();
        } else {
            C0729b m722f = m722f(file);
            if (m722f == null) {
                m1141a.setRegister(false);
                return m1141a.build();
            } else if (!m730b(m722f)) {
                m723e(file);
                m1141a.setRegister(false);
                return m1141a.build();
            } else {
                DocumentFile m728b = m728b(file);
                if (m728b == null) {
                    m1141a.setRegister(false);
                    return m1141a.build();
                } else if (!m728b.isDirectory()) {
                    m1141a.setRegister(false);
                    return m1141a.build();
                } else if (sspMonitorFolderRequest.getRegister()) {
                    if (CommonUtils.m511a(this.context, m728b.getUri())) {
                        Uri buildTreeDocumentUri = DocumentsContract.buildTreeDocumentUri("com.android.externalstorage.documents", m722f.f3503c);
                        String m729b = m729b(m722f, file);
                        m542a = new DocumentFileChangeObserver(path, DocumentsContract.buildChildDocumentsUriUsingTree(buildTreeDocumentUri, m729b), new Uri.Builder().scheme("content").authority("com.android.externalstorage.documents").appendPath("document").appendPath(m729b).appendPath("children").build());
                    } else {
                        m542a = StorageChangeObserver.m542a(path);
                    }
                    EventManager.getInstance().m799a(m542a.m539g());
                    StorageObserverManager.m792a().m791a(path);
                    StorageObserverManager.m792a().m790a(path, m542a);
                    m1141a.setRegister(true);
                    return m1141a.build();
                } else {
                    StorageChangeObserver m789b = StorageObserverManager.m792a().m789b(path);
                    StorageObserverManager.m792a().m791a(path);
                    if (m789b != null) {
                        EventManager.getInstance().m795b(m789b.m539g());
                    }
                    m1141a.setRegister(true);
                    return m1141a.build();
                }
            }
        }
    }

    /* renamed from: a */
    public final void m750a(int i, SmartSyncProtocolProtos.SSPDownloadFileRequest sspDownloadFileRequest) {
        boolean z;
        String m1729l = sspDownloadFileRequest.getFile().getPath();
        File file = new File(m1729l);
        long rangeOffset = sspDownloadFileRequest.getRange().getOffset();
        long rangeLength = sspDownloadFileRequest.getRange().getLength();
        long length = file.length();
        SmartSyncProtocolProtos.SSPDownloadFileResponseHeader.Builder builder
                = SmartSyncProtocolProtos.SSPDownloadFileResponseHeader.newBuilder()
                .setFile(SmartSyncProtocolProtos.SSPFile.newBuilder()
                        .setPath(file.getAbsolutePath())
                        .setFileSize(length)
                        .setModifiedTimestamp(file.lastModified() / 1000)
                        .setCreatedTimestamp(file.lastModified() / 1000)
                        .setIsDirectory(false).build())
                .setRange(sspDownloadFileRequest.getRange())
                .setDataMd5("");
        if (DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().m448b(m1729l)) {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_SDCARD_REMOVED);
            z = false;
        } else if (file.isFile()) {
            if ((length == 0 && rangeLength == 0) || (length > 0 && rangeOffset < length)) {
                long j = (rangeLength > length || rangeLength == 0) ? length : rangeLength;
                rangeLength = j > length - rangeOffset ? length - rangeOffset : j;
                builder.setRange(SmartSyncProtocolProtos.SSPDataRange.newBuilder()
                        .setOffset(rangeOffset).setLength(rangeLength).build());
                z = true;
            } else {
                builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
                z = false;
            }
        } else {
            builder.setErrorCode(SmartSyncProtocolProtos.SSPFileIOError.FILE_IO_INVALID_SOURCE);
            z = false;
        }
        builder.setReady(z);
        this.f3498f.m609a(i, builder.build().toByteArray());
        if (z) {
            SyncManager.getInstance().m660a(sspDownloadFileRequest.getFile());
            boolean m444c = DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().m444c(m1729l);
            if (m444c) {
                synchronized (this.atomicInteger) {
                    this.atomicInteger.incrementAndGet();
                    HandShaker.debug("FileProcessor", "downloadFile register sessionId = " + i);
                    if (!EventBus.getDefault().isRegistered(this)) {
                        EventBus.getDefault().register(this);
                    }
                }
            }
            try {
                this.f3498f.m611a(i, file, rangeOffset, rangeLength, m444c);
                HandShaker.debug("FileProcessor", "downloadFile unregister");
                if (m444c) {
                    synchronized (this.atomicInteger) {
                        if (this.atomicInteger.decrementAndGet() == 0) {
                            EventBus.getDefault().unregister(this);
                        }
                    }
                }
            } catch (Throwable th) {
                HandShaker.debug("FileProcessor", "downloadFile unregister");
                if (m444c) {
                    synchronized (this.atomicInteger) {
                        if (this.atomicInteger.decrementAndGet() == 0) {
                            EventBus.getDefault().unregister(this);
                        }
                    }
                }
                throw th;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public final void onMessageEvent(SDCardRemovedEvent sDCardRemovedEvent) {
        if (HandShaker.LOG) {
            HandShaker.debug("FileProcessor", "SDCardRemovedEvent");
        }
        EventBus.getDefault().unregister(this);
        this.f3498f.m607b();
    }

    /* renamed from: a */
    public final void m748a(int i, String str) {
        SmartSyncProtocolProtos.SSPCancelRequest.Builder m903a = SmartSyncProtocolProtos.SSPCancelRequest
                .newBuilder()
                .setSessionId(i)
                .setType(SmartSyncProtocolProtos.SSPRequestType.CANCEL_REQUEST);
        if (DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().m448b(str)) {
            m903a.setErrorCode(SmartSyncProtocolProtos.SSPCancelErrorCode.ERROR_CODE_SDCARD_REMOVED);
        } else {
            m903a.setErrorCode(SmartSyncProtocolProtos.SSPCancelErrorCode.ERROR_CODE_UNKNOWN);
        }
        HandShaker.debug("FileProcessor", "onIOException errorCode = " + m903a.getErrorCode());
        EventManager.getInstance();
        int m793d = EventManager.m793d();
        this.f3498f.m609a(m793d, m903a.build().toByteArray());
        HandShaker.debug("FileProcessor", "onIOException thisId = " + m793d + ", errorCode = "
                + m903a.getErrorCode() + ", " + m903a.getType() + ", sessionId = " + m903a.getSessionId());
    }

    /* renamed from: b */
    private DocumentFile m728b(File file) {
        if (m725c(file) || Build.VERSION.SDK_INT < 21) {
            return DocumentFile.fromFile(file);
        }
        C0729b m722f = m722f(file);
        if (m722f == null) {
            return null;
        }
        Uri buildTreeDocumentUri = DocumentsContract.buildTreeDocumentUri("com.android.externalstorage.documents", m722f.f3503c);
        return m747a(new Uri.Builder().scheme("content").authority(buildTreeDocumentUri.getAuthority()).appendPath("tree").appendPath(m722f.f3503c).appendPath("document").appendPath(m729b(m722f, file)).build());
    }

    @TargetApi(21)
    /* renamed from: a */
    private DocumentFile m747a(Uri uri) {
        try {
            Constructor<?> declaredConstructor = Class.forName("android.support.v4.b.e").getDeclaredConstructor(DocumentFile.class, Context.class, Uri.class);
            declaredConstructor.setAccessible(true);
            return (DocumentFile) declaredConstructor.newInstance(null, this.context, uri);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return null;
        } catch (InstantiationException e3) {
            e3.printStackTrace();
            return null;
        } catch (NoSuchMethodException e4) {
            e4.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    private static boolean m725c(File file) {
        if (file.getAbsolutePath().startsWith(Environment.getExternalStorageDirectory().getAbsolutePath())) {
            return true;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (!file.isDirectory()) {
            file = file.getParentFile();
        }
        while (true) {
            long j = 1 + currentTimeMillis;
            File file2 = new File(file, ".HandShake_test_write_permission_" + currentTimeMillis);
            if (!file2.exists()) {
                try {
                    file2.createNewFile();
                    file2.delete();
                    HandShaker.debug("FileProcessor", "already has write permission");
                    return true;
                } catch (IOException e) {
                    HandShaker.debug("FileProcessor", "NO write permission");
                    return false;
                }
            }
            currentTimeMillis = j;
        }
    }

    /* renamed from: d */
    private List<String> m724d(File file) {
        ArrayList arrayList = new ArrayList();
        if (file.exists()) {
            if (file.isDirectory()) {
                m731a(arrayList, file);
            } else {
                arrayList.add(file.getAbsolutePath());
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m731a(List<String> list, File file) {
        File[] listFiles;
        for (File file2 : file.listFiles()) {
            if (file2.isDirectory()) {
                m731a(list, file2);
            } else {
                list.add(file2.getAbsolutePath());
            }
        }
    }

    /* renamed from: e */
    private void m723e(File file) {
        C0729b m722f = m722f(file);
        if (m722f != null && !TextUtils.isEmpty(m722f.f3503c)) {
            SSPEventDispatcher.m596a().mo590a(file.getAbsolutePath(), m722f.f3503c);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileProcessor.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.c$a */
    /* loaded from: classes.dex */
    public static class C0728a implements FilenameFilter {

        /* renamed from: a */
        List<String> f3500a;

        C0728a(List<String> list) {
            this.f3500a = list;
        }

        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            for (String str2 : this.f3500a) {
                if (str.matches(str2)) {
                    return false;
                }
            }
            return true;
        }
    }

    /* renamed from: a */
    private int m734a(File file, int i, FilenameFilter filenameFilter) {
        File[] listFiles;
        int i2 = 0;
        if (i > 0 && (listFiles = file.listFiles(filenameFilter)) != null) {
            for (File file2 : listFiles) {
                if (file2.isDirectory()) {
                    i2 += m734a(file2, i - 1, filenameFilter);
                } else {
                    i2++;
                }
            }
        }
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c5  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static ArrayMap<String, Long> getFileIdMaps(ArrayList<SmartSyncProtocolProtos.SSPFile.Builder> arrayList) {
        Cursor cursor;
        int i = 0;
        Uri contentUri = MediaStore.Files.getContentUri("external");
        String[] strArr = {"_id", "_data"};
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i2 = i;
            if (i2 >= arrayList.size()) {
                break;
            }
            SmartSyncProtocolProtos.SSPFile.Builder c0503a = arrayList.get(i2);
            if (i2 == 0) {
                sb.append("_data in ('" + c0503a.getPath() + "'");
            } else {
                sb.append(", '" + c0503a.getPath() + "'");
            }
            if (i2 == arrayList.size() - 1) {
                sb.append(")");
            }
            i = i2 + 1;
        }
        HandShaker.info("GetFiles", "where:" + sb.toString());
        ArrayMap<String, Long> arrayMap = new ArrayMap<>();
        try {
            cursor = MediaUtils.m718a(contentUri, strArr, sb.toString(), null, "_id desc");
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndex("_id");
                        int columnIndex2 = cursor.getColumnIndex("_data");
                        do {
                            arrayMap.put(cursor.getString(columnIndex2), Long.valueOf(cursor.getLong(columnIndex)));
                        } while (cursor.moveToNext());
                        if (cursor != null) {
                            cursor.close();
                        }
                        HandShaker.info("GetFiles", "Cursor file count:" + arrayMap.size());
                        return arrayMap;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            HandShaker.noLogInfo("GetFiles", "cursor is null");
            if (cursor != null) {
            }
            HandShaker.info("GetFiles", "Cursor file count:" + arrayMap.size());
            return arrayMap;
        } catch (Throwable th2) {

            cursor = null;
        }
        return arrayMap;
    }

    /* renamed from: a */
    private static void m746a(DocumentFile documentFile, long j, ArrayList<SmartSyncProtocolProtos.SSPFile.Builder> arrayList) {
        DocumentFile[] mo3756j;
        if (j != 0 && documentFile.isDirectory() && (mo3756j = documentFile.listFiles()) != null) {
            for (DocumentFile documentFile2 : mo3756j) {
                SmartSyncProtocolProtos.SSPFile.Builder m1719v = SmartSyncProtocolProtos.SSPFile.newBuilder();
                boolean mo3762d = documentFile2.isFile();
                m1719v.setPath(documentFile2.getUri().getPath()).setFileSize(mo3762d ? documentFile2.length() : 0L)
                        .setModifiedTimestamp(documentFile2.lastModified() / 1000)
                        .setCreatedTimestamp(documentFile2.lastModified() / 1000).setIsDirectory(!mo3762d).build();
                arrayList.add(m1719v);
                if (documentFile2.isDirectory()) {
                    m746a(documentFile2, j - 1, arrayList);
                }
            }
        }
    }

    /* renamed from: b */
    private static String m729b(C0729b c0729b, File file) {
        String substring;
        String absolutePath = file.getAbsolutePath();
        if (c0729b == null) {
            HandShaker.info("FileProcessor", "can not found root : " + file.getAbsolutePath());
            return null;
        }
        String str = c0729b.f3502b;
        String absolutePath2 = c0729b.f3501a.getAbsolutePath();
        if (absolutePath2.equals(absolutePath)) {
            substring = "";
        } else if (absolutePath2.endsWith("/")) {
            substring = absolutePath.substring(absolutePath2.length());
        } else {
            substring = absolutePath.substring(absolutePath2.length() + 1);
        }
        return str + ':' + substring;
    }

    /* renamed from: f */
    private C0729b m722f(File file) {
        SimpleArrayMap simpleArrayMap;
        String m530b;
        String absolutePath = file.getAbsolutePath();
        try {
            SimpleArrayMap arrayMap = new ArrayMap();
            Object[] objArr = (Object[]) StorageManager.class.getMethod("getVolumeList", new Class[0]).invoke(this.storageManager, new Object[0]);
            HandShaker.debug("FileProcessor", "volumes size " + objArr.length);
            for (Object obj : objArr) {
                StorageVolumeWrap storageVolumeWrap = new StorageVolumeWrap(obj);
                if (!CommonUtils.isOnePlus() || storageVolumeWrap.isMounted()) {
                    if (storageVolumeWrap.isPrimary()) {
                        m530b = "primary";
                    } else {
                        m530b = storageVolumeWrap.getUUID();
                    }
                    if (!TextUtils.isEmpty(m530b) && !arrayMap.containsKey(m530b)) {
                        C0729b c0729b = new C0729b(this, (byte) 0);
                        arrayMap.put(m530b, c0729b);
                        c0729b.f3502b = m530b;
                        c0729b.f3501a = storageVolumeWrap.getPath();
                        c0729b.f3503c = m729b(c0729b, c0729b.f3501a);
                        HandShaker.debug("FileProcessor", "add root info " + c0729b);
                    }
                }
            }
            simpleArrayMap = arrayMap;
        } catch (Exception e) {
            e.printStackTrace();
            simpleArrayMap = null;
        }
        if (simpleArrayMap == null) {
            HandShaker.info("FileProcessor", "can not found root : " + file.getAbsolutePath());
            return null;
        }
        for (int i = 0; i < simpleArrayMap.size(); i++) {
            C0729b c0729b2 = (C0729b) simpleArrayMap.get(i);
            if (absolutePath.startsWith(c0729b2.f3501a.getAbsolutePath())) {
                return c0729b2;
            }
        }
        HandShaker.info("FileProcessor", "can not found root from mRoots : " + file.getAbsolutePath());
        if (HandShaker.LOG) {
            for (int i2 = 0; i2 < simpleArrayMap.size(); i2++) {
                HandShaker.debug("rootPath: " + ((C0729b) simpleArrayMap.get(i2)).f3501a.getAbsolutePath());
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: FileProcessor.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.c$b */
    /* loaded from: classes.dex */
    public class C0729b {

        /* renamed from: a */
        public File f3501a;

        /* renamed from: b */
        public String f3502b;

        /* renamed from: c */
        public String f3503c;

        private C0729b() {
        }

        /* synthetic */ C0729b(FileProcessor fileProcessor, byte b) {
            this();
        }

        public final String toString() {
            return "RootInfo: mPath = " + this.f3501a + ", rootId = " + this.f3502b + ", docId = " + this.f3503c;
        }
    }
}
