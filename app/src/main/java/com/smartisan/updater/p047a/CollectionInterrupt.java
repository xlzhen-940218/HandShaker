package com.smartisan.updater.p047a;

import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.smartisan.updater.a.a */
/* loaded from: classes.dex */
public final class CollectionInterrupt {

    /* renamed from: a */
    private List<Interrupt> f2716a = new ArrayList();

    /* renamed from: b */
    private Context f2717b;

    public CollectionInterrupt(Context context) {
        this.f2717b = context;
    }

    /* renamed from: a */
    public final Bundle m1862a(Bundle bundle) {
        for (Interrupt interrupt : this.f2716a) {
            bundle = interrupt.m1860a();
        }
        return bundle;
    }
}
