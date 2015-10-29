package in.guanjia.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.guanjia.demo.R;
import in.guanjia.demo.app.AppClientConfig;
import in.guanjia.demo.base.BaseAbsFragment;
import in.guanjia.demo.bean.IpAddressInfo;
import in.guanjia.demo.listener.ApiInterface;
import in.guanjia.demo.listener.OnScrollListener;
import in.guanjia.demo.util.LogUtils;
import in.guanjia.demo.util.StringUtils;
import in.guanjia.demo.view.FloatTopMenuScrollView;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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
public class FloatTopMenuFragment extends BaseAbsFragment implements OnScrollListener {

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
    private Call<IpAddressInfo> mRequestCall = null;

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
        ApiInterface service = AppClientConfig.getApiClient();
        mRequestCall = service.getIpAddressInfo("json", "101.81.97.188");

        //异步请求
        mRequestCall.enqueue(new Callback<IpAddressInfo>() {
            @Override
            public void onResponse(Response<IpAddressInfo> response, Retrofit retrofit) {
                if (response != null && response.isSuccess() && response.body() != null) {
                    setIpAddressInfo(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void setIpAddressInfo(IpAddressInfo info){

        String addressInfo = "";

        if (StringUtils.notEmpty(info.country)){
            addressInfo += info.country;
        }

        if (StringUtils.notEmpty(info.province)){
            addressInfo += info.province;
        }
        if (StringUtils.notEmpty(info.city)){
            addressInfo += info.city;
        }
        mTvAddress.setText(addressInfo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRequestCall.cancel();
    }
}
