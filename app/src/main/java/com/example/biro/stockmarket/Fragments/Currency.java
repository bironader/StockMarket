package com.example.biro.stockmarket.Fragments;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.biro.stockmarket.Adapters.CurrencyAdapter;
import com.example.biro.stockmarket.Adapters.StockAdapter;
import com.example.biro.stockmarket.Models.Currencies;
import com.example.biro.stockmarket.MyApp;
import com.example.biro.stockmarket.R;
import com.example.biro.stockmarket.Services.CurrencyServices;
import com.example.biro.stockmarket.Services.StockServices;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;


@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class Currency extends Fragment {

    @BindView(R.id.currency_list)
    RecyclerView recyclerView;

    private AlarmManager alarmManager;
    PendingIntent servicePendingIntent;
    long interval = 1000 * 60 * 3;
    private CurrencyAdapter recyclerAdapter;
    MyBroadcastReceiver myBroadcastReceiver;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.currency_fragment, container, false);
        ButterKnife.bind(this,view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        myBroadcastReceiver = new MyBroadcastReceiver();// Register the broadcast
        Intent intent = new Intent(getActivity(), CurrencyServices.class);
        IntentFilter intentFilter = new IntentFilter(CurrencyServices.ACTION_MyIntentService);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        getActivity().registerReceiver(myBroadcastReceiver, intentFilter);

        Calendar calendar = Calendar.getInstance();

        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE); // Use Alarm manager to run the currendy service perodic
        servicePendingIntent = PendingIntent.getService(MyApp.getAppContext(),
                1,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, servicePendingIntent);

        return view;

    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            // When service fireup the brodcast will receive the data
            ArrayList<Currencies> finalCurrency = intent.getParcelableArrayListExtra(CurrencyServices.key);
            recyclerAdapter = new CurrencyAdapter(finalCurrency); //Adapt data to recycler view
            recyclerView.setAdapter(recyclerAdapter);


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().unregisterReceiver(myBroadcastReceiver); //when fragment is destroyed unregister the broadcast
        alarmManager.cancel(servicePendingIntent);// and cancel the alarm
    }


}
