package com.smartisanos.smartfolder.aoa.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.net.wifi.WifiInfo;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.MainActivity;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.DeviceInfoHelper;
import com.smartisanos.smartfolder.aoa.p056h.NetWorkUtils;
import com.smartisanos.smartfolder.aoa.p056h.Tracker;

/* loaded from: classes.dex */
public class ConnectInfoView extends RelativeLayout {

    /* renamed from: a */
    private TextView wifiPhoneNameTextView;

    /* renamed from: b */
    private TextView wifiSSIDTextView;

    /* renamed from: c */
    private TextView f3934c;

    /* renamed from: d */
    private TextView connectInfoTipTextView;

    /* renamed from: e */
    private View f3936e;

    /* renamed from: f */
    private View f3937f;

    /* renamed from: g */
    private ImageView f3938g;

    /* renamed from: h */
    private View.OnClickListener f3939h;

    public ConnectInfoView(Context context) {
        super(context);
        m223a(context);
    }

    public ConnectInfoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m223a(context);
    }

    public ConnectInfoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m223a(context);
    }

    /* renamed from: a */
    private void m223a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_connect_info, (ViewGroup) this, true);
        this.wifiPhoneNameTextView = (TextView) findViewById(R.id.wifi_phone_name);
        this.wifiSSIDTextView = (TextView) findViewById(R.id.wifi_ssid);
        this.connectInfoTipTextView = (TextView) findViewById(R.id.connect_info_tip);
        this.f3934c = (TextView) findViewById(R.id.connect_info_tip_for_image);
        this.f3936e = findViewById(R.id.connect_info_image_phone);
        this.f3937f = findViewById(R.id.connect_info_image_mac);
        this.f3938g = (ImageView) findViewById(R.id.connect_info_image_connect_icon);
        int color = context.getResources().getColor(R.color.cannot_connect_tip);
        String string = getContext().getResources().getString(R.string.wifi_scan_connect);
        String string2 = getContext().getResources().getString(R.string.wifi_cant_connect);
        int length = string.length();
        int length2 = string2.length();
        int integer = getContext().getResources().getInteger(R.integer.connect_tip_click_gap);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) string);
        spannableStringBuilder.append((CharSequence) string2);
        spannableStringBuilder.setSpan(new C0833b(), 0, length, 18);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(color), 0, length, 18);
        spannableStringBuilder.setSpan(new C0832a(), length + integer, (length + length2) - 1, 18);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(color), length + integer, (length + length2) - 1, 18);
        this.connectInfoTipTextView.setText(R.string.wifi_middle_tip);
        this.connectInfoTipTextView.append(spannableStringBuilder);
        this.connectInfoTipTextView.setHighlightColor(0);
        this.connectInfoTipTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* renamed from: a */
    public final void m224a() {
        HandShaker.info("ConnectInfoView", "showForConnectting");
        this.connectInfoTipTextView.setVisibility(View.VISIBLE);
        this.f3934c.setText(R.string.wifi_top_tip);
        this.f3936e.setBackgroundResource(R.drawable.phone_disconnected);
        this.f3937f.setBackgroundResource(R.drawable.mac_disconnected);
        this.f3938g.setImageResource(R.drawable.anim_connectting);
        ((AnimationDrawable) this.f3938g.getDrawable()).start();
        m219c();
    }

    /* renamed from: b */
    public final void m220b() {
        String string;
        HandShaker.info("ConnectInfoView", "showForConnectted");
        this.connectInfoTipTextView.setVisibility(View.GONE);
        String str = FolderApp.f3381e;
        if (((MainActivity) getContext()).mo782f()) {
            getResources().getString(R.string.connect_info_usb_show_name);
            string = getResources().getString(R.string.connect_info_usb_tip);
            this.f3936e.setBackgroundResource(R.drawable.phone_usb_connectted);
            this.f3937f.setBackgroundResource(R.drawable.mac_usb_connectted);
        } else {
            string = getResources().getString(R.string.connect_info_wifi_tip, str);
            this.f3936e.setBackgroundResource(R.drawable.phone_connected);
            this.f3937f.setBackgroundResource(R.drawable.mac_connected);
        }
        this.f3934c.setText(string);
        this.f3938g.setBackgroundResource(R.drawable.icon_connected);
        m219c();
    }

    /* renamed from: c */
    ConnectivityManager connectivityManager;

    private void m219c() {
        String m447c = DeviceInfoHelper.getDeviceInfoHelper().getDeviceInfo().getDeviceName();
        String m378b = NetWorkUtils.getWifiName(FolderApp.getInstance());
        HandShaker.info("ConnectInfoView", "refreshWifiInfo phoneName: " + m447c + ",  ssid: " + m378b);
        this.wifiPhoneNameTextView.setText(m447c);
        this.wifiSSIDTextView.setText(m378b);

        final NetworkRequest request =
                new NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .build();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
            connectivityManager = getContext().getSystemService(ConnectivityManager.class);

            final ConnectivityManager.NetworkCallback networkCallback = new ConnectivityManager.NetworkCallback(ConnectivityManager.NetworkCallback.FLAG_INCLUDE_LOCATION_INFO) {
                @Override
                public void onAvailable(@NonNull Network network) {
                    super.onAvailable(network);
                }

                @Override
                public void onCapabilitiesChanged(@NonNull Network network, @NonNull NetworkCapabilities networkCapabilities) {
                    super.onCapabilitiesChanged(network, networkCapabilities);
                    WifiInfo wifiInfo = (WifiInfo) networkCapabilities.getTransportInfo();
                    wifiSSIDTextView.setText(wifiInfo.getSSID());
                }
                // etc.
            };
            connectivityManager.requestNetwork(request, networkCallback); // For request
            connectivityManager.registerNetworkCallback(request, networkCallback);
        }
    }

    /* renamed from: a */
    public final void m222a(View.OnClickListener onClickListener) {
        this.f3939h = onClickListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.smartisanos.smartfolder.aoa.view.ConnectInfoView$a */
    /* loaded from: classes.dex */
    public class C0832a extends ClickableSpan {

        /* renamed from: a */
        URLSpan f3940a = new URLSpan("http://bbs.smartisan.com/thread-516359-1-1.html");

        C0832a() {
        }

        @Override // android.text.style.ClickableSpan
        public final void onClick(View view) {
            HandShaker.info("ConnectInfoView", "CantConnectClickableSpan onClick");
            Tracker.m523a("A300005");
            this.f3940a.onClick(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.smartisanos.smartfolder.aoa.view.ConnectInfoView$b */
    /* loaded from: classes.dex */
    public class C0833b extends ClickableSpan {
        C0833b() {
        }

        @Override // android.text.style.ClickableSpan
        public final void onClick(View view) {
            HandShaker.info("ConnectInfoView", "QRCode, text onClick");
            if (ConnectInfoView.this.f3939h != null) {
                ConnectInfoView.this.f3939h.onClick(view);
            }
        }
    }
}
