package com.smartisan.feedbackhelper;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.smartisan.feedbackhelper.utils.CustomDialog;
import com.smartisan.feedbackhelper.utils.ScreenShotsAdapter;
import com.smartisanos.smartfolder.aoa.R;

import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.smartisan.feedbackhelper.e */
/* loaded from: classes.dex */
public final class ReportProblemDescriptionFragment extends Fragment implements View.OnClickListener {

    /* renamed from: a */
    public String f2437a;

    /* renamed from: b */
    public String f2438b;

    /* renamed from: c */
    public EditText f2439c;

    /* renamed from: d */
    public TextView f2440d;

    /* renamed from: e */
    public boolean f2441e = true;

    /* renamed from: f */
    EditText f2442f;

    /* renamed from: g */
    Handler f2443g;

    /* renamed from: h */
    ArrayList<String> f2444h;

    /* renamed from: i */
    public Context context;

    /* renamed from: j */
    public TextView f2446j;

    /* renamed from: k */
    private ScreenShotsAdapter f2447k;

    /* renamed from: a */
    public final ScreenShotsAdapter m2129a() {
        return this.f2447k;
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.context = getActivity();
        if (this.context instanceof FeedbackActivity) {
            this.f2437a = ((FeedbackActivity) this.context).f2400b;
            this.f2438b = ((FeedbackActivity) this.context).f2403e;
            this.f2444h = ((FeedbackActivity) this.context).f2409k;
            this.f2447k = ((FeedbackActivity) this.context).f2401c;
            this.f2440d = ((FeedbackActivity) this.context).f2404f.m2065c();
        }
        this.f2443g = new Handler();
        if (this.f2447k == null) {
            this.f2447k = new ScreenShotsAdapter(this.context);
        }
        this.f2447k.m2030a(this.f2444h);
    }

    /* renamed from: a */
    private static String m2128a(Context context, Uri uri) {
        Cursor query;
        int columnIndex;
        String str = null;
        if (uri == null) {
            return null;
        }
        String scheme = uri.getScheme();
        if (scheme == null) {
            return uri.getPath();
        }
        if ("file".equals(scheme)) {
            return uri.getPath();
        }
        if (!"content".equals(scheme) || (query = context.getContentResolver().query(uri, new String[]{"_data"}, null, null, null)) == null) {
            return null;
        }
        if (query.moveToFirst() && (columnIndex = query.getColumnIndex("_data")) >= 0) {
            str = query.getString(columnIndex);
        }
        query.close();
        return str;
    }

    /* renamed from: b */
    public final void m2125b() {
        this.f2439c.setText("");
        this.f2442f.setText("");
        this.f2447k.m2035a();
    }

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Uri uri;
        View inflate = layoutInflater.inflate(R.layout.fragment_problem_description, viewGroup, false);
        this.f2446j = (TextView) inflate.findViewById(R.id.feedback_submit);
        this.f2446j.setEnabled(false);
        this.f2439c = (EditText) inflate.findViewById(R.id.et_problem_description);
        if (this.f2437a != null && !this.f2437a.trim().equals("")) {
            this.f2439c.setText(this.f2437a);
            this.f2446j.setEnabled(true);
        }
        this.f2442f = (EditText) inflate.findViewById(R.id.feedback_user_info_email);
        if (this.f2438b != null && !this.f2438b.trim().equals("")) {
            this.f2442f.setText(this.f2438b);
        }
        Intent intent = getActivity().getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if ("android.intent.action.SEND".equals(action) && type != null) {
            if (!type.startsWith("text/")) {
                if (type.startsWith("image/") && (uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM")) != null) {
                    this.f2447k.m2031a(m2128a(getActivity(), uri));
                }
            } else {
                String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
                if (stringExtra != null) {
                    int length = stringExtra.length();
                    if (length >= 1000) {
                        getActivity().finish();
                    } else {
                        this.f2439c.setText(stringExtra);
                        this.f2439c.setSelection(length);
                    }
                }
            }
        } else if ("android.intent.action.SEND_MULTIPLE".equals(action) && type != null && type.startsWith("image/")) {
            ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
            if (parcelableArrayListExtra.size() > 5) {
                Toast.makeText(this.context, R.string.feedback_add_pic_limit, Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
            Iterator it = parcelableArrayListExtra.iterator();
            while (it.hasNext()) {
                this.f2447k.m2031a(m2128a(getActivity(), (Uri) it.next()));
            }
        }
        this.f2442f.setOnClickListener(new View$OnClickListenerC0430f(this));
        this.f2439c.setCursorVisible(true);
        this.f2439c.setOnClickListener(new View$OnClickListenerC0431g(this));
        this.f2439c.addTextChangedListener(new C0432h(this));
        this.f2440d.setOnClickListener(new View$OnClickListenerC0433i(this));
        this.f2446j.setOnClickListener(new View$OnClickListenerC0434j(this));
        GridView gridView = (GridView) inflate.findViewById(R.id.gd_problem_screen_shots);
        gridView.setAdapter((ListAdapter) this.f2447k);
        gridView.setOnTouchListener(new View$OnTouchListenerC0435k(this));
        return inflate;
    }

    /* renamed from: a */
    public static void m2127a(Context context, String str) {
        if (context instanceof Activity) {
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType(str);
            ((FeedbackActivity) context).startActivityForResult(Intent.createChooser(intent, null), 1000);
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
    }

    /* renamed from: c */
    public final boolean m2123c() {
        String obj = this.f2439c.getText().toString();
        if ((obj.length() == 0 || obj.trim().equals("")) && this.f2447k.m2026c()) {
            return false;
        }
        return true;
    }

    /* renamed from: d */
    public final void m2121d() {
        CustomDialog.C0456a c0456a = new CustomDialog.C0456a(this.context);
        c0456a.m2054a(R.string.problem_description_dialog_info).m2051b(R.string.problem_description_dialog_cancel, new DialogInterface$OnClickListenerC0437m(this)).m2053a(R.string.problem_description_dialog_ok, new DialogInterface$OnClickListenerC0436l(this));
        c0456a.m2055a().show();
    }

    @Override // android.app.Fragment
    public final void onPause() {
        super.onPause();
    }

    @Override // android.app.Fragment
    public final void onDestroy() {
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public static /* synthetic */ boolean m2122c(ReportProblemDescriptionFragment reportProblemDescriptionFragment) {
        if (reportProblemDescriptionFragment.f2439c.getText().toString().equals("") || reportProblemDescriptionFragment.f2439c.getText().toString() == null) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: d */
    public static /* synthetic */ void m2120d(ReportProblemDescriptionFragment reportProblemDescriptionFragment) {
        if (!(reportProblemDescriptionFragment.context instanceof FeedbackActivity)) {
            return;
        }
        ((FeedbackActivity) reportProblemDescriptionFragment.context).finish();
    }
}
