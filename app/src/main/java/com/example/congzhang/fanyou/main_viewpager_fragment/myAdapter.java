package com.example.congzhang.fanyou.main_viewpager_fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.bmob.PutFanTuan;

import java.util.List;

/**
 * Created by CongZhang on 2016/8/4.
 */
public class myAdapter extends BaseAdapter{
    private List<PutFanTuan> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;

    public myAdapter(Context context, List<PutFanTuan> mDatas) {
        this.mDatas = mDatas;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void clearData() {
        mDatas.clear();
//        notifyItemRangeChanged(0, mDatas.size());
    }

    public void addData(List<PutFanTuan> Datas) {
        addData(0,Datas);
    }

    public void addData(int postion, List<PutFanTuan> Datas) {
        if (Datas != null && Datas.size() > 0) {
            mDatas.addAll(Datas);
//           notifyDateSetChanged(postion, mDatas.size());
        }

    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        myViewHolder viewHolder;
        if (view == null) {
            view = mInflater.inflate(R.layout.fantuan_list_item,null);
            viewHolder = new myViewHolder();
            /**得到各个控件的对象*/
            viewHolder.title = (TextView) view.findViewById(R.id.biaoti);
            viewHolder.leirong = (TextView) view.findViewById(R.id.jutileirong);
            viewHolder.juli = (TextView)view.findViewById(R.id.juli);
            viewHolder.numphone = (TextView) view.findViewById(R.id.nianxifangshi);
            view.setTag(viewHolder);//绑定ViewHolder对象
        }
        else{
            viewHolder = (myViewHolder)view.getTag();//取出ViewHolder对象
        }
        viewHolder.numphone.setText(mDatas.get(i).getPhone());
        viewHolder.leirong.setText(mDatas.get(i).getDescribe());
        viewHolder.title.setText(mDatas.get(i).getTitle());

        return null;
    }
    class myViewHolder{
        public TextView title;
        public TextView leirong;
        public TextView juli;
        public TextView numphone;
    }
}
