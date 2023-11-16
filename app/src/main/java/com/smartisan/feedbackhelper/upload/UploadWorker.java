package com.smartisan.feedbackhelper.upload;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;
import com.smartisan.feedbackhelper.utils.C0462r;
import com.smartisan.feedbackhelper.utils.ComplainReport;
import com.smartisan.feedbackhelper.utils.DeviceID;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* renamed from: com.smartisan.feedbackhelper.upload.k */
/* loaded from: classes.dex */
public final class UploadWorker {

    /* renamed from: a */
    public static String f2484a;

    /* renamed from: b */
    public ReportSender f2485b;

    /* renamed from: c */
    private ReliableUploader f2486c;

    /* renamed from: d */
    private ComplainReport f2487d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UploadWorker.java */
    /* renamed from: com.smartisan.feedbackhelper.upload.k$a */
    /* loaded from: classes.dex */
    public enum EnumC0447a {
        PREPARE,
        COMPRESS,
        CHANGE_STATE,
        REMOVE_FILES,
        CHANGE_LOGPATH,
        START_TO_UPLOAD
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* compiled from: UploadWorker.java */
    /* renamed from: com.smartisan.feedbackhelper.upload.k$b */
    /* loaded from: classes.dex */
    public static final class EnumC0448b {

        /* renamed from: a */
        public static final int f2495a = 1;

        /* renamed from: b */
        public static final int f2496b = 2;

        /* renamed from: c */
        public static final int f2497c = 3;

        /* renamed from: d */
        public static final int f2498d = 4;

        /* renamed from: e */
        private static final /* synthetic */ int[] f2499e = {f2495a, f2496b, f2497c, f2498d};
    }

    public UploadWorker(ReliableUploader reliableUploader) {
        this.f2486c = reliableUploader;
        this.f2485b = new ReportSender(reliableUploader, this);
    }

    /* renamed from: a */
    public final void m2106a(ComplainReport complainReport) {
        if (complainReport == null) {
            throw new IllegalArgumentException("Upload can not be null.");
        }
        Log.i("BugReportUploadWorker", "startUpload " + complainReport);
        this.f2487d = complainReport;
        new C0449l(this).start();
    }

