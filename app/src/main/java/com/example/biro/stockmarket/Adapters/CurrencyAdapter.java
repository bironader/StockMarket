package com.example.biro.stockmarket.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.biro.stockmarket.Fragments.Currency;
import com.example.biro.stockmarket.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.biro.stockmarket.Models.Currencies;


/**
 * Created by biro on 1/26/2017.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    private  ArrayList<Currencies> currencies;

    public CurrencyAdapter(ArrayList<Currencies> currencies)
    {
        this.currencies = currencies;

    }

    @Override
    public CurrencyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.currency_row, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(currencies.get(position).getName());
        holder.date.setText(currencies.get(position).getDate());
        holder.time.setText(currencies.get(position).getTime());
        holder.bid.setText("Bid: "+currencies.get(position).getBid());
        holder.ask.setText("Ask: "+currencies.get(position).getAsk());



    }



    @Override
    public int getItemCount() {
        return currencies.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder  {
        //using butter knife instead of find view by id

        @BindView(R.id.currencyName)
        TextView name;
        @BindView(R.id.currencyDate)
        TextView date;
        @BindView(R.id.currencyTime)
        TextView time;
        @BindView(R.id.currencyBid)
        TextView bid;
        @BindView(R.id.currencyAsk)
        TextView ask;


        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);

        }




    }

}
