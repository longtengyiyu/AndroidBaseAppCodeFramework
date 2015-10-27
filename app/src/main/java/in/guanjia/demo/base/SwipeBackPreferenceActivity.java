package in.guanjia.demo.base;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;

import in.guanjia.demo.listener.SwipeBackListener;
import in.guanjia.demo.util.SwipeBackActivityUtils;
import in.guanjia.demo.view.SwipeBackLayout;

public class SwipeBackPreferenceActivity extends PreferenceActivity implements SwipeBackListener {
    private SwipeBackActivityUtils mHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityUtils(this);
        mHelper.onActivityCreate();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }
    
    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }
    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
