package com.smartisanos.smartfolder.aoa.p056h;

import android.app.AlertDialog;
import android.content.Context;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p050b.TrustRequestEvent;
import com.smartisanos.smartfolder.aoa.view.CustomDialogBuilder;

/* renamed from: com.smartisanos.smartfolder.aoa.h.l */
/* loaded from: classes.dex */
public final class DialogUtils {
    /* renamed from: a */
    public static AlertDialog m421a(Context context, TrustRequestEvent trustRequestEvent) {
        CustomDialogBuilder m209a = CustomDialogBuilder.m209a(context);
        String string = context.getResources().getString(R.string.try_to_connect, trustRequestEvent.m766a().m624b());
        m209a.setTitle(R.string.is_trust_host);
        m209a.setMessage(string);
        int m765b = trustRequestEvent.m765b();
        String m764c = trustRequestEvent.m764c();
        m209a.m202b(R.string.trust_no, new View$OnClickListenerC0775m(m765b, m764c));
        m209a.m208a(new View$OnClickListenerC0776n(m765b, m764c));
        m209a.m210a(R.string.trust_always, new View$OnClickListenerC0777o(m765b, m764c));
        AlertDialog create = m209a.create();
        create.setCanceledOnTouchOutside(false);
        return create;
    }

    /* renamed from: a */
    public static AlertDialog m422a(Context context) {
        CustomDialogBuilder m209a = CustomDialogBuilder.m209a(context);
        String string = context.getResources().getString(R.string.scan_link_error_dialog_content);
        m209a.setTitle(R.string.scan_link_error_dialog_title);
        m209a.setMessage(string);
        m209a.m210a(R.string.button_verify_txt, new View$OnClickListenerC0778p());
        AlertDialog create = m209a.create();
        create.setCancelable(false);
        return create;
    }
}
