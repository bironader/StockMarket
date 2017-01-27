package com.example.biro.stockmarket.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by biro on 12/1/2016.
 */

public class Stocks implements Parcelable {

    private String Symbol;
    private String Name;
    private String AverageDaily;
    private String Change;
    private String DaysRange;
    private String DaysLow;
    private String DaysHigh;
    private String YearHigh;
    private String YearLow;
    private String LastTrade;
    private String MarketCapitalization;
    private String Volume;
  //  private String Domain;


    public Stocks() {
    }

    public Stocks(Parcel in) {
        Symbol = in.readString();
        Name = in.readString();
        AverageDaily = in.readString();
        Change = in.readString();
        DaysRange = in.readString();
        DaysLow = in.readString();
        DaysHigh = in.readString();
        YearHigh = in.readString();
        YearLow = in.readString();
        LastTrade = in.readString();
        MarketCapitalization = in.readString();
        Volume = in.readString();
       // Domain = in.readString();
    }

    public static final Creator<Stocks> CREATOR = new Creator<Stocks>() {
        @Override
        public Stocks createFromParcel(Parcel in) {
            return new Stocks(in);
        }

        @Override
        public Stocks[] newArray(int size) {
            return new Stocks[size];
        }
    };

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAverageDaily() {
        return AverageDaily;
    }

    public void setAverageDaily(String averageDaily) {
        AverageDaily = averageDaily;
    }

    public String getChange() {
        return Change;
    }

    public void setChange(String change) {
        Change = change;
    }

    public String getDaysRange() {
        return DaysRange;
    }

    public void setDaysRange(String daysRange) {
        DaysRange = daysRange;
    }

    public String getDaysLow() {
        return DaysLow;
    }

    public void setDaysLow(String daysLow) {
        DaysLow = daysLow;
    }

    public String getDaysHigh() {
        return DaysHigh;
    }

    public void setDaysHigh(String daysHigh) {
        DaysHigh = daysHigh;
    }

    public String getYearHigh() {
        return YearHigh;
    }

    public void setYearHigh(String yearHigh) {
        YearHigh = yearHigh;
    }

    public String getYearLow() {
        return YearLow;
    }

    public void setYearLow(String yearLow) {
        YearLow = yearLow;
    }

    public String getLastTrade() {
        return LastTrade;
    }

    public void setLastTrade(String lastTrade) {
        LastTrade = lastTrade;
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public String getMarketCapitalization() {
        return MarketCapitalization;
    }

    public void setMarketCapitalization(String marketCapitalization) {
        MarketCapitalization = marketCapitalization;
    }

//    public String getDomain() {
//        return Domain;
//    }
//
//    public void setDomain(String domain) {
//        Domain = domain;
//    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Symbol);
        dest.writeString(Name);
        dest.writeString(AverageDaily);
        dest.writeString(Change);
        dest.writeString(DaysRange);
        dest.writeString(DaysLow);
        dest.writeString(DaysHigh);
        dest.writeString(YearHigh);
        dest.writeString(YearLow);
        dest.writeString(LastTrade);
        dest.writeString(MarketCapitalization);
        dest.writeString(Volume);
      //  dest.writeString(Domain);
    }
}
