package com.acchain.community.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 功能----基础model
 * <p>
 * Created by ACChain on 2017/9/25.
 *
 * @param <T> 数据真实的model类型
 */

public class BaseHttpBean<T extends Object> extends BaseBean implements Parcelable {

    public static Creator CREATOR = new Creator() {
        @Override
        public Object createFromParcel(Parcel parcel) {
            return null;
        }

        @Override
        public Object[] newArray(int i) {
            return new Object[0];
        }
    };

    /**
     * 结果码,请求状态码
     */
    public int result;
    public String message;
    public String token;
    public String systemTime;
    public T dataSet;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + this.hashCode() + "数据:" + dataSet;
    }
}
