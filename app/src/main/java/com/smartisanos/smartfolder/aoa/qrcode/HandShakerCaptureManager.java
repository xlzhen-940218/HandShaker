package com.smartisanos.smartfolder.aoa.qrcode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.zxing.ResultMetadataType;
import com.google.zxing.client.android.BeepManager;
import com.google.zxing.client.android.InactivityTimer;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/* renamed from: com.smartisanos.smartfolder.aoa.qrcode.h */
/* loaded from: classes.dex */
public class HandShakerCaptureManager {

    /* renamed from: a */
    public static final String TAG = HandShakerCaptureManager.class.getSimpleName();

    /* renamed from: b */
    private static int f3835b = 250;

    /* renamed from: c */
    public Activity activity;

    /* renamed from: d */
    public MainScanView mainScanView;

    /* renamed from: h */
    private InactivityTimer inactivityTimer;

    /* renamed from: i */
    public BeepManager beepManager;

    /* renamed from: j */
    public Handler handler;

    /* renamed from: e */
    private int orientationLock = -1;

    /* renamed from: f */
    private boolean barcodeImageEnabled = false;

    /* renamed from: g */
    private boolean f3840g = false;

    /* renamed from: k */
    public boolean f3844k = false;

    /* renamed from: l */
    public BarcodeCallback sBarcodeCallback = new SBarcodeCallback(this);

    /* renamed from: m */
    private final HandShakerCameraPreview.InterfaceC0787a f3846m = new C0801m(this);

    /* renamed from: n */
    private boolean f3847n = false;

    public HandShakerCaptureManager(Activity activity, MainScanView mainScanView) {
        this.activity = activity;
        this.mainScanView = mainScanView;
        mainScanView.m333d().m361a(this.f3846m);
        this.handler = new Handler();
        this.inactivityTimer = new InactivityTimer(activity, new RunnableC0802n(this));
        this.beepManager = new BeepManager(activity);
    }

    /* renamed from: a */
    @SuppressLint("WrongConstant")
    public final void m329a(Intent intent, Bundle bundle) {
        int i;
        this.activity.getWindow().addFlags(128);
        if (bundle != null) {
            this.orientationLock = bundle.getInt("SAVED_ORIENTATION_LOCK", -1);
        }
        if (intent != null) {
            if (intent.getBooleanExtra("SCAN_ORIENTATION_LOCKED", true)) {
                if (this.orientationLock == -1) {
                    int rotation = this.activity.getWindowManager().getDefaultDisplay().getRotation();
                    int i2 = this.activity.getResources().getConfiguration().orientation;
                    if (i2 == 2) {
                        i = (rotation == 0 || rotation == 1) ? 0 : 8;
                    } else if (i2 == 1) {
                        i = (rotation == 0 || rotation == 3) ? 1 : 9;
                    } else {
                        i = 0;
                    }
                    this.orientationLock = i;
                }
                this.activity.setRequestedOrientation(this.orientationLock);
            }
            if ("com.google.zxing.client.android.SCAN".equals(intent.getAction())) {
                this.mainScanView.m339a(intent);
            }
            if (!intent.getBooleanExtra("BEEP_ENABLED", true)) {
                this.beepManager.setBeepEnabled(false);
            }
            if (intent.hasExtra("TIMEOUT")) {
                this.handler.postDelayed(new RunnableC0803o(this), intent.getLongExtra("TIMEOUT", 0L));
            }
            if (intent.getBooleanExtra("BARCODE_IMAGE_ENABLED", false)) {
                this.barcodeImageEnabled = true;
            }
        }
    }

    /* renamed from: a */
    public final void m331a() {
        this.mainScanView.m337a(this.sBarcodeCallback);
    }

    /* renamed from: b */
    public final void m325b() {
        if (Build.VERSION.SDK_INT < 23) {
            this.mainScanView.m334c();
        } else if (ContextCompat.checkSelfPermission(this.activity, "android.permission.CAMERA") == 0) {
            this.mainScanView.m334c();
        } else if (!this.f3847n) {
            ActivityCompat.requestPermissions(this.activity, new String[]{"android.permission.CAMERA"}, f3835b);
            this.f3847n = true;
        }
        this.inactivityTimer.start();
    }

