package in.guanjia.demo.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.simple.eventbus.EventBus;

import butterknife.Bind;
import butterknife.OnClick;
import in.guanjia.demo.R;
import in.guanjia.demo.base.BaseAbsFragment;
import in.guanjia.demo.bean.LoginSuccess;
import in.guanjia.demo.ui.MainActivity;
import in.guanjia.demo.ui.RegisterActivity;
import in.guanjia.demo.util.LogUtils;
import in.guanjia.demo.util.StringUtils;
import in.guanjia.demo.util.ToastUtils;
import in.guanjia.demo.util.Utils;

/**
 * Description: 登录fragment
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/20
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/20        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class LoginFragment extends BaseAbsFragment {


    @Bind(R.id.et_username)
    EditText mEtUsername;
    @Bind(R.id.et_password)
    EditText mEtPassword;
    @Bind(R.id.tv_login)
    TextView mTvLogin;

    private String mUserNameContext = "";
    private String mPasswordContent = "";

    public LoginFragment() {
        //empty construction
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_login;
    }

    @OnClick({R.id.tv_login, R.id.tv_forget_password})
    void login(View view) {
        if (view.getId() == R.id.tv_login) {   //login
            Utils.getInstance().hideSoftKeyboard(getActivity(), mEtPassword);
            if (checkInputValid()){
                onLogin();
            }
        } else if (view.getId() == R.id.tv_forget_password) {  //find password back

            Bundle bundle = new Bundle();
            bundle.putString("title", "找回密码");
            Utils.getInstance().startNewActivity(RegisterActivity.class, bundle);
        }
    }

    //登录
    private void onLogin() {
        Utils.getInstance().startNewActivity(MainActivity.class);
        EventBus.getDefault().post(new LoginSuccess());
        getActivity().finish();
    }

    private boolean checkInputValid() {
        mUserNameContext = mEtUsername.getEditableText().toString();
        LogUtils.d(TAG, "EditableText" + mUserNameContext);
        LogUtils.d(TAG, "Text" + mEtUsername.getText());
        if (StringUtils.isEmpty(mUserNameContext)) {
            ToastUtils.getInstance().showInfo(mTvLogin, R.string.username_empty);
            return false;
        }

        mPasswordContent = mEtPassword.getEditableText().toString();
        LogUtils.d(TAG, "EditableText" + mPasswordContent);
        LogUtils.d(TAG, "Text" + mEtPassword.getText());
        if (StringUtils.isEmpty(mPasswordContent)) {
            ToastUtils.getInstance().showInfo(mTvLogin, R.string.password_empty);
            return false;
        }
        return true;
    }
}
