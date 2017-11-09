package com.bjxiyang.shuzianfang.myapplication.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bjxiyang.shuzianfang.R;
import com.bjxiyang.shuzianfang.myapplication.manager.SPManager;

/**
 * Created by Administrator on 2017/7/14 0014.
 */

public class MyWebViewActivity extends MySwipeBackActivity{
    private WebView web;
    private String url;
    private static final String APP_CACAHE_DIRNAME = "/webcache";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xy_jinrongweb_activity);
        Intent intent=getIntent();
        url=intent.getStringExtra("url");
        initUI();
    }
    private void initUI() {
        web= (WebView) findViewById(R.id.web);
        WebSettings webSettings=web.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        initWebView();
        web.setWebViewClient(new MyWebViewActivity.Callback());
        web.loadUrl(url+"?token="+
                SPManager.getInstance().getString("mobilePhone","")+
                "_"+SPManager.getInstance().getString("loginKey","")
                +"&cmemberId="+ SPManager.getInstance().getString("c_memberId","0"));



//        web.setWebViewClient(new WebViewClient() {
//            //覆盖shouldOverrideUrlLoading 方法
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url1) {
//                web.loadUrl("http://"+url);
//                return true;
//            }
//        });
    }
    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url){
            // TODO Auto-generated method stub
            if (MyWebViewActivity.this == null) {
                return false;
            }


            //调用拨号程序
            if (url.startsWith("mailto:") || url.startsWith("geo:") || url.startsWith("tel:") || url.startsWith("smsto:")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            return false;
        }
    }
    private void initWebView() {

        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        web.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);  //设置 缓存模式
        // 开启 DOM storage API 功能
        web.getSettings().setDomStorageEnabled(true);
        //开启 database storage API 功能
        web.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME;
        //      String cacheDirPath = getCacheDir().getAbsolutePath()+Constant.APP_DB_DIRNAME;
        //设置数据库缓存路径
        web.getSettings().setDatabasePath(cacheDirPath);
        //设置  Application Caches 缓存目录
        web.getSettings().setAppCachePath(cacheDirPath);
        //开启 Application Caches 功能
        web.getSettings().setAppCacheEnabled(true);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()){
            web.goBack();
            return true;
        }else{
//            if((System.currentTimeMillis()-exitTime) > 2000){
////                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                exitTime = System.currentTimeMillis();
//            } else {
                finish();
//            }
        }
        return true;
    }
}
