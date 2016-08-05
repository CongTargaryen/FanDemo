package com.example.congzhang.fanyou.main_viewpager_fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.congzhang.fanyou.MyApplication;
import com.example.congzhang.fanyou.R;
import com.example.congzhang.fanyou.bmob.PutFanTuan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobDate;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by CongZhang on 2016/7/19.
 */
public class FanTuanFragment extends Fragment {
    public RecyclerView recyclerView;
    private FanTuanListAdapter fanTuanListAdapter;
    private List<PutFanTuan> fanTuanItems;
    private static final int STATE_NEW = 2;
    private static final int STATE_REFRESH = 0;// 下拉刷新
    private static final int STATE_MORE = 1;// 加载更多
    private MaterialRefreshLayout materialRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sousuo_activity, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fantuanRecyclerView);
        fanTuanItems = new ArrayList<PutFanTuan>();
        materialRefreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.fantuanrefresh);
        showData(0, STATE_NEW);
        materialRefreshLayout.setLoadMore(true);
        materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                new Handler().postDelayed(new Runnable() {
                                              @Override
                                              public void run() {
                                                  System.out.println("刷新执行权"+ fanTuanItems.size() + "and" + fanTuanListAdapter.getmDatas().size());
                                                  showData(0, STATE_REFRESH);
                                                  System.out.println("刷新执行"+ fanTuanItems.size() + "and" + fanTuanListAdapter.getmDatas().size());
                                              }
                }, 3000);

            }
            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("more执行权"+ fanTuanItems.size() + "and" + fanTuanListAdapter.getmDatas().size());
                        System.out.println("这是第"+curPage);
                        showData(curPage, STATE_MORE);
                        System.out.println("more执行"+ fanTuanItems.size() + "and" + fanTuanListAdapter.getmDatas().size());
                    }
                }, 3000);
            }
        });
        recyclerView.addOnItemTouchListener(new OnItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {

                System.out.println("" + vh.getAdapterPosition());
            }
        });
        return view;
    }
    int count = 1;
    int curPage = 0;//当前页数
    String lastTime;
    int number;
    private void showData(int page,int satae) {
        BmobQuery<PutFanTuan> query = new BmobQuery<>();
        query.order("-createdAt");
        query.setLimit(count);
        switch (satae) {
            case STATE_NEW:
                query.findObjects(new FindListener<PutFanTuan>() {
                    @Override
                    public void done(List<PutFanTuan> list, BmobException e) {
                        if (list != null) {
                            for (PutFanTuan p : list) {
                                fanTuanItems.add(p);
                                System.out.println("初始化" + p.getTitle());
                            }
                            fanTuanListAdapter = new FanTuanListAdapter(MyApplication.getInstance(), fanTuanItems);
                            recyclerView.setLayoutManager(new LinearLayoutManager(MyApplication.getInstance()));
                            recyclerView.setAdapter(fanTuanListAdapter);
                            recyclerView.addItemDecoration(new DividerItemDecoration(MyApplication.getInstance(),
                                    DividerItemDecoration.VERTICAL_LIST));
                            curPage++;
                            System.out.println("这是第" + curPage);
                            lastTime = fanTuanItems.get(fanTuanItems.size() - 1).getCreatedAt();
                            System.out.println(lastTime);
                        }
                    }
                });
                break;
            case STATE_MORE:
                Date date = null;
                System.out.println("转换时间");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    date = sdf.parse(lastTime);
                } catch (java.text.ParseException e) {
                    e.printStackTrace();
                }
                query.addWhereLessThanOrEqualTo("createdAt", new BmobDate(date));
                System.out.println("转换时间" + lastTime);
                query.setSkip((page - 1) * count);
                query.setLimit(count);
                query.findObjects(new FindListener<PutFanTuan>() {
                    @Override
                    public void done(List<PutFanTuan> list, BmobException e) {
                        for (PutFanTuan p : list) {
                            fanTuanItems.add(p);
                            System.out.println("上拉加载" + p.getTitle());
                        }
                        fanTuanListAdapter.addData(fanTuanListAdapter.getmDatas().size(), fanTuanItems);
                        recyclerView.scrollToPosition(fanTuanListAdapter.getmDatas().size());
//                    materialRefreshLayout.finishRefreshLoadMore();
                        curPage++;
                        lastTime = fanTuanItems.get(fanTuanItems.size() - 1).getCreatedAt();
                        System.out.println(lastTime);
                    }
                });
                materialRefreshLayout.finishRefreshLoadMore();
                break;
            case STATE_REFRESH:
                query.findObjects(new FindListener<PutFanTuan>() {
                    @Override
                    public void done(List<PutFanTuan> list, BmobException e) {
                        if (list != null) {
                            curPage = 0;
                            fanTuanListAdapter.clearData();
                            fanTuanItems.clear();
                            for (PutFanTuan td : list) {
                                fanTuanItems.add(td);
                                System.out.println("加载了一次" + td.getTitle());
                            }
                            fanTuanListAdapter.addData(fanTuanItems);
                            recyclerView.scrollToPosition(0);
                            curPage++;
                            lastTime = fanTuanItems.get(fanTuanItems.size() - 1).getCreatedAt();
                            System.out.println(lastTime);
                        }
                    }
                });
                materialRefreshLayout.finishRefresh();
                break;
            default:
                break;

        }

    }

    public abstract class OnItemTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetectorCompat mGestureDetectorCompat;
        private RecyclerView mRecyclerView;

        public OnItemTouchListener(RecyclerView recyclerView) {
            mRecyclerView = recyclerView;
            mGestureDetectorCompat = new GestureDetectorCompat(mRecyclerView.getContext(),
                    new MyGestureListener());
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetectorCompat.onTouchEvent(e);
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mGestureDetectorCompat.onTouchEvent(e);
            return false;
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

        public abstract void onItemClick(RecyclerView.ViewHolder vh);

        private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View childe = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childe != null) {
                    RecyclerView.ViewHolder VH = mRecyclerView.getChildViewHolder(childe);
                    onItemClick(VH);
                }
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childe = mRecyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childe != null) {
                    RecyclerView.ViewHolder VH = mRecyclerView.getChildViewHolder(childe);
                    onItemClick(VH);
                }
            }
        }
    }


}
