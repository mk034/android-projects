package com.example.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Ingredient {
    private String name;
    private int quantity;
    private String expirationDate;  // String 타입으로 저장
    private int imageResId;  // 이미지 리소스를 int 타입으로 저장

    // 생성자
    public Ingredient(String name, int quantity, String expirationDate, int imageResId) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;  // String 타입으로 저장
        this.imageResId = imageResId;  // 리소스 ID로 저장
    }

    // Getter 및 Setter
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getExpirationDate() {  // 유통기한 반환
        return expirationDate;
    }

    public int getImageResId() {
        return imageResId;  // 리소스 ID를 반환
    }

    // 유통기한이 곧 만료될 경우 true를 반환하는 메서드
    public boolean isExpiringSoon() {
        // 오늘 날짜와 유통기한을 비교
        String todayDate = getCurrentDate();
        return compareDates(todayDate, expirationDate) <= 3 && compareDates(todayDate, expirationDate) >= 0;  // 유통기한이 3일 이내인 경우 true
    }

    // 유통기한이 만료되었는지 확인하는 메서드
    public boolean isExpired() {
        String todayDate = getCurrentDate();
        return compareDates(todayDate, expirationDate) > 0;  // 유통기한이 오늘 날짜보다 이전이면 true
    }

    // 현재 날짜를 "YYYY-MM-DD" 형식으로 반환
    private String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());  // 현재 날짜를 String으로 반환
    }

    // 날짜 비교 메서드: String 형식 "yyyy-MM-dd"로 날짜 비교
    private int compareDates(String date1, String date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d1 = sdf.parse(date1);
            Date d2 = sdf.parse(date2);
            return d1.compareTo(d2);  // d1이 d2보다 이전이면 -1, 같으면 0, 이후면 1 반환
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;  // 예외 처리
        }
    }
}