package com.pwc.screen.cn.video;

import android.app.Application;

import com.xsj.crasheye.Crasheye;

import io.agora.rtc.RtcEngine;

/**
 * Created by Popturn on 2016/7/25.
 */
public class AgoraApplication extends Application {
    private RtcEngine rtcEngine;
    private MessageHandler messageHandler;

    @Override
    public void onCreate(){

        super.onCreate();
        Crasheye.initWithNativeHandle(this, "06798b00");

        messageHandler = new MessageHandler();
    }

    /**
     * Test vendor key:  6D7A26A1D3554A54A9F43BE6797FE3E2
     * @param vendorKey
     */
    public void setRtcEngine(String vendorKey){

        if(rtcEngine==null) {
            rtcEngine = RtcEngine.create(getApplicationContext(), vendorKey, messageHandler);
        }
    }

    public RtcEngine getRtcEngine(){

        return rtcEngine;
    }

    public void setEngineEventHandlerActivity(BaseEngineEventHandlerActivity engineEventHandlerActivity){
        messageHandler.setActivity(engineEventHandlerActivity);
    }
}
