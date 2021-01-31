package com.gyamfimartins.drinks.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.gyamfimartins.drinks.model.Drink;
import com.gyamfimartins.drinks.repository.DrinkRepository;

import java.util.ArrayList;

public class DrinkViewModel extends AndroidViewModel {
    private DrinkRepository drinkRepository;

    public DrinkViewModel(@NonNull Application application) {
        super(application);
        drinkRepository = new DrinkRepository();
    }

    public LiveData<ArrayList<Drink>> getAllDrinks() {
        return drinkRepository.getAllDrinks(getApplication());
    }
}
