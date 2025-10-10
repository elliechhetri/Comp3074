package com.example.a1_ellie_thomas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etHours, etRate;
    private TextView tvResult;
    private Button btnCalculate;
    private PaymentRepository repository = new PaymentRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etHours = findViewById(R.id.etHours);
        etRate = findViewById(R.id.etRate);
        tvResult = findViewById(R.id.tvPay);
        btnCalculate = findViewById(R.id.btnCalculate);

        etHours.setFilters(new android.text.InputFilter[]{ new TwoDecimalDigitsInputFilter() });
        etRate.setFilters(new android.text.InputFilter[]{ new TwoDecimalDigitsInputFilter() });

        btnCalculate.setOnClickListener(v -> calculatePay());

    }

    private void calculatePay() {
        String name = etName.getText().toString().trim();
        String hoursStr = etHours.getText().toString().trim();
        String rateStr = etRate.getText().toString().trim();

        if (name.isEmpty() || hoursStr.isEmpty() || rateStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double hours = Double.parseDouble(hoursStr);
        double rate = Double.parseDouble(rateStr);
        Payment payment = new Payment(name, hours, rate);
        repository.addPayment(payment);

        tvResult.setText(payment.toString());

        android.widget.Toast.makeText(this, "Payment saved", android.widget.Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_view_payments) {
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class TwoDecimalDigitsInputFilter implements android.text.InputFilter {
        @Override
        public CharSequence filter(CharSequence src, int start, int end,
                                   android.text.Spanned dst, int dstart, int dend) {
            String newVal = dst.subSequence(0, dstart) + src.toString() + dst.subSequence(dend, dst.length());
            if (newVal.matches("^\\d*(\\.\\d{0,2})?$")) return null;
            return "";
        }
    }
}
