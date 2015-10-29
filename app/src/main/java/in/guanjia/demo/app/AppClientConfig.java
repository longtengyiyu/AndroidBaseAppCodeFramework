package in.guanjia.demo.app;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import in.guanjia.demo.listener.ApiInterface;
import in.guanjia.demo.util.GsonConverterFactory;
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

//    public static final String APP_BASE_URL = "";
    public static final String APP_BASE_URL = "";


    private static ApiInterface mApiInterface = null;

    public static ApiInterface getApiClient() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        if (mApiInterface == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APP_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            mApiInterface = retrofit.create(ApiInterface.class);
        }
        return mApiInterface;
    }
}
