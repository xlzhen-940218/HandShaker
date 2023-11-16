package smartisanos.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SearchBar.java */
/* renamed from: smartisanos.widget.i */
/* loaded from: classes.dex */
public final class C0945i extends AnimatorListenerAdapter {

    /* renamed from: a */
    final /* synthetic */ boolean f4497a;

    /* renamed from: b */
    final /* synthetic */ SearchBar f4498b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0945i(SearchBar searchBar, boolean z) {
        this.f4498b = searchBar;
        this.f4497a = z;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        boolean z;
        TextView textView;
        ImageView imageView;
        z = this.f4498b.f4388l;
        if (z) {
            if (!this.f4497a) {
                imageView = this.f4498b.f4383g;
                imageView.setVisibility(View.VISIBLE);
                return;
            }
        } else if (!this.f4497a) {
            textView = this.f4498b.f4379c;
            textView.setVisibility(View.GONE);
        }
        SearchBar.m41a(this.f4498b, this.f4497a);
    }
}
