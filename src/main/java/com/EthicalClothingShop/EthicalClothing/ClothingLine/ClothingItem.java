package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

//@Entity
//@Table
public class ClothingItem {
//    @Id
//    @SequenceGenerator(name = "clothingItem_sequence", sequenceName = "clothingItem_sequence", allocationSize = 1)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clothingItem_sequence")
    private Long id;
    private String type;
    private String description;
    private String color;
    private int size;
    private String material;
    private double price;
    private boolean inStock;
    private int quantity;

    public ClothingItem(Long id, String type, String description,
                        String color, int size, String material,
                        double price) {

        this.type = type;
        this.id = id;
        this.description = description;
        this.color = color;
        this.size = size;
        this.material = material;
        this.price = price;
        this.inStock = true;
        this.quantity = 1;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getIsInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "ClothingItem{" +
                "type='" + type + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", color='" + color + '\'' +
                ", size=" + size +
                ", material='" + material + '\'' +
                ", price=" + price +
                ", hasBeenBought=" + inStock +
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


