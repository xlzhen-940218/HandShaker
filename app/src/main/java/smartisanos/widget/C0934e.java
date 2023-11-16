package smartisanos.widget;

import android.animation.Animator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: QuickBar.java */
/* renamed from: smartisanos.widget.e */
/* loaded from: classes.dex */
public final class C0934e implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ int f4464a = 0;

    /* renamed from: b */
    final /* synthetic */ QuickBar f4465b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0934e(QuickBar quickBar) {
        this.f4465b = quickBar;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f4465b.f4247F = true;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f4465b.f4247F = false;
        f4465b.m63a(this.f4465b, this.f4464a);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }
}
