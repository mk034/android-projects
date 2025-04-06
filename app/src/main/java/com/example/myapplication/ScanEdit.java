package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;

public class ScanEdit extends AppCompatActivity {

    private LinearLayout containerInputs;
    private Button btnAddProduct, btnAdd, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan_edit);

        containerInputs = findViewById(R.id.container_inputs);
        btnAddProduct = findViewById(R.id.btn_add_product);
        btnAdd = findViewById(R.id.btn_add);
        btnCancel = findViewById(R.id.btn_cancel);

        btnAddProduct.setOnClickListener(v -> addNewInputRow());

        addNewInputRow();

        // 완료
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(ScanEdit.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });

        // 취소
        btnCancel.setOnClickListener(v -> {
            Intent intent = new Intent(ScanEdit.this, ScanReceipt.class);
            startActivity(intent);
            finish();
        });
    }

    private void addNewInputRow() {
        // 새 레이아웃 inflate
        LayoutInflater inflater = LayoutInflater.from(this);
        View inputRow = inflater.inflate(R.layout.item_input_row, containerInputs, false);

        Spinner spinner = inputRow.findViewById(R.id.spinner_unit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.unit_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        containerInputs.addView(inputRow);
    }
}
