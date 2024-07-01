package com.api.thuctaptotnghiepbackend.Paypal;

public class SummaryData {
    private int totalOrders;
    private double totalRevenue;
    private int totalProducts;
    private int totalCancelledOrders;
    private double totalRevenuerefund;

    // Constructors, getters, and setters
    // Constructor
    public SummaryData(int totalOrders, double totalRevenue, int totalProducts,int totalCancelledOrders,double totalRevenuerefund) {
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
        this.totalProducts = totalProducts;
        this.totalCancelledOrders=totalCancelledOrders;
        this.totalRevenuerefund=totalRevenuerefund;
    }

    // Getters and Setters
    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalProducts() {
        return totalProducts;
    }

    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }


    public int gettotalCancelledOrders() {
        return totalCancelledOrders;
    }

    public void settotalCancelledOrders(int totalCancelledOrders) {
        this.totalCancelledOrders = totalCancelledOrders;
    }
    
    public double gettotalRevenuerefund() {
        return totalRevenuerefund;
    }

    public void settotalRevenuerefund(double totalRevenuerefund) {
        this.totalRevenuerefund = totalRevenuerefund;
    }
}
