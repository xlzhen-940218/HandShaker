package com.smartisanos.smartfolder.aoa.p052d;

import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.DeadObjectException;
import android.os.Environment;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.google.protobuf.ByteString;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* renamed from: com.smartisanos.smartfolder.aoa.d.e */
/* loaded from: classes.dex */
public final class MediaUtils {

    /* renamed from: a */
    public static final Uri f3507a = Uri.parse("content://com.smartisan.music.provider.TrackAddonsProvider/hide_dir");

    /* renamed from: b */
    public static final Uri f3508b = Uri.parse("content://smartisanos_gallery/files");

    /* renamed from: c */
    public static final Uri f3509c = Uri.parse("content://smartisanos_gallery/bucket");

    /* renamed from: d */
    private static final String[] f3510d = {"_id", "_data", "_size", "(select bucket_id from files where audio._id = files._id ) AS bucket_id", "(select bucket_display_name from files where audio._id = files._id ) AS bucket_name", "date_added", "title", "duration", "artist_id", "artist", "composer", "album_id", "album", "(select audio_genres_map.genre_id from audio_genres_map where audio._id = audio_genres_map.audio_id) AS genre_id", "(select audio_genres.name from audio_genres,audio_genres_map where audio_genres._id = audio_genres_map.genre_id AND audio._id = audio_genres_map.audio_id) AS genre_name", "track", "year", "album_artist", "mime_type", "date_modified", "composer"};

    /* renamed from: b */
    private static String m713b(ArrayList<Long> arrayList) {
        return m711c(arrayList) + " AND (_data LIKE '%/Ringtones/%'  OR ( (is_music = 1 or is_podcast = 1 ) AND (_data LIKE '%/smartisan/music/cloud/%' OR _data LIKE '%/Music/%' OR _size > 800000  OR  mime_type = 'audio/x-smartisanos-cua') AND _data NOT LIKE '%.ogg' AND _data NOT LIKE '%.3gp' AND _data NOT LIKE '%.ac3' ))";
    }

