package com.example.benjious.bear_newspaper.view;

import com.example.benjious.bear_newspaper.base.DataBean;

import java.util.List;

import static android.R.id.list;

/**
 * Created by Benjious on 2016/12/31.
 */

public interface FirstView {
    void showProgress();
    void hideProgress();
    void addData(List<DataBean> list);
    void showLoadFail();

}
