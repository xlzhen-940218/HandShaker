package com.smartisan.feedbackhelper;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.smartisan.feedbackhelper.utils.AsyncDialog;
import com.smartisan.feedbackhelper.utils.BugReportException;
import com.smartisan.feedbackhelper.utils.C0462r;
import com.smartisan.feedbackhelper.utils.ScreenShotsAdapter;
import com.smartisan.feedbackhelper.utils.Title;
import com.smartisanos.smartfolder.aoa.R;

import java.io.File;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/* loaded from: classes.dex */
public class FeedbackActivity extends Activity {

    /* renamed from: l */
    public static int f2398l = 0;

    /* renamed from: b */
    public String f2400b;

    /* renamed from: c */
    public ScreenShotsAdapter f2401c;

    /* renamed from: e */
    public String f2403e;

    /* renamed from: f */
    Title f2404f;

    /* renamed from: g */
    protected String f2405g;

    /* renamed from: h */
    String f2406h;

    /* renamed from: k */
    ArrayList<String> f2409k;

    /* renamed from: m */
    private FragmentManager f2410m;

    /* renamed from: n */
    private FragmentTransaction f2411n;

    /* renamed from: o */
    public ReportProblemDescriptionFragment f2412o;

    /* renamed from: p */
    public StringBuilder f2413p;

    /* renamed from: q */
    public ArrayList<String> f2414q;

    /* renamed from: r */
    private ScrollView f2415r;

    /* renamed from: s */
    public AsyncDialog f2416s;

    /* renamed from: a */
    String f2399a = "Bug2goMainActivity";

    /* renamed from: d */
    public int f2402d = 0;

    /* renamed from: i */
    String f2407i = "unknown";

    /* renamed from: j */
    String f2408j = "unknown";

