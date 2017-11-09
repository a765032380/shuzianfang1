package com.bjxiyang.shuzianfang.myapplication.fragment_key;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bjxiyang.shuzianfang.R;

/**
 * Created by Administrator on 2017/9/6 0006.
 */

public class ZuKeFragment extends Fragment {
    private View view;
//    private List<PermissionList.Obj> mList;
//    private SwipeListView mListView;
//    private RelativeLayout ib_fanghui;
//    private RelativeLayout ib_tianjia;
//    private LinearLayout ll_activity_wushouquan;
//    private LinearLayout ll_activity_listview;
//    private LinearLayout ll_activity_wuwangluo;
//    private SwipeRefreshLayout swipeRefreshLayout;
//    private SwipeRefreshLayout swipeRefreshLayout1;
//    private SwipeRefreshLayout swipeRefreshLayout2;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=View.inflate(getContext(), R.layout.xy_fragment_jiaren3,null);
        initUI();
        return view;
    }

    private void initUI() {




    }

}
