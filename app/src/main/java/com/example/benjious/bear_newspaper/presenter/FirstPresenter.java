package com.example.benjious.bear_newspaper.presenter;

/**
 * Created by Benjious on 2016/12/31.
 */

public interface FirstPresenter<E> {
    void loadData(E type, int page);
}
