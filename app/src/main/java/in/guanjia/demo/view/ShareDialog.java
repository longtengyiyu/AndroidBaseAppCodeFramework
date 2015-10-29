package in.guanjia.demo.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import in.guanjia.demo.R;
import in.guanjia.demo.app.AppContact;
import in.guanjia.demo.param.ShareParam;
import in.guanjia.demo.util.StringUtils;
import in.guanjia.demo.util.ToastUtils;

/**
 * Description:分享Dialog
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/28
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/28        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class ShareDialog implements View.OnClickListener {

    private ShareParam mParam = null;
    private IWXAPI mWxApi = null;
    private AlertDialog mDialog = null;

    protected ShareDialog(ShareParam param){
        mParam = param;
    }

    protected void showDialog(){
        if (mDialog == null) {
            View view = LayoutInflater.from(mParam.getContext()).inflate(R.layout.item_share, null, false);
            TextView tvWeiXin = (TextView) view.findViewById(R.id.tv_wei_xin);
            TextView tvWeiBo = (TextView) view.findViewById(R.id.tv_wei_bo);
            TextView tvMessage = (TextView) view.findViewById(R.id.tv_message);
            tvWeiXin.setOnClickListener(this);
            tvWeiBo.setOnClickListener(this);
            tvMessage.setOnClickListener(this);
            mDialog = new AlertDialog.Builder(mParam.getContext())
                    .setView(view)
                    .create();
            mDialog.setCanceledOnTouchOutside(false);
        }
        mDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_wei_xin:
                mParam.setPlatform(ShareParam.WEI_XIN_PLATFORM);
                break;
            case R.id.tv_wei_bo:
                mParam.setPlatform(ShareParam.WEI_BO_PLATFORM);
                break;
            case R.id.tv_message:
                mParam.setPlatform(ShareParam.MESSAGE_PLATFORM);
                break;
        }
        share2Friends(mParam);
        mDialog.dismiss();
        mDialog = null;  //频繁分享，此dialog不需要致空，如果分享频率很小建议致空处理
    }

    protected void share2Friends(ShareParam shareParam){
        switch (shareParam.getPlatform()){
            case ShareParam.WEI_XIN_PLATFORM:
                share2WeChat(shareParam);
                break;
            case ShareParam.WEI_BO_PLATFORM:
                share2WeiBo(shareParam);
                break;
            case ShareParam.MESSAGE_PLATFORM:
                share2Message(shareParam);
                break;
        }
    }

    private void share2WeChat(ShareParam shareParam){

        if (mWxApi == null) {
            mWxApi = WXAPIFactory.createWXAPI(shareParam.getContext(), AppContact.THIRD_LOGIN_WX_ID); //初始化wx api
        }

        if (!mWxApi.isWXAppInstalled()){
            ToastUtils.getInstance().showInfo(((Activity)shareParam.getContext()).findViewById(R.id.root_view), "您还没有安装微信");  //声明activity的parent view的id必须root_view，否则此处必须修改
            return;
        }

        //只实现发送文字功能，如需添加图片、url或者其他内容参考wechat adk doc
        WXTextObject textObj = new WXTextObject();  //初始化一个WXTextObject对象
        textObj.text = StringUtils.notEmpty(shareParam.getContent()) ? shareParam.getContent() : "" ;

        WXMediaMessage msg = new WXMediaMessage(); //用WXTextObject对象初始化一个WXMediaMessage对象
        msg.mediaObject = textObj;
        // msg.title = "Will be ignored";
        msg.description = "这个是测试测试测试测试";  //发送文本类型的消息时，title字段不起作用

        // 构造一个Req
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("text");  //transaction字段用于唯一标识一个请求
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneSession;  //分享到朋友圈；SendMessageToWX.Req.WXSceneSession 不分享朋友圈

        // 调用api接口发送数据到微信
        mWxApi.sendReq(req);
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private void share2Message(ShareParam shareParam){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(AppContact.SEND_MESSAGE_TO + ""));
        intent.putExtra(AppContact.SEND_MESSAGE_PARAM, shareParam.getContent());
        shareParam.getContext().startActivity(intent);
    }

    private void share2WeiBo(ShareParam shareParam){

    }

    public static final class Builder{
        private ShareParam param;
        private ShareDialog mShareDialog;

        public Builder(){

        }

        public Builder(Context context){
            if (param == null){
                param = new ShareParam();
            }
            this.param.setContext(context);
        }

        public Builder setShareScene(int scene){
            this.param.setScene(scene);
            return this;
        }

        public Builder setShareContent(String content){
            this.param.setContent(content);
            return this;
        }

        public Builder setShareUrl(String url){
            this.param.setUrl(url);
            return this;
        }

        public Builder setSharePicUrl(String picUrl){
            this.param.setPicUrl(picUrl);
            return this;
        }

        public Builder setSharePicByteArray(byte[] byteArray){
            this.param.setPic(byteArray);
            return this;
        }
        public ShareDialog create(){
            if (mShareDialog == null){
                mShareDialog = new ShareDialog(param);  //实例化对象
            }
            mShareDialog.showDialog();
            return mShareDialog;
        }
    }
}
