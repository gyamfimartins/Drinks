package com.gyamfimartins.drinks.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gyamfimartins.drinks.R;
import com.gyamfimartins.drinks.model.Drink;

import java.util.ArrayList;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkAdapter.DrinkHolder>{
    private ArrayList<Drink> drinkList = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public DrinkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item,parent,false);
        return new DrinkHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkHolder holder, int position) {
        Drink drink = drinkList.get(position);
        holder.textView_name.setText(drink.getStrDrink());
        String imageurl = drink.getStrDrinkThumb();
        Glide.with(context)
                .load(imageurl)
                .thumbnail(0.5f)
                .into(holder.imageView_strDrinkThumb);

    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }

    public void setDrinks(ArrayList<Drink> drinkList, Context context){
          this.drinkList = drinkList;
          this.context =context;
          notifyDataSetChanged();
    }

    class DrinkHolder extends RecyclerView.ViewHolder{
        private ImageView imageView_strDrinkThumb;
        private TextView textView_name;

        public DrinkHolder(@NonNull View itemView) {
            super(itemView);
            imageView_strDrinkThumb = itemView.findViewById(R.id.imageView_strDrinkThumb);
            textView_name = itemView.findViewById(R.id.textView_name);
        }
    }
}
