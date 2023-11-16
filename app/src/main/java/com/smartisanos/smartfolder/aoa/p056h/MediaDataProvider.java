package com.smartisanos.smartfolder.aoa.p056h;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.MediaStore;

import com.google.protobuf.ByteString;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p049a.EventManager;
import com.smartisanos.smartfolder.aoa.p052d.MediaUtils;
import com.smartisanos.smartfolder.aoa.p054f.SyncManager;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.smartisanos.smartfolder.aoa.h.v */
/* loaded from: classes.dex */
public class MediaDataProvider {

    /* renamed from: b */
    private static final String f3763b = MediaDataProvider.class.getSimpleName();

    /* renamed from: c */
    private static final Uri f3764c = Uri.parse("content://media/external");

    /* renamed from: d */
    private static MediaDataProvider f3765d = new MediaDataProvider();

    /* renamed from: e */
    private Handler f3767e;

    /* renamed from: f */
    private C0783b f3768f;

    /* renamed from: g */
    private HandlerThread f3769g;

    /* renamed from: h */
    private ArrayList<ContentValues> f3770h = new ArrayList<>();

    /* renamed from: i */
    private ConcurrentHashMap<EventManager.REQUEST_TYPE, List<ContentValues>> f3771i = new ConcurrentHashMap<>();

    /* renamed from: a */
    Comparator f3766a = new C0784w(this);

    /* renamed from: a */
    public static MediaDataProvider m401a() {
        return f3765d;
    }

