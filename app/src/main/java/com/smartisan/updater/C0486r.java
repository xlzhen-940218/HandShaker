package com.smartisan.updater;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: Version.java */
/* renamed from: com.smartisan.updater.r */
/* loaded from: classes.dex */
final class C0486r implements Parcelable.Creator<Version> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ Version[] newArray(int i) {
        return new Version[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Version createFromParcel(Parcel parcel) {
        return new Version(parcel);
    }
}
