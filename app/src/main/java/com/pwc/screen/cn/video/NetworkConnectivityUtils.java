package com.pwc.screen.cn.video;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Popturn on 2016/7/25.
 */
public class NetworkConnectivityUtils {
    /**
     * Log
     */
    private static final String TAG = NetworkConnectivityUtils.class.getSimpleName ();

    public static boolean isConnectedToMobile (Context context) {

        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        // mobile
        NetworkInfo.State mobile = conMan.getNetworkInfo (0).getState ();

        return mobile == NetworkInfo.State.CONNECTED;

    }

    public static boolean isConnectedToWifi (Context context) {

        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        // wifi
        NetworkInfo.State wifi = conMan.getNetworkInfo (1).getState ();


        return wifi == NetworkInfo.State.CONNECTED;
    }

    /**
     * This is a simple way to check if you are CONNECTED or is CONNECTING to
     * network. NOTE: you need to set <uses-permission
     * android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
     * in your AndroidManifest.xml
     *
     * @param context a context used to getSystemInfo
     * @return
     */
    public static boolean isConnectedToNetwork (Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService (Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo ();
        boolean isConnected = netInfo != null && netInfo.isConnected ();

        return isConnected;
    }

    private NetworkConnectivityUtils() {

    }
}
