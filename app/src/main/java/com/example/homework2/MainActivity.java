package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    float telescopePrice = 14_000;
    int account = 1000;
    float wage = 2500;
    float percentBank = 5;
    float[] monthlyPayments = new float[120];

    private float telescopePriceWithContribution() {
        return telescopePrice - account;
    }

    public int countMonth(float total, float wage, float percentBankYear) {
        float percentBankMonth = percentBankYear / 12;
        int count = 0;
        while (total > 0) {
            count++;
            total = (total + (total * percentBankMonth) / 100) - wage;
            if (total > wage) {
                monthlyPayments[count - 1] = wage;
            } else {
                monthlyPayments[count - 1] = total;
            }
        }
        return count;
    }

    private TextView countOut;
    private TextView manyMonthOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countOut = findViewById(R.id.countOut);
        manyMonthOut = findViewById(R.id.manyMonthOut);
        countOut.setText("Монеты будут накапливаться в течение " + countMonth(telescopePriceWithContribution(), wage, percentBank) + " месяцев");
        String monthlyPaymentlist = "";
        for (float list : monthlyPayments) {
            if (list > 0) {
                monthlyPaymentlist = monthlyPaymentlist + Float.toString(list) + " монет ";
            } else {
                break;
            }
        }
        manyMonthOut.setText("Первоначальный счёт " + account + " монет, ежемесячные накопления: " + monthlyPaymentlist);
    }

}