    private MediaDataProvider() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: f */
    public synchronized void m388f() {
        if (DeviceInfoHelper.needImage) {
            C0782a m399a = m399a(EventManager.REQUEST_TYPE.IMAGE, false);
            HandShaker.info(f3763b, "photoLibray......1");
            if (C0782a.m387a(m399a)) {
                HandShaker.info(f3763b, "photoLibray......2");
                ArrayList<SmartSyncProtocolProtos.SSPImageFile> m397a = m397a(m399a.added);
                EventManager.getInstance().m798a(EventManager.REQUEST_TYPE.IMAGE, SmartSyncProtocolProtos.SSPPhotoLibraryChange
                        .newBuilder()
                        .setType(SmartSyncProtocolProtos.SSPRequestType.PHOTO_LIB_CHANGE)
                        .addAllAddedImage((Iterable<? extends SmartSyncProtocolProtos.SSPImageFile>) m397a)
                        .addAllDeletedImage(m397a(m399a.deleted)).build().toByteArray());
            }
        }
        if (DeviceInfoHelper.needVideo) {
            C0782a m399a2 = m399a(EventManager.REQUEST_TYPE.VIDEO, true);
            if (C0782a.m387a(m399a2)) {
                ArrayList<SmartSyncProtocolProtos.SSPVideoFile> addedVideo = contentValuesToVideoFiles(m399a2.added);
                ArrayList<SmartSyncProtocolProtos.SSPVideoFile> updateVideo = contentValuesToVideoFiles(m399a2.updated);
                ArrayList<SmartSyncProtocolProtos.SSPVideoFile> deleteVideo = contentValuesToVideoFiles(m399a2.deleted);
                ArrayList arrayList = new ArrayList();
                if (updateVideo.size() > 0) {
                    arrayList.addAll(addedVideo);
                    arrayList.addAll(updateVideo);
                    EventManager.getInstance().m798a(EventManager.REQUEST_TYPE.VIDEO
                            , SmartSyncProtocolProtos.SSPVideoLibraryChange
                                    .newBuilder()
                                    .setType(SmartSyncProtocolProtos.SSPRequestType.VIDEO_LIB_CHANGE)
                                    .addAllAddedVideo(arrayList)
                                    .addAllDeletedVideo(deleteVideo).build().toByteArray());
                } else {
                    EventManager.getInstance().m798a(EventManager.REQUEST_TYPE.VIDEO, SmartSyncProtocolProtos.SSPVideoLibraryChange
                            .newBuilder().setType(SmartSyncProtocolProtos.SSPRequestType.VIDEO_LIB_CHANGE)
                            .addAllAddedVideo(addedVideo)
                            .addAllDeletedVideo(deleteVideo).build().toByteArray());
                }
            }
        }
        if (DeviceInfoHelper.f3687d) {
            C0782a m399a3 = m399a(EventManager.REQUEST_TYPE.AUDIO, false);
            if (C0782a.m387a(m399a3)) {
                HashMap<Integer, ArrayList> addedMap = m391c(m399a3.added);
                EventManager.getInstance().m798a(EventManager.REQUEST_TYPE.AUDIO, SmartSyncProtocolProtos.SSPAudioLibraryChange
                        .newBuilder()
                        .setType(SmartSyncProtocolProtos.SSPRequestType.AUDIO_LIB_CHANGE)
                        .addAllAddedAudio(addedMap.get(1))
                        .addAllAddedAlbum(addedMap.get(2))
                        .addAllDeletedAudio(m391c(m399a3.deleted).get(1))
                        .build().toByteArray());
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00e2  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00fe  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x011a  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x01bc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x020c  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static ArrayList<SmartSyncProtocolProtos.SSPImageFile> m397a(List<ContentValues> list) {
        int intValue = 0;
        ArrayList<SmartSyncProtocolProtos.SSPImageFile> arrayList = new ArrayList<>();
        if (list == null) {
            return arrayList;
        }
        for (ContentValues contentValues : list) {
            if (contentValues.get("orientation") != null) {
                try {
                    intValue = ((Long) contentValues.get("orientation")).intValue();
                } catch (ClassCastException e) {
                    HandShaker.error("Fail to convert to long:" + contentValues.get("orientation") + " exception:" + e.toString());
                }
                arrayList.add(SmartSyncProtocolProtos.SSPImageFile.newBuilder()
                        .setPath(contentValues.get("_data") != null ? "" : (String) contentValues.get("_data"))
                        .setFileSize(CommonUtils.m512a(contentValues, "_size"))
                        .setCreatedTimestamp(contentValues.get("date_added") != null ? 0L : ((Long) contentValues.get("date_added")).longValue())
                        .setModifiedTimestamp(contentValues.get("date_modified") != null ? 0L : ((Long) contentValues.get("date_modified")).longValue())
                        .setWidth(contentValues.get("width") != null ? 0 : ((Long) contentValues.get("width")).intValue())
                        .setHeight(contentValues.get("height") != null ? 0 : ((Long) contentValues.get("height")).intValue())
                        .setOrientation(intValue)
                        .setMediaId(contentValues.get("_id") != null ? 0L : ((Long) contentValues.get("_id")).longValue())
                        .setAlbumId(contentValues.get("bucket_id") != null ? 0L : Long.parseLong((String) contentValues.get("bucket_id")))
                        .setMimeType(contentValues.get("mime_type") != null ? "" : (String) contentValues.get("mime_type"))
                        .setThumbnail(contentValues.get("_display_name") != null ? ByteString.copyFromUtf8("") : ByteString.copyFromUtf8((String) contentValues.get("_display_name")))
                        .setAlbumName(contentValues.get("bucket_display_name") != null ? "" : (String) contentValues.get("bucket_display_name"))
                        .setDateTaken(contentValues.get("datetaken") != null ? 0L : CommonUtils.m512a(contentValues, "datetaken"))
                        .setLatitude(contentValues.get("latitude") != null ? "" : String.valueOf(contentValues.get("latitude")))
                        .setLongitude(contentValues.get("longitude") != null ? "" : String.valueOf(contentValues.get("longitude")))
                        .setMiniThumbMagic(contentValues.get("mini_thumb_magic") != null ? "" : String.valueOf((Long) contentValues.get("mini_thumb_magic")))
                        .setTitle(contentValues.get("title") != null ? "" : (String) contentValues.get("title"))
                        .setStarred(contentValues.getAsBoolean("star") != null ? false : contentValues.getAsBoolean("star").booleanValue())
                        .build());
            }
            intValue = 0;
            arrayList.add(SmartSyncProtocolProtos.SSPImageFile.newBuilder()
                    .setPath(contentValues.get("_data") != null ? "" : (String) contentValues.get("_data"))
                    .setFileSize(CommonUtils.m512a(contentValues, "_size"))
                    .setCreatedTimestamp(contentValues.get("date_added") != null ? 0L : ((Long) contentValues.get("date_added")).longValue())
                    .setModifiedTimestamp(contentValues.get("date_modified") != null ? 0L : ((Long) contentValues.get("date_modified")).longValue())
                    .setWidth(contentValues.get("width") != null ? 0 : ((Long) contentValues.get("width")).intValue())
                    .setHeight(contentValues.get("height") != null ? 0 : ((Long) contentValues.get("height")).intValue())
                    .setOrientation(intValue)
                    .setMediaId(contentValues.get("_id") != null ? 0L : ((Long) contentValues.get("_id")).longValue())
                    .setAlbumId(contentValues.get("bucket_id") != null ? 0L : Long.parseLong((String) contentValues.get("bucket_id")))
                    .setMimeType(contentValues.get("mime_type") != null ? "" : (String) contentValues.get("mime_type"))
                    .setThumbnail(contentValues.get("_display_name") != null ? ByteString.copyFromUtf8("") : ByteString.copyFromUtf8((String) contentValues.get("_display_name")))
                    .setAlbumName(contentValues.get("bucket_display_name") != null ? "" : (String) contentValues.get("bucket_display_name"))
                    .setDateTaken(contentValues.get("datetaken") != null ? 0L : CommonUtils.m512a(contentValues, "datetaken"))
                    .setLatitude(contentValues.get("latitude") != null ? "" : String.valueOf(contentValues.get("latitude")))
                    .setLongitude(contentValues.get("longitude") != null ? "" : String.valueOf(contentValues.get("longitude")))
                    .setMiniThumbMagic(contentValues.get("mini_thumb_magic") != null ? "" : String.valueOf((Long) contentValues.get("mini_thumb_magic")))
                    .setTitle(contentValues.get("title") != null ? "" : (String) contentValues.get("title"))
                    .setStarred(contentValues.getAsBoolean("star") != null ? false : contentValues.getAsBoolean("star").booleanValue())
                    .build());
        }
        return arrayList;
    }

    /* renamed from: b */
    private static ArrayList<SmartSyncProtocolProtos.SSPVideoFile> contentValuesToVideoFiles(List<ContentValues> list) {
        ArrayList<SmartSyncProtocolProtos.SSPVideoFile> arrayList = new ArrayList<>();
        if (list == null) {
            return arrayList;
        }
        for (ContentValues contentValues : list) {
            arrayList.add(SmartSyncProtocolProtos.SSPVideoFile.newBuilder()
                    .setPath(contentValues.get("_data") == null ? "" : (String) contentValues.get("_data"))
                    .setFileSize(contentValues.get("_size") == null ? 0L : ((Long) contentValues.get("_size")).longValue())
                    .setCreatedTimestamp(contentValues.get("date_added") == null ? 0 : ((Long) contentValues.get("date_added")).intValue())
                    .setModifiedTimestamp(contentValues.get("date_modified") == null ? 0 : ((Long) contentValues.get("date_modified")).intValue())
                    .setWidth(contentValues.get("width") == null ? 0 : ((Long) contentValues.get("width")).intValue())
                    .setHeight(contentValues.get("height") == null ? 0 : ((Long) contentValues.get("height")).intValue())
                    .setMediaId(contentValues.get("_id") == null ? 0L : ((Long) contentValues.get("_id")).longValue())
                    .setAlbumId(contentValues.get("bucket_id") == null ? 0L : Long.parseLong((String) contentValues.get("bucket_id")))
                    .setMimeType(contentValues.get("mime_type") == null ? "" : (String) contentValues.get("mime_type"))
                    .setThumbnail(contentValues.get("_display_name") == null ? ByteString.copyFromUtf8("") : ByteString.copyFromUtf8((String) contentValues.get("_display_name")))
                    .setDuration(contentValues.get("duration") == null ? 0.0d : ((Long) contentValues.get("duration")).doubleValue() / 1000.0d)
                    .build());
        }
        return arrayList;
    }

    /* renamed from: c */
    private static HashMap<Integer, ArrayList> m391c(List<ContentValues> list) {
        HashMap<Integer, ArrayList> hashMap = new HashMap<>();
        ArrayList arrayList = new ArrayList();
        HashMap hashMap2 = new HashMap();
        if (list != null) {
            for (ContentValues contentValues : list) {
                SmartSyncProtocolProtos.SSPAudioFile sspAudioFile = SmartSyncProtocolProtos.SSPAudioFile.newBuilder()
                        .setPath(contentValues.get("_data") == null ? "" : (String) contentValues.get("_data"))
                        .setFileSize(contentValues.get("_size") == null ? 0L : ((Long) contentValues.get("_size")).longValue())
                        .setCreatedTimestamp(contentValues.get("date_added") == null ? 0L : ((Long) contentValues.get("date_added")).intValue())
                        .setModifiedTimestamp(contentValues.get("date_modified") == null ? 0L : ((Long) contentValues.get("date_modified")).intValue())
                        .setMediaId(contentValues.get("_id") == null ? 0L : ((Long) contentValues.get("_id")).longValue())
                        .setAlbumId(CommonUtils.m512a(contentValues, "album_id"))
                        .setArtistId(contentValues.containsKey("artist_id") ? contentValues.getAsLong("artist_id").longValue() : 0L)
                        .setArtist(contentValues.containsKey("artist") ? contentValues.getAsString("artist") : "")
                        .setMimeType(contentValues.get("mime_type") == null ? "" : (String) contentValues.get("mime_type"))
                        .setTitle(contentValues.get("title") == null ? "" : (String) contentValues.get("title"))
                        .setTrack(contentValues.get("track") == null ? 0 : ((Long) contentValues.get("track")).intValue())
                        .setYear(contentValues.get("year") == null ? 0 : ((Long) contentValues.get("year")).intValue())
                        .setComposer(contentValues.get("composer") == null ? "" : (String) contentValues.get("composer"))
                        .setGenre(contentValues.get("genre_id") == null ? 0 : ((Long) contentValues.get("genre_id")).intValue())
                        .setDuration(contentValues.get("duration") == null ? 0.0d : ((Long) contentValues.get("duration")).doubleValue() / 1000.0d)
                        .setGenreName(contentValues.get("genre_name") == null ? "" : contentValues.getAsString("genre_name")).build();
                arrayList.add(sspAudioFile);
                long albumId = sspAudioFile.getAlbumId();
                if (!hashMap2.containsKey(Long.valueOf(albumId))) {
                    String path = sspAudioFile.getPath();
                    hashMap2.put(Long.valueOf(albumId), SmartSyncProtocolProtos.SSPAudioAlbum.newBuilder()
                            .setAlbumId(albumId)
                            .setAlbumPath(path.substring(0, path.lastIndexOf("/")))
                            .setYear(sspAudioFile.getYear())
                            .setAlbumName(contentValues.containsKey("album") ? contentValues.getAsString("album") : "")
                            .setArtist(sspAudioFile.getArtist())
                            .setArtistId(sspAudioFile.getArtistId()).build());
                }
            }
        }
        ArrayList arrayList2 = new ArrayList(hashMap2.values());
        hashMap.put(1, arrayList);
        hashMap.put(2, arrayList2);
        return hashMap;
    }

    /* renamed from: a */
    private synchronized C0782a m399a(EventManager.REQUEST_TYPE enumC0705a, boolean z) {
        C0782a c0782a;
        c0782a = new C0782a(this, (byte) 0);
        List<ContentValues> m400a = m400a(enumC0705a);
        List<ContentValues> list = this.f3771i.get(enumC0705a);
        c0782a.added = m396a(m400a, list);
        c0782a.deleted = m396a(list, m400a);
        if (z) {
            if (!c0782a.added.isEmpty()) {
                this.f3770h = new ArrayList<>();
                this.f3770h.addAll(c0782a.added);
            } else {
                List<ContentValues> m393b = m393b(m400a, this.f3770h);
                if (m393b.size() > 0) {
                    c0782a.updated = m393b;
                    this.f3770h.clear();
                }
            }
        }
        if (C0782a.m387a(c0782a)) {
            this.f3771i.put(enumC0705a, m400a);
        }
        return c0782a;
    }

    /* renamed from: a */
    private static List<ContentValues> m400a(EventManager.REQUEST_TYPE enumC0705a) {
        Map<String, ContentValues> m709e;
        List<ContentValues> m717a = MediaUtils.m717a(enumC0705a);
        if (CommonUtils.m484r() && enumC0705a == EventManager.REQUEST_TYPE.IMAGE && (m709e = MediaUtils.m709e()) != null) {
            HandShaker.debug(f3763b, "getMediaValues photoInfos.size:" + m709e.size() + ", values.size:" + m717a.size());
            for (ContentValues contentValues : m717a) {
                ContentValues contentValues2 = m709e.get(contentValues.getAsString("_data"));
                if (contentValues2 != null) {
                    contentValues.putAll(contentValues2);
                }
            }
        }
        return m717a;
    }

    /* renamed from: a */
    private ArrayList<ContentValues> m396a(List<ContentValues> list, List<ContentValues> list2) {
        ArrayList<ContentValues> arrayList = new ArrayList<>();
        for (ContentValues contentValues : list) {
            if (Collections.binarySearch(list2, contentValues, this.f3766a) < 0) {
                arrayList.add(contentValues);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private List<ContentValues> m393b(List<ContentValues> list, List<ContentValues> list2) {
        int binarySearch;
        ArrayList arrayList = new ArrayList();
        if (list != null && list2 != null) {
            for (ContentValues contentValues : list2) {
                if (contentValues != null && (binarySearch = Collections.binarySearch(list, contentValues, this.f3766a)) >= 0) {
                    arrayList.add(list.get(binarySearch));
                }
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    public final synchronized void m395b() {
        this.f3771i.put(EventManager.REQUEST_TYPE.IMAGE, m400a(EventManager.REQUEST_TYPE.IMAGE));
        this.f3771i.put(EventManager.REQUEST_TYPE.VIDEO, m400a(EventManager.REQUEST_TYPE.VIDEO));
        this.f3771i.put(EventManager.REQUEST_TYPE.AUDIO, m400a(EventManager.REQUEST_TYPE.AUDIO));
        this.f3769g = new HandlerThread("MediaProviderContentObserver --> ");
        this.f3769g.start();
        this.f3767e = new Handler(this.f3769g.getLooper(), new C0785x(this));
        this.f3768f = new C0783b(this.f3767e);
        C0783b c0783b = this.f3768f;
        ContentResolver contentResolver = FolderApp.getInstance().getContentResolver();
        contentResolver.registerContentObserver(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, true, c0783b);
        contentResolver.registerContentObserver(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, true, c0783b);
        contentResolver.registerContentObserver(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, true, c0783b);
        contentResolver.registerContentObserver(MediaUtils.f3507a, true, c0783b);
        contentResolver.registerContentObserver(MediaUtils.f3509c, true, c0783b);
        contentResolver.registerContentObserver(MediaUtils.f3508b, true, c0783b);
        HandShaker.debug(f3763b, "MediaObserver...onStart()");
    }

    /* renamed from: c */
    public final synchronized void m392c() {
        FolderApp.getInstance().getContentResolver().unregisterContentObserver(this.f3768f);
        this.f3769g.quit();
        this.f3769g = null;
        this.f3767e = null;
        HandShaker.debug(f3763b, "MediaObserver...onStop()");
    }

    /* renamed from: d */
    public final synchronized void m390d() {
        if (this.f3767e != null && !this.f3767e.hasMessages(1) && !SyncManager.getInstance().m651b()) {
            this.f3767e.sendEmptyMessageDelayed(1, 1000L);
        }
    }

    /* compiled from: MediaDataProvider.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.v$b */
    /* loaded from: classes.dex */
    private class C0783b extends ContentObserver {
        C0783b(Handler handler) {
            super(handler);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z, Uri uri) {
            HandShaker.info(MediaDataProvider.f3763b, "MediaObserver...onChange(boolean, uri:[" + uri + "])");
            super.onChange(z, uri);
        }

        @Override // android.database.ContentObserver
        public final void onChange(boolean z) {
            HandShaker.info(MediaDataProvider.f3763b, "MediaObserver...onChange(boolean)");
            MediaDataProvider.this.m390d();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MediaDataProvider.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.h.v$a */
    /* loaded from: classes.dex */
    public static class C0782a {

        /* renamed from: b */
        private List<ContentValues> added;

        /* renamed from: c */
        private List<ContentValues> updated;

        /* renamed from: d */
        private List<ContentValues> deleted;

        private C0782a() {
        }

        /* synthetic */ C0782a(MediaDataProvider mediaDataProvider, byte b) {
            this();
        }

        /* renamed from: a */
        private static boolean isEmpty(List<ContentValues> list) {
            return list == null || list.isEmpty();
        }

        /* renamed from: a */
        /* synthetic */
        static boolean m387a(C0782a c0782a) {
            return (isEmpty(c0782a.added) && isEmpty(c0782a.updated) && isEmpty(c0782a.deleted)) ? false : true;
        }
    }
}
