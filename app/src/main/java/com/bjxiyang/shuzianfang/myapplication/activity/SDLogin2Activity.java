package com.bjxiyang.shuzianfang.myapplication.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baisi.myapplication.adutil.APP_ID;
import com.baisi.myapplication.okhttp.listener.DisposeDataListener;
import com.baisi.myapplication.okhttp.request.RequestParams;
import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;
import com.bjxiyang.shuzianfang.myapplication.manager.UserManager;
import com.bjxiyang.shuzianfang.myapplication.model.FanHui;
import com.bjxiyang.shuzianfang.myapplication.model.Users;
import com.bjxiyang.shuzianfang.myapplication.response_xy.XY_Response;
import com.bjxiyang.shuzianfang.myapplication.until.DialogUntil;
import com.bjxiyang.shuzianfang.myapplication.until.MyUntil;
import com.bjxiyang.shuzianfang.myapplication.update.network.RequestCenter;
import com.bjxiyang.shuzianfang.myapplication.view.MyDialog;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SDLogin2Activity extends BeasActivity {

    private static final String WEIXIN_APP_ID="wxfe7d0b3660506256";
    private static final String WEIXIN_SCOPE="snsapi_userinfo";
    private static final String WEIXIN_STATE="wechat_sdk_login";
    private TextView tv_yonghuxieyi;
    private LinearLayout il_deletephone;



    private EditText et_zhuce_password_again;
    private Button bt_get_smscode;
    private Button wx;
    Button loginMenutView;
//    Button regiestMenutView;
    LinearLayout loginView;
    LinearLayout regiestView;

    ImageView point_login;
    ImageView point_regiest;

    // 登录模块的
    EditText input_user_name_login_editview;
    EditText input_user_password_login_editview;
    Button login_action_button;

    //注册模块的
    EditText input_user_name_regiest_editview;
    EditText input_user_sms_code_editview;
    EditText input_user_password_regiest_editeView;
    private int timeCount = 60;
    private Timer mtimer;
    Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what==8000){
                loginView.setVisibility(View.VISIBLE);
                regiestView.setVisibility(View.GONE);
                MyUntil.show(SDLogin2Activity.this,"注册成功");
//                showPointForLogin();
                qingkong();
                DialogUntil.closeLoadingDialog();

            }
            if (msg.what==7000){
                MyUntil.show(SDLogin2Activity.this,"短信发送成功，请注意查收");
                startCountdown();
            }

            return false;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login5);
        getQuanxian();

        if (HomeActivity.homeActivity!=null) {
            HomeActivity.homeActivity.finish();
        }
        if (GeRenZhongXinActivity.geRenZhongXinActivity!=null) {
            GeRenZhongXinActivity.geRenZhongXinActivity.finish();
        }

        //判断有没有用户的信息,如果有,直接跳转到主界面
        if (SPManager.getInstance().getString("mobilePhone",null)!=null){
            //将得到的用户信息保存到实体类中
            UserManager.getInstance().setUser(setUserforSP());
            Intent intent = new Intent(SDLogin2Activity.this, HomeActivity.class);
            startActivity(intent);
            //关闭当前页面

            finish();
           
            //跳转到主页面

        }
        //初始化控件
        initView();

    }


    @Override
    protected void onResume() {
        super.onResume();
    }
    private void initView()
    {
        input_user_name_login_editview = (EditText) findViewById(R.id.et_login_username);
        input_user_password_login_editview = (EditText) findViewById(R.id.et_login_pwd);
        wx= (Button) findViewById(R.id.bt_weixindenglu);
        wx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginWithWeixin();
            }
        });
        tv_yonghuxieyi= (TextView) findViewById(R.id.tv_yonghuxieyi);
        tv_yonghuxieyi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyUntil.mStartActivity(SDLogin2Activity.this,FuWuTiaoKuanActivity.class);
            }
        });
        il_deletephone= (LinearLayout) findViewById(R.id.il_deletephone);
        il_deletephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_user_name_login_editview.setText("");
            }
        });
        point_login = (ImageView)findViewById(R.id.selected_menu_login);
        point_regiest = (ImageView) findViewById(R.id.selected_menu_regiest);
        //发送验证码按钮
        bt_get_smscode= (Button) findViewById(R.id.bt_get_smscode);
        //点击发送验证码按钮后的逻辑处理
        bt_get_smscode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置发送验证码后的倒计时的时间
                timeCount=60;
                //得到用户的手机号
                String uPhone= String.valueOf(input_user_name_login_editview.getText());
