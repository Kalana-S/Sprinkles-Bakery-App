package com.example.sprinklesbakery.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String category;
    private String cupcakeName;
    private double price;
    private int quantity;
    private int memberId;
    private String memberName;
    private String memberEmail;
    private double totalPrice;

    public Order(String category, String cupcakeName, double price, int quantity, int memberId, String memberName, String memberEmail, double totalPrice) {
        this.category = category;
        this.cupcakeName = cupcakeName;
        this.price = price;
        this.quantity = quantity;
        this.memberId = memberId;
        this.memberName = memberName;
        this.memberEmail = memberEmail;
        this.totalPrice = totalPrice;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCategory() { return category; }
    public String getCupcakeName() { return cupcakeName; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public int getMemberId() { return memberId; }
    public String getMemberName() { return memberName; }
    public String getMemberEmail() { return memberEmail; }
    public double getTotalPrice() { return totalPrice; }

}
