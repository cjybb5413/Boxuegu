package cn.edu.gdmec.android.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
<<<<<<< HEAD
 * Created by ASUS on 2018/3/19.
 */

public class AnalysisUtils {
    /*从SharedPreference中读取登录用户名*/
    public static String readLoginUserName(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        String userName = sp.getString("loginUserName", "");
        return userName;
    }

    //读取登录状态
    public static boolean readLoginStatus(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        return isLogin;
    }

    //清除登录状态
    public static void clearLoginStatus(Context context) {
        SharedPreferences sp = context.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("isLogin", false);
        editor.putString("loginUserName", "");
    }
}