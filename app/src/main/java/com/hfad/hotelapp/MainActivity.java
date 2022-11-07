package com.hfad.hotelapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.hotelapp.models.Hotel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Hotel> hotelData;
    ImageView img;
    TextView name;
    TextView roomType;
    TextView checkInTextView;
    TextView checkOutText;
    TextView totalPriceText;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!SecondActivity.intentCreated) {
            setContentView(R.layout.activity_main);
            initRoomList();
            setRecyclerViewAdapter();
        } else {
            setContentView(R.layout.order_review);
            handleIntent();
        }
        //startActivityForResult();
    }

    public static void startSecondActivityFromMainActivity(Context ctx,int pos){
        Intent intent2=new Intent(ctx,SecondActivity.class);
        intent2.putExtra("rowId",pos);
        //ctx.startActivityForResult();
        ctx.startActivity(intent2);
    }

    void handleIntent(){
        String i;
        img=findViewById(R.id.orderReviewImage);
        name=findViewById(R.id.titleOrderReview);
        roomType=findViewById(R.id.orderReviewRoomTypeText);
        checkInTextView=findViewById(R.id.orderReviewCheckInText);
        checkOutText=findViewById(R.id.orderReviewCheckOutText);
        totalPriceText=findViewById(R.id.orderReviewTotalText);
        intent = getIntent();
        img.setImageResource(intent.getIntExtra("img",0));
        name.setText(intent.getStringExtra("title"));
        i="Room: "+intent.getStringExtra("roomType");
        roomType.setText(i);
//        Log.d("ooo","checkIn="+intent.getStringExtra("checkIn"));
//        Log.d("ooo","checkOut="+intent.getStringExtra("checkOut"));
        i="CheckIn Date: "+intent.getStringExtra("checkIn");
        checkInTextView.setText(i);
        i="CheckOut Date: "+intent.getStringExtra("checkOut");
        checkOutText.setText(i);
        i="Total: "+intent.getStringExtra("total");
        totalPriceText.setText(i);
    }

    private void setRecyclerViewAdapter() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(hotelData));
    }

    private void initRoomList() {
        hotelData = new ArrayList<>();
        hotelData.add(new Hotel(
                R.drawable.room1_icon,
                R.drawable.room1,
                "Heritage Apartment",
                "Old Town Area",
                5,
                "28 meter",
                "10 mins to City Center",
                new String[]{"Executive-RM", "Deluxe-RM", "Superior-RM"},
                new int[]{1200, 700, 500},
                new String[]{"Housekeeping", "Toiletries", "Wi-Fi", "Mini Bar"}));
        hotelData.add(new Hotel(
                R.drawable.room2_icon,
                R.drawable.room2,
                "Ameron Hotel",
                "Shenton Way, Down Town",
                3,
                "25 meter",
                "25 mins to Subway",
                new String[]{"Deluxe- RM", "Superior-RM", "Single- RM"},
                new int[]{500, 415, 300},
                new String[]{"Housekeeping", "Toiletries", "Wi-Fi", "Refrigerator"}));
        hotelData.add(new Hotel(
                R.drawable.room3_icon,
                R.drawable.room3,
                "Dorsett Studio Apartment",
                "City Center",
                4,
                "28 meter",
                "5 mins to Bus Station",
                new String[]{"Premier- RM", "Deluxe- RM", "Superior- RM"},
                new int[]{900, 600, 415},
                new String[]{"Kitchenette", "Toiletries", "Wi-Fi", "Refrigerator"}));
        hotelData.add(new Hotel(
                R.drawable.room4_icon,
                R.drawable.room4,
                "Travelodge Harbourfront ",
                "Harbourfront",
                3,
                "20 meter",
                "1.5km to City Center",
                new String[]{"Family Room- RM", "Deluxe- RM"},
                new int[]{600, 400},
                new String[]{"Breakfast", "Toiletries", "Wi-Fi", "Refrigerator"}));
        hotelData.add(new Hotel(
                R.drawable.room5_icon,
                R.drawable.room5,
                "Clover Apartment",
                "East-West Coast",
                2,
                "19 meter",
                "10km to City Center",
                new String[]{"Deluxe- RM", "Superior- RM", "Single- RM"},
                new int[]{450, 370, 200},
                new String[]{"Toiletries", "Wi-Fi", "Drinking Water"}));
        hotelData.add(new Hotel(
                R.drawable.room6_icon,
                R.drawable.room6,
                "Wonderloft Hostel",
                "China Town",
                3,
                "30 meter",
                "220 meters to public transportation",
                new String[]{"Premium Room- RM", "Dormitory Room- RM"},
                new int[]{350, 160},
                new String[]{"Wi-Fi", "Shower", "Laundry"})
        );
    }
}