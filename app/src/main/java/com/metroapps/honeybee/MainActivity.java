package com.metroapps.honeybee;

import android.os.Bundle;
import android.view.Menu;

import com.metroapps.honeybee.adapter.FoodItemAdapter;
import com.metroapps.honeybee.adapter.FoodTypeAdapter;
import com.metroapps.honeybee.model.FoodItem;
import com.metroapps.honeybee.model.FoodType;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    //UI IMPLEMENTATION
    RecyclerView mtyperecycler,mrecycler;
    FoodTypeAdapter machineTypeAdapter;
    FoodItemAdapter mmadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


        //Load Code
        List<FoodType> foodTypeList = new ArrayList<>();
        foodTypeList.add(new FoodType("Single Needle",R.drawable.m1));
        foodTypeList.add(new FoodType("Double Needle",R.drawable.m2));
        foodTypeList.add(new FoodType("Overlock",R.drawable.m3));

        setMtypeRecycler(foodTypeList);

        List<FoodItem> foodItemList = new ArrayList<>();
        foodItemList.add(new FoodItem("Single Needle","Rs 25000","Bandula Restaurant",R.drawable.m1));
        foodItemList.add(new FoodItem("Double Needle","Rs 90000","Bandula Restaurant",R.drawable.m2));
        foodItemList.add(new FoodItem("Overlock","Rs 150,000","Bandula Restaurant",R.drawable.m3));

        setModelRecycler(foodItemList);

    }

    private void setMtypeRecycler(List<FoodType> foodTypeList){
        mtyperecycler = findViewById(R.id.foodTypeRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        mtyperecycler.setLayoutManager(layoutManager);
        machineTypeAdapter = new FoodTypeAdapter(this, foodTypeList);
        mtyperecycler.setAdapter(machineTypeAdapter);
    }

    private void setModelRecycler(List<FoodItem> foodItemList){
        mrecycler = findViewById(R.id.foodItemRecycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mrecycler.setLayoutManager(layoutManager);
        mmadapter = new FoodItemAdapter(this, foodItemList);
        mrecycler.setAdapter(mmadapter);
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