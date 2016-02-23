package in.guanjia.demo.base;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;
import in.guanjia.demo.R;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2016/2/23
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2016/2/23        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseRecyclerViewFragment extends BaseAbsFragment {

    @Bind(R.id.recycler_view)
    protected RecyclerView mRecyclerView;
    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    protected BaseRecyclerViewAdapter mBaseAdapter;

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_base_recycler_view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewComponent();
    }

    private void setUpViewComponent(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    /**
     * 重新加载
     */
    public abstract void onLoadRefresh();

    /**
     * 加载更多
     */
    public abstract void onLoadMore();
}
