package in.guanjia.demo.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.simple.eventbus.EventBus;

import in.guanjia.demo.app.AppContact;
import in.guanjia.demo.bean.ThirdPartLogin;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/21
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/21        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI mWxApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWxApi = WXAPIFactory.createWXAPI(this, AppContact.THIRD_LOGIN_WX_ID);
        mWxApi.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWxApi.handleIntent(intent, this);
    }


    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (ConstantsAPI.COMMAND_SENDAUTH == baseResp.getType()) {
            switch (baseResp.errCode) {
                case BaseResp.ErrCode.ERR_OK:
                    SendAuth.Resp localResp = (SendAuth.Resp) baseResp;
                    EventBus.getDefault().post(new ThirdPartLogin(localResp.code));
                    break;
                case BaseResp.ErrCode.ERR_USER_CANCEL: //取消

                    break;
                case BaseResp.ErrCode.ERR_AUTH_DENIED: //拒绝授权

                    break;
                case BaseResp.ErrCode.ERR_UNSUPPORT://不支持的客户端

                    break;
                case BaseResp.ErrCode.ERR_SENT_FAILED: //授权失败

                    break;
                case BaseResp.ErrCode.ERR_COMM: //一般错误？

                    break;
            }
        }else if(ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX == baseResp.getType()){  //发送消息（分享）

        }
        finish();
    }

}
