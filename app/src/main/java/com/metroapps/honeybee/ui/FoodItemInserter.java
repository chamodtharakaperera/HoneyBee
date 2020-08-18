package com.metroapps.honeybee.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.metroapps.honeybee.R;

import java.util.HashMap;

public class FoodItemInserter extends AppCompatActivity {

    EditText txtName;
    EditText txtPrice;
    EditText txtRating;
    EditText txtRest;
    Button btnUpdate;

    private StorageReference Folder;
    private static final int ImageBack = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_inserter);
        //buttonClicked();

        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtRating = findViewById(R.id.txtRating);
        txtRest = findViewById(R.id.txtRest);
        btnUpdate = findViewById(R.id.btnUpdate);

        Folder = FirebaseStorage.getInstance().getReference().child("AsianFolder");


    }

    private void buttonClicked() {
        Button btn2 = findViewById(R.id.button2);
        Intent intent = new Intent(this, FoodTypeInserter.class);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FoodItemInserter.this, FoodTypeInserter.class));
            }
        });
    }


    public void uploadValues(View view) {
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
                        Toast.makeText(FoodItemInserter.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
                        Imagename.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                DatabaseReference imageStore = FirebaseDatabase.getInstance().getReference().child("asianfood").child(txtName.getText().toString());
                                HashMap<String, String> hashMap = new HashMap<>();
                                hashMap.put("imageurl", String.valueOf(uri));
                                hashMap.put("name", String.valueOf(txtName.getText().toString()));
                                hashMap.put("price", String.valueOf(txtPrice.getText().toString()));
                                hashMap.put("rating", String.valueOf(txtRating.getText().toString()));
                                hashMap.put("restaurant", String.valueOf(txtRest.getText().toString()));

                                imageStore.setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(FoodItemInserter.this, "Finally Completed", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        }

    }
}