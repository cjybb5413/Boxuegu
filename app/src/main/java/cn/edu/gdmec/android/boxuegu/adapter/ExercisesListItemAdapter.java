package cn.edu.gdmec.android.boxuegu.adapter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.activity.ExercisesDetailActivity;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;

public class ExercisesListItemAdapter extends BaseAdapter {

    private List<ExercisesBean> objects = new ArrayList<ExercisesBean>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ExercisesListItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public void setData(List<ExercisesBean> objects){
        this.objects=objects;
        notifyDataSetChanged();
    }
    public void updateView(List<ExercisesBean> objects){
        this.objects=objects;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return objects == null ? 0 : objects.size();
    }

    @Override
    public ExercisesBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.exercises_list_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((ExercisesBean) getItem(position), (ViewHolder) convertView.getTag(),position,convertView);
        return convertView;
    }

    private void initializeViews(ExercisesBean object, ViewHolder holder, int position, View convertView) {
        //TODO implement
        final ExercisesBean bean=getItem(position);
        SharedPreferences share=context.getSharedPreferences("total", Context.MODE_PRIVATE);
        boolean ih=share.getBoolean("isFinish"+(position+1),false);
        if (bean!=null){
            holder.tvOrder.setText(position + 1 + "");
            holder.tvTitle.setText(bean.title);
            if (ih){
                holder.tvCentent.setText("已经完成！");
            }else {
                holder.tvCentent.setText(bean.content);
            }

            holder.tvOrder.setBackgroundResource(bean.background);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean == null) {
                    return;

                }
                Intent intent=new Intent(context, ExercisesDetailActivity.class);
                intent.putExtra("id",bean.id);
                intent.putExtra("title",bean.title);
                ((Activity)context).startActivityForResult(intent,000);
            }
        });
    }
}

    protected class ViewHolder {
        private TextView tvOrder;
        private TextView tvTitle;
        private TextView tvCentent;

        public ViewHolder(View view) {
            tvOrder = (TextView) view.findViewById(R.id.tv_order);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            tvCentent = (TextView) view.findViewById(R.id.tv_centent);
        }
    }
}
