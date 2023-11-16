package com.smartisanos.smartfolder.aoa.p055g;


import androidx.documentfile.provider.DocumentFile;

import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.aoa.p051c.WriteStopException;
import com.smartisanos.smartfolder.aoa.p053e.HeartBeatChecker;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.util.HashSet;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

/* renamed from: com.smartisanos.smartfolder.aoa.g.a */
/* loaded from: classes.dex */
public class Connection {

    /* renamed from: a */
    public static String TAG = Connection.class.getSimpleName();

    /* renamed from: b */
    public final SspReader sspReader;

    /* renamed from: c */
    private final C0748c f3579c;

    /* renamed from: e */
    private final EnumC0746a f3581e;

    /* renamed from: f */
    private ConnectionManager connectionManager;

    /* renamed from: g */
    private volatile boolean f3583g;

    /* renamed from: h */
    private PublicKey publicKey;

    /* renamed from: i */
    private String f3585i;

    /* renamed from: j */
    private long f3586j;

    /* renamed from: k */
    private Thread f3587k = new C0749b(this);

    /* renamed from: d */
    public SspExecutorManager f3580d = new SspExecutorManager(this);

    /* compiled from: Connection.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.g.a$a */
    /* loaded from: classes.dex */
    public enum EnumC0746a {
        USB,
        WIFI
    }

    /* renamed from: a */
    public final PublicKey m631a() {
        return this.publicKey;
    }

    /* renamed from: a */
    public final void m626a(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    /* renamed from: b */
    public final String m624b() {
        return this.f3585i;
    }

    /* renamed from: a */
    public final void setHostName(String str) {
        this.f3585i = str;
    }

    /* compiled from: Connection.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.g.a$b */
    /* loaded from: classes.dex */
    public static abstract class SspReader {

        /* renamed from: b */
        protected InputStream f3591b;

        /* renamed from: c */
        protected boolean f3592c = false;

        /* renamed from: b */
        public abstract SspPacket mo256b() throws IOException;

        public SspReader(InputStream inputStream) {
            this.f3591b = inputStream;
        }

        /* renamed from: a */
        public void mo268a() {
            synchronized (this) {
                if (this.f3592c) {
                    HandShaker.debug(Connection.TAG, "SspReader has been closed!");
                    return;
                }
                this.f3592c = true;
                try {
                    this.f3591b.close();
                    HandShaker.debug(Connection.TAG, "SspReader close success!");
                } catch (IOException e) {
                    HandShaker.error(Connection.TAG, "SspReader close exception: " + e);
                }
            }
        }
    }

    public Connection(SspReader abstractC0747b, C0748c c0748c, EnumC0746a enumC0746a, ConnectionManager connectionManager) {
        this.sspReader = abstractC0747b;
        this.f3579c = c0748c;
        this.f3581e = enumC0746a;
        this.connectionManager = connectionManager;
        this.f3587k.start();
    }

    /* renamed from: c */
    public final void m621c() {
        m625a(true);
    }

    /* renamed from: a */
    public final void m625a(boolean z) {
        if (z) {
            SmartSyncProtocolProtos.SSPRequest d = SmartSyncProtocolProtos.SSPRequest.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.QUIT_REQUEST).build();
            EventManager.getInstance();
            int m793d = EventManager.m793d();
            this.f3579c.m609a(m793d, d.toByteArray());
            HandShaker.debug(TAG, "sendQuitRequest success " + d.getType() + ", sid = " + m793d);
        }
        HandShaker.debug(TAG, "close");
        this.f3587k.interrupt();
        this.sspReader.mo268a();
        this.f3579c.m613a();
    }

    /* renamed from: d */
    public final void m620d() {
        this.f3580d.m588a();
        this.connectionManager.mo240b(this);
    }

    /* renamed from: e */
    public final boolean m619e() {
        return this.f3583g;
    }

    /* renamed from: a */
    public final void m630a(int i, String str, DocumentFile documentFile, long j, long j2, SspExecutorManager.InterfaceC0754b interfaceC0754b) {
        this.f3580d.m586a(i, str, documentFile, j, j2, interfaceC0754b);
    }

    /* renamed from: f */
    public final EnumC0746a m618f() {
        return this.f3581e;
    }

