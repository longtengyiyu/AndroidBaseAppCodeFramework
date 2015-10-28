package in.guanjia.demo.param;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Description:分享实体类
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/28
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/28        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class ShareParam implements Serializable{

    public static final int WEI_BO_PLATFORM = 0;
    public static final int WEI_XIN_PLATFORM = 1;
    public static final int MESSAGE_PLATFORM = 3;

    private Context context;            //activity上下文
    private int platform;               //分享的平台，微信、微博
    private int scene;                  //微信字段，分享的位置，朋友圈、好友，
    private String content;             //分享的内容
    private String url;                 //分享的连接
    private String picUrl;              //分享网络图片
    private byte[] pic;                 //图片byte数组，需要bitmap转byte[]

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public int getScene() {
        return scene;
    }

    public void setScene(int scene) {
        this.scene = scene;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }
}
