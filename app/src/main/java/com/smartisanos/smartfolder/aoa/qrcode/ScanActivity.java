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
    private HandShakerCaptureManager f3824a;

    /* renamed from: b */
    private MainScanView f3825b;

    /* renamed from: c */
    private TextView f3826c;

    @SuppressLint("MissingInflatedId")
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_scan);
        this.f3825b = (MainScanView) findViewById(R.id.dbv_custom);
        this.f3825b.m336a(new C0806r(this));
        this.f3824a = new HandShakerCaptureManager(this, this.f3825b);
        this.f3824a.m329a(getIntent(), bundle);
        this.f3824a.m331a();
        ((Button) findViewById(R.id.scan_quit)).setOnClickListener(new View$OnClickListenerC0807s(this));
        this.f3826c = (TextView) findViewById(R.id.wifi_name);
        EventBus.getDefault().register(this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.f3824a.m325b();
        if (this.f3826c == null) {
            return;
        }
        this.f3826c.setText(getResources().getString(R.string.scan_wifi_name, NetWorkUtils.m378b(FolderApp.getInstance())));
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        this.f3824a.m322c();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.f3824a.m319d();
        EventBus.getDefault().unregister(this);
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.f3824a.m328a(bundle);
    }

    @Override // android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.f3824a.m330a(i, iArr);
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
