package com.example.a1_ellie_thomas;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PaymentTest {
    @Test
    public void calculatePay_isCorrect() {
        Payment p = new Payment("Alice", 10.0, 20.0);
        assertEquals(200.0, p.calculatePay(), 1e-6);
    }
}