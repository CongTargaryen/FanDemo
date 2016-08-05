package com.example.congzhang.fanyou.main_viewpager_fragment;


import android.net.ParseException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.congzhang.fanyou.MyApplication;
import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.bmob.PutFanTuan;

import org.json.JSONObject;



import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobRealTimeData;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.ValueEventListener;

/**
 * Created by CongZhang on 2016/7/19.
 */
public class ShouYeFragment extends Fragment{

    private RecyclerView recyclerView;
    private List<PutFanTuan> putFanTuanList;
    private FanTuanListAdapter fanTuanListAdapter;
    private PutFanTuan putFanTuan;
    BmobRealTimeData rtd;

    private static final int STATE_NEW = 2;
    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多
    private int limit = 5;		// 每页的数据是10条
    private int curPage = 0;// 当前页的编号，从0开始
    private int count = 5;
    private int state;
    @Nullable
    private MaterialRefreshLayout materialRefreshLayout;

    String lastTime = null;


//    private void refreshData() {
//        curPage = 1;
//        state = STATE_REFRESH;
//        showData();
//    }

    private void showToast(String msg){
        Toast.makeText(MyApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }

    private void showData() {
//        switch (state) {
//            case STATE_NEW:
//                BmobQuery<PutFanTuan> query = new BmobQuery<>();
//                query.order("-createdAt");
//                query.setLimit(count);
//
//                    query.findObjects(new FindListener<PutFanTuan>() {
//                        @Override
//                        public void done(List<PutFanTuan> list, BmobException e) {
//                            for (PutFanTuan gameScore : list) {
//                                //获得playerName的信息
//                                System.out.println("成功加载！！1");
//                                bankCards.add(gameScore);
//                            }
//                        }
//                    });
//
//
//                fanTuanListAdapter = new FanTuanListAdapter(MyApplication.getInstance(), putFanTuanList);
//                recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance()));
//                recyclerView.setAdapter(fanTuanListAdapter);
//                recyclerView.addItemDecoration(new DividerItemDecoration(MyApplication.getInstance(),
//                        DividerItemDecoration.VERTICAL_LIST));
//                break;
//            case STATE_REFRESH:
//                fanTuanListAdapter.clearData();
//                fanTuanListAdapter.addData(putFanTuanList);
//                recyclerView.scrollToPosition(0);
//                materialRefreshLayout.finishRefresh();
//                break;
//        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.zhuye_fragment, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
//        JianTingPut();

        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.refresh);
        putFanTuanList = new ArrayList<PutFanTuan>();
        queryData(0,STATE_NEW);
        materialRefreshLayout.setLoadMore(true);
        if (materialRefreshLayout != null) {
            materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
                @Override
                public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
//                    refreshData();
                    queryData(0 , STATE_REFRESH);
                }

                @Override
                public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
//                    queryData(curPage, STATE_MORE);
                }
            });
        }

//        for (int i = 0; i < 10 ; i++)
//        {
//            putFanTuan = new PutFanTuan();
//            putFanTuan.setTitle("2222");
//            putFanTuan.setPhone("ddddd");
//            putFanTuan.setDescribe("ddd");
//            putFanTuanList.add(putFanTuan);
//        }
//        fanTuanListAdapter = new FanTuanListAdapter(MyApplication.getInstance(), putFanTuanList);
//        recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance()));
//        recyclerView.setAdapter(fanTuanListAdapter);
//        recyclerView.addItemDecoration(new DividerItemDecoration(MyApplication.getInstance(),
//                DividerItemDecoration.VERTICAL_LIST));
        return view;
    }

    //监测PutFT表的变化
