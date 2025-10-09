package com.example.a1_ellie_thomas;

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
        return hoursWorked * hourlyRate;
    }

    @Override
    public String toString() {
        return String.format("Hrs %.2f @ %.2f → %.2f",
                hoursWorked, hourlyRate, hoursWorked * hourlyRate);
    }
}
