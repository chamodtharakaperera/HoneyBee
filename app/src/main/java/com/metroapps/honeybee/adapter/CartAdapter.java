package com.metroapps.honeybee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metroapps.honeybee.R;
import com.metroapps.honeybee.model.Cart;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>
{

    private ArrayList<Cart> cart;

    public CartAdapter(ArrayList<Cart> cart3)
    {
        this.cart = cart3;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from( parent.getContext() ).inflate( R.layout.item_cart, parent, false );
        return new CartViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart1 = cart.get( position );
        holder.name.setText( cart1.getItemName() );
        holder.qty.setText( cart1.getIqty() );
        holder.price.setText( cart1.getIprice() );

    }

    @Override
    public int getItemCount() {
        if(cart != null)
            return cart.size();
        else
            return 0;
    }

    public static class CartViewHolder extends RecyclerView.ViewHolder
    {
        public final TextView name;
        public final TextView qty;
        public final TextView price;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById( R.id.tiname );
            qty = itemView.findViewById( R.id.tiqty );
            price = itemView.findViewById( R.id.tiprice );
        }
    }
}
