package com.gyamfimartins.drinks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.gyamfimartins.drinks.adapter.DrinkAdapter;
import com.gyamfimartins.drinks.model.Drink;
import com.gyamfimartins.drinks.viewmodel.DrinkViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DrinkViewModel drinkViewModel;
    private RecyclerView rvdrinks;
  private DrinkAdapter drinkAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         rvdrinks = findViewById(R.id.rvdrinks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvdrinks.setHasFixedSize(true);
        rvdrinks.setLayoutManager(linearLayoutManager);

        drinkViewModel = new ViewModelProvider(this).get(DrinkViewModel.class);
        drinkAdapter = new DrinkAdapter();

        getAllDrinks();
    }

    public void getAllDrinks(){
           drinkViewModel.getAllDrinks().observe(this, drinks -> {
             drinkAdapter.setDrinks(drinks, MainActivity.this);
               rvdrinks.setAdapter(drinkAdapter);

           });

    }
}