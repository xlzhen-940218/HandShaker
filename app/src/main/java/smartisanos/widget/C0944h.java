package smartisanos.widget;

import android.animation.Animator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RQuickBar.java */
/* renamed from: smartisanos.widget.h */
/* loaded from: classes.dex */
public final class C0944h implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ int f4495a = 0;

    /* renamed from: b */
    final /* synthetic */ RQuickBar f4496b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0944h(RQuickBar rQuickBar) {
        this.f4496b = rQuickBar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f4496b.f4315F = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f4496b.f4315F = false;
        f4496b.m52a(this.f4496b, this.f4495a);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }
}
