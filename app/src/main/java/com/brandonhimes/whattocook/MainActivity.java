package com.brandonhimes.whattocook;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by brandon on 6/30/18.
 */

public class MainActivity extends AppCompatActivity {

    String key = "f1d8120be67457590be148d4e3e25e70";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://food2fork.com/api/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        Food2ForkClient client = retrofit.create(Food2ForkClient.class);
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("carne asada");
        client.ingredientSearch(ingredients, key).enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                for(int i = 0; i < response.body().getRecipes().size(); i++) {
                    Log.d("recipe", response.body().getRecipes().get(i).getTitle());
                }
            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {

            }
        });
    }
}
