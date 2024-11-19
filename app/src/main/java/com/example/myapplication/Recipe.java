package com.example.myapplication;

import java.util.List;

public class Recipe {
    private String name;
    private int imageResId;
    private List<String> ingredients;
    private String cookingInstructions;

    // 생성자
    public Recipe(String name, int imageResId, List<String> ingredients, String cookingInstructions) {
        this.name = name;
        this.imageResId = imageResId;
        this.ingredients = ingredients;
        this.cookingInstructions = cookingInstructions;

    }

    // 레시피 이름을 반환
    public String getName() {
        return name;
    }

    // 레시피 이미지 리소스 ID 반환
    public int getImageResId() {
        return imageResId;
    }

    // 재료들을 문자열로 변환 (쉼표로 구분)
    public String getIngredientsAsString() {
        return String.join(", ", ingredients);
    }

    // 재료 목록 반환
    public List<String> getIngredients() {
        return ingredients;
    }

    // 조리 방법 반환
    public String getCookingInstructions() {
        return cookingInstructions;
    }
}
