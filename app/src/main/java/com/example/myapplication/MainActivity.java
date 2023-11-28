package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumbers = findViewById(R.id.editTextNumbers);
    }

    public void calculateSum(View view) {
        String input = editTextNumbers.getText().toString().trim();

        if (input.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập dãy số.", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] numbers = input.split("\\s+"); // Phân tách dãy số bằng khoảng trắng
        float floatSum = 0;
        StringFraction fractionSum = new StringFraction(0, 1);
        ComplexNumber complexSum = new ComplexNumber(0, 0);

        for (String number : numbers) {
            if (isFloat(number)) {
                floatSum += Float.parseFloat(number);
            } else if (isFraction(number)) {
                StringFraction fraction = new StringFraction(number);
                fractionSum = fractionSum.add(fraction);
            } else if (isComplex(number)) {
                ComplexNumber complexNumber = new ComplexNumber(number);
                complexSum = complexSum.add(complexNumber);
            } else {
                // Xử lý trường hợp không hợp lệ (có thể thông báo lỗi)
                Toast.makeText(this, "Dữ liệu không hợp lệ: " + number, Toast.LENGTH_SHORT).show();
            }
        }

        fractionSum.simplify();

        String resultText = "Tổng số thực: " + floatSum +
                "\nTổng phân số: " + fractionSum.toString() +
                "\nTổng số phức: " + complexSum.toString();

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("result", resultText);
        startActivity(intent);
    }

    private boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFraction(String s) {
        return s.contains("/");
    }

    private boolean isComplex(String s) {
        return s.contains("+") && s.contains("i");
    }
}
