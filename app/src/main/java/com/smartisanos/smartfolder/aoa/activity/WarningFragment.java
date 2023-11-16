package com.smartisanos.smartfolder.aoa.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.smartisanos.smartfolder.aoa.MainActivity;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.view.TitleBar;

/* renamed from: com.smartisanos.smartfolder.aoa.activity.n */
/* loaded from: classes.dex */
public final class WarningFragment extends Fragment implements View.OnClickListener {

    /* renamed from: a */
    private Button f3462a;

    /* renamed from: b */
    private MainActivity f3463b;

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_warning_layout, viewGroup, false);
        this.f3462a = (Button) inflate.findViewById(R.id.back_button);
        this.f3462a.setOnClickListener(this);
        TitleBar titleBar = (TitleBar) inflate.findViewById(R.id.titlebar);
        titleBar.m215a(this);
        titleBar.m214b();
        if (getActivity() instanceof MainActivity) {
            this.f3463b = (MainActivity) getActivity();
        }
        return inflate;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button /* 2131624045 */:
            case R.id.title_btn_back /* 2131624098 */:
                this.f3463b.m820a();
                return;
            default:
                return;
        }
    }
}