    /* renamed from: t */
    private BroadcastReceiver f2417t = new C0418a(this);

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        int i;
        int i2;
        requestWindowFeature(1);
        if ("com.smartisanos.home".equals(getApplication().getPackageName())) {
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= 19) {
                try {
                    i = WindowManager.LayoutParams.class.getField("FLAG_TRANSLUCENT_STATUS").getInt(WindowManager.LayoutParams.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    i = 0;
                }
                if (i != 0) {
                    window.addFlags(i);
                }
                if ("Meizu".equals(Build.MANUFACTURER)) {
                    try {
                        i2 = WindowManager.LayoutParams.class.getField("FLAG_TRANSLUCENT_NAVIGATION").getInt(WindowManager.LayoutParams.class);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        i2 = 0;
                    }
                    if (i2 != 0) {
                        window.addFlags(i2);
                    }
                }
            } else {
                String[] systemSharedLibraryNames = getPackageManager().getSystemSharedLibraryNames();
                String str = "SYSTEM_UI_FLAG_TRANSPARENT_BACKGROUND";
                if (systemSharedLibraryNames != null) {
                    for (String str2 : systemSharedLibraryNames) {
                        if (str2.equals("touchwiz") || str2.startsWith("com.htc.")) {
                            str = "SYSTEM_UI_FLAG_TRANSPARENT_BACKGROUND";
                            break;
                        } else if (str2.startsWith("com.sonyericsson.navigationbar")) {
                            str = "SYSTEM_UI_FLAG_TRANSPARENT";
                            break;
                        }
                    }
                    try {
                        Field field = View.class.getField(str);
                        if (field.getType() == Integer.TYPE) {
                            window.getDecorView().setSystemUiVisibility(field.getInt(null));
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
            m2139a((Activity) this);
        }
        overridePendingTransition(R.anim.feedback_slide_in_from_right, R.anim.feedback_slide_out_to_left);
        setContentView(R.layout.feedback_activity);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        this.f2404f = (Title) findViewById(R.id.view_title);
        Intent intent = getIntent();
        if (intent.getStringExtra("theme_style") == null || !intent.getStringExtra("theme_style").equals("light")) {
            if (intent.getStringExtra("theme_style") == null || !intent.getStringExtra("theme_style").equals("custom")) {
                this.f2404f.m2066b().setBackgroundResource(R.drawable.feedback_title_button_ok);
            } else {
                int intExtra = intent.getIntExtra("title_bg_res", -1);
                if (intExtra != -1) {
                    this.f2404f.setBackgroundResource(intExtra);
                }
                int intExtra2 = intent.getIntExtra("title_ok_btn_bg", -1);
                if (intExtra2 != -1) {
                    this.f2404f.m2066b().setBackgroundResource(intExtra2);
                }
                int intExtra3 = intent.getIntExtra("title_back_btn_bg", -1);
                if (intExtra3 != -1) {
                    this.f2404f.m2065c().setBackgroundResource(intExtra3);
                }
                int intExtra4 = intent.getIntExtra("title_text_color", -1);
                if (intExtra4 != -1) {
                    TextView m2066b = this.f2404f.m2066b();
                    TextView m2065c = this.f2404f.m2065c();
                    TextView m2069a = this.f2404f.m2069a();
                    if (m2066b instanceof TextView) {
                        m2066b.setTextColor(intExtra4);
                    }
                    if (m2065c instanceof TextView) {
                        m2065c.setTextColor(intExtra4);
                    }
                    if (m2069a instanceof TextView) {
                        m2069a.setTextColor(intExtra4);
                    }
                }
                ((ImageView) findViewById(R.id.feedback_title_bar_shadow)).setBackgroundResource(R.drawable.title_light_shadow);
            }
        } else {
            this.f2404f.setBackgroundResource(R.drawable.titlebar_light);
            this.f2404f.m2065c().setBackgroundResource(R.drawable.feedback_title_button_back_light);
            this.f2404f.m2066b().setBackgroundResource(R.drawable.feedback_title_light_button_ok);
            TextView m2066b2 = this.f2404f.m2066b();
            TextView m2065c2 = this.f2404f.m2065c();
            TextView m2069a2 = this.f2404f.m2069a();
            if (m2066b2 instanceof TextView) {
                m2066b2.setTextAppearance(this, R.style.Light_TitleButtonStyle);
            }
            if (m2065c2 instanceof TextView) {
                m2065c2.setTextAppearance(this, R.style.Light_TitleButtonStyle);
            }
            if (m2069a2 instanceof TextView) {
                m2069a2.setTextAppearance(this, R.style.Light_TitleBarStyle);
            }
            ((ImageView) findViewById(R.id.feedback_title_bar_shadow)).setBackgroundResource(R.drawable.title_light_shadow);
        }
        if (intent.getStringExtra("back_text") != null) {
            this.f2404f.m2067a(intent.getStringExtra("back_text"));
        } else {
            Title title = this.f2404f;
            title.m2067a(title.getResources().getString(R.string.title_button_text_back));
        }
        if (intent.getStringExtra("app_name") != null) {
            this.f2407i = intent.getStringExtra("app_name");
        }
        if (intent.getStringExtra("package_name") != null) {
            this.f2408j = intent.getStringExtra("package_name");
        }
        if (bundle != null) {
            this.f2400b = bundle.getString("Description", "");
            this.f2403e = bundle.getString("Email", "");
            this.f2409k = bundle.getStringArrayList("ImageList");
        }
        this.f2414q = new ArrayList<>();
        this.f2410m = getFragmentManager();
        this.f2411n = this.f2410m.beginTransaction();
        this.f2411n.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        this.f2412o = new ReportProblemDescriptionFragment();
        this.f2411n.replace(R.id.launcher_container, this.f2412o);
        this.f2411n.commit();
        this.f2416s = new AsyncDialog(this);
        this.f2415r = (ScrollView) findViewById(R.id.view_main);
        this.f2415r.setOnTouchListener(new View$OnTouchListenerC0417a(this, (byte) 0));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("smartisan.intent.action.BUGREPORT.REPORT_UPLOAD_SUCCESS");
        intentFilter.addAction("smartisan.intent.action.BUGREPORT.REPORT_UPLOAD_FAILED");
        registerReceiver(this.f2417t, intentFilter);
        super.onCreate(bundle);
    }

    /* renamed from: com.smartisan.feedbackhelper.FeedbackActivity$a */
    /* loaded from: classes.dex */
    private class View$OnTouchListenerC0417a implements View.OnTouchListener {
        private View$OnTouchListenerC0417a() {
        }

        /* synthetic */ View$OnTouchListenerC0417a(FeedbackActivity feedbackActivity, byte b) {
            this();
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                case 2:
                    View currentFocus = FeedbackActivity.this.getCurrentFocus();
                    if (currentFocus != null) {
                        FeedbackActivity.this.m2137a(currentFocus);
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    /* renamed from: a */
    protected final boolean m2137a(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && view.getWindowToken() != null) {
            return inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        return false;
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.f2412o.m2123c()) {
            this.f2412o.m2121d();
        } else {
            finish();
        }
    }

    @Override // android.app.Activity
    public void finish() {
        m2137a(this.f2415r);
        this.f2412o.f2443g.postDelayed(null, 200L);
        super.finish();
        overridePendingTransition(R.anim.feedback_slide_in_from_left, R.anim.feedback_slide_out_to_right);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.f2417t);
        this.f2412o = null;
    }

    /* renamed from: a */
    public final void m2140a() {
        this.f2416s.m2060a(new RunnableC0419b(this), R.string.feedback_uploading_report);
    }

    /* renamed from: b */
    public final void m2133b() {
        this.f2400b = this.f2412o.f2439c.getText().toString();
        this.f2406h = this.f2412o.f2442f.getText().toString();
        this.f2413p = new StringBuilder();
        this.f2414q.clear();
        this.f2401c = this.f2412o.m2129a();
        if (!this.f2401c.m2029b().isEmpty()) {
            int size = this.f2401c.m2029b().size();
            for (int i = 0; i < size; i++) {
                String m2034a = this.f2401c.m2034a(i);
                this.f2413p.append(m2034a.substring(m2034a.lastIndexOf("/") + 1) + ",");
                this.f2414q.add(i, m2034a);
            }
        }
        this.f2404f.m2066b().setEnabled(true);
        new C0420c(this).start();
    }

    /* renamed from: a */
    public static File m2134a(String str, long j) throws BugReportException {
        String m2022a = C0462r.m2022a();
        if (m2022a == null) {
            throw new BugReportException("No space left on device or storage path not writable");
        }
        String str2 = m2022a + File.separator + str + "@" + new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSSZ").format(new Date(j));
        File file = new File(str2);
        if (!file.exists() && !file.mkdirs()) {
            throw new BugReportException("Failed to create directory for report (tag=" + str + ", ts=" + j + ")");
        }
        return new File(str2);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putString("Description", this.f2412o.f2439c.getText().toString());
        bundle.putString("Email", this.f2412o.f2442f.getText().toString());
        bundle.putStringArrayList("ImageList", new ArrayList<>(this.f2412o.m2129a().m2029b()));
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        String[] split = new String[0];
        String str = null;
        str = null;
        Uri uri = null;
        str = null;
        if (this.f2401c == null) {
            this.f2401c = this.f2412o.m2129a();
        }
        switch (i) {
            case 1000:
                if (i2 == -1) {
                    Uri data = intent.getData();
                    if ((Build.VERSION.SDK_INT >= 19 ? (byte) 1 : (byte) 0) != 0 && DocumentsContract.isDocumentUri(this, data)) {
                        if ("com.android.externalstorage.documents".equals(data.getAuthority())) {
                            if ("primary".equalsIgnoreCase(DocumentsContract.getDocumentId(data).split(":")[0])) {
                                str = Environment.getExternalStorageDirectory() + "/" + split[1];
                            }
                        } else if ("com.android.providers.downloads.documents".equals(data.getAuthority())) {
                            str = m2138a(this, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(data)).longValue()), null, null);
                        } else if ("com.android.providers.media.documents".equals(data.getAuthority())) {
                            String[] split2 = DocumentsContract.getDocumentId(data).split(":");
                            String str2 = split2[0];
                            if ("image".equals(str2)) {
                                uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                            } else if ("video".equals(str2)) {
                                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                            } else if ("audio".equals(str2)) {
                                uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                            }
                            str = m2138a(this, uri, "_id=?", new String[]{split2[1]});
                        }
                    } else if ("content".equalsIgnoreCase(data.getScheme())) {
                        str = m2138a(this, data, null, null);
                    } else if ("file".equalsIgnoreCase(data.getScheme())) {
                        str = data.getPath();
                    }
                    if (!ScreenShotsAdapter.m2025c(str)) {
                        Toast.makeText(this, R.string.feedback_add_err_pic, Toast.LENGTH_SHORT).show();
                        return;
                    } else if (!ScreenShotsAdapter.m2024d(str)) {
                        Toast.makeText(this, R.string.feedback_add_pic_err_type, Toast.LENGTH_SHORT).show();
                        return;
                    } else if (str != null && str.length() > 0) {
                        if (!this.f2401c.m2027b(str)) {
                            if (this.f2401c.m2034a(f2398l) != null) {
                                this.f2401c.m2033a(f2398l, str);
                            } else {
                                this.f2401c.m2031a(str);
                            }
                            this.f2401c.notifyDataSetChanged();
                            return;
                        }
                        Toast.makeText(this, getString(R.string.problem_description_select_same_file), Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    private static String m2138a(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor = null;
        try {
            Cursor query = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query != null) {
                            query.close();
                            return string;
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th2) {

        }
        return null;
    }

    /* renamed from: a */
    private static String m2135a(String str) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, null);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m2139a(Activity activity) {
        String m2135a = m2135a("ro.product.manufacturer");
        if (m2135a == null) {
            m2135a = m2135a("ro.product.model");
        }
        if (TextUtils.isEmpty(m2135a)) {
            m2135a = "";
        }
        String lowerCase = m2135a.toLowerCase();
        if (lowerCase.startsWith("xiaomi") || lowerCase.startsWith("mi")) {
            Class<?> cls = activity.getWindow().getClass();
            try {
                Class<?> cls2 = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                int i = cls2.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE").getInt(cls2);
                cls.getMethod("setExtraFlags", Integer.TYPE, Integer.TYPE).invoke(activity.getWindow(), Integer.valueOf(i), Integer.valueOf(i));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }
}
