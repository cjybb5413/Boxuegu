package cn.edu.gdmec.android.boxuegu.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;

public class SettingActivity extends Activity implements View.OnClickListener{

    private TextView tv_back;
    private TextView tv_main_title;
    private RelativeLayout title_bar;
    private RelativeLayout rl_modify_psw;
    private RelativeLayout rl_security_setting;
    private RelativeLayout rl_exit_login;
    public static SettingActivity instance=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        instance=this;
        initView();
    }

    private void initView() {
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_main_title = (TextView) findViewById(R.id.tv_main_title);
        title_bar = (RelativeLayout) findViewById(R.id.title_bar);
        rl_modify_psw = (RelativeLayout) findViewById(R.id.rl_modify_psw);
        rl_security_setting = (RelativeLayout) findViewById(R.id.rl_security_setting);
        rl_exit_login = (RelativeLayout) findViewById(R.id.rl_exit_login);
        tv_main_title.setText("设置");
        title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));

        tv_back.setOnClickListener(this);
        rl_exit_login.setOnClickListener(this);
        rl_modify_psw.setOnClickListener(this);
        rl_security_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_back:
                SettingActivity.this.finish();
                break;
            case R.id.rl_modify_psw:
                Intent intent=new Intent(SettingActivity.this,ModifyPswActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_security_setting:
              Intent intent1=new Intent(SettingActivity.this,FindPswActivity.class);
              intent1.putExtra("from","security");
              startActivity(intent1);
                break;
            case R.id.rl_exit_login:
                Toast.makeText(SettingActivity.this,"退出登录成功",Toast.LENGTH_SHORT).show();
                AnalysisUtils.clearLoginStatus(this);
                Intent data=new Intent();
                data.putExtra("isLogin",false);
                setResult(RESULT_OK,data);
                SettingActivity.this.finish();
                break;
        }
    }
}
