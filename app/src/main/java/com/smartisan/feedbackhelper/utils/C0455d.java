package com.smartisan.feedbackhelper.utils;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: ComplainReport.java */
/* renamed from: com.smartisan.feedbackhelper.utils.d */
/* loaded from: classes.dex */
final class C0455d implements Parcelable.Creator<ComplainReport> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ ComplainReport[] newArray(int i) {
        return new ComplainReport[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ ComplainReport createFromParcel(Parcel parcel) {
        return new ComplainReport(parcel);
    }
}
