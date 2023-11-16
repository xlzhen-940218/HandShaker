package com.smartisanos.smartfolder.aoa.view;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.smartisanos.smartfolder.aoa.R;
import org.apache.http.protocol.HTTP;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ShareView.java */
/* renamed from: com.smartisanos.smartfolder.aoa.view.h */
/* loaded from: classes.dex */
public final class View$OnClickListenerC0838h implements View.OnClickListener {

    /* renamed from: a */
    final /* synthetic */ ShareView f3986a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public View$OnClickListenerC0838h(ShareView shareView) {
        this.f3986a = shareView;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Activity activity;
        Activity activity2;
        ComponentName[] componentNameArr;
        ComponentName[] componentNameArr2;
        ComponentName[] componentNameArr3;
        ComponentName[] componentNameArr4;
        ComponentName[] componentNameArr5;
        ComponentName[] componentNameArr6;
        Uri m190b = this.f3986a.m190b();
        activity = this.f3986a.f3976f;
        String string = activity.getString(R.string.share_msg);
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/*");
        intent.putExtra("android.intent.extra.TEXT", string);
        intent.putExtra("android.intent.extra.TITLE", string);
        switch (view.getId()) {
            case R.id.share_weibo /* 2131624139 */:
                intent.putExtra("android.intent.extra.STREAM", m190b);
                componentNameArr6 = this.f3986a.f3982l;
                intent.setComponent(componentNameArr6[0]);
                break;
            case R.id.share_weixin /* 2131624140 */:
                intent.setType(HTTP.PLAIN_TEXT_TYPE);
                componentNameArr5 = this.f3986a.f3982l;
                intent.setComponent(componentNameArr5[1]);
                break;
            case R.id.share_twitter /* 2131624141 */:
                intent.putExtra("android.intent.extra.STREAM", m190b);
                componentNameArr4 = this.f3986a.f3982l;
                intent.setComponent(componentNameArr4[2]);
                break;
            case R.id.share_qzone /* 2131624142 */:
                intent.putExtra("android.intent.extra.STREAM", m190b);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                componentNameArr3 = this.f3986a.f3982l;
                intent.setComponent(componentNameArr3[3]);
                break;
            case R.id.share_weixin_timeline /* 2131624143 */:
                intent.putExtra("android.intent.extra.STREAM", m190b);
                componentNameArr2 = this.f3986a.f3982l;
                intent.setComponent(componentNameArr2[4]);
                break;
            case R.id.share_facebook /* 2131624144 */:
                intent.putExtra("android.intent.extra.STREAM", m190b);
                componentNameArr = this.f3986a.f3982l;
                intent.setComponent(componentNameArr[5]);
                break;
        }
        try {
            this.f3986a.dismiss();
            activity2 = this.f3986a.f3976f;
            activity2.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
