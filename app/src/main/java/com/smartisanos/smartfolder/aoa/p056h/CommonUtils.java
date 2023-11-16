package com.smartisanos.smartfolder.aoa.p056h;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.KeyguardManager;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.Settings;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.smartisanos.smartfolder.aoa.FolderApp;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.protocol.HttpRequestExecutor;

/* renamed from: com.smartisanos.smartfolder.aoa.h.d */
/* loaded from: classes.dex */
public final class CommonUtils {

    /* renamed from: b */
    private static Method f3675b;

    /* renamed from: a */
    public static final boolean isSmartISan = TextUtils.equals("smartisan", getProductBrand());

    /* renamed from: c */
    private static final Object f3676c = new Object();

    /* renamed from: d */
    private static boolean f3677d = false;

    /* renamed from: a */
    public static String getProductName() {
        return getProp("ro.product.name");
    }

    /* renamed from: b */
    public static String getDeviceName() {
        String str = Build.MANUFACTURER;
        ContentResolver contentResolver = FolderApp.getInstance().getContentResolver();
        String string = Settings.Global.getString(contentResolver, "device_name");
        if (str.contains("OPPO")) {
            string = Settings.Secure.getString(contentResolver, "oppo_device_name");
        } else if (str.contains("HUAWEI")) {
            string = Settings.Global.getString(contentResolver, "unified_device_name");
        }
        if (TextUtils.isEmpty(string)) {
            string = Settings.Secure.getString(contentResolver, "bluetooth_name");
        }
        if (TextUtils.isEmpty(string)) {
            string = Build.MODEL;
        }
        if (string == null) {
            return "";
        }
        return string;
    }

    @TargetApi(19)
    /* renamed from: c */
    public static long getDeviceSpace() {
        if (hasStorage()) {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
        }
        return -1L;
    }

    @TargetApi(19)
    /* renamed from: d */
    public static long getExtSdTotalSize() {
        if (hasStoragePath()) {
            try {
                StatFs statFs = new StatFs(getStoragePath());
                return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
            } catch (Exception e) {
                e.printStackTrace();
                HandShaker.error("CommonUtils", "getExtSdTotalSize exception: " + e);
            }
        }
        return 0L;
    }

    /* renamed from: x */
    private static boolean hasStorage() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    /* renamed from: y */
    private static boolean hasStoragePath() {
        return !TextUtils.isEmpty(getStoragePath());
    }

    @TargetApi(19)
    /* renamed from: z */
    private static long getExtSdAvailableSize() {
        if (hasStoragePath()) {
            try {
                StatFs statFs = new StatFs(getStoragePath());
                return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            } catch (Exception e) {
                e.printStackTrace();
                HandShaker.error("CommonUtils", "getExtSdAvailableSize exception: " + e);
            }
        }
        return 0L;
    }

