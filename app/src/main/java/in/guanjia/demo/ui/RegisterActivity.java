package in.guanjia.demo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import in.guanjia.demo.base.BaseAbsActivity;
import in.guanjia.demo.fragment.RegisterFragment;
import in.guanjia.demo.util.StringUtils;

/**
 * Description:注册or找回密码
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/19
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/19        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class RegisterActivity extends BaseAbsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getExtras() != null && StringUtils.notEmpty(getIntent().getExtras().getString("title"))){
            setTitle(getIntent().getExtras().getString("title"));
        }
    }

    @Override
    protected Fragment getFragment() {
        return RegisterFragment.newInstance();
    }
}
