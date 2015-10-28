package in.guanjia.demo.ui;

import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;

import in.guanjia.demo.R;
import in.guanjia.demo.base.BaseAbsActivity;
import in.guanjia.demo.fragment.FloatTopMenuFragment;
import in.guanjia.demo.view.ShareDialog;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_share){
            new ShareDialog.Builder(this)
                    .setShareContent("test test test test test")
                    .setShareScene(SendMessageToWX.Req.WXSceneSession)
                    .create();
        }
        return super.onOptionsItemSelected(item);
    }
}
