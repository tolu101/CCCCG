package com.example.user.cg.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.cg.R;
import com.example.user.cg.inter.itemClickListener;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView food_name;
    public ImageView food_image;

    private AdapterView.OnItemClickListener itemClickListener;


    public void setItemClickListener(com.example.user.cg.inter.itemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_name = (TextView)itemView.findViewById(R.id.food_name);
        food_image = (ImageView) itemView.findViewById(R.id.food_image);


        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
       // itemClickListener.onItemClick(view,getAdapterPosition(),int);
//helppppp!!!!

    }
}
