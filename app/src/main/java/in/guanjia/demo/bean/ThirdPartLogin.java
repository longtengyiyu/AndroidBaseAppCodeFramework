package in.guanjia.demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description:第三方登录返回参数实体类
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/10/21
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/10/21        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class ThirdPartLogin implements Parcelable {

    public String token;
    public ThirdPartLogin(String token){
        this.token = token;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
    }

    protected ThirdPartLogin(Parcel in) {
        this.token = in.readString();
    }

    public static final Parcelable.Creator<ThirdPartLogin> CREATOR = new Parcelable.Creator<ThirdPartLogin>() {
        public ThirdPartLogin createFromParcel(Parcel source) {
            return new ThirdPartLogin(source);
        }

        public ThirdPartLogin[] newArray(int size) {
            return new ThirdPartLogin[size];
        }
    };
}
