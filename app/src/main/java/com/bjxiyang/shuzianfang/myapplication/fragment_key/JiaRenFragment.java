package com.bjxiyang.shuzianfang.myapplication.fragment_key;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.app.GuardApplication;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.PermissionList;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.ui.view.XYKeyaccreditAdapter;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.view.SwipeListLayout;
import com.bjxiyang.shuzianfang.myapplication.view.SwipeListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class JiaRenFragment extends Fragment implements View.OnClickListener,SwipeRefreshLayout.OnRefreshListener {
    private View view;
    private int type;

    private List<PermissionList.Obj> mList;
    private SwipeListView mListView;
    private RelativeLayout ib_fanghui;
    private RelativeLayout ib_tianjia;
    private LinearLayout ll_activity_wushouquan;
    private LinearLayout ll_activity_listview;
    private LinearLayout ll_activity_wuwangluo;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SwipeRefreshLayout swipeRefreshLayout1;
    private SwipeRefreshLayout swipeRefreshLayout2;
    private Set<SwipeListLayout> sets = new HashSet();


    public JiaRenFragment(int type){
        this.type=type;
    }


    @Override
    public void onResume() {
        super.onResume();
        initUI();
        initData();
        getData();
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getContext(), R.layout.xy_fragment_jiaren3,null);
        initUI();
        initData();
        getData();
        return view;
    }
    private void initUI() {
        ll_activity_wushouquan= (LinearLayout)view.findViewById(R.id.ll_activity_wushouquan);
        ll_activity_wuwangluo= (LinearLayout) view.findViewById(R.id.ll_activity_wuwangluo);
        mListView= (SwipeListView) view.findViewById(R.id.lv_keyaccredit);

        swipeRefreshLayout= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout1= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout1);
        swipeRefreshLayout1.setOnRefreshListener(this);
        swipeRefreshLayout1.setColorSchemeResources(R.color.color_898787);
        swipeRefreshLayout2= (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout2);
        swipeRefreshLayout2.setOnRefreshListener(this);
        swipeRefreshLayout2.setColorSchemeResources(R.color.color_898787);
    }

    @Override
    public void onClick(View v) {

    }
    public List<PermissionList.Obj> getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(getContext(),"正在加载",true);
        String url= XY_Response.URL_FINDPERMISSIONSBYTYPE+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone","0")
                +"&findType="+type;


        Log.i("llll",url);
        RequestCenter.findPermissions(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                PermissionList permissionList= (PermissionList) responseObj;
                if (permissionList.getCode().equals("1000")){
                    mList= permissionList.getObj();
                    if (mList.size()>0){
                        XYKeyaccreditAdapter adapter=new XYKeyaccreditAdapter(getContext(),mList);
                        mListView.setAdapter(adapter);
                        showShuJu();
                    }else {
                        showWuShuJu();
                    }
                }else {
                    showWuShuJu();
                    Toast.makeText(GuardApplication.getContent(),permissionList.getMsg(), Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                showWuWangLuo();
            }
        });
        return mList;
    }
    @Override
    public void onRefresh() {
        getData();
        swipeRefreshLayout.setRefreshing(false);
        swipeRefreshLayout1.setRefreshing(false);
        swipeRefreshLayout2.setRefreshing(false);
    }
    private void showShuJu(){
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }
    private void showWuWangLuo(){
        swipeRefreshLayout2.setVisibility(View.VISIBLE);
        swipeRefreshLayout1.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    private void showWuShuJu(){
        swipeRefreshLayout2.setVisibility(View.GONE);
        swipeRefreshLayout1.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.GONE);
    }
    private void initData() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
                    case SCROLL_STATE_TOUCH_SCROLL:
                        if (sets.size() > 0) {
                            for (SwipeListLayout s : sets) {
                                s.setStatus(SwipeListLayout.Status.Close, true);
                                sets.remove(s);
                            }
                        }
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

            }
        });
    }
}
