package com.example.myapplication;

public class StringFraction {
    private int numerator;
    private int denominator;

    public StringFraction(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public StringFraction(String fraction) {
        String[] parts = fraction.split("/");
        this.numerator = Integer.parseInt(parts[0]);
        this.denominator = Integer.parseInt(parts[1]);
    }

    public StringFraction add(StringFraction other) {
        int resultNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
        int resultDenominator = this.denominator * other.denominator;
        return new StringFraction(resultNumerator, resultDenominator);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    void simplify() {
        int gcd = findGCD(this.numerator, this.denominator);
        this.numerator /= gcd;
        this.denominator /= gcd;
    }

    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
