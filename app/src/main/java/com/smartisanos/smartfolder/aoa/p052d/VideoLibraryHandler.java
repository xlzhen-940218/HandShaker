package com.smartisanos.smartfolder.aoa.p052d;

import java.util.ArrayList;

/* renamed from: com.smartisanos.smartfolder.aoa.d.i */
/* loaded from: classes.dex */
public final class VideoLibraryHandler extends BaseHandler {

    /* renamed from: a */
    private static VideoLibraryHandler f3534a = null;

    private VideoLibraryHandler() {
    }

    /* renamed from: a */
    public static VideoLibraryHandler m687a() {
        if (f3534a == null) {
            f3534a = new VideoLibraryHandler();
        }
        return f3534a;
    }

    /* renamed from: b */
    public static ArrayList<ArrayList> m686b() {
        return MediaUtils.m712c();
    }
}
