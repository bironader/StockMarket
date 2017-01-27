package com.example.biro.stockmarket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.biro.stockmarket.Models.Stocks;
import com.example.biro.stockmarket.Services.HistoricalStockServices;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.symbolStock)
    TextView symbolStock;
    @BindView(R.id.priceStock)
    TextView priceStock;
    @BindView(R.id.changeStock)
    TextView changeStock;
    @BindView(R.id.dayLowStock)
    TextView dayLowStock;
    @BindView(R.id.dayHighStock)
    TextView dayHighStock;
    @BindView(R.id.yearLowStock)
    TextView yearLowStock;
    @BindView(R.id.yearHighStock)
    TextView yearHighStock;
    @BindView(R.id.marketStock)
    TextView marketStock;
    @BindView(R.id.avStock)
    TextView avStock;
    @BindView(R.id.volumeStock)
    TextView volumeStock;
    @BindView(R.id.line_chart)
    LineChart lineChart;
    @BindView(R.id.charttitle)
    TextView chartTitle;
    MenuItem menuItem;


    private Stocks stock;
    private ArrayList<String> historyStocks;
    private MyBroadcastReceiver myBroadcastReceiver;
    private ArrayList<Entry> chartsDataY;
    private ArrayList<String> chartsDataX;
    private DatabaseReference databaseReference;
    //private static boolean isWatchList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        menuItem = (MenuItem) findViewById(R.id.addWatchList);
        ButterKnife.bind(DetailActivity.this);
        Intent intent = getIntent();
        stock = intent.getParcelableExtra("Stocks");
        databaseReference = FirebaseDatabase.getInstance().getReference();
        toolbar.setTitle(stock.getName());
        toolbar.inflateMenu(R.menu.menu);


        symbolStock.setText(stock.getSymbol());
        priceStock.setText(stock.getLastTrade());
        dayLowStock.setText(stock.getDaysLow());
        dayHighStock.setText(stock.getDaysHigh());
        chartTitle.setText(stock.getSymbol() + " Week Price Graph");
        if (stock.getChange().charAt(0) == '+') {
            changeStock.setTextColor(Color.parseColor("#308727"));
            changeStock.setText(stock.getChange());

        } else if (stock.getChange().charAt(0) == '-') {
            changeStock.setTextColor(Color.parseColor("#d82323"));
            changeStock.setText(stock.getChange());
        }
        yearLowStock.setText(stock.getYearLow());
        yearHighStock.setText(stock.getYearHigh());
        avStock.setText(stock.getAverageDaily());
        volumeStock.setText(stock.getVolume());
        marketStock.setText(stock.getMarketCapitalization());
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(HistoricalStockServices.ACTION_MyIntentService);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(myBroadcastReceiver, intentFilter);
        Intent serviceIntent = new Intent(this, HistoricalStockServices.class);
        serviceIntent.putExtra(HistoricalStockServices.SYMBOL_KEY, stock.getSymbol());
        startService(serviceIntent);
    }
    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            // When service fireup the brodcast will receive the data

            historyStocks = intent.getStringArrayListExtra(HistoricalStockServices.key);
            float yValues[] = new float[historyStocks.size()];
            String xValues[] = {"D1", "D2", "D3", "D4", "D5", "D6", "D7", "D8"};
            for (int i = 0; i < historyStocks.size(); i++) {
                yValues[i] = Float.parseFloat(historyStocks.get(i));
            }
            ArrayList<Entry> yData = new ArrayList<Entry>();
            for (int x = 0; x < yValues.length; x++) {
                yData.add(new Entry(yValues[x], x));
            }
            ArrayList<String> xData = new ArrayList<String>();
            for (int j = 0; j < xValues.length; j++) {
                xData.add(xValues[j]);
            }

            LineDataSet lineDataSet = new LineDataSet(yData, "price");
            XAxis xAxis = lineChart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            lineDataSet.setColors(new int[]{Color.parseColor("#308727")});
            LineData lineData = new LineData(xData, lineDataSet);
            lineData.setValueTextColor(Color.WHITE);
            lineData.setValueTextSize(10f);
            lineChart.setData(lineData);
            lineChart.invalidate();


            // Toast.makeText(MyApp.getAppContext(),historyStocks.get(0),Toast.LENGTH_SHORT).show();


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
    }
}


