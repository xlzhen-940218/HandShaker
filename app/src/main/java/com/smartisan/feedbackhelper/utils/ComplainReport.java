package com.smartisan.feedbackhelper.utils;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressLint({"SimpleDateFormat"})
/* loaded from: classes.dex */
public class ComplainReport implements Parcelable {
    public static final Parcelable.Creator<ComplainReport> CREATOR = new C0455d();

    /* renamed from: a */
    private long f2502a;

    /* renamed from: b */
    private String f2503b;

    /* renamed from: c */
    private EnumC0451a f2504c;

    /* renamed from: d */
    private EnumC0452b f2505d;

    /* renamed from: e */
    private Date f2506e;

    /* renamed from: f */
    private String f2507f;

    /* renamed from: g */
    private String f2508g;

    /* renamed from: h */
    private String f2509h;

    /* renamed from: i */
    private String f2510i;

    /* renamed from: j */
    private int f2511j;

    /* renamed from: k */
    private int f2512k;

    /* renamed from: l */
    private int f2513l;

    /* renamed from: m */
    private String f2514m;

    /* renamed from: n */
    private String f2515n;

    /* renamed from: o */
    private String f2516o;

    /* renamed from: p */
    private String f2517p;

    /* renamed from: q */
    private String f2518q;

    /* renamed from: r */
    private String f2519r;

    /* renamed from: s */
    private int f2520s;

    /* renamed from: t */
    private String f2521t;

    /* renamed from: u */
    private String f2522u;

    /* renamed from: v */
    private String f2523v;

    /* renamed from: w */
    private String f2524w;

    /* renamed from: com.smartisan.feedbackhelper.utils.ComplainReport$a */
    /* loaded from: classes.dex */
    public enum EnumC0451a {
        BUILDING,
        WAIT_USER_INPUT,
        READY_TO_UPLOAD,
        READY_TO_COMPRESS,
        COMPRESSING,
        COMPRESSION_PAUSED,
        READY_TO_TRANSMIT,
        TRANSMITTING,
        READY_TO_COMPLETE,
        COMPLETING,
        READY_TO_ARCHIVE,
        ARCHIVED_FULL,
        ARCHIVED_PARTIAL,
        BUILD_FAILED,
        COMPRESS_FAILED,
        TRANSMIT_FAILED,
        COMPLETE_FAILED,
        USER_DELETED_OUTBOX,
        USER_DELETED_DRAFT
    }

    /* renamed from: com.smartisan.feedbackhelper.utils.ComplainReport$b */
    /* loaded from: classes.dex */
    public enum EnumC0452b {
        AUTO,
        USER
    }

    public ComplainReport() {
        this.f2504c = null;
        this.f2505d = null;
        this.f2506e = null;
        this.f2507f = null;
        this.f2508g = null;
        this.f2509h = null;
        this.f2510i = null;
        this.f2514m = null;
        this.f2515n = null;
        this.f2516o = null;
        this.f2517p = null;
        this.f2518q = "false";
        this.f2519r = null;
        this.f2520s = 1;
        this.f2521t = null;
        this.f2522u = "unknown";
        this.f2523v = "unknown";
        this.f2504c = EnumC0451a.WAIT_USER_INPUT;
        this.f2505d = EnumC0452b.USER;
    }

