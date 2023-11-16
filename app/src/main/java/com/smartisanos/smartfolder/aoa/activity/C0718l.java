package com.smartisanos.smartfolder.aoa.activity;

import android.content.res.Resources;
import android.view.View;
import android.widget.AdapterView;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p056h.SharedPreferenceHelper;

/* compiled from: SwitchLanguageActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.activity.l */
/* loaded from: classes.dex */
final class C0718l implements AdapterView.OnItemClickListener {

    /* renamed from: a */
    final /* synthetic */ Resources f3459a;

    /* renamed from: b */
    final /* synthetic */ SwitchLanguageActivity f3460b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0718l(SwitchLanguageActivity switchLanguageActivity, Resources resources) {
        this.f3460b = switchLanguageActivity;
        this.f3459a = resources;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int i2 = this.f3459a.getIntArray(R.array.language_id)[i];
        SharedPreferenceHelper.setLocalLanguage(i2);
        SwitchLanguageActivity.m786a(this.f3460b, i2);
        this.f3460b.finish();
    }
}
