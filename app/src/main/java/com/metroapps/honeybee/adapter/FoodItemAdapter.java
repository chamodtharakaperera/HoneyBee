package com.metroapps.honeybee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.metroapps.honeybee.R;
import com.metroapps.honeybee.model.FoodItem;

import java.util.List;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    Context context;
    List<FoodItem> foodItemList;

    public FoodItemAdapter(Context context, List<FoodItem> foodItemList) {
        this.context = context;
        this.foodItemList = foodItemList;
    }

    public static final class FoodItemViewHolder extends RecyclerView.ViewHolder {

        ImageView foodItemImage;
        TextView price, name, restaurant;

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodItemImage = itemView.findViewById(R.id.foodItemImage);
            price = itemView.findViewById(R.id.foodItemPrice);
            name = itemView.findViewById(R.id.foodItemHotel);
            restaurant = itemView.findViewById(R.id.foodItemHotel);
        }
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fooditem_row_item, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        holder.name.setText(foodItemList.get(position).getName());
        holder.price.setText(foodItemList.get(position).getPrice());
        holder.restaurant.setText(foodItemList.get(position).getRestaurantName());
        Glide.with(context).load(foodItemList.get(position).getImageUrl()).into(holder.foodItemImage);
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }
}
