
package com.pwc.screen.cn.dialog;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.pwc.screen.cn.R;
import com.pwc.screen.cn.video.MeetingActivity;


/**
 * 半屏显示
 *
 * @author likebamboo
 */
public class OverlayView extends Overlay {

    /**
     * 网络操作结果
     */
    public static final int MSG_OK = 0x1000;

    /**
     * 网络操作结果
     */
    public static final int MSG_FAILED = 0x1001;

    /**
     * 来电号码extra
     */
    public static final String EXTRA_PHONE_NUM = "phoneNum";

    private static Context mContext = null;


    private static View overlay_dealwith_layout;


    /**
     * 挂电话按钮
     */
    private static Button mEndCallBt = null;

    /**
     * 接听电话按钮
     */
    private static Button mAnswerCallBt = null;


    @SuppressLint("HandlerLeak")
    private static Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            // super.handleMessage(msg);
            switch (msg.what) {
                case MSG_FAILED:
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 显示
     *
     * @param context 上下文对象
     * @param number
     */
    public static void show(final Context context, final String number, final int percentScreen) {
        synchronized (monitor) {
            mContext = context;

            init(context, number, R.layout.call_over_layout, percentScreen);
            InputMethodManager imm = (InputMethodManager) context
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                    InputMethodManager.HIDE_IMPLICIT_ONLY);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

        }
    }


    /**
     * 初始化布局
     *
     * @param context 上下文对象
     * @param number  电话号码
     * @param layout  布局文件
     * @return 布局
     */
    private static ViewGroup init(Context context, String number, int layout, int percentScreen) {
        WindowManager.LayoutParams params = getShowingParams();
        int height = getHeight(context, percentScreen);
        params.height = height;
        ViewGroup overlay = init(context, layout, params);

        initView(overlay, number, percentScreen);

        return overlay;
    }

    /**
     * 初始化界面
     */
    private static void initView(View v, String phoneNum, int percentScreen) {

        if (percentScreen == 100) {
            // 接听电话与挂断电话
            mEndCallBt = (Button) v.findViewById(R.id.overlay_end_call_bt);
            mAnswerCallBt = (Button) v.findViewById(R.id.overlay_answer_call_bt);
            overlay_dealwith_layout = v.findViewById(R.id.overlay_dealwith_layout);

            addListener();
        }
    }

    /**
     * 添加监听器
     */
    private static void addListener() {
        mEndCallBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                hide(mContext);
            }
        });
        mAnswerCallBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (Utils.hasGingerbread()) {
                    overlay_dealwith_layout.setVisibility(View.GONE);
                    startMeeting();
                } else {
                    overlay_dealwith_layout.setVisibility(View.GONE);
                    startMeeting();
                }
            }
        });
    }

    private static void startMeeting() {
        Intent intent = new Intent(mContext, MeetingActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(MeetingActivity.EXTRA_CHANNEL_ID, "123");
        intent.putExtra(MeetingActivity.EXTRA_VENDOR_KEY, mContext.getString(R.string.vendor_key));
        mContext.startActivity(intent);
        hide(mContext);
    }


    /**
     * 获取显示参数
     *
     * @return
     */
    private static WindowManager.LayoutParams getShowingParams() {
        WindowManager.LayoutParams params = new WindowManager.LayoutParams();
        params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        // TYPE_TOAST TYPE_SYSTEM_OVERLAY 在其他应用上层 在通知栏下层 位置不能动鸟
        // TYPE_PHONE 在其他应用上层 在通知栏下层
        // TYPE_PRIORITY_PHONE TYPE_SYSTEM_ALERT 在其他应用上层 在通知栏上层 没试出来区别是啥
        // TYPE_SYSTEM_ERROR 最顶层(通过对比360和天天动听歌词得出)
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.x = 0;
        params.y = 0;
        params.format = PixelFormat.RGBA_8888;// value = 1
        params.gravity = Gravity.TOP;
        params.flags = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD;
        params.screenOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        return params;
    }

    /**
     * 获取界面显示的高度 ，默认为手机高度的2/3
     *
     * @param context 上下文对象
     * @return
     */
    private static int getHeight(Context context, int percentScreen) {
        return getLarger(context) * percentScreen / 100;
    }

    @SuppressWarnings("deprecation")
    private static int getLarger(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        int height = 0;
        if (Utils.hasHoneycombMR2()) {
            height = getLarger(display);
        } else {
            height = display.getHeight() > display.getWidth() ? display.getHeight() : display
                    .getWidth();
        }
        System.out.println("getLarger: " + height);
        return height;
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private static int getLarger(Display display) {
        Point size = new Point();
        display.getSize(size);
        return size.y > size.x ? size.y : size.x;
    }

    /**
     * 隐藏
     *
     * @param context
     */
    public static void hide(Context context) {
        synchronized (monitor) {
            if (mOverlay != null) {
                try {
                    WindowManager wm = (WindowManager) context
                            .getSystemService(Context.WINDOW_SERVICE);
                    // Remove view from WindowManager
                    wm.removeView(mOverlay);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                mOverlay = null;
            }
        }
    }

}
