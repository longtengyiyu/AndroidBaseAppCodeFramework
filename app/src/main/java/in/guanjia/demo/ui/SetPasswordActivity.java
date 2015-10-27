package in.guanjia.demo.ui;

import android.support.v4.app.Fragment;

import in.guanjia.demo.base.BaseAbsActivity;
import in.guanjia.demo.fragment.SetPasswordFragment;

/**
 * Description:设置密码
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/21
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/21        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class SetPasswordActivity extends BaseAbsActivity {
    @Override
    protected Fragment getFragment() {
        return SetPasswordFragment.newInstance();
    }
}
