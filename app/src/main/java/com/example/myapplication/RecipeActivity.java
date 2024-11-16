package com.example.myapplication;

import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends AppCompatActivity {
    private RecyclerView recyclerViewRecipes;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        // RecyclerView 초기화
        recyclerViewRecipes = findViewById(R.id.recycler_view_recipes);
        recyclerViewRecipes.setLayoutManager(new LinearLayoutManager(this));

        // 레시피 리스트 초기화
        recipeList = new ArrayList<>();
        recipeAdapter = new RecipeAdapter(recipeList, this);
        recyclerViewRecipes.setAdapter(recipeAdapter);

        // 홈 화면에서 전달된 재료 목록 받기
        ArrayList<String> ingredients = getIntent().getStringArrayListExtra("ingredients");

        // 레시피 데이터 불러오기
        loadRecipes(ingredients);
    }

    private void loadRecipes(List<String> ingredients) {
        ApiRequest apiRequest = new ApiRequest(this);
        apiRequest.fetchRecipesByIngredients(ingredients, new ApiRequest.RecipeFetchListener() {
            @Override
            public void onFetchSuccess(List<Recipe> recipes) {
                recipeList.clear();
                recipeList.addAll(recipes);
                recipeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFetchError(VolleyError error) {
                Toast.makeText(RecipeActivity.this, "레시피를 불러오는 데 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
