package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClothingService {
    private ClothingDataAccessServicePsql database_access;

    public ClothingService(ClothingDataAccessServicePsql database_access) {
        this.database_access = database_access;
    }


    public List<ClothingItem> getClothingItems() {

        List<ClothingItem> clothingItems = database_access.getClothingItems();
        ArrayList<ClothingItem> clothingItemByType = new ArrayList<ClothingItem>();

        boolean requestedTypeFound = false;
        for (ClothingItem clothingItem : clothingItems) {
            clothingItems.add(clothingItem);
        }

        if (!requestedTypeFound) {
            throw new IllegalStateException("We do not have this type of clothing");
        }
        return clothingItems;
    }


    public ArrayList<ClothingItem> getClothingItemsOfSameType(String clothingType) {
        boolean requestedTypeFound = false;
        List<ClothingItem> clothingItems = database_access.getClothingItems();
        ArrayList<ClothingItem> clothingItemByType = new ArrayList<ClothingItem>();

        for (ClothingItem clothing : clothingItems) {
            if (clothing.getType().equals(clothingType)) {
                clothingItemByType.add(clothing);
                requestedTypeFound = true;
            }
        }

        if (!requestedTypeFound) {
            throw new IllegalStateException("We do not have this type of clothing");
        }
        return clothingItemByType;
    }


    public ArrayList<ClothingItem> getClothingItemsOfSameSubtype(String clothingSubtype) {
        boolean requestedTypeFound = false;
        List<ClothingItem> clothingItems = database_access.getClothingItems();
        ArrayList<ClothingItem> clothingItemsBySubtype = new ArrayList<ClothingItem>();

        for (ClothingItem clothing : clothingItems) {
            if (clothing.getType().equals(clothingSubtype)) {
                clothingItemsBySubtype.add(clothing);
                requestedTypeFound = true;
            }
        }

        if (!requestedTypeFound) {
            throw new IllegalStateException("We do not have this type of clothing");
        }
        return clothingItemsBySubtype;
    }


    public void addClothingItem(ClothingItem clothingItem) {

        database_access.addClothingItem(clothingItem);
    }


    public void removeClothingItem(int clothesBarcode){
        List<ClothingItem> clothingItems = database_access.getClothingItems();
        boolean requestTypeFound = false;
        for(ClothingItem clothingItem:clothingItems){
            if(clothingItem.getId() == clothesBarcode){
                requestTypeFound = true;
                database_access.removeClothingItem(clothingItem);
            }
        }if (!requestTypeFound) {
            throw new IllegalStateException("sorry this Item does not exist!");
        }
    }


    public void updateClothingItem(double price, int quantity, int clothingItemId) {

        database_access.updateClothingItem(price, quantity, clothingItemId);
//        List<ClothingItem> clothingItems = database_access.getClothingItems();
//        boolean requestTypeFound = false;
//        for (ClothingItem clothingItem :clothingItems) {
//           if (clothingItem.getId() == clothingItemId) {
//               requestTypeFound = true;
//               clothingItem.setPrice(clothingItem.getPrice());
//               clothingItem.setQuantity(clothingItem.getQuantity());
//           }
//        }
//        if (!requestTypeFound) {
//            throw new IllegalStateException("we cannot update this item");
//        }
    }


    public ClothingItem getClothingItemUsingId(int clothingId) {

        boolean requestedTypeFound = false;
        List<ClothingItem> clothingItems = database_access.getClothingItems();
        ClothingItem clothingItem = null;
        for (ClothingItem clothing : clothingItems) {
            if (clothing.getId() == clothingId) {
                clothingItem = clothing;
                requestedTypeFound = true;
            }
        }
        if (!requestedTypeFound) {
            throw new IllegalStateException("We do not have this type of clothing");
        }
        return clothingItem;
    }




}