    /* renamed from: f */
    private static ArrayList<Long> m708f() {
        Cursor cursor = null;
        ArrayList<Long> arrayList = new ArrayList<>();
        try {
            cursor = m718a(f3507a, new String[]{"bucket_id"}, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    arrayList.add(Long.valueOf(cursor.getLong(0)));
                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            CommonUtils.m509a(cursor);
        }
        return arrayList;
    }

    /* renamed from: c */
    private static String m711c(ArrayList<Long> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return " 1 = 1 ";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("(select bucket_id from files where _id = audio._id)");
        sb.append(" NOT IN ");
        sb.append("(");
        Iterator<Long> it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    private static Cursor m716a(String str) {
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        if (TextUtils.isEmpty(str)) {
            str = "_id";
        }
        return m718a(uri, f3510d, m713b(m708f()), null, str);
    }

    /* renamed from: a */
    public static SmartSyncProtocolProtos.SSPGetAudioLibraryResponse m721a() {
        ArrayList<SmartSyncProtocolProtos.SSPAudioFile> m706h = getAudioFiles();
        if (m706h == null || m706h.isEmpty()) {
            return SmartSyncProtocolProtos.SSPGetAudioLibraryResponse.newBuilder()
                    .setType(SmartSyncProtocolProtos.SSPRequestType.GET_AUDIO_LIB_REQUEST).build();
        }
        return SmartSyncProtocolProtos.SSPGetAudioLibraryResponse.newBuilder().addAllAudio(m706h)
                .addAllAlbum(getAudioAlbums()).setType(SmartSyncProtocolProtos.SSPRequestType.GET_AUDIO_LIB_REQUEST).build();
    }

    /* renamed from: g */
    private static ArrayList<SmartSyncProtocolProtos.SSPAudioAlbum> getAudioAlbums() {
        Cursor cursor = null;
        ArrayList<SmartSyncProtocolProtos.SSPAudioAlbum> arrayList = new ArrayList<>();
        try {
            String str = "album_id";
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            if (TextUtils.isEmpty("album_id")) {
                str = "_id";
            }
            cursor = m718a(uri, f3510d, m713b(m708f()) + ") group by  (album_id", null, str);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    SmartSyncProtocolProtos.SSPAudioAlbum.Builder m1815r = SmartSyncProtocolProtos.SSPAudioAlbum.newBuilder();
                    String albumPath = cursor.getString(1);
                    m1815r.setAlbumPath(albumPath.substring(0, albumPath.lastIndexOf("/")));
                    m1815r.setAlbumId(cursor.getLong(11));
                    m1815r.setYear(cursor.getInt(16));
                    m1815r.setAlbumName(cursor.getString(12) == null ? "" : cursor.getString(12));
                    m1815r.setArtist(cursor.getString(9) == null ? "" : cursor.getString(9));
                    m1815r.setArtistId(cursor.getLong(8));
                    arrayList.add(m1815r.build());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CommonUtils.m509a(cursor);
        }
        return arrayList;
    }

    /* renamed from: h */
    private static ArrayList<SmartSyncProtocolProtos.SSPAudioFile> getAudioFiles() {
        Cursor cursor;
        Exception e;
        ArrayList arrayList = null;
        Throwable th;
        try {
            cursor = m716a("album_id");
            try {
                try {
                    arrayList = new ArrayList();
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            try {
                                SmartSyncProtocolProtos.SSPAudioFile.Builder builder = SmartSyncProtocolProtos.SSPAudioFile.newBuilder();
                                builder.setPath(cursor.getString(1) == null ? "" : cursor.getString(1));
                                builder.setFileSize(cursor.getLong(2));
                                builder.setCreatedTimestamp(cursor.getLong(5));
                                builder.setModifiedTimestamp(cursor.getLong(19));
                                builder.setMediaId(cursor.getLong(0));
                                builder.setAlbumId(cursor.getLong(11));
                                builder.setArtistId(cursor.getLong(8));
                                builder.setMimeType(cursor.getString(18) == null ? "" : cursor.getString(18));
                                builder.setTitle(cursor.getString(6));
                                builder.setArtist(cursor.getString(9) == null ? "" : cursor.getString(9));
                                builder.setTrack(cursor.getInt(15));
                                builder.setYear(cursor.getInt(16));
                                builder.setDuration(cursor.getDouble(7) / 1000.0d);
                                builder.setComposer(cursor.getString(20) == null ? "" : cursor.getString(20));
                                builder.setGenre(cursor.getInt(13));
                                String string = cursor.getString(14);
                                if (TextUtils.isEmpty(string)) {
                                    string = "null";
                                }
                                builder.setGenreName(string);
                                arrayList.add(builder.build());
                            } catch (Exception e2) {
                                e = e2;
                                e.printStackTrace();
                                CommonUtils.m509a(cursor);
                                return arrayList;
                            }
                        }
                    }
                    CommonUtils.m509a(cursor);
                } catch (Throwable th1) {
                    th = th1;
                    CommonUtils.m509a(cursor);
                    throw th;
                }
            } catch (Exception e3) {
                arrayList = null;
                e = e3;
            }
        } catch (Exception e4) {
            cursor = null;
            e = e4;
            arrayList = null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
            CommonUtils.m509a(cursor);
        }
        return arrayList;
    }

    /* renamed from: a */
    public static List<ContentValues> m717a(EventManager.REQUEST_TYPE enumC0705a) {
        Cursor cursor = null;
        try {
            try {
                switch (enumC0705a) {
                    case IMAGE:
                        cursor = m718a(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, m715a(m714b()), null, "_id");
                        break;
                    case VIDEO:
                        cursor = m718a(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, null, null, null, "_id");
                        break;
                    case AUDIO:
                        cursor = m716a("_id");
                        break;
                }
                ArrayList arrayList = new ArrayList();
                if (cursor == null || !cursor.moveToFirst()) {
                    return arrayList;
                }
                int columnCount = cursor.getColumnCount();
                do {
                    ContentValues contentValues = new ContentValues();
                    for (int i = 0; i < columnCount; i++) {
                        String columnName = cursor.getColumnName(i);
                        switch (cursor.getType(i)) {
                            case 0:
                                contentValues.putNull(columnName);
                                break;
                            case 1:
                                contentValues.put(columnName, Long.valueOf(cursor.getLong(i)));
                                break;
                            case 2:
                                contentValues.put(columnName, Float.valueOf(cursor.getFloat(i)));
                                break;
                            case 3:
                                contentValues.put(columnName, cursor.getString(i));
                                break;
                            case 4:
                                contentValues.put(columnName, cursor.getBlob(i));
                                break;
                        }
                    }
                    arrayList.add(contentValues);
                } while (cursor.moveToNext());
                return arrayList;
            } catch (Exception e) {
                e.printStackTrace();
                CommonUtils.m509a(cursor);
                return new ArrayList();
            }
        } finally {
            CommonUtils.m509a(cursor);
        }
    }

