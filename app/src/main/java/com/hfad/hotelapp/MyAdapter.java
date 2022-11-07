package com.hfad.hotelapp;

import static com.hfad.hotelapp.MainActivity.startSecondActivityFromMainActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.hotelapp.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    List<Hotel> hotel;
    //Intent intent;
    Context context;
    //boolean check=true;

    public MyAdapter(List<Hotel> hotel){
//        Log.d("ttt"," hotel " + hotel.size());
         this.hotel=hotel;
//        Log.d("ttt"," this.hotel " + this.hotel.size());
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_view_row,parent,false
        );
//        if(check){
//          context=parent.getContext();
//          intent=new Intent(parent.getContext(),SecondActivity.class);
//          check=false;
//        }
        context=parent.getContext();
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Hotel ht = hotel.get(position);

        //use glide library here
        holder.dp.setImageResource(ht.getImage());

        ///
        holder.name.setText(ht.getName());
        holder.location.setText(ht.getLocation());
        holder.rating.setText(ht.getRating() + " Star");
//        holder.price.setText("RM "+ht.getPrizeFromRoom(position));
        holder.cl.setOnClickListener(view -> {
//            intent.putExtra("rowId",position);
//            context.startActivity(intent);
            startSecondActivityFromMainActivity(context,position);
          }
        );
//        Log.d("ttt"," posit " + position);
//        holder.price.setText(hotel.get(position).getpri);
    }

    private List<Hotel> getList(){
        return hotel;
    }
    @Override
    public int getItemCount() {
        return hotel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView dp;
        TextView name;
        TextView location;
        TextView rating;
        TextView price;
        ConstraintLayout cl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dp=itemView.findViewById(R.id.dp);
            name=itemView.findViewById(R.id.title);
            location=itemView.findViewById(R.id.location);
            rating=itemView.findViewById(R.id.rating_text);
            price=itemView.findViewById(R.id.price_text);
            cl=itemView.findViewById(R.id.constraintLayout);
        }
    }
}
