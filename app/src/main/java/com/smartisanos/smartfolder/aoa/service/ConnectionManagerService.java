package com.smartisanos.smartfolder.aoa.service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbAccessory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.TextUtils;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.MainActivity;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.aoa.p050b.QuitEvent;
import com.smartisanos.smartfolder.aoa.p050b.StartWifiServerEvent;
import com.smartisanos.smartfolder.aoa.p055g.ConnectionManager;
import com.smartisanos.smartfolder.aoa.p056h.ActivityLifecycleManager;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.DeviceInfoHelper;
import com.smartisanos.smartfolder.aoa.p056h.ToastHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* loaded from: classes.dex */
public class ConnectionManagerService extends Service {

    /* renamed from: a */
    public static final String TAG = ConnectionManagerService.class.getSimpleName();

    /* renamed from: c */
    public InterfaceC0810c f3862c;

    /* renamed from: d */
    public UsbConnectionManager usbConnectionManager;

    /* renamed from: e */
    public WifiConnectionManager wifiConnectionManager;

    /* renamed from: f */
    public Handler f3865f;

    /* renamed from: g */
    private boolean f3866g;

    /* renamed from: h */
    public PowerManager.WakeLock wakeLock;

    /* renamed from: b */
    private BinderC0809b f3861b = new BinderC0809b();

    /* renamed from: i */
    private ConnectionManager.InterfaceC0750a f3868i = new C0815b(this);

    /* renamed from: j */
    private ConnectionManager.InterfaceC0750a f3869j = new C0816c(this);

    /* renamed from: k */
    public BroadcastReceiver f3870k = new C0817d(this);

    /* renamed from: l */
    private BroadcastReceiver f3871l = new C0818e(this);

    /* renamed from: com.smartisanos.smartfolder.aoa.service.ConnectionManagerService$c */
    /* loaded from: classes.dex */
    public interface InterfaceC0810c {
        /* renamed from: a */
        void mo281a();

        /* renamed from: b */
        void mo280b();

        /* renamed from: c */
        void mo279c();

        /* renamed from: d */
        void mo278d();
    }

    /* renamed from: com.smartisanos.smartfolder.aoa.service.ConnectionManagerService$b */
    /* loaded from: classes.dex */
    public class BinderC0809b extends Binder {
        public BinderC0809b() {
        }

        /* renamed from: a */
        public final ConnectionManagerService m282a() {
            return ConnectionManagerService.this;
        }
    }

