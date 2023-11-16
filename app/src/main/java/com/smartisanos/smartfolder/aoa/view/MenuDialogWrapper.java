package com.smartisanos.smartfolder.aoa.view;

import android.content.Context;
import android.view.View;
import com.smartisanos.smartfolder.aoa.R;
import java.util.ArrayList;
import java.util.List;
import smartisanos.app.MenuDialog;
import smartisanos.app.MenuDialogListAdapter;

/* renamed from: com.smartisanos.smartfolder.aoa.view.d */
/* loaded from: classes.dex */
public final class MenuDialogWrapper {

    /* renamed from: a */
    Context f3964a;

    /* renamed from: b */
    private MenuDialog f3965b;

    /* renamed from: c */
    private ArrayList<View.OnClickListener> f3966c = new ArrayList<>();

    /* renamed from: d */
    private ArrayList<String> f3967d = new ArrayList<>();

    /* renamed from: e */
    private MenuDialogListAdapter f3968e;

    public MenuDialogWrapper(Context context) {
        this.f3964a = context;
        if (this.f3965b == null) {
            this.f3965b = new MenuDialog(this.f3964a);
        }
        this.f3968e = new MenuDialogListAdapter(this.f3964a, this.f3967d, this.f3966c);
        this.f3965b.m86a(this.f3968e);
    }

    /* renamed from: a */
    public final void m199a() {
        this.f3965b.show();
    }

    /* renamed from: b */
    public final void m195b() {
        this.f3965b.dismiss();
    }

    /* renamed from: a */
    public final void m198a(int i) {
        this.f3965b.setTitle(i);
    }

    /* renamed from: a */
    public final void m196a(List<C0835a> list) {
        this.f3966c.clear();
        this.f3967d.clear();
        this.f3968e.notifyDataSetChanged();
        for (C0835a c0835a : list) {
            int i = c0835a.f3969a;
            View.OnClickListener onClickListener = c0835a.f3970b;
            String string = this.f3964a.getResources().getString(i);
            if (!this.f3967d.contains(string)) {
                this.f3967d.add(string);
                this.f3966c.add(onClickListener);
            }
        }
        this.f3968e.notifyDataSetChanged();
    }

    /* renamed from: a */
    public final void m197a(View.OnClickListener onClickListener) {
        this.f3965b.m87a(onClickListener);
    }

    /* compiled from: MenuDialogWrapper.java */
    /* renamed from: com.smartisanos.smartfolder.aoa.view.d$a */
    /* loaded from: classes.dex */
    public static class C0835a {

        /* renamed from: a */
        int f3969a = R.string.follow_dialog_coyp_text;

        /* renamed from: b */
        View.OnClickListener f3970b;

        public C0835a(View.OnClickListener onClickListener) {
            this.f3970b = onClickListener;
        }
    }
}
