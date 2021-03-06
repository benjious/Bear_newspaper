package com.example.benjious.bear_newspaper.presenter;

import com.example.benjious.bear_newspaper.base.DataBean;
import com.example.benjious.bear_newspaper.commons.Urls;
import com.example.benjious.bear_newspaper.fragment.FirstFragment;
import com.example.benjious.bear_newspaper.model.FirstModeImpl;
import com.example.benjious.bear_newspaper.model.FirstModel;
import com.example.benjious.bear_newspaper.view.FirstView;

import java.util.List;

/**
 * 这个类,实现Model的一个接口,当加载完数据方便通过presenter调用view的方法更新view,
 * Created by Benjious on 2016/12/31.
 */

public class FirstListFragmentImpl implements FirstPresenter,FirstModeImpl.OnloadFirstDataListener{
    public FirstView mFirstView;
    public FirstModel mFirstModel;
    public static final String TAG="FirstListFragmentImpl  ";
    public FirstListFragmentImpl(FirstView view) {
        this.mFirstView=view;
        this.mFirstModel=new FirstModeImpl();

    }

    @Override
    public void onSuccess(List<DataBean> list) {
        mFirstView.hideProgress();
        mFirstView.addData(list);
    }

    @Override
    public void onFailure(String str, Exception e) {
        mFirstView.hideProgress();
        mFirstView.showLoadFail();
    }


    //presenter接口的方法
    //不走请求图中的第一步
    @Override
    public void loadData(Object type, int page) {
        String url = getUrl((Integer) type, page);
        System.out.println(TAG+url);
        if (page==0) {
            mFirstView.showProgress();
        }
        mFirstModel.loadData(url,(Integer) type,this);
    }

    private String getUrl(int type, int page) {
        StringBuilder stringBuilder = new StringBuilder();
        switch (type) {
            case FirstFragment.ONE:
                stringBuilder.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
            case FirstFragment.TWO:
                stringBuilder.append(Urls.COMMON_URL).append(Urls.NBA_ID);
                break;
            case FirstFragment.THREE:
                stringBuilder.append(Urls.COMMON_URL).append(Urls.CAR_ID);
                break;
            default:
                stringBuilder.append(Urls.TOP_URL).append(Urls.TOP_ID);
                break;
        }
        stringBuilder.append("/").append(page).append(Urls.END_URL);
        return stringBuilder.toString();
    }

}
