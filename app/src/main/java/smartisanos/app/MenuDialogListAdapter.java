package smartisanos.app;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.smartisanos.smartfolder.aoa.R;

import java.util.ArrayList;
import java.util.List;

/* renamed from: smartisanos.app.c */
/* loaded from: classes.dex */
public final class MenuDialogListAdapter extends BaseAdapter {

    /* renamed from: a */
    private Context f4180a;

    /* renamed from: b */
    public Dialog f4181b;

    /* renamed from: c */
    private List<String> f4182c;

    /* renamed from: d */
    public List<View.OnClickListener> f4183d;

    public MenuDialogListAdapter(Context context, List<String> list, List<View.OnClickListener> list2) {
        this.f4182c = new ArrayList();
        this.f4183d = new ArrayList();
        this.f4180a = context;
        this.f4182c = list;
        this.f4183d = list2;
        if (list == null || list2 == null || list.size() != list2.size()) {
            throw new IllegalArgumentException();
        }
    }

    /* renamed from: a */
    public final void m85a(Dialog dialog) {
        this.f4181b = dialog;
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f4182c.size();
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return this.f4182c.get(i);
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        View inflate = view == null ? LayoutInflater.from(this.f4180a).inflate(R.layout.menu_dialog_list_item, (ViewGroup) null) : view;
        TextView textView = (TextView) inflate;
        textView.setText(this.f4182c.get(i));
        textView.setOnClickListener(new View$OnClickListenerC0922d(this, i, textView));
        return inflate;
    }
}
