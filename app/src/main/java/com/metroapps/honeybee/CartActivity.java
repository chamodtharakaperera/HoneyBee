package com.metroapps.honeybee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.metroapps.honeybee.adapter.CartAdapter;
import com.metroapps.honeybee.model.Cart;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView cart;
    private RecyclerView.Adapter adapter;
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
    }

    private ArrayList<Cart> initCart() {
        ArrayList<Cart> cartlist = new ArrayList<>();

        // Start  of new code

        Cursor c = db.getCartData();
        String fname = null;
        int qty=0, price=0, tot=0;
        if (c != null && c.moveToFirst())
        {
            do
            {
                //pw = c.getString(1);
                fname = c.getString( 0 );
                qty = c.getInt( 1 );
                price = c.getInt( 2 );
                String qty2 = String.valueOf( qty );
                String price2 = String.valueOf( price );

                cartlist.add(new Cart( fname, qty2, price2 ));

            } while (c.moveToNext());
        }

        // End of new code

        /*cartlist.add(new Cart( "AAA", "10", "20" ));
        cartlist.add(new Cart( "BBB", "5", "30" ));
        cartlist.add(new Cart( "CCC", "8", "40" ));
        cartlist.add(new Cart( "DDD", "7", "50" ));
        cartlist.add(new Cart( "EEE", "6", "10" ));*/

        return cartlist;
    }
}