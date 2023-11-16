package com.smartisan.updater;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Version implements Parcelable {
    public static final Parcelable.Creator<Version> CREATOR = new C0486r();

    /* renamed from: a */
    private boolean f2691a;

    /* renamed from: b */
    private String f2692b;

    /* renamed from: c */
    private int f2693c;

    /* renamed from: d */
    private String f2694d;

    /* renamed from: e */
    private long f2695e;

    /* renamed from: f */
    private boolean f2696f;

    /* renamed from: g */
    private boolean f2697g;

    /* renamed from: h */
    private String f2698h;

    public Version() {
        this.f2696f = false;
        this.f2697g = false;
    }

    public Version(Parcel parcel) {
        this.f2696f = false;
        this.f2697g = false;
        this.f2691a = parcel.readInt() == 1;
        this.f2692b = parcel.readString();
        this.f2693c = parcel.readInt();
        this.f2694d = parcel.readString();
        this.f2695e = parcel.readLong();
        this.f2696f = parcel.readInt() == 1;
        this.f2697g = parcel.readInt() == 1;
        this.f2698h = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2691a ? 1 : 0);
        parcel.writeString(this.f2692b);
        parcel.writeInt(this.f2693c);
        parcel.writeString(this.f2694d);
        parcel.writeLong(this.f2695e);
        parcel.writeInt(this.f2696f ? 1 : 0);
        parcel.writeInt(this.f2697g ? 1 : 0);
        parcel.writeString(this.f2698h);
    }

    /* renamed from: a */
    public final String m1893a() {
        return this.f2692b;
    }

    /* renamed from: b */
    public final int m1891b() {
        return this.f2693c;
    }

    /* renamed from: c */
    public final String m1890c() {
        return this.f2694d;
    }

    /* renamed from: d */
    public final String m1889d() {
        return this.f2698h;
    }

    /* renamed from: e */
    public final boolean m1888e() {
        return this.f2691a;
    }

    /* renamed from: f */
    public final boolean m1887f() {
        return this.f2696f;
    }

    /* renamed from: g */
    public final boolean m1886g() {
        return this.f2697g;
    }

    /* renamed from: h */
    public final void m1885h() {
        this.f2691a = false;
    }

    /* renamed from: i */
    public final long m1884i() {
        return this.f2695e;
    }

    /* renamed from: a */
    public static Version m1892a(Context context, JSONObject jSONObject) {
        boolean z;
        if (jSONObject == JSONObject.NULL) {
            return null;
        }
        Version version = new Version();
        version.f2692b = jSONObject.optString("version_name");
        version.f2693c = jSONObject.optInt("version_code");
        version.f2694d = jSONObject.optString("url");
        int i = version.f2693c;
        if (i >= 0) {
            z = i > UpdateUtils.m1835c(context);
        } else {
            z = false;
        }
        version.f2691a = z;
        version.f2695e = jSONObject.optLong("size");
        version.f2696f = "on".equals(jSONObject.optString("ota_update"));
        version.f2697g = "on".equals(jSONObject.optString("force"));
        version.f2698h = jSONObject.optString("md5");
        return version;
    }

    public String toString() {
        return "NoteVersion [needUpdate=" + this.f2691a + ", name=" + this.f2692b + ", code=" + this.f2693c + ", updateUrl=" + this.f2694d + "]";
    }
}
