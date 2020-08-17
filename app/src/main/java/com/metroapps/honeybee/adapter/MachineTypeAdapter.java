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
import com.metroapps.honeybee.model.MachineType;

import java.util.List;

public class MachineTypeAdapter extends RecyclerView.Adapter<MachineTypeAdapter.MachineTypeViewHolder> {

    Context context;
    List<MachineType> machineTypeList;

    public MachineTypeAdapter(Context context, List<MachineType> machineTypeList) {
        this.context = context;
        this.machineTypeList = machineTypeList;
    }

    @NonNull
    @Override
    public MachineTypeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.modeltype_row_item,parent,false);
        return new MachineTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MachineTypeViewHolder holder, int position) {
        holder.machineImage.setImageResource(machineTypeList.get(position).getImageUrl());
        holder.name.setText(machineTypeList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return machineTypeList.size();
    }

    public static final class MachineTypeViewHolder extends RecyclerView.ViewHolder{

        ImageView machineImage;
        TextView name;

        public MachineTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            machineImage = itemView.findViewById(R.id.machine_model_image);
            name = itemView.findViewById(R.id.machinetypename);
        }
    }

}
