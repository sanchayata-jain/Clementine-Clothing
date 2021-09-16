package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClothingDAO {
    public void addClothingItem(ClothingItem clothingItem);
    public void removeClothingItem(ClothingItem clothingItem);
    public void updateClothingItem(double price, int quantity, int clothing_id);
    //public List<ClothingItem> ClothingItems();

}