package com.EthicalClothingShop.EthicalClothing.ClothingLine;


public class ClothingItem {
    private int id;
    private String type;
    private String subtype;
    private String color;
    private String size;
    private String material;
    private double price;
    private int quantity;

    public ClothingItem(int id, String type, String subtype,
                        String size, String color, String material,
                        int quantity, double price) {

        this.type = type;
        this.id = id;
        this.subtype = subtype;
        this.color = color;
        this.size = size;
        this.material = material;
        this.price = price;
        this.quantity = quantity;
    }


    // getter methods
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getSubtype() {
        return subtype;
    }

    public String getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public String getMaterial() {
        return material;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // setter methods
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
