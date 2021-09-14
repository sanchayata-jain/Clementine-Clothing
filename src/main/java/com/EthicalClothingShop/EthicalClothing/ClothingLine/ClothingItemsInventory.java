//CLOTHING ITEMS INVENTORY CLASS, MAIN TABLE

package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class ClothingItemsInventory {
    @Id
    @SequenceGenerator(
            name = "clothingItemsInventory_sequence",
            sequenceName = "clothingItemsInventory_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clothingItemsInventory_sequence"
    )
    @NotNull
    private Integer id;

// ONE TO ONE ERs for attributes of the ClothingItemsInventory class:
    // from ClothingType TABLE
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="type_id",referencedColumnName = "id")
    private ClothingType typeId;

    // from ClothingSubtype TABLE
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="subtype_id",referencedColumnName = "id")
    private ClothingSubtype subtypeId;

    // from ClothingSize TABLE
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="size_id",referencedColumnName = "id")
    private ClothingSize clothingSize;

    // from ClothingColor TABLE
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="color_id",referencedColumnName = "id")
    private ClothingColor clothingColor;

    // from ClothingMaterial TABLE
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="material_id",referencedColumnName = "id")
    private ClothingMaterial clothingMaterial;

// NOT FOREIGN KEYS
    @NotNull
    private Double price;
    @NotNull
    private Integer quantity;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CONSTRUCTOR
    public ClothingItemsInventory() {

    }

//GETTERS AND SETTERS


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ClothingType getTypeId() {
        return typeId;
    }

    public void setTypeId(ClothingType typeId) {
        this.typeId = typeId;
    }

    public ClothingSubtype getSubtypeId() {
        return subtypeId;
    }

    public void setSubtypeId(ClothingSubtype subtypeId) {
        this.subtypeId = subtypeId;
    }

    public ClothingSize getClothingSize() {
        return clothingSize;
    }

    public void setClothingSize(ClothingSize clothingSize) {
        this.clothingSize = clothingSize;
    }

    public ClothingColor getClothingColor() {
        return clothingColor;
    }

    public void setClothingColor(ClothingColor clothingColor) {
        this.clothingColor = clothingColor;
    }

    public ClothingMaterial getClothingMaterial() {
        return clothingMaterial;
    }

    public void setClothingMaterial(ClothingMaterial clothingMaterial) {
        this.clothingMaterial = clothingMaterial;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
//TO STRING OVERRIDE

    @Override
    public String toString() {
        return "ClothingItemsInventory{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", subtypeId=" + subtypeId +
                ", clothingSize=" + clothingSize +
                ", clothingColor=" + clothingColor +
                ", clothingMaterial=" + clothingMaterial +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}



