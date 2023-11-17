package com.smartisanos.smartfolder.aoa.p055g;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.os.Build;
import androidx.documentfile.provider.DocumentFile;
import com.smartisanos.smartfolder.aoa.decoder.C0736a;
import com.smartisanos.smartfolder.aoa.p050b.TrustCancelEvent;
import com.smartisanos.smartfolder.aoa.p050b.UploadFileEvent;
import com.smartisanos.smartfolder.aoa.p053e.HeartBeatChecker;
import com.smartisanos.smartfolder.aoa.p056h.BiHasMap;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.SyncFailedException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* renamed from: com.smartisanos.smartfolder.aoa.g.h */
/* loaded from: classes.dex */
public class SspExecutorManager {

    /* renamed from: a */
    private static String f3614a = SspExecutorManager.class.getSimpleName();

    /* renamed from: b */
    private final Connection connection;

    /* renamed from: d */
    private C0736a f3617d;

    /* renamed from: c */
    private C0755c f3616c = new C0755c((byte) 0);

    /* renamed from: e */
    private ConcurrentHashMap<Integer, C0753a> f3618e = new ConcurrentHashMap<>();

    /* compiled from: SspExecutorManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.g.h$b */
    /* loaded from: classes.dex */
    public interface InterfaceC0754b {
        /* renamed from: a */
        void mo576a(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SspExecutorManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.g.h$c */
    /* loaded from: classes.dex */
    public static class C0755c extends ThreadPoolExecutor {

        /* renamed from: a */
        private final String f3630a;

        /* renamed from: b */
        private BiHasMap<Integer, Future<?>> f3631b;

        /* renamed from: c */
        private Map<Integer, Queue<RunnableC0757e>> f3632c;

        /* synthetic */ C0755c(byte b) {
            this();
        }

        private C0755c() {
            super(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue());
            this.f3630a = C0755c.class.getSimpleName();
            this.f3631b = new BiHasMap<>();
            this.f3632c = new HashMap();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: a */
        public void m573a(RunnableC0757e runnableC0757e) {
            synchronized (this) {
                if (!isShutdown()) {
                    if (this.f3631b.containsKey(Integer.valueOf(runnableC0757e.f3636b.m572a()))) {
                        Queue<RunnableC0757e> queue = this.f3632c.get(Integer.valueOf(runnableC0757e.f3636b.m572a()));
                        if (queue == null) {
                            queue = new ArrayDeque<>();
                            this.f3632c.put(Integer.valueOf(runnableC0757e.f3636b.m572a()), queue);
                        }
                        queue.add(runnableC0757e);
                    } else {
                        this.f3631b.put(Integer.valueOf(runnableC0757e.f3636b.m572a()), super.submit(runnableC0757e));
                    }
                }
            }
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        protected void afterExecute(Runnable runnable, Throwable th) {
            Integer m522a;
            Future<?> future = (Future) runnable;
            synchronized (this) {
                m522a = this.f3631b.m522a(future);
                if (m522a != null) {
                    this.f3631b.remove(m522a);
                    Queue<RunnableC0757e> queue = this.f3632c.get(m522a);
                    if (queue != null) {
                        m573a(queue.remove());
                        if (queue.isEmpty()) {
                            this.f3632c.remove(m522a);
                        }
                    }
                }
            }
            try {
                if (future.isDone()) {
                    future.get();
                }
            } catch (InterruptedException e) {
                HandShaker.info(this.f3630a, "Task of session(" + m522a + ") is interrupted, probably caused by cancel");
            } catch (CancellationException e2) {
                HandShaker.verbose(this.f3630a, "Task of session(" + m522a + ") is cancelled");
            } catch (ExecutionException e3) {
                Throwable cause = e3.getCause();
                HandShaker.error(this.f3630a, "Task of session(" + m522a + ") finished with an uncaught exception: " + cause);
                cause.printStackTrace();
            }
        }

        /* renamed from: a */
        static /* synthetic */ void m575a(C0755c c0755c, int i) {
            synchronized (c0755c) {
                c0755c.f3632c.remove(Integer.valueOf(i));
                Future<?> future = c0755c.f3631b.get(Integer.valueOf(i));
                if (future != null && !future.isDone() && !future.isCancelled()) {
                    future.cancel(true);
                }
            }
        }
    }

    /* compiled from: SspExecutorManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.g.h$e */
    /* loaded from: classes.dex */
    public class RunnableC0757e implements Runnable {

        /* renamed from: a */
        private String f3635a = "SspTask";

        /* renamed from: b */
        SspPacket f3636b;

        public RunnableC0757e(SspPacket sspPacket) {
            this.f3636b = sspPacket;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                byte[] bArr = new byte[this.f3636b.m570c().limit() - this.f3636b.m570c().position()];
                this.f3636b.m570c().get(bArr);
                SspExecutorManager.this.f3617d.m684a(this.f3636b.m572a(), this.f3636b.m571b(), bArr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getClass().getSimpleName()).append("[SspPacket = ").append(this.f3636b).append("]");
            return stringBuffer.toString();
        }
    }

    /* compiled from: SspExecutorManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.g.h$d */
    /* loaded from: classes.dex */
    public class C0756d extends RunnableC0757e {

        /* renamed from: d */
        private String f3634d;

        public C0756d(SspPacket sspPacket) {
            super(sspPacket);
            this.f3634d = "SspFileTransferTask";
        }

        @Override // com.smartisanos.smartfolder.aoa.p055g.SspExecutorManager.RunnableC0757e, java.lang.Runnable
        public final void run() {
            int m572a = this.f3636b.m572a();
            C0753a c0753a = (C0753a) SspExecutorManager.this.f3618e.get(Integer.valueOf(m572a));
            if (c0753a == null) {
                HandShaker.warn(this.f3634d, "Cannot find upload file info, may be task is cancelled ? sid=" + m572a);
                return;
            }
            OutputStream m578b = c0753a.m578b();
            ByteBuffer m570c = this.f3636b.m570c();
            int limit = m570c.limit() - m570c.position();
            try {
                m578b.write(m570c.array(), m570c.position(), limit);
                c0753a.f3625g = limit + c0753a.f3625g;
                HandShaker.debug(this.f3634d, "upload over: uploaded size: " + c0753a.f3625g + ", total size: " + c0753a.f3622d);
                if (c0753a.f3625g != c0753a.f3622d) {
                    return;
                }
                if (c0753a.f3624f != null) {
                    try {
                        c0753a.f3624f.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                c0753a.m577b(c0753a);
                HandShaker.debug("session = " + m572a + ", GET_UPLOAD_FILE_REQUEST END");
                SspExecutorManager.this.f3618e.remove(Integer.valueOf(m572a));
                File file = new File(c0753a.f3621c);
                SspExecutorManager.this.connection.m616h().m609a(this.f3636b.m572a()
                        , SmartSyncProtocolProtos.SSPUploadFileResponse.newBuilder()
                                .setFile(SmartSyncProtocolProtos.SSPFile.newBuilder().setPath(c0753a.f3621c)
                                        .setFileSize(c0753a.f3620b)
                                        .setModifiedTimestamp(file.lastModified() / 1000)
                                        .setCreatedTimestamp(file.lastModified() / 1000)
                                        .setIsDirectory(false).build())
                                .setCanceled(false).setSucceed(true).build().toByteArray());
                if (c0753a.f3627i != null) {
                    c0753a.f3627i.mo576a(true);
                }
                ConnectionManager m617g = SspExecutorManager.this.connection.getConnectionManager();
                if (!CommonUtils.m511a(m617g.m598f(), c0753a.f3619a.getUri())) {
                    m617g.m603a(c0753a.f3621c);
                }
            } catch (IOException e3) {
                HandShaker.error(this.f3634d, "Failed to upload file: " + c0753a);
                c0753a.m577b(c0753a);
                c0753a.m580a();
                SspExecutorManager.this.f3618e.remove(Integer.valueOf(m572a));
                SspExecutorManager.this.f3617d.m683a(m572a, c0753a.f3621c);
                if (c0753a.f3627i != null) {
                    c0753a.f3627i.mo576a(false);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SspExecutorManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.g.h$a */
    /* loaded from: classes.dex */
    public class C0753a {

        /* renamed from: a */
        final DocumentFile f3619a;

        /* renamed from: b */
        int f3620b;

        /* renamed from: c */
        String f3621c;

        /* renamed from: d */
        long f3622d;

        /* renamed from: e */
        long f3623e;

        /* renamed from: f */
        OutputStream f3624f;

        /* renamed from: g */
        long f3625g;

        /* renamed from: h */
        FileDescriptor f3626h;

        /* renamed from: i */
        InterfaceC0754b f3627i;

        /* renamed from: k */
        private AssetFileDescriptor f3629k;

        /* synthetic */ C0753a(SspExecutorManager sspExecutorManager, int i, String str, DocumentFile documentFile, long j, long j2, InterfaceC0754b interfaceC0754b, byte b) {
            this(i, str, documentFile, j, j2, interfaceC0754b);
        }

        private C0753a(int i, String str, DocumentFile documentFile, long j, long j2, InterfaceC0754b interfaceC0754b) {
            this.f3620b = i;
            this.f3621c = str;
            this.f3622d = j;
            this.f3623e = j2;
            this.f3619a = documentFile;
            this.f3627i = interfaceC0754b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public OutputStream m578b() {
            SspExecutorManager.this.connection.m622b(true);
            if (this.f3624f == null) {
                Context applicationContext = SspExecutorManager.this.connection.getConnectionManager().m598f().getApplicationContext();
                try {
                    if (Build.VERSION.SDK_INT > 18) {
                        this.f3629k = applicationContext.getContentResolver().openAssetFileDescriptor(this.f3619a.getUri(), "w", null);
                    } else {
                        this.f3629k = applicationContext.getContentResolver().openAssetFileDescriptor(this.f3619a.getUri(), "w");
                    }
                    if (this.f3629k != null) {
                        this.f3626h = this.f3629k.getFileDescriptor();
                        this.f3624f = new FileOutputStream(this.f3626h);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    SspExecutorManager.this.connection.m622b(false);
                    SspExecutorManager.this.f3617d.m683a(this.f3620b, this.f3621c);
                }
            }
            return this.f3624f;
        }

        /* renamed from: a */
        public final void m580a() {
            try {
                HandShaker.debug(SspExecutorManager.f3614a, "delete file: " + this.f3621c + ", isExists: " + this.f3619a.exists() + ", isDir: " + this.f3619a.isDirectory());
                if (this.f3619a.exists() && !this.f3619a.isDirectory()) {
                    this.f3619a.delete();
                    SspExecutorManager.this.connection.getConnectionManager().m603a(this.f3621c);
                }
            } catch (Exception e) {
                e.printStackTrace();
                HandShaker.error(SspExecutorManager.f3614a, "upload exception: deleteFile[" + this.f3619a + "] exception: " + e);
            }
        }

        /* renamed from: b */
         /* synthetic */ void m577b(C0753a c0753a) {
            if (c0753a.f3624f != null) {
                try {
                    c0753a.f3624f.close();
                    HeartBeatChecker.getInstance().m671c();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (c0753a.f3626h != null) {
                try {
                    c0753a.f3626h.sync();
                } catch (SyncFailedException e2) {
                    e2.printStackTrace();
                }
            }
            if (c0753a.f3629k != null) {
                try {
                    c0753a.f3629k.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
            SspExecutorManager.this.connection.m622b(false);
        }
    }

    public SspExecutorManager(Connection connection) {
        this.connection = connection;
        this.f3617d = new C0736a(this.connection);
    }

    /* renamed from: a */
    public final void m584a(SspPacket sspPacket) {
        HeartBeatChecker.getInstance().m671c();
        switch (sspPacket.m571b()) {
            case 0:
            case 1:
                this.f3616c.m573a(new RunnableC0757e(sspPacket));
                return;
            case 2:
                int i = sspPacket.m570c().getInt();
                HandShaker.debug(f3614a, "cancel task sid: " + i);
                C0755c.m575a(this.f3616c, i);
                C0753a remove = this.f3618e.remove(Integer.valueOf(i));
                if (remove == null) {
                    return;
                }
                remove.m577b(remove);
                remove.m580a();
                return;
            case 3:
                EventBus.getDefault().post(new UploadFileEvent());
                this.f3616c.m573a(new C0756d(sspPacket));
                return;
            case 4:
                EventBus.getDefault().post(new TrustCancelEvent());
                return;
            case 5:
                HandShaker.error(f3614a, "QUIT_FLAG");
                this.connection.m625a(false);
                return;
            default:
                HandShaker.error(f3614a, "Unexpected flag: " + ((int) sspPacket.m571b()));
                return;
        }
    }

    /* renamed from: a */
    public final void m586a(int i, String str, DocumentFile documentFile, long j, long j2, InterfaceC0754b interfaceC0754b) {
        if (!Thread.interrupted()) {
            this.f3618e.put(Integer.valueOf(i), new C0753a(this, i, str, documentFile, j, j2, interfaceC0754b, (byte) 0));
        }
    }

    /* renamed from: a */
    public final void m587a(int i, byte b) {
        C0753a c0753a;
        if (b == 3 && this.f3618e != null && this.f3618e.size() > 0 && (c0753a = this.f3618e.get(Integer.valueOf(i))) != null) {
            c0753a.m577b(c0753a);
            c0753a.m580a();
            this.f3618e.remove(Integer.valueOf(i));
        }
    }

    /* renamed from: a */
    public final void m588a() {
        this.f3617d.m685a();
        HandShaker.debug(f3614a, "onDestroy");
        for (C0753a c0753a : this.f3618e.values()) {
            if (c0753a != null) {
                c0753a.m577b(c0753a);
                c0753a.m580a();
                this.f3618e.remove(Integer.valueOf(c0753a.f3620b));
            }
        }
        if (!this.f3616c.isShutdown()) {
            this.f3616c.shutdownNow();
        }
    }
}