    /* renamed from: g */
    public final ConnectionManager m617g() {
        return this.connectionManager;
    }

    /* renamed from: h */
    public final C0748c m616h() {
        return this.f3579c;
    }

    /* renamed from: a */
    public final void setHeartbeatTimeoutSecond(long j) {
        HandShaker.debug(TAG, "setTimeout " + j);
        this.f3586j = j;
    }

    /* renamed from: i */
    public final long m615i() {
        return this.f3586j;
    }

    /* compiled from: Connection.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.g.a$c */
    /* loaded from: classes.dex */
    public static class C0748c {

        /* renamed from: a */
        private static final String TAG = C0748c.class.getSimpleName();

        /* renamed from: b */
        private final OutputStream f3594b;

        /* renamed from: c */
        private HashSet<Integer> f3595c = new HashSet<>();

        /* renamed from: d */
        private HashSet<Integer> f3596d = new HashSet<>();

        /* renamed from: e */
        private boolean f3597e;

        public C0748c(OutputStream outputStream) {
            this.f3594b = outputStream;
        }

        /* renamed from: a */
        public final void m609a(int i, byte[] bArr) {
            int length = bArr.length;
            ByteBuffer allocate = ByteBuffer.allocate(length + 8);
            allocate.putLong(length);
            allocate.put(bArr, 0, length);
            stop(i, new ByteArrayInputStream(allocate.array()), allocate.array().length);
        }

        /* renamed from: a */
        public final void m611a(int i, File file, long j, long j2, boolean z) {
            if (z) {
                synchronized (this.f3595c) {
                    this.f3595c.add(Integer.valueOf(i));
                }
            }
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);

                fileInputStream.skip(j);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stop(i, fileInputStream, j2);
        }

        /* renamed from: a */
        private void externalFileFinish(int i) {
            if (this.f3595c.contains(Integer.valueOf(i))) {
                HandShaker.debug(TAG, "externalFileFinish sessionId: " + i);
                synchronized (this.f3595c) {
                    this.f3595c.remove(Integer.valueOf(i));
                    if (this.f3597e) {
                        this.f3596d.remove(Integer.valueOf(i));
                        if (this.f3596d.isEmpty()) {
                            HandShaker.debug(TAG, "externalFileFinish mStopSessionIds is empty");
                            this.f3597e = false;
                        }
                    }
                }
            }
        }

        /* renamed from: a */
        private void stop(int i, InputStream inputStream, long j) {
            long j2 = 0;
            ByteBuffer allocate = ByteBuffer.allocate(32767);
            while (j2 < j) {
                try {
                    if (!Thread.currentThread().isInterrupted()) {
                        if (this.f3597e) {
                            synchronized (this.f3595c) {
                                if (this.f3596d.contains(Integer.valueOf(i))) {
                                    HandShaker.debug(TAG, "stop sessionId = " + i);
                                    externalFileFinish(i);
                                    throw new WriteStopException("sessionId[" + i + "] is stopped");
                                }
                            }
                        }
                        allocate.clear();
                        allocate.putInt(i);
                        int read = inputStream.read(allocate.array(), 6, allocate.array().length - 6);
                        if (read <= 0) {
                            break;
                        }
                        allocate.putShort(4, (short) read);
                        j2 += read;
                        m608a(allocate.array(), read + 6);
                    } else {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } finally {
                    externalFileFinish(i);
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        /* renamed from: a */
        private synchronized void m608a(byte[] bArr, int i) {
            if (!Thread.interrupted()) {
                try {
                    this.f3594b.write(bArr, 0, i);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                HeartBeatChecker.getInstance().m671c();
            }
        }

        /* renamed from: a */
        public final synchronized void m613a() {
            try {
                this.f3594b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /* renamed from: b */
        public final void m607b() {
            HandShaker.debug(TAG, "stopWriteFile");
            synchronized (this.f3595c) {
                this.f3596d.addAll(this.f3595c);
                this.f3597e = true;
            }
        }
    }

    /* renamed from: b */
    public final void m622b(boolean z) {
        this.f3583g = z;
        if (!z) {
            HeartBeatChecker.getInstance().m669e();
        }
    }
}
