package in.guanjia.demo.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;

import in.guanjia.demo.base.BaseAbsActivity;
import in.guanjia.demo.fragment.SignFragment;

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
public class SignActivity extends BaseAbsActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionBar.hide();
        setSwipeBackEnable(false);
    }

    @Override
    protected Fragment getFragment() {
        return SignFragment.newInstance();
    }
}
