package com.smartisanos.smartfolder.aoa.p052d;

import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import com.google.protobuf.ByteString;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p051c.TerminatedException;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.HttpStatus;

/* renamed from: com.smartisanos.smartfolder.aoa.d.h */
/* loaded from: classes.dex */
public final class ThumbnailHandler extends BaseHandler {

    /* renamed from: c */
    private final AtomicBoolean f3517c = new AtomicBoolean(false);

    /* renamed from: f */
    private final ArrayList<Future> f3518f = new ArrayList<>();

    /* renamed from: b */
    private static final Uri f3514b = Uri.parse("content://media/external/audio/albumart");

    /* renamed from: d */
    private static ThumbnailHandler f3515d = null;

    /* renamed from: e */
    private static final ExecutorService f3516e = Executors.newFixedThreadPool(10);

    /* renamed from: a */
    public static int f3513a = 86;

    /* renamed from: a */
    public static ThumbnailHandler m703a() {
        if (f3515d == null) {
            f3515d = new ThumbnailHandler();
        }
        return f3515d;
    }

    private ThumbnailHandler() {
    }

    /* renamed from: a */
    public final ArrayList<ArrayList> m702a(SmartSyncProtocolProtos.SSPGetThumbnailRequest sspGetThumbnailRequest) {
        ArrayList<ArrayList> arrayList = new ArrayList<>();
        List<SmartSyncProtocolProtos.SSPImageFile> imageList = sspGetThumbnailRequest.getImageList();
        List<SmartSyncProtocolProtos.SSPVideoFile> videoList = sspGetThumbnailRequest.getVideoList();
        List<SmartSyncProtocolProtos.SSPAudioAlbum> audioAlbumList = sspGetThumbnailRequest.getAudioAlbumList();
        int size = imageList.size();
        int size2 = videoList.size();
        int size3 = audioAlbumList.size();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (SmartSyncProtocolProtos.SSPImageFile SSPImageFile : imageList) {
            this.f3518f.add(f3516e.submit(new RunnableC0733b(countDownLatch, SSPImageFile, arrayList2, this.f3517c)));
            if (this.f3517c.get()) {
                throw new TerminatedException();
            }
        }
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        arrayList.add(arrayList2);
        CountDownLatch countDownLatch2 = new CountDownLatch(size2);
        for (SmartSyncProtocolProtos.SSPVideoFile SSPVideoFile : videoList) {
            this.f3518f.add(f3516e.submit(new RunnableC0734c(countDownLatch2, SSPVideoFile, arrayList3, this.f3517c)));
            if (this.f3517c.get()) {
                throw new TerminatedException();
            }
        }
        try {
            countDownLatch2.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        arrayList.add(arrayList3);
        CountDownLatch countDownLatch3 = new CountDownLatch(size3);
        for (SmartSyncProtocolProtos.SSPAudioAlbum SSPAudioAlbum : audioAlbumList) {
            this.f3518f.add(f3516e.submit(new RunnableC0732a(countDownLatch3, SSPAudioAlbum, arrayList4, this.f3517c)));
            if (this.f3517c.get()) {
                throw new TerminatedException();
            }
        }
        try {
            countDownLatch3.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        arrayList.add(arrayList4);
        return arrayList;
    }

    /* compiled from: ThumbnailHandler.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.h$b */
    /* loaded from: classes.dex */
    public static class RunnableC0733b implements Runnable {

        /* renamed from: a */
        private final AtomicBoolean atomicBoolean;

        /* renamed from: b */
        private ArrayList f3525b;

        /* renamed from: c */
        private CountDownLatch countDownLatch;

        /* renamed from: d */
        private long mediaId;

        /* renamed from: e */
        private SmartSyncProtocolProtos.SSPImageFile sspImageFile;

        public RunnableC0733b(CountDownLatch countDownLatch, SmartSyncProtocolProtos.SSPImageFile sspImageFile, ArrayList arrayList, AtomicBoolean atomicBoolean) {
            this.countDownLatch = countDownLatch;
            this.mediaId = sspImageFile.getMediaId();
            this.f3525b = arrayList;
            this.sspImageFile = sspImageFile;
            this.atomicBoolean = atomicBoolean;
            HandShaker.info("ThumbnailHandler", "getThumbnailBitmap : (" + this.mediaId + ") Thread ->  +"
                    + Thread.currentThread().getName());
        }

        @Override // java.lang.Runnable
        public final void run() {
            SmartSyncProtocolProtos.SSPImageFile m695a;
            try {
                if (!this.atomicBoolean.get()) {
                    if (this.mediaId != 0) {
                        m695a = m696a(this.mediaId);
                    } else {
                        long m693b = m693b(this.sspImageFile.getPath());
                        if (m693b != 0) {
                            m695a = m696a(m693b);
                        } else {
                            m695a = m695a(this.sspImageFile.getPath());
                        }
                    }
                    this.f3525b.add(SmartSyncProtocolProtos.SSPImageFile.newBuilder()
                            .setPath(this.sspImageFile.getPath())
                            .setFileSize(this.sspImageFile.getFileSize())
                            .setCreatedTimestamp(this.sspImageFile.getCreatedTimestamp())
                            .setModifiedTimestamp(this.sspImageFile.getModifiedTimestamp())
                            .setWidth(this.sspImageFile.getWidth())
                            .setHeight(this.sspImageFile.getHeight())
                            .setOrientation(this.sspImageFile.getOrientation())
                            .setMediaId(this.mediaId)
                            .setAlbumId(this.sspImageFile.getAlbumId())
                            .setMimeType(this.sspImageFile.getMimeType())
                            .setThumbnail(m695a.getThumbnail())
                            .setGetThumbnailError(m695a.getGetThumbnailError())
                            .setAlbumName(this.sspImageFile.getAlbumName()).build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.countDownLatch.countDown();
            }
        }

        /* renamed from: a */
        private static SmartSyncProtocolProtos.SSPImageFile m696a(long j) {
            SmartSyncProtocolProtos.SSPImageFile.Builder m1185y = SmartSyncProtocolProtos.SSPImageFile.newBuilder();
            try {
                byte[] thumbnail = getThumbnail(j);
                if (thumbnail != null) {
                    m1185y.setThumbnail(ByteString.copyFrom(thumbnail));
                    m1185y.setGetThumbnailError(false);
                } else {
                    m1185y.setThumbnail(ByteString.copyFrom(new byte[0]));
                    m1185y.setGetThumbnailError(true);
                }
            } catch (Exception e) {
                m1185y.setThumbnail(ByteString.copyFrom(new byte[0]));
                m1185y.setGetThumbnailError(true);
                e.printStackTrace();
            }
            return m1185y.build();
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x0089 A[Catch: Exception -> 0x00a3, TRY_LEAVE, TryCatch #0 {Exception -> 0x00a3, blocks: (B:3:0x000a, B:5:0x0030, B:7:0x003a, B:8:0x003d, B:19:0x0071, B:21:0x0089, B:33:0x00bc, B:12:0x004f, B:14:0x0056, B:15:0x005b, B:17:0x006c, B:26:0x009f, B:32:0x00b8), top: B:37:0x000a }] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x00bc A[Catch: Exception -> 0x00a3, TRY_LEAVE, TryCatch #0 {Exception -> 0x00a3, blocks: (B:3:0x000a, B:5:0x0030, B:7:0x003a, B:8:0x003d, B:19:0x0071, B:21:0x0089, B:33:0x00bc, B:12:0x004f, B:14:0x0056, B:15:0x005b, B:17:0x006c, B:26:0x009f, B:32:0x00b8), top: B:37:0x000a }] */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static SmartSyncProtocolProtos.SSPImageFile m695a(String str) throws IOException {
            Bitmap decodeFile = null;
            byte[] bArr;
            Bitmap createBitmap;
            SmartSyncProtocolProtos.SSPImageFile.Builder m1185y = SmartSyncProtocolProtos.SSPImageFile.newBuilder();
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, options);
                int min = Math.min(options.outWidth / HttpStatus.SC_OK, options.outHeight / HttpStatus.SC_OK);
                options.inJustDecodeBounds = false;
                options.inSampleSize = min;
                options.inPurgeable = true;
                decodeFile = BitmapFactory.decodeFile(str, options);
            } catch (Exception e) {
                m1185y.setThumbnail(ByteString.copyFrom(new byte[0]));
                m1185y.setGetThumbnailError(true);
                e.printStackTrace();
            }
            if (decodeFile != null) {
                Bitmap extractThumbnail = ThumbnailUtils.extractThumbnail(decodeFile, HttpStatus.SC_OK, HttpStatus.SC_OK);
                if (extractThumbnail != decodeFile) {
                    decodeFile.recycle();
                }
                int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
                if (attributeInt == 3 || attributeInt == 6 || attributeInt == 8) {
                    Matrix matrix = new Matrix();
                    if (attributeInt == 3) {
                        matrix.postRotate(180.0f);
                    } else if (attributeInt == 6) {
                        matrix.postRotate(90.0f);
                    } else if (attributeInt == 8) {
                        matrix.postRotate(270.0f);
                    }
                    createBitmap = Bitmap.createBitmap(extractThumbnail, 0, 0, extractThumbnail.getWidth(), extractThumbnail.getHeight(), matrix, true);
                    if (createBitmap != extractThumbnail) {
                        extractThumbnail.recycle();
                    }
                } else {
                    createBitmap = extractThumbnail;
                }
                if (createBitmap != null) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    createBitmap.compress(Bitmap.CompressFormat.JPEG, ThumbnailHandler.f3513a, byteArrayOutputStream);
                    bArr = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    createBitmap.recycle();
                    if (bArr == null) {
                        m1185y.setThumbnail(ByteString.copyFrom(bArr));
                        m1185y.setGetThumbnailError(false);
                    } else {
                        m1185y.setThumbnail(ByteString.copyFrom(new byte[0]));
                        m1185y.setGetThumbnailError(true);
                    }
                    return m1185y.build();
                }
            }
            bArr = null;
            if (bArr == null) {
            }
            return m1185y.build();
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0035  */
        /* renamed from: b */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static long m693b(String str) {
            Cursor cursor;
            long j = 0;
            try {
                cursor = FolderApp.getInstance().getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id"}, "_data=?", new String[]{str}, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            j = cursor.getLong(0);
                            if (cursor != null) {
                                cursor.close();
                            }
                            return j;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                j = 0;
                if (cursor != null) {
                }
                return j;
            } catch (Throwable th2) {

                cursor = null;
            }
            return j;
        }

        /* renamed from: b */
        private static byte[] getThumbnail(long j) {
            byte[] bArr = null;
            Bitmap thumbnail = MediaStore.Images.Thumbnails.getThumbnail(FolderApp.getInstance().getContentResolver(), j, 1, null);
            if (thumbnail != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, ThumbnailHandler.f3513a, byteArrayOutputStream);
                try {
                    try {
                        byteArrayOutputStream.flush();
                        bArr = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        if (thumbnail != null && !thumbnail.isRecycled()) {
                            thumbnail.recycle();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (thumbnail != null && !thumbnail.isRecycled()) {
                            thumbnail.recycle();
                        }
                    }
                } catch (Throwable th) {
                    if (thumbnail != null && !thumbnail.isRecycled()) {
                        thumbnail.recycle();
                    }
                    throw th;
                }
            }
            return bArr;
        }
    }

    /* compiled from: ThumbnailHandler.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.h$c */
    /* loaded from: classes.dex */
    public static class RunnableC0734c implements Runnable {

        /* renamed from: a */
        private final AtomicBoolean atomicBoolean;

        /* renamed from: b */
        private ArrayList f3530b;

        /* renamed from: c */
        private CountDownLatch countDownLatch;

        /* renamed from: d */
        private long mediaId;

        /* renamed from: e */
        private SmartSyncProtocolProtos.SSPVideoFile sspVideoFile;

        public RunnableC0734c(CountDownLatch countDownLatch, SmartSyncProtocolProtos.SSPVideoFile sspVideoFile, ArrayList arrayList, AtomicBoolean atomicBoolean) {
            this.countDownLatch = countDownLatch;
            this.mediaId = sspVideoFile.getMediaId();
            this.f3530b = arrayList;
            this.sspVideoFile = sspVideoFile;
            this.atomicBoolean = atomicBoolean;
            HandShaker.info("ThumbnailHandler", "getThumbnailBitmap : (" + this.mediaId + ") Thread ->  +" + Thread.currentThread().getName());
        }

        @Override // java.lang.Runnable
        public final void run() {
            SmartSyncProtocolProtos.SSPVideoFile m691a;
            try {
                if (!this.atomicBoolean.get()) {
                    if (this.mediaId != 0) {
                        m691a = getThumbnail(this.mediaId);
                    } else {
                        long mediaId = getMediaId(this.sspVideoFile.getPath());
                        if (mediaId != 0) {
                            m691a = getThumbnail(mediaId);
                        } else {
                            m691a = m691a(this.sspVideoFile.getPath());
                        }
                    }
                    this.f3530b.add(SmartSyncProtocolProtos.SSPVideoFile
                            .newBuilder()
                            .setPath(this.sspVideoFile.getPath())
                            .setFileSize(this.sspVideoFile.getFileSize())
                            .setCreatedTimestamp(this.sspVideoFile.getCreatedTimestamp())
                            .setModifiedTimestamp(this.sspVideoFile.getModifiedTimestamp())
                            .setWidth(this.sspVideoFile.getWidth())
                            .setHeight(this.sspVideoFile.getHeight())
                            .setOrientation(this.sspVideoFile.getOrientation())
                            .setMediaId(this.mediaId)
                            .setAlbumId(this.sspVideoFile.getAlbumId())
                            .setMimeType(this.sspVideoFile.getMimeType())
                            .setDuration(this.sspVideoFile.getDuration())
                            .setThumbnail(m691a.getThumbnail())
                            .setGetThumbnailError(m691a.getGetThumbnailError())
                            .build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.countDownLatch.countDown();
            }
        }

        /* renamed from: a */
        private static SmartSyncProtocolProtos.SSPVideoFile getThumbnail(long mediaId) {
            SmartSyncProtocolProtos.SSPVideoFile.Builder builder = SmartSyncProtocolProtos.SSPVideoFile.newBuilder();
            byte[] thumbnail = getMediaId(mediaId);
            if (thumbnail != null) {
                builder.setThumbnail(ByteString.copyFrom(thumbnail));
                builder.setGetThumbnailError(false);
            } else {
                builder.setThumbnail(ByteString.copyFrom(new byte[0]));
                builder.setGetThumbnailError(true);
            }
            return builder.build();
        }

        /* renamed from: a */
        private static SmartSyncProtocolProtos.SSPVideoFile m691a(String str) {
            SmartSyncProtocolProtos.SSPVideoFile.Builder m943y = SmartSyncProtocolProtos.SSPVideoFile.newBuilder();
            try {
                Bitmap m689b = m689b(str);
                byte[] bArr = null;
                if (m689b != null) {
                    Bitmap extractThumbnail = ThumbnailUtils.extractThumbnail(m689b, HttpStatus.SC_OK, HttpStatus.SC_OK);
                    if (extractThumbnail != m689b) {
                        m689b.recycle();
                    }
                    int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
                    Bitmap bitmap = null;
                    if (attributeInt == 3 || attributeInt == 6 || attributeInt == 8) {
                        Matrix matrix = new Matrix();
                        if (attributeInt == 3) {
                            matrix.postRotate(180.0f);
                        } else if (attributeInt == 6) {
                            matrix.postRotate(90.0f);
                        } else if (attributeInt == 8) {
                            matrix.postRotate(270.0f);
                        }
                        bitmap = Bitmap.createBitmap(extractThumbnail, 0, 0, extractThumbnail.getWidth(), extractThumbnail.getHeight(), matrix, true);
                        if (bitmap != extractThumbnail) {
                            extractThumbnail.recycle();
                        }
                    }
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, ThumbnailHandler.f3513a, byteArrayOutputStream);
                    bArr = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    bitmap.recycle();
                }
                if (bArr != null) {
                    m943y.setThumbnail(ByteString.copyFrom(bArr));
                    m943y.setGetThumbnailError(false);
                } else {
                    m943y.setThumbnail(ByteString.copyFrom(new byte[0]));
                    m943y.setGetThumbnailError(true);
                }
            } catch (Exception e) {
                m943y.setThumbnail(ByteString.copyFrom(new byte[0]));
                m943y.setGetThumbnailError(true);
                e.printStackTrace();
            }
            return m943y.build();
        }

        /* renamed from: b */
        private static Bitmap m689b(String str) {
            Bitmap bitmap = null;
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            try {
                mediaMetadataRetriever.setDataSource(str);
                bitmap = mediaMetadataRetriever.getFrameAtTime(-1L);
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException e) {
                }
            } catch (IllegalArgumentException e2) {
                try {
                    mediaMetadataRetriever.release();
                    bitmap = null;
                } catch (RuntimeException | IOException e3) {
                    bitmap = null;
                }
            } catch (RuntimeException e4) {
                try {
                    mediaMetadataRetriever.release();
                    bitmap = null;
                } catch (RuntimeException | IOException e5) {
                    bitmap = null;
                }
            } catch (Throwable th) {
                try {
                    mediaMetadataRetriever.release();
                } catch (RuntimeException | IOException e6) {
                }

            }
            if (bitmap != null) {
                float width = bitmap.getWidth() < bitmap.getHeight() ? 200.0f / bitmap.getWidth() : 200.0f / bitmap.getHeight();
                Matrix matrix = new Matrix();
                matrix.setScale(width, width);
                bitmap.getWidth();
                bitmap.getHeight();
                float width2 = bitmap.getWidth();
                float height = bitmap.getHeight();
                if (width2 / height > 1.0f) {
                    float f = 200.0f / height;
                    if (f < 0.9f || f > 1.0f) {
                        matrix.setScale(f, f);
                    } else {
                        matrix = null;
                    }
                } else {
                    float f2 = 200.0f / width2;
                    if (f2 < 0.9f || f2 > 1.0f) {
                        matrix.setScale(f2, f2);
                    } else {
                        matrix = null;
                    }
                }
                Bitmap createBitmap = matrix != null ? Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true) : bitmap;
                Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, Math.max(0, createBitmap.getWidth() - 200) / 2, Math.max(0, createBitmap.getHeight() - 200) / 2, (int) HttpStatus.SC_OK, (int) HttpStatus.SC_OK);
                if (createBitmap2 != createBitmap && createBitmap != bitmap) {
                    createBitmap.recycle();
                }
                return createBitmap2;
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0035  */
        /* renamed from: c */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private static long getMediaId(String str) {
            Cursor cursor;
            long j = 0;
            try {
                cursor = FolderApp.getInstance().getContentResolver().query(MediaStore.Files.getContentUri("external"), new String[]{"_id"}, "_data=?", new String[]{str}, null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            j = cursor.getLong(0);
                            if (cursor != null) {
                                cursor.close();
                            }
                            return j;
                        }
                    } catch (Throwable th) {
                        th = th;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                j = 0;
                if (cursor != null) {
                }
                return j;
            } catch (Throwable th2) {

                cursor = null;
            }
            return j;
        }

        /* renamed from: b */
        private static byte[] getMediaId(long j) {
            byte[] bArr = null;
            Bitmap thumbnail = MediaStore.Video.Thumbnails.getThumbnail(FolderApp.getInstance().getContentResolver(), j, 1, null);
            if (thumbnail != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                thumbnail.compress(Bitmap.CompressFormat.JPEG, ThumbnailHandler.f3513a, byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
                thumbnail.recycle();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return bArr;
        }
    }

    /* compiled from: ThumbnailHandler.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.h$a */
    /* loaded from: classes.dex */
    public static class RunnableC0732a implements Runnable {

        /* renamed from: a */
        private final AtomicBoolean atomicBoolean;

        /* renamed from: b */
        private ArrayList f3520b;

        /* renamed from: c */
        private CountDownLatch countDownLatch;

        /* renamed from: d */
        private long albumId;

        /* renamed from: e */
        private SmartSyncProtocolProtos.SSPAudioAlbum sspAudioAlbum;

        public RunnableC0732a(CountDownLatch countDownLatch, SmartSyncProtocolProtos.SSPAudioAlbum sspAudioAlbum, ArrayList arrayList, AtomicBoolean atomicBoolean) {
            this.countDownLatch = countDownLatch;
            this.albumId = sspAudioAlbum.getAlbumId();
            this.f3520b = arrayList;
            this.sspAudioAlbum = sspAudioAlbum;
            this.atomicBoolean = atomicBoolean;
            HandShaker.info("ThumbnailHandler", "getThumbnailBitmap : (" + this.albumId + ") Thread ->  +" + Thread.currentThread().getName());
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                if (!this.atomicBoolean.get()) {
                    SmartSyncProtocolProtos.SSPAudioAlbum m699a = m699a(this.albumId);
                    this.f3520b.add(SmartSyncProtocolProtos.SSPAudioAlbum.newBuilder()
                            .setAlbumId(this.albumId)
                            .setAlbumName(this.sspAudioAlbum.getAlbumName())
                            .setArtist(this.sspAudioAlbum.getArtist())
                            .setYear(this.sspAudioAlbum.getYear())
                            .setThumbnail(m699a.getThumbnail())
                            .setGetThumbnailError(m699a.getGetThumbnailError()).build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.countDownLatch.countDown();
            }
        }

        /* renamed from: a */
        private static SmartSyncProtocolProtos.SSPAudioAlbum m699a(long j) {
            SmartSyncProtocolProtos.SSPAudioAlbum.Builder m1815r = SmartSyncProtocolProtos.SSPAudioAlbum.newBuilder();
            byte[] m698b = m698b(j);
            if (m698b != null) {
                m1815r.setThumbnail(ByteString.copyFrom(m698b));
                m1815r.setGetThumbnailError(false);
            } else {
                m1815r.setThumbnail(ByteString.copyFrom(new byte[0]));
                m1815r.setGetThumbnailError(true);
            }
            return m1815r.build();
        }

        /* renamed from: b */
        private static byte[] m698b(long j) {
            Bitmap m697c = m697c(j);
            byte[] bArr = null;
            if (m697c == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            m697c.compress(Bitmap.CompressFormat.JPEG, ThumbnailHandler.f3513a, byteArrayOutputStream);
            try {
                byteArrayOutputStream.flush();
                bArr = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return bArr;
            } catch (IOException e) {
                e.printStackTrace();
                return bArr;
            }
        }

        /* renamed from: c */
        private static Bitmap m697c(long j) {
            try {
                return MediaStore.Images.Media.getBitmap(FolderApp.getInstance().getContentResolver(), ContentUris.withAppendedId(ThumbnailHandler.f3514b, j));
            } catch (FileNotFoundException e) {
                HandShaker.debug("No entry for content://media/external/audio/albumart/" + j);
                return null;
            } catch (IOException e2) {
                e2.printStackTrace();
                return null;
            }
        }
    }

    /* renamed from: b */
    public final boolean m701b() {
        this.f3517c.set(true);
        synchronized (this) {
            Iterator<Future> it = this.f3518f.iterator();
            while (it.hasNext()) {
                it.next().cancel(true);
            }
        }
        return false;
    }
}
