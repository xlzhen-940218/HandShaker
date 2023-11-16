package com.smartisanos.smartfolder.aoa.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.view.ConnectInfoView;
import com.smartisanos.smartfolder.aoa.view.CustomDialogBuilder;
import com.smartisanos.smartfolder.aoa.view.TitleBar;

/* renamed from: com.smartisanos.smartfolder.aoa.activity.a */
/* loaded from: classes.dex */
public final class ConnecttedFragment extends Fragment implements View.OnClickListener {

    /* renamed from: a */
    private ConnectInfoView f3436a;

    /* renamed from: b */
    private Dialog f3437b;

    /* renamed from: c */
    public InterfaceC0708a f3438c;

    /* compiled from: ConnecttedFragment.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.activity.a$a */
    /* loaded from: classes.dex */
    public interface InterfaceC0708a {
        /* renamed from: d */
        void mo783d();

        /* renamed from: f */
        boolean mo782f();
    }

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_connectted, viewGroup, false);
        TitleBar titleBar = (TitleBar) inflate.findViewById(R.id.titlebar);
        titleBar.m218a();
        titleBar.m214b();
        inflate.findViewById(R.id.connectted_btn_disconnect).setOnClickListener(this);
        this.f3436a = (ConnectInfoView) inflate.findViewById(R.id.connectted_connect_info);
        View findViewById = inflate.findViewById(R.id.connectted_btn_disconnect);
        if (this.f3438c.mo782f()) {
            findViewById.setVisibility(View.GONE);
        } else {
            findViewById.setOnClickListener(this);
        }
        return inflate;
    }

    @Override // android.app.Fragment
    public final void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.f3438c = (InterfaceC0708a) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement ConnectedFragmentListener.");
        }
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        this.f3436a.m220b();
    }

    /* renamed from: a */
    public final void m785a() {
        this.f3436a.m220b();
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.connectted_btn_disconnect /* 2131624033 */:
                CustomDialogBuilder m209a = CustomDialogBuilder.m209a(getActivity());
                m209a.setMessage(R.string.disconnect_dialog_message);
                m209a.setTitle(R.string.disconnect_dialog_title);
                this.f3437b = m209a.create();
                m209a.m202b(R.string.disconnect_dialog_cancel, null);
                m209a.m210a(R.string.disconnect_dialog_yes, new View$OnClickListenerC0709b(this));
                this.f3437b.show();
                return;
            default:
                return;
        }
    }

    @Override // android.app.Fragment
    public final void onDestroyView() {
        if (this.f3437b != null) {
            this.f3437b.dismiss();
        }
        super.onDestroyView();
    }
}
