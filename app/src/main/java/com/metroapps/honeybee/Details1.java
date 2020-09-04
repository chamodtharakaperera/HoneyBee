package com.metroapps.honeybee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Details1 extends AppCompatActivity {

    ImageView image;
    TextView title;
    TextView price;
    DBHelper db;

    ImageView addCart2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details1);

        db = new DBHelper(this);

        setTitle();
        setImage();
        setPrice();

        addCart2 = findViewById(R.id.imgAddCart2);

        addCart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String food = getIntent().getStringExtra("ename");
                String unit = getIntent().getStringExtra("eprice");
                int uprice = Integer.parseInt(unit);
                db.insertCart(food, uprice);

                Toast.makeText(getApplicationContext(), " Ordered : " +food, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setPrice() {
        price = findViewById(R.id.textView14);
        price.setText(""+getIntent().getStringExtra("eprice"));
    }

    private void setTitle() {
        title= findViewById(R.id.textView11);
        title.setText(""+getIntent().getStringExtra("ename"));
    }

    private void setImage() {
        image = findViewById(R.id.actImage);
        image.setImageResource(getIntent().getIntExtra("eimage",R.drawable.splimg));
    }


}