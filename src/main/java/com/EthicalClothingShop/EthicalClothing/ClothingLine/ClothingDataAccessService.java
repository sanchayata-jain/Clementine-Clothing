package com.EthicalClothingShop.EthicalClothing.ClothingLine;


import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;

@Repository
public class ClothingDataAccessService implements ClothingDAO{
    ArrayList<ClothingItem> clothingItems;

    // constructor
    public ClothingDataAccessService() {

        this.clothingItems = new ArrayList<ClothingItem>();
        this.clothingItems.add(new ClothingItem(1l, "t-shirt", "cooltop",
                                              "red", 6, "silk", 55.90));
        this.clothingItems.add(new ClothingItem(2l, "skirt", "coolskirt",
                                               "pink", 8, "cotton", 42.99));
    }

    public ArrayList<ClothingItem> getClothingItems() {
        return clothingItems;
    }

    @Override
    public void addClothingItem(ClothingItem clothingItem) {
        this.clothingItems.add(clothingItem);
    }

    @Override
    public void removeClothingItem(ClothingItem clothingItem) {
        this.clothingItems.remove(clothingItem);
    }

}
