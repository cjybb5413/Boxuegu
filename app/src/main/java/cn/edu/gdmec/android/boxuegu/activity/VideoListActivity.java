package cn.edu.gdmec.android.boxuegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.VideoListItemAdapter;
import cn.edu.gdmec.android.boxuegu.bean.VideoBean;

public class VideoListActivity extends Activity {
    private List<VideoBean> videoList;
    private int chapterId;
    private String intro;
    private VideoListItemAdapter adapter;
    private TextView tv_intro;
    private TextView tv_video;
    private ListView lv_video_list;
    private TextView tv_chapter_intro;
    private ScrollView sv_chapter_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        chapterId = getIntent().getIntExtra("id",0);
        intro = getIntent().getStringExtra("intro");
        initData();
        initView();
    }
    private String read(InputStream is){
        BufferedReader reader=null;
        StringBuilder sb=null;
        String line=null;
        try {
            sb=new StringBuilder();
            reader=new BufferedReader(new InputStreamReader(is));

            while ((line = reader.readLine()) != null){
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }finally {
            if (is != null){
                try {
                    is.close();
                    if (reader != null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }
    private void initData(){
        JSONArray jsonArray;
        try {
            InputStream is=getResources().getAssets().open("data.json");
            jsonArray=new JSONArray(read(is));
            videoList=new ArrayList<VideoBean>();
            for (int i=0;i<jsonArray.length();i++){
                VideoBean bean=new VideoBean();
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                if (jsonObject.getInt("chapterId") == chapterId){
                    bean.chapterId = jsonObject.getInt("chapterId");
                    bean.videoId = Integer.parseInt(jsonObject.getString("videoId"));
                    bean.title=jsonObject.getString("title");
                    bean.secondTitle=jsonObject.getString("secondTitle");
                    bean.videoPath=jsonObject.getString("videoPath");
                    videoList.add(bean);
                }
                bean = null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        tv_intro = (TextView) findViewById(R.id.tv_intro);
        tv_video = (TextView) findViewById(R.id.tv_video);
        lv_video_list = (ListView) findViewById(R.id.lv_video_list);
        tv_chapter_intro = (TextView) findViewById(R.id.tv_chapter_intro);
        sv_chapter_intro = (ScrollView) findViewById(R.id.sv_chapter_intro);
        adapter=new VideoListItemAdapter(this);
        lv_video_list.setAdapter(adapter);
        adapter.setData(videoList);
        tv_chapter_intro.setText(intro);
        tv_intro.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_video.setBackgroundColor(Color.parseColor("#FFFFFF"));
        tv_intro.setTextColor(Color.parseColor("#FFFFFF"));
        tv_video.setTextColor(Color.parseColor("#000000"));
        tv_intro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                lv_video_list.setVisibility(View.GONE);
                sv_chapter_intro.setVisibility(View.VISIBLE);
                tv_intro.setBackgroundColor(Color.parseColor("#30B4FF"));
                tv_video.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tv_intro.setTextColor(Color.parseColor("#FFFFFF"));
                tv_video.setTextColor(Color.parseColor("#000000"));
            }
        });
        tv_video.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                lv_video_list.setVisibility(View.VISIBLE);
                sv_chapter_intro.setVisibility(View.GONE);
                tv_intro.setBackgroundColor(Color.parseColor("#FFFFFF"));
                tv_video.setBackgroundColor(Color.parseColor("#30B4FF"));
                tv_intro.setTextColor(Color.parseColor("#000000"));
                tv_video.setTextColor(Color.parseColor("#FFFFFF"));
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null){
            int position=data.getIntExtra("position",0);
            adapter.setSelectedPosition(position);
            lv_video_list.setVisibility(View.VISIBLE);
            sv_chapter_intro.setVisibility(View.GONE);
            tv_intro.setBackgroundColor(Color.parseColor("#FFFFFF"));
            tv_video.setBackgroundColor(Color.parseColor("#30B4FF"));
            tv_intro.setTextColor(Color.parseColor("#000000"));
            tv_video.setTextColor(Color.parseColor("#FFFFFF"));
        }
    }
}
