package com.example.biro.stockmarket.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.biro.stockmarket.Models.NaviModel;
import com.example.biro.stockmarket.R;

import java.util.ArrayList;

/**
 * Created by biro on 11/22/2016.
 */

public class NaviAdapter extends BaseAdapter {

    Context context;
    ViewManger manger;

    NaviModel [] temp = new NaviModel[2];

    public NaviAdapter(Context context) {
        this.context=context;
        int images[]={R.drawable.stocksicon,R.drawable.currencyicon};
        String []title= context.getResources().getStringArray(R.array.Navi);

        for (int i = 0 ; i<images.length;i++)
        {
            temp[i] = new NaviModel();
            temp[i].setImages(images[i]);
            temp[i].setTitles(title[i]);
        }
    }

    @Override
    public int getCount() {
        return temp.length;
    }

    @Override
    public Object getItem(int position) {
        return temp[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) // creating row for first time
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.navi_row, parent, false);
            manger = new ViewManger(convertView);
            convertView.setTag(manger);

        }
        else //recyling
        {
            manger = (ViewManger) convertView.getTag(); // no new operation

        }

        manger.text.setText(temp[position].getTitles());
        manger.icon.setImageResource(temp[position].getImages());

        return convertView;
    }

    public class ViewManger {

        ImageView icon;
        TextView text;

        ViewManger(View v) {
            icon = (ImageView) v.findViewById(R.id.imageNavi);
            text = (TextView) v.findViewById(R.id.textNavi);

        }
    }
    }




