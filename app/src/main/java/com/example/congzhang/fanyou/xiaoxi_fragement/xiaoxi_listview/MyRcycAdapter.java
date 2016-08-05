package com.example.congzhang.fanyou.xiaoxi_fragement.xiaoxi_listview;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.congzhang.fanyou.R;

import java.util.List;

/**
 * Created by CongZhang on 2016/7/16.
 */
public class MyRcycAdapter extends RecyclerView.Adapter<MyViewHolder>{

    private List<XiaoXiItem> mDatas;
    private LayoutInflater mInflater;
    private Context mContext;


    public MyRcycAdapter(Context context, List<XiaoXiItem> mDatas) {
        this.mDatas = mDatas;
        mContext = context;
        mInflater = LayoutInflater.from(context);

    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("1");
        View view = mInflater.inflate(R.layout.myxiaoxi_recycview_item, parent, false);
        System.out.println("2");
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        System.out.println("3");
        holder.name.setText("ddddd");
        holder.title.setText("dd");
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder {
    public ImageView img;
    public CardView cardView;
    public TextView title;
    public TextView name;

    public MyViewHolder(View itemView) {
        super(itemView);
        System.out.println("6");
        cardView = (CardView) itemView.findViewById(R.id.view2) ;
        title = (TextView) itemView.findViewById(R.id.textView6);
        name = (TextView) itemView.findViewById(R.id.textView7);
        img = (ImageView) itemView.findViewById(R.id.userheader);
    }
}
