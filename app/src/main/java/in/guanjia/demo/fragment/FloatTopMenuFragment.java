package in.guanjia.demo.fragment;

import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import in.guanjia.demo.R;
import in.guanjia.demo.base.BaseAbsFragment;
import in.guanjia.demo.bean.IpAddressInfo;
import in.guanjia.demo.controller.IpAddressController;
import in.guanjia.demo.listener.ApiCallBack;
import in.guanjia.demo.listener.OnScrollListener;
import in.guanjia.demo.util.StringUtils;
import in.guanjia.demo.util.ToastUtils;
import in.guanjia.demo.view.FloatTopMenuScrollView;

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
public class FloatTopMenuFragment extends BaseAbsFragment implements OnScrollListener, ApiCallBack<IpAddressInfo> {

    @Bind(R.id.tv_float)
    TextView mTvFloat;
    @Bind(R.id.tv_float_top)
    TextView mTvFloatTop;
    @Bind(R.id.sv_float)
    FloatTopMenuScrollView mSvFloat;
    @Bind(R.id.root_view)
    LinearLayout mRootView;
    @Bind(R.id.tv_address)
    TextView mTvAddress;
    private IpAddressController mIpAddressController = null;

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
        setUpComponent();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUpViewComponent();
    }

    private void setUpViewComponent() {
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

    private void setUpComponent(){
        if (mIpAddressController == null){
            mIpAddressController = new IpAddressController("json", "101.81.97.188");
        }
        mIpAddressController.setApiCallBack(this);
        mIpAddressController.loadData();
    }

    private void setIpAddressInfo(IpAddressInfo info){

        StringBuffer sb = new StringBuffer("");
        if (StringUtils.notEmpty(info.country)){
            sb.append(info.country);
        }

        if (StringUtils.notEmpty(info.province)){
            sb.append(info.province);
        }
        if (StringUtils.notEmpty(info.city)){
            sb.append(info.city);
        }
        mTvAddress.setText(sb);
    }

    @Override
    public void onSuccess(IpAddressInfo ipAddressInfo) {
        if (ipAddressInfo != null) {
            setIpAddressInfo(ipAddressInfo);
        }
    }

    @Override
    public void onFail(String error) {
        ToastUtils.getInstance().showInfo(mRootView, error);
    }

    @Override
    public void onFail() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mIpAddressController != null && mIpAddressController.getRequestCall() != null){
            mIpAddressController.getRequestCall().cancel(); //新增加cancel函数，更加灵活，之前取消使用setListener(null),
        }
    }
}
