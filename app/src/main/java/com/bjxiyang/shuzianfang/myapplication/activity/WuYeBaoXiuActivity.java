package com.bjxiyang.shuzianfang.myapplication.activity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.luban.LuBan;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui2;
import com.bjxiyang.shuzianfang.myapplication.model.ImageUrl;
import com.bjxiyang.shuzianfang.myapplication.model.ImageUrl2;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response2;
import com.bjxiyang.shuzianfang.myapplication.ui.activity.MySwipeBackActivity;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.view.MyDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lidroid.xutils.bitmap.core.BitmapDecoder.calculateInSampleSize;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class WuYeBaoXiuActivity extends MySwipeBackActivity implements View.OnClickListener{

    private static final int RESULT_LOAD_IMAGE_ONE=1;
    private static final int RESULT_LOAD_IMAGE_TWO=2;
    private static final int RESULT_LOAD_IMAGE_THREE=3;
    private static final int RESULT_LOAD_IMAGE_FOUR=4;
    private Map map=new HashMap<>();
    private List<File> imageList=new ArrayList<>();

    private String imageUrl1;
    private String imageUrl2;
    private String imageUrl3;
    private String imageUrl4;
    private String resultimageUrl1;
    private String resultimageUrl2;
    private String resultimageUrl3;
    private String resultimageUrl4;



    private String yijian;
    private String phone;

    private File mFile;
    private String picturePath;
    private List<String> mList=new ArrayList();
    private RelativeLayout iv_baoxiufuwu_fanhui;
    private EditText et_fankuiyijian2;
    private EditText et_fankuiyijian_lianxifangshi;
    private ImageView iv_yijianfankui_tianjiatupian1,
            iv_yijianfankui_tianjiatupian2,
            iv_yijianfankui_tianjiatupian3,
            iv_yijianfankui_tianjiatupian4,
            iv_yijianfankui_tijiaofankui,
            iv_delete1,
            iv_delete2,
            iv_delete3,
            iv_delete4;
    private String responseImgList;
    private RelativeLayout rl_lishibaoxiu;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baoxiufuwu);
        initUI();
        initData();
    }

    private void initData() {
        map=new HashMap();
    }

    private void initUI() {
        rl_lishibaoxiu= (RelativeLayout) findViewById(R.id.rl_lishibaoxiu);
        rl_lishibaoxiu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUntil.mStartActivity(WuYeBaoXiuActivity.this,BaoXiuLiShiActivity.class);
            }
        });
        iv_baoxiufuwu_fanhui= (RelativeLayout) findViewById(R.id.iv_baoxiufuwu_fanhui);
        et_fankuiyijian2= (EditText) findViewById(R.id.et_fankuiyijian2);
        et_fankuiyijian_lianxifangshi=
                (EditText) findViewById(R.id.et_fankuiyijian_lianxifangshi);

        iv_yijianfankui_tianjiatupian1=
                (ImageView) findViewById(R.id.iv_yijianfankui_tianjiatupian1);
        iv_yijianfankui_tianjiatupian2=
                (ImageView) findViewById(R.id.iv_yijianfankui_tianjiatupian2);
        iv_yijianfankui_tianjiatupian3=
                (ImageView) findViewById(R.id.iv_yijianfankui_tianjiatupian3);
        iv_yijianfankui_tianjiatupian4=
                (ImageView) findViewById(R.id.iv_yijianfankui_tianjiatupian4);
        iv_yijianfankui_tijiaofankui=
                (ImageView) findViewById(R.id.iv_yijianfankui_tijiaofankui);
        iv_delete1= (ImageView) findViewById(R.id.iv_delete1);
        iv_delete2= (ImageView) findViewById(R.id.iv_delete2);
        iv_delete3= (ImageView) findViewById(R.id.iv_delete3);
        iv_delete4= (ImageView) findViewById(R.id.iv_delete4);

        iv_baoxiufuwu_fanhui.setOnClickListener(this);
        iv_yijianfankui_tianjiatupian1.setOnClickListener(this);
        iv_yijianfankui_tianjiatupian2.setOnClickListener(this);
        iv_yijianfankui_tianjiatupian3.setOnClickListener(this);
        iv_yijianfankui_tianjiatupian4.setOnClickListener(this);
        iv_yijianfankui_tijiaofankui.setOnClickListener(this);
        iv_delete1.setOnClickListener(this);
        iv_delete2.setOnClickListener(this);
        iv_delete3.setOnClickListener(this);
        iv_delete4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_baoxiufuwu_fanhui:
                finish();
                break;
            case R.id.iv_yijianfankui_tianjiatupian1:
                selectImage(RESULT_LOAD_IMAGE_ONE);
                break;
            case R.id.iv_yijianfankui_tianjiatupian2:
                selectImage(RESULT_LOAD_IMAGE_TWO);
                break;
            case R.id.iv_yijianfankui_tianjiatupian3:
                selectImage(RESULT_LOAD_IMAGE_THREE);
                break;
            case R.id.iv_yijianfankui_tianjiatupian4:
                selectImage(RESULT_LOAD_IMAGE_FOUR);
                break;
            case R.id.iv_delete1:
                delete1();
                break;
            case R.id.iv_delete2:
                delete2();
                break;
            case R.id.iv_delete3:
                delete3();
                break;
            case R.id.iv_delete4:
                delete4();
                break;


            //提交反馈按钮
            case R.id.iv_yijianfankui_tijiaofankui:



                yijian= String.valueOf(et_fankuiyijian2.getText());
                phone= String.valueOf(et_fankuiyijian_lianxifangshi.getText());


                if (phone==null||phone.equals("")){
                    MyUntil.show(WuYeBaoXiuActivity.this,"请输入报修内容");
                    break;
                }
