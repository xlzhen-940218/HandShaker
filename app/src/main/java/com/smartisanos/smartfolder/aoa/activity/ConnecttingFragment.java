package com.smartisanos.smartfolder.aoa.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.CommonUtils;
import com.smartisanos.smartfolder.aoa.p056h.NetWorkUtils;
import com.smartisanos.smartfolder.aoa.p056h.Tracker;
import com.smartisanos.smartfolder.aoa.view.ConnectInfoView;
import com.smartisanos.smartfolder.aoa.view.TitleBar;

/* renamed from: com.smartisanos.smartfolder.aoa.activity.c */
/* loaded from: classes.dex */
public final class ConnecttingFragment extends Fragment implements View.OnClickListener {

    /* renamed from: a */
    private ConnectInfoView hasWifiLayoutView;

    /* renamed from: b */
    View noWifiLayoutView;

    /* renamed from: c */
    View f3442c;

    /* renamed from: d */
    private TextView bottomTipView;

    /* renamed from: e */
    AlertDialog f3444e;

    /* renamed from: f */
    private BroadcastReceiver f3445f = new C0710d(this);

    @Override // android.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_connectting, viewGroup, false);
        this.hasWifiLayoutView = (ConnectInfoView) inflate.findViewById(R.id.connectting_wifi_layout);
        this.noWifiLayoutView = inflate.findViewById(R.id.connectting_no_wifi_layout);
        this.bottomTipView = (TextView) inflate.findViewById(R.id.connectting_bottom_tip);
        TitleBar titleBar = (TitleBar) inflate.findViewById(R.id.titlebar);
        titleBar.m213b(this);
        titleBar.m218a();
        if (CommonUtils.isSmartisanPhone()) {
            titleBar.m215a(this);
            titleBar.m214b();
        }
        inflate.findViewById(R.id.no_wifi_btn_settings).setOnClickListener(this);
        this.hasWifiLayoutView.m222a(new StartScanQRCodeClickListener(this));
        m781a();
        getActivity().registerReceiver(this.f3445f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        String m482t = CommonUtils.getDebugBuildTime();
        HandShaker.debug("showBuildTime buildTime: " + m482t);
        if (!TextUtils.isEmpty(m482t)) {
            this.bottomTipView.setText(((Object) this.bottomTipView.getText()) + "(" + m482t + ")");
        }
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m781a() {
        boolean m379a = NetWorkUtils.m379a(FolderApp.getInstance());
        HandShaker.info("ConnecttingFragment", "refreshLayout isWifi: " + m379a);
        if (m379a) {
            if (this.f3442c != this.hasWifiLayoutView) {
                this.f3442c = this.hasWifiLayoutView;
                this.hasWifiLayoutView.setVisibility(View.VISIBLE);
                this.noWifiLayoutView.setVisibility(View.GONE);
                this.hasWifiLayoutView.m224a();
            }
        } else if (this.f3442c != this.noWifiLayoutView) {
            this.f3442c = this.noWifiLayoutView;
            m777b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m777b() {
        this.hasWifiLayoutView.setVisibility(View.GONE);
        this.noWifiLayoutView.setVisibility(View.VISIBLE);
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (view.getId()) {
            case R.id.no_wifi_btn_settings /* 2131624083 */:
                HandShaker.info("ConnecttingFragment", "btn settings clicked");
                startActivity(new Intent("android.net.wifi.PICK_WIFI_NETWORK"));
                return;
            case R.id.title_btn_back /* 2131624098 */:
                getActivity().onBackPressed();
                return;
            case R.id.title_btn_settings /* 2131624099 */:
                Tracker.m523a("A300004");
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                return;
            default:
                return;
        }
    }

    @Override // android.app.Fragment
    public final void onDestroyView() {
        super.onDestroyView();
        getActivity().unregisterReceiver(this.f3445f);
        m775c();
    }

    @Override // android.app.Fragment
    public final void onResume() {
        super.onResume();
        this.hasWifiLayoutView.m224a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m775c() {
        if (this.f3444e != null) {
            if (this.f3444e.isShowing()) {
                this.f3444e.dismiss();
            }
            this.f3444e = null;
        }
    }
}
