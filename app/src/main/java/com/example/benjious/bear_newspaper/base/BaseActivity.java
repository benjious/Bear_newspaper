package com.example.benjious.bear_newspaper.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import static android.view.View.SYSTEM_UI_FLAG_IMMERSIVE;


/**
 * Created by Benjious on 2016/12/19.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private ConnectivityManager mConnectivityManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //锁定只能是竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //存放一下context上下文
        mContext = getActivityContext();
        initView();
        initData();

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    /**
     * 初始化Activity
     */
    private void initView() {

        loadViewLayout();
    }

    private void initData() {
        findViewById();
        setListener();
        processLogic();
    }

    //--------------------------------------------

    /**
     * 加载页面元素
     */
    protected abstract void findViewById();

    /**
     * 设置监听器
     */
    protected abstract void setListener();

    /**
     * 业务逻辑处理,主要与后端交互
     */
    protected abstract void processLogic();


    //--------------------------------------------------

    /**
     * 加载layout
     */
    protected abstract void loadViewLayout();


    protected abstract Context getActivityContext();

    //--------------------------------------------
    public boolean checkNetworkState() {
        boolean flag = false;
        //得到网络链接信息
        mConnectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        if (mConnectivityManager.getActiveNetworkInfo() != null) {
            flag = mConnectivityManager.getActiveNetworkInfo().isAvailable();

        }
        return flag;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus && Build.VERSION.SDK_INT > 19) {
            hideSystemUI(decorView);
//        }else {
//            showSystemUI(decorView);
//        }
        }
    }


    // This snippet hides the system bars.
    @TargetApi(19)
    private void hideSystemUI(View decorView) {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);//system bar 过多一段时间自动消失
    }

    // This snippet shows the system bars. It does this by removing all the flags
// except for the ones that make the content appear under the system bars.
    @TargetApi(19)
    private void showSystemUI(View decorView) {
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }
}


