package com.example.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editHours;
    private EditText editRate;
    private EditText editTax;

    private TextView textPay;
    private TextView textOvertime;
    private TextView textTotal;
    private TextView textTax;

    private Button buttonCalculate;
    private Button buttonAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editHours = findViewById(R.id.editHours);
        editRate = findViewById(R.id.editRate);
        editTax = findViewById(R.id.editTax);

        textPay = findViewById(R.id.textPay);
        textOvertime = findViewById(R.id.textOvertime);
        textTotal = findViewById(R.id.textTotal);
        textTax = findViewById(R.id.textTax);

        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonAbout = findViewById(R.id.buttonAbout);

        buttonCalculate.setOnClickListener(v -> calculatePay());

        buttonAbout.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }

    private void calculatePay() {

        String hoursText = editHours.getText().toString();
        String rateText = editRate.getText().toString();
        String taxText = editTax.getText().toString();

        if (hoursText.isEmpty() || rateText.isEmpty() || taxText.isEmpty()) {
            Toast.makeText(
                    this,
                    "Please enter all values",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        double hours = Double.parseDouble(hoursText);
        double hourlyRate = Double.parseDouble(rateText);
        double taxRate = Double.parseDouble(taxText);

        double pay;
        double overtimePay;

        if (hours <= 40) {
            pay = hours * hourlyRate;
            overtimePay = 0;
        } else {
            pay = 40 * hourlyRate;
            overtimePay = (hours - 40) * hourlyRate * 1.5;
        }

        double totalPay = pay + overtimePay;
        double tax = pay * taxRate;

        textPay.setText(String.format("Pay: $%.2f", pay));
        textOvertime.setText(String.format("Overtime Pay: $%.2f", overtimePay));
        textTotal.setText(String.format("Total Pay: $%.2f", totalPay));
        textTax.setText(String.format("Tax: $%.2f", tax));
    }
}