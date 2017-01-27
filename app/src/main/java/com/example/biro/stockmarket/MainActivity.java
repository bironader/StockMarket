package com.example.biro.stockmarket;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.biro.stockmarket.Adapters.NaviAdapter;
import com.example.biro.stockmarket.Fragments.Currency;
import com.example.biro.stockmarket.Fragments.Stock;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    MainActivity mainActivity = this;
    private ListView listView;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private DrawerLayout drawerLayout;
    private NaviAdapter naviAdapter;
    private static final boolean flag1 = false;
//    @BindView(R.id.tabs)
//    TabLayout tabLayout;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        if (savedInstanceState == null) {
            Stock stock = new Stock();
            android.app.FragmentManager manager = getFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.Container, stock, "StcokFragment");
            ft.commit();

        } else {
            Stock stock = new Stock();
            android.app.FragmentManager manager = getFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.Container, stock, "StcokFragment");
            ft.commit();
        }

        naviAdapter = new NaviAdapter(this);
        listView = (ListView) findViewById(R.id.list);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.opendrawer, R.string.closedrawer);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        listView.setAdapter(naviAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                android.app.FragmentManager manager = getFragmentManager();
                FragmentTransaction fragmentTransaction;

                switch (position) {
                    case 0:
                        Stock stockFrg = new Stock();
                        fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.replace(R.id.Container, stockFrg, "StockFragment").commit();
                        drawerLayout.closeDrawers();
                        break;
                    case 1:
                        Currency currencyFrg = new Currency();
                        fragmentTransaction = manager.beginTransaction();
                        fragmentTransaction.replace(R.id.Container, currencyFrg, "CurrencyFragment").commit();
                        drawerLayout.closeDrawers();
                        break;

                }
            }
        });











     /*
        TelephonyManager teleMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        if (teleMgr != null){
            String countryISOCode = teleMgr.getSimCountryIso();
            Toast.makeText(this,countryISOCode,Toast.LENGTH_LONG).show();
        }

    */


    }


//    private void setupViewPager(ViewPager pager) {
//
//        Stock tab1Fragment = new Stock();
//        Currency tab2Fragment = new Currency();
//        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        viewPagerAdapter.addFragment(tab1Fragment, "Stocks");
//        viewPagerAdapter.addFragment(tab2Fragment, "Currencies");
//        viewPager.setAdapter(viewPagerAdapter);
//    }


}
