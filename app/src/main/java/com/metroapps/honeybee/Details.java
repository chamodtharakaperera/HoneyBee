package com.metroapps.honeybee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Details extends AppCompatActivity {

    ImageView image;
    TextView title;
    TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        setTitle();
        setImage();
        setPrice();
    }

    private void setPrice() {
        price = findViewById(R.id.textView14);
        price.setText("Price"+getIntent().getStringExtra("eprice"));
    }

    private void setTitle() {
        title= findViewById(R.id.textView11);
        title.setText("Name"+getIntent().getStringExtra("ename"));
    }

    private void setImage() {
        image = findViewById(R.id.actImage);
        image.setImageResource(getIntent().getIntExtra("eimage",R.drawable.splimg));
    }
}