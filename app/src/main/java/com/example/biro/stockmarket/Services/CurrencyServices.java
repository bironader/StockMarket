package com.example.biro.stockmarket.Services;

import android.app.IntentService;
import android.content.Intent;

import com.example.biro.stockmarket.Contracts.APIContract;
import com.example.biro.stockmarket.Models.Currencies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by biro on 1/26/2017.
 */

public class CurrencyServices extends IntentService {

    private ArrayList<Currencies> currencies = new ArrayList<Currencies>();
    public static final String ACTION_MyIntentService = "Currency services fired up";
    public static final String key = "CURRENCIES";

    public CurrencyServices() {
        super("CurrencyServices");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        HttpURLConnection connection = null;
        String result = "";
        BufferedReader reader = null;
        try {
            URL url = new URL(APIContract.Currencies.url);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            StringBuffer buffer = new StringBuffer();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((result = reader.readLine()) != null)
                buffer.append(result); // final json string
            String jason = buffer.toString();
            JSONObject parent = new JSONObject(jason);
            JSONObject parentObj = parent.getJSONObject(APIContract.Currencies.jquery);
            JSONObject childObj = parentObj.getJSONObject(APIContract.Currencies.result);
            JSONArray parentArray = childObj.getJSONArray(APIContract.Currencies.rate);
            com.example.biro.stockmarket.Models.Currencies[] tempCurrency = new com.example.biro.stockmarket.Models.Currencies[parentArray.length()];
            for (int i = 0; i < parentArray.length(); i++) {
                JSONObject finalObject = parentArray.getJSONObject(i);
                tempCurrency[i] = new Currencies();
                tempCurrency[i].setName(finalObject.getString(APIContract.Currencies.Name));
                tempCurrency[i].setDate(finalObject.getString(APIContract.Currencies.Date));
                tempCurrency[i].setTime(finalObject.getString(APIContract.Currencies.Time));
                tempCurrency[i].setAsk(finalObject.getString(APIContract.Currencies.Bid));
                tempCurrency[i].setBid(finalObject.getString(APIContract.Currencies.Ask));

                currencies.add(tempCurrency[i]);
            }

            Intent intentResponse = new Intent();
            intentResponse.setAction(ACTION_MyIntentService);
            intentResponse.addCategory(Intent.CATEGORY_DEFAULT);
            intentResponse.putParcelableArrayListExtra(key, currencies);
            sendBroadcast(intentResponse);


        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

    }
}
