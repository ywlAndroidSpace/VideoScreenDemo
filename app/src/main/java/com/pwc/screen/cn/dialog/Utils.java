
package com.pwc.screen.cn.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.pwc.screen.cn.R;
import com.pwc.screen.cn.video.MeetingActivity;


public class Utils {


    /**
     * >= android 2.3 版本
     * 
     * @return
     */
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD;
    }

    /**
     * >= android 3.0 版本
     * 
     * @return
     */
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB;
    }

    public static boolean hasHoneycombMR2() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2;
    }


    /**
     * 字符串转Html
     * 
     * @param text 字符串
     * @return 格式化的html。
     */
    public static Spanned formatHtml(String text) {
        return Html.fromHtml(text);
    }

    /**
     * 关闭输入法
     */
    public static void closeEditer(Activity context) {
        View view = context.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager)context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 收起输入法
     *
     * @param ctx
     * @param view
     */
    public static void HideKeyboard(Context ctx, View view) {
        if (null == view)
            return;
        InputMethodManager imm = (InputMethodManager)ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 挂断电话
     */
    public static synchronized void endCall(Context ctx) {
        ((Activity)ctx).finish();
    }

    /**
     * 接听电话
     */
    public static synchronized void answerRingingCall(Context ctx) {
        Intent intent = new Intent(ctx, MeetingActivity.class);
        intent.putExtra(MeetingActivity.EXTRA_CHANNEL_ID, "123");
        intent.putExtra(MeetingActivity.EXTRA_VENDOR_KEY, ctx.getString(R.string.vendor_key));
        ctx.startActivity(intent);
    }

    /**
     * 接听电话
     */
    public static synchronized void answerCall(Context ctx) {
        Intent intent = new Intent(ctx, MeetingActivity.class);
        intent.putExtra(MeetingActivity.EXTRA_CHANNEL_ID, "123");
        intent.putExtra(MeetingActivity.EXTRA_VENDOR_KEY, ctx.getString(R.string.vendor_key));
        ctx.startActivity(intent);
    }
}
