package com.example.honeybee;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Menu;

import com.example.honeybee.adapter.MachineModelAdapter;
import com.example.honeybee.adapter.MachineTypeAdapter;
import com.example.honeybee.model.MachineModel;
import com.example.honeybee.model.MachineType;
import com.example.honeybee.ui.HoneyBeeSplash;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
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
    MachineTypeAdapter machineTypeAdapter;
    MachineModelAdapter mmadapter;


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
        List<MachineType> machineTypeList = new ArrayList<>();
        machineTypeList.add(new MachineType("Single Needle",R.drawable.m1));
        machineTypeList.add(new MachineType("Double Needle",R.drawable.m2));
        machineTypeList.add(new MachineType("Overlock",R.drawable.m3));

        setMtypeRecycler(machineTypeList);

        List<MachineModel>machineModelList = new ArrayList<>();
        machineModelList.add(new MachineModel("Single Needle","Rs 25000",R.drawable.m1));
        machineModelList.add(new MachineModel("Double Needle","Rs 90000",R.drawable.m2));
        machineModelList.add(new MachineModel("Overlock","Rs 150,000",R.drawable.m3));

        setModelRecycler(machineModelList);

    }

    private void setMtypeRecycler(List<MachineType> machineTypeList){
        mtyperecycler = findViewById(R.id.mtype_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        mtyperecycler.setLayoutManager(layoutManager);
        machineTypeAdapter = new MachineTypeAdapter(this,machineTypeList);
        mtyperecycler.setAdapter(machineTypeAdapter);
    }

    private void setModelRecycler(List<MachineModel>machineModelList){
        mrecycler = findViewById(R.id.mmodel_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        mrecycler.setLayoutManager(layoutManager);
        mmadapter = new MachineModelAdapter(this,machineModelList);
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