//    private void JianTingPut() {
//        rtd = new BmobRealTimeData();
//        rtd.start(new ValueEventListener() {
//
//            @Override
//            public void onConnectCompleted(Exception e) {
//                if (rtd.isConnected() == true)
//                {
//                rtd.subTableUpdate("PutFanTuan");
//                }
//                Log.d("bmob", "连接成功:"+rtd.isConnected());
//        }
//        @Override
//            public void onDataChange(JSONObject jsonObject) {
//                Log.d("bmob", "("+jsonObject.optString("action")+")"+"数据："+jsonObject);
//            if (BmobRealTimeData.ACTION_UPDATETABLE.equals(jsonObject.optString("action"))) {
//                JSONObject data = jsonObject.optJSONObject("data");
//                String a = data.optString("name");
//                System.out.println(a);
//                putFanTuan = new PutFanTuan();
//                putFanTuan.setPhone(data.optString("phone"));
//                putFanTuan.setName(data.optString("name"));
//                putFanTuan.setMoney(data.optString("money"));
//                putFanTuan.setFangshi(data.optString("fangshi"));
//                putFanTuan.setTitle(data.optString("title"));
//                putFanTuan.setDescribe(data.optString("describe"));
//                putFanTuan.setTime(data.optString("time_s"));
//                putFanTuan.setTime_y(data.optString("time_y"));
//                putFanTuanList.add(putFanTuan);
//            }
//        }
//        });
//
//    }
    private void queryData( int page, final int actionType) {
        BmobQuery<PutFanTuan> query = new BmobQuery<>();
        query.order("-createdAt");
        query.setLimit(count);
        if (actionType == STATE_NEW) {
            query.findObjects(new FindListener<PutFanTuan>() {
                @Override
                public void done(List<PutFanTuan> list, BmobException e) {
//                    lastTime = list.get(list.size()-1).getCreatedAt();
//                    System.out.println(lastTime);
                    for (PutFanTuan gameScore : list) {
                        //获得playerName的信息
                        System.out.println("成功加载！！1");
                        putFanTuanList.add(gameScore);
                    }
                    fanTuanListAdapter = new FanTuanListAdapter(MyApplication.getInstance(), putFanTuanList);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance()));
                    recyclerView.setAdapter(fanTuanListAdapter);
                    recyclerView.addItemDecoration(new DividerItemDecoration(MyApplication.getInstance(),
                            DividerItemDecoration.VERTICAL_LIST));
                    curPage++;

                }
            });
        }
        // 如果是加载更多;
        if(actionType == STATE_MORE){
            // 处理时间查询
//            Date date = null;
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            try {
//                date = sdf.parse(lastTime);
//            }
//            catch (java.text.ParseException e) {
//                e.printStackTrace();
//            }
            // 只查询小于等于最后一个item发表时间的数据
//            query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
            // 跳过之前页数并去掉重复数据
            query.setSkip(page * count);
            query.findObjects(new FindListener<PutFanTuan>() {
                @Override
                public void done(List<PutFanTuan> list, BmobException e) {
                    if (list != null) {
//                        lastTime = list.get(list.size()-1).getCreatedAt();
                        for (PutFanTuan myputFanTuan : list) {
                            putFanTuanList.add(myputFanTuan);
                            System.out.println("加载了一次");
                        }
                        fanTuanListAdapter.addData(fanTuanListAdapter.getmDatas().size(),putFanTuanList);
                        recyclerView.scrollToPosition(fanTuanListAdapter.getmDatas().size());
                        materialRefreshLayout.finishRefreshLoadMore();
                        curPage++;
                    }
                }
            });
        }else{
            page = 0;
            query.setSkip(page);
        }
        // 设置每页数据个数
        // 查找数据
        if (actionType == STATE_REFRESH) {
            query.findObjects(new FindListener<PutFanTuan>() {
                @Override
                public void done(List<PutFanTuan> list, BmobException e) {
                    if(list != null){
                            // 当是下拉刷新操作时，将当前页的编号重置为0，并把bankCards清空，重新添加
                            curPage = 0;
                            fanTuanListAdapter.clearData();
                            putFanTuanList.clear();
                            // 获取最后时间
                            lastTime = list.get(list.size()-1).getCreatedAt();


                            // 将本次查询的数据添加到bankCards中
                            for (PutFanTuan td : list) {
                                putFanTuanList.add(td);
                                System.out.println("加载了一次");
                            }
                            fanTuanListAdapter.addData(putFanTuanList);
                            recyclerView.scrollToPosition(0);

                            // 这里在每次加载完数据后，将当前页码+1，这样在上拉刷新的onPullUpToRefresh方法中就不需要操作curPage了
                            curPage++; }
                        showToast("第"+"页数据加载完成");
                    materialRefreshLayout.finishRefresh();
                }

            });
        }

    }
}
