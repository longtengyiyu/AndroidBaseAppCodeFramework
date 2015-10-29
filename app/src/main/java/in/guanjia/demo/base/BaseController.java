package in.guanjia.demo.base;

import in.guanjia.demo.listener.ApiCallBack;
import retrofit.Call;

/**
 * Description:网络请求基类
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/29
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/29        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public abstract class BaseController<T> {

    protected Call<T> mRequestCall = null;
    protected ApiCallBack<T> mApiCallBack = null;

    public void setApiCallBack(ApiCallBack<T> apiCallBack) {
        this.mApiCallBack = apiCallBack;
    }

    //获取请求call对象
    public Call getRequestCall() {
        if (mRequestCall != null) {
            return mRequestCall;
        }
        return null;
    }

    public abstract void loadData();

}
