package in.guanjia.demo.listener;

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
public interface ApiCallBack<T> {
    void onSuccess(T t);
    void onFail(T t);
    void onFail();
}
