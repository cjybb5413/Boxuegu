package cn.edu.gdmec.android.boxuegu.activity;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity {
    private TextView tvUserName;
    private RelativeLayout rlNickName;
    private TextView tvNickName;
    private RelativeLayout rlSex;
    private TextView tvSex;
    private RelativeLayout rlSignature;
    private RelativeLayout rl_title_bar;
    private TextView tvSignature;
    private String spUserName;
    private TextView tv_back;
    private TextView tv_main_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spUserName= AnalysisUtils.readLoginUserName(this);
        init();
    }

    private void init() {
        tv_back=findViewById(R.id.tv_back);
        tv_main_title=findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_title_bar=findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        rlNickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        tvNickName = (TextView) findViewById(R.id.tv_nickName);
        rlSex = (RelativeLayout) findViewById(R.id.rl_sex);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        rlSignature = (RelativeLayout) findViewById(R.id.rl_signature);
        tvSignature = (TextView) findViewById(R.id.tv_signature);
    }
    private void initData(){
        UserBean bean=null;
        bean= DBUtils.getInstance(this).getUserInfo(spUserName);
    }
}
