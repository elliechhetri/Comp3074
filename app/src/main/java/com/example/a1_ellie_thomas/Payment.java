package com.example.a1_ellie_thomas;

import android.annotation.SuppressLint;

public class Payment {
    private String name;
    private double hoursWorked;
    private double hourlyRate;

    public Payment(String name, double hoursWorked, double hourlyRate) {
        this.name = name;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public String getName() {
        return name;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double calculatePay() {
        if(hoursWorked <= 0){
            return 0;
        }
        else if(hoursWorked > 40){
            return 40 * hourlyRate;
        }
        return hoursWorked * hourlyRate;
    }
    public double calculateOverTimePay() {
        if (hoursWorked >40){
            return (hoursWorked-40) * (hourlyRate*1.5);
        }
        return 0;
    }
    public double calculateGrossPay() {
        return calculatePay() + calculateOverTimePay();
    }

    public double calculateTaxed(){
        return calculateGrossPay() * 0.18;
    }

    public double calculateIncome(){
        return calculateGrossPay() - calculateTaxed();
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        String breakline= "---------------------------------------";
        return String.format("%s:\nHOURS:\t\t%.2f\nRATE:\t\t\t$%.2f/h\nINCOME:\t\t$%.2f\n%s",
                name, hoursWorked, hourlyRate, calculateIncome(), breakline);
    }
}
