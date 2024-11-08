package com.example.myapplication;

import java.util.Calendar;

public class Ingredient {
    private String name;
    private int quantity;
    private Calendar expirationDate;  // 날짜를 Calendar 객체로 저장
    private int imageResId;  // 이미지 리소스를 int 타입으로 저장

    // 생성자
    public Ingredient(String name, int quantity, Calendar expirationDate, int imageResId) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.imageResId = imageResId;  // 리소스 ID로 저장
    }

    // Getter 및 Setter
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public Calendar getExpirationDate() {  // 유통기한 (Calendar 객체) 반환
        return expirationDate;
    }

    public int getImageResId() {
        return imageResId;  // 리소스 ID를 반환
    }

    // 유통기한 확인 메서드
    public boolean isExpiringSoon() {
        Calendar today = Calendar.getInstance();
        long diffInMillis = expirationDate.getTimeInMillis() - today.getTimeInMillis();
        long diffInDays = diffInMillis / (1000 * 60 * 60 * 24);  // 일 수로 차이 계산
        return diffInDays >= 0 && diffInDays <= 3;  // 유통기한이 3일 이내인 경우 true
    }

    // 유통기한 비교 메서드
    public boolean isExpired() {
        Calendar today = Calendar.getInstance();
        return expirationDate.before(today);  // 유통기한이 오늘 날짜보다 이전이면 true
    }
}