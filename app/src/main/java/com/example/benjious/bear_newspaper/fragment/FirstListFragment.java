package com.example.benjious.bear_newspaper.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.benjious.bear_newspaper.R;
import com.example.benjious.bear_newspaper.adapter.FirstAdapter;
import com.example.benjious.bear_newspaper.base.DataBean;
import com.example.benjious.bear_newspaper.commons.Urls;
import com.example.benjious.bear_newspaper.presenter.FirstListFragmentImpl;
import com.example.benjious.bear_newspaper.presenter.FirstPresenter;
import com.example.benjious.bear_newspaper.view.FirstView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.benjious.bear_newspaper.fragment.FirstFragment.ONE;

/**
 * RecycleView 中有滚动监听,
 * <p>
 * Created by Benjious on 2016/12/28.
 */

public class FirstListFragment extends Fragment implements FirstView, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.recycle_view)
    RecyclerView mRecycleView;
    @Bind(R.id.swipe_refresh_widget)
    SwipeRefreshLayout mSwipeRefreshWidget;
    @Bind(R.id.fa_firstlist)
    FloatingActionButton mFloatingActionButton;

    private int type = ONE;
    private int pageIndex = 0;
    private ArrayList<DataBean> mDataBeen;

    private FirstPresenter mFirstPresenter;
    private LinearLayoutManager mLayoutManager;
    private FirstAdapter mFirstAdapter;

    public static final String TAG = "FirstListFragment";

    private FirstAdapter.OnItemClickListener mOnItemClickListener = new FirstAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            /**
             * 当我按下新闻条目,
             * 1.获取基本信息,就是bean
             * 2.根据bean,去加载另外的界面
             *
             */
            DataBean dataBean = mFirstAdapter.getItem(position);
            Log.d(TAG, "xyz  onItemClick: 点击的数据======" + dataBean.getTitle());


        }
    };

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        //最后一个的条目的角标 0,1,2,3,4,5,6,7,
        private int mLastVisibleItemPostion;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            mLastVisibleItemPostion = mLayoutManager.findLastVisibleItemPosition();
        }


        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            //当没有滚动 并且 到达最后一个条目的时候
            if (newState == RecyclerView.SCROLL_STATE_IDLE && mLastVisibleItemPostion + 1 == mFirstAdapter.getItemCount() && mFirstAdapter.isShowFooter()) {
                mFirstPresenter.loadData(type, pageIndex + Urls.PAZE_SIZE);
            }
        }

    };

    //-------------------------------------------------------------------
    public static FirstListFragment newInstance(int type) {
        Bundle args = new Bundle(type);
        FirstListFragment fragment = new FirstListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFirstPresenter = new FirstListFragmentImpl(this);
        //保存数据
        type = getArguments().getInt("type");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstlist_fragment, container, false);
        ButterKnife.bind(this, view);
        initView();
        onRefresh();
        return view;
    }

    private void initView() {
        mSwipeRefreshWidget.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent, R.color.colorAccent);
        mSwipeRefreshWidget.setOnRefreshListener(this);
        mRecycleView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecycleView.setLayoutManager(mLayoutManager);
        mFirstAdapter = new FirstAdapter(getActivity());
        mRecycleView.setAdapter(mFirstAdapter);
        mFirstAdapter.setOnItemClickListener(mOnItemClickListener);
        mRecycleView.addOnScrollListener(mOnScrollListener);

        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoTop(0);
            }
        });


    }

    //点击滚动到列表的最顶端
    private void gotoTop(int position) {
        mRecycleView.scrollToPosition(position);
    }

    @Override
    public void showProgress() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        mSwipeRefreshWidget.setRefreshing(false);
    }


    @Override
    public void addData(List list) {
        if (mDataBeen == null) {
            mDataBeen = new ArrayList<>();
        }

        //把数据放进去
        mDataBeen.addAll(list);
        if (pageIndex == 0) {
            mFirstAdapter.setData(list);
        } else {
            if (list.size() == 0) {
                mFirstAdapter.isShowFooter(false);
            }
            mFirstAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoadFail() {
        if (pageIndex == 0) {
            mFirstAdapter.isShowFooter(false);
            mFirstAdapter.notifyDataSetChanged();
        }
        View view = getActivity() == null ? mRecycleView.getRootView() : getActivity().findViewById(R.id.drawer_layout);
        Snackbar.make(view, "加载数据失败", Snackbar.LENGTH_SHORT).show();
    }


    //让页数定位为0,数据清空,重新加载
    @Override
    public void onRefresh() {
        pageIndex = 0;
        if (mDataBeen != null) {
            mDataBeen.clear();
        }
        mFirstPresenter.loadData(type, pageIndex);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
