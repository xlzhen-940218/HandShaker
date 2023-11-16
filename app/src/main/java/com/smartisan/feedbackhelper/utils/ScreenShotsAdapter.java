package com.smartisan.feedbackhelper.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartisan.feedbackhelper.FeedbackActivity;
import com.smartisanos.smartfolder.aoa.R;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.smartisan.feedbackhelper.utils.n */
/* loaded from: classes.dex */
public final class ScreenShotsAdapter extends BaseAdapter {

    /* renamed from: b */
    TextView f2603b;

    /* renamed from: f */
    public Context context;

    /* renamed from: g */
    public LinkedList<String> f2608g = new LinkedList<>();

    /* renamed from: a */
    public Map<String, SoftReference<Bitmap>> f2602a = new ConcurrentHashMap();

    /* renamed from: d */
    View.OnClickListener f2605d = new View$OnClickListenerC0459o(this);

    /* renamed from: e */
    View.OnClickListener f2606e = new View$OnClickListenerC0460p(this);

    /* renamed from: c */
    Handler f2604c = new Handler();

    public ScreenShotsAdapter(Context context) {
        this.context = context;
    }

    /* renamed from: a */
    public final void m2035a() {
        this.f2608g.clear();
        notifyDataSetChanged();
    }

    /* renamed from: a */
    public final void m2031a(String str) {
        this.f2608g.addLast(str);
    }

    /* renamed from: a */
    public final void m2033a(int i, String str) {
        this.f2608g.set(i, str);
    }

    /* renamed from: a */
    public final String m2034a(int i) {
        if (i < this.f2608g.size()) {
            return this.f2608g.get(i);
        }
        return null;
    }

    /* renamed from: b */
    public final boolean m2027b(String str) {
        return this.f2608g.contains(str);
    }

    /* renamed from: b */
    public final LinkedList<String> m2029b() {
        return this.f2608g;
    }

    /* renamed from: a */
    public final void m2030a(ArrayList<String> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < arrayList.size()) {
                    m2031a(arrayList.get(i2));
                    i = i2 + 1;
                } else {
                    return;
                }
            }
        }
    }

    @Override // android.widget.Adapter
    public final int getCount() {
        return this.f2608g.size() >= 5 ? this.f2608g.size() : this.f2608g.size() + 1;
    }

    @Override // android.widget.Adapter
    public final Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public final long getItemId(int i) {
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006f  */
    @Override // android.widget.Adapter
    @SuppressLint({"InflateParams"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final View getView(int i, View view, ViewGroup viewGroup) {
        Bitmap bitmap;
        LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.f2603b = (TextView) ((FeedbackActivity) this.context).findViewById(R.id.bug2go_addpic);
        if (view == null) {
            view = layoutInflater.inflate(R.layout.grid_view_item, (ViewGroup) null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.grid_del);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.grid_item);
        if (m2026c()) {
            this.f2603b.setVisibility(View.VISIBLE);
        } else {
            this.f2603b.setVisibility(View.GONE);
        }
        if (this.f2608g.size() - 1 >= i) {
            String str = this.f2608g.get(i);
            if (this.f2602a.containsKey(str)) {
                SoftReference<Bitmap> softReference = this.f2602a.get(str);
                if (softReference.get() != null) {
                    bitmap = softReference.get();
                    if (bitmap == null) {
                        bitmap = m2023e(str);
                        this.f2602a.put(str, new SoftReference<>(bitmap));
                    }
                    imageView2.setImageBitmap(bitmap);
                    imageView2.setTag(Integer.valueOf(i));
                    imageView2.setOnClickListener(this.f2606e);
                    imageView.setVisibility(View.VISIBLE);
                    imageView.setTag(Integer.valueOf(i));
                    imageView.setOnClickListener(this.f2605d);
                }
            }
            bitmap = null;
            if (bitmap == null) {
            }
            imageView2.setImageBitmap(bitmap);
            imageView2.setTag(Integer.valueOf(i));
            imageView2.setOnClickListener(this.f2606e);
            imageView.setVisibility(View.VISIBLE);
            imageView.setTag(Integer.valueOf(i));
            imageView.setOnClickListener(this.f2605d);
        } else {
            imageView.setVisibility(View.GONE);
            imageView2.setImageResource(R.drawable.report_fragment_add);
            imageView2.setTag(Integer.valueOf(i));
            imageView2.setOnClickListener(this.f2606e);
        }
        return view;
    }

    /* renamed from: c */
    public static boolean m2025c(String str) {
        return new File(str).exists();
    }

    /* renamed from: d */
    public static boolean m2024d(String str) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return (options.outWidth > options.outHeight ? options.outHeight : options.outWidth) > 0;
    }

    /* renamed from: e */
    private static Bitmap m2023e(String str) {
        ExifInterface exifInterface;
        int i = 0;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        options.inSampleSize = C0462r.m2020a(options);
        options.inJustDecodeBounds = false;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        int i2 = options.outWidth > options.outHeight ? options.outHeight : options.outWidth;
        int i3 = (options.outWidth - options.outHeight) / 2;
        int i4 = (options.outHeight - options.outWidth) / 2;
        if (i3 < 0) {
            i3 = 0;
        }
        if (i4 < 0) {
            i4 = 0;
        }
        Matrix matrix = new Matrix();
        float f = 120.0f / i2;
        matrix.setScale(f, f);
        try {
            exifInterface = new ExifInterface(str);
        } catch (IOException e) {
            e.printStackTrace();
            exifInterface = null;
        }
        if (exifInterface != null) {
            switch (exifInterface.getAttributeInt("Orientation", 0)) {
                case 3:
                    i = 180;
                    break;
                case 6:
                    i = 90;
                    break;
                case 8:
                    i = 270;
                    break;
            }
        }
        if (i != 0) {
            matrix.postRotate(i);
        }
        return Bitmap.createBitmap(decodeFile, i3, i4, i2, i2, matrix, true);
    }

    /* renamed from: c */
    public final boolean m2026c() {
        return this.f2608g == null || this.f2608g.size() <= 0;
    }
}
