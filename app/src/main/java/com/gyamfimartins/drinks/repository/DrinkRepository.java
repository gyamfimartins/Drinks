package com.gyamfimartins.drinks.repository;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.gyamfimartins.drinks.R;
import com.gyamfimartins.drinks.model.Drink;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DrinkRepository {

    public DrinkRepository() {

    }

    public LiveData<ArrayList<Drink>> getAllDrinks(final Context context) {
        final MutableLiveData<ArrayList<Drink>> data = new MutableLiveData<>();
        final ArrayList<Drink> drinkList = new ArrayList<>();

        String AlcoholicUrl = context.getString(R.string.weblink) + "Alcoholic";
        String NonalcoholicUrl = context.getString(R.string.weblink) + "Non_Alcoholic";

        StringRequest alcoholicRequest = new StringRequest(Request.Method.POST, AlcoholicUrl,
                response -> {

                    Log.d("Alcoholic drinks", ">>" + response);

                    try {
                        Gson gson = new Gson();
                        JSONObject obj = new JSONObject(response);
                        JSONArray dataArray = obj.getJSONArray("drinks");

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            Drink drink = gson.fromJson(String.valueOf(dataobj), Drink.class);
                            drinkList.add(drink);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show()) {

        };

        StringRequest nonalcoholRequest = new StringRequest(Request.Method.POST, NonalcoholicUrl,
                response -> {

                    Log.d("Non-acoholic drinks", ">>" + response);

                    try {
                        Gson gson = new Gson();
                        JSONObject obj = new JSONObject(response);
                        JSONArray dataArray = obj.getJSONArray("drinks");

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject dataobj = dataArray.getJSONObject(i);
                            Drink drink = gson.fromJson(String.valueOf(dataobj), Drink.class);
                            drinkList.add(drink);
                        }
                        data.setValue(drinkList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show()) {

        };


        RequestQueue requestQueue = Volley.newRequestQueue(context);
        alcoholicRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        nonalcoholRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(alcoholicRequest);
        requestQueue.add(nonalcoholRequest);
        return data;
    }

}
