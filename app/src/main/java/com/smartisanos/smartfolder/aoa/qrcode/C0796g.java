package com.smartisanos.smartfolder.aoa.qrcode;

import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: HandShakerCameraPreview.java */
/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.g */
/* loaded from: classes.dex */
public final class C0796g implements HandShakerCameraPreview.InterfaceC0787a {

    /* renamed from: a */
    final /* synthetic */ HandShakerCameraPreview f3833a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0796g(HandShakerCameraPreview handShakerCameraPreview) {
        this.f3833a = handShakerCameraPreview;
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: a */
    public final void mo308a() {
        List<HandShakerCameraPreview.InterfaceC0787a> list;
        list = this.f3833a.f3800k;
        for (HandShakerCameraPreview.InterfaceC0787a interfaceC0787a : list) {
            interfaceC0787a.mo308a();
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: b */
    public final void start() {
        List<HandShakerCameraPreview.InterfaceC0787a> list;
        list = this.f3833a.f3800k;
        for (HandShakerCameraPreview.InterfaceC0787a interfaceC0787a : list) {
            interfaceC0787a.start();
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: c */
    public final void stop() {
        List<HandShakerCameraPreview.InterfaceC0787a> list;
        list = this.f3833a.f3800k;
        for (HandShakerCameraPreview.InterfaceC0787a interfaceC0787a : list) {
            interfaceC0787a.stop();
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: a */
    public final void error(Exception exc) {
        List<HandShakerCameraPreview.InterfaceC0787a> list;
        list = this.f3833a.f3800k;
        for (HandShakerCameraPreview.InterfaceC0787a interfaceC0787a : list) {
            interfaceC0787a.error(exc);
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview.InterfaceC0787a
    /* renamed from: d */
    public final void close() {
        List<HandShakerCameraPreview.InterfaceC0787a> list;
        list = this.f3833a.f3800k;
        for (HandShakerCameraPreview.InterfaceC0787a interfaceC0787a : list) {
            interfaceC0787a.close();
        }
    }
}
