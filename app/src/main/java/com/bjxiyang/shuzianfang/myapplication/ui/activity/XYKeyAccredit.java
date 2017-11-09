package com.bjxiyang.shuzianfang.myapplication.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerTabStrip;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.adapter.MyFragmentAdapter;
import com.bjxiyang.shuzianfang.myapplication.fragment_key.JiaRenFragment;
import com.bjxiyang.shuzianfang.myapplication.view.CustomViewPager;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gll on 17-5-23.
 */

public class XYKeyAccredit extends MySwipeBackActivity implements View.OnClickListener {

    /**
     *DATA
     */
    private static final int JIAREN_TYPE=0;
    private static final int ZUKE_TYPE=1;
    private static final String JIAREN="家人";
    private static final String ZUKE="租客";

    private RelativeLayout rl_jinrong_fanhui;
    private View view;
    private Context mContext;
    private MyFragmentAdapter mMyFragmentAdapter;
    //声明ViewPager
    private CustomViewPager mViewPager;
    //ViewPager的指示器
    private PagerTabStrip mPagerTabStrip;
    //储存ViewPager的指示器文本内容的集合
    private List<String> mTitleList;
    //储存Fragment的集合
    private List<Fragment> mFragmentList;
    //Fragment管理者
    private FragmentManager fm;

//    private PagerSlidingTabStrip tabs;
    private LinearLayout ib_fanghui;
    private RelativeLayout ib_tianjia;

    @BindView(R.id.ll_jiaren)
    LinearLayout ll_jiaren;
    @BindView(R.id.ll_zuke)
    LinearLayout ll_zuke;
    @BindView(R.id.tv_jairen)
    TextView tv_jairen;
    @BindView(R.id.tv_zuke)
    TextView tv_zuke;
    @BindView(R.id.iv_line1)
    ImageView iv_line1;
    @BindView(R.id.iv_line2)
    ImageView iv_line2;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_activity_keyaccredit3);
        ButterKnife.bind(this);
        initUI();
//        initData();
    }

//    private void initData() {
//        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                switch (scrollState) {
//                    //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
//                    case SCROLL_STATE_TOUCH_SCROLL:
//                        if (sets.size() > 0) {
//                            for (SwipeListLayout s : sets) {
//                                s.setStatus(SwipeListLayout.Status.Close, true);
//                                sets.remove(s);
//                            }
//                        }
//                        break;
//
//                }
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem,
//                                 int visibleItemCount, int totalItemCount) {
//
//            }
//        });
//    }

    private void initUI() {
//        ll_activity_wushouquan= (LinearLayout) findViewById(R.id.ll_activity_wushouquan);
//        ll_activity_wuwangluo= (LinearLayout) findViewById(R.id.ll_activity_wuwangluo);
//        mListView= (SwipeListView) findViewById(R.id.lv_keyaccredit);
        ib_fanghui= (LinearLayout) findViewById(R.id.ib_fanghui);
        ib_fanghui.setOnClickListener(this);
        ib_tianjia= (RelativeLayout) findViewById(R.id.ib_tianjia);
        ib_tianjia.setOnClickListener(this);

        //实例化UI
        mViewPager= (CustomViewPager)findViewById(R.id.pager);
//        mPagerTabStrip= (PagerTabStrip)view. findViewById(R.id.tab);
        //实例化Fragment管理者
        fm=getSupportFragmentManager();
        //实例化集合
        mTitleList=new ArrayList<>();
        mFragmentList=new ArrayList<>();
        //获取数据源
        getData();
        //实例化适配器

        mMyFragmentAdapter=new MyFragmentAdapter(fm,mFragmentList,mTitleList);
        //加载适配器
        mViewPager.setAdapter(mMyFragmentAdapter);
//        tabs= (PagerSlidingTabStrip)findViewById(R.id.tabs);
//        tabs.setViewPager(mViewPager);
//        tabs.setTextColor(0x333333);
        ll_jiaren.setOnClickListener(this);
        ll_zuke.setOnClickListener(this);


    }
//    public List<PermissionList.Obj> getData(){
//        mList=new ArrayList<>();
//        DialogUntil.showLoadingDialog(XYKeyAccredit.this,"正在加载",true);
//        String url= XY_Response.URL_FINDPERMISSIONS+"mobilePhone="+
//                SPManager.getInstance().getString("mobilePhone","0");
//        RequestCenter.findPermissions(url, new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                DialogUntil.closeLoadingDialog();
//                PermissionList permissionList= (PermissionList) responseObj;
//                if (permissionList.getCode().equals("1000")){
//                    mList= permissionList.getObj();
//                    if (mList.size()>0){
//                        XYKeyaccreditAdapter adapter=new XYKeyaccreditAdapter(XYKeyAccredit.this,mList);
//                        mListView.setAdapter(adapter);
////                        showShuJu();
//                    }else {
////                        showWuShuJu();
//                    }
//                }else {
////                    showWuShuJu();
//                    Toast.makeText(GuardApplication.getContent(),permissionList.getMsg(), Toast.LENGTH_LONG).show();
//
//                }
//            }
//            @Override
//            public void onFailure(Object reasonObj) {
//                    DialogUntil.closeLoadingDialog();
//            }
//        });
//        return mList;
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //返回的按键
            case R.id.ib_fanghui:
                finish();
                break;
            //添加的按键
            case R.id.ib_tianjia:
                Intent intent=new Intent(this,XY_AddKeyaccreditActivity.class);
                startActivity(intent);
                break;
            //显示家人的按键
            case R.id.ll_jiaren:
                showJiaRen();
                break;
            //显示租客的页面
            case R.id.ll_zuke:
                showZuKe();
                break;
        }
    }

    //显示租客的页面
    private void showZuKe() {
        mViewPager.setCurrentItem(1);
        tv_jairen.setTextColor(0xffaaaaaa);
        tv_zuke.setTextColor(0xff333333);
        iv_line2.setVisibility(View.VISIBLE);
        iv_line1.setVisibility(View.INVISIBLE);
    }
    //显示家人的页面
    private void showJiaRen() {
        mViewPager.setCurrentItem(0);
        tv_jairen.setTextColor(0xff333333);
        tv_zuke.setTextColor(0xffaaaaaa);
        iv_line1.setVisibility(View.VISIBLE);
        iv_line2.setVisibility(View.INVISIBLE);

    }

    //添加数据
    private void getData(){
        mTitleList.add(JIAREN);
        mTitleList.add(ZUKE);
        mFragmentList.add(new JiaRenFragment(JIAREN_TYPE));
        mFragmentList.add(new JiaRenFragment(ZUKE_TYPE));
    }
//    private void showShuJu(){
//        swipeRefreshLayout1.setVisibility(View.GONE);
//        swipeRefreshLayout2.setVisibility(View.GONE);
//        swipeRefreshLayout.setVisibility(View.VISIBLE);
//    }
//    private void showWuWangLuo(){
//        swipeRefreshLayout2.setVisibility(View.VISIBLE);
//        swipeRefreshLayout1.setVisibility(View.GONE);
//        swipeRefreshLayout.setVisibility(View.GONE);
//    }
//    private void showWuShuJu(){
//        swipeRefreshLayout2.setVisibility(View.GONE);
//        swipeRefreshLayout1.setVisibility(View.VISIBLE);
//        swipeRefreshLayout.setVisibility(View.GONE);
//    }
//
//    @Override
//    public void onRefresh() {
//        getData();
//        swipeRefreshLayout.setRefreshing(false);
//        swipeRefreshLayout1.setRefreshing(false);
//        swipeRefreshLayout2.setRefreshing(false);
//    }
}