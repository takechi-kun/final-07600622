package com.example.speedrecords.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speedrecords.R;
import com.example.speedrecords.model.Speed;


public class SpeedAdapter extends RecyclerView.Adapter<SpeedAdapter.MyViewHolder> {


    private Context mContext;
    private  Speed[] mSpeed;
    public SpeedAdapter(Context context , Speed[] speeds) {
        this.mContext = context;
        this.mSpeed = speeds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_speed,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Speed speed =  mSpeed[position];
        holder.velocityTextView.setText(String.valueOf(speed.velocity) + " KM/H");
        holder.DistanceTimeTextView.setText(String.valueOf(speed.distance) +" METERS " +String.valueOf(speed.time) + " SECONDS");
        if(speed.velocity > 80){
            holder.cowImageView.setImageResource(R.drawable.red_cow);
        }
    }

    @Override
    public int getItemCount() {
        return mSpeed.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView velocityTextView;
        TextView DistanceTimeTextView;
        ImageView cowImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.velocityTextView = itemView.findViewById(R.id.velocity_text_view);
            this.DistanceTimeTextView = itemView.findViewById(R.id.distance_time_text_view);
            this.cowImageView = itemView.findViewById(R.id.cow);

        }
    }
}
