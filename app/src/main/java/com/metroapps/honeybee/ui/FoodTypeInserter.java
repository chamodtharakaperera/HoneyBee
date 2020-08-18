package com.metroapps.honeybee.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metroapps.honeybee.R;

import java.util.HashMap;

public class FoodTypeInserter extends AppCompatActivity {
    TextView msg;

    //Uploadtext
    EditText name;
    EditText price;

    private StorageReference Folder;
    private static final int ImageBack = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type_inserter);

        name = findViewById(R.id.nameText);
        price = findViewById(R.id.priceText);
        // age = findViewById(R.id.ageText);

        Folder = FirebaseStorage.getInstance().getReference().child("ImageFolder");

        jumptonext();
    }


    private void jumptonext() {
        Button btnh = findViewById(R.id.btnSwitch);
        Intent intent = new Intent(this, FoodItemInserter.class);
        btnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodTypeInserter.this, FoodItemInserter.class));
            }
        });
    }

    public void UploadData(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, ImageBack);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImageBack) {

            if (resultCode == RESULT_OK) {
                Uri ImageData = data.getData();

                final StorageReference Imagename = Folder.child("image" + ImageData.getLastPathSegment());

                Imagename.putFile(ImageData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(FoodTypeInserter.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        Imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                DatabaseReference imageStore = FirebaseDatabase.getInstance().getReference().child("fooditem").child(name.getText().toString());
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("imageurl", String.valueOf(uri));
                                hashMap.put("name", String.valueOf(name.getText().toString()));
                                hashMap.put("price", String.valueOf(price.getText().toString()));
                                imageStore.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(FoodTypeInserter.this, "Finally Completed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }

    }


    public void setData(View view) {
        Log.v("Hi all", "I am chamod perera");
        msg = findViewById(R.id.dbTest);
        msg.setText("Chamod IS a firebase hero");
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");


        myRef.setValue(msg.getText().toString());
        Log.v("Hi all", "Bollaw yakkayani db test ekath read vuna yako");
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
    }
}