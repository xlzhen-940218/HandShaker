package smartisanos.p066os;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: RemoteCallback.java */
/* renamed from: smartisanos.os.a */
/* loaded from: classes.dex */
final class C0923a implements Parcelable.Creator<RemoteCallback> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ RemoteCallback[] newArray(int i) {
        return new RemoteCallback[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RemoteCallback createFromParcel(Parcel parcel) {
        return new RemoteCallback();
    }
}
