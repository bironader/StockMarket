package com.example.biro.stockmarket.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by biro on 1/26/2017.
 */

public class Currencies implements Parcelable {

    private String Name;
    private String Date;
    private String Time;
    private String Ask;
    private String Bid;

    public Currencies()
    {

    }


    public Currencies(Parcel in) {
        Name = in.readString();
        Date = in.readString();
        Time = in.readString();
        Ask = in.readString();
        Bid = in.readString();
    }

    public static final Creator<Currencies> CREATOR = new Creator<Currencies>() {
        @Override
        public Currencies createFromParcel(Parcel in) {
            return new Currencies(in);
        }

        @Override
        public Currencies[] newArray(int size) {
            return new Currencies[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAsk() {
        return Ask;
    }

    public void setAsk(String ask) {
        Ask = ask;
    }

    public String getBid() {
        return Bid;
    }

    public void setBid(String bid) {
        Bid = bid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Date);
        dest.writeString(Time);
        dest.writeString(Ask);
        dest.writeString(Bid);
    }
}
