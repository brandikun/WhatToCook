package com.brandonhimes.whattocook.models;

import com.brandonhimes.whattocook.models.Recipe;

import java.util.List;

/**
 * Created by brandon on 6/30/18.
 */

public class Recipes {
    List<Recipe> recipes;
    Integer count;

    public Recipes(List<Recipe> recipes, Integer count) {
        this.recipes = recipes;
        this.count = count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
