package in.guanjia.demo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;
import butterknife.Bind;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;
import in.guanjia.demo.R;
import in.guanjia.demo.app.AppClientConfig;
import in.guanjia.demo.app.AppContact;
import in.guanjia.demo.base.BaseAbsFragment;
import in.guanjia.demo.base.BaseRequest;
import in.guanjia.demo.bean.LoginSuccess;
import in.guanjia.demo.bean.ThirdPartLogin;
import in.guanjia.demo.ui.FloatTopMenuActivity;
import in.guanjia.demo.ui.LoginActivity;
import in.guanjia.demo.ui.MainActivity;
import in.guanjia.demo.ui.RegisterActivity;
import in.guanjia.demo.util.LogUtils;
import in.guanjia.demo.util.NetInfoUtils;
import in.guanjia.demo.util.RandomUtils;
import in.guanjia.demo.util.StringUtils;
import in.guanjia.demo.util.ToastUtils;
import in.guanjia.demo.util.Utils;

/**
 * Description:登录界面
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/20
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/20        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class SignFragment extends BaseAbsFragment {
    @Bind(R.id.root_view)
    RelativeLayout mRootView;
    private IWXAPI mWxApi;
    private RequestParams mRequestParams;

    public SignFragment() {

    }

    public static SignFragment newInstance() {
        return new SignFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_sign;
    }

    @OnClick({R.id.tv_register, R.id.tv_login, R.id.tv_qq, R.id.tv_weibo, R.id.tv_wechat})
    void onClick(View view) {
        if (NetInfoUtils.isWifiEnabled(getActivity())) {
            switch (view.getId()) {
                case R.id.tv_register:

                    Utils.getInstance().startNewActivity(RegisterActivity.class);
                    break;
                case R.id.tv_login:

                    Utils.getInstance().startNewActivity(LoginActivity.class);
                    break;
                case R.id.tv_qq:

                    ToastUtils.getInstance().showInfo(view, "您还未安装QQ客户端");
                    break;
                case R.id.tv_weibo:

                    ToastUtils.getInstance().showInfo(view, "您还未安装微博客户端");
                    Utils.getInstance().startNewActivity(FloatTopMenuActivity.class);
                    break;
                case R.id.tv_wechat:
                    onWeChatLogin();
                    break;
            }
        } else {
            ToastUtils.getInstance().showInfo(view, R.string.network_error);
        }
    }

    //登录成功关闭页面
    @Subscriber
    private void closePage(LoginSuccess data){
        getActivity().finish();
    }

    //==========================================================WeChat Start======================================================
    private void onWeChatLogin() {
        regAppId2Wx();
        openWxClient();
    }

    /**
     * 注册到微信
     */
    private void regAppId2Wx() {
        /**实例化WXAPI*/
        mWxApi = WXAPIFactory.createWXAPI(getActivity(), AppContact.THIRD_LOGIN_WX_ID);
        /**将应用注册到微信*/
        mWxApi.registerApp(AppContact.THIRD_LOGIN_WX_ID);
    }

    /**
     * 调起微信客户端
     */
    private void openWxClient() {
        if (mWxApi.isWXAppInstalled()) {
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo"; //作用域，snsapi_userinfo 获取用户信息
            req.state = RandomUtils.randomKey(8); //用于保持请求和回调的状态，授权请求后原样带回给第三方
            mWxApi.sendReq(req);
        } else {
            ToastUtils.getInstance().showInfo(mRootView, R.string.wx_not_install);
        }
    }

    @Subscriber
    private void onGetWxToken(ThirdPartLogin data) {
        LogUtils.d(TAG, "GetWxToken == " + data.token);
        if (StringUtils.notEmpty(data.token)){
            getAccessTokenByCode(data.token);
        }
    }

    private void getAccessTokenByCode(String code){
        if (mRequestParams == null){
            mRequestParams = new RequestParams();
        }

        BaseRequest.get(AppClientConfig.WX_ACCESS_TOKEN_URL, mRequestParams, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                //获取openId
                try {
                    if(StringUtils.notEmpty(response.getString("openid")) && StringUtils.notEmpty(response.getString("access_token"))){
                        Utils.getInstance().startNewActivity(MainActivity.class);
                        getActivity().finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                LogUtils.d(TAG, "statusCode ==" + statusCode + "errorResponse ==" + errorResponse.toString());
                ToastUtils.getInstance().showInfo(mRootView, "授权失败");
            }
        });
    }

    //=====================================================================end=====================================================================

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
