package com.smartisanos.smartfolder.aoa.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p056h.SharedPreferenceHelper;
import com.smartisanos.smartfolder.aoa.view.TitleBar;
import java.util.Locale;

/* loaded from: classes.dex */
public class UserExperienceActivity extends Activity {

    /* renamed from: a */
    private WebView f3435a;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        String str;
        String str2;
        super.onCreate(bundle);
        setContentView(R.layout.activity_user_experience);
        TitleBar titleBar = (TitleBar) findViewById(R.id.titlebar);
        titleBar.m217a(R.string.setting_user_experience_txt);
        titleBar.m214b();
        titleBar.m215a(new View$OnClickListenerC0719m(this));
        this.f3435a = (WebView) findViewById(R.id.experience_plan_info);
        WebSettings settings = this.f3435a.getSettings();
        settings.setSaveFormData(false);
        settings.setBlockNetworkLoads(true);
        settings.setJavaScriptEnabled(false);
        int m552a = SharedPreferenceHelper.getLocalLanguage();
        if (m552a == 0) {
            String country = Locale.getDefault().getCountry();
            if (!TextUtils.isEmpty(country)) {
                if (country.equals("CN")) {
                    str = "smartisan_experience_plan_content.html";
                } else if (country.equals("TW")) {
                    str = "smartisan_experience_plan_content_tw.html";
                } else {
                    str = "smartisan_experience_plan_content_en.html";
                }
            } else {
                str2 = null;
                if (this.f3435a != null || str2 == null) {
                }
                this.f3435a.loadUrl(str2);
                return;
            }
        } else if (m552a == 2) {
            str = "smartisan_experience_plan_content.html";
        } else if (m552a == 5) {
            str = "smartisan_experience_plan_content_tw.html";
        } else {
            str = "smartisan_experience_plan_content_en.html";
        }
        str2 = "file:///android_asset/" + str;
        if (this.f3435a != null) {
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        if (this.f3435a != null) {
            this.f3435a.destroy();
        }
    }
}
