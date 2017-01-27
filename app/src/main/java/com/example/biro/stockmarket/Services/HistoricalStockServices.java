package com.example.biro.stockmarket.Services;

import android.app.IntentService;
import android.content.Intent;

import com.example.biro.stockmarket.Contracts.APIContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by biro on 1/25/2017.
 */

public class HistoricalStockServices extends IntentService {
    public static final String SYMBOL_KEY = "SYMBOL";
    public static final String ACTION_MyIntentService="Historical services fired up";
    public static final String key= "HISTORICAL";
    private ArrayList<String> historyStocks = new ArrayList<>();


    public HistoricalStockServices() {
        super(" HistoricalStockServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        String symbol = intent.getStringExtra(SYMBOL_KEY);
        HttpURLConnection connection = null;
        String result = "";
        BufferedReader reader = null;
        String QUERY = "select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20=%20" +"%22"+symbol +"%22"+
                "and%20startDate=" +"%22" +getEnd()+"%22" + "%20and%20endDate=%22" + getDate()+"%22";
        String finalUrl = APIContract.HistoricalStocks.historicalStocks + QUERY + APIContract.HistoricalStocks.format;

        try {
            URL url = new URL(finalUrl);
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
            String temp[] = new String[parentArray.length()];
            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject finalObject = parentArray.getJSONObject(i);
                temp[i] = finalObject.getString("High");
                historyStocks.add(temp[i]);

            }
            Intent intentResponse = new Intent();
            intentResponse.setAction(ACTION_MyIntentService);
            intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
            intentResponse.putStringArrayListExtra(key,historyStocks);
            sendBroadcast(intentResponse);


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private String getDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        return mdformat.format(calendar.getTime());

    }

    private String getEnd() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, -10);
        return mdformat.format(calendar.getTime());
    }
}