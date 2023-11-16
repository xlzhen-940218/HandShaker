package com.smartisan.feedbackhelper.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.smartisanos.smartfolder.aoa.R;

import java.text.SimpleDateFormat;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.smartisan.feedbackhelper.utils.j */
/* loaded from: classes.dex */
public final class JsonData {

    /* renamed from: a */
    static String f2596a = "BugReportJsonData";

    /* renamed from: b */
    private static SimpleDateFormat f2597b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* renamed from: a */
    public static JSONObject toJson(Context context, ComplainReport complainReport) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tag", complainReport.m2088g());
        jSONObject.put("from", complainReport.m2073o());
        jSONObject.put("createtime", f2597b.format(complainReport.m2092e()));
        jSONObject.put("description", complainReport.m2086h());
        jSONObject.put("summary", complainReport.m2090f());
        if (!complainReport.m2080k().equals("") && complainReport.m2080k() != null) {
            String[] split = complainReport.m2080k().split(",");
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < split.length; i++) {
                jSONArray.put(i, split[i]);
            }
            jSONObject.put("attachlist", jSONArray);
        }
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryInfo(memoryInfo);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("total_mem", (int) (memoryInfo.availMem / 1048576));
        String m2048a = DeviceID.m2049a().m2048a(context);
        if (!TextUtils.isEmpty(m2048a)) {
            jSONObject2.put("uid", m2048a);
        }
        if (!TextUtils.isEmpty(complainReport.m2076m())) {
            jSONObject2.put("bp_version", complainReport.m2076m());
        }
        if (!TextUtils.isEmpty(Build.PRODUCT)) {
            jSONObject2.put("product", Build.PRODUCT);
        }
        if (!TextUtils.isEmpty(Build.MODEL)) {
            jSONObject2.put("model", Build.MODEL);
        }
        if (!TextUtils.isEmpty(Build.BOARD)) {
            jSONObject2.put("board", Build.BOARD);
        }
        if (!TextUtils.isEmpty(Build.BRAND)) {
            jSONObject2.put("brand", Build.BRAND);
        }
        if (!TextUtils.isEmpty(Build.SERIAL)) {
            jSONObject2.put("serial", Build.SERIAL);
        }
        String m2046b = DeviceID.m2049a().m2046b(context);
        if (!TextUtils.isEmpty(m2046b)) {
            jSONObject2.put("telephony_device_id", m2046b);
        }
        String m2047b = DeviceID.m2049a().m2047b();
        if (!TextUtils.isEmpty(m2047b)) {
            jSONObject2.put("wifi_mac", m2047b);
        }
        JSONObject jSONObject3 = new JSONObject();
        if (!TextUtils.isEmpty(complainReport.m2078l())) {
            jSONObject3.put("softwareVersion", complainReport.m2078l());
        }
        if (!TextUtils.isEmpty(Build.TYPE)) {
            jSONObject3.put("type", Build.TYPE);
        }
        if (!TextUtils.isEmpty(Build.FINGERPRINT)) {
            jSONObject3.put("fingerprint", Build.FINGERPRINT);
        }
        if (!TextUtils.isEmpty(Build.CPU_ABI)) {
            jSONObject3.put("cpuAbi", Build.CPU_ABI);
        }
        if (!TextUtils.isEmpty(Build.CPU_ABI2)) {
            jSONObject3.put("cpuAbi2", Build.CPU_ABI2);
        }
        jSONObject3.put("version_sdk_int", String.valueOf(Build.VERSION.SDK_INT));
        JSONObject jSONObject4 = new JSONObject();
        jSONObject4.put("email", complainReport.m2103a());
        JSONObject jSONObject5 = new JSONObject();
        jSONObject5.put("package_name", complainReport.m2072p());
        jSONObject5.put("package_version", complainReport.m2074n());
        JSONObject jSONObject6 = new JSONObject();
        String charSequence = context.getText(R.string.feedback_sdk_name).toString();
        String charSequence2 = context.getText(R.string.feedback_sdk_version).toString();
        jSONObject6.put("app_name", charSequence);
        jSONObject6.put("app_version", charSequence2);
        jSONObject.put("userinfo", jSONObject4);
        jSONObject.put("deviceinfo", jSONObject2);
        jSONObject.put("systeminfo", jSONObject3);
        jSONObject.put("processinfo", jSONObject5);
        jSONObject.put("appinfo", jSONObject6);
        JSONObject jSONObject7 = new JSONObject();
        jSONObject7.put("data", jSONObject);
        return jSONObject7;
    }
}
