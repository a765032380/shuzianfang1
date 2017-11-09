package com.bjxiyang.shuzianfang.myapplication.ui.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.activity.SheZhiActivity;
import com.bjxiyang.shuzianfang.myapplication.activity.ShengHuoJiaoFeiActivity;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.GuangGao;
import com.bjxiyang.shuzianfang.myapplication.model.Users1;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.step.UpdateUiCallBack;
import com.bjxiyang.shuzianfang.myapplication.step.service.StepService;
import com.bjxiyang.shuzianfang.myapplication.step.utils.SharedPreferencesUtils;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.GetGuanggaoUrl;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.until.WechatShareUtil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.view.CircleImageView;
import com.library.viewspread.helper.BaseViewHelper;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Administrator on 2017/9/5 0005.
 *
 */

public class GeRenZhongXinActivity extends MySwipeBackActivity implements View.OnClickListener{


    protected static final String TRANSITION_DATA = "data";

    @BindView(R.id.ll_close)
    LinearLayout ll_close;
    @BindView(R.id.viv_touxiang)
    CircleImageView viv_touxiang;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.ll_gerenxinxi)
    LinearLayout ll_gerenxinxi;
    @BindView(R.id.ll_menjinjilu)
    LinearLayout ll_menjinjilu;
    @BindView(R.id.ll_yaoshishouquan)
    LinearLayout ll_yaoshishouquan;
    @BindView(R.id.ll_kefurexian)
    LinearLayout ll_kefurexian;
    @BindView(R.id.ll_yijianfankui)
    LinearLayout ll_yijianfankui;
    @BindView(R.id.ll_setting)
    LinearLayout ll_setting;
    @BindView(R.id.iv_xitongxiaoxi)
    ImageView iv_xitongxiaoxi;
    @BindView(R.id.ll_shenghuofuwu)
    LinearLayout ll_shenghuofuwu;
    @BindView(R.id.ll_yaoqinghaoyou)
    LinearLayout ll_yaoqinghaoyou;
    @BindView(R.id.home_sun)
    ImageView home_sun;
    @BindView(R.id.ll_yonghuzhinan)
    LinearLayout ll_yonghuzhinan;
    //开门次数
    @BindView(R.id.tv_kaimencishu)
    TextView tv_kaimencishu;
    //步数
    @BindView(R.id.tv_bushu)
    TextView tv_bushu;
    //消耗的卡路里
    @BindView(R.id.tv_kaluli)
    TextView tv_kaluli;

//    private StepArcView cc;
    private SharedPreferencesUtils sp;


    public static GeRenZhongXinActivity geRenZhongXinActivity;
    BaseViewHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenzhongxin4);
        ButterKnife.bind(this);
        geRenZhongXinActivity=this;
        switch (getIntent().getIntExtra("id",-1)){
            case R.id.lv_shezhi:
                startTranslation1();
                break;
        }
        initUI();
        initData();
        getData();

        getImageUrl();

//        setHongDian();
//        TransitionsHeleper.getInstance()
//                .setShowMethod(new ColorShowMethod(R.color.white,R.color.white) {
//                    @Override
//                    public void loadCopyView(InfoBean bean, ImageView copyView) {
//                        AnimatorSet set = new AnimatorSet();
//                        set.playTogether(
//                                ObjectAnimator.ofFloat(copyView,"rotation",0,180),
//                                ObjectAnimator.ofFloat(copyView, "scaleX", 1, 0),
//                                ObjectAnimator.ofFloat(copyView, "scaleY", 1, 0)
//                        );
//                        set.setInterpolator(new AccelerateInterpolator());
//                        set.setDuration(duration / 4 * 5).start();
//                    }
//
//                    @Override
//                    public void loadTargetView(InfoBean bean, ImageView targetView) {
//
//                    }
//
//                })
//                .show(this,null);
//


