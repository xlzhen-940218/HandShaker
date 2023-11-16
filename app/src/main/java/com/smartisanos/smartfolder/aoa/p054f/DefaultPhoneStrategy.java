package com.smartisanos.smartfolder.aoa.p054f;

import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/* renamed from: com.smartisanos.smartfolder.aoa.f.a */
/* loaded from: classes.dex */
public final class DefaultPhoneStrategy implements PhoneStrategy {
    @Override // com.smartisanos.smartfolder.aoa.p054f.PhoneStrategy
    /* renamed from: a */
    public final Uri mo665a() {
        return MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    }

    @Override // com.smartisanos.smartfolder.aoa.p054f.PhoneStrategy
    /* renamed from: b */
    public final String[] mo663b() {
        return new String[]{"_data", "_size", "date_added"};
    }

    @Override // com.smartisanos.smartfolder.aoa.p054f.PhoneStrategy
    /* renamed from: a */
    public final String getExtData(Cursor cursor) {
        return "";
    }

    @Override // com.smartisanos.smartfolder.aoa.p054f.PhoneStrategy
    /* renamed from: c */
    public final String mo662c() {
        return null;
    }
}
