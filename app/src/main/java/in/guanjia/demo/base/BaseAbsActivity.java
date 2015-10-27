package in.guanjia.demo.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.guanjia.demo.R;
import in.guanjia.demo.util.LogUtils;
import in.guanjia.demo.view.SwipeBackLayout;

/**
 * Description: Activity基类
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/20
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/20        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseAbsActivity extends BaseSwipeBackActivity {
    protected final String TAG = getClass().getCanonicalName();

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    protected ActionBar mActionBar;
    protected SwipeBackLayout mSwipeBackLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, getFragment())
                    .commit();
        }
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        LogUtils.d(TAG, "ActionBar  = " + mActionBar);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.mipmap.ic_back); //特么终于自定义返回按钮了（actionBar back button）

        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);  //设置滑动方向，可设置EDGE_LEFT, EDGE_RIGHT, EDGE_ALL, EDGE_BOTTOM
//        setSwipeBackEnable(false);  //禁止滑动删除
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtils.d(TAG, "OptionsItemSelected " + item.getItemId());
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 获取Fragment抽象方法
     *
     * @return V4 fragment
     */
    protected abstract Fragment getFragment();
}
