package com.example.biro.stockmarket.Fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.biro.stockmarket.Adapters.StockAdapter;
import com.example.biro.stockmarket.DetailActivity;
import com.example.biro.stockmarket.Models.Stocks;
import com.example.biro.stockmarket.MyApp;
import com.example.biro.stockmarket.R;
import com.example.biro.stockmarket.Services.StockServices;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class Stock extends android.app.Fragment implements StockAdapter.onClickListener {


    @BindView(R.id.stock_list)
    RecyclerView recyclerView;
    private StockAdapter recyclerAdapter;
    private MyBroadcastReceiver myBroadcastReceiver;
    private ArrayList<Stocks> finalStocks;
    public Stock stock = this;
    public AlarmManager alarmManager;
    public  long interval = 1000 * 60 ;
    PendingIntent servicePendingIntent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.stock_fragment, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = new Intent(getActivity(), StockServices.class);
        Calendar calendar = Calendar.getInstance();
        // getActivity().startService(intent);
        // register broadcast
        myBroadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter(StockServices.ACTION_MyIntentService);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        getActivity().registerReceiver(myBroadcastReceiver, intentFilter);

        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE); // Use Alarm manger to run the stockservice perodic

         servicePendingIntent = PendingIntent.getService(MyApp.getAppContext(),
                StockServices.ID,
                intent,
                PendingIntent.FLAG_ONE_SHOT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval , servicePendingIntent);
        return view;
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {


            // When service fireup the brodcast will receive the data
            finalStocks = intent.getParcelableArrayListExtra(StockServices.key);
            recyclerAdapter = new StockAdapter(finalStocks); //Adapt data to recyler view
            recyclerView.setAdapter(recyclerAdapter);
            recyclerAdapter.setListener(stock);// onclick listener


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().unregisterReceiver(myBroadcastReceiver);
        alarmManager.cancel(servicePendingIntent);
    }

    @Override
    public void setOnClickLisntener(View view, int position) {

        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("Stocks", finalStocks.get(position));
        startActivity(intent);

    }

}
