package com.smartisanos.smartfolder.aoa.p056h;

import android.content.ContentValues;
import java.util.Comparator;

/* compiled from: MediaDataProvider.java */
/* renamed from: com.smartisanos.smartfolder.aoa.h.w */
/* loaded from: classes.dex */
final class C0784w implements Comparator<ContentValues> {

    /* renamed from: a */
    final /* synthetic */ MediaDataProvider f3777a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0784w(MediaDataProvider mediaDataProvider) {
        this.f3777a = mediaDataProvider;
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(ContentValues contentValues, ContentValues contentValues2) {
        ContentValues contentValues3 = contentValues;
        ContentValues contentValues4 = contentValues2;
        boolean z = contentValues3 == null || contentValues3.getAsLong("_id") == null;
        boolean z2 = contentValues4 == null || contentValues4.getAsLong("_id") == null;
        if (z && z2) {
            return 0;
        }
        if (z) {
            return -1;
        }
        if (z2) {
            return 1;
        }
        return contentValues3.getAsLong("_id").compareTo(contentValues4.getAsLong("_id"));
    }
}
