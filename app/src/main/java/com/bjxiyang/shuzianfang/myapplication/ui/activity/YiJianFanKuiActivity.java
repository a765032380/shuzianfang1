package com.bjxiyang.shuzianfang.myapplication.ui.activity;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.imoocsdk.imageloader.ImageLoaderManager;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.activity.FanKuiLiShiActivtiy;
import com.bjxiyang.shuzianfang.myapplication.luban.LuBan;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui;
import com.bjxiyang.shuzianfang.myapplication.model.ImageUrl2;
import com.bjxiyang.shuzianfang.myapplication.model.SelectPlot;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response2;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.until.UserType;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.view.MyDialog;
import com.bjxiyang.shuzianfang.myapplication.widgets.CommonActionSheetDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.lidroid.xutils.bitmap.core.BitmapDecoder.calculateInSampleSize;

/**
 * Created by Administrator on 2017/6/1 0001.
 */

public class YiJianFanKuiActivity extends MySwipeBackActivity implements View.OnClickListener{

    private static final int RESULT_LOAD_IMAGE_ONE=1;
    private static final int RESULT_LOAD_IMAGE_TWO=2;
    private static final int RESULT_LOAD_IMAGE_THREE=3;
    private static final int RESULT_LOAD_IMAGE_FOUR=4;
    private Map map=new HashMap<>();
    private List<String> mList=new ArrayList();
    private List<SelectPlot.Obj> mList1=new ArrayList();


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

