package com.smartisanos.smartfolder.aoa.p052d;

import com.smartisanos.smartfolder.aoa.p049a.EventManager;

/* compiled from: MediaUtils.java */
/* renamed from: com.smartisanos.smartfolder.aoa.d.f */
/* loaded from: classes.dex */
final /* synthetic */ class C0731f {

    /* renamed from: a */
    static final /* synthetic */ int[] f3511a = new int[EventManager.REQUEST_TYPE.values().length];

    static {
        try {
            f3511a[EventManager.REQUEST_TYPE.IMAGE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f3511a[EventManager.REQUEST_TYPE.VIDEO.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f3511a[EventManager.REQUEST_TYPE.AUDIO.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