//                if(imageUrl1==null||imageUrl1.equals("")){
//                    MyUntil.show(WuYeBaoXiuActivity.this,"请添加图片");
//                    break;
//                }
                if (imageList.size()>0) {
                    for (int i = 0; i < imageList.size(); i++) {
                        map.put("pic", imageList.get(i));
                        String url1 = XY_Response2.URL_UESRCENTER_UPDATEUSERINFO
                                + "cmemberId=" + SPManager.getInstance().getString("c_memberId", "");
                        final int finalI = i;
                        RequestCenter.uploadPictures2(url1, map, new DisposeDataListener() {
                            @Override
                            public void onSuccess(Object responseObj) {
                                ImageUrl2 imageUrl2 = (ImageUrl2) responseObj;
                                if (imageUrl2.getCode() == 1000) {
                                    mList.add(imageUrl2.getObj().getPicUrl());
                                }
                                Log.i("LLL",imageList.size()+"---"+finalI);
                                if (imageList.size() == mList.size()) {
                                    Log.i("LLL","执行到了这里");
                                    String url = XY_Response.URL_USERCENTER_PROPERTYREPAIR;
                                    RequestParams params=new RequestParams();
                                    params.put("cmemberId",SPManager.getInstance().getString("c_memberId","0"));

                                    params.put("communityId",SPManager.getInstance().getInt("communityId",0)+"");
                                    params.put("nperId",SPManager.getInstance().getInt("nperId",0)+"");
                                    params.put("floorId",SPManager.getInstance().getInt("floorId",0)+"");
                                    params.put("unitId",SPManager.getInstance().getInt("unitId", 0) +"");
                                    params.put("doorId",SPManager.getInstance().getInt("doorId",0)+"");
                                    params.put("contactWay",phone);
                                    params.put("repairContent",yijian);
                                    String imageUrl="";
                                    for (int i=0;i<mList.size();i++){
                                        if (i==mList.size()-1) {
                                            imageUrl+=mList.get(i);
                                        }else {
                                            imageUrl+=mList.get(i)+";";
                                        }
                                    }
                                    params.put("repairPic", imageUrl);

                                    RequestCenter.register(url,params, new DisposeDataListener() {
                                        @Override
                                        public void onSuccess(Object responseObj) {
                                            DialogUntil.closeLoadingDialog();
                                            FanHui fanHui = (FanHui) responseObj;
                                            if (fanHui.getCode().equals("1000")) {
                                                Toast.makeText(WuYeBaoXiuActivity.this, "提交成功", Toast.LENGTH_LONG).show();
                                                finish();
                                            } else {
                                                Toast.makeText(WuYeBaoXiuActivity.this, fanHui.getMsg(), Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Object reasonObj) {
                                            DialogUntil.closeLoadingDialog();
                                            MyDialog.showDialog(WuYeBaoXiuActivity.this, "请检查网络");
                                        }
                                    });
                                }

                            }

                            @Override
                            public void onFailure(Object reasonObj) {

                            }
                        });
                    }
                }else {

                    String url = XY_Response.URL_USERCENTER_PROPERTYREPAIR;
                    RequestParams params=new RequestParams();
                    params.put("cmemberId",SPManager.getInstance().getString("c_memberId","0"));

                    params.put("communityId",SPManager.getInstance().getInt("communityId",0)+"");
                    params.put("nperId",SPManager.getInstance().getInt("nperId",0)+"");
                    params.put("floorId",SPManager.getInstance().getInt("floorId",0)+"");
                    params.put("unitId",SPManager.getInstance().getInt("unitId",0)+"");
                    params.put("doorId",SPManager.getInstance().getInt("doorId",0)+"");
                    params.put("contactWay",phone);
                    params.put("repairContent",yijian);
                    params.put("repairPic", "");
                    RequestCenter.register(url, params,new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            DialogUntil.closeLoadingDialog();
                            FanHui fanHui = (FanHui) responseObj;
                            if (fanHui.getCode().equals("1000")) {
                                Toast.makeText(WuYeBaoXiuActivity.this, "提交成功", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(WuYeBaoXiuActivity.this, fanHui.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {
                            DialogUntil.closeLoadingDialog();
                            MyDialog.showDialog(WuYeBaoXiuActivity.this, "请检查网络");
                        }
                    });
                }

//                RequestCenter.register(url,map,new DisposeDataListener() {
//                    @Override
//                    public void onSuccess(Object responseObj) {
//                        DialogUntil.closeLoadingDialog();
//                        FanHui fanHui= (FanHui) responseObj;
//                        if (fanHui.getCode().equals("1000")){
//                            Toast.makeText(WuYeBaoXiuActivity.this,"提交成功",Toast.LENGTH_LONG).show();
//                            finish();
//                        }else {
//                            Toast.makeText(WuYeBaoXiuActivity.this,fanHui.getMsg(),Toast.LENGTH_LONG).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Object reasonObj) {
//                        DialogUntil.closeLoadingDialog();
//                        MyDialog.showDialog(WuYeBaoXiuActivity.this,"请检查网络");
//                    }
//                });
                break;

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && null != data){
            final Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath= cursor.getString(columnIndex);
            mFile=new File(picturePath);

            cursor.close();
            switch (requestCode){
                case RESULT_LOAD_IMAGE_ONE:
                    imageUrl1=String.valueOf(selectedImage);
                    iv_delete1.setVisibility(View.VISIBLE);
                    LuBan.setOnGetImage(WuYeBaoXiuActivity.this, mFile, new LuBan.OnGetImage() {
                        @Override
                        public void getImage(File file) {
                            imageList.add(mFile);
                            ImageLoaderManager.getInstance(WuYeBaoXiuActivity.this)
                                    .displayImage(iv_yijianfankui_tianjiatupian1, String.valueOf(selectedImage));
                            iv_yijianfankui_tianjiatupian2.setVisibility(View.VISIBLE);
                        }
                    });

                    break;
                case RESULT_LOAD_IMAGE_TWO:
                    imageUrl2=String.valueOf(selectedImage);
                    iv_delete2.setVisibility(View.VISIBLE);
                    LuBan.setOnGetImage(WuYeBaoXiuActivity.this, mFile, new LuBan.OnGetImage() {
                        @Override
                        public void getImage(File file) {
                            imageList.add(mFile);
                            ImageLoaderManager.getInstance(WuYeBaoXiuActivity.this)
                                    .displayImage(iv_yijianfankui_tianjiatupian2,String.valueOf(selectedImage));
                            iv_yijianfankui_tianjiatupian3.setVisibility(View.VISIBLE);

                        }
                    });

                    break;
                case RESULT_LOAD_IMAGE_THREE:
                    imageUrl3=String.valueOf(selectedImage);
                    iv_delete3.setVisibility(View.VISIBLE);
                    LuBan.setOnGetImage(WuYeBaoXiuActivity.this, mFile, new LuBan.OnGetImage() {
                        @Override
                        public void getImage(File file) {
                            imageList.add(mFile);
                            ImageLoaderManager.getInstance(WuYeBaoXiuActivity.this)
                                    .displayImage(iv_yijianfankui_tianjiatupian3,String.valueOf(selectedImage));
                            iv_yijianfankui_tianjiatupian4.setVisibility(View.VISIBLE);
                        }
                    });

                    break;
                case RESULT_LOAD_IMAGE_FOUR:
                    imageUrl4=String.valueOf(selectedImage);
                    iv_delete4.setVisibility(View.VISIBLE);
                    LuBan.setOnGetImage(WuYeBaoXiuActivity.this, mFile, new LuBan.OnGetImage() {
                        @Override
                        public void getImage(File file) {
                            imageList.add(mFile);
                            ImageLoaderManager.getInstance(WuYeBaoXiuActivity.this)
                                    .displayImage(iv_yijianfankui_tianjiatupian4,String.valueOf(selectedImage));
                            iv_yijianfankui_tianjiatupian4.setVisibility(View.VISIBLE);
                        }
                    });

                    break;
            }
        }
    }

    private void selectImage(int code){
        Intent i = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, code);
    }
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, 640, 960);
        options.inJustDecodeBounds = false;
        Bitmap localBitmap1 = BitmapFactory.decodeFile(filePath, options);
        return localBitmap1;
    }
    private void delete1(){
        imageList.remove(0);
        if(imageUrl4!=null){
            imageUrl1=imageUrl2;
            imageUrl2=imageUrl3;
            imageUrl3=imageUrl4;
            imageUrl4=null;
            iv_delete4.setVisibility(View.GONE);
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian1,imageUrl1);
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian2,imageUrl2);
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian3,imageUrl3);

            iv_yijianfankui_tianjiatupian4.setImageResource(R.drawable.i_icon_dianjiatupian);
        }else if (imageUrl3!=null){
            imageUrl1=imageUrl2;
            imageUrl2=imageUrl3;
            imageUrl3=null;
            iv_delete3.setVisibility(View.GONE);
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian1,imageUrl1);
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian2,imageUrl2);
            iv_yijianfankui_tianjiatupian4.setVisibility(View.GONE);
            iv_yijianfankui_tianjiatupian3.setImageResource(R.drawable.i_icon_dianjiatupian);

        }else if (imageUrl2!=null){
            iv_delete2.setVisibility(View.GONE);
            imageUrl1=imageUrl2;
            imageUrl2=null;
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian1,imageUrl1);
            iv_yijianfankui_tianjiatupian3.setVisibility(View.GONE);
            iv_yijianfankui_tianjiatupian2.setImageResource(R.drawable.i_icon_dianjiatupian);
        }else {
            imageUrl1=null;
            iv_delete1.setVisibility(View.GONE);
            iv_yijianfankui_tianjiatupian2.setVisibility(View.GONE);
            iv_yijianfankui_tianjiatupian1.setImageResource(R.drawable.i_icon_dianjiatupian);
        }
    }
    private void delete2(){
        imageList.remove(1);
        if (imageUrl4!=null){
            imageUrl2=imageUrl3;
            imageUrl3=imageUrl4;
            imageUrl4=null;
            iv_delete4.setVisibility(View.GONE);

            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian2,imageUrl2);
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian3,imageUrl3);
            iv_yijianfankui_tianjiatupian4.setImageResource(R.drawable.i_icon_dianjiatupian);
        }else if (imageUrl3!=null){
            imageUrl2=imageUrl3;
            imageUrl3=null;
            iv_delete3.setVisibility(View.GONE);

            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian2,imageUrl2);
            iv_yijianfankui_tianjiatupian4.setVisibility(View.GONE);
