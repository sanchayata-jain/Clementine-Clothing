package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import java.util.ArrayList;
import java.util.List;

public class ClothingService {
    private ClothingDAO clothingDAO;
    private ClothingDataAccessService clothingDataAccessService

    public ClothingService(ClothingDataAccessService clothingDataAccessService,ClothingDAO clothingDAO) {
        this.clothingDataAccessService = clothingDataAccessService;
        this.clothingDAO = clothingDAO;
    }



    public List<ClothingItem> allClothingItems() {
        return clothingDAO.allClothingItems;
    }


    public ArrayList<ClothingItem> getClothingItemOfType(String clothingType) {
        boolean requestedTypeFound = false;
        ArrayList<ClothingItem> clothingItems = clothingDAO.allClothingItems;
        ArrayList<ClothingItem> clothingItemByType = new ArrayList<ClothingItem>();

        for (ClothingItem clothing : clothingItems) {
            if (clothing.getType().equals(clothingType) && clothing.getHasBeenBought == false) {
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


    public ClothingItem getOneClothingItem(String clothingName) {
        return clothingDAO.allClothingItems.stream().filter(clothing->clothing.getName()
                        .equals(clothingName))
                .findFirst()
                .orElseThrow(new IllegalStateException(clothingName
                        +"not in stock!"));
    }
    public void addClothingItem(ClothingItem clothingItem) {

        if (clothingItem.getHasBeenBrought == true) {
            clothingDataAccessService.addClothingItem(clothingItem);
            //I don't know if this is right? Ideas?//
        }
    }
    public void removeOneClothingItem(String clothingItemDescription){
        ArrayList<ClothingItem> clothingItems = clothingDAO.allClothingItems;
        boolean requestTypeFound=false;
        for(ClothingItem clothingItem:clothingItems){
            if(clothingItem.getDescription().equals(clothingItemDescription)){
                requestTypeFound=true
                clothingDataAccessService.removeClothingItem(clothingItem);
            }
        }if(!requestTypeFound){
            throw new IllegalStateException("sorry this Item does not exist!")

        }
    }
    /*public void updateClothingItems(long iD){
        ArrayList<ClothingItem> clothingItems = clothingDAO.allClothingItems;
        boolean requestTypeFound=false;
        for(ClothingItem cl:clothingItems){
           if(cl.getId().equals(iD)&& hasBeenBrought==false;){
            requestTypeFound=true;
            cl.setId(iD);
           }
        }if(!requestTypeFound){
            throw new IllegalStateException("we cannot update this item")
        }
    }*/
    //This is not doing the right thing//



}