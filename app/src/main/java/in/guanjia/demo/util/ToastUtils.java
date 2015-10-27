package in.guanjia.demo.util;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Description:提示信息
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/21
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/21        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class ToastUtils {

    private static ToastUtils toastUtils = null;

    private ToastUtils(){

    }

    public static ToastUtils getInstance(){
        if (toastUtils == null){
            toastUtils = new ToastUtils();
        }
        return toastUtils;
    }

    public void showInfo(View view, int contentId){
       Snackbar.make(view, contentId, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    public void showInfo(View view, String content){
        Snackbar.make(view, content, Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

}
