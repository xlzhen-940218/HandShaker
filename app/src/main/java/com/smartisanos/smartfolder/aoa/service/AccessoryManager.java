package com.smartisanos.smartfolder.aoa.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;
import android.os.ParcelFileDescriptor;
import com.smartisanos.smartfolder.aoa.p055g.Connection;
import com.smartisanos.smartfolder.aoa.p055g.SspPacket;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;

import java.io.BufferedInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: com.smartisanos.smartfolder.aoa.service.a */
/* loaded from: classes.dex */
abstract class AccessoryManager {

    /* renamed from: a */
    private final Context f3888a;

    /* renamed from: b */
    private UsbManager f3889b;

    /* renamed from: c */
    private ParcelFileDescriptor f3890c;

    /* renamed from: a */
    protected abstract void mo247a();

    /* renamed from: a */
    protected abstract void mo246a(C0814a c0814a, Connection.C0748c c0748c);

    /* renamed from: b */
    protected abstract void mo245b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public AccessoryManager(Context context) {
        this.f3888a = context;
        this.f3889b = (UsbManager) context.getSystemService(Context.USB_SERVICE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public final boolean m271a(UsbAccessory usbAccessory) {
        ParcelFileDescriptor openAccessory = null;
        try {
            HandShaker.info("AoaManager", "openAccessory");
            openAccessory = this.f3889b.openAccessory(usbAccessory);
        } catch (Exception e) {
            e.printStackTrace();
            HandShaker.error("Open accessory failed by exception: " + e);
            mo247a();
        }
        if (openAccessory != null) {
            if (this.f3890c != null) {
                HandShaker.warn("AoaManager", "Old fd is not closed, old fd: " + this.f3890c.getFd() + ", new fd: " + openAccessory.getFd());
            }
            mo245b();
            this.f3890c = openAccessory;
            FileDescriptor fileDescriptor = this.f3890c.getFileDescriptor();
            mo246a(new C0814a(new FileInputStream(fileDescriptor)), new Connection.C0748c(new FileOutputStream(fileDescriptor)));
            HandShaker.info("AoaManager", "sharePreference..saved.." + this.f3888a.getSharedPreferences("serials", Context.MODE_PRIVATE).getAll().toString());
            HandShaker.info("AoaManager", "openAccessory succeeded");
            return true;
        }
        HandShaker.error("AoaManager", "Open accessory FAILED, may be caused by duplicated usb connection intent");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public final void m269c() {
        try {
        } finally {
            this.f3890c = null;
        }
        if (this.f3890c != null) {
            try {
                this.f3890c.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* compiled from: AccessoryManager.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.a$a */
    /* loaded from: classes.dex */
    public class C0814a extends TcpSocketManager.C0823b {

        /* renamed from: d */
        private final String f3892d;

        @Override // com.smartisanos.smartfolder.aoa.service.TcpSocketManager.C0823b, com.smartisanos.smartfolder.aoa.p055g.Connection.AbstractC0747b
        /* renamed from: b */
        public final /* bridge */ /* synthetic */ SspPacket mo256b() throws IOException {
            return super.mo256b();
        }

        C0814a(InputStream inputStream) {
            super(new BufferedInputStream(inputStream, 16384));
            this.f3892d = C0814a.class.getSimpleName();
        }

        @Override // com.smartisanos.smartfolder.aoa.p055g.Connection.AbstractC0747b
        @TargetApi(19)
        /* renamed from: a */
        public final void mo268a() {
            boolean z = this.f3592c;
            super.mo268a();
            if (!z) {
                try {
                    Class<?> cls = Class.forName("libcore.io.IoBridge");
                    cls.getMethod("closeAndSignalBlockedThreads", FileDescriptor.class).invoke(cls, AccessoryManager.this.f3890c.getFileDescriptor());
                    HandShaker.debug("IoBridge.closeAndSignalBlockedThreads success !!!");
                } catch (Exception e) {
                    HandShaker.debug("IoBridge.closeAndSignalBlockedThreads fail !!!");
                }
            }
        }
    }
}
