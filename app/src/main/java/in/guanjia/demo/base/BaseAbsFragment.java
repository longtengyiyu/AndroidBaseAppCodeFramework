package in.guanjia.demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
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
public abstract class BaseAbsFragment extends Fragment {
    protected final String TAG = getClass().getSimpleName();
    protected SharedPreferencesUtils mSharedPreferencesHelper = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferencesHelper = SharedPreferencesUtils.getInstance();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(getFragmentResourceId(), container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected abstract int getFragmentResourceId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
