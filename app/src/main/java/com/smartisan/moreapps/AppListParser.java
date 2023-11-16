package com.smartisan.moreapps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import com.smartisan.moreapps.AppInfoList;
import com.smartisan.p043a.C0411a;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.smartisan.moreapps.b */
/* loaded from: classes.dex */
public final class AppListParser {

    /* renamed from: a */
    private Context f2637a;

    /* renamed from: b */
    private String f2638b;

    /* renamed from: c */
    private boolean f2639c;

    public AppListParser(Context context, String str, boolean z) {
        this.f2637a = context;
        this.f2638b = str;
        this.f2639c = z;
    }

    /* renamed from: a */
    public final ArrayList<AppInfoList.C0464a> m1997a() {
        int i;
        JSONArray jSONArray;
        String str;
        String str2;
        String[] split;
        int i2 = 0;
        if (this.f2637a == null || TextUtils.isEmpty(this.f2638b)) {
            return null;
        }
        String[] strArr = {"values", "values-ja", "values-ko", "values-zh-rCN", "values-zh-rTW"};
        ArrayList<AppInfoList.C0464a> arrayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("values");
        String locale = this.f2637a.getResources().getConfiguration().locale.toString();
        if (locale != null && (split = locale.split("_")) != null && split.length > 0) {
            if (split[0].equals("zh") && split.length >= 2) {
                if (split[1].equals("rTW") || split[1].equals("TW")) {
                    sb.append("-" + split[0]);
                    sb.append("-rTW");
                } else if (split[1].equals("rCN") || split[1].equals("CN")) {
                    sb.append("-" + split[0]);
                    sb.append("-rCN");
                }
            } else if (!split[0].equals("en")) {
                sb.append("-" + split[0]);
            }
        }
        String sb2 = sb.toString();
        int i3 = 0;
        while (true) {
            if (i3 >= 5) {
                i = -1;
                break;
            } else if (strArr[i3].equals(sb2)) {
                i = i3;
                break;
            } else {
                i3++;
            }
        }
        try {
            JSONObject jSONObject = new JSONObject(this.f2638b);
            if (jSONObject != JSONObject.NULL && (jSONArray = jSONObject.getJSONArray("apps")) != null && jSONArray.length() > 0) {
                while (true) {
                    int i4 = i2;
                    if (i4 >= jSONArray.length()) {
                        break;
                    }
                    JSONObject jSONObject2 = (JSONObject) jSONArray.opt(i4);
                    String string = jSONObject2.getString("package_name");
                    String string2 = jSONObject2.getString("update_info");
                    JSONArray jSONArray2 = jSONObject2.getJSONArray("app_name");
                    if (jSONArray2 == null || jSONArray2.length() <= 0) {
                        str = null;
                    } else {
                        str = (i < 0 || i >= jSONArray2.length()) ? null : ((JSONObject) jSONArray2.opt(i)).getString(strArr[i]);
                        if (TextUtils.isEmpty(str)) {
                            str = ((JSONObject) jSONArray2.opt(0)).getString(strArr[0]);
                        }
                    }
                    JSONArray jSONArray3 = jSONObject2.getJSONArray("app_desc");
                    if (jSONArray3 == null || jSONArray3.length() <= 0) {
                        str2 = null;
                    } else {
                        str2 = (i < 0 || i >= jSONArray3.length()) ? null : ((JSONObject) jSONArray3.opt(i)).getString(strArr[i]);
                        if (TextUtils.isEmpty(str2)) {
                            str2 = ((JSONObject) jSONArray3.opt(0)).getString(strArr[0]);
                        }
                    }
                    Bitmap m1995a = m1995a(jSONObject2.getString("icon_uri"), string);
                    if (m1995a == null) {
                        Log.e("AppListParser", "Fail to get bitmap");
                    }
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && m1995a != null) {
                        arrayList.add(new AppInfoList.C0464a(false, string, str, str2, string2, m1995a));
                    }
                    i2 = i4 + 1;
                }
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:104:0x00ce A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x00ef A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:120:0x00fe A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:129:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0046 A[Catch: IOException -> 0x0107, TRY_LEAVE, TryCatch #7 {IOException -> 0x0107, blocks: (B:21:0x0041, B:23:0x0046), top: B:113:0x0041 }] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00d3 A[Catch: IOException -> 0x00d8, TRY_LEAVE, TryCatch #0 {IOException -> 0x00d8, blocks: (B:55:0x00ce, B:57:0x00d3), top: B:104:0x00ce }] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00f4 A[Catch: IOException -> 0x010d, TRY_LEAVE, TryCatch #5 {IOException -> 0x010d, blocks: (B:69:0x00ef, B:71:0x00f4), top: B:110:0x00ef }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private Bitmap m1995a(String str, String str2) {
        InputStream inputStream;
        InputStream inputStream2;
        InputStream inputStream3;
        Bitmap bitmap;
        Exception e;
        Bitmap createScaledBitmap = null;
        InputStream inputStream4;
        FileOutputStream fileOutputStream;
        Throwable th;
        IOException e2;
        InputStream inputStream5 = null;
        try {
            if (this.f2639c) {
                inputStream = m1994b(str2);
            } else {
                inputStream = SmartisanAppUtils.m1965b(this.f2637a) ? m1996a(str) : null;
            }
            if (inputStream != null) {
                try {
                    try {
                        int integer = this.f2637a.getResources().getInteger(C0411a.C0414c.item_icon_size);
                        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                        if (decodeStream != null) {
                            createScaledBitmap = Bitmap.createScaledBitmap(decodeStream, integer, integer, true);
                            if (createScaledBitmap != decodeStream) {
                                try {
                                    decodeStream.recycle();
                                } catch (Exception e3) {
                                    inputStream3 = inputStream;
                                    e = e3;
                                    bitmap = createScaledBitmap;
                                    inputStream2 = null;
                                    try {
                                        e.printStackTrace();
                                        if (inputStream3 != null) {
                                            try {
                                                inputStream3.close();
                                            } catch (IOException e4) {
                                                e4.printStackTrace();
                                                return bitmap;
                                            }
                                        }
                                        if (inputStream2 != null) {
                                            inputStream2.close();
                                            return bitmap;
                                        }
                                        return bitmap;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        inputStream5 = inputStream2;
                                        inputStream = inputStream3;
                                        if (inputStream != null) {
                                        }
                                        if (inputStream5 != null) {
                                        }
                                        throw th;
                                    }
                                }
                            }
                            if (this.f2639c && SmartisanAppUtils.m1965b(this.f2637a)) {
                                inputStream4 = m1996a(str);
                                if (inputStream4 != null) {
                                    try {
                                        if (!TextUtils.isEmpty(str2)) {
                                            File filesDir = this.f2637a.getApplicationContext().getFilesDir();
                                            File file = new File(filesDir.toString());
                                            if (!file.exists()) {
                                                file.mkdirs();
                                            }
                                            File file2 = new File(filesDir.toString(), str2 + ".png");
                                            if (file2.exists()) {
                                                file2.delete();
                                            }
                                            try {
                                                file2.createNewFile();
                                                fileOutputStream = new FileOutputStream(file2);
                                            } catch (IOException e5) {
                                                fileOutputStream = null;
                                                e2 = e5;
                                            } catch (Throwable th3) {
                                                fileOutputStream = null;
                                                th = th3;
                                                if (fileOutputStream != null) {
                                                }
                                                throw th;
                                            }
                                            try {
                                                try {
                                                    byte[] bArr = new byte[8192];
                                                    while (true) {
                                                        int read = inputStream4.read(bArr, 0, 8192);
                                                        if (read != -1) {
                                                            fileOutputStream.write(bArr, 0, read);
                                                        } else {
                                                            break;
                                                        }
                                                    }
                                                    fileOutputStream.close();
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    if (fileOutputStream != null) {
                                                        try {
                                                            fileOutputStream.close();
                                                        } catch (IOException e7) {
                                                            e7.printStackTrace();
                                                        }
                                                    }
                                                    throw th;
                                                }
                                            } catch (IOException e8) {
                                                e2 = e8;
                                                e2.printStackTrace();
                                                if (fileOutputStream != null) {
                                                    try {
                                                        fileOutputStream.close();
                                                    } catch (IOException e9) {
                                                        e9.printStackTrace();
                                                    }
                                                }
                                                if (inputStream != null) {
                                                }
                                                if (inputStream4 != null) {
                                                }
                                                return createScaledBitmap;
                                            }
                                        }
                                    } catch (Exception e10) {
                                        e = e10;
                                        inputStream3 = inputStream;
                                        bitmap = createScaledBitmap;
                                        inputStream2 = inputStream4;
                                        e.printStackTrace();
                                        if (inputStream3 != null) {
                                        }
                                        if (inputStream2 != null) {
                                        }
                                    } catch (Throwable th5) {
                                        inputStream5 = inputStream4;
                                        th = th5;
                                        if (inputStream != null) {
                                        }
                                        if (inputStream5 != null) {
                                        }
                                        throw th;
                                    }
                                }
                            } else {
                                inputStream4 = null;
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e11) {
                                    e11.printStackTrace();
                                }
                            }
                            if (inputStream4 != null) {
                                inputStream4.close();
                            }
                            return createScaledBitmap;
                        }
                    } catch (Exception e12) {
                        inputStream2 = null;
                        inputStream3 = inputStream;
                        bitmap = null;
                        e = e12;
                        e.printStackTrace();
                        if (inputStream3 != null) {
                        }
                        if (inputStream2 != null) {
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e13) {
                            e13.printStackTrace();
                            throw th;
                        }
                    }
                    if (inputStream5 != null) {
                        inputStream5.close();
                    }
                    throw th;
                }
            }
            createScaledBitmap = null;
            if (this.f2639c) {
            }
            inputStream4 = null;
            if (inputStream != null) {
            }
            if (inputStream4 != null) {
            }
            return createScaledBitmap;
        } catch (Exception e14) {
            inputStream2 = null;
            inputStream3 = null;
            bitmap = null;
            e = e14;
        } catch (Throwable th7) {
            th = th7;
            inputStream = null;
        }
        return createScaledBitmap;
    }

    /* renamed from: a */
    private static InputStream m1996a(String str) {
        new HttpData();
        return HttpData.m1991b(str);
    }

    /* renamed from: b */
    private InputStream m1994b(String str) {
        FileInputStream fileInputStream = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(this.f2637a.getApplicationContext().getFilesDir().toString(), str + ".png");
        if (file.exists()) {
            try {
                fileInputStream = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return fileInputStream;
        }
        fileInputStream = null;
        return fileInputStream;
    }
}
