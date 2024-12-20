package org.example.java_lab_4;

public class Tariffs {
    private String tariff;
    private Double price;
    private Double discount;
    private final int id;
    private final double finalPrice;

    public Tariffs(int id, String tariff, Double price, Double discount, Double finalPrice) {
        this.id = id;
        this.tariff = tariff;
        this.price = price;
        this.discount = discount;
        this.finalPrice = finalPrice;
    }

    public int getId(){
        return id;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double priceDiscount) {
        this.discount = priceDiscount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}
