package com.pwc.screen.cn;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;

import com.pwc.screen.cn.dialog.OverlayView;
import com.pwc.screen.cn.dialog.ShowPref;

public class MainActivity extends Activity {
    /**
     * 配置信息
     */
    private ShowPref pref = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setDialog();
    }

    private void setDialog() {
        pref = ShowPref.getInstance(this);
        pref.putInt(ShowPref.SHOW_TYPE, ShowPref.TYPE_FULL_DIALOG);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                showWindow(MainActivity.this, "who are you?", 100);
            }
        }, 100);
        return;
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
