package in.guanjia.demo.controller;

import in.guanjia.demo.app.CustomApplication;
import in.guanjia.demo.base.BaseController;
import in.guanjia.demo.bean.IpAddressInfo;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/29
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/29        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class IpAddressController extends BaseController<IpAddressInfo> {

    public IpAddressController(String format, String ip){
        mRequestCall = CustomApplication.getRequestService().getIpAddressInfo(format, ip);
    }

    @Override
    public void loadData() {

        mRequestCall.enqueue(new Callback<IpAddressInfo>() {
            @Override
            public void onResponse(Response<IpAddressInfo> response, Retrofit retrofit) {
                if (mApiCallBack != null && response != null && response.isSuccess()){
                    mApiCallBack.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if (mApiCallBack != null){
                    mApiCallBack.onFail(t.getMessage());
                }
            }
        });
    }
}
