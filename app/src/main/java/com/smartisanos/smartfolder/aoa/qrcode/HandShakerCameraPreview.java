package com.smartisanos.smartfolder.aoa.qrcode;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.journeyapps.barcodescanner.RotationCallback;
import com.journeyapps.barcodescanner.RotationListener;
import com.journeyapps.barcodescanner.Size;
import com.journeyapps.barcodescanner.Util;
import com.journeyapps.barcodescanner.camera.CameraInstance;
import com.journeyapps.barcodescanner.camera.CameraSettings;
import com.journeyapps.barcodescanner.camera.CameraSurface;
import com.journeyapps.barcodescanner.camera.CenterCropStrategy;
import com.journeyapps.barcodescanner.camera.DisplayConfiguration;
import com.journeyapps.barcodescanner.camera.FitCenterStrategy;
import com.journeyapps.barcodescanner.camera.FitXYStrategy;
import com.journeyapps.barcodescanner.camera.PreviewScalingStrategy;

import com.smartisanos.smartfolder.aoa.R;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HandShakerCameraPreview extends ViewGroup {

    /* renamed from: a */
    public static final String TAG = HandShakerCameraPreview.class.getSimpleName();

    /* renamed from: A */
    public final InterfaceC0787a f3790A;

    /* renamed from: b */
    private CameraInstance cameraInstance;

    /* renamed from: c */
    private WindowManager windowManager;

    /* renamed from: d */
    public Handler handler;

    /* renamed from: e */
    private boolean useTextureView;

    /* renamed from: f */
    private SurfaceView f3795f;

    /* renamed from: g */
    private TextureView f3796g;

    /* renamed from: h */
    private boolean f3797h;

    /* renamed from: i */
    private RotationListener f3798i;

    /* renamed from: j */
    private int f3799j;

    /* renamed from: k */
    public List<InterfaceC0787a> f3800k;

    /* renamed from: l */
    private DisplayConfiguration displayConfiguration;

    /* renamed from: m */
    private CameraSettings cameraSettings;

    /* renamed from: n */
    private Size f3803n;

    /* renamed from: o */
    private Size scaleSize;

    /* renamed from: p */
    private Rect f3805p;

    /* renamed from: q */
    public Size f3806q;

    /* renamed from: r */
    private Rect f3807r;

    /* renamed from: s */
    private Rect cropRect;

    /* renamed from: t */
    private Size f3809t;

    /* renamed from: u */
    private double f3810u;

    /* renamed from: v */
    private PreviewScalingStrategy strategy;

    /* renamed from: w */
    private boolean torch;

    /* renamed from: x */
    private final SurfaceHolder.Callback surfaceCallback;

    /* renamed from: y */
    private final Handler.Callback handlerCallback;

    /* renamed from: z */
    private RotationCallback rotationCallback;

    /* renamed from: com.smartisanos.smartfolder.aoa.qrcode.HandShakerCameraPreview$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0787a {
        /* renamed from: a */
        void mo308a();

        /* renamed from: a */
        void error(Exception exc);

        /* renamed from: b */
        void start();

        /* renamed from: c */
        void stop();

        /* renamed from: d */
        void close();
    }

    @TargetApi(14)
    /* renamed from: a */
    private TextureView.SurfaceTextureListener m366a() {
        return new TextureView$SurfaceTextureListenerC0791b(this);
    }

    public HandShakerCameraPreview(Context context) {
        super(context);
        this.useTextureView = false;
        this.f3797h = false;
        this.f3799j = -1;
        this.f3800k = new ArrayList();
        this.cameraSettings = new CameraSettings();
        this.f3807r = null;
        this.cropRect = null;
        this.f3809t = null;
        this.f3810u = 0.1d;
        this.strategy = null;
        this.torch = false;
        this.surfaceCallback = new SurfaceHolder$CallbackC0792c(this);
        this.handlerCallback = new C0793d(this);
        this.rotationCallback = new C0794e(this);
        this.f3790A = new C0796g(this);
        m365a(context, (AttributeSet) null);
    }

    public HandShakerCameraPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.useTextureView = false;
        this.f3797h = false;
        this.f3799j = -1;
        this.f3800k = new ArrayList();
        this.cameraSettings = new CameraSettings();
        this.f3807r = null;
        this.cropRect = null;
        this.f3809t = null;
        this.f3810u = 0.1d;
        this.strategy = null;
        this.torch = false;
        this.surfaceCallback = new SurfaceHolder$CallbackC0792c(this);
        this.handlerCallback = new C0793d(this);
        this.rotationCallback = new C0794e(this);
        this.f3790A = new C0796g(this);
        m365a(context, attributeSet);
    }

    public HandShakerCameraPreview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.useTextureView = false;
        this.f3797h = false;
        this.f3799j = -1;
        this.f3800k = new ArrayList();
        this.cameraSettings = new CameraSettings();
        this.f3807r = null;
        this.cropRect = null;
        this.f3809t = null;
        this.f3810u = 0.1d;
        this.strategy = null;
        this.torch = false;
        this.surfaceCallback = new SurfaceHolder$CallbackC0792c(this);
        this.handlerCallback = new C0793d(this);
        this.rotationCallback = new C0794e(this);
        this.f3790A = new C0796g(this);
        m365a(context, attributeSet);
    }

    /* renamed from: a */
    private void m365a(Context context, AttributeSet attributeSet) {
        if (getBackground() == null) {
            setBackgroundColor(-16777216);
        }
        m364a(attributeSet);
        this.windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        this.handler = new Handler(this.handlerCallback);
        this.f3798i = new RotationListener();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useTextureView && Build.VERSION.SDK_INT >= 14) {
            this.f3796g = new TextureView(getContext());
            this.f3796g.setSurfaceTextureListener(m366a());
            addView(this.f3796g);
            return;
        }
        this.f3795f = new SurfaceView(getContext());
        if (Build.VERSION.SDK_INT < 11) {
            this.f3795f.getHolder().setType(3);
        }
        this.f3795f.getHolder().addCallback(this.surfaceCallback);
        addView(this.f3795f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public final void m364a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.zxing_camera_preview);
        int dimension = (int) obtainStyledAttributes.getDimension(R.styleable.zxing_camera_preview_zxing_framing_rect_width, -1.0f);
        int dimension2 = (int) obtainStyledAttributes.getDimension(R.styleable.zxing_camera_preview_zxing_framing_rect_height, -1.0f);
        if (dimension > 0 && dimension2 > 0) {
            this.f3809t = new Size(dimension, dimension2);
        }
        this.useTextureView = obtainStyledAttributes.getBoolean(R.styleable.zxing_camera_preview_zxing_use_texture_view, true);
        int integer = obtainStyledAttributes.getInteger(R.styleable.zxing_camera_preview_zxing_preview_scaling_strategy, -1);
        if (integer == 1) {
            this.strategy = new CenterCropStrategy();
        } else if (integer == 2) {
            this.strategy = new FitCenterStrategy();
        } else if (integer == 3) {
            this.strategy = new FitXYStrategy();
        }
        obtainStyledAttributes.recycle();
    }

    /* renamed from: a */
    public final void m361a(InterfaceC0787a interfaceC0787a) {
        this.f3800k.add(interfaceC0787a);
    }

    /* renamed from: a */
    public final void setTorch(boolean torch) {
        this.torch = torch;
        if (this.cameraInstance != null) {
            this.cameraInstance.setTorch(torch);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: l */
    public void m342l() {
        float f;
        float f2 = 1.0f;
        if (this.f3806q != null && this.scaleSize != null && this.f3805p != null) {
            if (this.f3795f != null && this.f3806q.equals(new Size(this.f3805p.width(), this.f3805p.height()))) {
                m362a(new CameraSurface(this.f3795f.getHolder()));
            } else if (this.f3796g != null && Build.VERSION.SDK_INT >= 14 && this.f3796g.getSurfaceTexture() != null) {
                if (this.scaleSize != null) {
                    Size size = new Size(this.f3796g.getWidth(), this.f3796g.getHeight());
                    Size size2 = this.scaleSize;
                    float f3 = size.width / size.height;
                    float f4 = size2.width / size2.height;
                    if (f3 < f4) {
                        f = f4 / f3;
                    } else {
                        f2 = f3 / f4;
                        f = 1.0f;
                    }
                    Matrix matrix = new Matrix();
                    matrix.setScale(f, f2);
                    matrix.postTranslate((size.width - (f * size.width)) / 2.0f, (size.height - (f2 * size.height)) / 2.0f);
                    this.f3796g.setTransform(matrix);
                }
                m362a(new CameraSurface(this.f3796g.getSurfaceTexture()));
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @SuppressLint({"DrawAllocation"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        PreviewScalingStrategy fitCenterStrategy;
        Size size = new Size(i3 - i, i4 - i2);
        this.f3803n = size;
        if (this.cameraInstance != null && this.cameraInstance.getDisplayConfiguration() == null) {
            this.displayConfiguration = new DisplayConfiguration(m341m(), size);
            DisplayConfiguration displayConfiguration = this.displayConfiguration;
            if (this.strategy != null) {
                fitCenterStrategy = this.strategy;
            } else if (this.f3796g != null) {
                fitCenterStrategy = new CenterCropStrategy();
            } else {
                fitCenterStrategy = new FitCenterStrategy();
            }
            displayConfiguration.setPreviewScalingStrategy(fitCenterStrategy);
            this.cameraInstance.setDisplayConfiguration(this.displayConfiguration);
            this.cameraInstance.configureCamera();
            if (this.torch) {
                this.cameraInstance.setTorch(this.torch);
            }
        }
        if (this.f3795f != null) {
            if (this.f3805p == null) {
                this.f3795f.layout(0, 0, getWidth(), getHeight());
            } else {
                this.f3795f.layout(this.f3805p.left, this.f3805p.top, this.f3805p.right, this.f3805p.bottom);
            }
        } else if (this.f3796g != null && Build.VERSION.SDK_INT >= 14) {
            this.f3796g.layout(0, 0, getWidth(), getHeight());
        }
    }

    /* renamed from: d */
    public final Rect getCropRect() {
        return this.cropRect;
    }

    /* renamed from: a */
    public final void m363a(CameraSettings cameraSettings) {
        this.cameraSettings = cameraSettings;
    }

    /* renamed from: e */
    public final void m350e() {
        Util.validateMainThread();
        Log.d(TAG, "resume()");
        if (this.cameraInstance != null) {
            Log.w(TAG, "initCamera called twice");
        } else {
            CameraInstance cameraInstance = new CameraInstance(getContext());
            cameraInstance.setCameraSettings(this.cameraSettings);
            this.cameraInstance = cameraInstance;
            this.cameraInstance.setReadyHandler(this.handler);
            this.cameraInstance.startPreview();
            this.f3799j = m341m();
        }
        if (this.f3806q != null) {
            m342l();
        } else if (this.f3795f != null) {
            this.f3795f.getHolder().addCallback(this.surfaceCallback);
        } else if (this.f3796g != null && Build.VERSION.SDK_INT >= 14) {
            if (this.f3796g.isAvailable()) {
                m366a().onSurfaceTextureAvailable(this.f3796g.getSurfaceTexture(), this.f3796g.getWidth(), this.f3796g.getHeight());
            } else {
                this.f3796g.setSurfaceTextureListener(m366a());
            }
        }
        requestLayout();
        this.f3798i.listen(getContext(), this.rotationCallback);
    }

    /* renamed from: c */
    public void mo354c() {
        Util.validateMainThread();
        Log.d(TAG, "pause()");
        this.f3799j = -1;
        if (this.cameraInstance != null) {
            this.cameraInstance.close();
            this.cameraInstance = null;
            this.f3797h = false;
        } else {
            this.handler.sendEmptyMessage(R.id.zxing_camera_closed);
        }
        if (this.f3806q == null && this.f3795f != null) {
            this.f3795f.getHolder().removeCallback(this.surfaceCallback);
        }
        if (this.f3806q == null && this.f3796g != null && Build.VERSION.SDK_INT >= 14) {
            this.f3796g.setSurfaceTextureListener(null);
        }
        this.f3803n = null;
        this.scaleSize = null;
        this.cropRect = null;
        this.f3798i.stop();
        this.f3790A.stop();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: g */
    public final boolean m347g() {
        return this.cameraInstance != null;
    }

    /* renamed from: m */
    private int m341m() {
        return this.windowManager.getDefaultDisplay().getRotation();
    }

    /* renamed from: a */
    private void m362a(CameraSurface cameraSurface) {
        if (!this.f3797h && this.cameraInstance != null) {
            Log.i(TAG, "Starting preview");
            this.cameraInstance.setSurface(cameraSurface);
            this.cameraInstance.startPreview();
            this.f3797h = true;
            mo357b();
            this.f3790A.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public void mo357b() {
    }

    /* renamed from: h */
    public final CameraInstance m346h() {
        return this.cameraInstance;
    }

    /* renamed from: i */
    public final boolean m345i() {
        return this.f3797h;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        Parcelable onSaveInstanceState = super.onSaveInstanceState();
        Bundle bundle = new Bundle();
        bundle.putParcelable("super", onSaveInstanceState);
        bundle.putBoolean("torch", this.torch);
        return bundle;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof Bundle)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        Bundle bundle = (Bundle) parcelable;
        super.onRestoreInstanceState(bundle.getParcelable("super"));
        setTorch(bundle.getBoolean("torch"));
    }

    /* renamed from: j */
    public final boolean m344j() {
        return this.cameraInstance == null || this.cameraInstance.isCameraClosed();
    }

    /* renamed from: f */
    public final void m348f() {
        CameraInstance cameraInstance = this.cameraInstance;
        mo354c();
        long nanoTime = System.nanoTime();
        while (cameraInstance != null && !cameraInstance.isCameraClosed() && System.nanoTime() - nanoTime <= 2000000000) {
            try {
                Thread.sleep(1L);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static /* synthetic */ void m355b(HandShakerCameraPreview handShakerCameraPreview, Size size) {
        handShakerCameraPreview.scaleSize = size;
        if (handShakerCameraPreview.f3803n == null) {
            return;
        }
        if (handShakerCameraPreview.f3803n == null || handShakerCameraPreview.scaleSize == null || handShakerCameraPreview.displayConfiguration == null) {
            handShakerCameraPreview.cropRect = null;
            handShakerCameraPreview.f3807r = null;
            handShakerCameraPreview.f3805p = null;
            throw new IllegalStateException("containerSize or previewSize is not set yet");
        }
        int i = handShakerCameraPreview.scaleSize.width;
        int i2 = handShakerCameraPreview.scaleSize.height;
        int i3 = handShakerCameraPreview.f3803n.width;
        int i4 = handShakerCameraPreview.f3803n.height;
        handShakerCameraPreview.f3805p = handShakerCameraPreview.displayConfiguration.scalePreview(handShakerCameraPreview.scaleSize);
        Rect rect = new Rect(0, 0, i3, i4);
        Rect rect2 = handShakerCameraPreview.f3805p;
        Rect rect3 = new Rect(rect);
        rect3.intersect(rect2);
        if (handShakerCameraPreview.f3809t != null) {
            rect3.inset(Math.max(0, (rect3.width() - handShakerCameraPreview.f3809t.width) / 2), Math.max(0, (rect3.height() - handShakerCameraPreview.f3809t.height) / 2));
        } else {
            int min = (int) Math.min(rect3.width() * handShakerCameraPreview.f3810u, rect3.height() * handShakerCameraPreview.f3810u);
            rect3.inset(min, min);
            if (rect3.height() > rect3.width()) {
                rect3.inset(0, (rect3.height() - rect3.width()) / 2);
            }
        }
        handShakerCameraPreview.f3807r = rect3;
        Rect rect4 = new Rect(handShakerCameraPreview.f3807r);
        rect4.offset(-handShakerCameraPreview.f3805p.left, -handShakerCameraPreview.f3805p.top);
        handShakerCameraPreview.cropRect = new Rect((rect4.left * i) / handShakerCameraPreview.f3805p.width()
                , (rect4.top * i2) / handShakerCameraPreview.f3805p.height(), (i * rect4.right) / handShakerCameraPreview.f3805p.width(), (i2 * rect4.bottom) / handShakerCameraPreview.f3805p.height());
        if (handShakerCameraPreview.cropRect.width() <= 0 || handShakerCameraPreview.cropRect.height() <= 0) {
            handShakerCameraPreview.cropRect = null;
            handShakerCameraPreview.f3807r = null;
            Log.w(TAG, "Preview frame is too small");
        } else {
            handShakerCameraPreview.f3790A.mo308a();
        }
        handShakerCameraPreview.requestLayout();
        handShakerCameraPreview.m342l();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static /* synthetic */ void m353c(HandShakerCameraPreview handShakerCameraPreview) {
        if (!handShakerCameraPreview.m347g() || handShakerCameraPreview.m341m() == handShakerCameraPreview.f3799j) {
            return;
        }
        handShakerCameraPreview.mo354c();
        handShakerCameraPreview.m350e();
    }
}
