package com.brandonhimes.whattocook.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.brandonhimes.whattocook.R;

public class RecipeView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_view);

        WebView recipeWebView = findViewById(R.id.recipe_web_view);
        recipeWebView.getSettings().setJavaScriptEnabled(true);
        recipeWebView.setWebViewClient(new WebViewClient());
        recipeWebView.setWebChromeClient(new WebChromeClient());
        recipeWebView.loadUrl(getIntent().getStringExtra("url"));
    }
}
