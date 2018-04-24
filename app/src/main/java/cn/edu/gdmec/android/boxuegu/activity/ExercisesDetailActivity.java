package cn.edu.gdmec.android.boxuegu.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.adapter.ExercisesDetailListItemAdapter;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class ExercisesDetailActivity extends Activity {
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private RecyclerView rv_list;
    private int id;
    private String title;
    private TextView tv_dibu;
    private List<ExercisesBean> eb1;
    private ExercisesDetailListItemAdapter adapter;
    private int total=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);
        id=getIntent().getIntExtra("id",0);
        title = getIntent().getStringExtra("title");
        eb1=new ArrayList<ExercisesBean>();
        initData();
        initView();


    }
    private void initData(){
        try {
            InputStream is=getResources().getAssets().open("chapter" + id + ".xml");
            eb1 = AnalysisUtils.getExercisesInfos(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_dibu=findViewById(R.id.tv_dibu);
        tv_main_title.setText(title);
        rv_list =  findViewById(R.id.rv_list);
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExercisesDetailActivity.this.finish();
            }
        });
       adapter = new ExercisesDetailListItemAdapter(ExercisesDetailActivity.this, new ExercisesDetailListItemAdapter.OnSelectListener() {
           @Override
           public void onSelectA(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
               if (eb1.get(position).answer !=1){
                   eb1.get(position).select=1;
               }else {
                   eb1.get(position).select=0;
               }
               switch (eb1.get(position).answer){
                   case 1:
                       iv_a.setImageResource(R.drawable.exercises_right_icon);
                       break;
                   case 2:
                       iv_a.setImageResource(R.drawable.exercises_error_icon);
                       iv_b.setImageResource(R.drawable.exercises_right_icon);
                       break;
                   case 3:
                       iv_a.setImageResource(R.drawable.exercises_error_icon);
                       iv_c.setImageResource(R.drawable.exercises_right_icon);
                       break;
                   case 4:
                       iv_a.setImageResource(R.drawable.exercises_error_icon);
                       iv_d.setImageResource(R.drawable.exercises_right_icon);
                       break;
               }
               AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
           }

           @Override
           public void onSelectB(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
               if (eb1.get(position).answer !=2){
                   eb1.get(position).select=2;
               }else {
                   eb1.get(position).select=0;
               }
               switch (eb1.get(position).answer){
                   case 1:
                       iv_a.setImageResource(R.drawable.exercises_right_icon);
                       iv_b.setImageResource(R.drawable.exercises_error_icon);
                       break;
                   case 2:
                       iv_b.setImageResource(R.drawable.exercises_right_icon);
                       break;
                   case 3:
                       iv_b.setImageResource(R.drawable.exercises_error_icon);
                       iv_c.setImageResource(R.drawable.exercises_right_icon);
                       break;
                   case 4:
                       iv_b.setImageResource(R.drawable.exercises_error_icon);
                       iv_d.setImageResource(R.drawable.exercises_right_icon);
                       break;
               }
               AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
           }

           @Override
           public void onSelectC(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
               if (eb1.get(position).answer !=3){
                   eb1.get(position).select=3;
               }else {
                   eb1.get(position).select=0;
               }
               switch (eb1.get(position).answer){
                   case 1:
                       iv_a.setImageResource(R.drawable.exercises_right_icon);
                       iv_c.setImageResource(R.drawable.exercises_error_icon);
                       break;
                   case 2:
                       iv_c.setImageResource(R.drawable.exercises_error_icon);
                       iv_b.setImageResource(R.drawable.exercises_right_icon);
                       break;
                   case 3:
                       iv_c.setImageResource(R.drawable.exercises_right_icon);
                       break;
                   case 4:
                       iv_c.setImageResource(R.drawable.exercises_error_icon);
                       iv_d.setImageResource(R.drawable.exercises_right_icon);
                       break;
               }
               AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
           }

           @Override
           public void onSelectD(int position, ImageView iv_a, ImageView iv_b, ImageView iv_c, ImageView iv_d) {
               if (eb1.get(position).answer !=4){
                   eb1.get(position).select=4;
               }else {
                   eb1.get(position).select=0;
               }
               switch (eb1.get(position).answer){
                   case 1:
                       iv_a.setImageResource(R.drawable.exercises_right_icon);
                       iv_d.setImageResource(R.drawable.exercises_error_icon);
                       break;
                   case 2:
                       iv_d.setImageResource(R.drawable.exercises_error_icon);
                       iv_b.setImageResource(R.drawable.exercises_right_icon);
                       break;
                   case 3:
                       iv_d.setImageResource(R.drawable.exercises_error_icon);
                       iv_c.setImageResource(R.drawable.exercises_right_icon);
                       break;
                   case 4:
                       iv_d.setImageResource(R.drawable.exercises_right_icon);
                       break;
               }
               AnalysisUtils.setABCDEnable(false,iv_a,iv_b,iv_c,iv_d);
           }
       });

       adapter.setData(eb1);
       rv_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
       adapter.setItemListener(new ExercisesDetailListItemAdapter.MyItemOnClicklistener() {
           @Override
           public void setItem(View view, int position) {
               total++;
               tv_dibu.setText("第"+(position+1)+"题完成，共"+adapter.getItemCount()+"题");
               if (total==5){
                   AnalysisUtils.finishItem(ExercisesDetailActivity.this,id);
                   setResult(RESULT_OK);
               }
           }
       });
       rv_list.setAdapter(adapter);
    }
}
