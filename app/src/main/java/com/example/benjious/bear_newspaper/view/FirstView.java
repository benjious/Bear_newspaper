package com.example.benjious.bear_newspaper.view;

import java.util.List;

/**
 * Created by Benjious on 2016/12/31.
 */

public interface FirstView {
    void showProgress();
    void hideProgress();
    void addData(List list);
    void showLoadFail();

}
