package com.example.benjious.bear_newspaper.model;

/**
 * Created by Benjious on 2016/12/31.
 */
public interface FirstModel {
    void  loadData(String url, int type, FirstModeImpl.OnloadFirstDataListener dataListener);
    void  loadDetailData(String url, FirstModeImpl.OnloadFirstDataDetialListener detialListener);

}
