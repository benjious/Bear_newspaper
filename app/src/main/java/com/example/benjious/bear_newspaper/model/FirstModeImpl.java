package com.example.benjious.bear_newspaper.model;

import com.example.benjious.bear_newspaper.base.DataBean;
import com.example.benjious.bear_newspaper.commons.Urls;
import com.example.benjious.bear_newspaper.fragment.FirstFragment;
import com.example.benjious.bear_newspaper.util.NewsJsonUtils;
import com.example.benjious.bear_newspaper.util.OkHttpUtils;

import java.util.List;

import static com.example.benjious.bear_newspaper.fragment.FirstFragment.ONE;
import static com.example.benjious.bear_newspaper.fragment.FirstFragment.TWO;

/**
 * 加载首页数据条目相关类
 * Created by Benjious on 2016/12/31.
 */
public class FirstModeImpl implements FirstModel {

    @Override
    public void loadData(String url, final int type, final OnloadFirstDataListener dataListener) {
        //这里定义CallBack类
        OkHttpUtils.ResultCallback<String> loadNewsCallback=new OkHttpUtils.ResultCallback<String>() {
            @Override
            public void onSuccess(String response) {
                List<DataBean> dataBeens= NewsJsonUtils.readJsonDataBeans(response,getID(type));
                dataListener.onSuccess(dataBeens);
            }

            @Override
            public void onFailure(Exception e) {
                dataListener.onFailure("load news list failed ",e);
            }
        };
        //这个才是真正的操作
        OkHttpUtils.get(url,loadNewsCallback);
    }

    /**
     * 获取ID
     * @param type
     * @return
     */
    private String getID(int type) {
        String id;
        switch (type) {
            case ONE:
                id= Urls.TOP_ID;
                break;
            case TWO:
                id=Urls.NBA_ID;
                break;
            case FirstFragment.THREE:
                id = Urls.CAR_ID;
                break;
            default:
                id = Urls.TOP_ID;
                break;
        }
        return id;
    }

    @Override
    public void loadDetailData(String url, OnloadFirstDataDetialListener detialListener) {

    }

    public interface OnloadFirstDataListener{
        void onSuccess(List<DataBean> list);
        void onFailure(String str, Exception e);

    }

    public interface OnloadFirstDataDetialListener{
        void onSuccess(List<DataBean> list);
        void onFailure(String str, Exception e);

    }
}
