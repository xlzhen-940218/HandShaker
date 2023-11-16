package com.smartisanos.smartfolder.aoa.activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.view.View;
import android.widget.Toast;
import com.smartisanos.smartfolder.aoa.R;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SettingsActivity.java */
/* renamed from: com.smartisanos.smartfolder.aoa.activity.j */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0716j implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ String f3456a;

    /* renamed from: b */
    final /* synthetic */ SettingsActivity f3457b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0716j(SettingsActivity settingsActivity, String str) {
        this.f3457b = settingsActivity;
        this.f3456a = str;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        ((ClipboardManager) this.f3457b.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Notes", this.f3456a));
        Toast.makeText(this.f3457b, this.f3457b.getResources().getString(R.string.follow_dialog_coyp_toast_text), Toast.LENGTH_SHORT).show();
    }
}