    public ComplainReport(Parcel parcel) {
        this();
        this.f2502a = parcel.readLong();
        this.f2503b = parcel.readString();
        this.f2504c = EnumC0451a.valueOf(parcel.readString());
        this.f2505d = EnumC0452b.valueOf(parcel.readString());
        this.f2506e = new Date(parcel.readLong());
        this.f2507f = parcel.readString();
        this.f2508g = parcel.readString();
        this.f2509h = parcel.readString();
        this.f2510i = parcel.readString();
        this.f2511j = parcel.readInt();
        this.f2512k = parcel.readInt();
        this.f2513l = parcel.readInt();
        this.f2514m = parcel.readString();
        this.f2515n = parcel.readString();
        this.f2516o = parcel.readString();
        this.f2517p = parcel.readString();
        this.f2518q = parcel.readString();
        this.f2519r = parcel.readString();
        this.f2521t = parcel.readString();
        this.f2522u = parcel.readString();
        this.f2523v = parcel.readString();
        this.f2524w = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f2502a);
        parcel.writeString(this.f2503b);
        parcel.writeString(this.f2504c.name());
        parcel.writeString(this.f2505d.name());
        parcel.writeLong(this.f2506e.getTime());
        parcel.writeString(this.f2507f);
        parcel.writeString(this.f2508g);
        parcel.writeString(this.f2509h);
        parcel.writeString(this.f2510i);
        parcel.writeInt(this.f2511j);
        parcel.writeInt(this.f2512k);
        parcel.writeInt(this.f2513l);
        parcel.writeString(this.f2514m);
        parcel.writeString(this.f2515n);
        parcel.writeString(this.f2516o);
        parcel.writeString(this.f2517p);
        parcel.writeString(this.f2518q);
        parcel.writeString(this.f2519r);
        parcel.writeString(this.f2521t);
        parcel.writeString(this.f2522u);
        parcel.writeString(this.f2523v);
        parcel.writeString(this.f2524w);
    }

    /* renamed from: a */
    public final String m2103a() {
        return this.f2524w;
    }

    /* renamed from: a */
    public final void m2100a(String str) {
        this.f2524w = str;
    }

    /* renamed from: b */
    public final String m2098b() {
        return this.f2503b;
    }

    /* renamed from: b */
    public final void m2097b(String str) {
        this.f2503b = str;
    }

    /* renamed from: c */
    public final EnumC0451a m2096c() {
        return this.f2504c;
    }

    /* renamed from: a */
    public final void m2102a(EnumC0451a enumC0451a) {
        this.f2504c = enumC0451a;
    }

    /* renamed from: d */
    public final EnumC0452b m2094d() {
        return this.f2505d;
    }

    /* renamed from: a */
    public final void m2101a(EnumC0452b enumC0452b) {
        this.f2505d = enumC0452b;
    }

    /* renamed from: e */
    public final Date m2092e() {
        return this.f2506e;
    }

    /* renamed from: a */
    public final void m2099a(Date date) {
        this.f2506e = date;
    }

    /* renamed from: f */
    public final String m2090f() {
        return this.f2507f;
    }

    /* renamed from: c */
    public final void m2095c(String str) {
        this.f2507f = str;
    }

    /* renamed from: g */
    public final String m2088g() {
        return this.f2508g;
    }

    /* renamed from: d */
    public final void m2093d(String str) {
        this.f2508g = str;
    }

    /* renamed from: h */
    public final String m2086h() {
        return this.f2509h;
    }

    /* renamed from: e */
    public final void m2091e(String str) {
        this.f2509h = str;
    }

    /* renamed from: i */
    public final String m2084i() {
        return this.f2510i;
    }

    /* renamed from: f */
    public final void m2089f(String str) {
        this.f2510i = str;
    }

    /* renamed from: j */
    public final String m2082j() {
        return this.f2514m;
    }

    /* renamed from: k */
    public final String m2080k() {
        return this.f2515n;
    }

    /* renamed from: g */
    public final void m2087g(String str) {
        this.f2515n = str;
    }

    /* renamed from: l */
    public final String m2078l() {
        return this.f2516o;
    }

    /* renamed from: h */
    public final void m2085h(String str) {
        this.f2516o = str;
    }

    /* renamed from: m */
    public final String m2076m() {
        return this.f2517p;
    }

    /* renamed from: i */
    public final void m2083i(String str) {
        this.f2517p = str;
    }

    /* renamed from: n */
    public final String m2074n() {
        return this.f2519r;
    }

    /* renamed from: j */
    public final void m2081j(String str) {
        this.f2519r = str;
    }

    /* renamed from: o */
    public final String m2073o() {
        return this.f2521t;
    }

    /* renamed from: k */
    public final void m2079k(String str) {
        this.f2521t = str;
    }

    /* renamed from: l */
    public final void m2077l(String str) {
        this.f2522u = str;
    }

    /* renamed from: p */
    public final String m2072p() {
        return this.f2523v;
    }

    /* renamed from: m */
    public final void m2075m(String str) {
        this.f2523v = str;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return this.f2507f == null ? "None" : this.f2507f + " : " + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSSZ").format(this.f2506e) + ", " + this.f2504c.name();
    }

    public int hashCode() {
        return this.f2506e.hashCode();
    }
}