//                String url=XY_Response.URL+"";
                //判断用户输入的手机号是不是正确的手机号
                if (!isMobilephone(uPhone)) {
                    Toast.makeText(SDLogin2Activity.this,"请输入正确的手机号", Toast.LENGTH_LONG).show();
                    return;
                }
                //弹出等待的Dialog
                DialogUntil.showLoadingDialog(SDLogin2Activity.this,"请稍等",true);
                //设置类型为注册类型,后台需要的
                String type="3";
                //拼接访问的URL
                String test= XY_Response.URL_SED_MSM+"mobilePhone="+uPhone+"&type="+type;
                //请求后台数据
                RequestCenter.register(test, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        //请求成功返回数据并转化为实体类
                        FanHui fanHui= (FanHui) responseObj;
                        //清除Dialog
                        DialogUntil.closeLoadingDialog();
                        //判断后台的返回码,如果是1000的话说明发送验证码成功
                        if (fanHui.getCode().equals("1000")){
                            //发送验证码成功以后进行操作,因为是在线程中所以发送给Handler
                            Message message=new Message();
                            message.what=7000;
                            handler.sendMessage(message);
                            //如果返回的是500的话打印返回的错误信息
                        }else if (fanHui.getCode().equals("500")){
                            Toast.makeText(SDLogin2Activity.this,
                                    fanHui.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Object reasonObj) {
                        //网络请求失败也要清掉Dialog
                        DialogUntil.closeLoadingDialog();
                        //弹出请用户检查网络的Dialog,可以选择取消或者调到网络修改的页面
                        MyDialog.showDialog(SDLogin2Activity.this,"请检查网络连接");
                    }
                });
            }
        });



        //登录按钮实例化
        login_action_button = (Button) findViewById(R.id.btn_login);
        //点击登录按钮后的逻辑处理
        login_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录时输入的手机号
                 String uPhone = String.valueOf(input_user_name_login_editview.getText());
                //登录时输入的密码
                 String uPassword = String.valueOf(input_user_sms_code_editview.getText());
                //判断用户输入的手机号是否正确
                if (!isMobilephone(uPhone)) {
                    Toast.makeText(SDLogin2Activity.this,"请输入正确的手机号", Toast.LENGTH_LONG).show();
                    return;
                }
                //弹出正在登陆的等待动画
                DialogUntil.showLoadingDialog(SDLogin2Activity.this,"正在登陆",true);
                //拼接访问的URL
                String url=XY_Response.URL_INIT_THIRDACCOUNT
                        +"openid="+SPManager.getInstance().getString("openid","0")
                        +"&type="+0
                        +"&mobilePhone=" +uPhone
                        +"&dynamic=" +uPassword
                        +"&picUrl="+""
                        +"&nickname="+""
                        +"&unionid="+SPManager.getInstance().getString("unionid","0");
                RequestParams headers=new RequestParams();
                headers.put("user-agent", ""+"appId="+ APP_ID.APP_ID+"");
                RequestCenter.login(url,headers, new DisposeDataListener() {
                    @Override
                    public void onSuccess(Object responseObj) {
                        //访问成功清除Dialog
                        DialogUntil.closeLoadingDialog();
                        //得到返回数据并保存到实体类中
                        Users users= (Users) responseObj;
                        //如果返回码为1000,说明登陆成功
                        if (users.getCode()==1000){
                            //登陆成功以后将用户的实体类保存到单例模式下的用户实体类中
                            UserManager.getInstance().setUser(users);
                                //将用户的信息报错到SP中
                                setSP(users);
                            //登陆成功后跳转到主页面
                            Intent intent=new Intent(SDLogin2Activity.this,HomeActivity.class);
                            startActivity(intent);
                            SDLoginActivity.sdLoginActivity.finish();
                            finish();
                            //如果返回码是200,说明登陆有误
                        }else {
                            //弹出用户登陆的错误信息
                            Toast.makeText(SDLogin2Activity.this,
                                    users.getMsg(), Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Object reasonObj) {
                        //网络访问失败也要清除Dialog
                        DialogUntil.closeLoadingDialog();
                        //弹出请用户检查网络的Dialog
                        MyDialog.showDialog(SDLogin2Activity.this,"请检查网络连接");
                    }
                });
            }
        });
        //手机号输入框
        input_user_name_regiest_editview = (EditText)findViewById(R.id.inputusernameedittext) ;
        //验证码输入框
        input_user_sms_code_editview = (EditText) findViewById(R.id.et_reg_smscode) ;
        //首次输入密码
        input_user_password_regiest_editeView = (EditText)findViewById(R.id.et_reg_password);
        //第二次密码输入框
        et_zhuce_password_again= (EditText) findViewById(R.id.et_zhuce_password_again);
        loginView = (LinearLayout) findViewById(R.id.loginView);
        //布局的控制
        regiestView = (LinearLayout)findViewById(R.id.registerView);
    }
    //倒计时的方法
    public void setTimerTask(){
        mtimer = new Timer();
        mtimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = 1;
                timerHandler.sendMessage(message);
            }
        },1000,1000);
    }
    //在倒计时的时候,对按钮上的字进行赋值
    public void changeSmsButton(){
        bt_get_smscode.setText(timeCount + "秒重发");

    }
    private Handler timerHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                timeCount --;
                if (timeCount >= 0){
                    changeSmsButton();
                }else{
                    mtimer.cancel();
                    bt_get_smscode.setEnabled(true);
                    bt_get_smscode.setText(R.string.getsmsversion);
                }
            }
        }
    };

    //开始倒计时
    public void startCountdown(){
        changeSmsButton();
        bt_get_smscode.setEnabled(false);
        setTimerTask();
    }
    public static boolean isMobilephone(String phone) {
        if (phone.startsWith("86") || phone.startsWith("+86")) {
            phone = phone.substring(phone.indexOf("86") + 2);
        }
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }
    //密码的正则验证
    public static boolean rexCheckPassword(String input) {
       // 7-16 位，字母、数字、字符
       //String reg = "^([A-Z]|[a-z]|[0-9]|[`-=[];,./~!@#$%^*()_+}{:?]){6,20}$";
        String regStr = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$";
//       String regStr = "^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\[\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？]){8,16}$";
       return input.matches(regStr);
    }
    //MD5加密的方法
    public static String getMD5(String info){
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));
            byte[] encryption = md5.digest();
            StringBuffer strBuf = new StringBuffer();
            for (int i = 0; i < encryption.length; i++)
            {
                if (Integer.toHexString(0xff & encryption[i]).length() == 1)
                {
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else
                {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            return "";
        }
        catch (UnsupportedEncodingException e)
        {
            return "";
        }
    }
    //清空已输入信息的方法
    private void qingkong(){
        input_user_name_login_editview.setText("");
        input_user_password_login_editview.setText("");
        input_user_name_regiest_editview.setText("");
        //验证码输入框
        input_user_sms_code_editview.setText("");
        //首次输入密码
        input_user_password_regiest_editeView.setText("");
        //第二次密码输入框
        et_zhuce_password_again.setText("");
    }
    //保存信息到SP中
    private void setSP(Users users){
        //会员编号
        SPManager.getInstance().putString("loginKey", String.valueOf(users.getObj().getLoginKey()));
        SPManager.getInstance().putString("c_memberId", String.valueOf(users.getObj().getCmemberId()));
        SPManager.getInstance().putString("mobilePhone",users.getObj().getMobilePhone());
        SPManager.getInstance().putString("realName",users.getObj().getRealName());
        SPManager.getInstance().putString("nickName",users.getObj().getNickName());
        SPManager.getInstance().putString("sex", String.valueOf(users.getObj().getSex()));
        SPManager.getInstance().putString("headPhotoUrl",users.getObj().getHeadPhotoUrl());
//        SPManager.getInstance().putString("status",users.getObj().get(0).getStatus());
//        SPManager.getInstance().putString("birthday",users.getObj().getBirthday());
//        SPManager.getInstance().putString("email",users.getObj().getEmail());
//        SPManager.getInstance().putString("address",users.getObj().getAddress());
//        SPManager.getInstance().putString("qq",users.getObj().getQq());
//        SPManager.getInstance().putString("weChat",users.getObj().getWeChat());
//        SPManager.getInstance().putString("age", users.getObj().getAge());
//        SPManager.getInstance().putString("ownerId",users.getObj().getOwnerId());
    }
    //从SP中得到数据保存到实体类中
    private Users setUserforSP(){
        Users users=new Users();
        Users.ObjBean obj=new Users.ObjBean();
        obj.setCmemberId(Integer.parseInt(SPManager.getInstance().getString("c_memberId",null)));
        obj.setMobilePhone(SPManager.getInstance().getString("mobilePhone",null));
        obj.setRealName(SPManager.getInstance().getString("realname",null));
        obj.setNickName(SPManager.getInstance().getString("nickName",null));
        obj.setHeadPhotoUrl(SPManager.getInstance().getString("headPhotoUrl",null));
        users.setObj(obj);
        return users;
    }


    //得到设备码
    private void getIMEI(){
        TelephonyManager telephonyManager=(TelephonyManager)
                this.getSystemService(Context.TELEPHONY_SERVICE);
        APP_ID.APP_ID =telephonyManager.getDeviceId();

    }
    public void getQuanxian() {
        if (Build.VERSION.SDK_INT >= 23) {
            if(ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE)!= PackageManager.PERMISSION_GRANTED){
                android.support.v4.app.ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},1);
            }
        }
    }
    private IWXAPI mWeixinAPI;
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
    }


}
