package com.example.biro.stockmarket.Adapters;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.biro.stockmarket.Models.Stocks;
import com.example.biro.stockmarket.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class StockAdapter extends RecyclerView.Adapter<StockAdapter.ViewHolder> {

    private ArrayList<Stocks> arrayList;
    private onClickListener clickListener;

    public StockAdapter(ArrayList<Stocks> arrayList) {
        this.arrayList = arrayList;

    }


    @Override
    public StockAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_row, parent, false);
        ViewHolder holder = new ViewHolder(v);

        return holder;
    }


    public void setListener(onClickListener clickListener)
    {
        this.clickListener = clickListener;

    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.symbol.setText(arrayList.get(position).getSymbol());
        holder.company.setText(arrayList.get(position).getName());
        String change = arrayList.get(position).getChange();
        if (change.charAt(0) == '+') {
            holder.change.setTextColor(Color.parseColor("#308727"));
            holder.change.setText(change);

        } else if (change.charAt(0) == '-') {
            holder.change.setTextColor(Color.parseColor("#d82323"));
            holder.change.setText(change);
        }

        holder.price.setText(arrayList.get(position).getLastTrade()+"$");

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


     class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //using butter knife instead of find view by id

        @BindView(R.id.symbol)
        TextView symbol;
        @BindView(R.id.companyname)
        TextView company;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.change)
        TextView change;


        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            ButterKnife.bind(this, v);

        }


        @Override
        public void onClick(View v) {
            if( clickListener!=null)
                clickListener.setOnClickLisntener(v,getAdapterPosition());

        }
    }

   public interface onClickListener{

        void setOnClickLisntener(View view, int position);
    }
}
