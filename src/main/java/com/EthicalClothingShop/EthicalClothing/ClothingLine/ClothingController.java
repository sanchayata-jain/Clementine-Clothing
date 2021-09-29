package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "ethical-clothes")
public class ClothingController {
    private ClothingService clothingService;

    // Constructor
    public ClothingController(ClothingService clothingService) {
        this.clothingService = clothingService;
    }

    //methods
    @GetMapping("/explore-range")
    public List<ClothingItem> getAllClothingItems() {
        return clothingService.getClothingItems();
    }

    @GetMapping("/{clothingType}")
    public List<ClothingItem> getClothingItemsByType(@PathVariable("clothingType") String type) {
        return clothingService.getClothingItemsOfSameType(type);
    }

    @GetMapping("/clothing-sub/{clothingSubtype}")
    public ArrayList<ClothingItem> getClothingItemBySub(@PathVariable("clothingSubtype") String subtype) {
        ArrayList<ClothingItem> clothingItemsBySubType = new ArrayList<ClothingItem>();
        try {
            clothingItemsBySubType = clothingService.getClothingItemsOfSameSubtype(subtype);
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }

        return clothingItemsBySubType;
    }

    @PostMapping("add-clothing-item")    //to add an entire item of clothing to the clothing_items_inventory
    public void addClothingItem(@RequestBody ClothingItem clothingItem) {
        clothingService.addClothingItem(clothingItem);
    }

    @PostMapping("/add-type")
    public void addClothingType(@RequestParam String typeName) {
        clothingService.addClothingType(typeName);
    }


    @PostMapping("/add-subtype")
    public void addClothingSubtype(@RequestParam String subtypeName) {
        clothingService.addClothingSubtype(subtypeName);
    }

    @PostMapping("/add-material")
    public void addClothingMaterial(@RequestParam String materialName) {
        clothingService.addClothingMaterial(materialName);
    }

    @PostMapping("/add-color")
    public void addClothingColor(@RequestParam String colorName) {
        clothingService.addClothingColor(colorName);
    }

    @PostMapping("/add-size")
    public void addClothingSize(@RequestParam String sizeName) {
        clothingService.addClothingSize(sizeName);
    }

    @DeleteMapping("{barcode}")
    public void removeAllClothingItemsOfSameBarcode(@PathVariable("barcode") int id) {
        clothingService.removeClothingItem(id);
    }

    @PutMapping
    public void editClothingItem(@RequestParam double price,
                                 @RequestParam int quantity,
                                 @RequestParam int clothingId) {
        clothingService.updateClothingItem(price, quantity, clothingId);
    }

}
