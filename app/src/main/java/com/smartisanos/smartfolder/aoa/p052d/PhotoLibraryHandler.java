package com.smartisanos.smartfolder.aoa.p052d;

import java.util.ArrayList;

/* renamed from: com.smartisanos.smartfolder.aoa.d.g */
/* loaded from: classes.dex */
public final class PhotoLibraryHandler extends BaseHandler {

    /* renamed from: a */
    private static PhotoLibraryHandler f3512a = null;

    /* renamed from: a */
    public static PhotoLibraryHandler m705a() {
        if (f3512a == null) {
            f3512a = new PhotoLibraryHandler();
        }
        return f3512a;
    }

    private PhotoLibraryHandler() {
    }

    /* renamed from: b */
    public static ArrayList<ArrayList> m704b() {
        return MediaUtils.m710d();
    }
}
