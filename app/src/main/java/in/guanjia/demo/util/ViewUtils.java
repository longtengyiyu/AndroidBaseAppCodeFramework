package in.guanjia.demo.util;

import android.view.View;

/**
 * Description:Views显示帮助类（可同时设置多个view）
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/29
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/29        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class ViewUtils {

    public static void setViewsGone(boolean isGone, View... views){
        for (View view : views) {
            view.setVisibility(isGone ? View.GONE : View.VISIBLE);
        }
    }
}
