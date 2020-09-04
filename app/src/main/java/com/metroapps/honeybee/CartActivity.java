package com.metroapps.honeybee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.metroapps.honeybee.adapter.CartAdapter;
import com.metroapps.honeybee.model.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cart;
    private RecyclerView.Adapter adapter;
    private TextView tot;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cart );

        db = new DBHelper(this);

        ArrayList<Cart> carts3 = initCart();

        this.cart = (RecyclerView)findViewById( R.id.cart );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( this );
        this.cart.setLayoutManager( mLayoutManager );

        adapter = new CartAdapter(carts3);
        this.cart.setAdapter( adapter );

        tot = findViewById(R.id.txtTotal);
        Cursor d = db.getTotal();
        if (d != null && d.moveToFirst())
        {
            do
            {
                int total = d.getInt( 0 );
                tot.setText(String.valueOf(total));
            } while (d.moveToNext());
        }
    }

    private ArrayList<Cart> initCart() {
        ArrayList<Cart> cartlist = new ArrayList<>();

        Cursor c = db.getCartData();
        String fname = null;
        int qty=0, price=0, tot=0;
        if (c != null && c.moveToFirst())
        {
            do
            {
                fname = c.getString( 0 );
                qty = c.getInt( 1 );
                price = c.getInt( 2 );
                String qty2 = String.valueOf( qty );
                String price2 = String.valueOf( price );

                cartlist.add(new Cart( fname, qty2, price2 ));

            } while (c.moveToNext());
        }


        return cartlist;
    }
}