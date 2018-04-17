package cn.edu.gdmec.android.boxuegu.activity;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

import cn.edu.gdmec.android.boxuegu.R;

public class ExercisesDetailActivity extends Activity  {

    private ListView lvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_detail);

        lvList = (ListView) findViewById(R.id.lv_list);
    }

}
