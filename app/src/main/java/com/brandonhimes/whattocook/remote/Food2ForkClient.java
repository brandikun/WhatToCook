package com.brandonhimes.whattocook.remote;

import com.brandonhimes.whattocook.models.Recipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by brandon on 6/30/18.
 */

public interface Food2ForkClient {
    @GET("search")
    Call<Recipes> ingredientSearch(@Query("q")List<String> ingredients, @Query("key") String key);
}
