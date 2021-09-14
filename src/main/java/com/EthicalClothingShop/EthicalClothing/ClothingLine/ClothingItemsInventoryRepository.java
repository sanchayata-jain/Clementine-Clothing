// DATA ACCESS LAYER

package com.EthicalClothingShop.EthicalClothing.ClothingLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingItemsInventoryRepository extends JpaRepository<ClothingItemsInventory,Integer> {

}
