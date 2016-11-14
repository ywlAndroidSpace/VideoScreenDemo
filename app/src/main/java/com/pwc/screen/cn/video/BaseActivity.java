package com.pwc.screen.cn.video;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

/**
 * Created by Popturn on 2016/7/25.
 */
public class BaseActivity extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }



    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }


    // Global view click listener
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onUserInteraction(view);
        }
    };


    public View.OnClickListener getViewClickListener(){
        return onClickListener;
    }

    /**
     * Central point of handling all view click events
     * @param view
     */
    public void onUserInteraction(View view){

    }

    public void log(Object obj) {

        // You can use filter *** to filter out message

        Log.e(getClass().getName(), String.format("*** %s ***",
                obj == null ? "--!--"
                        : obj.toString()));
    }
}
