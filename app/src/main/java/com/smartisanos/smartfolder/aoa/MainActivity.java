package com.smartisanos.smartfolder.aoa;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.Settings;
import android.text.TextUtils;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.smartisan.trackerlib.Agent;
import com.smartisanos.smartfolder.aoa.activity.ConnecttedFragment;
import com.smartisanos.smartfolder.aoa.activity.ConnecttingFragment;
import com.smartisanos.smartfolder.aoa.activity.WarningFragment;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.aoa.p050b.QuitEvent;
import com.smartisanos.smartfolder.aoa.p050b.ScanCancelEvent;
import com.smartisanos.smartfolder.aoa.p050b.StartWifiServerEvent;
import com.smartisanos.smartfolder.aoa.p050b.TrustCancelEvent;
import com.smartisanos.smartfolder.aoa.p050b.TrustRequestEvent;
import com.smartisanos.smartfolder.aoa.p055g.SSPEventDispatcher;
import com.smartisanos.smartfolder.aoa.p055g.SSPEventListener;
import com.smartisanos.smartfolder.aoa.p056h.ActivityLifecycleManager;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.aoa.p056h.DeviceInfoHelper;
import com.smartisanos.smartfolder.aoa.p056h.DialogUtils;
import com.smartisanos.smartfolder.aoa.p056h.SharedPreferenceHelper;
import com.smartisanos.smartfolder.aoa.service.ConnectionManagerService;
import com.smartisanos.smartfolder.aoa.service.UiDialogService;
import com.smartisanos.smartfolder.aoa.view.CustomDialogBuilder;
import com.smartisanos.smartfolder.aoa.view.CustomProgressBar;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class MainActivity extends Activity implements ConnecttedFragment.InterfaceC0708a, SSPEventListener {

    /* renamed from: e */
    private ConnecttingFragment f3388e;

    /* renamed from: f */
    private ConnecttedFragment f3389f;

    /* renamed from: g */
    private WarningFragment f3390g;

    /* renamed from: h */
    public ConnectionManagerService f3391h;

    /* renamed from: i */
    private Intent f3392i;

    /* renamed from: j */
    private boolean f3393j;

    /* renamed from: k */
    private AlertDialog f3394k;

    /* renamed from: l */
    public AlertDialog f3395l;

    /* renamed from: m */
    private AlertDialog f3396m;

    /* renamed from: n */
    private CustomProgressBar f3397n;

    /* renamed from: o */
    private C0702a f3398o;

    /* renamed from: q */
    public HandlerC0703b f3400q;

    /* renamed from: a */
    private final String f3384a = "MainActivity";

    /* renamed from: b */
    private final int f3385b = 1001;

    /* renamed from: c */
    private final int f3386c = 1;

    /* renamed from: d */
    private final int f3387d = 2;

    /* renamed from: p */
    private boolean f3399p = true;

    /* renamed from: r */
    private ServiceConnection f3401r = new ServiceConnectionC0704a(this);

    /* renamed from: s */
    public ConnectionManagerService.InterfaceC0810c f3402s = new C0720b(this);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static /* synthetic */ boolean m816b(MainActivity mainActivity) {
        mainActivity.f3393j = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: g */
    public static /* synthetic */ C0702a m809g(MainActivity mainActivity) {
        mainActivity.f3398o = null;
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.smartisanos.smartfolder.aoa.MainActivity$b */
    /* loaded from: classes.dex */
    public class HandlerC0703b extends Handler {
        private HandlerC0703b() {
        }

        /* synthetic */ HandlerC0703b(MainActivity mainActivity, byte b) {
            this();
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    HandShaker.debug("MainActivity", "MESSAGE_TRUST_TIMEOUT");
                    EventBus.getDefault().post(new TrustCancelEvent());
                    return;
                case 2:
                    MainActivity.this.m802k();
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: com.smartisanos.smartfolder.aoa.MainActivity$a */
    /* loaded from: classes.dex */
    public static class C0702a {

        /* renamed from: a */
        private String f3403a;

        /* renamed from: b */
        private String f3404b;

        public C0702a(String str, String str2) {
            this.f3403a = str2;
            this.f3404b = str;
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        HandShaker.info("MainActivity", "onCreate");
        super.onCreate(bundle);
        if (!(Settings.Global.getInt(getContentResolver(), "device_provisioned", 0) != 0)) {
            finish();
            return;
        }
        CommonUtils.getDeviceName();
        try {
            Agent.m1952a().m1944c();
        } catch (Exception e) {
            HandShaker.error("Tracker", "onLaunch exception:" + e.toString());
        }
        this.f3400q = new HandlerC0703b(this, (byte) 0);
        if (SharedPreferenceHelper.isFirstLaunch()) {
            SharedPreferenceHelper.firstLaunch();
            try {
                Agent.m1952a().m1943d();
            } catch (Exception e2) {
                HandShaker.error("Tracker", "onNewUser exception:" + e2.toString());
            }
        }
        this.f3392i = new Intent(this, ConnectionManagerService.class);
        m810g();
        DeviceInfoHelper.getDeviceInfoHelper().m470b();
        EventManager.m796b();
        FolderApp.getInstance().startService(new Intent(FolderApp.getInstance(), UiDialogService.class));
        setContentView(R.layout.activity_main);
        m817b();
        SSPEventDispatcher.m596a().m593a(this);
        EventBus.getDefault().register(this);
        VersionUpdater.m557a(this, false, null);
    }

    /* renamed from: g */
    private void m810g() {
        Intent intent = getIntent();
        HandShaker.debug("MainActivity", "handleIntent, intent = " + intent);
        if (TextUtils.equals(intent.getAction(), "android.hardware.usb.action.USB_ACCESSORY_ATTACHED")) {
            this.f3392i.putExtra("accessory", (Bundle) intent.getParcelableExtra("accessory"));
        } else {
            this.f3392i.removeExtra("accessory");
        }
        startService(this.f3392i);
        bindService(this.f3392i, this.f3401r, 64);
    }

    /* renamed from: a */
    public final void m820a() {
        this.f3399p = true;
        m817b();
    }

    /* renamed from: b */
    public final void m817b() {
        if (this.f3399p && !isDestroyed()) {
            if (this.f3388e == null) {
                this.f3388e = new ConnecttingFragment();
            }
            if (!this.f3388e.isVisible()) {
                getFragmentManager().beginTransaction().replace(R.id.container, this.f3388e).commitAllowingStateLoss();
            }
            EventBus.getDefault().post(new StartWifiServerEvent());
        }
    }

    /* renamed from: h */
    private void m808h() {
        if (this.f3391h != null) {
            this.f3391h.m301a();
        }
    }

    /* renamed from: c */
    public final void m815c() {
        if (this.f3399p && !isDestroyed()) {
            EventBus.getDefault().post(new ScanCancelEvent());
            if (this.f3389f == null) {
                this.f3389f = new ConnecttedFragment();
            }
            if (!this.f3389f.isVisible()) {
                getFragmentManager().beginTransaction().replace(R.id.container, this.f3389f).commitAllowingStateLoss();
            } else {
                this.f3389f.m785a();
            }
        }
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancel(1001);
        }
        EventBus.getDefault().post(new StartWifiServerEvent());
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        HandShaker.debug("MainActivity", "onNewIntent");
        setIntent(intent);
        m810g();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.f3389f == null || !this.f3389f.isVisible()) {
            super.onBackPressed();
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        intent.addCategory("android.intent.category.HOME");
        startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        Agent.m1952a().m1942e();
        if (ActivityLifecycleManager.getInstance().activitiesIsEmpty()) {
            m808h();
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HandShaker.info("MainActivity", "onDestroy");
        if (this.f3393j) {
            this.f3391h.m300a((ConnectionManagerService.InterfaceC0810c) null);
            unbindService(this.f3401r);
            this.f3393j = false;
        }
        if (this.f3400q != null) {
            this.f3400q.removeCallbacksAndMessages(null);
        }
        m802k();
        if (this.f3396m != null && this.f3396m.isShowing()) {
            this.f3396m.dismiss();
        }
        SSPEventDispatcher.m596a().m592b(this);
        EventBus.getDefault().unregister(this);
    }

    @Override // com.smartisanos.smartfolder.aoa.activity.ConnecttedFragment.InterfaceC0708a
    /* renamed from: d */
    public final void mo783d() {
        EventBus.getDefault().post(new QuitEvent());
        m817b();
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.SSPEventListener
    /* renamed from: e */
    public final void mo589e() {
        HandShaker.debug("MainActivity", "onCheckUpdate");
        VersionUpdater.m557a(this, false, null);
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.SSPEventListener
    /* renamed from: a */
    public final void mo590a(String str, String str2) {
        this.f3398o = new C0702a(str, str2);
        if (!ActivityLifecycleManager.getInstance().activitiesIsEmpty()) {
            m806i();
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.SSPEventListener
    /* renamed from: a */
    public final void mo591a(int i, int i2, String str) {
        boolean z = true;
        int m504b = CommonUtils.getClientMinHostVersionCode(i);
        this.f3399p = i2 >= m504b;
        if (i2 == 0 && i == 1) {
            if (CommonUtils.m507a(str, "2.1.0") < 0 || CommonUtils.m507a(str, "2.5.0") >= 0) {
                z = false;
            }
            this.f3399p = z;
        }
        HandShaker.info("MainActivity", "onHostVersionCheck host type: " + i + " host version: " + i2 + ", minVersion: " + m504b + ", valid = " + this.f3399p + ", version str:" + str);
        if (!this.f3399p) {
            HandShaker.debug("MainActivity", "host version too low");
            EventBus.getDefault().post(new QuitEvent(false));
            m808h();
            if (isDestroyed()) {
                return;
            }
            if (this.f3390g == null) {
                this.f3390g = new WarningFragment();
            }
            if (this.f3390g.isVisible()) {
                return;
            }
            getFragmentManager().beginTransaction().replace(R.id.container, this.f3390g).commitAllowingStateLoss();
            return;
        }
        m802k();
        m815c();
    }

    /* renamed from: i */
    private void m806i() {
        if (this.f3395l == null || !this.f3395l.isShowing()) {
            View$OnClickListenerC0721c view$OnClickListenerC0721c = new View$OnClickListenerC0721c(this);
            View$OnClickListenerC0722d view$OnClickListenerC0722d = new View$OnClickListenerC0722d(this);
            String string = getResources().getString(R.string.permission_description);
            CustomDialogBuilder m209a = CustomDialogBuilder.m209a(this);
            m209a.setTitle(R.string.permission_title);
            m209a.setMessage(string);
            m209a.m212a();
            m209a.m202b(17039360, view$OnClickListenerC0721c);
            m209a.m210a(17039379, view$OnClickListenerC0722d);
            AlertDialog create = m209a.create();
            create.setCanceledOnTouchOutside(false);
            this.f3395l = create;
            this.f3395l.show();
        }
    }

    @SuppressLint("WrongConstant")
    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        IntentResult intentResult;
        if (i == 33) {
            if (i2 == -1 && Build.VERSION.SDK_INT >= 21 && this.f3398o != null) {
                String str = this.f3398o.f3403a;
                if (!TextUtils.isEmpty(str)) {
                    Uri data = intent.getData();
                    if (str.equals(DocumentsContract.getTreeDocumentId(data))) {
                        int flags = intent.getFlags() & 3;
                        if (data != null) {
                            getContentResolver().takePersistableUriPermission(data, flags);
                        }
                        Toast.makeText(getApplicationContext(), (int) R.string.grant_permission_success, Toast.LENGTH_SHORT).show();
                        this.f3398o = null;
                        return;
                    }
                    m806i();
                }
            }
        } else if (i == IntentIntegrator.REQUEST_CODE && (intentResult = IntentIntegrator.parseActivityResult(i, i2, intent)) != null) {
            String contents = intentResult.getContents();
            HandShaker.info("MainActivity", "QRCode, activity, get content:" + contents);
            if (CommonUtils.checkQRData(contents) && this.f3391h != null) {
                this.f3391h.m298a(contents, new C0741f(this));
            } else {
                HandShaker.error("QRCode, activity, Invalid data:" + contents);
            }
        }
    }

    @Override // com.smartisanos.smartfolder.aoa.activity.ConnecttedFragment.InterfaceC0708a
    /* renamed from: f */
    public final boolean mo782f() {
        return this.f3391h != null && this.f3391h.m297b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: j */
    public void m804j() {
        if (this.f3394k != null) {
            if (this.f3394k.isShowing()) {
                this.f3394k.dismiss();
            }
            this.f3394k = null;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TrustRequestEvent trustRequestEvent) {
        if (HandShaker.LOG) {
            HandShaker.debug("MainActivity", "TrustRequestEvent");
        }
        m802k();
        EventBus.getDefault().post(new ScanCancelEvent());
        m804j();
        this.f3394k = DialogUtils.m421a(this, trustRequestEvent);
        this.f3394k.setOnDismissListener(new DialogInterface$OnDismissListenerC0738e(this));
        this.f3394k.show();
        this.f3400q.sendEmptyMessageDelayed(1, 30000L);
        if (!ActivityLifecycleManager.getInstance().activitiesIsEmpty()) {
            return;
        }
        PendingIntent activity = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_IMMUTABLE);
        String string = getResources().getString(R.string.notification_trust_title);
        Notification build = new Notification.Builder(this).setSmallIcon(R.drawable.ic_launcher).setTicker(string).setWhen(System.currentTimeMillis()).setContentIntent(activity).setContentTitle(string).setContentText(getResources().getString(R.string.notification_trust_message)).build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager == null) {
            return;
        }
        notificationManager.notify(1001, build);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(TrustCancelEvent trustCancelEvent) {
        if (HandShaker.LOG) {
            HandShaker.debug("MainActivity", "TrustCancelEvent");
        }
        m804j();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: k */
    public void m802k() {
        if (this.f3397n != null) {
            this.f3397n.m200b();
            HandShaker.debug("QRCode, activity, progress dialog dismiss");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: i */
    public static /* synthetic */ void m805i(MainActivity mainActivity) {
        if (mainActivity.f3397n == null) {
            mainActivity.f3397n = new CustomProgressBar(mainActivity);
        }
        mainActivity.f3397n.m201a();
        if (mainActivity.f3400q.hasMessages(2)) {
            mainActivity.f3400q.removeMessages(2);
        }
        mainActivity.f3400q.sendEmptyMessageDelayed(2, 15000L);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j */
    public static /* synthetic */ void m803j(MainActivity mainActivity) {
        if (mainActivity.f3396m == null) {
            mainActivity.f3396m = DialogUtils.m422a(mainActivity);
        }
        mainActivity.f3396m.show();
    }
}
