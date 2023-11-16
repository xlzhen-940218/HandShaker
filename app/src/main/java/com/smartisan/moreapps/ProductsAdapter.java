package com.smartisan.moreapps;

import static android.os.Build.VERSION_CODES.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.smartisan.moreapps.AppInfoList;
import com.smartisan.moreapps.download.AppDownloader;
import com.smartisan.p043a.C0411a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: com.smartisan.moreapps.e */
/* loaded from: classes.dex */
public final class ProductsAdapter extends BaseAdapter {

    /* renamed from: a */
    private AppInfoList f2646a;

    /* renamed from: b */
    public Context f2647b;

    public ProductsAdapter(Context context) {
        this.f2647b = context;
        this.f2646a = new AppInfoList(this.f2647b);
    }

    /* renamed from: a */
    public final void m1982a() {
        this.f2646a.m2005a();
        m1977b();
        this.f2646a.m2003a(this.f2647b.getPackageName());
    }

    /* renamed from: b */
    private void m1977b() {
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        List<ResolveInfo> queryIntentActivities = this.f2647b.getPackageManager().queryIntentActivities(intent, 0);
        if (!queryIntentActivities.isEmpty()) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                String str = resolveInfo.activityInfo.packageName;
                for (int i = 0; i < this.f2646a.m2000b(); i++) {
                    AppInfoList.C0464a m2004a = this.f2646a.m2004a(i);
                    if (str.equals(m2004a.f2634d)) {
                        this.f2646a.m2002a(m2004a.f2634d, resolveInfo.activityInfo.name);
                        m2004a.f2631a = true;
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public final boolean m1979a(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (int i = 0; i < this.f2646a.m2000b(); i++) {
            AppInfoList.C0464a m2004a = this.f2646a.m2004a(i);
            if (str.equals(m2004a.f2634d)) {
                m2004a.f2631a = z;
                if (!m2004a.f2631a) {
                    this.f2646a.m2002a(str, null);
                } else if (!TextUtils.isEmpty(str)) {
                    Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
                    intent.addCategory("android.intent.category.LAUNCHER");
                    intent.setPackage(str);
                    List<ResolveInfo> queryIntentActivities = this.f2647b.getPackageManager().queryIntentActivities(intent, 0);
                    if (!queryIntentActivities.isEmpty()) {
                        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            ResolveInfo next = it.next();
                            if (next.activityInfo.packageName.equals(str)) {
                                this.f2646a.m2002a(str, next.activityInfo.name);
                                break;
                            }
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final void m1978a(ArrayList<AppInfoList.C0464a> arrayList) {
        if (arrayList != null && arrayList.size() > 0) {
            this.f2646a.m2001a(arrayList);
            m1977b();
            this.f2646a.m2003a(this.f2647b.getPackageName());
        }
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f2646a.m2000b();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return this.f2646a.m2004a(i);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        return true;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = ((LayoutInflater) this.f2647b.getSystemService("layout_inflater")).inflate(C0411a.C0415d.product_list_item, (ViewGroup) null);
        }
        if (i == 0) {
            view.setBackgroundResource(C0411a.C0412a.list_item_top);
        } else if (i == this.f2646a.m2000b() - 1) {
            view.setBackgroundResource(C0411a.C0412a.list_item_bottom);
        } else {
            view.setBackgroundResource(C0411a.C0412a.list_item_middle);
        }
        AppInfoList.C0464a m2004a = this.f2646a.m2004a(i);
        if (m2004a != null) {
            ImageView imageView = (ImageView) view.findViewById(C0411a.C0413b.product_app_icon);
            if (imageView != null) {
                imageView.setImageBitmap(m2004a.f2636f);
            }
            ((TextView) view.findViewById(C0411a.C0413b.product_app_name)).setText(m2004a.f2632b);
            ((TextView) view.findViewById(C0411a.C0413b.product_app_describe)).setText(m2004a.f2633c);
            Button button = (Button) view.findViewById(C0411a.C0413b.product_app_operation);
            String str = m2004a.f2634d;
            if (m2004a.f2631a) {
                button.setText(C0411a.C0416e.app_open_txt);
                button.setOnClickListener(new View$OnClickListenerC0468h(this, str));
            } else {
                button.setText(C0411a.C0416e.app_install_txt);
                button.setOnClickListener(new View$OnClickListenerC0469i(this, str));
            }
        }
        return view;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m1980a(ProductsAdapter productsAdapter, String str) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setComponent(productsAdapter.f2646a.m1999b(str));
        productsAdapter.f2647b.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public static /* synthetic */ void m1976b(ProductsAdapter productsAdapter, String str) {
        if (!TextUtils.isEmpty(str)) {
            String m1998c = productsAdapter.f2646a.m1998c(str);
            int m1966a = SmartisanAppUtils.m1966a(productsAdapter.f2647b);
            if (m1998c != null) {
                if (m1966a == 1) {
                    new AppDownloader(productsAdapter.f2647b).m1988a(str, m1998c);
                } else if (m1966a != 2) {
                    if (m1966a != 0) {
                        return;
                    }
                    SharedPreferences sharedPreferences = productsAdapter.f2647b.getSharedPreferences("download_pending", 0);
                    String string = sharedPreferences.getString("package_names", "##");
                    String string2 = sharedPreferences.getString("location_names", "##");
                    if (!string.contains(str) && !string2.contains(m1998c)) {
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("package_names", string + str + "##");
                        edit.putString("location_names", string2 + m1998c + "##");
                        edit.commit();
                    }
                    Toast.makeText(productsAdapter.f2647b, productsAdapter.f2647b.getString(C0411a.C0416e.download_no_net), Toast.LENGTH_SHORT).show();
                } else {
                    new AlertDialog.Builder(new ContextThemeWrapper(productsAdapter.f2647b, android.R.style.Theme_DeviceDefault_Light)).setTitle(productsAdapter.f2647b.getString(C0411a.C0416e.download_dialog_title)).setMessage(productsAdapter.f2647b.getString(C0411a.C0416e.download_dialog_message)).setNegativeButton(productsAdapter.f2647b.getString(C0411a.C0416e.download_dialog_cancel), new DialogInterface$OnClickListenerC0467g(productsAdapter)).setPositiveButton(productsAdapter.f2647b.getString(C0411a.C0416e.download_dialog_download), new DialogInterface$OnClickListenerC0466f(productsAdapter, str, m1998c)).create().show();
                }
            }
        }
    }
}
