package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.fragment.CourseFragment;
import cn.edu.gdmec.android.boxuegu.fragment.ExercisesFragment;
import cn.edu.gdmec.android.boxuegu.fragment.MyinfoFragment;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;


/**
 * Created by apple on 18/3/20.
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private FrameLayout mBodyLayout;
    public LinearLayout mBottomLayout;
    private View mCourseBtn;
    private View mExercisesBtn;
    private View mMyInfoBtn;
    private TextView tv_course;
    private TextView tv_exercises;
    private TextView tv_myInfo;
    private ImageView iv_course;
    private ImageView iv_exercises;
    private ImageView iv_myInfo;
    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout rl_title_bar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.main_body,new CourseFragment()).commit();*/
        init();
        initBottomBar();
        /*setListener();*/
       /* setInitStatus();*/
        setMain();
    }
    private void setMain(){
        this.getSupportFragmentManager().beginTransaction().add(R.id.main_body,new MyinfoFragment()).commit();
        setSelectedStatus(2);
    }
    private void init() {
        tv_back = findViewById(R.id.tv_back);
        tv_main_title = findViewById(R.id.tv_main_title);
        tv_main_title.setText("博学谷课程");
        rl_title_bar = findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tv_back.setVisibility(View.GONE);
       /* initBodyLayout();*/
    }

    private void initBottomBar() {
        mBottomLayout = findViewById(R.id.main_bottom_bar);
        mCourseBtn = findViewById(R.id.bottom_bar_course_btn);
        mExercisesBtn = findViewById(R.id.bottom_bar_exercises_btn);
        mMyInfoBtn = findViewById(R.id.bottom_bar_myinfo_btn);
        tv_course = findViewById(R.id.bottom_bar_text_course);
        tv_exercises = findViewById(R.id.bottom_bar_text_exercises);
        tv_myInfo = findViewById(R.id.bottom_bar_text_myinfo);
        iv_course = findViewById(R.id.bottom_bar_image_course);
        iv_exercises = findViewById(R.id.bottom_bar_image_exercises);
        iv_myInfo = findViewById(R.id.bottom_bar_image_myinfo);

        mCourseBtn.setOnClickListener(this);
        mExercisesBtn.setOnClickListener(this);
        mMyInfoBtn.setOnClickListener(this);

    }

    /*private void initBodyLayout() {
        mBodyLayout = findViewById(R.id.main_body);
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_bar_course_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new CourseFragment()).commit();
                setSelectedStatus(0);
                break;
            case R.id.bottom_bar_exercises_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new ExercisesFragment()).commit();
                setSelectedStatus(1);
                break;
            case R.id.bottom_bar_myinfo_btn:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new MyinfoFragment()).commit();
                setSelectedStatus(2);
                break;
            default:
                break;
        }
    }

    /*private void setListener() {
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setOnClickListener(this);
        }
    }*/

   /* private void clearBottomImageState() {
        tv_course.setTextColor(Color.parseColor("#666666"));
        tv_exercises.setTextColor(Color.parseColor("#666666"));
        tv_myInfo.setTextColor(Color.parseColor("#666666"));
        iv_course.setImageResource(R.drawable.main_course_icon);
        iv_exercises.setImageResource(R.drawable.main_exercises_icon);
        iv_myInfo.setImageResource(R.drawable.main_my_icon);
        for (int i = 0; i < mBottomLayout.getChildCount(); i++) {
            mBottomLayout.getChildAt(i).setSelected(false);
        }
    }*/

    private void setSelectedStatus(int index) {
        switch (index) {
            case 0:
                mCourseBtn.setSelected(true);
                iv_course.setImageResource(R.drawable.main_course_icon_selected);
                tv_course.setTextColor(Color.parseColor("#0097F7"));
                tv_exercises.setTextColor(Color.parseColor("#666666"));
                tv_myInfo.setTextColor(Color.parseColor("#666666"));
                iv_exercises.setImageResource(R.drawable.main_exercises_icon);
                iv_myInfo.setImageResource(R.drawable.main_my_icon);
                tv_main_title.setText("博学谷课程");
                tv_main_title.setVisibility(View.VISIBLE);
                break;
            case 1:
                mExercisesBtn.setSelected(true);
                iv_exercises.setImageResource(R.drawable.main_exercises_icon_selected);
                tv_exercises.setTextColor(Color.parseColor("#0097F7"));
                tv_course.setTextColor(Color.parseColor("#666666"));
                tv_myInfo.setTextColor(Color.parseColor("#666666"));
                iv_course.setImageResource(R.drawable.main_course_icon);
                iv_myInfo.setImageResource(R.drawable.main_my_icon);
                tv_main_title.setText("博学谷习题");
                tv_main_title.setVisibility(View.VISIBLE);
                break;
            case 2:
                mMyInfoBtn.setSelected(true);
                iv_myInfo.setImageResource(R.drawable.main_my_icon_selected);
                tv_myInfo.setTextColor(Color.parseColor("#0097F7"));
                tv_course.setTextColor(Color.parseColor("#666666"));
                tv_exercises.setTextColor(Color.parseColor("#666666"));
                iv_course.setImageResource(R.drawable.main_course_icon);
                iv_exercises.setImageResource(R.drawable.main_exercises_icon);
                tv_main_title.setVisibility(View.GONE);
                break;
        }
    }

    /*private void removeAllView() {
        for (int i = 0; i < mBodyLayout.getChildCount(); i++) {
            mBodyLayout.getChildAt(i).setVisibility(View.GONE);
        }
    }*/

    /*private void setInitStatus() {
        clearBottomImageState();
        setSelectedStatus(2);
        createView(2);
    }*/

   /* private void selectDisplayView(int index) {
        removeAllView();
        createView(index);
        setSelectedStatus(index);
    }*/

   /* private void createView(int viewIndex) {
        switch (viewIndex) {
            case 0:

                break;
            case 1:

                break;
            case 2:
                if (mMyInfoView == null) {
                    mMyInfoView = new MyInfoView(this);
                    mBodyLayout.addView(mMyInfoView.getView());
                } else {
                    mMyInfoView.getView();
                }
                mMyInfoView.showView();
                break;
        }
    }*/

    protected long exitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainActivity.this, "再按一次退出博学谷",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                MainActivity.this.finish();
                if (AnalysisUtils.readLoginStatus(this)) {
                    AnalysisUtils.clearLoginStatus(this);
                }
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
          boolean isLogin=data.getBooleanExtra("isLogin",true);
            String userName=data.getStringExtra("userName");
            if (isLogin) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new MyinfoFragment()).commit();
                setSelectedStatus(2);
            }else {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_body,new MyinfoFragment()).commit();
                setSelectedStatus(2);
            }

        }
         if (requestCode==000){
            setSelectedStatus(1);
         }
    }
}

