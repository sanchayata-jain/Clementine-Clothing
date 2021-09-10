package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClothingService {
    private ClothingDAO clothingDAO;
    private ClothingDataAccessService clothingDataAccessService;

    public ClothingService(ClothingDataAccessService clothingDataAccessService,ClothingDAO clothingDAO) {
        this.clothingDataAccessService = clothingDataAccessService;
        this.clothingDAO = clothingDAO;
    }



    public List<ClothingItem> getClothingItems() {
        return clothingDataAccessService.getClothingItems();
    }


    public ArrayList<ClothingItem> getClothingItemsOfSameType(String clothingType) {
        boolean requestedTypeFound = false;
        ArrayList<ClothingItem> clothingItems = clothingDataAccessService.getClothingItems();
        ArrayList<ClothingItem> clothingItemByType = new ArrayList<ClothingItem>();

        for (ClothingItem clothing : clothingItems) {
            if (clothing.getType().equals(clothingType) && !clothing.getIsInStock()) {
//
                clothingItemByType.add(clothing);
                requestedTypeFound = true;
            }
        }

        if (!requestedTypeFound) {
            throw new IllegalStateException("We do not have this type of clothing");
        }
        return clothingItemByType;
    }


//    public ClothingItem getOneClothingItem(String clothingName) {
//        return clothingDataAccessService.getClothingItems().stream().filter(clothing->clothing.getDescription()
//                        .equals(clothingName))
//                .findFirst()
//               .orElseThrow(new IllegalArgumentException(clothingName + "not in stock!"));
//    }

    public ClothingItem getOneClothingItem(String clothingName) {

        boolean requestedTypeFound = false;
        ArrayList<ClothingItem> clothingItems = clothingDataAccessService.getClothingItems();
        ClothingItem clothingItem = null;
        for (ClothingItem clothing : clothingItems) {
            if (clothing.getDescription().equals(clothingName) && !clothing.getIsInStock()) {
                clothingItem = clothing;
                requestedTypeFound = true;
            }
        }

        if (!requestedTypeFound) {
            throw new IllegalStateException("We do not have this type of clothing");
        }
        return clothingItem;
    }


    public void addClothingItem(ClothingItem clothingItem) {
        clothingDataAccessService.addClothingItem(clothingItem);
    }


    public void removeOneClothingItem(String clothingItemDescription){
        ArrayList<ClothingItem> clothingItems = clothingDataAccessService.getClothingItems();
        boolean requestTypeFound = false;
        for(ClothingItem clothingItem:clothingItems){
            if(clothingItem.getDescription().equals(clothingItemDescription)){
                requestTypeFound = true;
                clothingDataAccessService.removeClothingItem(clothingItem);
            }
        }if (!requestTypeFound) {
            throw new IllegalStateException("sorry this Item does not exist!");
        }
    }


    public void updateClothingItem(ClothingItem updatedClothingItem) {
        ArrayList<ClothingItem> clothingItems = clothingDataAccessService.getClothingItems();
        boolean requestTypeFound = false;
        for (ClothingItem clothingItem :clothingItems) {
           if (clothingItem.getId() == updatedClothingItem.getId()) {
               requestTypeFound = true;
               clothingItem.setPrice(updatedClothingItem.getPrice());
               clothingItem.setColor(updatedClothingItem.getColor());
               clothingItem.setSize(updatedClothingItem.getSize());
               clothingItem.setDescription(updatedClothingItem.getDescription());
               clothingItem.setMaterial(updatedClothingItem.getMaterial());
               clothingItem.setInStock(updatedClothingItem.getIsInStock());
           }
        }

        if (!requestTypeFound) {
            throw new IllegalStateException("we cannot update this item");
        }
    }



}