package com.smartisan.feedbackhelper.upload;

import com.smartisan.feedbackhelper.upload.UploadWorker;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UploadWorker.java */
/* renamed from: com.smartisan.feedbackhelper.upload.m */
/* loaded from: classes.dex */
public final /* synthetic */ class C0450m {

    /* renamed from: a */
    static final /* synthetic */ int[] f2501a = new int[UploadWorker.EnumC0447a.values().length];

    static {
        try {
            f2501a[UploadWorker.EnumC0447a.PREPARE.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            f2501a[UploadWorker.EnumC0447a.COMPRESS.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            f2501a[UploadWorker.EnumC0447a.CHANGE_STATE.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
        try {
            f2501a[UploadWorker.EnumC0447a.REMOVE_FILES.ordinal()] = 4;
        } catch (NoSuchFieldError e4) {
        }
        try {
            f2501a[UploadWorker.EnumC0447a.CHANGE_LOGPATH.ordinal()] = 5;
        } catch (NoSuchFieldError e5) {
        }
        try {
            f2501a[UploadWorker.EnumC0447a.START_TO_UPLOAD.ordinal()] = 6;
        } catch (NoSuchFieldError e6) {
        }
    }
}