    /* renamed from: a */
    private void m2104a(File file, ZipOutputStream zipOutputStream, int i, byte[] bArr) {
        BufferedInputStream bufferedInputStream;
        if (file.isDirectory()) {
            if (i >= 20) {
                Log.e("BugReportUploadWorker", "Max nest level of 20 reached at " + file.getAbsolutePath() + "; aborting branch");
                return;
            }
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (File file2 : listFiles) {
                    m2104a(file2, zipOutputStream, i + 1, bArr);
                }
                return;
            }
            return;
        }
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file), bArr.length);
            try {
                ZipEntry zipEntry = new ZipEntry(file.getName());
                if (m2105a(file)) {
                    zipOutputStream.setLevel(0);
                } else {
                    zipOutputStream.setLevel(-1);
                }
                zipOutputStream.putNextEntry(zipEntry);
                while (true) {
                    int read = bufferedInputStream.read(bArr, 0, bArr.length);
                    if (read != -1) {
                        zipOutputStream.write(bArr, 0, read);
                    } else {
                        zipOutputStream.closeEntry();
                        try {
                            bufferedInputStream.close();
                            return;
                        } catch (IOException e) {
                            return;
                        }
                    }
                }
            } catch (Throwable th) {
                th = th;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e2) {
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {

            bufferedInputStream = null;
        }
    }

    /* renamed from: a */
    private static boolean m2105a(File file) {
        String name = file.getName();
        String substring = name.substring(name.lastIndexOf(46) + 1, name.length());
        String[] strArr = {"gz", "zip", "rar", "7z", "tgz", "png"};
        for (int i = 0; i < 6; i++) {
            if (strArr[i].equals(substring)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    @SuppressLint("LongLogTag")
    public final void m2108a(int i) {
        if (i == EnumC0448b.f2498d) {
            this.f2487d.m2102a(ComplainReport.EnumC0451a.READY_TO_COMPLETE);
        } else {
            this.f2487d.m2102a(ComplainReport.EnumC0451a.READY_TO_TRANSMIT);
        }
        if (ComplainReport.EnumC0451a.READY_TO_COMPLETE == this.f2487d.m2096c()) {
            this.f2487d.m2102a(ComplainReport.EnumC0451a.READY_TO_ARCHIVE);
            this.f2487d.m2102a(ComplainReport.EnumC0451a.ARCHIVED_FULL);
        }
        C0462r.m2015a(new String[]{this.f2487d.m2098b(), this.f2487d.m2082j()});
        ReliableUploader reliableUploader = this.f2486c;
        Log.v("BugReportReliableUploader", "onUploadFinished : " + this.f2487d);
        if (EnumC0448b.f2497c == i) {
            reliableUploader.sendBroadcast(new Intent("smartisan.intent.action.BUGREPORT.REPORT_UPLOAD_FAILED"));
            new C0438a(reliableUploader).start();
            return;
        }
        reliableUploader.sendBroadcast(new Intent("smartisan.intent.action.BUGREPORT.REPORT_UPLOAD_SUCCESS"));
        new C0439b(reliableUploader).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00f2 A[Catch: all -> 0x014f, TRY_ENTER, TryCatch #1 {IOException -> 0x0115, blocks: (B:3:0x0018, B:5:0x002b, B:6:0x002e, B:8:0x004d, B:9:0x004f, B:10:0x0092, B:19:0x00c6, B:20:0x00cd, B:41:0x0141, B:25:0x00f1, B:30:0x0101, B:31:0x0108, B:33:0x010f, B:48:0x0152, B:50:0x0179, B:51:0x01a8, B:53:0x01b2, B:54:0x01b9, B:18:0x00ae, B:12:0x0096, B:14:0x009c, B:15:0x00a0, B:17:0x00aa, B:26:0x00f2, B:28:0x00fc, B:29:0x0100, B:42:0x0142), top: B:63:0x0018 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x010f A[Catch: IOException -> 0x0115, TRY_LEAVE, TryCatch #1 {IOException -> 0x0115, blocks: (B:3:0x0018, B:5:0x002b, B:6:0x002e, B:8:0x004d, B:9:0x004f, B:10:0x0092, B:19:0x00c6, B:20:0x00cd, B:41:0x0141, B:25:0x00f1, B:30:0x0101, B:31:0x0108, B:33:0x010f, B:48:0x0152, B:50:0x0179, B:51:0x01a8, B:53:0x01b2, B:54:0x01b9, B:18:0x00ae, B:12:0x0096, B:14:0x009c, B:15:0x00a0, B:17:0x00aa, B:26:0x00f2, B:28:0x00fc, B:29:0x0100, B:42:0x0142), top: B:63:0x0018 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0152 A[Catch: IOException -> 0x0115, TryCatch #1 {IOException -> 0x0115, blocks: (B:3:0x0018, B:5:0x002b, B:6:0x002e, B:8:0x004d, B:9:0x004f, B:10:0x0092, B:19:0x00c6, B:20:0x00cd, B:41:0x0141, B:25:0x00f1, B:30:0x0101, B:31:0x0108, B:33:0x010f, B:48:0x0152, B:50:0x0179, B:51:0x01a8, B:53:0x01b2, B:54:0x01b9, B:18:0x00ae, B:12:0x0096, B:14:0x009c, B:15:0x00a0, B:17:0x00aa, B:26:0x00f2, B:28:0x00fc, B:29:0x0100, B:42:0x0142), top: B:63:0x0018 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ void m2107a(UploadWorker uploadWorker) {
        EnumC0447a enumC0447a;
        File parentFile = null;
        Throwable th;
        ZipOutputStream zipOutputStream;
        boolean z = false;
        Log.d("BugReportUploadWorker", "compressLogs : " + uploadWorker.f2487d);
        try {
            EnumC0447a enumC0447a2 = EnumC0447a.PREPARE;
            File file = new File(uploadWorker.f2487d.m2098b());
            if (!file.exists()) {
                enumC0447a = EnumC0447a.CHANGE_LOGPATH;
            } else if (file.isFile()) {
                enumC0447a = EnumC0447a.START_TO_UPLOAD;
            } else {
                enumC0447a = uploadWorker.f2487d.m2096c() == ComplainReport.EnumC0451a.READY_TO_TRANSMIT ? EnumC0447a.REMOVE_FILES : enumC0447a2;
            }
            f2484a = C0462r.m2016a("yyyy-MM-dd_HH-mm-ss.SSSZ", uploadWorker.f2487d.m2092e());
            String str = new File(uploadWorker.f2487d.m2098b()).getParentFile() == null ? File.separator : parentFile.getAbsolutePath() + File.separator;
            String m2078l = uploadWorker.f2487d.m2078l();
            ComplainReport.EnumC0452b.AUTO.equals(uploadWorker.f2487d.m2094d());
            String format = String.format("%s%s-%s-%s@%s.zip", str, m2078l, uploadWorker.f2487d.m2088g(), DeviceID.m2049a().m2048a(uploadWorker.f2486c), f2484a);
            switch (C0450m.f2501a[enumC0447a.ordinal()]) {
                case 1:
                    uploadWorker.f2487d.m2102a(ComplainReport.EnumC0451a.COMPRESSING);
                    String absolutePath = file.getAbsolutePath();
                    byte[] bArr = new byte[8192];
                    try {
                        zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(format)));
                    } catch (Throwable th1) {
                        th = th1;
                        zipOutputStream = null;
                    }
                    try {
                        uploadWorker.m2104a(new File(absolutePath), zipOutputStream, 0, bArr);
                        try {
                            zipOutputStream.close();
                        } catch (IOException e) {
                        }
                        synchronized (uploadWorker) {
                            ComplainReport.EnumC0451a m2096c = uploadWorker.f2487d.m2096c();
                            if (m2096c == ComplainReport.EnumC0451a.USER_DELETED_OUTBOX) {
                                C0462r.m2018a(format);
                                z = true;
                            } else {
                                uploadWorker.f2487d.m2102a(ComplainReport.EnumC0451a.READY_TO_TRANSMIT);
                                if (m2096c != ComplainReport.EnumC0451a.COMPRESSING) {
                                    z = true;
                                }
                            }
                        }
                        C0462r.m2018a(file.getAbsolutePath());
                        uploadWorker.f2487d.m2097b(format);
                        if (!z) {
                            uploadWorker.m2108a(EnumC0448b.f2496b);
                            return;
                        }
                        Log.d("BugReportUploadWorker", "startUploadJob : " + uploadWorker.f2487d);
                        if (!new File(uploadWorker.f2487d.m2098b()).exists()) {
                            Log.i("BugReportUploadWorker", "Empty zip file: " + uploadWorker.f2487d.m2098b());
                            Log.i("UploadWorker", "Empty File");
                            uploadWorker.f2487d.m2102a(ComplainReport.EnumC0451a.WAIT_USER_INPUT);
                            uploadWorker.m2108a(EnumC0448b.f2497c);
                            return;
                        }
                        if (uploadWorker.f2487d.m2096c() != ComplainReport.EnumC0451a.TRANSMITTING) {
                            uploadWorker.f2487d.m2102a(ComplainReport.EnumC0451a.TRANSMITTING);
                        }
                        uploadWorker.f2485b.m2115a(uploadWorker.f2487d);
                        return;
                    } catch (Throwable th2) {
                        th = th2;
                        if (zipOutputStream != null) {
                            try {
                                zipOutputStream.close();
                            } catch (IOException e2) {
                            }
                        }
                        throw th;
                    }
                case 2:
                    String absolutePath2 = file.getAbsolutePath();
                    byte[] bArr2 = new byte[8192];
                    zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(format)));
                    uploadWorker.m2104a(new File(absolutePath2), zipOutputStream, 0, bArr2);
                    zipOutputStream.close();
                    synchronized (uploadWorker) {
                    }
                    break;
                case 3:
                    synchronized (uploadWorker) {
                    }
                    break;
                case 4:
                    C0462r.m2018a(file.getAbsolutePath());
                    uploadWorker.f2487d.m2097b(format);
                    if (!z) {
                    }
                    break;
                case 5:
                    uploadWorker.f2487d.m2097b(format);
                    if (!z) {
                    }
                    break;
                case 6:
                    if (!z) {
                    }
                    break;
                default:
                    return;
            }
        } catch (Throwable e3) {
            Log.e("BugReportUploadWorker", "Failed to compress " + uploadWorker.f2487d, e3);
            uploadWorker.f2487d.m2102a(ComplainReport.EnumC0451a.COMPRESS_FAILED);
            uploadWorker.m2108a(EnumC0448b.f2497c);
        }
    }
}
