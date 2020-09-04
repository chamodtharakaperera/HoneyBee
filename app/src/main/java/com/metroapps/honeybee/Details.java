package com.metroapps.honeybee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends AppCompatActivity {

    ImageView image;
    TextView title;
    TextView price;
    ImageView addCart;


    TextView ytitle;
    TextView yprice;
    ImageView yimage;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        db = new DBHelper(this);

        addCart = findViewById(R.id.imgAddCart);

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = getIntent().getStringExtra("yname");
                String unit = getIntent().getStringExtra("yprice");
                int uprice = Integer.parseInt(unit);
                db.insertCart(food, uprice);

                Toast.makeText(getApplicationContext(), " Ordered : " +food, Toast.LENGTH_LONG).show();
            }
        });

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