package smartisanos.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.smartisanos.smartfolder.aoa.R;

/* renamed from: smartisanos.app.a */
/* loaded from: classes.dex */
public final class MenuDialog extends Dialog {

    /* renamed from: a */
    private TextView title;

    /* renamed from: b */
    private TextView cancelRightBtn;

    /* renamed from: c */
    private TextView cancelLeftBtn;

    /* renamed from: d */
    private TextView okBtn;

    /* renamed from: e */
    private ListView contentList;

    /* renamed from: f */
    private Context f4177f;

    /* renamed from: g */
    private View.OnClickListener f4178g;

    public MenuDialog(Context context) {
        super(context, R.style.MenuDialogTheme);
        this.title = null;
        this.cancelRightBtn = null;
        this.cancelLeftBtn = null;
        this.contentList = null;
        this.f4178g = new View$OnClickListenerC0921b(this);
        this.f4177f = context;
        setContentView(R.layout.menu_dialog);
        this.title = (TextView) findViewById(R.id.title);
        this.cancelRightBtn = (TextView) findViewById(R.id.btn_cancel_right);
        this.cancelRightBtn.setOnClickListener(this.f4178g);
        this.cancelLeftBtn = (TextView) findViewById(R.id.btn_cancel_left);
        this.cancelLeftBtn.setOnClickListener(this.f4178g);
        m88a();
        this.okBtn = (TextView) findViewById(R.id.btn_ok);
        this.contentList = (ListView) findViewById(R.id.content_list);
        this.contentList.setDividerHeight(0);
        getWindow().setGravity(80);
        getWindow().setLayout(-1, -2);
        getWindow().addFlags(262144);
        getWindow().addFlags(131072);
        setCanceledOnTouchOutside(true);
    }

    @Override // android.app.Dialog
    public final void setTitle(int i) {
        this.title.setText(i);
    }

    @Override // android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        this.title.setText(charSequence);
    }

    /* renamed from: a */
    private void m88a() {
        int i = 1;
        try {
            if (Build.VERSION.SDK_INT > 16) {
                i = Settings.Global.getInt(this.f4177f.getContentResolver(), "one_hand_mode", 1);
            }
        } catch (Exception e) {
        }
        if (i == 0) {
            this.cancelLeftBtn.setVisibility(View.VISIBLE);
            this.cancelRightBtn.setVisibility(View.INVISIBLE);
            return;
        }
        this.cancelLeftBtn.setVisibility(View.INVISIBLE);
        this.cancelRightBtn.setVisibility(View.VISIBLE);
    }

    /* renamed from: a */
    public final void m86a(MenuDialogListAdapter menuDialogListAdapter) {
        this.contentList.setVisibility(View.VISIBLE);
        this.contentList.setAdapter((ListAdapter) menuDialogListAdapter);
        this.contentList.getLayoutParams().height = -2;
        this.contentList.setBackgroundResource(R.drawable.menu_dialog_background);
        menuDialogListAdapter.m85a(this);
    }

    /* renamed from: a */
    public final void m87a(View.OnClickListener onClickListener) {
        this.cancelRightBtn.setOnClickListener(onClickListener);
        this.cancelLeftBtn.setOnClickListener(onClickListener);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final void onWindowFocusChanged(boolean z) {
        if (z) {
            m88a();
        }
    }
}