    /* renamed from: b */
    public static ArrayList<Long> m714b() {
        Exception exc;
        ArrayList<Long> arrayList = null;
        Cursor m718a = null;
        Cursor cursor = null;
        try {
            try {
                m718a = m718a(f3509c, new String[]{"_id"}, "status = ?", new String[]{"2"}, null);
            } catch (Exception e) {
                exc = e;
                arrayList = null;
            }
            if (m718a != null) {
                try {
                    try {
                    } catch (Exception e2) {
                        exc = e2;
                        arrayList = null;
                        cursor = m718a;
                    }
                    if (m718a.moveToFirst()) {
                        ArrayList<Long> arrayList2 = new ArrayList<>();
                        do {
                            try {
                                arrayList2.add(Long.valueOf(m718a.getLong(0)));
                            } catch (Exception e3) {
                                cursor = m718a;
                                exc = e3;
                                arrayList = arrayList2;
                                exc.printStackTrace();
                                if (cursor != null && !cursor.isClosed()) {
                                    cursor.close();
                                }
                                return arrayList;
                            }
                        } while (m718a.moveToNext());
                        arrayList = arrayList2;
                        if (m718a != null && !m718a.isClosed()) {
                            m718a.close();
                        }
                        return arrayList;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = m718a;
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            arrayList = null;
            if (m718a != null) {
                m718a.close();
            }
            return arrayList;
        } catch (Throwable th2) {

        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0163  */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<ArrayList> m712c() {
        Cursor m718a = null;
        String albumName;
        Cursor cursor = null;
        Throwable th;
        ArrayList<ArrayList> arrayList = new ArrayList<>();
        try {
            m718a = m718a(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "_size"
                    , "date_added", "date_modified", "width", "height", "_id", "bucket_id", "mime_type"
                    , "_display_name", "duration", "album"}, null, null, "bucket_id");
        } catch (Throwable th1) {
            th = th1;
        }
        try {
            HashMap<Long,ArrayList> hashMap = new HashMap<>();
            ArrayList<SmartSyncProtocolProtos.SSPVideoFile> arrayList2 = new ArrayList<>();
            ArrayList<SmartSyncProtocolProtos.SSPVideoAlbum> arrayList3 = new ArrayList<>();
            if (m718a != null && m718a.moveToFirst()) {
                do {
                    SmartSyncProtocolProtos.SSPVideoFile.Builder builder = SmartSyncProtocolProtos.SSPVideoFile.newBuilder();
                    builder.setPath(m718a.getString(0) == null ? "" : m718a.getString(0));
                    builder.setFileSize(m718a.getLong(1));
                    builder.setCreatedTimestamp(m718a.getInt(2));
                    builder.setModifiedTimestamp(m718a.getInt(3));
                    builder.setWidth(m718a.getInt(4));
                    builder.setHeight(m718a.getInt(5));
                    builder.setMediaId(m718a.getLong(6));
                    builder.setAlbumId(m718a.getLong(7));
                    builder.setMimeType(m718a.getString(8) == null ? "" : m718a.getString(8));
                    Object m720a = m720a(m718a.getType(9), m718a, 9);
                    builder.setThumbnail(m720a == null ? ByteString.copyFromUtf8("") : ByteString.copyFromUtf8((String) m720a));
                    builder.setDuration(m718a.getInt(10) / 1000.0d);
                    arrayList2.add(builder.build());
                    Long albumId = Long.valueOf(builder.getAlbumId());
                    if (albumId != null) {
                        String path = builder.getPath();
                        String albumPath = path.substring(0, path.lastIndexOf("/"));
                        if (!albumPath.contains("/")) {
                            albumName = "";
                        } else {
                            albumName = albumPath.substring(albumPath.lastIndexOf("/") + 1, albumPath.length());
                        }
                        SmartSyncProtocolProtos.SSPVideoAlbum.Builder albumBuilder = SmartSyncProtocolProtos.SSPVideoAlbum.newBuilder();
                        albumBuilder.setAlbumId(albumId.longValue());
                        albumBuilder.setAlbumPath(albumPath);
                        albumBuilder.setAlbumName(albumName);
                        ArrayList arrayList4 = (ArrayList) hashMap.get(albumId);
                        if (arrayList4 == null) {
                            arrayList4 = new ArrayList();
                            hashMap.put(albumId, arrayList4);
                            arrayList3.add(albumBuilder.build());
                        }
                        arrayList4.add(albumBuilder.build());
                    }
                } while (m718a.moveToNext());
                arrayList.add(arrayList2);
                arrayList.add(arrayList3);
                if (m718a != null) {
                }
                return arrayList;
            }
            arrayList.add(arrayList2);
            arrayList.add(arrayList3);
            if (m718a != null) {
                m718a.close();
            }
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            cursor = m718a;
            if (cursor != null) {
                cursor.close();
            }

        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:50:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x023a  */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static ArrayList<ArrayList> m710d() {
        Map<String, ContentValues> m709e = null;
        Throwable th;
        ContentValues contentValues;
        ArrayList<Long> m714b = m714b();
        Cursor cursor = null;
        ArrayList<ArrayList> arrayList = new ArrayList<>();
        boolean m484r = CommonUtils.m484r();
        if (!m484r) {
            m709e = null;
        } else {
            try {
                m709e = m709e();
            } catch (Throwable th2) {
                th = th2;
                if (cursor != null) {
                    cursor.close();
                }

            }
        }
        Cursor m718a = m718a(MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                , new String[]{"_data", "_size", "date_added", "date_modified", "width", "height"
                        , "orientation", "_id", "bucket_id", "mime_type", "_display_name"
                        , "bucket_display_name", "datetaken", "latitude", "longitude", "mini_thumb_magic"
                        , "title"}, m715a(m714b), null, " date_added desc");
        try {
            HashMap hashMap = new HashMap();
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            if (m718a != null && m718a.moveToFirst()) {
                do {
                    SmartSyncProtocolProtos.SSPImageFile.Builder imageBuilder = SmartSyncProtocolProtos.SSPImageFile.newBuilder();
                    imageBuilder.setPath(m718a.getString(0) == null ? "" : m718a.getString(0));
                    imageBuilder.setFileSize(m718a.getLong(1));
                    imageBuilder.setCreatedTimestamp(m718a.getLong(2));
                    imageBuilder.setModifiedTimestamp(m718a.getLong(3));
                    imageBuilder.setWidth(m718a.getInt(4));
                    imageBuilder.setHeight(m718a.getInt(5));
                    imageBuilder.setOrientation(m718a.getInt(6));
                    imageBuilder.setMediaId(m718a.getLong(7));
                    imageBuilder.setAlbumId(m718a.getLong(8));
                    imageBuilder.setMimeType(m718a.getString(9) == null ? "" : m718a.getString(9));
                    Object m720a = m720a(m718a.getType(10), m718a, 10);
                    imageBuilder.setThumbnail(m720a == null ? ByteString.copyFromUtf8("") : ByteString.copyFromUtf8((String) m720a));
                    imageBuilder.setAlbumName(m718a.getString(11) == null ? "" : m718a.getString(11));
                    imageBuilder.setDateTaken(m718a.getLong(12));
                    Object m720a2 = m720a(m718a.getType(13), m718a, 13);
                    imageBuilder.setLatitude(m720a2 == null ? "" : String.valueOf((Float) m720a2));
                    Object m720a3 = m720a(m718a.getType(14), m718a, 14);
                    imageBuilder.setLongitude(m720a3 == null ? "" : String.valueOf((Float) m720a3));
                    Object m720a4 = m720a(m718a.getType(15), m718a, 15);
                    imageBuilder.setMiniThumbMagic(m720a4 == null ? "" : String.valueOf((Integer) m720a4));
                    Object m720a5 = m720a(m718a.getType(16), m718a, 16);
                    imageBuilder.setTitle(m720a5 == null ? "" : (String) m720a5);
                    if (m484r && m709e != null && (contentValues = m709e.get(imageBuilder.getPath())) != null) {
                        imageBuilder.setStarred(contentValues.getAsBoolean("star").booleanValue());
                    }
                    arrayList2.add(imageBuilder.build());
                    Long albumId = Long.valueOf(imageBuilder.getAlbumId());
                    if (albumId != null) {
                        String path = imageBuilder.getPath();
                        String albumPath = path.substring(0, path.lastIndexOf("/"));
                        SmartSyncProtocolProtos.SSPImageAlbum.Builder m1249l = SmartSyncProtocolProtos.SSPImageAlbum.newBuilder();
                        m1249l.setAlbumPath(albumPath);
                        m1249l.setAlbumId(albumId.longValue());
                        m1249l.setAlbumName(imageBuilder.getAlbumName());
                        m1249l.setCoverImage(imageBuilder);
                        ArrayList arrayList4 = (ArrayList) hashMap.get(albumId);
                        if (arrayList4 == null) {
                            arrayList4 = new ArrayList();
                            hashMap.put(albumId, arrayList4);
                            arrayList3.add(m1249l.build());
                        }
                        arrayList4.add(m1249l.build());
                    }
                } while (m718a.moveToNext());
                arrayList.add(arrayList2);
                arrayList.add(arrayList3);
                if (m718a != null) {
                }
                return arrayList;
            }
            arrayList.add(arrayList2);
            arrayList.add(arrayList3);
            if (m718a != null) {
                m718a.close();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            cursor = m718a;
            if (cursor != null) {
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static String m715a(ArrayList<Long> arrayList) {
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder("bucket_id NOT IN ");
        sb.append("(");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            sb.append(arrayList.get(i));
            if (i != size - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* renamed from: a */
    private static Cursor m719a(ContentResolver contentResolver, Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
        if (acquireUnstableContentProviderClient == null) {
            return null;
        }
        try {
            return acquireUnstableContentProviderClient.query(uri, strArr, str, strArr2, str2);
        } catch (DeadObjectException e) {
            e.printStackTrace();
            return null;
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return null;
        } finally {
            acquireUnstableContentProviderClient.release();
        }
    }

    /* renamed from: a */
    private static Object m720a(int i, Cursor cursor, int i2) {
        switch (i) {
            case 1:
                return Integer.valueOf(cursor.getInt(i2));
            case 2:
                return Float.valueOf(cursor.getFloat(i2));
            case 3:
                return cursor.getString(i2);
            case 4:
                return cursor.getBlob(i2);
            default:
                return null;
        }
    }

    /* renamed from: a */
    public static Cursor m718a(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        try {
            return m719a(FolderApp.getInstance().getContentResolver(), uri, strArr, str, strArr2, str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00a7  */
    /* renamed from: e */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static Map<String, ContentValues> m709e() {
        Cursor cursor;
        Throwable th;
        Exception e;
        if (CommonUtils.isSmartisanPhone()) {
            ArrayList<Long> m714b = m714b();
            HashMap hashMap = new HashMap();
            try {
                Uri parse = Uri.parse("content://smartisanos_gallery/files");
                String[] strArr = {"_data", "star"};
                String m715a = m715a(m714b);
                String str = " _data LIKE '" + Environment.getExternalStorageDirectory() + "%' ";
                if (!TextUtils.isEmpty(m715a)) {
                    str = str + " AND " + m715a;
                }
                cursor = m718a(parse, strArr, str, null, " date_added desc");
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            try {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("star", Boolean.valueOf(cursor.getInt(1) == 1));
                                hashMap.put(cursor.getString(0), contentValues);
                            } catch (Throwable th2) {
                                th = th2;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            e.printStackTrace();
                            if (cursor != null) {
                                cursor.close();
                            }
                            return hashMap;
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Exception e3) {
                cursor = null;
                e = e3;
            } catch (Throwable th3) {
                cursor = null;
                th = th3;
                if (cursor != null) {
                }
            }
            return hashMap;
        }
        return null;
    }
}
