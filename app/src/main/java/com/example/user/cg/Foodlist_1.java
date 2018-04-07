package com.example.user.cg;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.user.cg.Model.Food1;
import com.example.user.cg.ViewHolder.FoodViewHolder;
import com.example.user.cg.inter.itemClickListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Foodlist_1 extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    FirebaseDatabase database;
    DatabaseReference foodList;
    String categoryId = "";

    FirebaseRecyclerAdapter<Food1,FoodViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist_1);

        //Firebase
        database = FirebaseDatabase.getInstance();
        foodList = database.getReference("Foods");

        recyclerView = (RecyclerView)findViewById(R.id.recycler_food1);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        //get intent here
        if(getIntent() !=null)
            categoryId = getIntent().getStringExtra("CategoryId");
        if (!categoryId.isEmpty() && categoryId !=null)

                    loadlistFood(categoryId);

    }

    private void loadlistFood(String categoryId) {
        adapter = new FirebaseRecyclerAdapter<Food1, FoodViewHolder>(Food1.class, R.layout.food1_item, FoodViewHolder.class,
                foodList.orderByChild("MenuId").equalTo(categoryId) // like:Select from foods where MenuId =
                ) {
            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food1 model, int position) {
                viewHolder.food_name.setText(model.getName());
                Picasso.with(getBaseContext()).load(model.getImage())
                        .into(viewHolder.food_image);


                final Food1 local = model;
                viewHolder.setItemClickListener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        //start new activity
                        Intent foodDetail = new Intent(Foodlist_1.this, FoodDetail.class);
                        foodDetail.putExtra("FoodId",adapter.getRef(position).getKey()); //send food ID to new activity
                        startActivity(foodDetail);

                    }
                });

            }
        };

        //set adapter
        Log.d("TAG", ""+adapter.getItemCount());
        recyclerView.setAdapter(adapter);


    }
}
