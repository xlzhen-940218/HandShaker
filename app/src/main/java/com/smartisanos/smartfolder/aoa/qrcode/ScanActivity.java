package com.smartisanos.smartfolder.aoa.qrcode;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p050b.ScanCancelEvent;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.NetWorkUtils;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class ScanActivity extends Activity {

    /* renamed from: a */
    private HandShakerCaptureManager handShakerCaptureManager;

    /* renamed from: b */
    private MainScanView f3825b;

    /* renamed from: c */
    private TextView wifiName;

    @SuppressLint("MissingInflatedId")
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scan);
        this.f3825b = (MainScanView) findViewById(R.id.dbv_custom);
        this.f3825b.m336a(new C0806r(this));
        this.handShakerCaptureManager = new HandShakerCaptureManager(this, this.f3825b);
        this.handShakerCaptureManager.m329a(getIntent(), bundle);
        this.handShakerCaptureManager.m331a();
        ((Button) findViewById(R.id.scan_quit)).setOnClickListener(new View$OnClickListenerC0807s(this));
        this.wifiName = (TextView) findViewById(R.id.wifi_name);
        EventBus.getDefault().register(this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();

        this.handShakerCaptureManager.resume();
        if (this.wifiName == null) {
            return;
        }
        this.wifiName.setText(getResources().getString(R.string.scan_wifi_name, NetWorkUtils.getWifiName(FolderApp.getInstance())));
    }



    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();

        this.handShakerCaptureManager.pause();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.handShakerCaptureManager.m319d();
        EventBus.getDefault().unregister(this);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.handShakerCaptureManager.m328a(bundle);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.handShakerCaptureManager.m330a(i, iArr);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.f3825b.onKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(ScanCancelEvent scanCancelEvent) {
        HandShaker.debug("QRCode, Cancel scan, Connection has created");
        finish();
    }
}
