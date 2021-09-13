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
                        double price, int quantity) {

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


    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "ClothingItem{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", subtype='" + subtype + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", material='" + material + '\'' +
                ", price=" + price +
                '}';
    }
}

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ClothingItem that = (ClothingItem) o;
//        return id == that.id && size == that.size && inStock == that.inStock && Objects.equals(type, that.type) && Objects.equals(description, that.description) && Objects.equals(color, that.color) && Objects.equals(material, that.material) && Objects.equals(price, that.price);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(type, id, description, color, size, material, price, inStock);
//    }
//}


