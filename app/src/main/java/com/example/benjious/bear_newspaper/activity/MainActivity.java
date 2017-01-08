package com.example.benjious.bear_newspaper.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.example.benjious.bear_newspaper.R;
import com.example.benjious.bear_newspaper.base.BaseActivity;
import com.example.benjious.bear_newspaper.fragment.FirstFragment;
import com.example.benjious.bear_newspaper.view.MainView;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.id;

public class MainActivity extends BaseActivity implements MainView, NavigationView.OnNavigationItemSelectedListener {


    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.appbar)
    AppBarLayout mAppbar;
    @Bind(R.id.frame_content)
    FrameLayout mFrameContent;
    @Bind(R.id.main_content)
    CoordinatorLayout mMainContent;
    @Bind(R.id.nav_view)
    NavigationView mNavView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void findViewById() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout,mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);

        //该方法会自动和actionBar关联,将开关的图片显示在actionBar上,如果不设置
        //也有抽屉效果,不过是默认的图标
        toggle.syncState();
        mNavView.setNavigationItemSelectedListener(this);
        switchFirst();

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic() {

    }

    @Override
    protected void loadViewLayout() {
        setContentView(R.layout.activity_main);

    }

    @Override
    protected Context getActivityContext() {
        return this;
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void switchFirst() {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_content, new FirstFragment()).commitAllowingStateLoss();
        mToolbar.setTitle("新闻");
    }

    @Override
    public void switchSecond() {

    }

    @Override
    public void switchThree() {

    }

    @Override
    public void switchMain() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int  id=item.getItemId();
        if (id==R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
