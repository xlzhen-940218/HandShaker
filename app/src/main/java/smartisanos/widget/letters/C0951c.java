package smartisanos.widget.letters;

import android.animation.Animator;

/* compiled from: QuickBarEx.java */
/* renamed from: smartisanos.widget.letters.c */
/* loaded from: classes.dex */
final class C0951c implements Animator.AnimatorListener {

    /* renamed from: a */
    final /* synthetic */ QuickBarEx f4561a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0951c(QuickBarEx quickBarEx) {
        this.f4561a = quickBarEx;
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        LettersBar lettersBar;
        this.f4561a.f4542q = true;
        lettersBar = this.f4561a.f4532g;
        lettersBar.m16b(true);
        this.f4561a.invalidate();
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f4561a.f4542q = false;
        QuickBarEx.m6c(this.f4561a);
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
    }
}
