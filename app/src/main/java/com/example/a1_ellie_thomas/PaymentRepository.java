package com.example.a1_ellie_thomas;

import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private static final List<Payment> payments = new ArrayList<>();

    public static void addPayment(Payment payment) {
        payments.add(payment);
    }

    public static List<Payment> getAllPayments() {
        return new ArrayList<>(payments);
    }

    public static void clearPayments() {
        payments.clear();
    }
}
