package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import com.sun.istack.NotNull;

import javax.persistence.*;
@Entity
@Table
public class ClothingSubtype {
    @Id
    @SequenceGenerator(
            name = "clothingSubtype_sequence",
            sequenceName = "clothingSubtype_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clothingSubtype_sequence"
    )
    @NotNull
    private Integer id;
    @NotNull
    private String subtypeName;
// CONSTRUCTORS

    public ClothingSubtype(Integer id, String subtypeName) {
        this.id = id;
        this.subtypeName = subtypeName;
    }

    public ClothingSubtype() {
    }
    // GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubtypeName() {
        return subtypeName;
    }

    public void setSubtypeName(String subtypeName) {
        this.subtypeName = subtypeName;
    }

    // TO STRING OVERRIDE

    @Override
    public String toString() {
        return "ClothingSubtype{" +
                "id=" + id +
                ", subtypeName='" + subtypeName + '\'' +
                '}';
    }
}
