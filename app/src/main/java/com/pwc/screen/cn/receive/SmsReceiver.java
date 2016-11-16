package com.pwc.screen.cn.receive;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.pwc.screen.cn.dialog.OverlayView;

/**
 * Created by Vergil Yang on 11/11/2016.
 */

public class SmsReceiver extends BroadcastReceiver {


    private Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.mContext = context;
        SmsMessage[] msg= null;
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            //StringBuilder buf = new StringBuilder();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                msg = new SmsMessage[pdusObj.length];
                for (int i = 0; i < pdusObj.length; i++)
                    msg[i] = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
            }
            for (int i = 0; i < msg.length; i++) {
                String msgTxt = msg[i].getMessageBody();
                if (msgTxt.equals("Testing!")) {
                    Toast.makeText(context, "success!", Toast.LENGTH_LONG).show();
                    return;
                } else {
//                    Toast.makeText(context, msgTxt, Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            showWindow(mContext, "who are you?", 100);
                        }
                    }, 100);
                    return;
                }
            }
        }
    }
    /**
     * 显示来电弹窗
     *
     * @param ctx 上下文对象
     * @param number 电话号码
     */
    private void showWindow(Context ctx, String number, int percentScreen) {

        OverlayView.show(ctx, number, percentScreen);
    }
}
