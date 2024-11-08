package com.example.myapplication;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class AddDetailActivity extends AppCompatActivity {

    private Button btnBack, btnDecreaseQuantity, btnIncreaseQuantity, btnIngredientAdd;
    private TextView tvItemName, quantityText;
    private ImageView ivItemImage;
    private EditText etExpirationDate;
    private int quantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_detail);

        // 뷰 초기화
        btnBack = findViewById(R.id.btn_back);
        btnDecreaseQuantity = findViewById(R.id.btn_decreaseQuantity);
        btnIncreaseQuantity = findViewById(R.id.btn_increaseQuantity);
        btnIngredientAdd = findViewById(R.id.btn_ingredient_add);
        tvItemName = findViewById(R.id.tv_itemName);
        quantityText = findViewById(R.id.quantityText);
        ivItemImage = findViewById(R.id.iv_itemImage);
        etExpirationDate = findViewById(R.id.et_expirationDate);

        // 초기 수량 설정
        quantityText.setText(String.valueOf(quantity));



        // Intent에서 데이터 받기
        Intent intent = getIntent();
        if (intent != null) {
            String itemName = intent.getStringExtra("itemName"); // 재료 이름 받기
            int itemImage = intent.getIntExtra("itemImage", 0); // 재료 이미지 리소스 ID 받기

            // 받은 데이터를 뷰에 설정
            tvItemName.setText(itemName); // 재료 이름 설정
            ivItemImage.setImageResource(itemImage); // 재료 이미지 설정
        }


        // 뒤로가기 버튼
        btnBack.setOnClickListener(v -> finish());

        // 수량 감소 버튼
        btnDecreaseQuantity.setOnClickListener(v -> {
            if (quantity > 0) { // 수량이 0보다 큰 경우에만 감소
                quantity--;
                quantityText.setText(String.valueOf(quantity));
            } else {
                Toast.makeText(this, "수량은 0보다 작을 수 없습니다", Toast.LENGTH_SHORT).show();
            }
        });

        // 수량 증가 버튼
        btnIncreaseQuantity.setOnClickListener(v -> {
            quantity++;
            quantityText.setText(String.valueOf(quantity));
        });

        // 유통기한 입력을 위한 날짜 선택기
        etExpirationDate.setOnClickListener(v -> showDatePickerDialog());

        // 재료 추가 버튼
        btnIngredientAdd.setOnClickListener(v -> {
            String ingredientName = tvItemName.getText().toString();
            String expirationDate = etExpirationDate.getText().toString();
            int image  = getIntent().getIntExtra("itemImage", R.drawable.ic_trashcan); // 기본값은 trashcan 이미지
            ivItemImage.setImageResource(image);

            // ApiRequest 클래스 사용하여 서버에 재료 추가
            ApiRequest apiRequest = new ApiRequest(this);
            apiRequest.addIngredient(ingredientName, quantity, expirationDate, image ); // 이미지 파일명 추가

            // 알림 설정
            scheduleNotification(ingredientName, expirationDate);

            Intent returnIntent = new Intent(AddDetailActivity.this, AddIngredientActivity.class);
            startActivity(returnIntent);
            finish();
        });
    }


    // 유통기한을 위한 날짜 선택기 다이얼로그 표시
    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String date = selectedYear + "-" + (selectedMonth + 1) + "-" + selectedDay;
                    etExpirationDate.setText(date);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void scheduleNotification(String ingredientName, String expirationDate) {
        // 날짜 형식 파싱 및 알림 예약
        Calendar calendar = Calendar.getInstance();
        // 예: expirationDate는 "2023-12-31" 형식으로 제공된다고 가정
        String[] dateParts = expirationDate.split("-");
        if (dateParts.length == 3) {
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]) - 1; // Calendar는 0-based month
            int day = Integer.parseInt(dateParts[2]);
            calendar.set(year, month, day, 9, 0); // 알림 시간: 오전 9시

            // 현재 시간보다 과거 날짜인지 확인
            if (calendar.getTimeInMillis() > System.currentTimeMillis()) {
                Intent notificationIntent = new Intent(this, NotificationReceiver.class);
                notificationIntent.putExtra("ingredientName", ingredientName);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

                Toast.makeText(this, "알림이 설정되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "유통기한이 이미 지난 날짜입니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

}