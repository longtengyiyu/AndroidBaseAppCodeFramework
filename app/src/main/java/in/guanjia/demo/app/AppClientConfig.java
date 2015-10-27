package in.guanjia.demo.app;

import java.util.concurrent.Executor;

import in.guanjia.demo.listener.ApiInterface;
import retrofit.Retrofit;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/21
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/21        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class AppClientConfig {

    public static final String WX_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static final String APP_BASE_URL = "";

    private static ApiInterface mApiInterface;

    public static ApiInterface getApiClient() {
        if (mApiInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APP_BASE_URL)

                    .build();
            mApiInterface = retrofit.create(ApiInterface.class);
        }
        return mApiInterface;
    }
}
