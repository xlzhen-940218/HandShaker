package smartisanos.widget;

import android.animation.Animator;

/* compiled from: RQuickBar.java */
/* renamed from: smartisanos.widget.g */
/* loaded from: classes.dex */
final class C0943g implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ RQuickBar f4494a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0943g(RQuickBar rQuickBar) {
        this.f4494a = rQuickBar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f4494a.f4316G = true;
        this.f4494a.f4352e = true;
        this.f4494a.invalidate();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f4494a.f4316G = false;
        RQuickBar.m53a(this.f4494a);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }
}