//            iv_yijianfankui_tianjiatupian2.setVisibility(View.GONE);
            iv_yijianfankui_tianjiatupian3.setImageResource(R.drawable.i_icon_dianjiatupian);
        }else {
            imageUrl2=null;
            iv_delete2.setVisibility(View.GONE);
            iv_yijianfankui_tianjiatupian3.setVisibility(View.GONE);
            iv_yijianfankui_tianjiatupian2.setImageResource(R.drawable.i_icon_dianjiatupian);
        }
    }
    private void delete3(){
        imageList.remove(2);
//        imageUrl3=imageUrl4;
//        imageUrl4=null;
        if (imageUrl4!=null){
            imageUrl3=imageUrl4;
            imageUrl4=null;
            iv_delete4.setVisibility(View.GONE);
            ImageLoaderManager.getInstance(this)
                    .displayImage(iv_yijianfankui_tianjiatupian3,imageUrl3);
//            iv_yijianfankui_tianjiatupian4.setVisibility(View.GONE);
            iv_yijianfankui_tianjiatupian4.setImageResource(R.drawable.i_icon_dianjiatupian);
        }else {
            imageUrl3=null;
            iv_yijianfankui_tianjiatupian4.setVisibility(View.GONE);
            iv_delete3.setVisibility(View.GONE);
            iv_yijianfankui_tianjiatupian3.setImageResource(R.drawable.i_icon_dianjiatupian);
        }
    }
    private void delete4(){
        imageList.remove(3);
        imageUrl4=null;
        iv_delete4.setVisibility(View.GONE);
        iv_yijianfankui_tianjiatupian4.setImageResource(R.drawable.i_icon_dianjiatupian);
    }
    private void shangchuanImage() {
        DialogUntil.showLoadingDialog(WuYeBaoXiuActivity.this,"正在提交",false);

        map=new HashMap();
        map.put("imgList",imageList);
        String imageUrl = XY_Response2.URL_NEIGHBOR_ADDPARTYIMG + "cmemberId=" +
                SPManager.getInstance().getString("c_memberId", null);
        RequestCenter.neighbor_addpartyimg(imageUrl, map, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {

                ImageUrl image = (ImageUrl) responseObj;
                if (image.getCode()==1000){
                    List imgListResult= image.getResult();

                    for (int i=0;i<imgListResult.size();i++){
                        if (i==0){
                            resultimageUrl1=imgListResult.get(0).toString();
                            responseImgList=resultimageUrl1;
                        }else if (i==1){
                            resultimageUrl2=imgListResult.get(1).toString();
                            responseImgList=responseImgList+";"+resultimageUrl2;
                        }else if (i==2){
                            resultimageUrl3=imgListResult.get(2).toString();
                            responseImgList=responseImgList+";"+resultimageUrl3;
                        }
                    }


                    String addHuoDongUrl=XY_Response.URL_USERCENTER_PROPERTYREPAIR
                            +"cmemberId="+SPManager.getInstance().getString("c_memberId",null)
                            +"&communityId="+SPManager.getInstance().getInt("communityId",0)                  // 报名截止时间
                            +"&nperId="+SPManager.getInstance().getInt("nperId_one",0)           // 活动开始时间
                            +"&floorId="+SPManager.getInstance().getInt("floorId_one",0)               // 活动结束时间
                            +"&unitId="+SPManager.getInstance().getInt("unitId_one",0)              // 活动描述
                            +"&doorId="+SPManager.getInstance().getInt("doorId_one",0)      // 集合地点
                            +"&contactWay=" +phone             //目的地
                            +"&repairContent="+yijian                     //费用类型 0：我买单 1：免费 2：线下AA
                            +"&repairPic="+responseImgList;          //约伴人数
                    RequestCenter.neighbor_addparty(addHuoDongUrl, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            DialogUntil.closeLoadingDialog();
                            FanHui2 fanhui= (FanHui2) responseObj;
                            if (fanhui.getCode()==1000){

                                finish();
                            }else {
                                MyUntil.show(WuYeBaoXiuActivity.this,fanhui.getMsg());
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {
                            DialogUntil.closeLoadingDialog();
                        }
                    });

                }

            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
            }
        });


    }
}
