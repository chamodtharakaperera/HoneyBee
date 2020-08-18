package com.metroapps.honeybee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.metroapps.honeybee.R;
import com.metroapps.honeybee.model.FoodType;

import java.util.List;

public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.FoodTypeViewHolder> {

    Context context;
    List<FoodType> foodTypeList;

    public FoodTypeAdapter(Context context, List<FoodType> foodTypeList) {
        this.context = context;
        this.foodTypeList = foodTypeList;
    }

    public static final class FoodTypeViewHolder extends RecyclerView.ViewHolder {

        ImageView foodTypeImage;
        TextView foodTypeName;

        public FoodTypeViewHolder(View view) {
            super(view);
            foodTypeImage = itemView.findViewById(R.id.foodTypeImage);
            foodTypeName = itemView.findViewById(R.id.foodTypeTitle);
        }
    }

    @NonNull
    @Override
    public FoodTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.foodtype_row_item, parent, false);
        return new FoodTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodTypeViewHolder holder, int position) {
        holder.foodTypeImage.setImageResource(foodTypeList.get(position).getImageUrl());
        holder.foodTypeName.setText(foodTypeList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return foodTypeList.size();
    }
}
