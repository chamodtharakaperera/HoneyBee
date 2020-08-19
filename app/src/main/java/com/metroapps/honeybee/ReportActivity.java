package com.metroapps.honeybee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ReportActivity extends AppCompatActivity {

    private EditText year, year2;
    private Button Slsrep, Slsrep2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        year = findViewById(R.id.txtYear);
        Slsrep = findViewById(R.id.btnSalesRep);

        year2 = findViewById(R.id.txtYear2);
        Slsrep2 = findViewById(R.id.btnSalesRep2);

        Slsrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearVal = year.getText().toString();

                Intent rep1 = new Intent(ReportActivity.this, SalesReport.class);
                rep1.putExtra("salesyr", yearVal);
                startActivity(rep1);

            }
        });

        Slsrep2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String yearVal2 = year2.getText().toString();

                Intent rep1 = new Intent(ReportActivity.this, SalesReport.class);
                rep1.putExtra("salesyr2", yearVal2);
                startActivity(rep1);

            }
        });
    }
}