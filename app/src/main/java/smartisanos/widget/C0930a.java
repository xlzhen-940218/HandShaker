package smartisanos.widget;

import android.animation.ValueAnimator;

/* compiled from: DownloadProgressView.java */
/* renamed from: smartisanos.widget.a */
/* loaded from: classes.dex */
final class C0930a implements ValueAnimator.AnimatorUpdateListener {

    /* renamed from: a */
    final /* synthetic */ DownloadProgressView f4461a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0930a(DownloadProgressView downloadProgressView) {
        this.f4461a = downloadProgressView;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.f4461a.f4194d = ((Integer) valueAnimator.getAnimatedValue()).intValue();
        this.f4461a.invalidate();
    }
}
