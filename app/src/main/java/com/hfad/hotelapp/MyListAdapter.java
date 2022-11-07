package com.hfad.hotelapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyListAdapter extends ArrayAdapter<String> {

    String[] temp;

    public MyListAdapter(Context context, String[] t){
        super(context,R.layout.list_view_layout,t);
        this.temp=t;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View v=layoutInflater.inflate(R.layout.list_view_layout,parent,false);
        TextView tv=v.findViewById(R.id.TextViewInList);
        tv.setText(temp[position]);
        return v;
    }
}
