package in.guanjia.demo.app;

import android.app.Application;
import android.content.Context;

import in.guanjia.demo.listener.ApiInterface;
import in.guanjia.demo.util.SharedPreferencesUtils;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/20
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/20        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class CustomApplication extends Application {

    public static Context mApplicationContext = null;

    public static ApiInterface mRequestService = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();                         //初始化Application上下文
        SharedPreferencesUtils.getInstance().Builder(mApplicationContext);     //初始化sharedPreferences
        mRequestService = AppClientConfig.getApiClient();                      //初始化网络请求
    }

    public static ApiInterface getRequestService() {
        return mRequestService;
    }
}
