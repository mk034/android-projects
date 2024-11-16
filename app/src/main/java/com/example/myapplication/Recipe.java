package com.example.myapplication;

import java.util.List;

public class Recipe {
    private String name;
    private String ingredients;

    // 생성자
    public Recipe(String name, String ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    // 레시피 이름을 반환
    public String getName() {
        return name;
    }

    // 재료들을 문자열로 변환 (쉼표로 구분)
    public String getIngredientsAsString() {
        return String.join(", ", ingredients);
    }
}
