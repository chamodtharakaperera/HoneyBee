package com.example.honeybee.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.honeybee.R;
import com.example.honeybee.model.MachineModel;

import java.util.List;

public class MachineModelAdapter extends RecyclerView.Adapter<MachineModelAdapter.MachineModelViewHolder> {

    Context context;
    List<MachineModel> machineModelList;

    public MachineModelAdapter(Context context, List<MachineModel> machineModelList) {
        this.context = context;
        this.machineModelList = machineModelList;
    }

    public static final class MachineModelViewHolder extends RecyclerView.ViewHolder{

        ImageView modelImage;
        TextView price,name;

        public MachineModelViewHolder(@NonNull View itemView) {
            super(itemView);
            modelImage = itemView.findViewById(R.id.machine_model_image);
            price = itemView.findViewById(R.id.modelprice);
            name = itemView.findViewById(R.id.modelname);
        }
    }

    @NonNull
    @Override
    public MachineModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.machinemodel_row_item,parent,false);
        return new MachineModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MachineModelViewHolder holder, int position) {

        holder.modelImage.setImageResource(machineModelList.get(position).getImageUrl());
        holder.name.setText(machineModelList.get(position).getName());
        holder.price.setText(machineModelList.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return machineModelList.size();
    }
}
