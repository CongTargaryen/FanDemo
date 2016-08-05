package com.example.congzhang.fanyou.main_viewpager_fragment;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.TintTypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.bmob.PutFanTuan;
import com.example.congzhang.fanyou.fabu.FaBuItem;
import com.example.congzhang.fanyou.xiaoxi_fragement.xiaoxi_listview.XiaoXiItem;

import java.util.List;

/**
 * Created by CongZhang on 2016/7/29.
 */
public class FanTuanListAdapter extends RecyclerView.Adapter<FanTuanListAdapter.MyFanTuanViewHolder> {

    private List<PutFanTuan> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;



    public List<PutFanTuan> getmDatas() {
        return mDatas;
    }

    public FanTuanListAdapter(Context context, List<PutFanTuan> mDatas) {
        this.mDatas = mDatas;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyFanTuanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.fantuan_list_item, parent, false);
        MyFanTuanViewHolder myViewHolder = new MyFanTuanViewHolder(view);
        return myViewHolder;
    }



    @Override
    public void onBindViewHolder(final MyFanTuanViewHolder holder, final int position) {
            holder.itemView.setTag(position);
            holder.numphone.setText(mDatas.get(position).getPhone());
            holder.leirong.setText(mDatas.get(position).getDescribe());
            holder.title.setText(mDatas.get(position).getTitle());



    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void clearData() {
        mDatas.clear();
        notifyItemRangeChanged(0, mDatas.size());
    }

    public void addData(List<PutFanTuan> Datas) {
        addData(0,Datas);
    }

    public void addData(int postion, List<PutFanTuan> Datas) {
        if (Datas != null && Datas.size() > 0) {
            mDatas.addAll(Datas);
            notifyItemRangeChanged(postion, mDatas.size());
        }

    }

    class MyFanTuanViewHolder extends RecyclerView.ViewHolder {
        //TODO
        public TextView title;
        public TextView leirong;
        public TextView juli;
        public TextView numphone;


        public MyFanTuanViewHolder(View itemView) {
            super(itemView);
                title = (TextView) itemView.findViewById(R.id.biaoti);
                leirong = (TextView) itemView.findViewById(R.id.jutileirong);
                juli = (TextView) itemView.findViewById(R.id.juli);
                numphone = (TextView) itemView.findViewById(R.id.nianxifangshi);

        }


    }
}
