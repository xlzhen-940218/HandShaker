package smartisanos.widget.letters;

import android.animation.Animator;
import smartisanos.widget.letters.QuickBarEx;

/* compiled from: QuickBarEx.java */
/* renamed from: smartisanos.widget.letters.d */
/* loaded from: classes.dex */
final class C0952d implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ QuickBarEx f4562a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0952d(QuickBarEx quickBarEx) {
        this.f4562a = quickBarEx;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f4562a.f4541p = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        QuickBarEx.InterfaceC0950a interfaceC0950a;
        QuickBarEx.InterfaceC0950a unused;
        this.f4562a.f4541p = false;
        QuickBarEx.m5d(this.f4562a);
        interfaceC0950a = this.f4562a.f4540o;
        if (interfaceC0950a != null) {
            unused = this.f4562a.f4540o;
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }
}
