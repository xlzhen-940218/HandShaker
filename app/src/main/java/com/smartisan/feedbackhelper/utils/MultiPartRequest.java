package com.smartisan.feedbackhelper.utils;

import java.io.File;
import java.util.Map;

/* renamed from: com.smartisan.feedbackhelper.utils.k */
/* loaded from: classes.dex */
public interface MultiPartRequest {
    /* renamed from: s */
    Map<String, File> getFilePartMaps();

    /* renamed from: t */
    Map<String, String> getParamMaps();
}
