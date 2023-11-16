package com.smartisanos.smartfolder.aoa.p052d;

import android.content.ClipData;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.google.protobuf.ByteString;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.protocol.SmartSyncProtocolProtos;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* renamed from: com.smartisanos.smartfolder.aoa.d.b */
/* loaded from: classes.dex */
public final class ClipboardHandler {

    /* renamed from: a */
    private static ClipboardHandler instance = null;

    /* renamed from: a */
    public static ClipboardHandler getInstance() {
        if (instance == null) {
            instance = new ClipboardHandler();
        }
        return instance;
    }

    private ClipboardHandler() {
    }

    /* renamed from: b */
    public final ArrayList<SmartSyncProtocolProtos.SSPClipboard> m756b() {
        ArrayList<SmartSyncProtocolProtos.SSPClipboard> arrayList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        C0724b c0724b = new C0724b(new RunnableC0726d(countDownLatch, arrayList), "getClip");
        c0724b.start();
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c0724b.m751a();
        return arrayList;
    }

    /* renamed from: c */
    public final ArrayList<Boolean> clearClipboard() {
        ArrayList<Boolean> arrayList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        C0724b c0724b = new C0724b(new RunnableC0723a(countDownLatch, arrayList), "clearClip");
        c0724b.start();
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c0724b.m751a();
        return arrayList;
    }

    /* renamed from: a */
    public final ArrayList<Boolean> m757a(SmartSyncProtocolProtos.SSPDeleteClipboardRequest generatedMessageLite) {
        ArrayList<Boolean> arrayList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        C0724b c0724b = new C0724b(new RunnableC0725c(countDownLatch, generatedMessageLite.getClipboard(), arrayList), "deleteClip");
        c0724b.start();
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c0724b.m751a();
        return arrayList;
    }

