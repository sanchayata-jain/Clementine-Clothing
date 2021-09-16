package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "ethical-clothes/homepage")
public class ClothingController {
    private ClothingService clothingService;

    // Constructor
    public ClothingController(ClothingService clothingService) {

        this.clothingService = clothingService;
    }

    //methods
    @GetMapping("/exploreRange")
    public List<ClothingItem> getAllClothingItems() {
        return clothingService.getClothingItems();
    }

    @GetMapping("/{clothingType}")
    public List<ClothingItem> getClothingItemsByType(@PathVariable("clothingType") String type) {
        return clothingService.getClothingItemsOfSameType(type);
    }

    @GetMapping("/{clothingSubtype}")
    public List<ClothingItem> getClothingItemBySub(@PathVariable("clothingSubtype") String subtype) {
        return clothingService.getClothingItemsOfSameSubtype(subtype);
    }

    @PostMapping
    public void addClothingItem(@RequestBody ClothingItem clothingItem) {
        clothingService.addClothingItem(clothingItem);
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
