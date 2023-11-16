package com.smartisanos.smartfolder.aoa.service;

import android.text.TextUtils;
import com.smartisanos.smartfolder.aoa.p051c.MalformedPacketException;
import com.smartisanos.smartfolder.aoa.p051c.PacketTooLargeException;
import com.smartisanos.smartfolder.aoa.p055g.Connection;
import com.smartisanos.smartfolder.aoa.p055g.SspPacket;
import com.smartisanos.smartfolder.aoa.p056h.BiHasMap;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;

/* renamed from: com.smartisanos.smartfolder.aoa.service.i */
/* loaded from: classes.dex */
public abstract class TcpSocketManager {

    /* renamed from: b */
    private int f3901b;

    /* renamed from: c */
    private ServerSocket f3902c;

    /* renamed from: d */
    private C0822a f3903d;

    /* renamed from: a */
    private final String f3900a = "WifiConnectionManager";

    /* renamed from: e */
    private final BiHasMap<Connection, Socket> f3904e = new BiHasMap<>((byte) 0);

    /* renamed from: a */
    protected abstract Connection mo227a(C0823b c0823b, Connection.C0748c c0748c);

    /* renamed from: a */
    protected abstract boolean mo228a();

    /* renamed from: b */
    protected abstract void mo226b();

    /* renamed from: c */
    protected abstract void mo225c();

    /* compiled from: TcpSocketManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.i$a */
    /* loaded from: classes.dex */
    private class C0822a extends Thread {

        /* renamed from: b */
        private final String f3906b;

        /* renamed from: c */
        private ServerSocket f3907c;

        /* renamed from: d */
        private boolean f3908d;

        C0822a(ServerSocket serverSocket) {
            super(C0822a.class.getSimpleName());
            this.f3906b = C0822a.class.getSimpleName();
            this.f3907c = null;
            this.f3908d = false;
            this.f3907c = serverSocket;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    HandShaker.debug(this.f3906b, "start accept socket");
                    Socket accept = this.f3907c.accept();
                    HandShaker.debug(this.f3906b, "accept socket: " + accept);
                    if (accept != null) {
                        boolean mo228a = TcpSocketManager.this.mo228a();
                        HandShaker.debug(this.f3906b, "Incoming wifi client, canConnect = " + mo228a);
                        if (mo228a) {
                            try {
                                TcpSocketManager.m261a(TcpSocketManager.this, accept);
                                TcpSocketManager.this.mo225c();
                                Connection mo227a = TcpSocketManager.this.mo227a(new C0823b(accept.getInputStream()), new Connection.C0748c(accept.getOutputStream()));
                                synchronized (TcpSocketManager.this.f3904e) {
                                    TcpSocketManager.this.f3904e.put(mo227a, accept);
                                    HandShaker.debug(this.f3906b, "mSockets add " + TcpSocketManager.this.f3904e.size());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            accept.close();
                        } catch (IOException e2) {
                            HandShaker.info(this.f3906b, "Close client failed due to exception " + e2);
                        }
                    } else {
                        HandShaker.error(this.f3906b, "RunServerSocket......4.2  mSocket.accept return null!!??");
                    }
                } catch (IOException e3) {
                    HandShaker.warn(this.f3906b, "Stop listening due to " + e3);
                }
            }
            HandShaker.debug(this.f3906b, "stop listen");
            if (!this.f3908d) {
                TcpSocketManager.this.mo226b();
            }
        }

