package com.example.a1_ellie_thomas;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

// the details page that displays saved payments
public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // uses res/layout/activity_detail.xml
        setContentView(R.layout.activity_detail);
        TextView tv = findViewById(R.id.tvList);
        List<Payment> all = PaymentRepository.getAllPayments();

        // if no payments saved, show placeholder
        if (all.isEmpty()) {
            tv.setText("No payments yet.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Payment p : all) sb.append(p.toString()).append("\n");
            tv.setText(sb.toString());
        }
        // enables the <- button on the Action bar to navigate back to the main screen
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    // Once the back arrow on the action bar is clicked, it closes the detail activity and returns to the main activity
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
