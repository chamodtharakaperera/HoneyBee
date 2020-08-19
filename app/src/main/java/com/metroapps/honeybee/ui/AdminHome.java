package com.metroapps.honeybee.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.metroapps.honeybee.R;
import com.metroapps.honeybee.ReportActivity;
import com.metroapps.honeybee.SalesReport;

public class AdminHome extends AppCompatActivity {

    private TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        user = findViewById(R.id.textView6);
        Intent nu = getIntent();
        String un = nu.getStringExtra("nuser2");
        user.setText("Welcome administrator " + un);
    }

    public void foodinsert(View view) {

        startActivity(new Intent(AdminHome.this, FoodTypeInserter.class));
    }

    public void otherShops(View view) {
        startActivity(new Intent(AdminHome.this, FoodItemInserter.class));
    }


    public void annualRep(View view) {
        startActivity(new Intent(AdminHome.this, ReportActivity.class));
    }
}