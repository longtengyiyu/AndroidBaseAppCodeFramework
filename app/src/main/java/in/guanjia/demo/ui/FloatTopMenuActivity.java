package in.guanjia.demo.ui;

import android.support.v4.app.Fragment;

import in.guanjia.demo.base.BaseAbsActivity;
import in.guanjia.demo.fragment.FloatTopMenuFragment;

/**
 * Description:顶端悬浮view测试
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/27
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/27        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class FloatTopMenuActivity extends BaseAbsActivity {
    @Override
    protected Fragment getFragment() {
        return FloatTopMenuFragment.newInstance();
    }
}
