package com.smartisanos.smartfolder.aoa.p054f;

import android.database.Cursor;
import android.net.Uri;
import com.smartisanos.smartfolder.aoa.p052d.MediaUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.smartisanos.smartfolder.aoa.f.d */
/* loaded from: classes.dex */
public final class SmartisanPhoneStragety implements PhoneStrategy {
    @Override // com.smartisanos.smartfolder.aoa.p054f.PhoneStrategy
    /* renamed from: a */
    public final Uri mo665a() {
        return MediaUtils.f3508b;
    }

    @Override // com.smartisanos.smartfolder.aoa.p054f.PhoneStrategy
    /* renamed from: b */
    public final String[] mo663b() {
        return new String[]{"_data", "file_size", "date_added", "star", "orientation", "date_attribute_update"};
    }

    @Override // com.smartisanos.smartfolder.aoa.p054f.PhoneStrategy
    /* renamed from: a */
    public final String getExtData(Cursor cursor) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("star", cursor.getInt(3) == 1);
            jSONObject.put("orientation", cursor.getInt(4));
            jSONObject.put("updateTime", cursor.getLong(5));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    @Override // com.smartisanos.smartfolder.aoa.p054f.PhoneStrategy
    /* renamed from: c */
    public final String mo662c() {
        return " media_type = 1";
    }
}
