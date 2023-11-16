package com.smartisanos.smartfolder.aoa.qrcode;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

import com.google.zxing.DecodeHintType;
import com.journeyapps.barcodescanner.BarcodeCallback;

import com.journeyapps.barcodescanner.Decoder;
import com.journeyapps.barcodescanner.DecoderFactory;
import com.journeyapps.barcodescanner.DecoderResultPointCallback;
import com.journeyapps.barcodescanner.DecoderThread;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;
import com.journeyapps.barcodescanner.Util;

import java.util.HashMap;

/* loaded from: classes.dex */
public class HandShakerBarcodeView extends HandShakerCameraPreview {

    /* renamed from: a */
    public int f3779a;

    /* renamed from: b */
    public BarcodeCallback barcodeCallback;

    /* renamed from: c */
    private DecoderThread decoderThread;

    /* renamed from: d */
    private DecoderFactory decoderFactory;

    /* renamed from: e */
    private Handler handler;

    /* renamed from: f */
    private final Handler.Callback callback;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* renamed from: com.smartisanos.smartfolder.aoa.qrcode.HandShakerBarcodeView$a */
    /* loaded from: classes.dex */
    public static final class EnumC0786a {

        /* renamed from: a */
        public static final int f3785a = 1;

        /* renamed from: b */
        public static final int f3786b = 2;

        /* renamed from: c */
        public static final int f3787c = 3;

        /* renamed from: d */
        private static final /* synthetic */ int[] f3788d = {f3785a, f3786b, f3787c};
    }

    public HandShakerBarcodeView(Context context) {
        super(context);
        this.f3779a = EnumC0786a.f3785a;
        this.barcodeCallback = null;
        this.callback = new C0790a(this);
        m370l();
    }

    public HandShakerBarcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3779a = EnumC0786a.f3785a;
        this.barcodeCallback = null;
        this.callback = new C0790a(this);
        m370l();
    }

    public HandShakerBarcodeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3779a = EnumC0786a.f3785a;
        this.barcodeCallback = null;
        this.callback = new C0790a(this);
        m370l();
    }

    /* renamed from: l */
    private void m370l() {
        this.decoderFactory = new DefaultDecoderFactory();
        this.handler = new Handler(this.callback);
    }

    /* renamed from: a */
    public final void m373a(DecoderFactory decoderFactory) {
        Util.validateMainThread();
        this.decoderFactory = decoderFactory;
        if (this.decoderThread != null) {
            this.decoderThread.setDecoder(getDecoder());
        }
    }

    /* renamed from: m */
    private Decoder getDecoder() {
        if (this.decoderFactory == null) {
            this.decoderFactory = new DefaultDecoderFactory();
        }
        DecoderResultPointCallback decoderResultPointCallback = new DecoderResultPointCallback();
        HashMap hashMap = new HashMap();
        hashMap.put(DecodeHintType.NEED_RESULT_POINT_CALLBACK, decoderResultPointCallback);
        Decoder decoder = this.decoderFactory.createDecoder(hashMap);
        decoderResultPointCallback.setDecoder(decoder);
        return decoder;
    }

    /* renamed from: a */
    public final void m374a(BarcodeCallback barcodeCallback) {
        this.f3779a = EnumC0786a.f3786b;
        this.barcodeCallback = barcodeCallback;
        m368n();
    }

    /* renamed from: a */
    public final void m375a() {
        this.f3779a = EnumC0786a.f3785a;
        this.barcodeCallback = null;
        m367o();
    }

    /* renamed from: n */
    private void m368n() {
        m367o();
        if (this.f3779a != EnumC0786a.f3785a && m345i()) {
            this.decoderThread = new DecoderThread(m346h(), getDecoder(), this.handler);
            this.decoderThread.setCropRect(getCropRect());
            this.decoderThread.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview
    /* renamed from: b */
    public final void mo357b() {
        super.mo357b();
        m368n();
    }

    /* renamed from: o */
    private void m367o() {
        if (this.decoderThread != null) {
            this.decoderThread.stop();
            this.decoderThread = null;
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview
    /* renamed from: c */
    public final void pause() {
        m367o();
        super.pause();
    }
}
