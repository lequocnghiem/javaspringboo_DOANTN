package com.api.thuctaptotnghiepbackend.Paypal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class SalesData {
    
    private int month;
    private double sales;


    public SalesData(int month, double sales) {
        this.month = month;
        this.sales = sales;
    }

    public int getMonth() {
        return month;
    }

    public double getSales() {
        return sales;
    }
}
