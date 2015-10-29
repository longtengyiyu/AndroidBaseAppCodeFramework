package in.guanjia.demo.controller;

import com.google.gson.Gson;

import java.util.HashMap;

import in.guanjia.demo.app.CustomApplication;
import in.guanjia.demo.base.BaseController;
import in.guanjia.demo.bean.WeChat;
import in.guanjia.demo.util.LogUtils;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Description:微信登录控制器
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/21
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/21        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class WeChatLoginController extends BaseController<WeChat>{

    public WeChatLoginController(String appId,String secret,String code, String grantType){
        mRequestCall = CustomApplication.getRequestService().getAccessToken(appId, secret, code, grantType);
    }


    @Override
    public void loadData() {

        mRequestCall.enqueue(new Callback<WeChat>() {
            @Override
            public void onResponse(Response<WeChat> response, Retrofit retrofit) {
                LogUtils.d("tang", " onResponse " + new Gson().toJson(response));
                if (mApiCallBack != null && response != null && response.isSuccess()){
                    mApiCallBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtils.d("tang", " onResponse " + new Gson().toJson(t));
                if (mApiCallBack != null && t != null){
                    mApiCallBack.onFail(t.getMessage());
                }
            }
        });
    }
}
