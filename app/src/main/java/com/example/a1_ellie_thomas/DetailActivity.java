package com.example.a1_ellie_thomas;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // uses res/layout/activity_detail.xml
        TextView tv = findViewById(R.id.tvList);
        List<Payment> all = PaymentRepository.getAllPayments();
        if (all.isEmpty()) {
            tv.setText("No payments yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Payment p : all) sb.append(p.toString()).append("\n");
            tv.setText(sb.toString());
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
