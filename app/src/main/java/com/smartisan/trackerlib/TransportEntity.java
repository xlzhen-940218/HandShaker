package com.smartisan.trackerlib;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.smartisan.trackerlib.p046c.CommonUtil;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class TransportEntity implements Parcelable {
    public static final Parcelable.Creator<TransportEntity> CREATOR = new C0471c();

    /* renamed from: a */
    private String f2661a;

    /* renamed from: b */
    private String f2662b;

    /* renamed from: c */
    private String f2663c;

    /* renamed from: d */
    private long f2664d;

    /* renamed from: e */
    private int f2665e;

    /* renamed from: f */
    private String f2666f;

    /* renamed from: g */
    private String f2667g;

    /* renamed from: h */
    private String f2668h;

    /* renamed from: i */
    private String f2669i;

    /* renamed from: j */
    private String f2670j;

    /* renamed from: k */
    private boolean f2671k;

    /* renamed from: l */
    private int f2672l;

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ TransportEntity(Parcel parcel, byte b) {
        this(parcel);
    }

    public TransportEntity(String str, String str2, boolean z) {
        this(str, str2, System.currentTimeMillis(), z);
    }

    private TransportEntity(String str, String str2, long j, boolean z) {
        this(str, str2, j, z, 0);
    }

    public TransportEntity(String str, String str2, long j, boolean z, int i) {
        this.f2671k = true;
        this.f2662b = str;
        if (TextUtils.isEmpty(str2)) {
            this.f2663c = new JSONObject().toString();
        } else {
            this.f2663c = str2;
        }
        this.f2664d = j;
        this.f2665e = 1;
        try {
            this.f2666f = CommonUtil.m1910e(Agent.m1952a().m1947b());
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.f2667g = CommonUtil.m1911e();
        this.f2668h = CommonUtil.m1912d(Agent.m1952a().m1947b());
        this.f2669i = CommonUtil.m1907g();
        this.f2670j = CommonUtil.m1920a();
        this.f2671k = z;
        this.f2672l = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2662b);
        parcel.writeString(this.f2663c);
        parcel.writeLong(this.f2664d);
        parcel.writeInt(this.f2665e);
        parcel.writeString(this.f2666f);
        parcel.writeString(this.f2667g);
        parcel.writeString(this.f2668h);
        parcel.writeString(this.f2669i);
        parcel.writeString(this.f2670j);
        parcel.writeByte((byte) (this.f2671k ? 1 : 0));
        parcel.writeInt(this.f2672l);
    }

    private TransportEntity(Parcel parcel) {
        this.f2671k = true;
        this.f2662b = parcel.readString();
        this.f2663c = parcel.readString();
        this.f2664d = parcel.readLong();
        this.f2665e = parcel.readInt();
        this.f2666f = parcel.readString();
        this.f2667g = parcel.readString();
        this.f2668h = parcel.readString();
        this.f2669i = parcel.readString();
        this.f2670j = parcel.readString();
        this.f2671k = parcel.readByte() != 0;
        this.f2672l = parcel.readInt();
    }

    public int hashCode() {
        return super.hashCode();
    }

    public String toString() {
        new JSONObject();
        JSONObject m1961a = m1961a();
        if (m1961a != null) {
            return m1961a.toString();
        }
        return null;
    }

    /* renamed from: a */
    public final JSONObject m1961a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("uid", m1958b(this.f2661a));
            jSONObject.put("event_id", m1958b(this.f2662b));
            jSONObject.put("event_data", m1956c(this.f2663c));
            jSONObject.put("timestamp", this.f2664d);
            jSONObject.put("device_id", m1956c(this.f2667g));
            jSONObject.put("app_version", m1958b(this.f2668h));
            jSONObject.put("rom_version", m1958b(this.f2669i));
            jSONObject.put("hardware_version", this.f2670j);
            jSONObject.put("platform", this.f2665e);
            jSONObject.put("channel", this.f2666f);
            return jSONObject;
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    private static String m1958b(String str) {
        return TextUtils.isEmpty(str) ? "null" : str;
    }

    /* renamed from: c */
    private static JSONObject m1956c(String str) {
        try {
            return new JSONObject(str);
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    /* renamed from: a */
    public final void m1960a(String str) {
        this.f2661a = str;
    }

    /* renamed from: b */
    public final String m1959b() {
        return this.f2662b;
    }

    /* renamed from: c */
    public final String m1957c() {
        return this.f2663c;
    }

    /* renamed from: d */
    public final long m1955d() {
        return this.f2664d;
    }

    /* renamed from: e */
    public final boolean m1954e() {
        return this.f2671k;
    }

    /* renamed from: f */
    public final int m1953f() {
        return this.f2672l;
    }
}
