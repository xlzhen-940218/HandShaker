package com.smartisan.feedbackhelper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/* compiled from: ReportProblemDescriptionFragment.java */
/* renamed from: com.smartisan.feedbackhelper.h */
/* loaded from: classes.dex */
final class C0432h implements TextWatcher {

    /* renamed from: a */
    final /* synthetic */ ReportProblemDescriptionFragment f2450a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0432h(ReportProblemDescriptionFragment reportProblemDescriptionFragment) {
        this.f2450a = reportProblemDescriptionFragment;
    }

    @Override // android.text.TextWatcher
    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    @Override // android.text.TextWatcher
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        TextView textView;
        TextView textView2;
        Editable text = this.f2450a.f2439c.getText();
        int length = text.length();
        if (text == null || length == 0 || text.toString().trim().equals("")) {
            textView = this.f2450a.f2446j;
            textView.setEnabled(false);
            return;
        }
        textView2 = this.f2450a.f2446j;
        textView2.setEnabled(true);
    }

    @Override // android.text.TextWatcher
    public final void afterTextChanged(Editable editable) {
    }
}
