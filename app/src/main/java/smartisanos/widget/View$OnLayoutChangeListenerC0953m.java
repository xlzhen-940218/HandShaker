package smartisanos.widget;

import android.animation.AnimatorSet;
import android.view.View;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SearchBar.java */
/* renamed from: smartisanos.widget.m */
/* loaded from: classes.dex */
public final class View$OnLayoutChangeListenerC0953m implements View.OnLayoutChangeListener {

    /* renamed from: a */
    final /* synthetic */ AnimatorSet f4563a;

    /* renamed from: b */
    final /* synthetic */ SearchBar f4564b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnLayoutChangeListenerC0953m(SearchBar searchBar, AnimatorSet animatorSet) {
        this.f4564b = searchBar;
        this.f4563a = animatorSet;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.f4564b.removeOnLayoutChangeListener(this);
        this.f4563a.start();
    }
}
