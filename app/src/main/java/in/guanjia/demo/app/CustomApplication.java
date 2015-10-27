package in.guanjia.demo.app;

import android.app.Application;
import android.content.Context;

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

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
        SharedPreferencesUtils.getInstance().Builder(mApplicationContext);
    }
}
