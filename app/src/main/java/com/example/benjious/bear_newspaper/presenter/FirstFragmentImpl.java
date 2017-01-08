package com.example.benjious.bear_newspaper.presenter;

import com.example.benjious.bear_newspaper.model.FirstModel;
import com.example.benjious.bear_newspaper.view.FirstView;

/**
 * Created by Benjious on 2016/12/31.
 */

public class FirstFragmentImpl extends FirstPresenter {
    public FirstView mFirstView;
    public FirstModel mFirstModel;
    public FirstFragmentImpl(FirstView view) {
        this.mFirstView=view;
        this.mFirstModel=new FirstModeImpl();

    }
}
