package cn.edu.gdmec.android.boxuegu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.UserBean;
import cn.edu.gdmec.android.boxuegu.utils.AnalysisUtils;
import cn.edu.gdmec.android.boxuegu.utils.DBUtils;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView tvUserName;
    private RelativeLayout rlNickName;
    private TextView tvNickName;
    private RelativeLayout rlSex;
    private TextView tvSex;
    private RelativeLayout rlSignature;
    private RelativeLayout rl_title_bar;
    private RelativeLayout rl_head;
    private RelativeLayout rl_account;
    private TextView tvSignature;
    private String spUserName;
    private String new_info;
    private TextView tv_back;
    private TextView tv_main_title;
    private TextView tv_qq;
    private RelativeLayout rl_qq;
    private static final int CHANGE_NICKNAME = 1;
    private static final int CHANGE_SIGNATURE = 2;
    private static final int CHANGE_QQ=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        spUserName= AnalysisUtils.readLoginUserName(this);
        init();
        initData();
    }

    private void init() {
        tv_back=findViewById(R.id.tv_back);
        tv_main_title=findViewById(R.id.tv_main_title);
        tv_main_title.setText("个人资料");
        rl_title_bar=findViewById(R.id.title_bar);
        rl_head=findViewById(R.id.rl_head);
        rl_account=findViewById(R.id.rl_account);
        rl_qq=findViewById(R.id.rl_qq);
        tv_qq=findViewById(R.id.tv_qq);
        rl_title_bar.setBackgroundColor(Color.parseColor("#30B4FF"));
        tvUserName = (TextView) findViewById(R.id.tv_user_name);
        rlNickName = (RelativeLayout) findViewById(R.id.rl_nickName);
        tvNickName = (TextView) findViewById(R.id.tv_nickName);
        rlSex = (RelativeLayout) findViewById(R.id.rl_sex);
        tvSex = (TextView) findViewById(R.id.tv_sex);
        rlSignature = (RelativeLayout) findViewById(R.id.rl_signature);
        tvSignature = (TextView) findViewById(R.id.tv_signature);

        rl_head.setOnClickListener(this);
        rl_account.setOnClickListener(this);
        rlNickName.setOnClickListener(this);
        rlSex.setOnClickListener(this);
        rlSignature.setOnClickListener(this);
        rl_qq.setOnClickListener(this);


    }
    private void initData(){
        UserBean bean=null;
        bean= DBUtils.getInstance(this).getUserInfo(spUserName);
        if (bean == null){
            bean = new UserBean();
            bean.userName = spUserName;
            bean.nickName = "问答精灵";
            bean.sex = "男";
            bean.signature = "问答精灵";
            bean.qq="未添加";
            DBUtils.getInstance(this).saveUserInfo(bean);
        }
        setValue(bean);
    }
    private void setValue(UserBean bean){
        tvUserName.setText(bean.userName);
        tvNickName.setText(bean.nickName);
        tvSex.setText(bean.sex);
        tvSignature.setText(bean.signature);
        tv_qq.setText(bean.qq);
    }
    private void sexDialog(String sex){
        int sexFlag = 0;
        if ("男".equals(sex)){
            sexFlag = 0;
        }else if ("女".equals(sex)){
            sexFlag = 1;
        }
        final String item[] = {"男","女"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("性别");
        builder.setSingleChoiceItems(item, sexFlag, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Toast.makeText(UserInfoActivity.this,item[i],Toast.LENGTH_SHORT).show();
                setSex(item[i]);
            }
        });
        builder.create().show();
    }
    private void setSex(String s){
        tvSex.setText(s);
        DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("sex",s,spUserName);
    }
    public void enterActivityForResult(Class<?> to, int requestcode, Bundle b){
        Intent i = new Intent(this,to);
        i.putExtras(b);
        startActivityForResult(i,requestcode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CHANGE_NICKNAME:
                if (data != null){
                    new_info = data.getStringExtra("nickName");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tvNickName.setText(new_info);
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("nickName",new_info,spUserName);
                }
                break;
            case CHANGE_SIGNATURE:
                if (data != null){
                    new_info = data.getStringExtra("signature");
                    if (TextUtils.isEmpty(new_info)){
                        return;
                    }
                    tvSignature.setText(new_info);
                    DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("signature",new_info,spUserName);
                }
                break;
                case CHANGE_QQ:
                    if (data != null){
                        new_info = data.getStringExtra("qq");
                        if (TextUtils.isEmpty(new_info)){
                            return;
                        }
                        tv_qq.setText(new_info);
                        DBUtils.getInstance(UserInfoActivity.this).updateUserInfo("qq",new_info,spUserName);
                    }
                    break;
        }
    }

    @Override
    public void onClick(View view) {
       switch (view.getId()){
           case R.id.tv_back:
               this.finish();
               break;
           case R.id.rl_nickName:
               String name=tvNickName.getText().toString();
               Bundle bdName=new Bundle();
               bdName.putString("content",name);
               bdName.putString("title","昵称");
               bdName.putInt("flag",1);
               enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_NICKNAME,bdName);
               break;
           case R.id.rl_sex:
               String sex=tvSex.getText().toString();
               sexDialog(sex);
               break;
           case R.id.rl_signature:
               String signature = tvSignature.getText().toString();
               Bundle bdSignature = new Bundle();
               bdSignature.putString("content",signature);
               bdSignature.putString("title","签名");
               bdSignature.putInt("flag",2);
               enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_SIGNATURE,bdSignature);
               break;
           case R.id.rl_qq:
               String qq = tv_qq.getText().toString();
               Bundle bdQq = new Bundle();
               bdQq.putString("content",qq);
               bdQq.putString("title","QQ号");
               bdQq.putInt("flag",3);
               enterActivityForResult(ChangeUserInfoActivity.class,CHANGE_QQ,bdQq);
               break;
       }
    }
}
