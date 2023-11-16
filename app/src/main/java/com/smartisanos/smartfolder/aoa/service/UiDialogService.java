package com.smartisanos.smartfolder.aoa.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import com.smartisanos.smartfolder.aoa.FolderApp;
import com.smartisanos.smartfolder.aoa.R;
import com.smartisanos.smartfolder.aoa.p056h.HandShaker;
import com.smartisanos.smartfolder.aoa.p056h.Tracker;

/* loaded from: classes.dex */
public class UiDialogService extends Service {

    /* renamed from: a */
    private static HandlerC0813a f3886a;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        HandShaker.info("UiDialogService", "onCreate");
        f3886a = new HandlerC0813a(Looper.getMainLooper());
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        HandShaker.info("UiDialogService", "onStartCommand");
        HandShaker.info("UiDialogService", "intent>>>>>>" + intent.getAction());
        if (intent != null && "ACTION_ALERT_SECURITY_SYSTEM_UPDATE".equals(intent.getAction())) {
            Tracker.m523a("A308000");
            Message message = new Message();
            message.what = 1;
            f3886a.sendMessage(message);
            return 2;
        }
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.smartisanos.smartfolder.aoa.service.UiDialogService$a */
    /* loaded from: classes.dex */
    public final class HandlerC0813a extends Handler {
        public HandlerC0813a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    AlertDialog create = new AlertDialog.Builder(FolderApp.getInstance(), 5).setTitle(R.string.system_update).setMessage(R.string.system_update_tip1).setPositiveButton(R.string.download_now, new DialogInterface$OnClickListenerC0825k(this)).setNegativeButton(R.string.later, new DialogInterface$OnClickListenerC0824j(this)).create();
                    create.getWindow().setType(2003);
                    create.show();
                    return;
                default:
                    return;
            }
        }
    }
}