    @SuppressLint("InvalidWakeLockTag")
    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        HandShaker.debug(TAG, "onCreate");
        DeviceInfoHelper.getDeviceInfoHelper().m470b();
        EventManager.m796b();
        ToastHelper.m527a();
        this.usbConnectionManager = new UsbConnectionManager(this, this.f3868i);
        this.wifiConnectionManager = new WifiConnectionManager(this, this.f3869j);
        this.f3865f = new Handler(getMainLooper());
        EventBus.getDefault().register(this);
        this.wakeLock = ((PowerManager) FolderApp.getInstance().getSystemService(Context.POWER_SERVICE))
                .newWakeLock(536870913, "HandShaker");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        HandShaker.debug(TAG, "onBind, isWifiOn: " + m289f());
        EventBus.getDefault().post(new StartWifiServerEvent());
        return this.f3861b;
    }

    @Override // android.app.Service
    public void onRebind(Intent intent) {
        HandShaker.debug(TAG, "onRebind");
        EventBus.getDefault().post(new StartWifiServerEvent());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        UsbAccessory usbAccessory = (UsbAccessory) intent.getParcelableExtra("accessory");
        HandShaker.debug(TAG, "onStartCommand, accessory = " + usbAccessory);
        if (usbAccessory != null) {
            this.usbConnectionManager.m254a(usbAccessory);
            registerReceiver(this.f3870k, new IntentFilter("android.hardware.usb.action.USB_ACCESSORY_DETACHED"));
            HandShaker.debug(TAG, "openAccessory over register usbReceiver");
            return Service.START_NOT_STICKY;
        }
        Bundle extras = intent.getExtras();
        if (extras != null) {
            int i3 = extras.getInt("ADB_PORT");
            this.usbConnectionManager.m255a(i3);
            HandShaker.debug(TAG, "openAdb  port : " + i3);
            return Service.START_NOT_STICKY;
        }
        return Service.START_NOT_STICKY;
    }

    @Override // android.app.Service
    public boolean onUnbind(Intent intent) {
        HandShaker.debug(TAG, "onUnbind, thread id = " + Thread.currentThread().getId());
        if (!m291e()) {
            this.wifiConnectionManager.m236d();
            if (this.f3866g) {
                this.f3866g = false;
                unregisterReceiver(this.f3871l);
            }
            DeviceInfoHelper.getDeviceInfoHelper().m468c();
            return true;
        }
        return true;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(StartWifiServerEvent startWifiServerEvent) {
        if (!m291e() && !this.usbConnectionManager.m248c() && !ActivityLifecycleManager.getInstance().activitiesIsEmpty()) {
            HandShaker.debug(TAG, "tryStartWifiServer ");
            if (m289f()) {
                this.wifiConnectionManager.m238c();
            }
            this.f3866g = true;
            registerReceiver(this.f3871l, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    /* renamed from: a */
    public final void m301a() {
        if (HandShaker.LOG) {
            HandShaker.debug(TAG, "stopWifiServer wifiConnected: " + this.wifiConnectionManager.m597g());
        }
        this.wifiConnectionManager.m236d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public boolean m291e() {
        return this.wifiConnectionManager.m597g() || this.usbConnectionManager.m597g();
    }

    /* renamed from: a */
    public final void m300a(InterfaceC0810c interfaceC0810c) {
        this.f3862c = interfaceC0810c;
    }

    /* renamed from: b */
    public final boolean m297b() {
        return this.usbConnectionManager != null && this.usbConnectionManager.m597g();
    }

    /* renamed from: c */
    public final boolean m295c() {
        return this.wifiConnectionManager.m597g();
    }

    /* renamed from: a */
    public final void m298a(String str, WifiConnectionManager.InterfaceC0828a interfaceC0828a) {
        if (!TextUtils.isEmpty(str) && this.wifiConnectionManager != null) {
            this.wifiConnectionManager.m241a(str, interfaceC0828a);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public boolean m289f() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEvent(QuitEvent quitEvent) {
        if (m297b()) {
            this.usbConnectionManager.m601a(quitEvent.m767a());
        } else if (this.wifiConnectionManager.m597g()) {
            this.wifiConnectionManager.m601a(quitEvent.m767a());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.ConnectionManagerService$a */
    /* loaded from: classes.dex */


    /* renamed from: g */
    static /* synthetic */ void m287g(ConnectionManagerService connectionManagerService) {
        String string;
        if (connectionManagerService.m297b()) {
            string = connectionManagerService.getString(R.string.notification_connected_usb);
        } else {
            string = connectionManagerService.getString(R.string.notification_connected_wifi);
        }
        String string2 = connectionManagerService.getString(R.string.notification_connected_text);
        PendingIntent activity = PendingIntent.getActivity(connectionManagerService, 0, new Intent(connectionManagerService, MainActivity.class), PendingIntent.FLAG_IMMUTABLE);
        Notification.Builder builder = new Notification.Builder(connectionManagerService);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setWhen(0L);
        builder.setOngoing(true);
        builder.setTicker(string);
        builder.setDefaults(0);
        builder.setSound(null);
        builder.setVibrate(null);
        builder.setContentTitle(string);
        builder.setContentText(string2);
        builder.setContentIntent(activity);
        connectionManagerService.startForeground(1, builder.build());
    }
}
