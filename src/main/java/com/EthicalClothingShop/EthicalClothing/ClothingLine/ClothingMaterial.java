package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class ClothingMaterial {
    @Id
    @SequenceGenerator(
            name = "clothingMaterial_sequence",
            sequenceName = "clothingMaterial_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clothingMaterial_sequence"
    )
    @NotNull
    private Integer id;
    @NotNull
    private String materialName;

// CONSTRUCTORS

    public ClothingMaterial(Integer id, String materialName) {
        this.id = id;
        this.materialName = materialName;
    }

    public ClothingMaterial() {
    }
// GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

//TO STRING OVERRIDE

    @Override
    public String toString() {
        return "ClothingMaterial{" +
                "id=" + id +
                ", materialName='" + materialName + '\'' +
                '}';
    }
}
