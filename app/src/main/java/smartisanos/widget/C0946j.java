package smartisanos.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SearchBar.java */
/* renamed from: smartisanos.widget.j */
/* loaded from: classes.dex */
public final class C0946j extends AnimatorListenerAdapter {

    /* renamed from: a */
    final /* synthetic */ boolean f4499a;

    /* renamed from: b */
    final /* synthetic */ SearchBar f4500b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0946j(SearchBar searchBar, boolean z) {
        this.f4500b = searchBar;
        this.f4499a = z;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ImageView imageView;
        TextView textView;
        TextView textView2;
        imageView = this.f4500b.f4383g;
        imageView.setVisibility(this.f4499a ? 4 : 0);
        if (this.f4499a) {
            textView2 = this.f4500b.f4379c;
            textView2.setVisibility(View.VISIBLE);
            return;
        }
        textView = this.f4500b.f4379c;
        textView.setVisibility(View.GONE);
        SearchBar.m41a(this.f4500b, this.f4499a);
    }
}
