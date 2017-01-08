package com.example.benjious.bear_newspaper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.benjious.bear_newspaper.R;
import com.example.benjious.bear_newspaper.presenter.FirstFragmentImpl;
import com.example.benjious.bear_newspaper.presenter.FirstPresenter;
import com.example.benjious.bear_newspaper.view.FirstView;

import java.util.List;

/**
 * Created by Benjious on 2016/12/28.
 */

public class FirstListFragment extends Fragment implements FirstView {
    private FirstPresenter mFirstPresenter;

    public static FirstListFragment newInstance(int type) {
        Bundle args = new Bundle(type);
        FirstListFragment fragment = new FirstListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirstPresenter = new FirstFragmentImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void addData(List list) {

    }

    @Override
    public void showLoadFail() {

    }
}
