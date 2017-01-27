package com.example.biro.stockmarket.Services;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.biro.stockmarket.Contracts.APIContract;
import com.example.biro.stockmarket.Models.Stocks;
import com.example.biro.stockmarket.MyApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by biro on 1/25/2017.
 */

public class StockServices extends IntentService {
    public static final int ID =0;
    public static final String key = "STOCKS";

    private ArrayList<com.example.biro.stockmarket.Models.Stocks> stocks = new ArrayList<>();
    public static final String ACTION_MyIntentService ="com.example.biro.stockmarket"; //The action which be listened in broadcast
    public StockServices() {
        super("StockServices");
    }


    @Override
    protected void onHandleIntent(Intent intent) {


        HttpURLConnection connection = null;
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(APIContract.RealStocks.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((result = reader.readLine()) != null)
                buffer.append(result); // final json string
            String jason = buffer.toString();
            JSONObject parent = new JSONObject(jason);
            JSONObject parentObj = parent.getJSONObject(APIContract.RealStocks.jquery);
            JSONObject childObj = parentObj.getJSONObject(APIContract.RealStocks.result);
            JSONArray parentArray = childObj.getJSONArray(APIContract.RealStocks.qoute);
            com.example.biro.stockmarket.Models.Stocks[] tempStocks = new com.example.biro.stockmarket.Models.Stocks[parentArray.length()];
            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject finalObject = parentArray.getJSONObject(i);
                tempStocks[i] = new com.example.biro.stockmarket.Models.Stocks();

                tempStocks[i].setSymbol(finalObject.getString(APIContract.RealStocks.Symbol));
                tempStocks[i].setName(finalObject.getString(APIContract.RealStocks.Name));
                tempStocks[i].setAverageDaily(finalObject.getString(APIContract.RealStocks.AverageDailyVolume));
                tempStocks[i].setChange(finalObject.getString(APIContract.RealStocks.Change));
                tempStocks[i].setDaysLow(finalObject.getString(APIContract.RealStocks.DaysLow));
                tempStocks[i].setDaysHigh(finalObject.getString(APIContract.RealStocks.DaysHigh));
                tempStocks[i].setYearLow(finalObject.getString(APIContract.RealStocks.YearLow));
                tempStocks[i].setYearHigh(finalObject.getString(APIContract.RealStocks.YearHigh));
                tempStocks[i].setDaysRange(finalObject.getString(APIContract.RealStocks.DaysRange));
                tempStocks[i].setLastTrade(finalObject.getString(APIContract.RealStocks.LastTradePriceOnly));
                tempStocks[i].setMarketCapitalization(finalObject.getString(APIContract.RealStocks.MarketCapitalization));
                tempStocks[i].setVolume(finalObject.getString(APIContract.RealStocks.Volume));
                stocks.add(tempStocks[i]);

                //now we need send the data back to activity using broadcast
                Intent intentResponse = new Intent();
                intentResponse.setAction(ACTION_MyIntentService);
                intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
                intentResponse.putParcelableArrayListExtra(key, stocks);
                sendBroadcast(intentResponse);

            }


        } catch (IOException | JSONException e) {

            e.printStackTrace();
        } finally {
            if (connection != null)
                connection.disconnect();
            if (reader != null)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }



}







