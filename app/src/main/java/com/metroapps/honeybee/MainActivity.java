package com.metroapps.honeybee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.metroapps.honeybee.adapter.FoodItemAdapter;
import com.metroapps.honeybee.adapter.FoodTypeAdapter;
import com.metroapps.honeybee.model.FoodItem;
import com.metroapps.honeybee.model.FoodType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    //UI IMPLEMENTATION
    RecyclerView foodTypeRecycler, foodItemRecycler;
    FoodTypeAdapter foodTypeAdapter;
    FoodItemAdapter foodItemAdapter;

    //Firebase
    private DatabaseReference myRef;
    private DatabaseReference myRefAsia;

    private TextView txt1, txt2;
    private ImageButton call, loc2, msg;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        call = findViewById(R.id.btn_call);
        loc2 = findViewById(R.id.btn_location2);
        msg = findViewById(R.id.btn_message);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = "0710689099";
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intent);
            }
        });

        loc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maps2 = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(maps2);
            }
        });

        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*Intent smsIntent = new Intent(Intent.ACTION_VIEW);

                smsIntent.setData(Uri.parse("0771825536"));
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address", new String("0710689099"));
                smsIntent.putExtra("sms_body", "Hello world this is the Honey Bee Food Systems ");

                try {
                    startActivity(smsIntent);
                    finish();
                    //Log.i("Finished sending SMS...", "");
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(MainActivity.this,
                            "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
                }*/

                Intent cart = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(cart);
            }
        });

/*
        Intent nu = getIntent();
        String un = nu.getStringExtra("nuser");


        View header = navigationView.getHeaderView(0);
        txt1 = (TextView) header.findViewById(R.id.nav_uname);
        txt1.setText("User");
        txt2 = (TextView) header.findViewById(R.id.nav_utype);
        txt2.setText(un);
*/
/*
        //Load Code
        List<FoodType> foodTypeList = new ArrayList<>();
        foodTypeList.add(new FoodType("Single Needle", R.drawable.m1));
        foodTypeList.add(new FoodType("Double Needle", R.drawable.m2));
        foodTypeList.add(new FoodType("Overlock", R.drawable.m3));

        setFoodTypeRecycler(foodTypeList);

        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(new FoodItem("Single Needle", "Rs 25000", "Bandula Restaurant", R.drawable.m1));
        foodItemList.add(new FoodItem("Double Needle", "Rs 90000", "Bandula Restaurant", R.drawable.m2));
        foodItemList.add(new FoodItem("Overlock", "Rs 150,000", "Bandula Restaurant", R.drawable.m3));

        setFoodItemRecycler(foodItemList);*/

        // now here we will add some dummy data to out model class
        final List<FoodType> FoodTypeList = new ArrayList<>();

        //Firebase
        myRef = FirebaseDatabase.getInstance().getReference();
        Query query = myRef.child("fooditem");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ClearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FoodType type = new FoodType();
                    type.setImageUrl(snapshot.child("imageurl").getValue().toString());
                    type.setName(snapshot.child("name").getValue().toString());

                    FoodTypeList.add(type);
                }
                setFoodTypeRecycler(FoodTypeList);
            }

            private void ClearAll() {
                if (FoodTypeList != null) {
                    FoodTypeList.clear();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        final List<FoodItem> FoodItemList = new ArrayList<>();

        //Firebase
        myRefAsia = FirebaseDatabase.getInstance().getReference();
        Query query1 = myRefAsia.child("asianfood");
        query1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                clearAll();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FoodItem item = new FoodItem();
                    item.setImageUrl(snapshot.child("imageurl").getValue().toString());
                    item.setName(snapshot.child("name").getValue().toString());
                    item.setPrice(snapshot.child("price").getValue().toString());
                    item.setRestaurantName(snapshot.child("restaurant").getValue().toString());

                    FoodItemList.add(item);
                }
                setFoodItemRecycler(FoodItemList);
            }

            private void clearAll() {
                if (FoodItemList != null) {
                    FoodItemList.clear();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void setFoodTypeRecycler(List<FoodType> foodTypeList) {
        foodTypeRecycler = findViewById(R.id.foodTypeRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        foodTypeRecycler.setLayoutManager(layoutManager);
        foodTypeAdapter = new FoodTypeAdapter(this, foodTypeList);
        foodTypeRecycler.setAdapter(foodTypeAdapter);
    }

    private void setFoodItemRecycler(List<FoodItem> foodItemList) {
        foodItemRecycler = findViewById(R.id.foodItemRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        foodItemRecycler.setLayoutManager(layoutManager);
        foodItemAdapter = new FoodItemAdapter(this, foodItemList);
        foodItemRecycler.setAdapter(foodItemAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}