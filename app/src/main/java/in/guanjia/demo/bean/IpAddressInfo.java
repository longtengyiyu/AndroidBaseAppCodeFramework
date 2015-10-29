package in.guanjia.demo.bean;

import android.os.Parcel;
import android.os.Parcelable;

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
public class IpAddressInfo implements Parcelable {
    public int ret;
    public int start;
    public int end;
    public String country;
    public String province;
    public String city;
    public String district;
    public String isp;
    public String type;
    public String desc;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ret);
        dest.writeInt(this.start);
        dest.writeInt(this.end);
        dest.writeString(this.country);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.district);
        dest.writeString(this.isp);
        dest.writeString(this.type);
        dest.writeString(this.desc);
    }

    public IpAddressInfo() {
    }

    protected IpAddressInfo(Parcel in) {
        this.ret = in.readInt();
        this.start = in.readInt();
        this.end = in.readInt();
        this.country = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.district = in.readString();
        this.isp = in.readString();
        this.type = in.readString();
        this.desc = in.readString();
    }

    public static final Parcelable.Creator<IpAddressInfo> CREATOR = new Parcelable.Creator<IpAddressInfo>() {
        public IpAddressInfo createFromParcel(Parcel source) {
            return new IpAddressInfo(source);
        }

        public IpAddressInfo[] newArray(int size) {
            return new IpAddressInfo[size];
        }
    };
}
