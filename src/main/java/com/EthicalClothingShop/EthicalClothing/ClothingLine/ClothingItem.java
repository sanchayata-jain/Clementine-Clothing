package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import java.math.BigDecimal;
import java.util.Objects;


public class ClothingItem {
    private String type;
    private Long id;
    private String description;
    private String color;
    private int size;
    private String material;
    private BigDecimal price;
    private boolean hasBeenBought;

    public ClothingItem(String type, long id, String description, String color, int size, String material, BigDecimal price, boolean hasBeenBought) {
        this.type = type;
        this.id = id;
        this.description = description;
        this.color = color;
        this.size = size;
        this.material = material;
        this.price = price;
        this.hasBeenBought = hasBeenBought;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isHasBeenBought() {
        return hasBeenBought;
    }

    public void setHasBeenBought(boolean hasBeenBought) {
        this.hasBeenBought = hasBeenBought;
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
                ", hasBeenBought=" + hasBeenBought +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClothingItem that = (ClothingItem) o;
        return id == that.id && size == that.size && hasBeenBought == that.hasBeenBought && Objects.equals(type, that.type) && Objects.equals(description, that.description) && Objects.equals(color, that.color) && Objects.equals(material, that.material) && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id, description, color, size, material, price, hasBeenBought);
    }
}


