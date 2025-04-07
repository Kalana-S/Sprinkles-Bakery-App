package com.example.sprinklesbakery.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cupcakes")
public class Cupcake {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String cupcakeName;
    private String category;
    private double price;
    private int quantity;
    private String imageUri;

    public Cupcake(String cupcakeName, String category, double price, int quantity, String imageUri) {
        this.cupcakeName = cupcakeName;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
        this.imageUri = imageUri;
    }

    @Override
    public String toString() {
        return cupcakeName;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCupcakeName() { return cupcakeName; }
    public void setCupcakeName(String cupcakeName) { this.cupcakeName = cupcakeName; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

}
