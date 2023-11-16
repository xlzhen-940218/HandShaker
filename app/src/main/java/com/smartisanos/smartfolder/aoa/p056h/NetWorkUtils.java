package com.smartisanos.smartfolder.aoa.p056h;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

/* renamed from: com.smartisanos.smartfolder.aoa.h.y */
/* loaded from: classes.dex */
public final class NetWorkUtils {
    /* renamed from: a */
    public static boolean m379a(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context == null || (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) == null || activeNetworkInfo.getType() != 1) {
            return false;
        }
        return activeNetworkInfo.isAvailable();
    }

    /* renamed from: b */
    public static String m378b(Context context) {
        NetworkInfo activeNetworkInfo;
        if (context != null && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo()) != null && activeNetworkInfo.getType() == 1) {
            StringBuilder sb = new StringBuilder(((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getSSID());
            if (sb.toString().endsWith("\"") && sb.toString().startsWith("\"")) {
                return sb.subSequence(1, sb.length() - 1).toString();
            }
            return sb.toString();
        }
        return "";
    }

    /* renamed from: c */
    public static String m377c(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService("wifi");
        if (!wifiManager.isWifiEnabled()) {
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                    while (inetAddresses.hasMoreElements()) {
                        InetAddress nextElement = inetAddresses.nextElement();
                        if (!nextElement.isLoopbackAddress()) {
                            return nextElement.getHostAddress().toString();
                        }
                    }
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
            return null;
        }
        int ipAddress = wifiManager.getConnectionInfo().getIpAddress();
        return (ipAddress & 255) + "." + ((ipAddress >> 8) & 255) + "." + ((ipAddress >> 16) & 255) + "." + ((ipAddress >> 24) & 255);
    }
}
