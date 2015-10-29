package in.guanjia.demo.ui;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.guanjia.demo.R;
import in.guanjia.demo.app.AppContact;
import in.guanjia.demo.util.ToastUtils;
import in.guanjia.demo.view.CustomProgressDialog;
import in.guanjia.demo.view.OverScrollView;
import in.guanjia.demo.web.WebAppInterface;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fab)
    FloatingActionButton mFab;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.web_view)
    WebView mWebView;
    @Bind(R.id.root_view)
    CoordinatorLayout mCoordinatorLayout;
    @Bind(R.id.scroll_view)
    OverScrollView mScrollView;

    private WebSettings mWebSettings;
    private WebAppInterface mWebAppInterface;
    private IWXAPI mWxApi = null;
    private long mBackPressedTime = 0L;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "分享", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                onShare2WeChat();
            }
        });
        setUpWebView();
    }

    private void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void setUpWebView() {

        mWebSettings = mWebView.getSettings(); //获取web settings
        mWebSettings.setSupportZoom(false);    //不支持缩放
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setDefaultTextEncodingName("UTF-8"); //网页文字编码
        mWebSettings.setLoadsImagesAutomatically(true);   //自动加载图片
        mWebSettings.setJavaScriptEnabled(true);
        CustomWebViewClient webViewClient = new CustomWebViewClient();
        mWebView.setWebViewClient(webViewClient);
        mWebAppInterface = new WebAppInterface(MainActivity.this);
        mWebView.addJavascriptInterface(mWebAppInterface, "AndroidWebAppInterface");
        mWebView.loadUrl("http://guanjia.in/");
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

        });
    }

    private class CustomWebViewClient extends WebViewClient {
        public CustomWebViewClient() {
            super();
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) { // 重写此方法表明点击网页里面的链接还是在当前的Web view里跳转，不跳到浏览器那边
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onLoadResource(WebView view, String url) {

        }

        @Override
        public void onPageFinished(WebView view, String url) {
            CustomProgressDialog.dismissProgressDialog();
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            CustomProgressDialog.showProgressDialog(MainActivity.this, R.string.loading);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.menu_main:
                Snackbar.make(mCoordinatorLayout, "Menu select", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //==============================================发送消息（微信分享） Start===========================================================
    private void onShare2WeChat() {
        if (mWxApi == null) {
            mWxApi = WXAPIFactory.createWXAPI(this, AppContact.THIRD_LOGIN_WX_ID); //初始化wx api
        }

        if (!mWxApi.isWXAppInstalled()){
            ToastUtils.getInstance().showInfo(mCoordinatorLayout, "您还没有安装微信");
            return;
        }

        WXTextObject textObj = new WXTextObject();  //初始化一个WXTextObject对象
        textObj.text = "测试测试测试测试测试";

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
    //==================================================End=========================================================================

    @Override
    public void onBackPressed() {
        if (mBackPressedTime + 2000 > System.currentTimeMillis())
            super.onBackPressed();
        else
            ToastUtils.getInstance().showInfo(mCoordinatorLayout, R.string.exit);
        mBackPressedTime = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

}
