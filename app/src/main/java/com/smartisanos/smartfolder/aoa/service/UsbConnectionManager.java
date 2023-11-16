package com.smartisanos.smartfolder.aoa.service;

import android.content.Context;
import android.hardware.usb.UsbAccessory;
import com.smartisanos.smartfolder.aoa.p055g.Connection;
import com.smartisanos.smartfolder.aoa.p055g.ConnectionManager;
import com.smartisanos.smartfolder.aoa.service.AccessoryManager;
import com.smartisanos.smartfolder.aoa.service.TcpSocketManager;

/* renamed from: com.smartisanos.smartfolder.aoa.service.l */
/* loaded from: classes.dex */
public class UsbConnectionManager extends ConnectionManager {

    /* renamed from: c */
    private static final String f3913c = UsbConnectionManager.class.getSimpleName();

    /* renamed from: d */
    private final C0826a f3914d;

    /* renamed from: e */
    private final C0827b f3915e;

    /* renamed from: f */
    private boolean f3916f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UsbConnectionManager(Context context, ConnectionManager.InterfaceC0750a interfaceC0750a) {
        super(context, interfaceC0750a);
        this.f3914d = new C0826a();
        this.f3915e = new C0827b(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UsbConnectionManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.l$b */
    /* loaded from: classes.dex */
    public class C0827b extends AccessoryManager {
        C0827b(Context context) {
            super(context);
        }

        @Override // com.smartisanos.smartfolder.aoa.service.AccessoryManager
        /* renamed from: b */
        protected final void mo245b() {
            UsbConnectionManager.this.f3600b.mo267a();
            UsbConnectionManager.this.f3916f = true;
        }

        @Override // com.smartisanos.smartfolder.aoa.service.AccessoryManager
        /* renamed from: a */
        protected final void mo246a(AccessoryManager.C0814a c0814a, Connection.C0748c c0748c) {
            UsbConnectionManager.this.m605a(c0814a, c0748c, Connection.EnumC0746a.USB);
        }

        @Override // com.smartisanos.smartfolder.aoa.service.AccessoryManager
        /* renamed from: a */
        protected final void mo247a() {
            UsbConnectionManager.this.f3916f = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UsbConnectionManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.l$a */
    /* loaded from: classes.dex */
    public class C0826a extends TcpSocketManager {
        C0826a() {
        }

        @Override // com.smartisanos.smartfolder.aoa.service.TcpSocketManager
        /* renamed from: a */
        protected final Connection mo227a(TcpSocketManager.C0823b c0823b, Connection.C0748c c0748c) {
            return UsbConnectionManager.this.m605a(c0823b, c0748c, Connection.EnumC0746a.USB);
        }

        @Override // com.smartisanos.smartfolder.aoa.service.TcpSocketManager
        /* renamed from: a */
        protected final boolean mo228a() {
            return !UsbConnectionManager.this.m597g();
        }

        @Override // com.smartisanos.smartfolder.aoa.service.TcpSocketManager
        /* renamed from: b */
        protected final void mo226b() {
            m259e();
        }

        @Override // com.smartisanos.smartfolder.aoa.service.TcpSocketManager
        /* renamed from: c */
        protected final void mo225c() {
            UsbConnectionManager.this.f3600b.mo267a();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m254a(UsbAccessory usbAccessory) {
        return this.f3915e.m271a(usbAccessory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final void m255a(int i) {
        this.f3916f = this.f3914d.m264a(i) != null;
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.ConnectionManager
    /* renamed from: a */
    public final synchronized void mo244a(Connection connection) {
        this.f3916f = false;
        super.mo244a(connection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final boolean m248c() {
        return this.f3916f;
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.ConnectionManager
    /* renamed from: b */
    public final void mo240b(Connection connection) {
        this.f3916f = false;
        super.mo240b(connection);
        this.f3915e.m269c();
        this.f3914d.m263a(connection);
    }
}
