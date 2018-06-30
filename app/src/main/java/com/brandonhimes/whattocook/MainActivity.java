package com.brandonhimes.whattocook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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

    private RecyclerView mRecipeRecycler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecipeRecycler = findViewById(R.id.recipe_recycler);
        mRecipeRecycler.setLayoutManager(new LinearLayoutManager(this));
        SearchView searchView = findViewById(R.id.recipe_searchview);

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("http://food2fork.com/api/")
                .addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();

        final Food2ForkClient client = retrofit.create(Food2ForkClient.class);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                ArrayList<String> ingredients = new ArrayList<>();
                ingredients.add(query);
                searchView.clearFocus();
                client.ingredientSearch(ingredients, getString(R.string.api_key)).enqueue(new Callback<Recipes>() {
                    @Override
                    public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                        if(response.body() != null && response.body().getRecipes() != null) {
                            if(response.body().getRecipes().isEmpty()) {
                                mRecipeRecycler.setAdapter(new RecipeResultsAdapter(MainActivity.this, new ArrayList<Recipe>()));
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                                builder.setTitle(R.string.no_results_title)
                                        .setMessage(R.string.no_results_message)
                                        .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss())
                                        .show();
                            } else {
                                mRecipeRecycler.setAdapter(new RecipeResultsAdapter(MainActivity.this, response.body().getRecipes()));
                            }
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                            builder.setTitle(R.string.general_error_title)
                                    .setMessage(R.string.general_error_message)
                                    .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss())
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Recipes> call, Throwable t) {
                        mRecipeRecycler.setAdapter(new RecipeResultsAdapter(MainActivity.this, new ArrayList<Recipe>()));
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                        builder.setTitle(R.string.network_error_title)
                                .setMessage(R.string.network_error_message)
                                .setPositiveButton(R.string.ok, (dialog, which) -> dialog.dismiss())
                                .show();
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
