package com.hfad.hotelapp;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.hfad.hotelapp.models.Hotel;

import org.intellij.lang.annotations.JdkConstants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    DatePickerDialog picker;
    TextView DateShowTextView1;
    TextView DateShowTextView2;
    int days;
    ImageView img;
    Hotel ht;
    TextView roomSizeTextView;
    TextView trainTextView;
    TextView checkInTextView;
    TextView checkOutText;
    TextView totalPriceText;
    TextView bookNow;
    Spinner spinner;
    int spinnerItemchoosed=0;
    public static boolean intentCreated=false;
    boolean check=false;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        Intent intent=getIntent();
        int rowId=intent.getIntExtra("rowId",0);
        contentSet(rowId);
        bookNow=findViewById(R.id.bookNow);
        bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(totalPriceText.getText().toString().equals("0.0"))
                    return;
                Intent intent2=new Intent(getApplicationContext(),MainActivity.class);
                intent2.putExtra("img",ht.getImage());
                intent2.putExtra("roomType",ht.getTypeFromRoom(spinnerItemchoosed));
                intent2.putExtra("title",ht.getName());
                intent2.putExtra("checkIn",DateShowTextView1.getText().toString());
                intent2.putExtra("checkOut",DateShowTextView2.getText().toString());
                intent2.putExtra("total",totalPriceText.getText().toString());
                intentCreated=true;
                startActivity(intent2);
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        //Toast.makeText(this, temp[i] + " clicked", Toast.LENGTH_SHORT).show();
        bookNow=findViewById(R.id.bookNow);
        totalPriceText=findViewById(R.id.totalPriceText);
//        if(i==0){
//            bookNow.setBackgroundColor(getResources().getColor(R.color.lightGrey));
//            bookNow.setTextColor(getResources().getColor(R.color.white));
//            totalPriceText.setText("0.0");
//            check=false;
//            return;
//        }
        int total=ht.getPrizeFromRoom(i)*days;
        totalPriceText.setText(String.valueOf(total));
        bookNow.setBackgroundColor(getResources().getColor(R.color.green));
        bookNow.setTextColor(getResources().getColor(R.color.white));
        check=true;
        spinnerItemchoosed=i;
        //Log.d("ooo"," ht.getPrizeFromRoom("+i+") = "+ht.getPrizeFromRoom(i)+ " days= "+days);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "Nothing clicked", Toast.LENGTH_SHORT).show();
    }

    void contentSet(int row){
        img=findViewById(R.id.image);
        ht=MainActivity.hotelData.get(row);
        img.setImageResource(ht.getImage2());
        roomSizeTextView=findViewById(R.id.roomSizeTextview);
        roomSizeTextView.setText("Room Size: "+ht.getRoomSize());
        trainTextView=findViewById(R.id.trainText);
        trainTextView.setText(ht.getLocation());
        checkInTextView = findViewById(R.id.checkInText);
        checkInTextView.setOnClickListener(View -> {
            showCalender(R.id.checkInText);
        });
        checkOutText = findViewById(R.id.checkOutText);
        checkOutText.setOnClickListener(View -> {
            showCalender(R.id.checkOutText);
        });
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        String[] roomTypes=new String[ht.getRoomtype().size()];
        //roomTypes[0]="Choose Room";
        //Log.d("ooo","ht.getRoomtype().size()="+ht.getRoomtype().size());
        for(int i=0;i<ht.getRoomtype().size();i++){
            //if((i-1)>ht.getRoomtype().size())
            //    break;
            roomTypes[i]=ht.getTypeFromRoom(i) +" "+ht.getPrizeFromRoom(i);
//            Log.d("ooo","loop="+i);
        }
        //Log.d("ooo","roomType="+roomTypes[0]+" "+roomTypes[1]+" "+roomTypes[2]+" "+roomTypes[3]);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roomTypes);
        spinner.setAdapter(aa);
        setFacilities();
    }

    private void setFacilities() {
        //String[] temp = {"FacilityOne", "FacilityTwo", "FacilityThree"};
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        LayoutInflater li = getLayoutInflater();
        for (int i = 0; i < ht.getFacilities().size(); i++) {
            View v = li.inflate(R.layout.list_view_layout, linearLayout, false);
            TextView tv = v.findViewById(R.id.TextViewInList);
            tv.setText(ht.getFacilities().get(i));
            linearLayout.addView(v);
        }
        //TextView tv=new TextView(this);
        //tv.setText("I am dynamically added");
        //LinearLayout.LayoutParams linParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //tv.setLayoutParams(linParams);
        //        MyListAdapter myListAdapter=new MyListAdapter(this,temp);
//        ListView lst=findViewById(R.id.listView);
//        lst.setAdapter(myListAdapter);
    }

    private void showCalender(int id) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        //Log.d("ooo"," "+day+" "+month+" "+year);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                if (id == R.id.checkInText) {
                    DateShowTextView1 = findViewById(R.id.DateShowTextView1);
                    DateShowTextView1.setText(i2 + "/" + i1 + "/" + i);
                } else {
                    DateShowTextView2 = findViewById(R.id.DateShowTextView2);
                    DateShowTextView2.setText(i2 + "/" + i1 + "/" + i);
                    if(DateShowTextView1.getText().toString().equals(DateShowTextView2.getText().toString())){
                        DateShowTextView2.setText("Invalid Date");
                        DateShowTextView2.setTextColor(getResources().getColor(R.color.red));
                        return;
                    }
                    try {
                        dayGet();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, year, month, day);
        picker.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        picker.show();
    }

    void dayGet() throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        Date firstDate=sdf.parse(DateShowTextView1.getText().toString());
        Date secondDate= sdf.parse(DateShowTextView2.getText().toString());

        long diffInMillies= Math.abs(secondDate.getTime()-firstDate.getTime());
        long diff= TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS);
        days= ((int) diff);
        totalPriceText=findViewById(R.id.totalPriceText);
        int total=ht.getPrizeFromRoom(0)*days;
        totalPriceText.setText(String.valueOf(total));
        //Log.d("ooo"," diff = "+ days);
    }
}
