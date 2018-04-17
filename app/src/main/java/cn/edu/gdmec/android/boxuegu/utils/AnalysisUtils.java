package cn.edu.gdmec.android.boxuegu.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;

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
        editor.commit();
    }

    /*解析每章节的习题*/
    public static List<ExercisesBean> getExercisesInfos(InputStream is) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setInput(is, "utf-8");
        List<ExercisesBean> exercisesInfos = null;
        ExercisesBean exercisesInfo = null;
        int type = parser.getEventType();
        while (type != XmlPullParser.END_DOCUMENT) {
            switch (type) {
                case XmlPullParser.START_TAG:
                    if ("infos".equals(parser.getName())) {
                        exercisesInfos = new ArrayList<ExercisesBean>();
                    } else if ("exercises".equals(parser.getName())) {
                        exercisesInfo = new ExercisesBean();
                        String ids = parser.getAttributeValue(0);
                        exercisesInfo.subjectId = Integer.parseInt(ids);
                    } else if ("subject".equals(parser.getName())) {
                        String subject = parser.nextText();
                        exercisesInfo.subject = subject;
                    } else if ("a".equals(parser.getName())) {
                        String a = parser.nextText();
                        exercisesInfo.a = a;
                    } else if ("b".equals(parser.getName())) {
                        String b = parser.nextText();
                        exercisesInfo.b = b;
                    } else if ("c".equals(parser.getName())) {
                        String c = parser.nextText();
                        exercisesInfo.c = c;
                    } else if ("d".equals(parser.getName())) {
                        String d = parser.nextText();
                        exercisesInfo.d = d;
                    } else if ("answer".equals(parser.getName())) {
                        String answer = parser.nextText();
                        exercisesInfo.answer = Integer.parseInt(answer);
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if ("exercises".equals(parser.getName())) {
                        exercisesInfos.add(exercisesInfo);
                        exercisesInfo = null;
                    }
                    break;
            }
            type = parser.next();
        }
        return exercisesInfos;
    }
}