    /* renamed from: a */
    public final void m330a(int i, int[] iArr) {
        if (i == f3835b) {
            if (iArr.length > 0 && iArr[0] == 0) {
                this.mainScanView.m334c();
            } else {
                error();
            }
        }
    }

    /* renamed from: c */
    public final void m322c() {
        this.inactivityTimer.cancel();
        this.mainScanView.m335b();
    }

    /* renamed from: d */
    public final void m319d() {
        this.f3840g = true;
        this.inactivityTimer.cancel();
        this.handler.removeCallbacksAndMessages(null);
    }

    /* renamed from: a */
    public final void m328a(Bundle bundle) {
        bundle.putInt("SAVED_ORIENTATION_LOCK", this.orientationLock);
    }

    /* renamed from: c */
    private String m321c(BarcodeResult barcodeResult) {
        if (!this.barcodeImageEnabled) {
            return null;
        }
        Bitmap bitmap = barcodeResult.getBitmap();
        try {
            File createTempFile = File.createTempFile("barcodeimage", ".jpg", this.activity.getCacheDir());
            FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.close();
            return createTempFile.getAbsolutePath();
        } catch (IOException e) {
            Log.w(TAG, "Unable to create temporary file and store bitmap! " + e);
            return null;
        }
    }

    /* renamed from: h */
    private void m311h() {
        if (!this.mainScanView.m333d().m344j()) {
            this.f3844k = true;
        } else {
            this.activity.finish();
        }
        this.mainScanView.m340a();
        this.inactivityTimer.cancel();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: e */
    public final void m317e() {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("TIMEOUT", true);
        this.activity.setResult(0, intent);
        m311h();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m327a(BarcodeResult barcodeResult) {
        String m321c = m321c(barcodeResult);
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        intent.putExtra("SCAN_RESULT", barcodeResult.toString());
        intent.putExtra("SCAN_RESULT_FORMAT", barcodeResult.getBarcodeFormat().toString());
        byte[] m2185b = barcodeResult.getRawBytes();
        if (m2185b != null && m2185b.length > 0) {
            intent.putExtra("SCAN_RESULT_BYTES", m2185b);
        }
        Map<ResultMetadataType, Object> m2183d = barcodeResult.getResultMetadata();
        if (m2183d != null) {
            if (m2183d.containsKey(ResultMetadataType.UPC_EAN_EXTENSION)) {
                intent.putExtra("SCAN_RESULT_UPC_EAN_EXTENSION", m2183d.get(ResultMetadataType.UPC_EAN_EXTENSION).toString());
            }
            Number number = (Number) m2183d.get(ResultMetadataType.ORIENTATION);
            if (number != null) {
                intent.putExtra("SCAN_RESULT_ORIENTATION", number.intValue());
            }
            String str = (String) m2183d.get(ResultMetadataType.ERROR_CORRECTION_LEVEL);
            if (str != null) {
                intent.putExtra("SCAN_RESULT_ERROR_CORRECTION_LEVEL", str);
            }
            Iterable<byte[]> iterable = (Iterable) m2183d.get(ResultMetadataType.BYTE_SEGMENTS);
            if (iterable != null) {
                int i = 0;
                for (byte[] bArr : iterable) {
                    intent.putExtra("SCAN_RESULT_BYTE_SEGMENTS_" + i, bArr);
                    i++;
                }
            }
        }
        if (m321c != null) {
            intent.putExtra("SCAN_RESULT_IMAGE_PATH", m321c);
        }
        this.activity.setResult(-1, intent);
        m311h();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: f */
    public final void error() {
        if (!this.activity.isFinishing() && !this.f3840g && !this.f3844k) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this.activity);
            builder.setTitle(this.activity.getString(R.string.zxing_app_name));
            builder.setMessage(this.activity.getString(R.string.zxing_msg_camera_framework_bug));
            builder.setPositiveButton(R.string.zxing_button_ok, new DialogInterface$OnClickListenerC0804p(this));
            builder.setOnCancelListener(new DialogInterface$OnCancelListenerC0805q(this));
            builder.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static /* synthetic */ boolean m324b(BarcodeResult barcodeResult) {
        if (barcodeResult == null || !CommonUtils.checkQRData(barcodeResult.toString())) {
            return false;
        }
        return true;
    }
}
