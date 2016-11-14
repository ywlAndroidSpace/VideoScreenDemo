package com.pwc.screen.cn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pwc.screen.cn.video.MeetingActivity;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
    }

    private void startVideo() {
        Intent intent = new Intent(this, MeetingActivity.class);
        intent.putExtra(MeetingActivity.EXTRA_CHANNEL_ID, "123");
        intent.putExtra(MeetingActivity.EXTRA_VENDOR_KEY, getString(R.string.vendor_key));
        startActivity(intent);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnStart) {
            startVideo();
        }
    }
}
