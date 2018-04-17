package cn.edu.gdmec.android.boxuegu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.gdmec.android.boxuegu.R;
import cn.edu.gdmec.android.boxuegu.bean.ExercisesBean;

public class ExercisesAdapter extends BaseAdapter{
    private Context mContext;
    private List<ExercisesBean> eb1;
    public ExercisesAdapter(Context context){
        this.mContext=context;
    }
    /*设置数据更新界面*/
    public void setData(List<ExercisesBean> eb1){
        this.eb1=eb1;
        notifyDataSetChanged();
    }
   /*获取Item的总数*/
    @Override
    public int getCount() {
        return eb1==null ? 0 : eb1.size();
    }
    /*根据position得到对应Item的对象*/
    @Override
    public ExercisesBean getItem(int position) {
        return eb1==null ? null : eb1.get(position);
    }
    /*根据position得到对应Item的id*/
    @Override
    public long getItemId(int position) {
        return position;
    }
    /*得到对应position对应的Item视图，position是当前Item的位置，
    converView参数就是滑出屏幕的Item的View*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        /*复用converView*/
        if (convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.exercises_list_item,null);
            vh.title=convertView.findViewById(R.id.tv_title);
            vh.content=convertView.findViewById(R.id.tv_centent);
            vh.order=convertView.findViewById(R.id.tv_order);
            convertView.setTag(vh);
        }else {
            vh=(ViewHolder) convertView.getTag();
        }
        final ExercisesBean bean=getItem(position);
        if (bean!=null){
            vh.order.setText(position + 1 + "");
            vh.title.setText(bean.title);
            vh.content.setText(bean.content);
            vh.order.setBackgroundResource(bean.background);
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bean==null){
                    return;
                    //跳转到习题详情页面
                }
            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView title,content;
        public TextView order;
    }
}
