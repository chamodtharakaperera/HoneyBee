package com.metroapps.honeybee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;

public class Details extends AppCompatActivity {

    ImageView image, cartImage;
    TextView title;

    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        cartImage = findViewById( R.id.imgAddCart );
        title= findViewById(R.id.textView11);

        db = new DBHelper(this);

        setTitle();
        setImage();

        cartImage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fdname = getIntent().getStringExtra("ename");
                Toast.makeText(getApplicationContext(), "You ordered : "+fdname, Toast.LENGTH_LONG).show();

                try{ db.insertCart(fdname); }
                catch (Exception e1)
                {Toast.makeText(getApplicationContext(), "Error(s)", Toast.LENGTH_LONG).show();}

            }
        } );
    }

    private void setTitle() {

        title.setText("Name : "+getIntent().getStringExtra("ename"));
    }

    private void setImage() {

        image = findViewById(R.id.actImage);
        image.setImageResource(getIntent().getIntExtra("eimage",R.drawable.splimg));
    }
}