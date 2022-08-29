package com.example.kfp_midterm;

import java.io.Serializable;

public class Expense implements Serializable {
    String name;
    double amount;
    String date;
    String category;

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public Expense(String name, double amount, String date, String category) {
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }
}
