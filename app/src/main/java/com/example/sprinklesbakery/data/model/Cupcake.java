package com.example.sprinklesbakery.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cupcakes")
public class Cupcake implements Parcelable {
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

    protected Cupcake(Parcel in) {
        id = in.readInt();
        cupcakeName = in.readString();
        category = in.readString();
        price = in.readDouble();
        quantity = in.readInt();
        imageUri = in.readString();
    }

    public static final Creator<Cupcake> CREATOR = new Creator<Cupcake>() {
        @Override
        public Cupcake createFromParcel(Parcel in) {
            return new Cupcake(in);
        }

        @Override
        public Cupcake[] newArray(int size) {
            return new Cupcake[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(cupcakeName);
        parcel.writeString(category);
        parcel.writeDouble(price);
        parcel.writeInt(quantity);
        parcel.writeString(imageUri);
    }

    @Override
    public int describeContents() {
        return 0;
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
    public String getImageUri() { return imageUri; }
    public void setImageUri(String imageUri) { this.imageUri = imageUri; }

}