        /* renamed from: a */
        public final void m258a(boolean z) {
            this.f3908d = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public final int m260d() {
        return this.f3901b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final ServerSocket m264a(int i) {
        if (this.f3902c != null) {
            HandShaker.info("WifiConnectionManager", "ServerSocket is already running");
            return this.f3902c;
        }
        HandShaker.info("WifiConnectionManager", "Start Wi-Fi ServerSocket...");
        this.f3902c = null;
        try {
            this.f3902c = new ServerSocket(i);
            this.f3902c.setReuseAddress(true);
            this.f3901b = this.f3902c.getLocalPort();
            this.f3903d = new C0822a(this.f3902c);
            this.f3903d.m258a(false);
            this.f3903d.start();
            return this.f3902c;
        } catch (IOException e) {
            this.f3902c = null;
            HandShaker.error("WifiConnectionManager", "createNewSocketServer failed");
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: e */
    public final void m259e() {
        if (this.f3902c != null) {
            HandShaker.info("WifiConnectionManager", "Close server socket, no client can connect any more");
            try {
                this.f3902c.close();
                this.f3903d.m258a(true);
                this.f3903d = null;
            } catch (IOException e) {
                HandShaker.error("WifiConnectionManager", "Close server socket failed due to " + e);
            } finally {
                this.f3902c = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m263a(Connection connection) {
        try {
            synchronized (this.f3904e) {
                Socket remove = this.f3904e.remove(connection);
                if (remove != null) {
                    remove.close();
                }
            }
        } catch (IOException e) {
            HandShaker.info("WifiConnectionManager", "Close client socket failed due to " + e);
        }
    }

    /* compiled from: TcpSocketManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.i$b */
    /* loaded from: classes.dex */
    static class C0823b extends Connection.SspReader {

        /* renamed from: a */
        private static final String f3909a = C0823b.class.getSimpleName();

        /* renamed from: d */
        private ByteBuffer f3910d;

        /* JADX INFO: Access modifiers changed from: package-private */
        public C0823b(InputStream inputStream) {
            super(inputStream);
            this.f3910d = ByteBuffer.allocate(9);
        }

        @Override // com.smartisanos.smartfolder.aoa.p055g.Connection.AbstractC0747b
        /* renamed from: b */
        public SspPacket mo256b() throws IOException {
            this.f3910d.clear();
            int m257a = m257a(this.f3910d, this.f3910d.capacity());
            if (m257a < this.f3910d.capacity()) {
                HandShaker.info(f3909a, "Not enough byte for packet header, need " + this.f3910d.capacity() + ", read " + m257a);
                return null;
            }
            int i = this.f3910d.getInt();
            byte b = this.f3910d.get();
            int i2 = this.f3910d.getInt();
            if (i2 >= 0 && i2 <= 4194304) {
                ByteBuffer allocate = ByteBuffer.allocate(i2);
                int m257a2 = m257a(allocate, i2);
                if (m257a2 < i2) {
                    try {
                        throw new MalformedPacketException("Not enough bytes for payload, disconnect. sid" + i + ", flag = " + ((int) b) + ", len = " + i2 + ", r = " + m257a2);
                    } catch (MalformedPacketException e) {
                        e.printStackTrace();
                    }
                }
                return new SspPacket(i, b, allocate);
            }
            try {
                throw new PacketTooLargeException("The length of packet is invalid! len = " + i2, i, b);
            } catch (PacketTooLargeException e) {
                e.printStackTrace();
            }
            return null;
        }

        /* renamed from: a */
        private int m257a(ByteBuffer byteBuffer, int i) throws IOException {
            boolean z = true;
            int i2 = 0;
            while (true) {
                if (i2 == i) {
                    break;
                }
                int read = this.f3591b.read(byteBuffer.array(), i2, Math.min(i - i2, z ? 16375 : 16384));
                if (read == -1) {
                    HandShaker.debug(f3909a, "Reach end of file!");
                    break;
                }
                i2 = read + i2;
                z = false;
            }
            return i2;
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m261a(TcpSocketManager tcpSocketManager, Socket socket) {
        synchronized (tcpSocketManager.f3904e) {
            String hostAddress = socket.getInetAddress().getHostAddress();
            HandShaker.debug("WifiConnectionManager", "onSocketConnected adress: " + hostAddress);
            if (!tcpSocketManager.f3904e.isEmpty()) {
                Collection<Socket> values = tcpSocketManager.f3904e.values();
                ArrayList<Connection> arrayList = new ArrayList();
                for (Socket socket2 : values) {
                    if (socket2 != null && TextUtils.equals(socket2.getInetAddress().getHostAddress(), hostAddress)) {
                        Connection m522a = tcpSocketManager.f3904e.m522a(socket2);
                        HandShaker.debug("WifiConnectionManager", "onSocketConnected close connection: " + m522a);
                        if (m522a != null) {
                            arrayList.add(m522a);
                        }
                    }
                }
                for (Connection connection : arrayList) {
                    connection.m621c();
                }
            }
        }
    }
}
