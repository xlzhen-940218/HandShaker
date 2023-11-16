package com.smartisanos.smartfolder.aoa.p054f;

import android.media.ExifInterface;
import android.text.TextUtils;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.smartisanos.smartfolder.aoa.f.c */
/* loaded from: classes.dex */
public final class PhotoExtInfo {

    /* renamed from: a */
    public String path;

    /* renamed from: b */
    public boolean star;

    /* renamed from: c */
    public int orientation;

    /* renamed from: d */
    public long updateTime;

    /* renamed from: e */
    public long createTime;

    /* renamed from: f */
    public boolean isExif;

    /* renamed from: a */
    public static PhotoExtInfo m667a(String path, SmartSyncProtocolProtos.SSPFile SSPFile) {
        PhotoExtInfo photoExtInfo = createPhotoExtInfo(path, SSPFile.getExtData());
        photoExtInfo.createTime = SSPFile.getCreatedTimestamp();
        photoExtInfo.isExif = isExif(SSPFile.getPath());
        return photoExtInfo;
    }

    /* renamed from: a */
    public static PhotoExtInfo createPhotoExtInfo(String path, String extData) {
        if (TextUtils.isEmpty(extData)) {
            return null;
        }
        PhotoExtInfo photoExtInfo = new PhotoExtInfo();
        photoExtInfo.path = path;
        try {
            JSONObject jSONObject = new JSONObject(extData);
            photoExtInfo.star = jSONObject.optBoolean("star");
            photoExtInfo.orientation = jSONObject.optInt("orientation");
            photoExtInfo.updateTime = jSONObject.optLong("updateTime");
            HandShaker.debug("PhotoExtInfo", "parse path:[" + path + "], isStar:" + photoExtInfo.star + ", orientation:" + photoExtInfo.orientation + ", updateTime: " + photoExtInfo.updateTime);
            return photoExtInfo;
        } catch (JSONException e) {
            e.printStackTrace();
            return photoExtInfo;
        }
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof PhotoExtInfo)) {
            PhotoExtInfo photoExtInfo = (PhotoExtInfo) obj;
            return this.star == photoExtInfo.star && this.orientation == photoExtInfo.orientation && this.updateTime == photoExtInfo.updateTime;
        }
        return false;
    }

    public final String toString() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("star", this.star);
            jSONObject.put("orientation", this.orientation);
            jSONObject.put("updateTime", this.updateTime);
            return jSONObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return super.toString();
        }
    }

    /* renamed from: a */
    private static boolean isExif(String path) {
        ExifInterface exifInterface;
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        try {
            exifInterface = new ExifInterface(new File(path).getAbsolutePath());
        } catch (IOException e) {
            HandShaker.info("It does not include Exif information, exception:" + e.toString());
            exifInterface = null;
        }
        if (exifInterface == null) {
            return false;
        }
        String exifVersion = exifInterface.getAttribute("ExifVersion");
        String dateTime = exifInterface.getAttribute("DateTime");
        if (TextUtils.isEmpty(exifVersion) || TextUtils.isEmpty(dateTime)) {
            return false;
        }
        return true;
    }
}