//        Intent intent = getIntent();
//        if (intent != null) {
////            tv.setText(intent.getStringExtra(TRANSITION_DATA));
//        }
//        TransitionsHeleper.getInstance()
//                .show(this, null);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!SPManager.getInstance().getString("headPhotoUrl","").equals("")) {
            ImageLoaderManager.getInstance(GeRenZhongXinActivity.this)
                    .displayImage(viv_touxiang,SPManager.getInstance().getString("headPhotoUrl",""));
        }
        getImageUrl();

    }

    private boolean isBind = false;
    private void initData() {
        sp = new SharedPreferencesUtils(this);
        //获取用户设置的计划锻炼步数，没有设置过的话默认7000
        String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
        //设置当前步数为0
        tv_bushu.setText("0");
//        cc.setCurrentCount(Integer.parseInt(planWalk_QTY), 0);

        setupService();
    }
    private void setupService() {
//        Intent intent = new Intent(this, StepService.class);
//        bindService(intent, conn, Context.BIND_AUTO_CREATE);
//        startService(intent);
    }

    //设置红点
    private void setHongDian(){
        if (SPManager.getInstance().getInt("xiaoxi",0)!=0){
            iv_xitongxiaoxi.setImageResource(R.drawable.b_btn_notice_pre);
        }else {
            iv_xitongxiaoxi.setImageResource(R.drawable.b_btn_notice);
        }
    }

    //初始化设置各种监听事件
    private void initUI() {

//        //将资源文件转化为Bitmap
//        Resources resources =getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.banner2);
//
//        //获取圆角图片
//        Bitmap roundBitmap = ImageUtil.getRoundedCornerBitmap(bitmap, 20.0f);
//        //这里可以让Bitmap再转化为Drawable
////      Drawable roundDrawable = new BitmapDrawable(roundBitmap);
////      Drawable reflectDrawable = new BitmapDrawable(reflectBitmap);
//
//        home_sun.setImageBitmap(roundBitmap);



        ll_yaoqinghaoyou.setOnClickListener(this);
        ll_shenghuofuwu.setOnClickListener(this);
        ll_yonghuzhinan.setOnClickListener(this);
        if (!SPManager.getInstance().getString("nickName","").equals("")){
            tv_phone.setText(SPManager.getInstance().getString("nickName",""));
        }else {
            tv_phone.setText(SPManager.getInstance().getString("mobilePhone",""));
        }




        ll_yaoshishouquan.setOnClickListener(this);
        ll_close.setOnClickListener(this);
        viv_touxiang.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        ll_gerenxinxi.setOnClickListener(this);
        ll_menjinjilu.setOnClickListener(this);
        ll_kefurexian.setOnClickListener(this);
        ll_yijianfankui.setOnClickListener(this);
        ll_setting.setOnClickListener(this);

        if (!SPManager.getInstance().getString("headPhotoUrl","").equals("")) {
            ImageLoaderManager.getInstance(this)
                    .displayImage(viv_touxiang, SPManager.getInstance().getString("headPhotoUrl", ""));
        }

    }

    //点击事件的监听回掉
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //关闭
            case R.id.ll_close:
//                startTranslation1();
                if (helper!=null && helper.isShowing()){
                    helper.backActivity(this);
                }else {
                    finish();
                }
//                finish();
                break;
            //头像
            case R.id.viv_touxiang:
                startIntent(com.bjxiyang.shuzianfang.myapplication.activity.MyXinXiActivity.class);
                break;
            //手机号
            case R.id.tv_phone:
                break;
            //个人信息
            case R.id.ll_gerenxinxi:
                startIntent(com.bjxiyang.shuzianfang.myapplication.activity.MyXinXiActivity.class);
                break;
            //门禁记录
            case R.id.ll_menjinjilu:
                startIntent(XYMenJinJiLuActivity.class);
                break;
            //客服热线
            case R.id.ll_kefurexian:

//                startIntent(MyKeFuActivity.class);
                Intent j = new Intent();
                j.setAction(Intent.ACTION_DIAL);
                j.setData(Uri.parse("tel:4000080828"));
                startActivity(j);
                break;
            //意见反馈
            case R.id.ll_yijianfankui:
                Intent intent2=new Intent(GeRenZhongXinActivity.this,YiJianFanKuiActivity.class);
                intent2.putExtra("type",1);
                startActivity(intent2);
