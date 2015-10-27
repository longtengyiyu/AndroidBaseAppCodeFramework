package in.guanjia.demo.fragment;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import in.guanjia.demo.R;
import in.guanjia.demo.base.BaseAbsFragment;
import in.guanjia.demo.listener.OnScrollListener;
import in.guanjia.demo.view.FloatTopMenuScrollView;
import in.guanjia.demo.view.OverScrollView;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/27
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/27        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class FloatTopMenuFragment extends BaseAbsFragment implements OnScrollListener{

    @Bind(R.id.tv_float)
    TextView mTvFloat;
    @Bind(R.id.tv_float_top)
    TextView mTvFloatTop;
    @Bind(R.id.sv_float)
    FloatTopMenuScrollView mSvFloat;
    @Bind(R.id.root_view)
    LinearLayout mRootView;

    public FloatTopMenuFragment() {

    }

    public static FloatTopMenuFragment newInstance() {
        return new FloatTopMenuFragment();
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_float_top_menu;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewComponent();
    }

    private void setUpViewComponent(){
        mSvFloat.setOnScrollListener(this);
        mSvFloat.getParent().requestDisallowInterceptTouchEvent(true);
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(mSvFloat.getScrollY());
            }
        });
    }

    @Override
    public void onScroll(int scrollY) {
        int mBuyLayout2ParentTop = Math.max(scrollY, mTvFloat.getTop());
        mTvFloatTop.layout(0, mBuyLayout2ParentTop, mTvFloatTop.getWidth(), mBuyLayout2ParentTop + mTvFloatTop.getHeight());
    }
}
