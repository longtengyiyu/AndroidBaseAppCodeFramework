package in.guanjia.demo.ui;

import android.support.v4.app.Fragment;

import in.guanjia.demo.base.BaseAbsActivity;
import in.guanjia.demo.fragment.LoginFragment;

/**
 * Description:登录
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/19
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/19        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class LoginActivity extends BaseAbsActivity {
    @Override
    protected Fragment getFragment() {
        return LoginFragment.newInstance();
    }
}
