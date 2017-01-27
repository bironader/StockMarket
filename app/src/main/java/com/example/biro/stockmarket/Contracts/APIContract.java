package com.example.biro.stockmarket.Contracts;

/**
 * Created by biro on 12/1/2016.
 */

public class APIContract {


    public static class RealStocks {

        public final static String realStocks = "https://query.yahooapis.com/v1/public/yql?q=";
        public final static String query = "select%20*%20from%20yahoo.finance.quote%20where%20symbol%20%3D%20%27YHOO,AAPL,GOOGL,NVDA,AMD,VOD,MSFT,FB,HPQ,TWTR,%27";
        public final static String url = realStocks + query +
                "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        public final static String result = "results";
        public final static String jquery = "query";
        public final static String qoute = "quote";
        public final static String Symbol = "Symbol";
        public final static String AverageDailyVolume = "AverageDailyVolume";
        public final static String Change = "Change";
        public final static String DaysLow = "DaysLow";
        public final static String DaysHigh = "DaysHigh";
        public final static String YearLow = "YearLow";
        public final static String YearHigh = "YearHigh";
        public final static String MarketCapitalization = "MarketCapitalization";
        public final static String LastTradePriceOnly = "LastTradePriceOnly";
        public final static String DaysRange = "DaysRange";
        public final static String Name = "Name";
        public final static String Volume = "Volume";

    }

    public static class HistoricalStocks {
        public final static String historicalStocks = "http://query.yahooapis.com/v1/public/yql?q=";
        public final static String format = "%20&format=json%20&diagnostics=true%20&env=store://datatables.org/alltableswithkeys";

    }

    public static class Currencies {
        public static final String currency = "http://query.yahooapis.com/v1/public/yql?q=";
        public static final String query = "select*from%20yahoo.finance.xchange%20where%20pair%20in%20(%22EUREGP%22,%22USDJPY%22,%22USDEGP%22,%22USDCZK%22,%22USDDKK%22,%22USDGBP%22,%22USDHUF%22,%22USDLTL%22,%22USDLVL%22,%22USDPLN%22,%22USDRON%22,%22USDSEK%22,%22USDCHF%22,%22USDNOK%22)";
        public static final String format = "&format=json&env=store://datatables.org/alltableswithkeys";
        public static final String url = currency + query + format;
        public static final String jquery = "query";
        public static final String result = "results";
        public static final String rate = "rate";
        public static final String Name = "Name";
        public static final String Date = "Date";
        public static final String Time = "Time";
        public static final String Ask = "Ask";
        public static final String Bid = "Bid";


    }


}
