package com.smartisanos.smartfolder.aoa.qrcode;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.android.DecodeFormatManager;
import com.google.zxing.client.android.DecodeHintManager;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DefaultDecoderFactory;

import com.journeyapps.barcodescanner.camera.CameraSettings;
import com.smartisanos.smartfolder.aoa.R;

import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes.dex */
public class MainScanView extends FrameLayout {

    /* renamed from: a */
    private HandShakerBarcodeView handShakerBarcodeView;

    /* renamed from: b */
    private TextView f3817b;

    /* renamed from: c */
    private TranslateAnimation f3818c;

    /* renamed from: d */
    private ImageView f3819d;

    /* renamed from: e */
    private int f3820e;

    /* renamed from: f */
    private InterfaceC0788a f3821f;

    /* renamed from: com.smartisanos.smartfolder.aoa.qrcode.MainScanView$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0788a {
        /* renamed from: a */
        void mo303a();

        /* renamed from: b */
        void mo302b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.smartisanos.smartfolder.aoa.qrcode.MainScanView$b */
    /* loaded from: classes.dex */
    public class C0789b implements BarcodeCallback {

        /* renamed from: b */
        private BarcodeCallback f3823b;

        public C0789b(BarcodeCallback barcodeCallback) {
            this.f3823b = barcodeCallback;
        }

        @Override // com.journeyapps.barcodescanner.BarcodeCallback
        /* renamed from: a */
        public final void barcodeResult(BarcodeResult barcodeResult) {
            this.f3823b.barcodeResult(barcodeResult);
        }

        @Override // com.journeyapps.barcodescanner.BarcodeCallback
        /* renamed from: a */
        public final void possibleResultPoints(List<ResultPoint> list) {
            this.f3823b.possibleResultPoints(list);
        }
    }

    public MainScanView(Context context) {
        super(context);
        m338a((AttributeSet) null);
    }

    public MainScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m338a(attributeSet);
    }

    public MainScanView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m338a(attributeSet);
    }

    /* renamed from: a */
    private void m338a(AttributeSet attributeSet) {
        inflate(getContext(), R.layout.layout_scanner, this);
        this.handShakerBarcodeView = (HandShakerBarcodeView) findViewById(R.id.barcode_surface);
        this.handShakerBarcodeView.m364a(attributeSet);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) getResources().getDimension(R.dimen.scanner_view_finder_width), (int) getResources().getDimension(R.dimen.scanner_view_finder_height));
        layoutParams.addRule(13);
        ((FrameLayout) findViewById(R.id.scan_frame)).setLayoutParams(layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.bottomMargin = (int) getResources().getDimension(R.dimen.scanner_tipsview_magin_bottom);
        layoutParams2.addRule(2, R.id.scan_frame);
        layoutParams2.addRule(15);
        layoutParams2.addRule(14);
        ((TextView) findViewById(R.id.scan_tips)).setLayoutParams(layoutParams2);
        this.f3817b = (TextView) findViewById(R.id.zxing_status_view);
        this.f3819d = (ImageView) findViewById(R.id.scan_indicator);
        this.f3820e = (int) getContext().getResources().getDimension(R.dimen.scanner_view_finder_height);
    }

    /* renamed from: a */
    public final void m339a(Intent intent) {
        int cameraId;
        Set<BarcodeFormat> decoderFactory = DecodeFormatManager.parseDecodeFormats(intent);
        Map<DecodeHintType, ?> decodeHintTypeMap = DecodeHintManager.parseDecodeHints(intent);
        CameraSettings cameraSettings = new CameraSettings();
        if (intent.hasExtra("SCAN_CAMERA_ID") && (cameraId = intent.getIntExtra("SCAN_CAMERA_ID", -1)) >= 0) {
            cameraSettings.setRequestedCameraId(cameraId);
        }
        String stringExtra = intent.getStringExtra("PROMPT_MESSAGE");
        if (stringExtra != null && this.f3817b != null) {
            this.f3817b.setText(stringExtra);
        }
        boolean invertedScan = intent.getBooleanExtra("INVERTED_SCAN", false);
        String characterSet = intent.getStringExtra("CHARACTER_SET");
        new MultiFormatReader().setHints(decodeHintTypeMap);
        this.handShakerBarcodeView.m363a(cameraSettings);
        this.handShakerBarcodeView.m373a(new DefaultDecoderFactory(decoderFactory, decodeHintTypeMap, characterSet, invertedScan ? 1 : 0));
    }

    /* renamed from: b */
    public final void pause() {
        this.handShakerBarcodeView.m348f();
    }

    /* renamed from: c */
    public final void resume() {
        this.handShakerBarcodeView.resume();
        if (this.f3818c == null) {
            this.f3818c = new TranslateAnimation(this.f3819d.getX(), this.f3819d.getX(), this.f3819d.getTranslationY() - this.f3820e, this.f3819d.getTranslationY() + this.f3820e);
            this.f3818c.setRepeatCount(-1);
            this.f3818c.setRepeatMode(1);
            this.f3818c.setDuration(2000L);
        }
        this.f3819d.startAnimation(this.f3818c);
        this.f3819d.setVisibility(View.VISIBLE);
    }

    /* renamed from: d */
    public final HandShakerBarcodeView m333d() {
        return this.handShakerBarcodeView;
    }

    /* renamed from: a */
    public final void m337a(BarcodeCallback barcodeCallback) {
        this.handShakerBarcodeView.m374a(new C0789b(barcodeCallback));
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 24:
                this.handShakerBarcodeView.setTorch(true);
                if (this.f3821f != null) {
                    this.f3821f.mo303a();
                    return true;
                }
                return true;
            case 25:
                this.handShakerBarcodeView.setTorch(false);
                if (this.f3821f != null) {
                    this.f3821f.mo302b();
                    return true;
                }
                return true;
            case 27:
            case 80:
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }

    /* renamed from: a */
    public final void m336a(InterfaceC0788a interfaceC0788a) {
        this.f3821f = interfaceC0788a;
    }

    /* renamed from: a */
    public final void m340a() {
        if (this.f3818c != null) {
            this.f3818c.cancel();
        }
        this.f3819d.setVisibility(View.GONE);
        this.handShakerBarcodeView.pause();
    }
}
