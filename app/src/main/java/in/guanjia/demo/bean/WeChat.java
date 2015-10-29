package in.guanjia.demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Description:
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/29
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/29        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class WeChat implements Parcelable {

    @SerializedName("openid")
    public String openId;
    @SerializedName("access_token")
    public String accessToken;
    @SerializedName("unionid")
    public String unionId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.openId);
        dest.writeString(this.accessToken);
        dest.writeString(this.unionId);
    }

    public WeChat() {
    }

    protected WeChat(Parcel in) {
        this.openId = in.readString();
        this.accessToken = in.readString();
        this.unionId = in.readString();
    }

    public static final Parcelable.Creator<WeChat> CREATOR = new Parcelable.Creator<WeChat>() {
        public WeChat createFromParcel(Parcel source) {
            return new WeChat(source);
        }

        public WeChat[] newArray(int size) {
            return new WeChat[size];
        }
    };
}
