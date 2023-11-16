package com.smartisanos.smartfolder.aoa.p056h;

import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.util.ArrayList;

/* compiled from: DeviceInfoHelper.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.i */
/* loaded from: classes.dex */
final class RunnableC0772i implements Runnable {

    /* renamed from: a */
    final /* synthetic */ DeviceInfoHelper.C0764b f3737a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0772i(DeviceInfoHelper.C0764b c0764b) {
        this.f3737a = c0764b;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ArrayList m460g;
        EventManager.getInstance();
        if (EventManager.m794c()) {
            try {
                m460g = DeviceInfoHelper.m460g();
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            if (m460g == null) {
                EventManager.getInstance().m799a(EventManager.REQUEST_TYPE.CLIPBOARD);
                EventManager.getInstance().m798a(EventManager.REQUEST_TYPE.CLIPBOARD
                        , SmartSyncProtocolProtos.SSPClipboardChange.newBuilder()
                                .setType(SmartSyncProtocolProtos.SSPRequestType.CLIPBOARD_CHANGE).build().toByteArray());
                return;
            }
            EventManager.getInstance().m799a(EventManager.REQUEST_TYPE.CLIPBOARD);
            EventManager.getInstance().m798a(EventManager.REQUEST_TYPE.CLIPBOARD
                    , SmartSyncProtocolProtos.SSPClipboardChange.newBuilder()
                            .setType(SmartSyncProtocolProtos.SSPRequestType.CLIPBOARD_CHANGE)
                            .addAllClipboard(m460g).build().toByteArray());
        }
    }
}
