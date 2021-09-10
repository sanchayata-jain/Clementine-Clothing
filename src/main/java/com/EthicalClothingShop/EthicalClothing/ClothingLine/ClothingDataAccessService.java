package com.EthicalClothingShop.EthicalClothing.ClothingLine;


import java.util.ArrayList;

public class ClothingDataAccessService implements ClothingDAO{
    ArrayList<ClothingItem> clothingItems;

    // constructor
    public ClothingDataAccessService() {
        this.clothingItems = new ArrayList<ClothingItem>();
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
