package com.smartisanos.smartfolder.aoa.p055g;

import android.os.Handler;
import com.smartisanos.smartfolder.aoa.FolderApp;
import java.util.HashSet;
import java.util.Set;

/* renamed from: com.smartisanos.smartfolder.aoa.g.e */
/* loaded from: classes.dex */
public final class SSPEventDispatcher implements SSPEventListener {

    /* renamed from: a */
    private static final SSPEventDispatcher f3608a = new SSPEventDispatcher();

    /* renamed from: b */
    private Set<SSPEventListener> f3609b = new HashSet();

    /* renamed from: c */
    private Handler f3610c = new Handler(FolderApp.getInstance().getMainLooper());

    private SSPEventDispatcher() {
    }

    /* renamed from: a */
    public static SSPEventDispatcher m596a() {
        return f3608a;
    }

    /* renamed from: a */
    public final void m593a(SSPEventListener sSPEventListener) {
        this.f3609b.add(sSPEventListener);
    }

    /* renamed from: b */
    public final void m592b(SSPEventListener sSPEventListener) {
        this.f3609b.remove(sSPEventListener);
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.SSPEventListener
    /* renamed from: e */
    public final void mo589e() {
        m595a(1, new Object[0]);
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.SSPEventListener
    /* renamed from: a */
    public final void mo590a(String str, String str2) {
        m595a(3, str, str2);
    }

    @Override // com.smartisanos.smartfolder.aoa.p055g.SSPEventListener
    /* renamed from: a */
    public final void mo591a(int hostType, int hostAppVersionCode, String hostAppVersion) {
        m595a(4, Integer.valueOf(hostType), Integer.valueOf(hostAppVersionCode), hostAppVersion);
    }

    /* renamed from: a */
    private void m595a(int i, Object... objArr) {
        this.f3610c.post(new RunnableC0752f(this, i, objArr));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m594a(SSPEventDispatcher sSPEventDispatcher, int i, Object[] objArr) {
        for (SSPEventListener sSPEventListener : sSPEventDispatcher.f3609b) {
            switch (i) {
                case 1:
                    sSPEventListener.mo589e();
                    break;
                case 3:
                    sSPEventListener.mo590a((String) objArr[0], (String) objArr[1]);
                    break;
                case 4:
                    sSPEventListener.mo591a(((Integer) objArr[0]).intValue(), ((Integer) objArr[1]).intValue(), (String) objArr[2]);
                    break;
            }
        }
    }
}
