package com.example.myapplication;

public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber(String complex) {
        if (complex.contains("+") && complex.contains("i")) {
            String[] parts = complex.split("\\+|i");
            this.real = Double.parseDouble(parts[0]);
            this.imaginary = Double.parseDouble(parts[1]);
        } else if (complex.contains("-") && complex.contains("i")) {
            if (complex.indexOf('-') != 0) {
                String[] parts = complex.split("-");
                if (parts.length == 3) {
                    this.real = Double.parseDouble(parts[1]);
                    this.imaginary = -Double.parseDouble(parts[2].replace("i", ""));
                } else {
                    this.real = Double.parseDouble(parts[0]);
                    this.imaginary = Double.parseDouble(parts[1].replace("i", ""));
                }
            } else {
                String[] parts = complex.split("-", 2);
                this.real = Double.parseDouble(parts[0]);
                this.imaginary = -Double.parseDouble(parts[1].replace("i", ""));
            }
        } else {
            throw new IllegalArgumentException("Dữ liệu không hợp lệ cho số phức: " + complex);
        }
    }

    public ComplexNumber add(ComplexNumber other) {
        double resultReal = this.real + other.real;
        double resultImaginary = this.imaginary + other.imaginary;
        return new ComplexNumber(resultReal, resultImaginary);
    }

    @Override
    public String toString() {
        if (imaginary == 0) {
            return String.valueOf(real);
        } else if (real == 0) {
            return (imaginary > 0 ? "" : "-") + Math.abs(imaginary) + "i";
        } else if (imaginary > 0) {
            return real + "+" + imaginary + "i";
        } else {
            return real + (imaginary < 0 ? "" : "+") + imaginary + "i";
        }
    }
}
