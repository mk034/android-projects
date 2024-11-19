package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView RecipeName, IngredientsTitle, IngredientsList, CookingInstructionsTitle, CookingInstructions;
    private ImageView RecipeImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        // Intent로부터 데이터 받음
        String recipeName = getIntent().getStringExtra("RECIPE_NAME");
        int recipeImage = getIntent().getIntExtra("RECIPE_IMAGE", R.drawable.ic_recipe); //기본 이미지
        String[] ingredients = getIntent().getStringArrayExtra("INGREDIENTS");
        String cookingInstructions = getIntent().getStringExtra("COOKING_INSTRUCTIONS");

        // 레시피 이름
        RecipeName = findViewById(R.id.recipe_name);
        RecipeName.setText("레시피 이름");
        RecipeName.setTextSize(20);
        RecipeName.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER); // 가운데 정렬

        // 레시피 이미지
        RecipeImage = findViewById(R.id.recipe_image);
        RecipeImage.setImageResource(recipeImage);

        // 재료 목록 타이틀
        IngredientsTitle = findViewById(R.id.ingredients_title);
        IngredientsTitle.setText("재료 목록");
        IngredientsTitle.setTextSize(18); //
        IngredientsTitle.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        // 재료 목록
        IngredientsList = findViewById(R.id.ingredients_list);
        setIngredientsList(ingredients);

        // 조리 방법 타이틀
        CookingInstructionsTitle = findViewById(R.id.cooking_instructions_title);
        CookingInstructionsTitle.setText("조리 방법");
        CookingInstructionsTitle.setTextSize(18);
        CookingInstructionsTitle.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);

        // 조리 방법
        CookingInstructions = findViewById(R.id.cooking_instructions);
        CookingInstructions.setText(cookingInstructions);
        CookingInstructions.setTextSize(16);
    }

    private void setIngredientsList(String[] ingredients) {
        // 재료 목록을 하나의 문자열로 변환하여 설정
        StringBuilder ingredientText = new StringBuilder();
        for (int i = 0; i < ingredients.length; i++) {
            ingredientText.append("• ").append(ingredients[i]).append("\n");
        }
        IngredientsList.setText(ingredientText.toString().trim());
        IngredientsList.setTextSize(16);
    }
}
