package com.smartisanos.smartfolder.aoa.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.smartisan.feedbackhelper.FeedbackActivity;
import com.smartisan.moreapps.AppsView;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.VersionUpdater;
import com.smartisanos.smartfolder.aoa.p056h.SharedPreferenceHelper;
import com.smartisanos.smartfolder.aoa.view.MenuDialogWrapper;
import com.smartisanos.smartfolder.aoa.view.ShareView;
import com.smartisanos.smartfolder.aoa.view.TitleBar;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class SettingsActivity extends Activity implements View.OnClickListener {

    /* renamed from: a */
    private static final String f3424a = SettingsActivity.class.getSimpleName();

    /* renamed from: b */
    private ShareView f3425b;

    /* renamed from: c */
    private TextView f3426c;

    /* renamed from: d */
    private AppsView f3427d;

    /* renamed from: e */
    private int f3428e = 0;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_settings);
        this.f3428e = SharedPreferenceHelper.getLocalLanguage();
        SharedPreferenceHelper.setLocale(this, this.f3428e);
        getWindow().setSoftInputMode(48);
        TitleBar titleBar = (TitleBar) findViewById(R.id.titlebar);
        titleBar.m214b();
        titleBar.m217a(R.string.settings_title);
        titleBar.m215a((View.OnClickListener) this);
        ((ScrollView) findViewById(R.id.scroll_view)).smoothScrollTo(0, 0);
        this.f3426c = (TextView) findViewById(R.id.current_language);
        this.f3427d = (AppsView) findViewById(R.id.setting_more_product);
        findViewById(R.id.app_list).setFocusable(false);
        TextView textView = (TextView) findViewById(R.id.setting_version_txt);
        try {
            textView.setText("v" + getPackageManager().getPackageInfo(getPackageName(), 128).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        findViewById(R.id.setting_share).setOnClickListener(this);
        findViewById(R.id.setting_help).setOnClickListener(this);
        findViewById(R.id.setting_check_update).setOnClickListener(this);
        findViewById(R.id.setting_switch_language).setOnClickListener(this);
        findViewById(R.id.setting_feedback).setOnClickListener(this);
        findViewById(R.id.setting_user_experience).setOnClickListener(this);
        findViewById(R.id.setting_follow_weixin).setOnClickListener(this);
        findViewById(R.id.setting_follow_weibo).setOnClickListener(this);
        findViewById(R.id.setting_follow_website).setOnClickListener(this);
        findViewById(R.id.setting_language_bar).setOnClickListener(this);
        this.f3425b = new ShareView(this);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        int m552a = SharedPreferenceHelper.getLocalLanguage();
        Resources resources = getResources();
        String string = resources.getString(R.string.language_system_txt);
        String str = resources.getStringArray(R.array.language_current)[m552a];
        this.f3426c.setText(m552a == 0 ? string : str);
        if (this.f3425b != null && !this.f3425b.isShowing()) {
            this.f3425b.m194a();
        }
    }

    @SuppressLint("WrongConstant")
    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.setting_share /* 2131623983 */:
                this.f3425b.show();
                return;
            case R.id.setting_help /* 2131623984 */:
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://bbs.smartisan.com/thread-516359-1-1.html"));
                intent.setFlags(805306368);
                startActivity(intent);
                return;
            case R.id.setting_check_update /* 2131623985 */:
                view.setClickable(false);
                view.postDelayed(new RunnableC0713g(this, view), 500L);
                VersionUpdater.m557a(this, true, new C0714h(this));
                return;
            case R.id.setting_switch_language /* 2131623988 */:
            case R.id.setting_language_bar /* 2131623994 */:
                Intent intent2 = new Intent();
                intent2.setClass(this, SwitchLanguageActivity.class);
                startActivity(intent2);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                return;
            case R.id.setting_feedback /* 2131623991 */:
                Intent intent3 = new Intent();
                intent3.setClass(getApplicationContext(), FeedbackActivity.class);
                intent3.putExtra("theme_style", "light");
                intent3.putExtra("app_name", getString(R.string.app_name));
                intent3.putExtra("package_name", getPackageName());
                intent3.putExtra("back_text", getResources().getString(R.string.title_btn_back));
                intent3.putExtra("smartisanos.intent.extra.ANIM_RESOURCE_ID", new int[]{R.anim.left_in, R.anim.right_out});
                startActivity(intent3);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                return;
            case R.id.setting_user_experience /* 2131623992 */:
                Intent intent4 = new Intent();
                intent4.setClass(this, UserExperienceActivity.class);
                startActivity(intent4);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                return;
            case R.id.setting_follow_weixin /* 2131624071 */:
                m788a(R.string.follow_dialog_weixin_title_text, R.string.follow_weixin_content);
                return;
            case R.id.setting_follow_weibo /* 2131624074 */:
                m788a(R.string.follow_dialog_weibo_title_text, R.string.follow_weibo_content);
                return;
            case R.id.setting_follow_website /* 2131624077 */:
                Intent intent5 = new Intent("android.intent.action.VIEW");
                intent5.setData(Uri.parse("http://www.smartisan.com"));
                startActivity(intent5);
                return;
            case R.id.title_btn_back /* 2131624098 */:
                onBackPressed();
                return;
            default:
                if (this.f3425b.isShowing()) {
                    this.f3425b.dismiss();
                    return;
                }
                return;
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    /* renamed from: a */
    private void m788a(int i, int i2) {
        MenuDialogWrapper menuDialogWrapper = new MenuDialogWrapper(this);
        String string = getResources().getString(i2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new MenuDialogWrapper.C0835a(new View$OnClickListenerC0716j(this, string)));
        menuDialogWrapper.m196a(arrayList);
        menuDialogWrapper.m198a(i);
        menuDialogWrapper.m197a(new View$OnClickListenerC0715i(this, menuDialogWrapper));
        menuDialogWrapper.m199a();
    }
}
