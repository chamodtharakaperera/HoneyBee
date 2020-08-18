package com.metroapps.honeybee;

import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

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


/*
    public void buget(View view) {

        Log.v("Hi all", "Failed to read value.");
        /*
        msg = findViewById(R.id.dbTest);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue(msg.getText().toString());

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                //Log.d(TAG, "Value is: " + value);
                msg.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

    }*/
}