    /* renamed from: e */
    public static long getMemoryInfo() {
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) FolderApp.getInstance().getSystemService(Context.ACTIVITY_SERVICE)).getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }

    /* renamed from: a */
    public static String getProp(String str) {
        try {
            return String.valueOf(Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: f */
    public static long usedSpaceSize() {
        long availableSpace = -1;
        if (hasStorage()) {
            long deviceSpace = getDeviceSpace();
            if (hasStorage()) {
                StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
                availableSpace = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            }
            return deviceSpace - availableSpace;
        }
        return -1L;
    }

    /* renamed from: g */
    public static long usedStorageSpace() {
        if (hasStoragePath()) {
            return getExtSdTotalSize() - getExtSdAvailableSize();
        }
        return 0L;
    }

    /* renamed from: h */
    public static String getStorageFullPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /* renamed from: i */
    public static String getVersionCode() {
        try {
            return String.valueOf(FolderApp.getInstance().getPackageManager().getPackageInfo(FolderApp.getInstance().getPackageName(), 0).versionCode);
        } catch (Exception e) {
            HandShaker.error("VersionInfo, Exception:" + e.toString());
            return "";
        }
    }

    /* renamed from: j */
    public static String getVersionName() {
        try {
            PackageInfo packageInfo = FolderApp.getInstance().getPackageManager().getPackageInfo(FolderApp.getInstance().getPackageName(), 0);
            if (packageInfo == null) {
                return "1.2.0";
            }
            return packageInfo.versionName;
        } catch (Exception e) {
            HandShaker.error("GetVersionName, Exception:" + e.toString());
            return "1.2.0";
        }
    }

    /* renamed from: k */
    public static int getCameraFolderHashCode() {
        return (Environment.getExternalStorageDirectory().toString() + "/DCIM/Camera").toLowerCase().hashCode();
    }

    /* renamed from: l */
    public static boolean isKeyguardLocked() {
        return ((KeyguardManager) FolderApp.getInstance().getSystemService(Context.KEYGUARD_SERVICE)).isKeyguardLocked();
    }

    /* renamed from: A */
    private static String getProductBrand() {
        String m508a = getProp("ro.product.brand");
        if (m508a == null) {
            m508a = "";
        }
        return m508a.toLowerCase();
    }

    /* renamed from: m */
    public static boolean isSmartisanPhone() {
        HandShaker.debug("isSmartisanPhone brand: " + getProductBrand());
        return isSmartISan;
    }

    /* renamed from: n */
    public static boolean isVivo() {
        return Build.BRAND.startsWith("vivo");
    }

    /* renamed from: p */
    public static String getSmartISanVersion() {
        try {
            try {
                String str = (String) Build.VERSION.class.getField("SMARTISAN_RELEASE").get(null);
                if (!TextUtils.isEmpty(str) && str.contains("-")) {
                    return str.substring(0, str.indexOf("-"));
                }
                return str;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        return "";
    }

    /* renamed from: q */
    public static boolean m485q() {
        try {
            String m486p = getSmartISanVersion();
            String[] split = "4.1.0".split("\\.");
            String[] split2 = m486p.split("\\.");
            for (int i = 0; i < split.length; i++) {
                String str = split[i];
                if (split2.length <= i) {
                    return false;
                }
                int intValue = Integer.valueOf(split2[i]).intValue();
                int intValue2 = Integer.valueOf(str).intValue();
                if (intValue != intValue2) {
                    return intValue > intValue2;
                }
            }
            if (split2.length > split.length) {
                for (int length = split.length; length < split2.length && Integer.valueOf(split2[length]).intValue() <= 0; length++) {
                }
                return true;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: r */
    public static boolean m484r() {
        return isSmartisanPhone() && FolderApp.f3379c;
    }

    @TargetApi(19)
    /* renamed from: s */
    public static String getStoragePath() {
        if (Build.VERSION.SDK_INT < 19) {
            return "";
        }
        String str = "";
        try {
            for (Object obj : (Object[]) StorageManager.class.getMethod("getVolumeList"
                    , new Class[0]).invoke((StorageManager) FolderApp.getInstance().getSystemService(Context.STORAGE_SERVICE), new Object[0])) {
                StorageVolumeWrap storageVolumeWrap = new StorageVolumeWrap(obj);
                if (storageVolumeWrap.isMounted() && !storageVolumeWrap.isPrimary() && !TextUtils.isEmpty(storageVolumeWrap.getUUID())) {
                    str = storageVolumeWrap.getPath().getAbsolutePath();
                }
            }
            return str;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return str;
        } catch (NoSuchMethodException e2) {
            e2.printStackTrace();
            return str;
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return str;
        }
    }

    /* renamed from: e */
    private static byte[] compressByGzip(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes());
            gZIPOutputStream.close();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
            return byteArray;
        } catch (Exception e) {
            HandShaker.error("compressByGzip fail by exception: " + e);
            return null;
        }
    }

    /* renamed from: b */
    public static ByteString compressToByteStringByGzip(String str) {
        try {
            byte[] m497e = compressByGzip(str);
            ByteString.Output m3142f = ByteString.newOutput();
            m3142f.write(m497e);
            return m3142f.toByteString();
        } catch (IOException e) {
            HandShaker.error("compressToByteStringByGzip fail by exception: " + e);
            return null;
        }
    }

    /* renamed from: a */
    public static String decompressGzipFromByteString(ByteString byteString) {
        try {
            byte[] m3143d = byteString.toByteArray();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(m3143d);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            byte[] bArr = new byte[8192];
            while (true) {
                int read = gZIPInputStream.read(bArr, 0, 8192);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    byteArrayOutputStream.close();
                    return byteArrayOutputStream.toString();
                }
            }
        } catch (IOException e) {
            HandShaker.error("decompressGzipFromByteString fail by exception: " + e);
            return null;
        }
    }

    /* renamed from: a */
    public static String getMinHostVersion(int type) {
        switch (type) {
            case 1:
                return "2.1.0";
            case 2:
                return "2.5.0";
            default:
                throw new IllegalArgumentException("unknown host type " + type);
        }
    }

    /* renamed from: b */
    public static int getClientMinHostVersionCode(int i) {
        switch (i) {
            case 1:
                return 333;
            case 2:
                return 12;
            default:
                throw new IllegalArgumentException("unknown host type " + i);
        }
    }

    /* renamed from: a */
    public static int m507a(String str, String str2) {
        int i = 0;
        if (str == null || str2 == null) {
            return -1;
        }
        String[] split = str.split("\\.");
        String[] split2 = str2.split("\\.");
        int min = Math.min(split.length, split2.length);
        for (int i2 = 0; i2 < min; i2++) {
            i = split[i2].length() - split2[i2].length();
            if (i != 0 || (i = split[i2].compareTo(split2[i2])) != 0) {
                break;
            }
        }
        return i == 0 ? split.length - split2.length : i;
    }

    /* renamed from: c */
    public static boolean m501c(String str) {
        return Build.VERSION.SDK_INT < 21 && DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().m444c(str);
    }

    /* renamed from: a */
    public static void m513a(Service service, boolean z) {
        if (isSmartisanPhone()) {
            synchronized (f3676c) {
                try {
                    if (f3675b == null) {
                        if (!f3677d) {
                            f3677d = true;
                            f3675b = service.getClass().getMethod("setActiveBackground", Integer.TYPE);
                        } else {
                            return;
                        }
                    }
                    if (f3675b != null) {
                        Method method = f3675b;
                        Object[] objArr = new Object[1];
                        objArr[0] = Integer.valueOf(z ? 1 : 0);
                        method.invoke(service, objArr);
                        HandShaker.debug("setActiveBackground success active: " + z);
                    } else {
                        HandShaker.debug("No setActiveBackground method");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
        }
        HandShaker.debug("Not smartisan phone, Dont setActiveBackground. active: " + z);
    }

    /* renamed from: a */
    public static void m509a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: a */
    public static long m512a(ContentValues contentValues, String str) {
        if (contentValues.containsKey(str)) {
            Object obj = contentValues.get(str);
            if (obj == null || !(obj instanceof Long)) {
                return 0L;
            }
            return ((Long) obj).longValue();
        }
        return 0L;
    }

    /* renamed from: t */
    public static String getDebugBuildTime() {
        HandShaker.debug("showBuildTime buildTime: 0");
        if (0 <= 0) {
            return "";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(0L));
    }

    /* renamed from: d */
    public static boolean checkQRData(String str) {
        String[] split;
        boolean z;
        String[] split2;
        boolean z2 = true;
        if (TextUtils.isEmpty(str) || (split = str.split(":")) == null || split.length != 2) {
            return false;
        }
        try {
            if (TextUtils.isEmpty(split[0]) || (split2 = split[0].split("\\.")) == null || split2.length != 4) {
                z = false;
            } else {
                z = false;
                for (int i = 0; i < split2.length; i++) {
                    int intValue = Integer.valueOf(split2[i]).intValue();
                    if ((i == 0 && intValue > 0) || intValue >= 0) {
                        z = true;
                    }
                }
            }
            return (!z || Integer.valueOf(split[1]).intValue() < 0) ? false : false;
        } catch (NumberFormatException e) {
            HandShaker.error("QRCode, Invalid data:" + str + " exception:" + e.toString());
            return false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003f  */
    /* renamed from: u */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m481u() {
        Camera camera;
        Exception exc;
        Camera open = null;
        Throwable th;
        boolean z = true;
        try {
            open = Camera.open();
        } catch (Exception e) {
            camera = null;
            exc = e;
        } catch (Throwable th1) {
            th = th1;
            camera = null;
        }
        try {
            open.setParameters(open.getParameters());
            if (open != null) {
                open.stopPreview();
                open.release();
            }
        } catch (Exception e2) {
            camera = open;
            exc = e2;
            z = false;
            try {
                HandShaker.error("CommonUtils", "Camera can not be used, ex:" + exc.toString());
                if (camera != null) {
                    camera.stopPreview();
                    camera.release();
                }
                return z;
            } catch (Throwable th2) {
                th = th2;
                if (camera != null) {
                    camera.stopPreview();
                    camera.release();
                }

            }
        } catch (Throwable th3) {
            th = th3;
            camera = open;
            if (camera != null) {
            }

        }
        return z;
    }

    /* renamed from: v */
    public static boolean checkCameraPermission() {
        AppOpsManager appOpsManager;
        if (Build.VERSION.SDK_INT >= 19 && (appOpsManager = (AppOpsManager) FolderApp.getInstance().getSystemService(Context.APP_OPS_SERVICE)) != null) {
            try {
                if (appOpsManager.checkOpNoThrow("android:camera", Binder.getCallingUid(), FolderApp.getInstance().getPackageName()) == 0) {
                    return true;
                }
            } catch (Exception e) {
                HandShaker.error("Check CameraPermission exception:" + e.toString());
            }
        }
        return false;
    }

    /* renamed from: w */
    public static boolean checkVivoCameraPermission() {
        boolean z;
        Camera camera = null;
        try {
            try {
                camera = Camera.open();
                Field declaredField = camera.getClass().getDeclaredField("mHasPermission");
                if (declaredField == null) {
                    z = false;
                } else {
                    declaredField.setAccessible(true);
                    z = ((Boolean) declaredField.get(camera)).booleanValue();
                }
                if (camera != null) {
                    camera.stopPreview();
                    camera.release();
                    return z;
                }
                return z;
            } catch (Exception e) {
                HandShaker.error("Check CameraPermission on VIVO exception:" + e.toString());
                if (camera == null) {
                    return false;
                }
                camera.stopPreview();
                camera.release();
                return false;
            }
        } catch (Throwable th) {
            if (camera != null) {
                camera.stopPreview();
                camera.release();
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x014a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x014f A[Catch: IOException -> 0x0158, TryCatch #14 {IOException -> 0x0158, blocks: (B:53:0x014a, B:55:0x014f, B:57:0x0154), top: B:102:0x014a }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0154 A[Catch: IOException -> 0x0158, TRY_LEAVE, TryCatch #14 {IOException -> 0x0158, blocks: (B:53:0x014a, B:55:0x014f, B:57:0x0154), top: B:102:0x014a }] */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r2v22 */
    /* JADX WARN: Type inference failed for: r2v23, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v24, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r2v31 */
    /* JADX WARN: Type inference failed for: r2v32 */
    /* JADX WARN: Type inference failed for: r2v33 */
    /* JADX WARN: Type inference failed for: r2v34 */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.io.BufferedWriter] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r3v22 */
    /* JADX WARN: Type inference failed for: r3v23, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v27, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v28 */
    /* JADX WARN: Type inference failed for: r3v29 */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.lang.String] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static boolean m506a(String str, String str2, int i) {
        OutputStream outputStream = null;
        Socket socket = null;
        SocketTimeoutException socketTimeoutException;
        BufferedWriter bufferedWriter = null;
        Socket socket2;
        IOException iOException;
        String str3 = null;
        boolean z = true;
        BufferedWriter bufferedWriter2 = null;
        Object r2 = null;
        OutputStream outputStream2 = null;
        Throwable th;
        try {
            try {
                String[] split = str.split(":");
                int intValue = Integer.valueOf(split[1]).intValue();
                str3 = str2 + ":" + i;
                socket = new Socket();
                try {
                    socket.connect(new InetSocketAddress(split[0], intValue), HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE);
                    outputStream = socket.getOutputStream();
                    try {
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                    } catch (NumberFormatException e2) {
                        bufferedWriter = null;
                        z = false;
                        outputStream2 = outputStream;
                        socket2 = socket;
                    }
                } catch (SocketTimeoutException e4) {
                    socketTimeoutException = e4;

                    z = false;
                } catch (IOException e5) {
                    iOException = e5;

                    z = false;
                } catch (NumberFormatException e6) {
                    bufferedWriter = null;
                    socket2 = socket;
                    z = false;
                } catch (Throwable th1) {
                    th = th1;
                    outputStream = null;
                }
            } catch (NumberFormatException e8) {
                bufferedWriter = null;
                socket2 = null;
                z = false;
            } catch (Throwable th2) {
                th = th2;
                outputStream = null;
                socket = null;
            }
            try {
                try {
                    bufferedWriter.write(str3);
                    bufferedWriter.flush();
                } catch (IOException e10) {
                    bufferedWriter2 = bufferedWriter;
                    iOException = e10;
                    z = false;
                    outputStream = outputStream;
                } catch (NumberFormatException e11) {
                    outputStream2 = outputStream;
                    z = false;
                    socket2 = socket;
                }
                try {
                    HandShaker.info("QRCode, Send address and port successfully");
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e13) {
                            HandShaker.error(new StringBuilder("QRCode, Send LinkInfo, finally Exception:").append(e13.toString()).toString());
                        }
                    }
                    bufferedWriter.close();
                    socket.close();
                } catch (IOException e14) {
                    bufferedWriter2 = bufferedWriter;
                    iOException = e14;
                    outputStream = outputStream;
                    HandShaker.error("QRCode, Fail to send LinkInfo, IO exception:" + iOException.toString());
                    iOException.printStackTrace();
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (IOException e15) {
                            HandShaker.error(new StringBuilder("QRCode, Send LinkInfo, finally Exception:").append(e15.toString()).toString());
                        }
                    }
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    if (socket != null) {
                        socket.close();
                    }
                    return z;
                } catch (NumberFormatException e16) {
                    outputStream2 = outputStream;
                    socket2 = socket;
                    try {
                        HandShaker.error("QRCode, Fail to send LinkInfo, addressInfo:" + str);
                        if (outputStream2 != null) {
                            try {
                                outputStream2.close();
                            } catch (IOException e17) {
                                HandShaker.error("QRCode, Send LinkInfo, finally Exception:" + e17.toString());
                            }
                        }
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        if (socket2 != null) {
                            socket2.close();
                        }
                        return z;
                    } catch (Throwable th3) {
                        th = th3;
                        socket = socket2;
                        outputStream = outputStream2;
                        bufferedWriter2 = bufferedWriter;
                        if (outputStream != null) {
                        }
                        if (bufferedWriter2 != null) {
                        }
                        if (socket != null) {
                        }
                        throw th;
                    }
                }
                return z;
            } catch (Throwable th4) {
                th = th4;
                bufferedWriter2 = bufferedWriter;
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e20) {
                        HandShaker.error("QRCode, Send LinkInfo, finally Exception:" + e20.toString());
                        throw th;
                    }
                }
                if (bufferedWriter2 != null) {
                    bufferedWriter2.close();
                }
                if (socket != null) {
                    socket.close();
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m511a(Context context, Uri uri) {
        boolean z = true;
        if (context == null || uri == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT > 18) {
            z = DocumentsContract.isDocumentUri(context, uri);
        } else {
            PackageManager packageManager = context.getPackageManager();
            String authority = uri.getAuthority();
            boolean z2 = false;
            for (ProviderInfo providerInfo : packageManager.queryContentProviders(null, 0, 0)) {
                z2 = authority.equals(providerInfo.authority) ? true : z2;
            }
            if (!"content".equals(uri.getScheme()) || !z2) {
                return false;
            }
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments.size() == 2) {
                z = "document".equals(pathSegments.get(0));
            } else if (pathSegments.size() != 4) {
                return false;
            } else {
                if (!"tree".equals(pathSegments.get(0)) || !"document".equals(pathSegments.get(2))) {
                    z = false;
                }
            }
        }
        return z;
    }

    /* renamed from: o */
    public static boolean isOnePlus() {
        return !Build.BRAND.startsWith("ONEPLUS") || Build.VERSION.SDK_INT >= 19;
    }
}
