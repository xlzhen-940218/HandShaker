package com.smartisanos.smartfolder.aoa.p056h;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p050b.SDCardRemovedEvent;
import com.smartisanos.smartfolder.aoa.p050b.UploadFileEvent;
import com.smartisanos.smartfolder.aoa.p050b.WakeLockEvent;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* renamed from: com.smartisanos.smartfolder.aoa.h.f */
/* loaded from: classes.dex */
public final class DeviceInfoHelper {

    /* renamed from: f */
    public DeviceInfo deviceInfo;

    /* renamed from: g */
    private HandlerThread f3690g;

    /* renamed from: h */
    private volatile MonitorStorageHandler f3691h;

    /* renamed from: i */
    private C0769g f3692i;

    /* renamed from: j */
    public Context context;

    /* renamed from: k */
    private C0763a f3694k;

    /* renamed from: l */
    private C0768f f3695l;

    /* renamed from: m */
    private C0767e f3696m;

    /* renamed from: n */
    private C0764b f3697n;

    /* renamed from: o */
    private boolean f3698o;

    /* renamed from: p */
    private StorageUtils.InterfaceC0759a f3699p = new C0770g(this);

    /* renamed from: e */
    private static DeviceInfoHelper deviceInfoHelper = new DeviceInfoHelper();

    /* renamed from: a */
    public static boolean needDeviceInfo = true;

    /* renamed from: b */
    public static boolean needImage = true;

    /* renamed from: c */
    public static boolean needVideo = true;

    /* renamed from: d */
    public static boolean f3687d = true;

    private DeviceInfoHelper() {
    }

    /* renamed from: a */
    public static DeviceInfoHelper getDeviceInfoHelper() {
        return deviceInfoHelper;
    }

