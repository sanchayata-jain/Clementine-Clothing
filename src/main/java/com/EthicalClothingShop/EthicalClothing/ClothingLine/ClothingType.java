package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity
@Table
public class ClothingType {
    @Id
    @SequenceGenerator(
            name = "clothingType_sequence",
            sequenceName = "clothingType_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clothingType_sequence"
    )
    @NotNull
    private Integer id;
    @NotNull
    private String typeName;
// CONSTRUCTORS


    public ClothingType(Integer id, String typeName) {
        this.id = id;
        this.typeName = typeName;
    }

    public ClothingType() {
    }

    // GETTERS AND SETTERS
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

// TO STRING OVERRIDE
    @Override
    public String toString() {
        return "ClothingType{" +
                "id=" + id +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
