package smartisanos.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import smartisanos.widget.SearchBar;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SearchBar.java */
/* renamed from: smartisanos.widget.k */
/* loaded from: classes.dex */
public final class C0947k extends AnimatorListenerAdapter {

    /* renamed from: a */
    final /* synthetic */ boolean f4501a;

    /* renamed from: b */
    final /* synthetic */ SearchBar f4502b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C0947k(SearchBar searchBar, boolean z) {
        this.f4502b = searchBar;
        this.f4501a = z;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        SearchBar.InterfaceC0928b interfaceC0928b;
        SearchBar.InterfaceC0927a interfaceC0927a;
        SearchBar.InterfaceC0928b unused;
        SearchBar.InterfaceC0927a unused2;
        this.f4502b.f4387k = true;
        interfaceC0928b = this.f4502b.f4384h;
        if (interfaceC0928b != null) {
            unused = this.f4502b.f4384h;
        }
        interfaceC0927a = this.f4502b.f4385i;
        if (interfaceC0927a != null) {
            unused2 = this.f4502b.f4385i;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        SearchBar.InterfaceC0928b interfaceC0928b;
        SearchBar.InterfaceC0927a interfaceC0927a;
        SearchBar.InterfaceC0928b unused;
        SearchBar.InterfaceC0927a unused2;
        interfaceC0928b = this.f4502b.f4384h;
        if (interfaceC0928b != null) {
            unused = this.f4502b.f4384h;
        }
        interfaceC0927a = this.f4502b.f4385i;
        if (interfaceC0927a != null) {
            unused2 = this.f4502b.f4385i;
        }
        this.f4502b.post(new RunnableC0948l(this));
        this.f4502b.f4387k = false;
    }
}