    /* renamed from: b */
    public final synchronized void m470b() {
        if (this.deviceInfo == null) {
            this.context = FolderApp.getInstance();
            this.deviceInfo = new DeviceInfo(this, (byte) 0);
        }
        HandShaker.info("DeviceInfoHelper", "onConnect isStart = " + this.f3698o);
        if (!this.f3698o) {
            this.f3698o = true;
            this.f3690g = new HandlerThread("THREAD");
            this.f3690g.start();
            this.f3691h = new MonitorStorageHandler(this.f3690g.getLooper());
            this.f3692i = new C0769g(this.f3691h);
            this.context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("device_name"), true, this.f3692i);
            this.context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("bluetooth_name"), true, this.f3692i);
            if (Build.MANUFACTURER.contains("OPPO")) {
                this.context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("oppo_device_name"), true, this.f3692i);
            }
            if (Build.MANUFACTURER.contains("HUAWEI")) {
                this.context.getContentResolver().registerContentObserver(Settings.Global.getUriFor("unified_device_name"), true, this.f3692i);
            }
            this.f3694k = new C0763a(this, (byte) 0);
            this.f3696m = new C0767e(this, (byte) 0);
            this.f3697n = new C0764b(this, (byte) 0);
            this.f3695l = new C0768f(this, (byte) 0);
            Intent registerReceiver = this.context.registerReceiver(this.f3694k, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            this.deviceInfo.level = registerReceiver.getIntExtra("level", -1);
            new IntentFilter("action_keyguard_to_dismiss");
            this.context.registerReceiver(this.f3696m, new IntentFilter("android.intent.action.USER_PRESENT"));
            this.context.registerReceiver(this.f3697n, new IntentFilter("android.intent.action.CopyHistoryChange"));
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.MEDIA_SHARED");
            intentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
            intentFilter.addAction("android.intent.action.MEDIA_CHECKING");
            intentFilter.addAction("android.intent.action.MEDIA_EJECT");
            intentFilter.addAction("android.intent.action.MEDIA_REMOVED");
            intentFilter.addDataScheme("file");
            this.context.registerReceiver(this.f3695l, intentFilter);
            this.f3691h.m424b();
            EventBus.getDefault().register(this);
        }
    }

    @TargetApi(19)
    /* renamed from: c */
    public final synchronized void m468c() {
        HandShaker.info("DeviceInfoHelper", "onDisconnect isStart = " + this.f3698o);
        if (this.f3698o) {
            this.f3698o = false;
            this.context.getContentResolver().unregisterContentObserver(this.f3692i);
            this.context.unregisterReceiver(this.f3694k);
            this.context.unregisterReceiver(this.f3696m);
            this.context.unregisterReceiver(this.f3697n);
            this.context.unregisterReceiver(this.f3695l);
            EventBus.getDefault().unregister(this);
            this.f3690g.quitSafely();
            this.f3690g = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public void m462f() {
        this.deviceInfo.usedSpaceSize = CommonUtils.usedSpaceSize();
        this.deviceInfo.useSpace = CommonUtils.usedStorageSpace();
    }

    /* renamed from: d */
    public final DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    /* compiled from: DeviceInfoHelper.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.f$c */
    /* loaded from: classes.dex */


    /* compiled from: DeviceInfoHelper.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.f$a */
    /* loaded from: classes.dex */
    private class C0763a extends BroadcastReceiver {
        private C0763a() {
        }

        /* synthetic */ C0763a(DeviceInfoHelper deviceInfoHelper, byte b) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent != null) {
                int intExtra = intent.getIntExtra("level", 0);
                HandShaker.info("BatteryReceiver", "level=" + intExtra);
                if (DeviceInfoHelper.this.deviceInfo.level != intExtra) {
                    DeviceInfoHelper.this.deviceInfo.level = intExtra;
                    if (DeviceInfoHelper.needDeviceInfo) {
                        DeviceInfoHelper.this.notifyListener();
                    }
                }
            }
        }
    }

    /* compiled from: DeviceInfoHelper.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.f$g */
    /* loaded from: classes.dex */
    private class C0769g extends ContentObserver {
        public C0769g(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            super.onChange(z);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            super.onChange(z, uri);
            String m505b = CommonUtils.getDeviceName();
            if (!TextUtils.equals(m505b, DeviceInfoHelper.this.deviceInfo.deviceName)) {
                DeviceInfoHelper.this.deviceInfo.deviceName = m505b;
                if (DeviceInfoHelper.needDeviceInfo) {
                    DeviceInfoHelper.this.notifyListener();
                }
            }
        }
    }

    /* compiled from: DeviceInfoHelper.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.f$e */
    /* loaded from: classes.dex */
    private class C0767e extends BroadcastReceiver {
        private C0767e() {
        }

        /* synthetic */ C0767e(DeviceInfoHelper deviceInfoHelper, byte b) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            HandShaker.debug("DeviceInfoHelper", "KeyguardReceiver " + intent.getAction());
            if (intent.getAction().equals("action_keyguard_to_dismiss") || intent.getAction().equals("android.intent.action.USER_PRESENT")) {
                EventBus.getDefault().post(new WakeLockEvent());
                HandShaker.debug("DeviceInfoHelper", "post WakeLockEvent");
            }
        }
    }

    /* compiled from: DeviceInfoHelper.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.f$f */
    /* loaded from: classes.dex */
    private class C0768f extends BroadcastReceiver {
        private C0768f() {
        }

        /* synthetic */ C0768f(DeviceInfoHelper deviceInfoHelper, byte b) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            HandShaker.debug("DeviceInfoHelper", "SdcardReceiver " + intent.getAction());
            String m483s = CommonUtils.getStoragePath();
            HandShaker.debug("DeviceInfoHelper", "SdcardReceiver sdcardPath" + m483s);
            if (m483s == null) {
                m483s = "";
            }
            if (!TextUtils.equals(m483s, DeviceInfoHelper.this.deviceInfo.externalStoragePath)) {
                if (TextUtils.isEmpty(m483s)) {
                    EventBus.getDefault().post(new SDCardRemovedEvent());
                }
                DeviceInfoHelper.this.deviceInfo.sdTotalSize = CommonUtils.getExtSdTotalSize();
                DeviceInfoHelper.this.deviceInfo.setExternalStoragePath(m483s);
                DeviceInfoHelper.this.notifyListener();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeviceInfoHelper.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.f$b */
    /* loaded from: classes.dex */
    public class C0764b extends BroadcastReceiver {
        private C0764b() {
        }

        /* synthetic */ C0764b(DeviceInfoHelper deviceInfoHelper, byte b) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.CopyHistoryChange")) {
                HandShaker.info("DeviceInfoHelper", "ACTION_CLIPBOARD_COPY_HISTORY");
                DeviceInfoHelper.this.f3691h.post(new RunnableC0772i(this));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: g */
    public static ArrayList<SmartSyncProtocolProtos.SSPClipboard> m460g() throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        List list = null;
        int i = 0;
        HandShaker.info("DeviceInfoHelper", "getClipboardHistory()");
        ArrayList<SmartSyncProtocolProtos.SSPClipboard> arrayList = new ArrayList<>();
        try {
            list = (List) Class.forName("android.content.ClipboardManager").getMethod("getCopyHistory", new Class[0]).invoke(Class.forName("android.content.ClipboardManager").getConstructor(Context.class, Handler.class).newInstance(FolderApp.getInstance(), new Handler()), new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.size() == 0) {
            return null;
        }
        while (true) {
            int i2 = i;
            if (i2 >= list.size()) {
                break;
            }
            Object obj = list.get(i2);
            Class<?> cls = Class.forName("android.content.CopyHistoryItem");
            Field declaredField = cls.getDeclaredField("mContent");
            Field declaredField2 = cls.getDeclaredField("mTimeStamp");
            SmartSyncProtocolProtos.SSPClipboard.Builder m883n = SmartSyncProtocolProtos.SSPClipboard.newBuilder();
            m883n.setContent(CommonUtils.compressToByteStringByGzip((String) declaredField.get(obj)));
            m883n.setMstimestamp(((Long) declaredField2.get(obj)).longValue());
            arrayList.add(m883n.build());
            i = i2 + 1;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: h */
    public void notifyListener() {
        HandShaker.debug("DeviceInfoHelper", "notifyListener: " + needDeviceInfo);
        this.f3691h.post(new RunnableC0771h(this));
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public final void onMessageEvent(UploadFileEvent uploadFileEvent) {
        this.f3691h.m425a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: DeviceInfoHelper.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.f$d */
    /* loaded from: classes.dex */
    public class MonitorStorageHandler extends Handler {

        /* renamed from: b */
        private final long f3728b;

        /* renamed from: c */
        private final long f3729c;

        /* renamed from: d */
        private final long f3730d;

        /* renamed from: e */
        private long f3731e;

        public MonitorStorageHandler(Looper looper) {
            super(looper);
            this.f3728b = 60000L;
            this.f3729c = 10000L;
            this.f3730d = 300000L;
            this.f3731e = 60000L;
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    DeviceInfoHelper.m461f(DeviceInfoHelper.this);
                    sendEmptyMessageDelayed(1, this.f3731e);
                    return;
                case 2:
                    HandShaker.debug("DeviceInfoHelper", "reset check storage internal as normal");
                    this.f3731e = 60000L;
                    return;
                case 3:
                    DeviceInfoHelper.m459g(DeviceInfoHelper.this);
                    sendEmptyMessageDelayed(3, 300000L);
                    return;
                default:
                    return;
            }
        }

        /* renamed from: a */
        public final void m425a() {
            if (hasMessages(2)) {
                removeMessages(2);
            }
            sendEmptyMessageDelayed(2, 10000L);
            if (this.f3731e == 60000) {
                HandShaker.debug("DeviceInfoHelper", "check storage internal as urgent type");
                this.f3731e = 10000L;
                m424b();
            }
        }

        /* renamed from: b */
        public final void m424b() {
            HandShaker.debug("DeviceInfoHelper", "startMonitorStorage");
            removeMessages(1);
            removeMessages(3);
            sendEmptyMessageDelayed(1, this.f3731e);
            sendEmptyMessageDelayed(3, 300000L);
        }
    }

    /* renamed from: f */
    static /* synthetic */ void m461f(DeviceInfoHelper deviceInfoHelper) {
        long m436i = deviceInfoHelper.deviceInfo.getUsedSpaceSize();
        long m435j = deviceInfoHelper.deviceInfo.getUseSpace();
        deviceInfoHelper.m462f();
        long abs = Math.abs(m436i - deviceInfoHelper.deviceInfo.usedSpaceSize);
        long abs2 = Math.abs(m435j - deviceInfoHelper.deviceInfo.useSpace);
        if (abs <= 104857600 && abs2 <= 104857600) {
            return;
        }
        HandShaker.debug("DeviceInfoHelper", "checkStorageSpace need refresh");
        deviceInfoHelper.notifyListener();
    }

    /* renamed from: g */
    static /* synthetic */ void m459g(DeviceInfoHelper deviceInfoHelper) {
        HandShaker.debug("DeviceInfoHelper", "refreshStorageState start");
        deviceInfoHelper.deviceInfo.audioSize = StorageUtils.getAudioSize(deviceInfoHelper.context);
        deviceInfoHelper.deviceInfo.pictureVideoSize = StorageUtils.m533a(Environment.DIRECTORY_DCIM, Environment.DIRECTORY_MOVIES, Environment.DIRECTORY_PICTURES);
        deviceInfoHelper.deviceInfo.downloadSize = StorageUtils.m533a(Environment.DIRECTORY_DOWNLOADS);
        StorageUtils.m536a(deviceInfoHelper.context, deviceInfoHelper.f3699p);
        HandShaker.debug("DeviceInfoHelper", "refreshStorageState end");
    }
}
