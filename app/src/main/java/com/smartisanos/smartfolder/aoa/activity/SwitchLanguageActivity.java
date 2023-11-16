package com.smartisanos.smartfolder.aoa.activity;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p056h.ActivityLifecycleManager;
import com.smartisanos.smartfolder.aoa.p056h.SharedPreferenceHelper;
import com.smartisanos.smartfolder.aoa.view.TitleBar;

/* loaded from: classes.dex */
public class SwitchLanguageActivity extends Activity {
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_switch_language);
        Resources resources = getResources();
        TitleBar titleBar = (TitleBar) findViewById(R.id.titlebar);
        titleBar.m217a(R.string.setting_switch_language_txt);
        titleBar.m214b();
        titleBar.m215a(new View$OnClickListenerC0717k(this));
        ListView listView = (ListView) findViewById(R.id.language_list);
        listView.setOnItemClickListener(new C0718l(this, resources));
        listView.setAdapter((ListAdapter) new C0706a(this, resources.getStringArray(R.array.language_current), (byte) 0));
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    /* renamed from: com.smartisanos.smartfolder.aoa.activity.SwitchLanguageActivity$a */
    /* loaded from: classes.dex */
    private class C0706a extends BaseAdapter {

        /* renamed from: b */
        private String[] f3430b;

        /* synthetic */ C0706a(SwitchLanguageActivity switchLanguageActivity, String[] strArr, byte b) {
            this(strArr);
        }

        private C0706a(String[] strArr) {
            this.f3430b = strArr;
        }

        @Override // android.widget.Adapter
        public final int getCount() {
            if (this.f3430b != null) {
                return this.f3430b.length;
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public final Object getItem(int i) {
            return this.f3430b[i];
        }

        @Override // android.widget.Adapter
        public final long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public final View getView(int i, View view, ViewGroup viewGroup) {
            C0707a c0707a;
            if (view == null) {
                C0707a c0707a2 = new C0707a();
                view = LayoutInflater.from(SwitchLanguageActivity.this).inflate(R.layout.layout_language_item, viewGroup, false);
                c0707a2.f3431a = (RelativeLayout) view.findViewById(R.id.item_layout);
                c0707a2.f3432b = (TextView) view.findViewById(R.id.item_language_text);
                c0707a2.f3433c = (ImageView) view.findViewById(R.id.item_language_icon);
                view.setTag(c0707a2);
                c0707a = c0707a2;
            } else {
                c0707a = (C0707a) view.getTag();
            }
            int i2 = R.drawable.settings_item_middle;
            if (this.f3430b.length > 1) {
                if (i == 0) {
                    i2 = R.drawable.selector_setting_sub_item_bg_top;
                } else if (i == this.f3430b.length - 1) {
                    i2 = R.drawable.selector_setting_sub_item_bg_bottom;
                }
            } else {
                i2 = R.drawable.setting_item_single_background;
            }
            c0707a.f3431a.setBackgroundResource(i2);
            if (i == 0) {
                c0707a.f3432b.setText(R.string.language_system_txt);
            } else {
                c0707a.f3432b.setText(this.f3430b[i]);
            }
            if (SharedPreferenceHelper.getLocalLanguage() == i) {
                c0707a.f3433c.setVisibility(View.VISIBLE);
            } else {
                c0707a.f3433c.setVisibility(View.GONE);
            }
            return view;
        }

        /* renamed from: com.smartisanos.smartfolder.aoa.activity.SwitchLanguageActivity$a$a */
        /* loaded from: classes.dex */
        class C0707a {

            /* renamed from: a */
            RelativeLayout f3431a;

            /* renamed from: b */
            TextView f3432b;

            /* renamed from: c */
            ImageView f3433c;

            C0707a() {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m786a(SwitchLanguageActivity switchLanguageActivity, int i) {
        SharedPreferenceHelper.setLocale(switchLanguageActivity, i);
        ActivityLifecycleManager.getInstance();
        ActivityLifecycleManager.m555a(switchLanguageActivity);
    }
}
