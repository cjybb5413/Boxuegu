package cn.edu.gdmec.android.boxuegu.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import cn.edu.gdmec.android.boxuegu.R;

public class VideoPlayActivity extends Activity  {

    private VideoView videoView;
    private MediaController controller;
    private String videoPath;
    private int position;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        videoPath = getIntent().getStringExtra("videoPath");
        position=getIntent().getIntExtra("position",0);
        videoView=(VideoView)findViewById(R.id.videoView);
        controller=new MediaController(this);
        videoView.setMediaController(controller);
        play();
    }
    private void play(){
        if (TextUtils.isEmpty(videoPath)){
            Toast.makeText(this,"本地没有此视频，暂时无法播放",Toast.LENGTH_SHORT).show();
            return;
        }
        /*String uri = "android.resource://" + getPackageName() + "/" + R.raw.video11;*/
        videoView.setVideoPath(videoPath);
        videoView.start();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Intent data=new Intent();
        data.putExtra("position",position);
        setResult(RESULT_OK,data);
        return super.onKeyDown(keyCode, event);
    }
}
