package com.smartisanos.smartfolder.aoa.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import com.smartisanos.smartfolder.aoa.R;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

/* renamed from: com.smartisanos.smartfolder.aoa.view.e */
/* loaded from: classes.dex */
public final class ShareView extends Dialog {

    /* renamed from: a */
    public static final String f3971a = Environment.getExternalStorageDirectory().getPath() + "/smartisan/smartfolder/share/";

    /* renamed from: b */
    public static final String f3972b = Environment.getExternalStorageDirectory().getPath() + "/smartisan/smartfolder/";

    /* renamed from: c */
    public static final String f3973c = f3972b + "share_image.png";

    /* renamed from: d */
    public static final String f3974d = f3971a + "share_image.png";

    /* renamed from: e */
    View.OnClickListener f3975e;

    /* renamed from: f */
    public Activity f3976f;

    /* renamed from: g */
    private TextView f3977g;

    /* renamed from: h */
    private TextView[] f3978h;

    /* renamed from: i */
    private boolean[] f3979i;

    /* renamed from: j */
    private int[] f3980j;

    /* renamed from: k */
    private int[] f3981k;

    /* renamed from: l */
    public ComponentName[] f3982l;

    /* renamed from: m */
    private String[] f3983m;

    public ShareView(Activity activity) {
        super(activity, R.style.ShareDialogTheme);
        this.f3979i = new boolean[]{false, false, false, false, false, false};
        this.f3980j = new int[]{R.drawable.weibo, R.drawable.wx, R.drawable.twitter, R.drawable.qzone, R.drawable.pyq, R.drawable.fb};
        this.f3981k = new int[]{R.drawable.weibo_invail, R.drawable.wx_invail, R.drawable.twitter_invail, R.drawable.qzone_invail, R.drawable.pyq_invail, R.drawable.fb_invail};
        this.f3982l = new ComponentName[]{new ComponentName("com.sina.weibo", "com.sina.weibo.composerinde.ComposerDispatchActivity"), new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI"), new ComponentName("com.twitter.android", "com.twitter.android.composer.ComposerActivity"), new ComponentName("com.qzone", "com.qzonex.module.operation.ui.QZonePublishMoodActivity"), new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI"), new ComponentName("com.facebook.katana", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias")};
        this.f3983m = new String[]{"com.sina.weibo.EditActivity", "com.sina.weibo.ComposerDispatchActivity", "com.sina.weibo.composerinde.ComposerDispatchActivity", "com.facebook.composer.shareintent.ImplicitShareIntentHandlerDefaultAlias", "com.facebook.composer.shareintent.ImplicitShareIntentHandler"};
        this.f3975e = new View$OnClickListenerC0838h(this);
        this.f3976f = activity;
        setContentView(R.layout.view_share);
        Window window = getWindow();
        window.setGravity(80);
        window.setLayout(-1, -2);
        window.addFlags(262144);
        window.addFlags(131072);
        this.f3978h = new TextView[6];
        this.f3978h[0] = (TextView) findViewById(R.id.share_weibo);
        this.f3978h[1] = (TextView) findViewById(R.id.share_weixin);
        this.f3978h[2] = (TextView) findViewById(R.id.share_twitter);
        this.f3978h[3] = (TextView) findViewById(R.id.share_qzone);
        this.f3978h[4] = (TextView) findViewById(R.id.share_weixin_timeline);
        this.f3978h[5] = (TextView) findViewById(R.id.share_facebook);
        m194a();
        this.f3977g = (TextView) findViewById(R.id.share_cancel);
        this.f3977g.setOnClickListener(new View$OnClickListenerC0836f(this));
        setCanceledOnTouchOutside(true);
    }

    /* renamed from: a */
    public final void m194a() {
        new Thread(new RunnableC0837g(this)).start();
    }

    /* renamed from: a */
    private static void m192a(File file) {
        File[] listFiles;
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                file2.delete();
            }
        }
    }

    /* renamed from: a */
    private static boolean m191a(String str) {
        File[] listFiles;
        if (!TextUtils.isEmpty(str)) {
            File file = new File(f3971a);
            if (file.exists() && (listFiles = file.listFiles()) != null) {
                boolean z = false;
                for (File file2 : listFiles) {
                    if (file2.getAbsolutePath().equals(str) && listFiles[0].length() != 0) {
                        z = true;
                    } else if (!file2.getName().equals(".nomedia")) {
                        file2.delete();
                    }
                }
                return z;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v4, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* renamed from: b */
    public final Uri m190b() {
        BufferedOutputStream bufferedOutputStream;
        InputStream inputStream = null;
        Exception e;
        Throwable th;
        File file = new File(f3972b);
        if (file.exists()) {
            m192a(file);
            file.delete();
        }
        File file2 = new File(f3974d);
        boolean m191a = m191a(f3974d);
        try {
            if (!m191a) {
                try {
                    new File(f3971a).mkdirs();
                    file2.createNewFile();
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                } catch (Exception e1) {
                    e = e1;
                    inputStream = null;
                    bufferedOutputStream = null;
                } catch (Throwable th1) {
                    m191a = false;
                    bufferedOutputStream = null;
                    th = th1;
                }
                try {
                    inputStream = this.f3976f.getAssets().open("share_image.png");
                } catch (Exception e2) {
                    e = e2;
                    inputStream = null;
                } catch (Throwable th2) {
                    m191a = false;
                    th = th2;
                    if (m191a) {
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e5) {
                            e5.printStackTrace();
                        }
                    }
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                } catch (Exception e7) {
                    e = e7;
                    e.printStackTrace();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    if (bufferedOutputStream != null) {
                        try {
                            bufferedOutputStream.close();
                            return null;
                        } catch (IOException e9) {
                            e9.printStackTrace();
                            return null;
                        }
                    }
                    return null;
                }
            }
            File file3 = new File(f3971a, ".nomedia");
            if (!file3.exists()) {
                try {
                    file3.createNewFile();
                } catch (IOException e10) {
                    e10.printStackTrace();
                }
            }
            return Uri.fromFile(file2);
        } catch (Throwable th3) {
            th = th3;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m193a(ShareView shareView) {
        Arrays.fill(shareView.f3979i, false);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        List<ResolveInfo> queryIntentActivities = shareView.f3976f.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
            int size = queryIntentActivities.size();
            for (int i = 0; i < size; i++) {
                ActivityInfo activityInfo = queryIntentActivities.get(i).activityInfo;
                for (int i2 = 0; i2 < shareView.f3982l.length; i2++) {
                    if (!shareView.f3979i[i2]) {
                        String packageName = shareView.f3982l[i2].getPackageName();
                        String className = shareView.f3982l[i2].getClassName();
                        if (activityInfo.packageName.equals(packageName)) {
                            shareView.f3979i[i2] = true;
                            if (activityInfo.name.equals(className)) {
                                shareView.f3979i[i2] = true;
                            } else {
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= shareView.f3983m.length) {
                                        break;
                                    } else if (!activityInfo.name.equals(shareView.f3983m[i3])) {
                                        i3++;
                                    } else {
                                        shareView.f3979i[i2] = true;
                                        String str = shareView.f3983m[i3];
                                        if (i2 >= 0 && i2 < shareView.f3982l.length && !TextUtils.isEmpty(str)) {
                                            shareView.f3982l[i2] = new ComponentName(shareView.f3982l[i2].getPackageName(), str);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        int length = shareView.f3982l.length;
        for (int i4 = 0; i4 < length; i4++) {
            if (shareView.f3979i[i4]) {
                shareView.f3978h[i4].setCompoundDrawablesWithIntrinsicBounds(0, shareView.f3980j[i4], 0, 0);
                shareView.f3978h[i4].setOnClickListener(shareView.f3975e);
            } else {
                shareView.f3978h[i4].setCompoundDrawablesWithIntrinsicBounds(0, shareView.f3981k[i4], 0, 0);
                shareView.f3978h[i4].setOnClickListener(null);
            }
        }
    }
}
