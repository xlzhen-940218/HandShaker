package smartisanos.widget;

import android.os.Parcel;
import android.os.Parcelable;
import smartisanos.widget.DownloadProgressView;

/* compiled from: DownloadProgressView.java */
/* renamed from: smartisanos.widget.b */
/* loaded from: classes.dex */
final class C0931b implements Parcelable.Creator<DownloadProgressView.SavedState> {
    @Override // android.os.Parcelable.Creator
    public final /* bridge */ /* synthetic */ DownloadProgressView.SavedState[] newArray(int i) {
        return new DownloadProgressView.SavedState[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DownloadProgressView.SavedState createFromParcel(Parcel parcel) {
        return new DownloadProgressView.SavedState(parcel, (byte) 0);
    }
}
