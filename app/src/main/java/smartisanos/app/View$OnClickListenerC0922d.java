package smartisanos.app;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;
import java.util.List;

/* compiled from: MenuDialogListAdapter.java */
/* renamed from: smartisanos.app.d */
/* loaded from: classes.dex */
final class View$OnClickListenerC0922d implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ int f4184a;

    /* renamed from: b */
    final /* synthetic */ TextView f4185b;

    /* renamed from: c */
    final /* synthetic */ MenuDialogListAdapter f4186c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0922d(MenuDialogListAdapter menuDialogListAdapter, int i, TextView textView) {
        this.f4186c = menuDialogListAdapter;
        this.f4184a = i;
        this.f4185b = textView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Dialog dialog;
        List list;
        Dialog dialog2;
        dialog = this.f4186c.f4181b;
        if (dialog != null) {
            dialog2 = this.f4186c.f4181b;
            dialog2.dismiss();
        }
        list = this.f4186c.f4183d;
        ((View.OnClickListener) list.get(this.f4184a)).onClick(this.f4185b);
    }
}
