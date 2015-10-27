package in.guanjia.demo.fragment;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import in.guanjia.demo.R;
import in.guanjia.demo.base.BaseAbsFragment;
import in.guanjia.demo.ui.SetPasswordActivity;
import in.guanjia.demo.util.LogUtils;
import in.guanjia.demo.util.StringUtils;
import in.guanjia.demo.util.ToastUtils;
import in.guanjia.demo.util.Utils;

/**
 * Description:注册or找回密码
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/20
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/20        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class RegisterFragment extends BaseAbsFragment {

    @Bind(R.id.tv_get_valid_code)
    TextView mTvGetValidCode;
    @Bind(R.id.et_username)
    EditText mEtUsername;
    @Bind(R.id.et_valid_code)
    EditText mEtValidCode;
    private String mUserNameContext = "";
    private String mValidCodeContext = "";

    public RegisterFragment() {

    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    protected int getFragmentResourceId() {
        return R.layout.fragment_register;
    }

    @OnClick({R.id.tv_get_valid_code, R.id.tv_next})
    void onClick(View view) {
        if (view.getId() == R.id.tv_get_valid_code) {
            countDownTimer.start();
            mTvGetValidCode.setEnabled(false);
        } else if (view.getId() == R.id.tv_next) {
            Utils.getInstance().hideSoftKeyboard(getActivity(),  mEtValidCode);
            if (checkInputValid()){
                Utils.getInstance().startNewActivity(SetPasswordActivity.class);
                getActivity().finish();
            }
        }
    }

    private CountDownTimer countDownTimer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            mTvGetValidCode.setText((millisUntilFinished/1000) + "s" + "后重试");
        }

        @Override
        public void onFinish() {
            mTvGetValidCode.setEnabled(true);
            mTvGetValidCode.setText("重新获取");
        }
    };

    private boolean checkInputValid() {
        mUserNameContext = mEtUsername.getEditableText().toString();
        LogUtils.d(TAG, "EditableText" + mUserNameContext);
        LogUtils.d(TAG, "Text" + mEtUsername.getText());
        if (StringUtils.isEmpty(mUserNameContext)) {
            mEtUsername.requestFocus();
            mEtUsername.setFocusable(true);
            ToastUtils.getInstance().showInfo(mTvGetValidCode, R.string.username_empty);
            return false;
        }

        mValidCodeContext = mEtValidCode.getEditableText().toString();
        LogUtils.d(TAG, "EditableText" + mValidCodeContext);
        LogUtils.d(TAG, "Text" + mEtValidCode.getText());
        if (StringUtils.isEmpty(mValidCodeContext)) {
            mEtValidCode.requestFocus();
            mEtValidCode.setFocusable(true);
            ToastUtils.getInstance().showInfo(mTvGetValidCode, R.string.password_empty);
            return false;
        }

        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }

}