//                startIntent(YiJianFanKuiActivity.class);
                break;
            //设置
            case R.id.ll_setting:
                startIntent(SheZhiActivity.class);
                break;
            //钥匙授权
            case R.id.ll_yaoshishouquan:
                startIntent(XYKeyAccredit.class);
                break;
            //用户指南
            case R.id.ll_yonghuzhinan:
                Intent intent=new Intent(GeRenZhongXinActivity.this,MyWebViewActivity.class);
                intent.putExtra("url","http://www.bjxiyang.com/guide");
                startActivity(intent);
                break;
            case R.id.ll_shenghuofuwu:
                startIntent(ShengHuoJiaoFeiActivity.class);
                break;
            case R.id.ll_yaoqinghaoyou:
                shareToWeChatWithWebpage(GeRenZhongXinActivity.this,"http://www.bjxiyang.com/share/","测试","我是测试数据",SendMessageToWX.Req.WXSceneSession);
                break;
        }
    }

    public void shareToWeChatWithWebpage(Context context, String url,
                                         String title, String description, int scene) {
        IWXAPI iwxapi = WXAPIFactory.createWXAPI(context, WechatShareUtil.WECHAT_APP_ID);

        if (!iwxapi.isWXAppInstalled()) {
            MyUntil.show(GeRenZhongXinActivity.this, "请先安装微信");
            return;
        }

        WXWebpageObject wxWebpageObject = new WXWebpageObject();
        wxWebpageObject.webpageUrl = url;

        WXMediaMessage wxMediaMessage = new WXMediaMessage(wxWebpageObject);
        wxMediaMessage.mediaObject = wxWebpageObject;
        wxMediaMessage.title = title;
        wxMediaMessage.description = description;
//        wxMediaMessage.thumbData =bmpToByteArray(BitmapFactory.decodeResource(context.getResources(), R.mipmap.logo_app), true);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wxMediaMessage;
        req.scene = scene;

        iwxapi.sendReq(req);
    }

    //跳转
    private void startIntent(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
    public void startTranslation1(){
        helper = new BaseViewHelper
                .Builder(GeRenZhongXinActivity.this)
                //.setEndView()//如果是两个切换的视图  这里设定最终显示的视图
                //.setTranslationView(viewById)//设置过渡视图
                .isFullWindow(true)//是否全屏显示
                .isShowTransition(false)//是否显示过渡动画
                .setDimColor(Color.WHITE)//遮罩颜色
                .setDimAlpha(205)//遮罩透明度
                .setTranslationX(0)//x轴平移
                .setRotation(360)//旋转
                .setScaleX(0)//x轴缩放
                .setScaleY(0)//y轴缩放
                .setTranslationY(0)//y轴平移
                .setDuration(500)//过渡时长
                .setInterpolator(new AccelerateDecelerateInterpolator())//设置插值器
                //设置监听
                .setOnAnimationListener(new BaseViewHelper.OnAnimationListener() {
                    @Override
                    public void onAnimationStartIn() {
                        Log.e("TAG","onAnimationStartIn");
                    }

                    @Override
                    public void onAnimationEndIn() {
                        Log.e("TAG","onAnimationEndIn");
                    }

                    @Override
                    public void onAnimationStartOut() {
                        Log.e("TAG","onAnimationStartOut");
                    }

                    @Override
                    public void onAnimationEndOut() {
                        Log.e("TAG","onAnimationEndOut");
                    }
                })
                .create();//开始动画
    }
    @Override
    public void onBackPressed() {

        if (helper!=null && helper.isShowing()){
            helper.backActivity(this);
        }else {
            super.onBackPressed();
        }
    }

    /**
     * 用于查询应用服务（application Service）的状态的一种interface，
     * 更详细的信息可以参考Service 和 context.bindService()中的描述，
     * 和许多来自系统的回调方式一样，ServiceConnection的方法都是进程的主线程中调用的。
     */
    ServiceConnection conn = new ServiceConnection() {
        /**
         * 在建立起于Service的连接时会调用该方法，目前Android是通过IBind机制实现与服务的连接。
         * @param name 实际所连接到的Service组件名称
         * @param service 服务的通信信道的IBind，可以通过Service访问对应服务
         */
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StepService stepService = ((StepService.StepBinder) service).getService();
            tv_bushu.setText(stepService.getStepCount()+"");
            tv_kaluli.setText(getKaLuli(stepService.getStepCount())+"千");
            //设置步数监听回调
            stepService.registerCallback(new UpdateUiCallBack() {
                @Override
                public void updateUi(int stepCount) {
                    String planWalk_QTY = (String) sp.getParam("planWalk_QTY", "7000");
//                    cc.setCurrentCount(Integer.parseInt(planWalk_QTY), stepCount);
                    tv_bushu.setText(stepCount+"");
                    tv_kaluli.setText(getKaLuli(stepCount)+"千");
                }
            });
        }

        /**
         * 当与Service之间的连接丢失的时候会调用该方法，
         * 这种情况经常发生在Service所在的进程崩溃或者被Kill的时候调用，
         * 此方法不会移除与Service的连接，当服务重新启动的时候仍然会调用 onServiceConnected()。
         * @param name 丢失连接的组件名称
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    DecimalFormat df = new DecimalFormat("0.00");
    private String getKaLuli(int number){
        Double kaluli=number*0.8*60*1.036/1000;
        return df.format(kaluli);
    }

    private void getData(){
        String url= XY_Response.URL_GETUSERINFO+"cmemberId="+
                SPManager.getInstance().getString("c_memberId",null);
        RequestCenter.getUserInfo(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                DialogUntil.closeLoadingDialog();
                Users1 users= (Users1) responseObj;
                if (users.getCode()==1000){
                    tv_kaimencishu.setText(users.getObj().getOpendoorCount()+"");
                    ImageLoaderManager.getInstance(GeRenZhongXinActivity.this)
                            .displayImage(viv_touxiang,users.getObj().getHeadPhotoUrl());
                    SPManager.getInstance().putString("headPhotoUrl",users.getObj().getHeadPhotoUrl());




                }

            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }

    public  byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    private void getImageUrl(){
        GetGuanggaoUrl.setOnGetImageUrl(4, new GetGuanggaoUrl.OnGetImageUrl() {
            @Override
            public void getImageUrl(final GuangGao.ObjBean.BannerObjBean.AdInfoBean adInfo) {

                if (adInfo==null||adInfo.equals("")){
                    home_sun.setVisibility(View.INVISIBLE);

                }else {
                    home_sun.setVisibility(View.VISIBLE);
                    ImageLoaderManager.getInstance(GeRenZhongXinActivity.this)
                            .displayImage(home_sun,adInfo.getImageurl());

                    home_sun.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(GeRenZhongXinActivity.this,MyWebViewActivity.class);
                            intent.putExtra("url",adInfo.getUrladdress());
                            startActivity(intent);
                        }
                    });
                }



            }
        });



    }

}