    /* renamed from: b */
    public final ArrayList<Boolean> m755b(SmartSyncProtocolProtos.SSPPostClipboardRequest sspPostClipboardRequest) {
        ArrayList<Boolean> arrayList = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        C0724b c0724b = new C0724b(new RunnableC0727e(countDownLatch, sspPostClipboardRequest.getClipboard(), arrayList)
                , "postClip");
        c0724b.start();
        try {
            countDownLatch.await(5L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        c0724b.m751a();
        return arrayList;
    }

    /* compiled from: ClipboardHandler.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.b$d */
    /* loaded from: classes.dex */
    public class RunnableC0726d implements Runnable {

        /* renamed from: b */
        private ArrayList<SmartSyncProtocolProtos.SSPClipboard> f3487b;

        /* renamed from: c */
        private CountDownLatch f3488c;

        public RunnableC0726d(CountDownLatch countDownLatch, ArrayList arrayList) {
            this.f3488c = countDownLatch;
            this.f3487b = arrayList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = 0;
            ArrayList<SmartSyncProtocolProtos.SSPClipboard> arrayList = this.f3487b;
            try {
                List list = (List) ClipboardHandler.m753d().getMethod("getCopyHistory", new Class[0]).invoke(ClipboardHandler.m752e(), new Object[0]);
                if (list.size() == 0) {
                    return;
                }
                while (true) {
                    int i2 = i;
                    if (i2 < list.size()) {
                        Object obj = list.get(i2);
                        Class<?> cls = Class.forName("android.content.CopyHistoryItem");
                        Field mContent = cls.getDeclaredField("mContent");
                        Field mTimeStamp = cls.getDeclaredField("mTimeStamp");
                        HandShaker.info("ClipboardHandler", "mContent=" + mContent.get(obj) + ", mTimeStampF=" + mTimeStamp.get(obj));
                        SmartSyncProtocolProtos.SSPClipboard.Builder builder = SmartSyncProtocolProtos.SSPClipboard.newBuilder();
                        builder.setContent(CommonUtils.compressToByteStringByGzip((String) mContent.get(obj)));
                        builder.setMstimestamp(((Long) mTimeStamp.get(obj)).longValue());
                        arrayList.add(builder.build());
                        i = i2 + 1;
                    } else {
                        return;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                this.f3488c.countDown();
            }
        }
    }

    /* compiled from: ClipboardHandler.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.b$a */
    /* loaded from: classes.dex */
    public class RunnableC0723a implements Runnable {

        /* renamed from: b */
        private ArrayList<Boolean> f3478b;

        /* renamed from: c */
        private CountDownLatch f3479c;

        public RunnableC0723a(CountDownLatch countDownLatch, ArrayList<Boolean> arrayList) {
            this.f3479c = countDownLatch;
            this.f3478b = arrayList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ArrayList<Boolean> arrayList = this.f3478b;
            try {
                try {
                    ClipboardHandler.m753d().getMethod("clearCopyHistory", new Class[0]).invoke(ClipboardHandler.m752e(), new Object[0]);
                    arrayList.add(true);
                    this.f3479c.countDown();
                } catch (Exception e) {
                    arrayList.add(false);
                    e.printStackTrace();
                    this.f3479c.countDown();
                }
            } catch (Throwable th) {
                this.f3479c.countDown();
                throw th;
            }
        }
    }

    /* compiled from: ClipboardHandler.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.b$c */
    /* loaded from: classes.dex */
    public class RunnableC0725c implements Runnable {

        /* renamed from: b */
        private ArrayList<Boolean> f3483b;

        /* renamed from: c */
        private CountDownLatch f3484c;

        /* renamed from: d */
        private SmartSyncProtocolProtos.SSPClipboard sspClipboard;

        public RunnableC0725c(CountDownLatch countDownLatch, SmartSyncProtocolProtos.SSPClipboard c0673n, ArrayList<Boolean> arrayList) {
            this.f3484c = countDownLatch;
            this.sspClipboard = c0673n;
            this.f3483b = arrayList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ByteString m885l = this.sspClipboard.getContent();
            long m884m = this.sspClipboard.getMstimestamp();
            ArrayList<Boolean> arrayList = this.f3483b;
            try {
                try {
                    String m510a = CommonUtils.decompressGzipFromByteString(m885l);
                    Class<?> cls = Class.forName("android.content.CopyHistoryItem");
                    ClipboardHandler.m753d().getMethod("delete", cls).invoke(ClipboardHandler.m752e(), cls.getConstructor(String.class, Long.TYPE).newInstance(m510a, Long.valueOf(m884m)));
                    arrayList.add(true);
                    this.f3484c.countDown();
                } catch (Exception e) {
                    arrayList.add(false);
                    e.printStackTrace();
                    this.f3484c.countDown();
                }
            } catch (Throwable th) {
                this.f3484c.countDown();
                throw th;
            }
        }
    }

    /* compiled from: ClipboardHandler.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.b$e */
    /* loaded from: classes.dex */
    public class RunnableC0727e implements Runnable {

        /* renamed from: b */
        private ArrayList<Boolean> f3490b;

        /* renamed from: c */
        private CountDownLatch f3491c;

        /* renamed from: d */
        private SmartSyncProtocolProtos.SSPClipboard f3492d;

        public RunnableC0727e(CountDownLatch countDownLatch, SmartSyncProtocolProtos.SSPClipboard c0673n, ArrayList<Boolean> arrayList) {
            this.f3491c = countDownLatch;
            this.f3492d = c0673n;
            this.f3490b = arrayList;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ByteString m885l = this.f3492d.getContent();
            ArrayList<Boolean> arrayList = this.f3490b;
            try {
                try {
                    String m510a = CommonUtils.decompressGzipFromByteString(m885l);
                    ClipboardHandler.m753d().getMethod("setPrimaryClip", ClipData.class).invoke(ClipboardHandler.m752e(), ClipData.newPlainText("text", m510a));
                    arrayList.add(true);
                    this.f3491c.countDown();
                } catch (Exception e) {
                    arrayList.add(false);
                    e.printStackTrace();
                    this.f3491c.countDown();
                }
            } catch (Throwable th) {
                this.f3491c.countDown();
                throw th;
            }
        }
    }

    /* compiled from: ClipboardHandler.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.d.b$b */
    /* loaded from: classes.dex */
    private static class C0724b extends Thread {

        /* renamed from: a */
        private Looper f3480a;

        /* renamed from: b */
        private Runnable f3481b;

        public C0724b(Runnable runnable, String str) {
            super(str);
            this.f3480a = null;
            this.f3481b = null;
            this.f3481b = runnable;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            super.run();
            Looper.prepare();
            this.f3480a = Looper.myLooper();
            if (this.f3481b != null) {
                this.f3481b.run();
            }
            Looper.loop();
        }

        /* renamed from: a */
        public final void m751a() {
            if (this.f3480a != null) {
                this.f3480a.quit();
                this.f3480a = null;
            }
            super.interrupt();
        }
    }

    /* renamed from: d */
    static /* synthetic */ Class m753d() throws ClassNotFoundException {
        return Class.forName("android.content.ClipboardManager");
    }

    /* renamed from: e */
    static /* synthetic */ Object m752e() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        return Class.forName("android.content.ClipboardManager")
                .getConstructor(Context.class, Handler.class)
                .newInstance(FolderApp.getInstance(), new Handler());
    }
}
