package in.guanjia.demo.param;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Description: loading text view param
 * Author:    Oscar
 * Version    V1.0
 * Date:      2015/9/7
 * Modification  History:
 * Date         	Author        		Version        	Description
 * -----------------------------------------------------------------------------------
 * 2015/9/7        Oscar           1.0                    1.0
 * Why & What is modified:
 */
public class TextViewParam implements Parcelable {
    public long during;  //show time
    public String content; //content with String
    public String textColor; //color with String
    public int colorResource; // content color resourceId;
    public int contentResource;  // content resourceId;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.during);
        dest.writeString(this.content);
        dest.writeString(this.textColor);
        dest.writeInt(this.colorResource);
        dest.writeInt(this.contentResource);
    }

    public TextViewParam() {
    }

    private TextViewParam(Parcel in) {
        this.during = in.readLong();
        this.content = in.readString();
        this.textColor = in.readString();
        this.colorResource = in.readInt();
        this.contentResource = in.readInt();
    }

    public static Creator<TextViewParam> CREATOR = new Creator<TextViewParam>() {
        public TextViewParam createFromParcel(Parcel source) {
            return new TextViewParam(source);
        }

        public TextViewParam[] newArray(int size) {
            return new TextViewParam[size];
        }
    };
}
