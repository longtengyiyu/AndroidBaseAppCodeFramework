package in.guanjia.demo.web;

import android.view.View;
import android.webkit.JavascriptInterface;

import in.guanjia.demo.util.LogUtils;
import in.guanjia.demo.util.ToastUtils;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/22
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/22        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class WebAppInterface {

    private static final String TAG = "WebAppInterface";

    /**
     *
     * @param view
     * @param toast
     */
    @JavascriptInterface
    public void showToast(View view, String toast) {
        LogUtils.d(TAG, "toast = " + toast);
        ToastUtils.getInstance().showInfo(view, toast);
    }

}
