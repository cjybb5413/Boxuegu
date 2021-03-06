package cn.edu.gdmec.android.boxuegu.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.MD5Utils;

/**
 * Created by Asus on 2018/3/27.
 */

public class FindPswActivity extends AppCompatActivity{
    private EditText et_validate_name,et_user_name,et_new_psw;
    private Button btn_validate;
    private TextView tv_main_title;
    private TextView tv_back;
    private TextView tv_new_psw;
    private String from;
    private TextView tv_reset_psw,tv_user_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_psw);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        from=getIntent().getStringExtra("from");
        init();
    }
    private void init(){
        tv_main_title=findViewById(R.id.tv_main_title);
        tv_back=findViewById(R.id.tv_back);
        et_validate_name=findViewById(R.id.et_validate_name);
        btn_validate=findViewById(R.id.btn_validate);
        et_new_psw=findViewById(R.id.et_new_psw);
        tv_new_psw=findViewById(R.id.tv_new_psw);
        et_user_name=findViewById(R.id.et_user_name);
        tv_user_name=findViewById(R.id.tv_user_name);
        if ("security".equals(from)){
            tv_main_title.setText("设置密保");
        }else {
            tv_main_title.setText("找回密码");
            tv_user_name.setVisibility(View.VISIBLE);
            et_user_name.setVisibility(View.VISIBLE);
        }
        tv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindPswActivity.this.finish();
            }
        });
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String validateName=et_validate_name.getText().toString().trim();
                if ("security".equals(from)){
                    if (TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActivity.this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        Toast.makeText(FindPswActivity.this,"密保设置成功",Toast.LENGTH_SHORT).show();
                        saveSecurity(validateName);
                        FindPswActivity.this.finish();
                    }
                }else {//找回密码
                    String userName=et_user_name.getText().toString().trim();
                    String sp_security=readSecurity(userName);
                    if (TextUtils.isEmpty(userName)){
                        Toast.makeText(FindPswActivity.this,"请输入您的用户名",Toast.LENGTH_SHORT).show();
                        return;
                    }else if (!isExistUserName(userName)){
                        Toast.makeText(FindPswActivity.this,"您输入的用户名不存在",Toast.LENGTH_SHORT).show();
                        return;
                    }else if (TextUtils.isEmpty(validateName)){
                        Toast.makeText(FindPswActivity.this,"请输入要验证的姓名",Toast.LENGTH_SHORT).show();
                        return;
                    }if (!validateName.equals(sp_security)){
                        Toast.makeText(FindPswActivity.this,"输入密保不正确",Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        et_new_psw.setVisibility(View.VISIBLE);
                        tv_new_psw.setVisibility(View.VISIBLE);
                        btn_validate.setText("确认修改");
                        String newPsw=et_new_psw.getText().toString().trim();
                        if (!TextUtils.isEmpty(newPsw)){
                            savePsw(userName,newPsw);
                        }

                    }
                }
            }
        });
    }
    private void savePsw(String userName,String newPsw){
        String md5Psw= MD5Utils.md5(newPsw);
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(userName,md5Psw);
        editor.commit();
        FindPswActivity.this.finish();
    }
    private void saveSecurity(String validateName){
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(AnalysisUtils.readLoginUserName(this)+"_security",validateName);
        editor.commit();
    }
    private String readSecurity(String userName){
        SharedPreferences sp=getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String security=sp.getString(userName+"_security","");
        return security;
    }
    private boolean isExistUserName(String userName){
        boolean hasUserName=false;
        SharedPreferences sp=getSharedPreferences("loginInfo",MODE_PRIVATE);
        String spPsw=sp.getString(userName,"");
        if (!TextUtils.isEmpty(spPsw)){
            hasUserName=true;
        }
        return hasUserName;
    }
}
