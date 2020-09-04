package com.metroapps.honeybee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    ImageView image;
    TextView title;
    TextView price;


    TextView ytitle;
    TextView yprice;
    ImageView yimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);



        setyTitle();
        setyPrice();
        setyImage();
    }

    private void setyImage() {
        image = findViewById(R.id.actImage);
        image.setImageResource(getIntent().getIntExtra("yimage",R.drawable.splimg));
    }

    private void setyPrice() {
        title= findViewById(R.id.textView14);
        title.setText(""+getIntent().getStringExtra("yprice"));
    }

    private void setyTitle() {
        title= findViewById(R.id.textView11);
        title.setText(""+getIntent().getStringExtra("yname"));
    }


}