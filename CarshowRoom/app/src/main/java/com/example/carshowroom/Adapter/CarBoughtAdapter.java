package com.example.carshowroom.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.carshowroom.Models.CarBought;
import com.example.carshowroom.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CarBoughtAdapter extends RecyclerView.Adapter<CarBoughtAdapter.ViewHolder> {
    private List<CarBought> carBought;
    private Context context;

    public CarBoughtAdapter(List<CarBought> carBought, Context context) {
        this.carBought = carBought;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.booked_item,parent,false);
        return new CarBoughtAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.contact.setText(carBought.get(position).getContact());
        holder.buyername.setText(carBought.get(position).getName());
        holder.price.setText("Price: "+carBought.get(position).getPrice()+" Rs");
        holder.Carname.setText(carBought.get(position).getDescription());
        //        Glide.with(mContext).load(mPost.get(position).getPostimage()).into(holder.carImage);
        Glide.with(context).load(carBought.get(position).getImageUrl()).into(holder.img);

    }
    @Override
    public int getItemCount() {
        return carBought.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView Carname,price,buyername,contact;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Carname = itemView.findViewById(R.id.cb_carModel);
            price = itemView.findViewById(R.id.cb_carPrice);
            buyername = itemView.findViewById(R.id.cb_buyerName);
            contact = itemView.findViewById(R.id.cb_buyerPhone);
            img = itemView.findViewById(R.id.cb_carImage);
        }
    }
}

