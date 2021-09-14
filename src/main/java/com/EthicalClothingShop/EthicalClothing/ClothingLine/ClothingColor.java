package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class ClothingColor {
    @Id
    @SequenceGenerator(
            name = "clothingColor_sequence",
            sequenceName = "clothingColor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clothingColor_sequence"
    )
    @NotNull
    private Integer id;
    @NotNull
    private String colorName;

//CONSTRUCTORS

    public ClothingColor(Integer id, String colorName) {
        this.id = id;
        this.colorName = colorName;
    }

    public ClothingColor() {
    }
// GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
// TO STRING OVERRIDE

    @Override
    public String toString() {
        return "ClothingColor{" +
                "id=" + id +
                ", colorName='" + colorName + '\'' +
                '}';
    }
}
