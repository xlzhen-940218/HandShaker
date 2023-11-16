package com.smartisan.trackerlib;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: TransportEntity.java */
/* renamed from: com.smartisan.trackerlib.c */
/* loaded from: classes.dex */
final class C0471c implements Parcelable.Creator<TransportEntity> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ TransportEntity[] newArray(int i) {
        return new TransportEntity[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ TransportEntity createFromParcel(Parcel parcel) {
        return new TransportEntity(parcel, (byte) 0);
    }
}