    private RelativeLayout iv_yijianfankui_fanhui,rl_lishifankui;
    private EditText et_fankuiyijian;
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
    private LinearLayout ll_xuanzewuye;
    private TextView tv_xiaoqu,tv_title;
    private int type=0;
    public int communityId=0;
    public CommonActionSheetDialog commonActionSheetDialog;
    private int nperId;
    private int floorId;
    private int unitId;
    private int doorId;
    private int roleType= UserType.USER_FOLK;
    private void initWuYe(){
        tv_xiaoqu.setText(SPManager.getInstance().getString("test_men","请选择要反馈的物业"));
        ll_xuanzewuye.setVisibility(View.VISIBLE);
        communityId=SPManager.getInstance().getInt("communityId",0);

    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yijianfankui4);
        initUI();
        type=getIntent().getIntExtra("type",0);
        if (type!=1){
            initWuYe();
            tv_title.setText("物业反馈");
            rl_lishifankui.setVisibility(View.VISIBLE);
            rl_lishifankui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyUntil.mStartActivity(YiJianFanKuiActivity.this, FanKuiLiShiActivtiy.class);
                }
            });
        }else {
            tv_title.setText("意见反馈");
            rl_lishifankui.setVisibility(View.INVISIBLE);
        }
        initData();
    }

    private void initData() {
        map=new HashMap();
    }

    private void initUI() {
        rl_lishifankui= (RelativeLayout) findViewById(R.id.rl_lishifankui);

        tv_title= (TextView) findViewById(R.id.tv_title);
        tv_xiaoqu= (TextView) findViewById(R.id.tv_xiaoqu);
        ll_xuanzewuye= (LinearLayout) findViewById(R.id.ll_xuanzewuye);
        ll_xuanzewuye.setOnClickListener(this);
        iv_yijianfankui_fanhui= (RelativeLayout) findViewById(R.id.iv_yijianfankui_fanhui);
        et_fankuiyijian= (EditText) findViewById(R.id.et_fankuiyijian);
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

        iv_yijianfankui_fanhui.setOnClickListener(this);
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
            case R.id.iv_yijianfankui_fanhui:
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
            case R.id.ll_xuanzewuye:
                getData();
                break;


            //提交反馈按钮
            case R.id.iv_yijianfankui_tijiaofankui:
                yijian= String.valueOf(et_fankuiyijian.getText());
                phone= String.valueOf(et_fankuiyijian_lianxifangshi.getText());
                if (yijian.length()>0){
                    MyUntil.show(YiJianFanKuiActivity.this,"请输入您的意见");
                    return;
                }

                DialogUntil.showLoadingDialog(this,"正在提交",false);
//                if(imageList.size()>0){
//                    map.put("suggestPic",imageList);
//                }else {
//                    map.put("suggestPic","");
//                }
                if (imageList.size()>0) {
                    for (int i = 0; i < imageList.size(); i++) {
                        map.put("pic", imageList.get(i));
                        String url1 = XY_Response2.URL_UESRCENTER_UPDATEUSERINFO
                                + "cmemberId=" + SPManager.getInstance().getString("c_memberId", null);
                        final int finalI = i;
                        RequestCenter.uploadPictures2(url1, map, new DisposeDataListener() {
                            @Override
                            public void onSuccess(Object responseObj) {
                                ImageUrl2 imageUrl2 = (ImageUrl2) responseObj;
                                if (imageUrl2.getCode() == 1000) {
                                    mList.add(imageUrl2.getObj().getPicUrl());
                                }
                                if (imageList.size() == mList.size()) {
                                    String url;
                                    RequestParams params;
                                    if (type==1) {
                                        url= XY_Response.URL_USERSUGGEST;
                                        params= new RequestParams();
                                        params.put("mobilePhone", SPManager.getInstance().getString("mobilePhone", ""));
                                        params.put("suggestContent", yijian);
                                        String imageUrl = "";
                                        for (int i = 0; i < mList.size(); i++) {
                                            if (i == mList.size() - 1) {
                                                imageUrl += mList.get(i);
                                            } else {
                                                imageUrl += mList.get(i) + ";";
                                            }
                                        }
                                        params.put("suggestPic", imageUrl);
                                        params.put("contactWay", phone);
                                    }else {
                                        url= XY_Response.URL_COMPLAINTPROPERTY;
                                        params= new RequestParams();
                                        params.put("cmemberId", SPManager.getInstance().getString("c_memberId", ""));
                                        params.put("content", yijian);
                                        String imageUrl = "";
                                        for (int i = 0; i < mList.size(); i++) {
                                            if (i == mList.size() - 1) {
                                                imageUrl += mList.get(i);
                                            } else {
                                                imageUrl += mList.get(i) + ";";
                                            }
                                        }
                                        params.put("piclist", imageUrl);
//                                        params.put("contactWay", phone);
                                        params.put("communityId", communityId+"");
                                    }
                                    Log.i("LLLL",url);
                                    RequestCenter.register(url,params, new DisposeDataListener() {
                                        @Override
                                        public void onSuccess(Object responseObj) {
                                            DialogUntil.closeLoadingDialog();
                                            FanHui fanHui = (FanHui) responseObj;
                                            mList.clear();
                                            if (fanHui.getCode().equals("1000")) {

                                                Toast.makeText(YiJianFanKuiActivity.this, "提交成功", Toast.LENGTH_LONG).show();
                                                finish();
                                            } else {
                                                Toast.makeText(YiJianFanKuiActivity.this, fanHui.getMsg(), Toast.LENGTH_LONG).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Object reasonObj) {
                                            mList.clear();
                                            DialogUntil.closeLoadingDialog();
                                            MyDialog.showDialog(YiJianFanKuiActivity.this, "请检查网络");
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
                    String url;
                    RequestParams params;
                    if (type==1) {
                        url= XY_Response.URL_USERSUGGEST;
                        params= new RequestParams();
                        params.put("mobilePhone", SPManager.getInstance().getString("mobilePhone", ""));
                        params.put("suggestContent", yijian);
//                        String imageUrl = "";
//                        for (int i = 0; i < mList.size(); i++) {
//                            if (i == mList.size() - 1) {
//                                imageUrl += mList.get(i);
//                            } else {
//                                imageUrl += mList.get(i) + ";";
//                            }
//                        }
//                        params.put("suggestPic", imageUrl);
                        params.put("contactWay", phone);
                    }else {
                        url= XY_Response.URL_COMPLAINTPROPERTY;
                        params= new RequestParams();
                        params.put("cmemberId", SPManager.getInstance().getString("c_memberId", ""));
                        params.put("content", yijian);
//                        String imageUrl = "";
//                        for (int i = 0; i < mList.size(); i++) {
//                            if (i == mList.size() - 1) {
//                                imageUrl += mList.get(i);
//                            } else {
//                                imageUrl += mList.get(i) + ";";
//                            }
//                        }
//                        params.put("piclist", imageUrl);
//                        params.put("contactWay", phone);
                            params.put("communityId", communityId+"");
                    }
//                String url = XY_Response.URL_USERSUGGEST;
//                RequestParams params=new RequestParams();
//                params.put("mobilePhone",SPManager.getInstance().getString("mobilePhone",""));
//                params.put("suggestContent",yijian);
//                params.put("contactWay",phone);


                    RequestCenter.register(url,params, new DisposeDataListener() {
                        @Override
                        public void onSuccess(Object responseObj) {
                            DialogUntil.closeLoadingDialog();
                            FanHui fanHui = (FanHui) responseObj;
                            if (fanHui.getCode().equals("1000")) {
                                Toast.makeText(YiJianFanKuiActivity.this, fanHui.getMsg(), Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(YiJianFanKuiActivity.this, fanHui.getMsg(), Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Object reasonObj) {
                            DialogUntil.closeLoadingDialog();
                            MyDialog.showDialog(YiJianFanKuiActivity.this, "请检查网络");
                        }
                    });
                }
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
                    LuBan.setOnGetImage(YiJianFanKuiActivity.this, mFile, new LuBan.OnGetImage() {
                        @Override
                        public void getImage(File file) {
                            imageList.add(mFile);
                            ImageLoaderManager.getInstance(YiJianFanKuiActivity.this)
                                    .displayImage(iv_yijianfankui_tianjiatupian1, String.valueOf(selectedImage));
                            iv_yijianfankui_tianjiatupian2.setVisibility(View.VISIBLE);
                        }
                    });

                    break;
                case RESULT_LOAD_IMAGE_TWO:
                    imageUrl2=String.valueOf(selectedImage);
                    iv_delete2.setVisibility(View.VISIBLE);
                    LuBan.setOnGetImage(YiJianFanKuiActivity.this, mFile, new LuBan.OnGetImage() {
                        @Override
                        public void getImage(File file) {
                            imageList.add(mFile);
                            ImageLoaderManager.getInstance(YiJianFanKuiActivity.this)
                                    .displayImage(iv_yijianfankui_tianjiatupian2,String.valueOf(selectedImage));
                            iv_yijianfankui_tianjiatupian3.setVisibility(View.VISIBLE);

                        }
                    });

                    break;
                case RESULT_LOAD_IMAGE_THREE:
                    imageUrl3=String.valueOf(selectedImage);
                    iv_delete3.setVisibility(View.VISIBLE);
                    LuBan.setOnGetImage(YiJianFanKuiActivity.this, mFile, new LuBan.OnGetImage() {
                        @Override
                        public void getImage(File file) {
                            imageList.add(mFile);
                            ImageLoaderManager.getInstance(YiJianFanKuiActivity.this)
                                    .displayImage(iv_yijianfankui_tianjiatupian3,String.valueOf(selectedImage));
                            iv_yijianfankui_tianjiatupian4.setVisibility(View.VISIBLE);
                        }
                    });

                    break;
                case RESULT_LOAD_IMAGE_FOUR:
                    imageUrl4=String.valueOf(selectedImage);
                    iv_delete4.setVisibility(View.VISIBLE);
                    LuBan.setOnGetImage(YiJianFanKuiActivity.this, mFile, new LuBan.OnGetImage() {
                        @Override
                        public void getImage(File file) {
                            imageList.add(mFile);
                            ImageLoaderManager.getInstance(YiJianFanKuiActivity.this)
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
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
//    private void delete1(){
//        imageList.remove(0);
//        if(imageUrl4!=null){
//            imageUrl1=imageUrl2;
//            imageUrl2=imageUrl3;
//            imageUrl3=imageUrl4;
//            imageUrl4=null;
//            iv_delete4.setVisibility(View.GONE);
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian1,imageUrl1);
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian2,imageUrl2);
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian3,imageUrl3);
//
//            iv_yijianfankui_tianjiatupian4.setImageResource(R.drawable.i_icon_dianjiatupian);
//        }else if (imageUrl3!=null){
//            imageUrl1=imageUrl2;
//            imageUrl2=imageUrl3;
//            imageUrl3=null;
//            iv_delete3.setVisibility(View.GONE);
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian1,imageUrl1);
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian2,imageUrl2);
//            iv_yijianfankui_tianjiatupian4.setVisibility(View.GONE);
//            iv_yijianfankui_tianjiatupian3.setImageResource(R.drawable.i_icon_dianjiatupian);
//
//        }else if (imageUrl2!=null){
//            iv_delete2.setVisibility(View.GONE);
//            imageUrl1=imageUrl2;
//            imageUrl2=null;
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian1,imageUrl1);
//            iv_yijianfankui_tianjiatupian3.setVisibility(View.GONE);
//            iv_yijianfankui_tianjiatupian2.setImageResource(R.drawable.i_icon_dianjiatupian);
//        }else {
//            imageUrl1=null;
//            iv_delete1.setVisibility(View.GONE);
//            iv_yijianfankui_tianjiatupian2.setVisibility(View.GONE);
//            iv_yijianfankui_tianjiatupian1.setImageResource(R.drawable.i_icon_dianjiatupian);
//        }
//    }
//    private void delete2(){
//        imageList.remove(1);
//        if (imageUrl4!=null){
//            imageUrl2=imageUrl3;
//            imageUrl3=imageUrl4;
//            imageUrl4=null;
//            iv_delete3.setVisibility(View.GONE);
//
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian2,imageUrl2);
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian3,imageUrl3);
//            iv_yijianfankui_tianjiatupian4.setImageResource(R.drawable.i_icon_dianjiatupian);
//        }else if (imageUrl3!=null){
//            imageUrl2=imageUrl3;
//            imageUrl3=null;
//            iv_delete3.setVisibility(View.GONE);
//
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian2,imageUrl2);
//            iv_yijianfankui_tianjiatupian2.setVisibility(View.GONE);
//            iv_yijianfankui_tianjiatupian3.setImageResource(R.drawable.i_icon_dianjiatupian);
//        }else {
//            imageUrl2=null;
//            iv_delete2.setVisibility(View.GONE);
//            iv_yijianfankui_tianjiatupian2.setImageResource(R.drawable.i_icon_dianjiatupian);
//        }
//    }
//    private void delete3(){
//        imageList.remove(2);
//        imageUrl3=imageUrl4;
//        imageUrl4=null;
//        if (imageUrl4!=null){
//            imageUrl3=imageUrl4;
//            imageUrl4=null;
//            iv_delete4.setVisibility(View.GONE);
//            ImageLoaderManager.getInstance(this)
//                    .displayImage(iv_yijianfankui_tianjiatupian3,imageUrl3);
//            iv_yijianfankui_tianjiatupian4.setVisibility(View.GONE);
//            iv_yijianfankui_tianjiatupian3.setImageResource(R.drawable.i_icon_dianjiatupian);
//        }else {
//            imageUrl3=null;
//            iv_delete3.setVisibility(View.GONE);
//            iv_yijianfankui_tianjiatupian3.setImageResource(R.drawable.i_icon_dianjiatupian);
//        }
//    }
//    private void delete4(){
//        imageList.remove(3);
//        imageUrl4=null;
//        iv_delete4.setVisibility(View.GONE);
//        iv_yijianfankui_tianjiatupian4.setImageResource(R.drawable.i_icon_dianjiatupian);
//    }

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


    private void getData(){
        mList=new ArrayList<>();
        DialogUntil.showLoadingDialog(YiJianFanKuiActivity.this,"正在加载",true);
        String url= XY_Response.URL_FINDCOMMUNITYBYPER+"mobilePhone="+
                SPManager.getInstance().getString("mobilePhone","");
        RequestCenter.findCommunityByPer(url, new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                SelectPlot selectPlot= (SelectPlot) responseObj;
                DialogUntil.closeLoadingDialog();
                if (selectPlot.getCode().equals("1000")){
                    mList1=selectPlot.getObj();
                    showActionSheet();
                }else {
                    Toast.makeText(YiJianFanKuiActivity.this,selectPlot.getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Object reasonObj) {
                DialogUntil.closeLoadingDialog();
                MyDialog.showDialog(YiJianFanKuiActivity.this,"请检查网络连接");
            }
        });
    }

    public void showActionSheet(){
        if (mList1.size()>0) {
            if (commonActionSheetDialog == null) {
                commonActionSheetDialog = new CommonActionSheetDialog(this);

                for (SelectPlot.Obj item : mList1) {
                    commonActionSheetDialog.addMenuItem(item.getCommunityName()
                            +item.getNperName()+item.getFloorName()
                            + item.getUnitName()
                            + item.getDoorName());
                }

                commonActionSheetDialog.setMenuListener(new CommonActionSheetDialog.MenuListener() {
                    @Override
                    public void onItemSelected(int position, String item) {

                        communityId = mList1.get(position).getCommunityId();
                        nperId = mList1.get(position).getNperId();
                        floorId = mList1.get(position).getFloorId();
                        unitId = mList1.get(position).getUnitId();
                        doorId = mList1.get(position).getDoorId();
                        tv_xiaoqu.setText(mList1.get(position).getCommunityName()
                                + mList1.get(position).getNperName() +
                                mList1.get(position).getUnitName() +
                                mList1.get(position).getDoorName());
                        roleType=mList1.get(position).getRoleType();
                    }

                    @Override
                    public void onCancel() {

                    }
                });

            }
            commonActionSheetDialog.show();
        }else {
            tv_xiaoqu.setText("请添加小区");
        }

    }
















}
