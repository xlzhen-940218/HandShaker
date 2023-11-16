package com.smartisan.moreapps;

import org.json.JSONObject;

/* renamed from: com.smartisan.moreapps.l */
/* loaded from: classes.dex */
public final class VersionInfo {

    /* renamed from: a */
    private String f2658a;

    /* renamed from: b */
    private int f2659b;

    /* renamed from: c */
    private String f2660c;

    /* renamed from: a */
    public final int m1964a() {
        return this.f2659b;
    }

    /* renamed from: b */
    public final String m1962b() {
        return this.f2660c;
    }

    /* renamed from: a */
    public static VersionInfo m1963a(JSONObject jSONObject) {
        if (jSONObject == JSONObject.NULL) {
            return null;
        }
        VersionInfo versionInfo = new VersionInfo();
        versionInfo.f2658a = jSONObject.optString("version_name");
        versionInfo.f2659b = jSONObject.optInt("version_code");
        versionInfo.f2660c = jSONObject.optString("url");
        return versionInfo;
    }

    public final String toString() {
        return "NoteVersion [name=" + this.f2658a + ", code=" + this.f2659b + ", updateUrl=" + this.f2660c + "]";
    }
}
