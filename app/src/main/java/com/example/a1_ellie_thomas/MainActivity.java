package com.example.a1_ellie_thomas;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
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
    private TextView tvPay,tvOvertime, tvTotal, tvTax, tvIncome;
    private Button btnCalculate;
    private PaymentRepository repository = new PaymentRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // use our private variables to connect to activity_main content
        etName = findViewById(R.id.etName);
        etHours = findViewById(R.id.etHours);
        etRate = findViewById(R.id.etRate);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvPay = findViewById(R.id.tvPay);
        tvOvertime = findViewById(R.id.tvOvertime);
        tvTotal = findViewById(R.id.tvTotal);
        tvTax = findViewById(R.id.tvTax);
        tvIncome = findViewById(R.id.tvTotalIncome);

        // filters the input to match the double format
        etHours.setFilters(new android.text.InputFilter[]{ new TwoDecimalDigitsInputFilter() });
        etRate.setFilters(new android.text.InputFilter[]{ new TwoDecimalDigitsInputFilter() });

        // listener for our button
        btnCalculate.setOnClickListener(v -> calculatePay());

    }

    @SuppressLint("DefaultLocale")
    private void calculatePay() {
        // save user inputs into variables
        String name = etName.getText().toString().trim();
        String hoursStr = etHours.getText().toString().trim();
        String rateStr = etRate.getText().toString().trim();

        // Validation for inputs. Can NOT be left blank
        if (name.isEmpty() || hoursStr.isEmpty() || rateStr.isEmpty()) {
            // displays a message to user to fill in all fields
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // convert necessary user inputs to double
        double hours = Double.parseDouble(hoursStr);
        double rate = Double.parseDouble(rateStr);

        // create new object Payment with user inputs
        Payment payment = new Payment(name, hours, rate);

        // calculate outputs based on the object param, put into variables to call in output below
        double basePay = payment.calculatePay();
        double overTimePay = payment.calculateOverTimePay();
        double grossPay = payment.calculateGrossPay();
        double taxed = payment.calculateTaxed();
        double income = payment.calculateIncome();

        // adds and saved the inputs into our list
        repository.addPayment(payment);

        // update the activity_main TextViews to display output in relation to user input
        tvPay.setText(String.format("Base Pay:\t$%.2f", basePay));
        tvOvertime.setText(String.format("Overtime Pay:\t$%.2f", overTimePay));
        tvTotal.setText(String.format("Total Earned:\t$%.2f", grossPay));
        tvTax.setText(String.format("Taxed:\t$%.2f", taxed));
        tvIncome.setText(String.format("Total Income:\t$%.2f", income));

        // displays a confirmation message on payment successfully saved in list
        android.widget.Toast.makeText(this, "Payment saved", android.widget.Toast.LENGTH_SHORT).show();
    }

    // creates and displays the action bar, loads the options from our res/menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // navigates to the option selected in the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_view_payments) {
            //Navigates to DetailActivity
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //Checks if the input matches at double format
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
