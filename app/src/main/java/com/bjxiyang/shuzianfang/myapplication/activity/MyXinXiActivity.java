package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.dialog.SelectSexDialog;
import com.bjxiyang.shuzianfang.myapplication.luban.LuBan;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui2;
import com.bjxiyang.shuzianfang.myapplication.model.ImageUrl2;
import com.bjxiyang.shuzianfang.myapplication.model.UpdateImage;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response2;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.view.CircleImageView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class MyXinXiActivity extends SwipeBackActivity implements View.OnClickListener {
    private static final String WEIXIN_APP_ID="wxfe7d0b3660506256";
    private static final String WEIXIN_SCOPE="snsapi_userinfo";
    private static final String WEIXIN_STATE="wechat_sdk_update";
    private Map map=new HashMap();
    private SelectSexDialog dialog;
    private File mFile;
    private String picturePath;
    final int RESULT_LOAD_IMAGE=2;
    private CircleImageView iv_gerenxinxi_xiugai_touxiang;

    private TextView tv_gerenxinxi_xiugai_name;
    private TextView tv_gerenxinxi_xiugai_sex;

//    private LinearLayout changepassworldlativelayout;
    private TextView tv_gerenxinxi_xiugai_menjinrenzhenge;
    private LinearLayout ll_username;
    private LinearLayout ll_gerenxinxi_xiugai_touxiang;
    private LinearLayout ll_gerenxinxi_xiugai_sex;

    private RelativeLayout iv_gerenxinxi_xiugai_fanhui;
    private String mHeadPhotoUrl="";
    private String sex="0";
    private String mNickName="";
    private String mRealName="";
    @BindView(R.id.ll_shimingrenzheng)
    LinearLayout ll_shimingrenzheng;
    @BindView(R.id.ll_weixinhao)
    LinearLayout ll_weixinhao;
    @BindView(R.id.ll_qq)
    LinearLayout ll_qq;
    @BindView(R.id.tv_weixinhao)
    TextView tv_weixinhao;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_gerenxinxi_xiugai_realname)
    TextView tv_gerenxinxi_xiugai_realname;
    @BindView(R.id.ll_gerenxinxi_xiugai_realname)
    LinearLayout ll_gerenxinxi_xiugai_realname;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenxinxi_xiugai2);
        ButterKnife.bind(this);
        initUI();
        mHeadPhotoUrl=SPManager.getInstance().getString("headPhotoUrl","");
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (SPManager.getInstance().getString("c_memberId",null)!=null ||
                !SPManager.getInstance().getString("c_memberId","").equals("")) {
            sex=SPManager.getInstance().getString("sex","");
            mNickName=SPManager.getInstance().getString("nickName","");
            mRealName=SPManager.getInstance().getString("realname","");
            setTextForHTTP();
        }
        tv_phone.setText(SPManager.getInstance().getString("mobilePhone",""));
    }
    private void initUI() {
        ll_gerenxinxi_xiugai_realname.setOnClickListener(this);
        ll_shimingrenzheng.setOnClickListener(this);
        ll_weixinhao.setOnClickListener(this);
        ll_qq.setOnClickListener(this);
        ll_username= (LinearLayout) findViewById(R.id.ll_username);
        ll_gerenxinxi_xiugai_touxiang= (LinearLayout) findViewById(R.id.ll_gerenxinxi_xiugai_touxiang);
        ll_gerenxinxi_xiugai_sex= (LinearLayout) findViewById(R.id.ll_gerenxinxi_xiugai_sex);
        ll_username.setOnClickListener(this);
        ll_gerenxinxi_xiugai_touxiang.setOnClickListener(this);
        iv_gerenxinxi_xiugai_touxiang= (CircleImageView) findViewById(R.id.iv_gerenxinxi_xiugai_touxiang);
        tv_gerenxinxi_xiugai_name= (TextView) findViewById(R.id.tv_gerenxinxi_xiugai_name);
        tv_gerenxinxi_xiugai_sex=(TextView) findViewById(R.id.tv_gerenxinxi_xiugai_sex);
        iv_gerenxinxi_xiugai_fanhui= (RelativeLayout) findViewById(R.id.iv_gerenxinxi_xiugai_fanhui);
        iv_gerenxinxi_xiugai_fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setTextForHTTP(){
        if (SPManager.getInstance().getString("openid","").equals("")||SPManager.getInstance().getString("openid","")==null){
            tv_weixinhao.setText("未绑定");
        }else {
            tv_weixinhao.setText("已绑定");
        }
        if (mNickName!=null&&!mNickName.equals("")){
            tv_gerenxinxi_xiugai_name.setText(mNickName);
        }else {
            tv_gerenxinxi_xiugai_name.setText("未填写");
        }
        if (mRealName!=null&&!mRealName.equals("")) {
            tv_gerenxinxi_xiugai_realname.setText(mRealName);
        }else {
            tv_gerenxinxi_xiugai_realname.setText("未填写");
        }
//        if (sex!=null) {
//            if (sex.equals("0")) {
//                tv_gerenxinxi_xiugai_sex.setText("女");
//            } else {
//                tv_gerenxinxi_xiugai_sex.setText("男");
//            }
//        }else {
//            tv_gerenxinxi_xiugai_sex.setText("女");
//        }
        Log.i("LLLL",SPManager.getInstance().getString("headPhotoUrl",""));

        if (!SPManager.getInstance().getString("headPhotoUrl","").equals("")){
            ImageLoaderManager.getInstance(this).displayImage(
                    iv_gerenxinxi_xiugai_touxiang,SPManager.getInstance().getString("headPhotoUrl",""));

        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //修改用户名
            case R.id.ll_username:
                Intent intent=new Intent(MyXinXiActivity.this,XiuGaiNameActivity.class);
                intent.putExtra("name",mNickName);
                intent.putExtra("type",0);
                startActivityForResult(intent,1);
                break;
            //修改真实姓名
            case R.id.ll_gerenxinxi_xiugai_realname:
                Intent intent11=new Intent(MyXinXiActivity.this,XiuGaiNameActivity.class);
                intent11.putExtra("realname",mRealName);
                intent11.putExtra("type",1);
                startActivityForResult(intent11,3);
                break;
            //点击跳转到门禁认证
//            case R.id.tv_gerenxinxi_xiugai_menjinrenzhenge:
//                break;
            //修改密码
//            case R.id.changepassworldlativelayout:
//                Intent intent2=new Intent(MyXinXiActivity.this,RegisteredActivity.class);
//                intent2.putExtra("isXiuGai",true);
//                startActivity(intent2);
//                break;
            //修改头像
            case R.id.ll_gerenxinxi_xiugai_touxiang:
                Intent intent1 = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent1, RESULT_LOAD_IMAGE);
                break;
            //修改性别
//            case R.id.ll_gerenxinxi_xiugai_sex:
//                showSelectSexDialog();
//                break;
            //实名认证
            case R.id.ll_shimingrenzheng:
                break;
            //绑定微信
            case R.id.ll_weixinhao:
                if (!SPManager.getInstance().getString("openid","").equals("")) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MyXinXiActivity.this);
                    builder.setTitle("您确定要解除绑定吗？");
                    builder.setIcon(R.mipmap.app_logo);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                logOutWithWeiXin();
                        }
                    });
                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            builder.create();
                        }
                    });
                    builder.show();
                }else {
                    loginWithWeixin();
                }
                break;
            //绑定QQ
            case R.id.ll_qq:
                break;
        }
    }
    private IWXAPI mWeixinAPI;
    private void logOutWithWeiXin(){
        SPManager.getInstance().remove("openid");
        String url=XY_Response.URL_INIT_UPDATETHIRDINFO;
        RequestParams params=new RequestParams();
        params.put("cmemberId",SPManager.getInstance().getString("c_memberId","0"));
        params.put("type","0");
        params.put("openid","");
        params.put("picUrl","");
        params.put("nickname","");
        params.put("unionid","");
        RequestCenter.all(url, params, FanHui.class, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                tv_weixinhao.setText("未绑定");
            }

            @Override
            public void onFailure(Object reasonObj) {

            }
        });
    }
    private void loginWithWeixin() {
        if (mWeixinAPI == null) {
            mWeixinAPI = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID, false);
        }
        if (!mWeixinAPI.isWXAppInstalled()) {
            MyUntil.show(this,"请先安装微信");
            //提醒用户没有按照微信
            return;
        }
        mWeixinAPI.registerApp(WEIXIN_APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = WEIXIN_SCOPE;
        req.state = WEIXIN_STATE;
        boolean b=mWeixinAPI.sendReq(req);
        finish();
    }
    private void showSelectSexDialog(){
        dialog=new SelectSexDialog(MyXinXiActivity.this);
        dialog.setOnGetSelectTime(new SelectSexDialog.OnGetSelectTime() {
            @Override
            public void getSelectTime(String selectTime) {
                tv_gerenxinxi_xiugai_sex.setText(selectTime);
            }

            @Override
            public void getSelectTime_int(final int selectTime_int) {
                String url= XY_Response2.URL_UESRCENTER_UPDATEUSERINFO
                        +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                        +"&sex="+selectTime_int;
                RequestCenter.usercenter_updateUserInfo(url, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        FanHui2 fanHui2= (FanHui2) responseObj;
                        if (fanHui2.getCode()==1000){
                            SPManager.getInstance().putString("sex", String.valueOf(selectTime_int));
                            MyUntil.show(MyXinXiActivity.this,"修改成功");
                            dialog.dismiss();
                        }else {
                            MyUntil.show(MyXinXiActivity.this,fanHui2.getMsg());
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {

                    }
                });

            }
        });
        dialog.show();
    }
    /**
     * 用onActivityResult()接收传回的图像，当用户拍完照片，或者取消后，系统都会调用这个函数
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK) {
            final Uri uri1 = intent.getData();
            Bitmap bitmap=getBitmapFromUri(uri1);
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(uri1,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath= cursor.getString(columnIndex);
            mFile=new File(picturePath);
            LuBan.setOnGetImage(MyXinXiActivity.this, mFile, new LuBan.OnGetImage() {
                @Override
                public void getImage(File file) {
                    map.put("pic", file);
                    String url=XY_Response2.URL_UESRCENTER_UPDATEUSERINFO
                            +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null);
                    RequestCenter.uploadPictures2(url, map, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            ImageUrl2 fanHui2= (ImageUrl2) responseObj;
                            if (fanHui2.getCode()==1000){
//                                SPManager.getInstance().putString("headPhotoUrl",fanHui2.getObj().getPicUrl());
                                String url1=XY_Response2.URL_UESRCENTER_UPDATEUSERPIC
                                        +"cmemberId="+ SPManager.getInstance().getString("c_memberId",null)
                                        +"&headPhotoUrl="+fanHui2.getObj().getPicUrl();
                                Log.i("LLLLL","执行到了这里");
                                RequestCenter.all(url1, UpdateImage.class, new DisposeDataListener() {
                                    @Override
                                    public void onSuccess(Object responseObj) {
                                        UpdateImage updateImage= (UpdateImage) responseObj;
                                        if (updateImage.getCode()==1000){
                                            MyUntil.show(MyXinXiActivity.this,"修改成功");
                                            ImageLoaderManager.getInstance(MyXinXiActivity.this)
                                                    .displayImage(iv_gerenxinxi_xiugai_touxiang, String.valueOf(uri1));
                                            SPManager.getInstance().putString("headPhotoUrl",updateImage.getObj().getHeadPhotoUrl());
                                        }

                                    }

                                    @Override
                                    public void onFailure(Object reasonObj) {
                                        MyUntil.show(MyXinXiActivity.this,"修改失败");
                                    }
                                });



                            }else {
                                MyUntil.show(MyXinXiActivity.this,fanHui2.getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {

                        }
                    });

                }
            });
            cursor.close();
            bitmap.recycle();


        }
        if (requestCode == 1 && resultCode == RESULT_OK) {
            mNickName = intent.getStringExtra("name");
            tv_gerenxinxi_xiugai_name.setText(mNickName);
        }
        if (requestCode == 3 && resultCode == RESULT_OK) {
            mRealName = intent.getStringExtra("realname");
            tv_gerenxinxi_xiugai_realname.setText(mRealName);
        }


    }
    private Bitmap getBitmapFromUri(Uri uri) {
        try
        {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            return bitmap;
        }
        catch (Exception e)
        {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

}
