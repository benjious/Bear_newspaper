package com.example.benjious.bear_newspaper.presenter;

import com.example.benjious.bear_newspaper.base.DataBean;

import java.util.Date;
import java.util.List;

/**
 * 加载首页数据条目相关类
 * Created by Benjious on 2016/12/31.
 */
public class FirstModeImpl implements com.example.benjious.bear_newspaper.model.FirstModel {

    @Override
    public void loadData(String url, int type, OnloadFirstDataListener dataListener) {
        
    }

    @Override
    public void loadDetailData(String url, OnloadFirstDataDetialListener detialListener) {

    }

    public interface OnloadFirstDataListener{
        void onSuccess(List<DataBean> list);
        void onFailure(String str,Exception e);

    }

    public interface OnloadFirstDataDetialListener{
        void onSuccess(List<DataBean> list);
        void onFailure(String str,Exception e);

    }
}
