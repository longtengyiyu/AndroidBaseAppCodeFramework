package in.guanjia.demo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.guanjia.demo.R;
import in.guanjia.demo.base.BaseAbsFragment;
import in.guanjia.demo.ui.LoginActivity;
import in.guanjia.demo.util.LogUtils;
import in.guanjia.demo.util.StringUtils;
import in.guanjia.demo.util.ToastUtils;
import in.guanjia.demo.util.Utils;

/**
 * Description:设置密码UI
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/21
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/21        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class SetPasswordFragment extends BaseAbsFragment {

    @Bind(R.id.et_set_password)
    EditText mEtSetPassword;
    @Bind(R.id.et_confirm_password)
    EditText mEtConfirmPassword;
    @Bind(R.id.tv_next)
    TextView mTvNext;

    private String mPasswordContent = "";
    private String mConfirmPasswordContent = "";

    public SetPasswordFragment() {

    }

    public static SetPasswordFragment newInstance() {
        return new SetPasswordFragment();
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_set_password;
    }

    @OnClick(R.id.tv_next)
    void onClick() {
        Utils.getInstance().hideSoftKeyboard(getActivity(), mEtConfirmPassword);

        if (checkInputValid()) {
            Utils.getInstance().startNewActivity(LoginActivity.class);
            getActivity().finish();
        }
    }

    private boolean checkInputValid() {
        mPasswordContent = mEtSetPassword.getEditableText().toString();
        LogUtils.d(TAG, "EditableText" + mPasswordContent);
        LogUtils.d(TAG, "Text" + mEtSetPassword.getText());
        if (StringUtils.isEmpty(mPasswordContent)) {
            mEtSetPassword.requestFocus();
            mEtSetPassword.setFocusable(true);
            ToastUtils.getInstance().showInfo(mTvNext, R.string.password_empty);
            return false;
        }

        mConfirmPasswordContent = mEtConfirmPassword.getEditableText().toString();
        LogUtils.d(TAG, "EditableText" + mConfirmPasswordContent);
        LogUtils.d(TAG, "Text" + mEtConfirmPassword.getText());
        if (StringUtils.isEmpty(mConfirmPasswordContent)) {
            mEtConfirmPassword.requestFocus();
            mEtConfirmPassword.setFocusable(true);
            ToastUtils.getInstance().showInfo(mTvNext, R.string.confirm_password_empty);
            return false;
        }

        if (!mConfirmPasswordContent.equals(mPasswordContent)) {
            ToastUtils.getInstance().showInfo(mTvNext, R.string.password_not_same);
            return false;
        }
        return true;
    }

}
