package in.guanjia.demo.base;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Description:基础请求类
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/5/4
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/5/4        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class BaseRequest {

    /**实例化对象*/
    private static AsyncHttpClient client = new AsyncHttpClient();

    /**设置响应时间*/
    static {
        client.setTimeout(20000);
    }

    /**get请求*/
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        client.get(url, params, handler);
    }

    /**post请求*/
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler handler) {
        client.post(url, params, handler);
    }

    public static void cancelAll(){
        client.cancelAllRequests(true);
    }

}
