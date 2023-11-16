package com.smartisanos.smartfolder.aoa.activity;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.smartisan.updater.UpdateUI;
import com.smartisanos.smartfolder.aoa.R;

/* compiled from: SettingsActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.activity.h */
/* loaded from: classes.dex */
final class C0714h implements UpdateUI {

    /* renamed from: a */
    TextView f3451a;

    /* renamed from: b */
    ProgressBar f3452b;

    /* renamed from: c */
    final /* synthetic */ SettingsActivity f3453c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0714h(SettingsActivity settingsActivity) {
        this.f3453c = settingsActivity;
        this.f3451a = (TextView) this.f3453c.findViewById(R.id.setting_version_txt);
        this.f3452b = (ProgressBar) this.f3453c.findViewById(R.id.update_progress_bar);
    }

    @Override // com.smartisan.updater.UpdateUI
    /* renamed from: a */
    public final void mo769a() {
        this.f3451a.setVisibility(View.GONE);
        this.f3452b.setVisibility(View.VISIBLE);
    }

    @Override // com.smartisan.updater.UpdateUI
    /* renamed from: b */
    public final void mo768b() {
        this.f3451a.setVisibility(View.VISIBLE);
        this.f3452b.setVisibility(View.GONE);
    }
}
