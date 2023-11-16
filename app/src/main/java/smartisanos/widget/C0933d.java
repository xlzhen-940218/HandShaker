package smartisanos.widget;

import android.animation.Animator;

/* compiled from: QuickBar.java */
/* renamed from: smartisanos.widget.d */
/* loaded from: classes.dex */
final class C0933d implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ QuickBar f4463a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0933d(QuickBar quickBar) {
        this.f4463a = quickBar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f4463a.f4248G = true;
        this.f4463a.f4284e = true;
        this.f4463a.invalidate();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f4463a.f4248G = false;
        QuickBar.m64a(this.f4463a);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }
}
