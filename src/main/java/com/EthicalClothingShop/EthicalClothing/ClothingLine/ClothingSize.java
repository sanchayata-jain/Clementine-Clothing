package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table
public class ClothingSize {
    @Id
    @SequenceGenerator(
            name = "clothingSizes_sequence",
            sequenceName = "clothingSizes_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "clothingSizes_sequence"
    )
    @NotNull
    private Integer id;
    @NotNull
    private String sizeName;

// CONSTRUCTORS

    public ClothingSize(Integer id, String sizeName) {
        this.id = id;
        this.sizeName = sizeName;
    }

    public ClothingSize() {
    }

// GETTERS AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }
// TO STRING OVERRIDE

    @Override
    public String toString() {
        return "ClothingSizes{" +
                "id=" + id +
                ", sizeName='" + sizeName + '\'' +
                '}';
    }
}
