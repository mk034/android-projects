package com.example.myapplication;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Ingredient implements Serializable {
    private String name;
    private int quantity;
    private final String intakeDate;
    private String expirationDate;
    private int imageResId;  // 이미지 리소스를 int 타입으로 저장

    // 생성자
    public Ingredient(String name, int quantity, String expirationDate, String intakeDate, int imageResId) {
        this.name = name;
        this.quantity = quantity;
        this.intakeDate = intakeDate;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public int getImageResId() {
        return imageResId;  // 리소스 ID를 반환
    }

    public String getIntakeDate() { return intakeDate;
    }

    // D-Day 계산 메서드
    public String calculateDDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  // 날짜 포맷
        try {
            Date expirationDateObj = sdf.parse(expirationDate);
            Date currentDate = new Date();

            // 날짜 차이 계산
            long diffInMillis = expirationDateObj.getTime() - currentDate.getTime();
            long diffInDays = TimeUnit.MILLISECONDS.toDays(diffInMillis);

            if (diffInDays == 0) {
                return "D-Day";
            } else if (diffInDays > 0) {
                return "D-" + diffInDays;  // 남은 날짜
            } else {
                return "D+" + Math.abs(diffInDays);  // 지난 날짜
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return "D-Day 계산 오류";
        }
    }
}