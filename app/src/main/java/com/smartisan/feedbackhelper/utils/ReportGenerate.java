package com.smartisan.feedbackhelper.utils;

import android.app.IntentService;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.smartisan.feedbackhelper.upload.ReliableUploader;
import com.smartisan.feedbackhelper.utils.ComplainReport;
import com.smartisanos.smartfolder.aoa.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: classes.dex */
public class ReportGenerate extends IntentService {

    /* renamed from: a */
    private SimpleDateFormat f2548a;

    public ReportGenerate() {
        super("BugReportGenerate");
        this.f2548a = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss.SSSZ");
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        String action = intent.getAction();
        if (TextUtils.isEmpty(action)) {
            Log.i("BugReportGenerate", "Invalid action!");
        } else if (action.equals("smartisan.intent.action.REPORT.DATA")) {
            Log.i("BugReportGenerate", "Report Data intent received!");
            ComplainReport complainReport = new ComplainReport();
            complainReport.m2099a(new Date(intent.getLongExtra("createtime", 0L)));
            complainReport.m2102a(ComplainReport.EnumC0451a.WAIT_USER_INPUT);
            if (intent.getStringExtra("summary") != null) {
                complainReport.m2095c(intent.getStringExtra("summary"));
                if (intent.getStringExtra("tag") != null) {
                    complainReport.m2093d(intent.getStringExtra("tag"));
                    if (intent.getStringExtra("from").equals(getString(R.string.user_feedback)) || intent.getStringExtra("from").equals("sdk")) {
                        complainReport.m2101a(ComplainReport.EnumC0452b.USER);
                    } else {
                        complainReport.m2101a(ComplainReport.EnumC0452b.AUTO);
                    }
                    if (intent.getStringExtra("description") != null) {
                        if (TextUtils.isEmpty(intent.getStringExtra("description"))) {
                            complainReport.m2091e(Long.toString(System.currentTimeMillis()));
                        } else {
                            complainReport.m2091e(intent.getStringExtra("description"));
                        }
                        if (TextUtils.isEmpty(intent.getStringExtra("attach_files"))) {
                            complainReport.m2097b(m2071a().getAbsolutePath());
                        } else {
                            String[] split = intent.getStringExtra("attach_files").split(",");
                            if (split.length == 1 && new File(split[0]).isDirectory()) {
                                complainReport.m2097b(split[0]);
                            } else {
                                File m2071a = m2071a();
                                if (m2071a.exists()) {
                                    try {
                                        m2070a(split, m2071a);
                                    } catch (Throwable e) {
                                        throw new RuntimeException(e);
                                    }
                                    C0462r.m2015a(split);
                                }
                                complainReport.m2097b(m2071a.getAbsolutePath());
                            }
                        }
                        if (intent.getStringExtra("from") != null) {
                            complainReport.m2079k(intent.getStringExtra("from"));
                            if (intent.getStringExtra("snapshots") != null && !intent.getStringExtra("snapshots").equals("")) {
                                complainReport.m2087g(intent.getStringExtra("snapshots"));
                            } else {
                                complainReport.m2087g("");
                            }
                            if (intent.getStringExtra("process") == null) {
                                complainReport.m2077l("unknown");
                            } else {
                                complainReport.m2077l(intent.getStringExtra("process"));
                            }
                            if (intent.getStringExtra("packageName") == null) {
                                complainReport.m2075m("unknown");
                            } else {
                                complainReport.m2075m(intent.getStringExtra("packageName"));
                            }
                            String m2017a = C0462r.m2017a("ro.modversion", "");
                            if (m2017a.equals("")) {
                                complainReport.m2085h(C0462r.m2017a("ro.build.description", ""));
                            } else {
                                complainReport.m2085h(m2017a);
                            }
                            complainReport.m2083i(C0462r.m2017a("gsm.version.baseband", ""));
                            complainReport.m2081j(C0462r.m2021a(this));
                            if (intent.getStringExtra("email_add") == null || intent.getStringExtra("email_add").trim().equals("")) {
                                complainReport.m2100a("unknown");
                            } else {
                                complainReport.m2100a(intent.getStringExtra("email_add"));
                            }
                            Log.d("BugReportGenerate", "uploadReport " + complainReport);
                            Intent intent2 = new Intent(this, ReliableUploader.class);
                            intent2.putExtra("COMPLAINT_REPORT", complainReport);
                            startService(intent2);
                        }
                    }
                }
            }
        } else {
            Log.i("BugReportGenerate", ">>>>>Please change the intent to specific intent!");
        }
    }

    /* renamed from: a */
    private File m2071a() {
        String m2022a = C0462r.m2022a();
        File file = new File(m2022a + File.separator + "others@" + this.f2548a.format(new Date(System.currentTimeMillis())));
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }

    /* renamed from: a */
    private static boolean m2070a(String[] strArr, File file) throws Throwable {
        String absolutePath = file.getAbsolutePath();
        if (file.isFile()) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < strArr.length; i++) {
            if (new File(strArr[i]).exists()) {
                if (new File(strArr[i]).isDirectory()) {
                    File file2 = new File(strArr[i]);
                    String str = strArr[i];
                    if (!str.endsWith(File.separator)) {
                        str = str + File.separator;
                    }
                    String[] list = file2.list();
                    for (int i2 = 0; list != null && i2 < list.length; i2++) {
                        String str2 = str + File.separator + list[i2];
                        if (new File(str2).isFile()) {
                            z = C0462r.m2013b(str2, absolutePath + File.separator + list[i2]);
                        }
                    }
                } else {
                    z = C0462r.m2013b(strArr[i], absolutePath + File.separator + new File(strArr[i]).getName());
                }
            }
        }
        return z;
    }
}
